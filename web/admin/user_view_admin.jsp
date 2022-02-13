<%-- 
    Document   : home
    Created on : 16-Jan-2022, 16:39:49
    Author     : lenovo_thinkpad
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
        <link rel='icon' href='https://by.com.vn/xQTXSg'>   
        <link href="../css/overview.css" rel="stylesheet">
    </head>
    <body>
        <div style="position: fixed;left: 0;top:  0;height: 100px;width: 100%;z-index: 2;">
            <c:import url="admin_header.jsp"></c:import>
            </div>
            <section class="h-100 gradient-form" style="background-color: #eee;">
                <div class="container py-5 h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-md-12">
                            <div class="card rounded-3 text-black">
                                <div class="row g-0">
                                    <div class="col-md-2 d-flex align-items-center gradient-custom-2">
                                        <div class="container">
                                            <div class="row">
                                                <div class="col-md-10" style="margin-left: 5%;">
                                                    <a type="button" class="btn btn-outline-info btn-field-col pushin" href="#"><h3>View</h3></a>
                                                </div>
                                            </div>                           
                                            <div style="height: 30px;"></div>
                                            <div class="row">
                                                <div class="col-md-10" style="margin-left: 5%;">                                                
                                                    <a type="button" class="btn btn-outline-info btn-field-col" href="#"><h3>Authorize</h3></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-1">
                                    </div>
                                    <div class="col-md-8">
                                        <div class="card-body p-md-5 mx-md-4">
                                            <div class="text-center">
                                                <img src="https://by.com.vn/xQTXSg" style="width: 185px;" alt="logo">
                                            </div>
                                            <table style="margin: 50px auto;border: 2px solid black;" >
                                                <colgroup>
                                                    <col width="200" span="1">
                                                    <col width="200" span="1">
                                                    <col width="150" span="1">
                                                    <col width="100" span="1">
                                                </colgroup>
                                                <tr>
                                                    <th>
                                                        User Name
                                                    </th>
                                                    <th>
                                                        Regency
                                                    </th>
                                                    <th>
                                                        Information
                                                    </th>
                                                    <th>
                                                        Action
                                                    </th>  
                                                </tr>
                                            <c:forEach var="user" items="${sessionScope.accounts}">
                                                <tr>
                                                    <td>
                                                        ${user.getUserName()}
                                                    </td>
                                                    <td>
                                                        ${user.getRole()==1?"admin":user.getRole()==2?"staff":"boarder"}
                                                    </td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${user.getRole()==1}">
                                                                <button type="button" data-bs-toggle="modal" onclick="loadUserInformation('Hoàng Trần Đức', 'Male', '19-05-2001', '@gmail.com', '0123456789');" data-bs-target="#accountInformation" class="btn btn-outline-info" style="margin: 5px 10px;">
                                                                    View
                                                                </button>
                                                            </c:when>
                                                            <c:when test="${user.getRole()==2}">
                                                                <button type="button" data-bs-toggle="modal" onclick="loadUserInformation('${sessionScope.domManagerDAO.getOne(user.getUserName()).getName()}', '${sessionScope.domManagerDAO.getOne(user.getUserName()).isGender()?"Male":"Female"}', '${sessionScope.domManagerDAO.getOne(user.getUserName()).getDateOfBirth()}', '${sessionScope.domManagerDAO.getOne(user.getUserName()).getEmail()}', '${sessionScope.domManagerDAO.getOne(user.getUserName()).getPhoneNumber()}');" data-bs-target="#accountInformation" class="btn btn-outline-info" style="margin: 5px 10px;">
                                                                    View
                                                                </button>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <button type="button" data-bs-toggle="modal" onclick="loadUserInformation('${sessionScope.boarderDAO.getOne(user.getUserName()).getBoarderName()}', '${sessionScope.domManagerDAO.getOne(user.getUserName()).isGender()?"Male":"Female"}', '${sessionScope.domManagerDAO.getOne(user.getUserName()).getDateOfBirth()}', '${sessionScope.domManagerDAO.getOne(user.getUserName()).getEmail()}', '${sessionScope.domManagerDAO.getOne(user.getUserName()).getPhoneNumber()}');" data-bs-target="#accountInformation" class="btn btn-outline-info" style="margin: 5px 10px;">
                                                                    View
                                                                </button>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </td>
                                                    <td>
                                                        Delete
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                        <div class="modal fade" id="accountInformation" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content" style="z-index: 3;">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="exampleModalLabel">Account Information</h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="container">
                                                            <div class="row">
                                                                <div class="col-md-3">
                                                                    Full Name:
                                                                </div>
                                                                <div class="col-md-5">
                                                                    <input class="account_information" id="chosenFullName" type="text" value="" style="border: none;" disabled>                                                                        
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-3">
                                                                    Gender:
                                                                </div>
                                                                <div class="col-md-5">
                                                                    <input class="account_information" id="chosenGender" type="text" value="" style="border: none;" disabled>                                                                        
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-3">
                                                                    Date of birth
                                                                </div>
                                                                <div class="col-md-5">
                                                                    <input class="account_information" id="chosenDOB" type="text" value="" style="border: none;" disabled>                                                                    
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-3">
                                                                    Email:
                                                                </div>
                                                                <div class="col-md-5">
                                                                    <input class="account_information" id="chosenEmail" type="text" value="" style="border: none;" disabled>                                                                    
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-3">
                                                                    Phone:
                                                                </div>
                                                                <div class="col-md-5">
                                                                    <input class="account_information" id="chosenPhone" type="text" value="" style="border: none;" disabled>                                                                    
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Close</button>
                                                        <!--<button type="button" class="btn btn-primary">Save changes</button>-->
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
        <script src="../js/checkJS.js"></script>
    </body>
</html>
