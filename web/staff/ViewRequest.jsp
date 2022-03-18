<%-- 
    Document   : ViewRequest
    Created on : Mar 11, 2022, 1:13:22 AM
    Author     : Dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
        <link rel='icon' href='../images/logo.png'>   
        <link href="../css/overview.css" rel="stylesheet">
        <link href="../css/violation_manage.css" rel="stylesheet">
        <title>Dormitory Management System</title>
    </head>
    <body>
        <div style="position: fixed;left: 0;top:  0;height: 100px;width: 100%;z-index: 2;">
            <c:import url="staff_header.jsp"></c:import>
            </div>
            <h1>Boarder List</h1>
            <section class="h-100 gradient-form" style="background-color: #eee;">
                <div class="container py-5 h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-xl-12">
                            <div class="card rounded-3 text-black">
                                <div class="row g-0">
                                    <div class="col-lg-2 d-flex align-items-center" style="background-color: #c9d8c9;">
                                        <div class="container">
                                            <div class="row">
                                                <style>
                                                    .pushin{
                                                        margin-left: 7%;
                                                        margin-top: 20px;
                                                        width: 85%;
                                                        padding: 10px;
                                                        background-color: #879091;
                                                        border: none;
                                                    }
                                                    .btn-map-field-col{
                                                        margin-left: 7%;
                                                        margin-top: 20px;
                                                        width: 85%;
                                                        padding: 10px;
                                                        background-color: #FFFFFF;
                                                        border: none;
                                                    }
                                                </style>
                                            <c:forEach var="d" items="${doms}">
                                                <form action="request" method="get">
                                                    <input type="hidden" name="dom" value="${d.getDomID()}"/>
                                                    <button type="submit" class="btn btn-outline-info ${d.getDomID() eq dom.getDomID()?'pushin':'btn-map-field-col'}"><h6>${d.getDomName()}</h6></button>    
                                                </form>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-10">
                                    <div class="card-body p-md-9 mx-md-4">
                                        <div class="container">
                                            <c:choose>
                                                <c:when test="${boarding.size()==0&&editing.size()==0}">
                                                    <h5 class="text-center">No request in this Dom</h5>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:if test="${boarding.size()>0}">
                                                        <table>
                                                            <colgroup>
                                                                <col width="200" span="1">
                                                                <col width="150" span="1">
                                                                <col width="150" span="1">
                                                                <col width="150" span="1">
                                                                <col width="100" span="1">
                                                                <col width="100" span="1">
                                                                <col width="150" span="1">
                                                            </colgroup>
                                                            <thead>
                                                                <tr style="text-align: center;">
                                                                    <th>Full Name</th>
                                                                    <th>Gender</th>
                                                                    <th>Date of Birth</th>
                                                                    <th>Phone Number</th>
                                                                    <th>Room</th>
                                                                    <th>Bed No</th> 
                                                                    <th>Action</th>
                                                                </tr>
                                                            </thead>
                                                            <c:forEach items="${boarding}" var="b">
                                                                <tr style="text-align: center;">
                                                                    <td>${b.getBoarder().getBoarderName()}</td>
                                                                    <td>${b.getBoarder().isGender()?"Male":"Female"}</td>
                                                                    <td>${b.getBoarder().getDateOfBirth()}</td>
                                                                    <td>${b.getBoarder().getPhoneNumber()}</td>
                                                                    <td>${b.getRoom()}</td>
                                                                    <td>${b.getBedNo()}</td>
                                                                    <td>
                                                                        <button type="button" class="btn btn-outline-success" data-bs-toggle="modal" data-bs-target="#acceptRequest" onclick="loadModal('${b.getBoarder().getBoarderID()}', '${b.getRoom().getRoomID()}', '${b.getBedNo()}', 'Accept');">Accept</button>
                                                                        <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#acceptRequest"onclick="loadModal('${b.getBoarder().getBoarderID()}', '${b.getRoom().getRoomID()}', '${b.getBedNo()}', 'Deny');">Deny</button>
                                                                    </td>
                                                                </tr>

                                                            </c:forEach>
                                                        </table>
                                                    </c:if>
                                                    <c:if test="${editing.size()>0}">
                                                        <table>
                                                            <colgroup>
                                                                <col width="200" span="1">
                                                                <col width="150" span="1">
                                                                <col width="150" span="1">
                                                                <col width="150" span="1">
                                                                <col width="100" span="1">
                                                                <col width="100" span="1">
                                                                <col width="200" span="1">
                                                            </colgroup>
                                                            <thead>
                                                                <tr><th colspan="7"><h5 class="text-center">Edit Information</h5></th></tr>
                                                                <tr style="text-align: center;">
                                                                    <th>Full Name</th>
                                                                    <th>Gender</th>
                                                                    <th>Date of Birth</th>
                                                                    <th>Phone Number</th>
                                                                    <th>Email</th>
                                                                    <th>Job</th> 
                                                                    <th>Action</th>
                                                                </tr>
                                                            </thead>
                                                            <c:forEach items="${editing}" var="b">
                                                                <c:set var="boarder" value="${bdao.getBoarderById(b.getBoarderID())}" scope="page" />
                                                                <tr style="text-align: center;">
                                                                    <td>Old: ${boarder.getBoarderName()}<br>New: ${b.getBoarderName()}</td>
                                                                    <td>${boarder.isGender()?"Male":"Female"}<br>${b.isGender()?"Male":"Female"}</td>
                                                                    <td>${boarder.getDateOfBirth()}<br>${b.getDateOfBirth()}</td>
                                                                    <td>${boarder.getPhoneNumber()}<br>${b.getPhoneNumber()}</td>
                                                                    <td>${boarder.getEmail()}<br>${b.getEmail()}</td>
                                                                    <td>${boarder.getJob()}<br>${b.getJob()}</td>
                                                                    <td>
                                                                        <button type="button" class="btn btn-outline-success" data-bs-toggle="modal" data-bs-target="#editInfor" onclick="loadInfor('${b.getBoarderID()}', 'Accept')">Accept</button>
                                                                        <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#editInfor"onclick="loadInfor('${b.getBoarderID()}', 'Deny')">Deny</button>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </table>
                                                    </c:if>
                                                </c:otherwise>
                                            </c:choose>
                                            <!-- Modal Booking-->
                                            <form action="request" method="post" class="modal fade" id="acceptRequest" data-bs-backdrop="static" data-bs-keyboard="false" aria-labelledby="staticBackdropLabel" tabindex="-1" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div style="height: 200px;"></div>
                                                    <div action="request" method="post" class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="staticBackdropLabel">Booking Request</h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <input id="boarderid" type="hidden" name="boarderid" value=""/>
                                                            <input id="roomid" type="hidden" name="roomid" value=""/>
                                                            <input id="bedno" type="hidden" name="bedno" value=""/>
                                                            <input id="act" type="hidden" name="act" value=""/>
                                                            <h5>Are you sure about your action?</h5>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                            <button id="btnReq" type="submit" class="btn btn-primary">Accept</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                            <!-- Modal Editing-->
                                            <form action="edit" method="post" class="modal fade" id="editInfor" data-bs-backdrop="static" data-bs-keyboard="false" aria-labelledby="staticBackdropLabel" tabindex="-1" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div style="height: 200px;"></div>
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="staticBackdropLabel">Edit Information</h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <input id="boarder_id" type="hidden" name="boarderid" value=""/>
                                                            <input id="action" type="hidden" name="act" value=""/>
                                                            <h5>Are you sure about your action?</h5>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                            <button id="btnEdit" type="submit" class="btn btn-primary">Accept</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                            <form action="boarder" method="get" class="row">
                                                <div class="col-lg-4"></div>
                                                <input type="hidden" name="dom" value="${dom.getDomID()}"/>
                                                <button type="submit" class="btn btn-outline-info col-lg-4">View Boarder</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>


    <div style="left: 0;bottom: 0;height: 100px;width: 100%;border-top: 1px dotted black;">
        <c:import url="staff_footer.jsp"></c:import>
    </div>
    <script>
        function loadInfor(boarderid, act) {
            document.getElementById('boarder_id').value = boarderid;
            document.getElementById('action').value = act;
            document.getElementById('btnEdit').innerHTML = act;
        }
        function loadModal(boarderid, roomid, bedno, act) {
            document.getElementById('boarderid').value = boarderid;
            document.getElementById('roomid').value = roomid;
            document.getElementById('bedno').value = bedno;
            document.getElementById('act').value = act;
            document.getElementById('btnReq').innerHTML = act;
        }
    </script>
</body>

</html>

