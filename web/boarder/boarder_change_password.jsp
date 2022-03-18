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
        <link href="../css/changepassword.css" rel="stylesheet">
        <link href="../css/feedback.css" rel="stylesheet">
        <script src="../js/feedback.js"></script>
    </head>
    <body>
        <div style="position: fixed;left: 0;top:  0;height: 100px;width: 100%;z-index: 2;">
            <c:import url="boarder_header.jsp"></c:import>
            </div>
            <section class="vh-100" style="background-color: #eee;">
                <div id="form">
                    <div class="fish" id="fish"></div>
                    <div class="fish" id="fish2"></div>
                    <form method="post" action="changepassword" style="margin-top: 150px">
                        <input type="test" class="text" name="user" id="old" hidden="" oninput="CheckAccount('message_username',this);" required="import old passworf" value="${sessionScope.account.getUserName()}">
                    <label for="old"> Old Password </label>
                    <input type="password" class="text email" name="old" id="old" oninput="CheckPassword(this);" required="import old passworf" value="${sessionScope.account.getPassWord()}">
                    <label for="new"> New Password: </label>
                    <input type="password" name="new" class="text" id="new" onkeyup="CheckNewPassword(this);" required="import new passworf" oninput="CheckNewPassword(this);" placeholder="New Password">
                    <div id="message1" style="color:red"> </div>${message1}
                    <br>
                    <label for="retype"> Confirm New Password: </label>
                    <input type="password" name="retype" id="retype" onkeyup="matchPassword(this);" class="text w3lpass" required="import confirm new passworf" placeholder="Confirm Password">
                    <div id="message2" style="color:red"> </div>${message2}
                    <div class="wthree-text">
                        <div class="clear"> </div>
                    </div>
                    <input type="submit" onclick="return CheckChangePassword(), showMess()"  value="Change Password">
                </form>
            </div>
<<<<<<< HEAD
        </section> 
       
=======
        </section>
>>>>>>> 84089ecc7420d9574db45ef926abea2a69698c38
        <div style=" position: fixed;left: 0;bottom: 0;height: 100px;width: 100%;border-top: 1px dotted black;">
            <c:import url="boarder_footer.jsp"></c:import>
        </div>


    </body>
    <script>
        function CheckNewPassword(param) {
            document.getElementById('message1').style.display = 'none';
            document.getElementById('message2').style.display = 'none';
            var password1 = param.value;
            param.value = password1.trim();
            if (password1.length < 6 || password1.length > 32) {
                document.getElementById('message1').innerHTML = 'Mật khẩu phải chứa 6-32 ký tự không bao gồm khoảng trắng';
            } else {
                document.getElementById('message1').innerHTML = '';
            }
        }
        function matchPassword(param) {
            document.getElementById('message1').style.display = 'none';
            document.getElementById('message2').style.display = 'none';
            var confirmpass = param.value;
            param.value = confirmpass.trim();
            var password = document.getElementById('new').value;
            if (password !== confirmpass) {
                document.getElementById('message2').innerHTML = 'Mật khẩu không khớp';
            } else {
                document.getElementById('message2').innerHTML = '';
            }
        }
        function CheckChangePassword() {
            var txt = document.getElementById('message1').innerHTML.trim().length
                    + document.getElementById('message2').innerHTML.trim().length;
            document.getElementById('message1').style.display = 'block';
            document.getElementById('message2').style.display = 'block';
            return txt === 0;
            alert("Change password successful");
        }
         
        function showMess() {
            alert("Change password successfuly");
        }
    
    </script> 
</html>
