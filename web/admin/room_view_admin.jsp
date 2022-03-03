<%-- 
    Document   : view_dom_admin
    Created on : 23-Jan-2022, 17:38:43
    Author     : lenovo_thinkpad
--%>

<%@page import="model.Dom"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Dormitory Management System</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
        <link rel='icon' href='https://by.com.vn/xQTXSg'>   
        <link href="../css/overview.css" rel="stylesheet">
    </head>
    <body>
        <div style="position: fixed;left: 0;top:  0;height: 100px;width: 100%;z-index: 2;">
            <c:import url="admin_header.jsp"></c:import>
            </div>
            <section class="h-100 gradient-form" style="background-color: #eee;">
                <div style="height: 50px;"></div>
                <div class="container py-5 h-90">
                    <div class="row d-flex justify-content-center align-items-center h-90">
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
                                                <form action="room" method="get">
                                                    <input type="hidden" name="dom" value="${d.getDomID()}"/>
                                                    <button type="submit" class="btn btn-outline-info ${d.getDomID() eq dom.getDomID()?'pushin':'btn-map-field-col'}"><h6>${d.getDomName()}</h6></button>    
                                                </form>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-10">
                                    <div class="card-body p-md-9 mx-md-4">
                                        <div class="container map">
                                            <table style="margin: 50px auto;border: 2px solid black;" >
                                                <c:set var="j" value="6" scope="page" />
                                                <% for (int i = 5; i > 0; i--) {%>
                                                <c:set var="j" value="${j - 1}" scope="page" />
                                                <tr style="border: 2px solid black;">
                                                    <td>
                                                        <h6>&ensp;Floor <%= i%></h6>
                                                    </td> 
                                                    <c:set var="count" value="0" scope="page" />
                                                    <c:forEach var="roomStatus" items="${mapdom}">
                                                        <c:if test="${roomStatus.getRoom().getFloor()== j}">
                                                            <c:set var="count" value="${count +1}" scope="page" />
                                                            <c:if test="${count%2==1}">
                                                                <td>
                                                                </c:if>                                                                
                                                                <div class="map-block">
                                                                    <button type="button" data-bs-toggle="modal" onclick="" data-bs-target="#roomInformation${roomStatus.getRoom().getRoomID()}" class="btn btn-outline-info map-${roomStatus.getStatus()}" style="margin: 5px 10px;">
                                                                        ${roomStatus.getRoom()}
                                                                    </button>
                                                                    <!-- Modal Show information-->
                                                                    <div class="modal fade" id="roomInformation${roomStatus.getRoom().getRoomID()}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                                        <div class="modal-dialog" style="margin-top: 15%;">
                                                                            <div class="modal-content" style="z-index: 3;">
                                                                                <div class="modal-header">
                                                                                    <h5 class="modal-title" id="exampleModalLabel">Room Information</h5>
                                                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                                </div>
                                                                                <div class="modal-body">
                                                                                    <div class="container">
                                                                                        <div class="row">
                                                                                            <div class="col-md-3">
                                                                                                Name:
                                                                                            </div>
                                                                                            <div class="col-md-5">
                                                                                                <input class="room_information" id="chosenName" type="text" value="${roomStatus.getRoom()}" style="border: none;" disabled>                                                                        
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="row">
                                                                                            <div class="col-md-3">
                                                                                                Floor:
                                                                                            </div>
                                                                                            <div class="col-md-5">
                                                                                                <input class="room_information" id="chosenFloor" type="text" value="${roomStatus.getRoom().getFloor()}" style="border: none;" disabled>                                                                        
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="row">
                                                                                            <div class="col-md-3">
                                                                                                Availability:
                                                                                            </div>
                                                                                            <div class="col-md-5">
                                                                                                <input class="room_information" id="chosenStatus" type="text" value="${roomStatus.getBedAvailable()} Bed" style="border: none;" disabled>                                                                    
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="row">
                                                                                            <div class="col-md-3">
                                                                                                Gender:
                                                                                            </div>
                                                                                            <div class="col-md-5">
                                                                                                <input class="room_information" id="chosenGender" type="text" value="${roomStatus.getRoom().getCategory().isRoomGender()?"Male":"Female"}" style="border: none;" disabled>                                                                    
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="modal-footer">
                                                                                    <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Close</button>
                                                                                    <c:choose>
                                                                                        <c:when test="${roomStatus.getRoom().getRoomName() eq '101'}">
                                                                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" onclick="" data-bs-target="#roomDisable${roomStatus.getRoom().getRoomID()}" disabled>Disable</button>
                                                                                        </c:when>
                                                                                        <c:otherwise>
                                                                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" onclick="" data-bs-target="#roomDisable${roomStatus.getRoom().getRoomID()}" >Disable</button>
                                                                                        </c:otherwise>
                                                                                    </c:choose>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <!-- Modal Disable -->
                                                                    <div class="modal fade modal-second" id="roomDisable${roomStatus.getRoom().getRoomID()}" tabindex="-2" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                                        <div class="modal-dialog" style="margin-top: 15%;">
                                                                            <div class="modal-content" style="z-index: 3;">
                                                                                <div class="modal-header">
                                                                                    <h5 class="modal-title" id="exampleModalLabel">Disable Room</h5>
                                                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                                </div>
                                                                                <div class="modal-body">
                                                                                    <div class="container">
                                                                                        <p>This action will erase all existing data of the room<br>Are you sure about that?</p>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="modal-footer">
                                                                                    <form action="disableroom" method="post">
                                                                                        <input type="hidden" name="dom" value="${dom.getDomID()}">
                                                                                        <input type="hidden" name="room" value="${roomStatus.getRoom().getRoomID()}">
                                                                                        <button type="submit" class="btn btn-outline-info">Yes</button>                                                                
                                                                                    </form>
                                                                                    <form action="room" method="post">
                                                                                        <input type="hidden" name="dom" value="${dom.getDomID()}">
                                                                                        <button type="submit" class="btn btn-outline-danger">No</button>                                                           
                                                                                    </form>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <c:if test="${count%2==0}">
                                                                </td>
                                                            </c:if>
                                                        </c:if>
                                                    </c:forEach>
                                                </tr>
                                                <%}%>
                                            </table>

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
            <c:import url="admin_footer.jsp"></c:import>
        </div>
        <script src="../js/checkJS.js"></script>
    </body>
</html>
