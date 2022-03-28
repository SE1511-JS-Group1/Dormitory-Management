<%-- 
    Document   : boarderwallet
    Created on : Mar 21, 2022, 8:16:00 AM
    Author     : Dell
--%>
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
        <link href="../css/background.css" rel="stylesheet">
        <link rel='icon' href='../images/logo.png'>   
        <link href="../css/overview.css" rel="stylesheet"> 
    </head>
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
    <body>
        <div style="position: fixed;left: 0;top:  0;height: 100px;width: 100%;z-index: 2;">
            <c:import url="boarder_header.jsp"></c:import>
            </div>
            <section class="stars-container vh-100">
                <div class="container">
                    <div id="stars" style="position: fixed;"></div>
                    <div id="stars2" style="position: fixed;"></div>
                    <div id="stars3"style="position: fixed;"></div>
                    <div class="row" >
                        <section class="vh-100" style="position: absolute; left: 0;top: 0;">
                            <div class="container py-5 h-100">
                                <div class="row d-flex justify-content-center align-items-center h-100">
                                    <div class="col col-lg-10">
                                        <div class="row">
                                            <h3 class="text-center" style="font-family: cursive; color: #ffffff;">Your Fees</h3>
                                        </div>
                                        <div class="card" style="border-radius: 1rem;">
                                            <div class="row g-0">
                                                <div class="col-lg-2 d-flex align-items-center" style="background-color: #c9d8c9;border-bottom-left-radius: 15px;border-top-left-radius: 15px;">
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
                                                            <form action="wallet" method="post">
                                                                <button type="submit" class="btn btn-outline-info ${act eq 'view' ? 'pushin':'btn-map-field-col'}"><h6>View</h6></button>    
                                                        </form>
                                                        <form action="fee" method="post">
                                                            <button type="submit" class="btn btn-outline-info ${act eq 'pay' ? 'pushin':'btn-map-field-col'}"name="editvalue" value="1"><h6>Pay</h6></button>    
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-10">
                                                <div class="card-body p-md-9 mx-md-4">
                                                    <div class="container map" style="font-family: cursive;">
                                                        <c:choose>
                                                            <c:when test="${RoomFees.size()>0||ElectricWater.size()>0}">
                                                                <table class="table table-striped">
                                                                    <colgroup>
                                                                        <col width="100" span="1">
                                                                        <col width="80" span="1">
                                                                        <col width="150" span="1">
                                                                        <col width="70" span="1">
                                                                    </colgroup>
                                                                    <thead>
                                                                        <tr>
                                                                            <th scope="col">Deadline</th>
                                                                            <th scope="col">Amount</th>
                                                                            <th scope="col">Title</th>
                                                                            <th scope="col">Action</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <c:if test="${RoomFees.size()>0}">
                                                                            <c:forEach var="i" items="${RoomFees}">
                                                                                <tr >
                                                                                    <td style="height: 50px;">${i.getDeadline()}</td>
                                                                                    <td style="height: 50px;">${boarding.getRoom().getCategory().getRoomFee()}</td>
                                                                                    <td style="height: 50px;">Room fee for ${i.getMonth()}</td>
                                                                                    <td style="height: 50px;">
                                                                                        <button type="button" class="btn btn-outline-info" data-bs-toggle="modal"  data-bs-target="#rpaying${i.getBillID()}">Pay</button>
                                                                                        <div class="modal fade" id="rpaying${i.getBillID()}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                                                            <c:choose>
                                                                                                <c:when test="${balance>boarding.getRoom().getCategory().getRoomFee()}">
                                                                                                    <form action="paying" method="post" class="modal-dialog" style="margin-top: 15%;">
                                                                                                        <div  class="modal-content" style="z-index: 3;">
                                                                                                            <div class="modal-header">
                                                                                                                <h5 class="modal-title" id="exampleModalLabel">Paying</h5>
                                                                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                                                            </div>
                                                                                                            <div class="modal-body">
                                                                                                                <div class="container">
                                                                                                                    <div class="row">
                                                                                                                        <h5>Confirm to pay!</h5>
                                                                                                                    </div>
                                                                                                                    <input type="hidden" name="billid" value="${i.getBillID()}"/>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                            <div class="modal-footer">
                                                                                                                <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Cancel</button>
                                                                                                                <button type="submit" class="btn btn-outline-info" name="type" value="1">Pay</button>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </form>
                                                                                                </c:when>
                                                                                                <c:otherwise>
                                                                                                    <form action="wallet" method="get"class="modal-dialog" style="margin-top: 15%;">
                                                                                                        <div   class="modal-content" style="z-index: 3;">
                                                                                                            <div class="modal-header">
                                                                                                                <h5 class="modal-title" id="exampleModalLabel">Warning</h5>
                                                                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                                                            </div>
                                                                                                            <div class="modal-body">
                                                                                                                <div class="container">
                                                                                                                    <div class="row">
                                                                                                                        <h5>Your balance not enough to pay! Please top-up to continue!</h5>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                            <div class="modal-footer">
                                                                                                                <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Cancel</button>
                                                                                                                <button type="submit" class="btn btn-outline-info">Top-up</button>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </form>
                                                                                                </c:otherwise>
                                                                                            </c:choose>

                                                                                        </div>
                                                                                    </td>
                                                                                </tr>
                                                                            </c:forEach>
                                                                        </c:if>
                                                                        <c:if test="${ElectricWater.size()>0}">
                                                                            <c:forEach var="i" items="${ElectricWater}">
                                                                                <tr >
                                                                                    <td style="height: 50px;">${i.getDeadline()}</td>
                                                                                    <td style="height: 50px;">${i.getWaterAmount() + i.getEletricAmount()}</td>
                                                                                    <td style="height: 50px;">Electric and water bill for ${i.getMonth()}</td>
                                                                                    <td style="height: 50px;">
                                                                                        <button type="button" class="btn btn-outline-info" data-bs-toggle="modal"  data-bs-target="#ewpaying${i.getBillID()}">Pay</button>
                                                                                        <div class="modal fade" id="ewpaying${i.getBillID()}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                                                            <c:choose>
                                                                                                <c:when test="${balance>i.getWaterAmount() + i.getEletricAmount()}">
                                                                                                    <form action="paying" method="post" class="modal-dialog" style="margin-top: 15%;">
                                                                                                        <div  class="modal-content" style="z-index: 3;">
                                                                                                            <div class="modal-header">
                                                                                                                <h5 class="modal-title" id="exampleModalLabel">Paying</h5>
                                                                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                                                            </div>
                                                                                                            <div class="modal-body">
                                                                                                                <div class="container">
                                                                                                                    <div class="row">
                                                                                                                        <h5>Confirm to pay!</h5>
                                                                                                                    </div>
                                                                                                                    <input type="hidden" name="billid" value="${i.getBillID()}"/>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                            <div class="modal-footer">
                                                                                                                <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Cancel</button>
                                                                                                                <button type="submit" class="btn btn-outline-info" name="type" value="2">Pay</button>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </form>
                                                                                                </c:when>
                                                                                                <c:otherwise>
                                                                                                    <form action="wallet" method="get"class="modal-dialog" style="margin-top: 15%;">
                                                                                                        <div   class="modal-content" style="z-index: 3;">
                                                                                                            <div class="modal-header">
                                                                                                                <h5 class="modal-title" id="exampleModalLabel">Warning</h5>
                                                                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                                                            </div>
                                                                                                            <div class="modal-body">
                                                                                                                <div class="container">
                                                                                                                    <div class="row">
                                                                                                                        <h5>Your balance not enough to pay! Please top-up to continue!</h5>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                            <div class="modal-footer">
                                                                                                                <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Cancel</button>
                                                                                                                <button type="submit" class="btn btn-outline-info">Top-up</button>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </form>
                                                                                                </c:otherwise>
                                                                                            </c:choose>
                                                                                        </div>
                                                                                    </td>
                                                                                </tr>
                                                                            </c:forEach>
                                                                        </c:if>
                                                                    </tbody>
                                                                </table>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div class="col-md-12" style="font-family: fantasy">
                                                                    You don't have any fees to pay yet!
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                </div>
                                            </div>                            
                                        </div>
                                    </div>
                                </div>
                                <div style="position: fixed;left: 0;bottom: -10px;width: 100%;z-index: 2;border-top: 1px dotted black;">
                                    <c:import url="boarder_footer.jsp"></c:import>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </section>
        <script src="../js/checkJS.js"></script>
    </body>
</html>
