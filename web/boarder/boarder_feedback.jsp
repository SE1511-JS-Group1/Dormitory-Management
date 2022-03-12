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
        <link rel='icon' href='https://by.com.vn/xQTXSg'>   
        <link href="../css/overview.css" rel="stylesheet">
        <link href="../css/feedback.css" rel="stylesheet">
        <script src="../js/feedback.js"></script>
    </head>
    <body>
        <div style="position: fixed;left: 0;top:  0;height: 100px;width: 100%;z-index: 2;">
            <c:import url="boarder_header.jsp"></c:import>
            </div>
           
                <div id="form">
                    <div class="fish" id="fish"></div>
                    <div class="fish" id="fish2"></div>
                    <form action="feedback" method="POST">
                        <div class="formgroup" id="name-form" style="margin-top: 150px">
                            <label for="name">Title</label>
                            <input name="title" id="name" type="text" onkeyup="CheckMessage(this)" placeholder="Title" required/>
                        </div>
                        <div class="formgroup" id="message-form">
                            <label for="message">Your message</label>
                            <textarea id="message" name="massage" onkeyup="CheckMessage(this)" placeholder="Massage" required></textarea>
                        </div>
                        <input type="submit" value="Send your message!" />
                        <script>
                            alert("Feedback Successful");
                        </script>
                    </form>
                </div>
                <div style=" position: fixed;left: 0;bottom: 0;height: 100px;width: 100%;border-top: 1px dotted black;">
                <c:import url="boarder_footer.jsp"></c:import>
            </div>
    </body>
    <script>
        function CheckMessage(param) {
            var massege = param.value;
            param.value = massege.trim();
            if (massege.length == 0) {
                document.getElementById('message1').innerHTML = 'Trống';
            } else {
                document.getElementById('message1').innerHTML = '';
            }
        }
    </script>
</html>
