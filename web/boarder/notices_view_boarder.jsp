<%-- 
 /*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * Notices_view_boarder.jsp
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-01-27      1.0                 HuyLQ           Update code
 */
--%>

<%@page import="model.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <%
            ArrayList<Notice> notices = (ArrayList<Notice>) request.getAttribute("ListNotice");
        %>
    </head>
    <body>
        <% for (Notice n : notices) {
        %>
            <h1><%=n.getId()%></h1>
        <%}%>
    </body>
</html>
