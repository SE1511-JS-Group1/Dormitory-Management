<%-- 
    Document   : ManageViolation
    Created on : Mar 1, 2022, 6:56:49 PM
    Author     : NgocDuy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
        <link rel='icon' href='https://by.com.vn/xQTXSg'>   
        <link href="../css/overview.css" rel="stylesheet">
        <link href="../css/violation_manage.css" rel="stylesheet">
        <style>
            table{
                width: 1000px;
            }
        </style>
        <title>Boarder List</title>
    </head>
    <body>
        <div style="position: fixed;left: 0;top:  0;height: 100px;width: 100%;z-index: 2;">
            <c:import url="staff_header.jsp"></c:import>
        </div>
        <h1>Boarder List</h1>
        
        <div class="container">
            <table>
                <thead>
                    <tr>
                        <th>Full Name</th>
                        <th>Gender</th>
                        <th>Date of Birth</th>
                        <th>Phone Number</th>
                        <th>Bed No</th>                       
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="p" varStatus="loop">
                        <tr>
                            <td>${p.getBoarder().getBoarderName()}</td>
                            <td>${p.getBoarder().isGender()}</td>
                            <td>${p.getBoarder().getDateOfBirth()}</td>
                            <td>${p.getBoarder().getPhoneNumber()}</td>
                            <td>${p.getBedNo().toString()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <form action="#" method="GET">
                <input type="submit" value="View Borader Request" style="margin-left: 158px; margin-top: 10px">
            </form>
        </div>
        
        <div style="position: fixed;left: 0;bottom: 0;height: 100px;width: 100%;border-top: 1px dotted black;">
            <c:import url="staff_footer.jsp"></c:import>
        </div>
    </body>

</html>
