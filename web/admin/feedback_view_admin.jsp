<%-- 
    Document   : feedback_view_admin
    Created on : Feb 16, 2022, 12:45:17 PM
    Author     : Dell
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
        <link href="../css/feedback.css" rel="stylesheet">
        <script src="../js/feedback.js"></script>
    </head>

    <body>
        <div style="position: fixed;left: 0;top:  0;height: 100px;width: 100%;z-index: 2;">
            <c:import url="admin_header.jsp"></c:import>
            </div>
            <div id="form">
                <div class="fish" id="fish"></div>
                <div class="fish" id="fish2"></div>
                <form action="feedback" method="POST" style="margin-top: 150px">
                    <div class="row">
                     From: <input class="col"type="date" name="date1" size="10" value="${date1}">
                    To: <input class="col" type="date" name="date2" size="10" value="${date2}">
                    <input class="col-md-3" type="submit" value="Search" >
                </div>
                <c:choose>
                    <c:when test="${feedback.size() == 0 }"><h4>no feedback</h4> </c:when>
                    <c:otherwise> 
                        <table border="1" style="margin: auto">
                            <colgroup>
                                <col width="250" >
                                <col width="250">
                                <col width="100">
                                <col width="50">
                            </colgroup>
                            <tr>
                                <td>Date Send</td> 
                                <td>Title</td>
                                <td>Boarder Send</td>
                                <td style="text-align: right">Action</td>
                            </tr>
                            <c:forEach items="${feedback}" var="f">
                                <tr>
                                    <td> ${f.getDateSend()}: ${f.getTimeSend()}</td>
                                    <td>${f.getTitle()}</td>
                                    <td>${f.getOwner().getBoarderName()}</td>
                                    <td >
                                        <button type="button" onclick="document.getElementById('delete1').value = '${f.getFeedbackId()}'" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#delete">Delete</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <c:if test="${numberofpage > 1}">
                            <c:choose>
                                <c:when test="${numberofpage ==2 }">
                                </c:when>
                                
                                <c:otherwise>
                                    <input type="hidden" value="${curpage}" name="page" id="page" >
                                    <nav aria-label="Page navigation example">
                                        <ul class="pagination">
                                            <li class="page-item">
                                                <button type="submit" class="btn-light" onclick="document.getElementById('page').value = '${curpage-1}'" aria-label="Previous" ${curpage == 1?"disabled":""}>
                                                    <span aria-hidden="true">&laquo;</span>
                                                </button>
                                            </li>
                                            <li class="page-item"><button type="submit" class=" ${curpage == 1?'btn-primary':'btn-light'}" onclick ="document.getElementById('page').value = '${curpage == 1?1:curpage == numberofpage?curpage-2:curpage-1}'">${curpage == 1?1:curpage == numberofpage?curpage-2:curpage-1}</button></li>
                                            <li class="page-item"><button type="submit" class=" ${curpage >1  && curpage < numberofpage?'btn-primary':'btn-light'}" onclick ="document.getElementById('page').value = '${curpage == 1?2:curpage == numberofpage?curpage-1:curpage}'">${curpage == 1?2:curpage == numberofpage?curpage-1:curpage}</button></li>
                                            <li class="page-item"><button type="submit"class=" ${curpage == numberofpage?'btn-primary':'btn-light'}" onclick ="document.getElementById('page').value = '${curpage == 1?3:curpage == numberofpage?curpage:curpage+1}'">${curpage == 1?3:curpage == numberofpage?curpage:curpage+1}</button></li>
                                            <li class="page-item">
                                                <button type="submit" class="btn-light" onclick="document.getElementById('page').value = '${curpage+1}'" aria-label="Next" ${curpage == numberofpage?"disabled":""}>
                                                    <span aria-hidden="true">&raquo;</span>
                                                </button>
                                            </li>
                                        </ul>
                                    </nav>
                                </c:otherwise>
                                
                            </c:choose>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </form>

            <div class="modal fade" id="delete" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <form class="modal-content" action="deletefeedback" method="POST">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <input type="hidden" value="" name="id" id="delete1">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Delete</button>
                        </div>
                    </form>
                </div>
            </div>


        </div>
        <div  style=" left: 0;bottom: 0;height: 100px;width: 100%;border-top: 1px dotted black;">
            <c:import url="admin_footer.jsp"></c:import>
        </div>

    </body>
</html>
