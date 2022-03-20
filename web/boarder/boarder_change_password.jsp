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
                                    <div class="col col-xl-8">
                                        <div class="row">
                                            <h3 class="text-center" style="font-family: cursive; color: #ffffff;">Change password</h3>
                                        </div>
                                        <div class="card" style="border-radius: 1rem;">
                                            <div class="row g-0">
                                                <div class="col-md-12 col-lg-12 d-flex align-items-center">
                                                    <form method="post" action="changepassword" class="card-body p-4 p-lg-5 text-black">
                                                        <div class="input-group mb-3">
                                                            <span class="input-group-text" id="inputGroup-sizing-default">Old password</span>
                                                            <input required maxlength="32" id="oldpass" name="oldpass" type="password" style="background-color: #ffffff;" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                                                        </div>
                                                        <p id="oldpass-txt" class="text-danger" style="display: none;"></p>
                                                        <div class="input-group mb-3">
                                                            <span class="input-group-text" id="inputGroup-sizing-default">New password</span>
                                                            <input required maxlength="32" id="newpass" name="newpass" type="password" style="background-color: #ffffff;" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                                                        </div>
                                                        <p id="newpass-txt" class="text-danger" style="display: none;"></p>
                                                        <div class="input-group mb-3">
                                                            <span class="input-group-text" id="inputGroup-sizing-default">Confirm password</span>
                                                            <input required maxlength="32" id="confirmpass" name="confirmpass" type="password" style="background-color: #ffffff;" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                                                        </div>
                                                        <p id="confirmpass-txt" class="text-danger" style="display: none;"></p>
                                                        <button type="submit" class="btn btn-outline-info" id="btnChange">Change</button>                                   
                                                        <button type="button" class="btn btn-outline-danger" id="btnChange" onclick="location.reload()">Cancel</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
                <div style="position: fixed;left: 0;bottom: 0;height: 100px;width: 100%;z-index: 2;border-top: 1px dotted black;">
                <c:import url="boarder_footer.jsp"></c:import>
            </div>
        </section>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        document.getElementById('btnChange').onclick = function () {
            document.getElementById('newpass-txt').style.display = 'block';
            document.getElementById('confirmpass-txt').style.display = 'block';
            document.getElementById('oldpass-txt').style.display = 'block';
            var txt = document.getElementById('newpass-txt').innerHTML.trim() +
                    document.getElementById('confirmpass-txt').innerHTML.trim() +
                    document.getElementById('oldpass-txt').innerHTML.trim();
            return txt.length === 0;

        };
    </script>
    <script>
        document.getElementById('newpass').oninput = function () {
            document.getElementById('newpass-txt').style.display = 'none';
            this.value = this.value.trim();
            var lengthPass = this.value.length;
            if (lengthPass < 6 || lengthPass > 32) {
                document.getElementById('newpass-txt').innerHTML = 'Mật khẩu phải chứa 6-32 ký tự không bao gồm khoảng trắng';
            } else {
                document.getElementById('newpass-txt').innerHTML = '';
            }
        };
    </script>
    <script>
        document.getElementById('confirmpass').oninput = function () {
            document.getElementById('confirmpass-txt').style.display = 'none';
            this.value = this.value.trim();
            var newpass = document.getElementById('newpass').value;
            if (this.value !== newpass) {
                document.getElementById('confirmpass-txt').innerHTML = 'Mật khẩu không khớp';
            } else {
                document.getElementById('confirmpass-txt').innerHTML = '';
            }
        };
    </script>
    <script>
        document.getElementById('oldpass').oninput = function () {
            this.value = this.value.trim();
            document.getElementById('oldpass-txt').style.display = 'none';
            $.ajax({
                url: "checkpass",
                type: "get", //send it through get method
                data: {
                    pass: document.getElementById('oldpass').value
                },
                success: function (data) {
                    var row = document.getElementById('oldpass-txt');
                    row.innerHTML = data;
                },
                error: function (xhr) {
                    //Do Something to handle error
                }
            });
        };
    </script> 
</html>
