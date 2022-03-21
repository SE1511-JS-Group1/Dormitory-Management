<%-- 
    Document   : boarder_feedback
    Created on : Feb 23, 2022, 11:41:22 AM
    Author     : XuanDinh
--%>

<%@page import="model.Boarder" %>
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
                                    <div class="col col-xl-8">
                                        <div class="row">
                                            <h3 class="text-center" style="font-family: cursive; color: #ffffff;">Feedback</h3>
                                        </div>
                                        <div class="card" style="border-radius: 1rem;">
                                            <div class="row g-0">
                                                <div class="col-md-12 col-lg-12 d-flex align-items-center">
                                                    <form action="feedback" method="POST" class="card-body p-4 p-lg-5 text-black">
                                                        <div class="row" id="name-form" >
                                                            <label for="name" class="col-md-2">Title</label>
                                                            <input class="col-md-10" name="title" id="name" type="text" onkeyup="CheckMessage(this)" placeholder="Title" required/>
                                                        </div>
                                                        <div class="row" id="message-form" style="margin-top: 10px;">
                                                            <label for="message" class="col-md-2">Message</label>
                                                            <textarea class="col-md-10" id="message" name="massage" onkeyup="CheckMessage(this)" placeholder="Massage" required maxlength="500" minlength="20" style="height: 100px;"></textarea>
                                                            <span id="characters" style="position: relative;float: right;color:#999;top:-30px;right: -680px;">500/500</span>
                                                        </div>
                                                        <div class="row" id="message-form" style="margin-top: 10px;">
                                                            <div class="col-md-2"></div>
                                                            <button type="submit" onclick="trimMess()" value="Send your message!" class="col-md-10 btn btn-outline-primary" >Send</button>
                                                        </div>
                                                    </form>
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
    </body>
    <script>
        function CheckMessage(param) {
            var massege = param.value;
            if (massege.trim().length === 0) {
                document.getElementById('message1').innerHTML = 'Trống';
            } else {
                document.getElementById('message1').innerHTML = '';
            }
        }
        document.getElementById('message').oninput = function () {
            var cs = [500 - this.value.length];
            document.getElementById('characters').innerHTML = cs + '/500';
        };
        function trimMess(){
            var mess = document.getElementById('message').value.trim();
            document.getElementById('message').value = mess;
        }
    </script>
</html>
