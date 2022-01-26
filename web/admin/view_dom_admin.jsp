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
        <link rel='icon' href='images/logo.png'>   
        <link href="css/overview.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">
                        <div class="logo" style="width: 70px;">                            
                            <img src="images/logo.png" class="img-thumbnail" alt="logo">
                        </div>
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="#"></a>
                            </li>
                        </ul>
                        <form action="login" method="get">
                            <p class="text-center align-items-center fs-4" style="margin: auto 5px;">${sessionScope.account.getUserName()}</p>
                        </form>
                        <form action="logout" method="post">
                            <button class="btn btn-outline-danger" type="submit" style="width: 80px;">Logout</button>
                        </form>
                    </div>
                </div>
            </nav>
        </div>
        <section class="h-100 gradient-form" style="background-color: #eee;">
            <div class="container py-5 h-90">
                <div class="row d-flex justify-content-center align-items-center h-90">
                    <div class="col-xl-10">
                        <div class="card rounded-3 text-black">
                            <div class="row g-0">
                                <div class="col-lg-1 d-flex align-items-center gradient-custom-2">
                                    <table class="field-map">
                                        <c:forEach var="dom" items="${sessionScope.doms}">
                                            <tr>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${dom.getDomID() eq sessionScope.dom.getDomID()}">
                                                            <form action="viewdom" method="get">
                                                                <input type="hidden" name="dom" value="${dom.getDomID()}"/>
                                                                <button type="submit" class="btn btn-outline-info btn-map-field-col pushin" href="#"><h6>${dom.getDomName()}</h6></button>    
                                                            </form>
                                                        </c:when>
                                                        <c:otherwise>                                                        
                                                            <form action="viewdom" method="get">
                                                                <input type="hidden" name="dom" value="${dom.getDomID()}"/>
                                                                <button type="submit" class="btn btn-outline-info btn-map-field-col" href="#"><h6>${dom.getDomName()}</h6></button>   
                                                            </form>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                                <div class="col-lg-11">
                                    <div class="card-body p-md-9 mx-md-4">
                                        <div class="container map">
                                            <table style="margin: 50px auto;border: 2px solid black;" >
                                                <tr style="border: 2px solid black;">
                                                    <%!int i = 0;%>
                                                    <c:forEach var="roomStatus" items="${sessionScope.mapdom}">
                                                        <!--sessionScope.mapdom.get(room)*10-->
                                                        <c:if test="<%=i % 2 == 0%>">
                                                            <td>
                                                            </c:if>
                                                            <% i++;%>
                                                            <div class="map-block">
                                                                <button class="btn btn-outline-info map-${roomStatus.getStatus()}" style="margin: 5px 10px;">
                                                                    ${roomStatus.getRoom()}
                                                                </button>
                                                            </div>

                                                            <c:if test="<%=i % 2 == 0%>">
                                                            </td>
                                                        </c:if>
                                                        <c:if test="<%= i % 16 == 0%>">
                                                        </tr><tr style="border: 2px solid black;">
                                                        </c:if>
                                                    </c:forEach>
                                                </tr>
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
    </body>
</html>
