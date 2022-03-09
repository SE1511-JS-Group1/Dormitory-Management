<%-- 
    Document   : boarder_change_password
    Created on : Feb 28, 2022, 9:54:49 PM
    Author     : XuanDinh
--%>

<%@page import="model.Boarder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            <c:import url="boarder_header.jsp"></c:import>
            </div>
            <div style="position: fixed;left: 0;top:  0;height: 100px;width: 100%;z-index: 2;">
            <c:import url="boarder_header.jsp"></c:import>
            </div>
            <section class="vh-100" style="background-color: #eee;">
                <div class="container py-5 h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col col-xl-4">
                            <div class="card" style="border-radius: 1rem;">
                                <div class="row g-0">
                                    <h1 class="col-md-12" style="text-align: center">Change Password</h1>
                                    <div class="col-md-12 col-lg-12 d-flex align-items-center">
                                        <div class="card-body p-4 p-lg-5 text-black">
                                            <form method = "POST" action = "changepassword" >
                                                <div class = "form-group">
                                                    
                                                    <input type = "test" name = "user" id = "old" hidden="" class = "form-control" oninput="CheckAccount('message_username',this);" required="import old passworf" value="${sessionScope.account.getUserName()}">
                                            </div>
                                            <div class = "form-group">
                                                <label for = "old"> Old Password </label>
                                                <input type = "password" name = "old" id = "old" class = "form-control"  oninput="CheckPassword(this);" required="import old passworf" value="${sessionScope.account.getPassWord()}">
                                            </div>
                                            <div class = "form-group">
                                                <label for = "new"> New Password: </label>
                                                <input type = "password" name = "new"  id = "new" onkeyup="CheckNewPassword(this);" class = "form-control" required="import new passworf" oninput="CheckNewPassword(this);" placeholder="New Password">
                                                <div id = "message1"  style="color:red"> </div>${message1}
                                            </div>
                                            <div class = "form-group">
                                                <label for = "retype"> Confirm New Password: </label>
                                                <input type = "password" name = "retype" id = "retype" onkeyup="matchPassword(this);" class = "form-control" required="import confirm new passworf"  placeholder="Confirm Password">

                                            </div>
                                            <p id = "message" style="color:red"> </p>
                                            <br>
                                            <button type = "submit"   name = "update" class = "btn btn-success"><span class = "glyphicon glyphicon-check"> </span>  Cập nhật </button>
                                            <script>
                                                alert("change password successful");
                                            </script>
                                            <a href="home" class = "btn btn-success">close</a>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div style=" position: fixed;left: 0;bottom: 0;height: 100px;width: 100%;border-top: 1px dotted black;">
                <c:import url="boarder_footer.jsp"></c:import>
            </div>
        </section>
        
    </body>
    <script>
            function CheckNewPassword(param){
                 var password1 = param.value; 
                 param.value = password1.trim();
                if (password1.length < 6 || password1.length > 32) {
                    document.getElementById('message1').innerHTML = 'Mật khẩu phải chứa 6-32 ký tự không bao gồm khoảng trắng';
                } else {
                    document.getElementById('message1').innerHTML = '';
                }
            }
            function matchPassword(param) {
                var password = document.getElementById("new").value;
                var confirmpassword = param.value;
                param.value = confirmpassword.trim();
                if (password !== confirmpassword)
                {
                     document.getElementById('message1').innerHTML = 'Mật khẩu không khớp';
                  
                } else {
                    document.getElementById('message1').innerHTML = 'Password created successfully';
                }
            }
        </script> 
</html>
