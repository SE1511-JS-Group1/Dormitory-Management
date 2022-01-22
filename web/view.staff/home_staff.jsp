<%-- 
    Document   : home_staff.jsp
    Created on : 22-Jan-2022, 17:37:20
    Author     : lenovo_thinkpad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello ${sessionScope.account.getUserName()}!</h1>
    </body>
</html>
