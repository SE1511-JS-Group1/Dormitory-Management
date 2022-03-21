<%-- 
    Document   : boarder_room_view
    Created on : Mar 3, 2022, 7:10:41 PM
    Author     : Admin
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
    <body>
        <div style="position: fixed;left: 0;top:  0;height: 100px;width: 100%;z-index: 2;">
            <c:import url="boarder_header.jsp"></c:import>
            </div>
            <section class="stars-container vh-100">
                <div class="container">
                    <div id="stars"></div>
                    <div id="stars2"></div>
                    <div id="stars3"></div>
                    <div class="row">
                        <section class="vh-100" style="position: fixed; left: 0;top: 0;">
                            <div class="container py-5 h-100">
                                <div class="row d-flex justify-content-center align-items-center h-100">
                                    <div class="col col-xl-10">
                                        <div class="row">
                                            <h3 class="text-center" style="font-family: cursive; color: #ffffff;">Your Room</h3>
                                        </div>
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
                                                            <form action="room" method="post">
                                                                <input type="hidden" name="dom" value="A"/>
                                                                <button type="submit" class="btn btn-outline-info ${act eq 'book' ? 'pushin':'btn-map-field-col'}"><h6>Book</h6></button>    
                                                        </form>
                                                        <form action="loadrenew" method="post">
                                                            <button type="submit" class="btn btn-outline-info ${act eq 'renew' ? 'pushin':'btn-map-field-col'}"><h6>Renew</h6></button>    
                                                        </form>
                                                        <form action="load" method="post">
                                                            <button type="submit" class="btn btn-outline-info ${act eq 'change' ? 'pushin':'btn-map-field-col'}"name="editvalue" value="1"><h6>Change</h6></button>    
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-10">
                                                <div class="card-body p-md-9 mx-md-4">
                                                    <div class="container map">
                                                        <div class="spinner-grow text-muted"></div>
                                                        <div class="spinner-grow text-primary"></div>
                                                        <div class="spinner-grow text-success"></div>
                                                        <div class="spinner-grow text-info"></div>
                                                        <div class="spinner-grow text-warning"></div>
                                                        <div class="spinner-grow text-danger"></div>
                                                        <div class="spinner-grow text-secondary"></div>
                                                        <div class="spinner-grow text-dark"></div>
                                                        <div class="spinner-grow text-light"></div>
                                                        <div>
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    <div style="text-align: center">
                                                                        <h7>Information room</h7>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                        <div class="container">
                                                                            <div class="row">
                                                                                <div class="col-md-5">
                                                                                    Dom
                                                                                </div>
                                                                                <div class="col-md-5">
                                                                                    <input class="room_information" id="chosenName" type="text" value="${infor.getRoom().getDom().getDomName()}" style="border: none;" disabled>                                                                        
                                                                                </div>
                                                                            </div>
                                                                            <div class="row">
                                                                                <div class="col-md-5">
                                                                                    Floor:
                                                                                </div>
                                                                                <div class="col-md-5">
                                                                                    <input class="room_information" id="chosenName" type="text" value="${infor.getRoom().getFloor()}" style="border: none;" disabled>                                                                        
                                                                                </div>
                                                                            </div>
                                                                            <div class="row">
                                                                                <div class="col-md-5">
                                                                                    Room:
                                                                                </div>
                                                                                <div class="col-md-5">
                                                                                    <input class="room_information" id="chosenName" type="text" value="${infor.getRoom()}" style="border: none;" disabled>                                                                        
                                                                                </div>
                                                                            </div>
                                                                            <div class="row">
                                                                                <div class="col-md-5">
                                                                                    Bed:
                                                                                </div>
                                                                                <div class="col-md-5">
                                                                                    <input class="room_information" id="chosenName"  name="Bedno"type="text" value="${infor.getBedNo()}" style="border: none;" disabled>                                                                        
                                                                                </div>
                                                                            </div>
                                                                            <div class="row">
                                                                                <div class="col-md-5">
                                                                                    Start Date:
                                                                                </div>
                                                                                <div class="col-md-5">
                                                                                    <input class="room_information" id="chosenName" type="text" value="${infor.getStartDate()}" style="border: none;" disabled>                                                                        
                                                                                </div>
                                                                            </div>
                                                                            <div class="row">
                                                                                <div class="col-md-5">
                                                                                    End Date:
                                                                                </div>
                                                                                <div class="col-md-5">
                                                                                    <input class="room_information" id="chosenName" type="text" value="${infor.getEndDate()}" style="border: none;" disabled>                                                                        
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                </div>
                                                                <div class="col-md-6">
                                                                    <c:choose>
                                                                        <c:when test="${month == null}">
                                                                            <div style="text-align: center">
                                                                                <h7>Number of month need to renew:</h7>
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                <div class="container">
                                                                                    <div class="row" style="height: 10px;">
                                                                                    </div>
                                                                                    <form action="renew" method="get" style="text-align: center;">
                                                                                        <input type="number" name="month" value="" min="1" max="12" style="margin-left: auto;margin-right: auto"/>
                                                                                        <button type="submit" class="btn btn-outline-info ${ 1==1?'pushin':'btn-map-field-col'}"style="width: 150px;margin-left: auto;margin-right: auto;display: block"><h6>Renew</h6></button>    
                                                                                    </form>
                                                                                </div>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <h5 style="margin-top: 15%;margin-left: auto;margin-right: auto">You have extended the ${month} month, waiting for a response. </h5>
                                                                        </c:otherwise>
                                                                    </c:choose>

                                                                </div>
                                                            </div>
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
                </div>
                <div style="position: absolute;left: 0;bottom: 0;width: 100%;z-index: 2;border-top: 1px dotted black;">
                    <c:import url="boarder_footer.jsp"></c:import>
                </div>
            </div>
        </section>
        <script src="../js/checkJS.js"></script>
    </body>
</html>
