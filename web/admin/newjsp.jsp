<%-- 
    Document   : newjsp
    Created on : Jan 28, 2022, 9:15:13 PM
    Author     : XuanDinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
                                <div class="carousel-indicators">
                                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                                <c:forEach var="dom" items="${domInformations}">
                                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${dom.getDom().getDomID().charAt(0)-64}" aria-label="Slide ${dom.getDom().getDomID().charAt(0)-63}"></button>
                                </c:forEach>
                            </div>
                            <div class="carousel-inner">
                                <c:forEach var="dom" items="${domInformations}">
                                    <div class="carousel-item">
                                        <img src="../images/logo.png">
                                        <table>
                                            <tr>
                                                <td>Dom: </td>
                                                <td>${dom.getDom().getDomName()}</td>
                                            </tr>
                                            <tr>
                                                <td>Manager: </td>
                                                <td>
                                                    <c:if test="${dom.getDomManagers().size()==0}">
                                                        Không có Manager
                                                    </c:if>
                                                    <c:forEach var="manager" items="${dom.getDomManagers()}">
                                                        ${manager.getRegency()}: ${manager.getName()} 
                                                    </c:forEach>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </c:forEach>
                            </div>
                            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>
    </body>
</html>
