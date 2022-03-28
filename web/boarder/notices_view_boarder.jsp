<%-- 
 /*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * notices_view_boarder.jsp
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-01-27      1.1                 HuyLQ           Update code
 */
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
                                            <h3 class="text-center" style="font-family: cursive; color: #ffffff;">Your Notices</h3>
                                        </div>
                                        <div class="card" style="border-radius: 1rem;">
                                            <div class="row g-0">
                                                <div class="col-md-12 col-lg-12 d-flex align-items-center">
                                                    <div class="card-body p-4 p-lg-5 text-black">
                                                    <c:choose>
                                                        <c:when test="${ListNotice.size()>0}">
                                                            <table class="table table-striped">
                                                                <colgroup>
                                                                    <col width="650" span="1">
                                                                    <col width="1050" span="1">
                                                                </colgroup>
                                                                <thead>
                                                                    <tr>
                                                                        <th scope="col">Time Send</th>
                                                                        <th scope="col">Content</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach var="i" items="${ListNotice}">
                                                                        <tr>
                                                                            <td>${i.getTimeSend()} ${i.getDateSend()}</td>
                                                                            <td>${i.getTitle()}</td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </tbody>
                                                            </table>
                                                            <c:if test="${numberofpage > 1}">
                                                                <form action="notice" method="post">
                                                                    <c:choose>
                                                                        <c:when test="${numberofpage ==2 }">
                                                                            <input type="hidden" value="${curpage}" name="page" id="page" >
                                                                            <nav aria-label="Page navigation example">
                                                                                <ul class="pagination">
                                                                                    <li class="page-item">
                                                                                        <button type="submit" class="btn-light" onclick="document.getElementById('page').value = '${curpage-1}'" aria-label="Previous" ${curpage == 1?"disabled":""}>
                                                                                            <span aria-hidden="true">&laquo;</span>
                                                                                        </button>
                                                                                    </li>
                                                                                    <li class="page-item"><button type="submit" class=" ${curpage == 1?'btn-primary':'btn-light'}" onclick ="document.getElementById('page').value = '1'">1</button></li>
                                                                                    <li class="page-item"><button type="submit" class=" ${curpage == 2?'btn-primary':'btn-light'}" onclick ="document.getElementById('page').value = '2'">2</button></li>
                                                                                    <li class="page-item">
                                                                                        <button type="submit" class="btn-light" onclick="document.getElementById('page').value = '${curpage+1}'" aria-label="Next" ${curpage == numberofpage?"disabled":""}>
                                                                                            <span aria-hidden="true">&raquo;</span>
                                                                                        </button>
                                                                                    </li>
                                                                                </ul>
                                                                            </nav>
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
                                                                </form>
                                                            </c:if>
                                                        </c:when>
                                                        <c:otherwise>

                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </section>
                </div>
                <div style="position: absolute;left: 0;bottom: 0;width: 100%;z-index: 3;">
                    <c:import url="boarder_footer.jsp"></c:import>
                </div>
            </div>
        </section>
    </body>
</html>
