<%-- 
    Document   : User View
    Created on : 16-Jan-2022, 16:39:49
    Author     : DucHT
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
                                    <div class="container-fluid">
                                        <div class="row flex-nowrap">
                                            <div class="col-auto col-md-4 col-xl-3 px-sm-3 px-0" style="background-color: #cccccc">
                                                <div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
                                                    <div style="height: 300px;"></div>
                                                    <style>
                                                        .page-menu{
                                                            width: 250px;
                                                            height: 70px;
                                                            border: 1px solid whitesmoke;
                                                            border-radius: 10px;
                                                        }
                                                        .page-menu:hover{
                                                            background-color: #FFFFFF;
                                                        }
                                                        .push-in{
                                                            background-color: #999999;
                                                        }
                                                        .page-menu h6{
                                                            margin-top: 15px;
                                                            text-align: center;
                                                        }
                                                    </style>
                                                    <a href="user" id="user_field" class="nav-link align-middle px-0 page-menu push-in">
                                                        <h6 style="text-align: center; margin-top: 15px;">View</h6> 
                                                    </a>
                                                    <div style="height: 50px;"></div>
                                                    <a href="authorize" id="authorize_field" class="nav-link align-middle px-0 page-menu">
                                                        <h6>Authorize</h6>
                                                    </a>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="col-auto col-md-4 col-xl-1 px-sm-1 px-0"></div>
                                            <div class="col py-6">
                                                <div class="col-md-12">
                                                    <div class="card-body p-md-5 mx-md-4">
                                                        <table style="margin: 50px auto;border: 2px solid black;" >
                                                            <colgroup>
                                                                <col width="200" span="1">
                                                                <col width="200" span="1">
                                                                <col width="150" span="1">
                                                                <col width="100" span="1">
                                                            </colgroup>
                                                            <tr>
                                                                <th>
                                                                    &ensp; User Name
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
                                                        <c:forEach var="user" items="${accounts}">
                                                            <tr>
                                                                <td>
                                                                    &ensp;${user.getUserName()}
                                                                </td>
                                                                <td>
                                                                    ${user.getRole()==1?"admin":user.getRole()==2?"staff":"boarder"}
                                                                </td>
                                                                <td>
                                                                    <c:if test="${user.getRole()==1}">
                                                                        <button type="button" data-bs-toggle="modal" onclick="loadUserInformation('Hoàng Trần Đức', 'Male', '19-05-2001', 'hihi@gmail.com', '0123456789');" data-bs-target="#accountInformation" class="btn btn-outline-info" style="margin: 5px 10px;">
                                                                            View
                                                                        </button>
                                                                    </c:if>
                                                                    <c:if test="${user.getRole()==2}">
                                                                        <button type="button" data-bs-toggle="modal" onclick="loadUserInformation('${domManagerDAO.getOne(user.getUserName()).getName()}', '${domManagerDAO.getOne(user.getUserName()).isGender()?"Male":"Female"}', '${domManagerDAO.getOne(user.getUserName()).getDateOfBirth()}', '${domManagerDAO.getOne(user.getUserName()).getEmail()}', '${domManagerDAO.getOne(user.getUserName()).getPhoneNumber()}');" data-bs-target="#accountInformation" class="btn btn-outline-info" style="margin: 5px 10px;">
                                                                            View
                                                                        </button>
                                                                    </c:if>
                                                                    <c:if test="${user.getRole()==3}">
                                                                        <button type="button" data-bs-toggle="modal" onclick="loadUserInformation('${boarderDAO.getOne(user.getUserName()).getBoarderName()}', '${boarderDAO.getOne(user.getUserName()).isGender()?"Male":"Female"}', '${boarderDAO.getOne(user.getUserName()).getDateOfBirth()}', '${boarderDAO.getOne(user.getUserName()).getEmail()}', '${boarderDAO.getOne(user.getUserName()).getPhoneNumber()}');" data-bs-target="#accountInformation" class="btn btn-outline-info" style="margin: 5px 10px;">
                                                                            View
                                                                        </button>
                                                                    </c:if>
                                                                </td>
                                                                <td>
                                                                    <button type="button" data-bs-toggle="modal" onclick="document.getElementById('choosen_delete_account').value = '${user.getUserName()}'" data-bs-target="#deleteAccount" class="btn btn-outline-danger" style="margin: 5px 10px;">
                                                                        Delete
                                                                    </button>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </table>
                                                    <!--Modal delete account-->
                                                    <div class="modal fade" id="deleteAccount" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog">
                                                            <div style="height: 200px;"></div>
                                                            <form action="user" method="post" class="modal-content" style="z-index: 3;">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="exampleModalLabel">Delete Account</h5>
                                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <div class="container">
                                                                        <input type="hidden" name="username" id="choosen_delete_account" value="">
                                                                        <h6>This account will be deleted from the system, are you sure</h6>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="submit" class="btn btn-outline-info">Sure</button>
                                                                    <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Close</button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                    <!--Modal account information-->
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
                                                                            <div class="col-md-4">
                                                                                Full Name:
                                                                            </div>
                                                                            <div class="col-md-5">
                                                                                <input class="account_information" id="chosenFullName" type="text" value="" style="border: none;" disabled>                                                                        
                                                                            </div>
                                                                        </div>
                                                                        <div class="row">
                                                                            <div class="col-md-4">
                                                                                Gender:
                                                                            </div>
                                                                            <div class="col-md-5">
                                                                                <input class="account_information" id="chosenUGender" type="text" value="" style="border: none;" disabled>                                                                        
                                                                            </div>
                                                                        </div>
                                                                        <div class="row">
                                                                            <div class="col-md-4">
                                                                                Date of birth
                                                                            </div>
                                                                            <div class="col-md-5">
                                                                                <input class="account_information" id="chosenDOB" type="text" value="" style="border: none;" disabled>                                                                    
                                                                            </div>
                                                                        </div>
                                                                        <div class="row">
                                                                            <div class="col-md-4">
                                                                                Email:
                                                                            </div>
                                                                            <div class="col-md-5">
                                                                                <input class="account_information" id="chosenEmail" type="text" value="" style="border: none;" disabled>                                                                    
                                                                            </div>
                                                                        </div>
                                                                        <div class="row">
                                                                            <div class="col-md-4">
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
