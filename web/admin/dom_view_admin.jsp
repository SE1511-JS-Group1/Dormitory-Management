<%-- 
    Document   : dom_view_admin
    Created on : Feb 16, 2022, 12:44:34 PM
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
        <link rel='icon' href='../images/logo.png'>   
        <link href="../css/overview.css" rel="stylesheet">
    </head>
    <body>
        <div style="position: fixed;left: 0;top:  0;height: 100px;width: 100%;z-index: 2;">
            <c:import url="admin_header.jsp"></c:import>
            </div>
            <section class="vh-100" style="background-color: #eee;">
                <div class="container py-5 h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col col-xl-6">
                            <div class="card" style="border-radius: 1rem;">
                                <div class="row g-0">
                                    <div class="col-md-12 col-lg-12 d-flex align-items-center">
                                        <div class="card-body p-4 p-lg-5 text-black">
                                            <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
                                                <div class="carousel-indicators">
                                                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                                                <c:forEach var="dom" items="${domInformations}">
                                                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${dom.getDom().getDomID().charAt(0)-64}" aria-label="Slide ${dom.getDom().getDomID().charAt(0)-63}"></button>
                                                </c:forEach>
                                            </div>
                                            <div class="carousel-inner">
                                                <c:forEach var="dom" items="${domInformations}">
                                                    <div class="carousel-item ${dom.getDom().getDomID() eq 'A'?'active':''}">
                                                        <img src="https://kokekokko.com.vn/wp-content/uploads/2021/06/tai-hinh-nen-mau-xam-dep-nhat-danh-rieng-cho-ban.jpg" class="d-block w-100" alt="...">
                                                        <table class="carousel-caption d-none d-md-block">
                                                            <colgroup>
                                                                <col width="150" span="1">
                                                                <col width="250" span="1">
                                                            </colgroup>
                                                            <tr>
                                                                <td>Dom </td>
                                                                <td>${dom.getDom().getDomName()}</td>
                                                            </tr>
                                                            <tr>
                                                                <td>Manager </td>
                                                                <td>
                                                                    <c:if test="${dom.getDomManagers().size()==0}">
                                                                        Không có 
                                                                    </c:if>
                                                                    <c:forEach var="manager" items="${dom.getDomManagers()}">
                                                                        ${manager.getRegency()}: ${manager.getName()} 
                                                                    </c:forEach>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Room#</td>
                                                                <td>${dom.getTotalRoom()}</td>
                                                            </tr>
                                                            <tr>
                                                                <td>Bed#</td>
                                                                <td>${dom.getTotalBed()}</td>
                                                            </tr>
                                                            <tr>
                                                                <td>Boarder#</td>
                                                                <td>${dom.getBookedBed()}</td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="2">
                                                                    <c:choose>
                                                                        <c:when test="${dom.getTotalRoom()>=80}">
                                                                            <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#cannotadd">
                                                                                Add new room
                                                                            </button>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#addroomfor${dom.getDom().getDomID()}">
                                                                                Add new room
                                                                            </button>
                                                                        </c:otherwise>
                                                                    </c:choose>                                                                    
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <!-- Modal Add new Room -->
                                                        <div class="modal fade" id="addroomfor${dom.getDom().getDomID()}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                            <div class="modal-dialog">
                                                                <div style="height: 100px;"></div>
                                                                <form action="addroom" class="modal-content">
                                                                    <input type="hidden" name="domid" value="${dom.getDom().getDomID()}">
                                                                    <div class="modal-header">
                                                                        <h5 class="modal-title text-center" id="exampleModalLabel">Add new room</h5>
                                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                        <p class="text-center">Complete Room information!</p>
                                                                        <table>
                                                                            <colgroup>
                                                                                <col width="150" span="1"> 
                                                                                <col width="300" span="1"> 
                                                                            </colgroup>
                                                                            <tr>
                                                                                <th>Floor</th>
                                                                                <td>
                                                                                    <input name="floor" type="radio" value="1" ${domDAO.checkFloorFull(dom.getDom().getDomID(),1)?'checked':'disabled'}> 1  
                                                                                    <input name="floor" type="radio" value="2" ${domDAO.checkFloorFull(dom.getDom().getDomID(),2)?'checked':'disabled'}> 2  
                                                                                    <input name="floor" type="radio" value="3" ${domDAO.checkFloorFull(dom.getDom().getDomID(),3)?'checked':'disabled'}> 3  
                                                                                    <input name="floor" type="radio" value="4" ${domDAO.checkFloorFull(dom.getDom().getDomID(),4)?'checked':'disabled'}> 4  
                                                                                    <input name="floor" type="radio" value="5" ${domDAO.checkFloorFull(dom.getDom().getDomID(),5)?'checked':'disabled'}> 5  
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th>Category</th>
                                                                                <td>
                                                                                    <select name="category" class="form-select">
                                                                                        <c:forEach var="category" items="${categorys}">                                                                                            
                                                                                        <option selected value="${category.getCategoryID()}">${category.getCategoryName()}</option>
                                                                                        </c:forEach>
                                                                                    </select>
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </div>
                                                                    <div class="modal-footer">
                                                                        <button type="submit" class="btn btn-outline-info">Add</button>
                                                                        <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Close</button>
                                                                    </div>
                                                                </form>
                                                            </div>
                                                        </div>  
                                                    </div>
                                                </c:forEach>
                                                <div class="carousel-item">
                                                    <img src="https://kokekokko.com.vn/wp-content/uploads/2021/06/tai-hinh-nen-mau-xam-dep-nhat-danh-rieng-cho-ban.jpg" class="d-block w-100" alt="...">
                                                    <div class="carousel-caption d-none d-md-block">
                                                        <button type="button" style="" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#notices">
                                                            Add new Dom <h1>+</h1>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- Modal Can't Add Room -->
                                            <div class="modal fade" id="cannotadd" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div style="height: 100px;"></div>
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title text-center" id="exampleModalLabel">Add new room</h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <p class="text-center">
                                                                You can't add new room in this Dom!
                                                                <br>It's full! Try another Dom or create new!</p>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Close</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- Modal Add Dom-->
                                            <div class="modal fade" id="notices" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div style="height: 100px;"></div>
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title text-center" id="exampleModalLabel">Add new Dom</h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <p class="text-center">
                                                                A new Dom will be add in the system!
                                                                <br>Are you sure about that?</p>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <form action="dom" method="post">
                                                                <button type="submit" class="btn btn-outline-info">Yes</button>
                                                            </form>
                                                            <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">No</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                                <span class="visually-hidden">Previous</span>
                                            </button>
                                            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                                <span class="visually-hidden">Next</span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <div style="position: fixed;left: 0;bottom: 0;height: 100px;width: 100%;border-top: 1px dotted black;">
            <c:import url="admin_footer.jsp"></c:import>
        </div>
        <script src="../js/checkJS.js"></script>
    </body>
</html>
