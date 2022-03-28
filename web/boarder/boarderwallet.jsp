<%-- 
    Document   : boarderwallet
    Created on : Mar 21, 2022, 8:16:00 AM
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
        <link href="../css/background.css" rel="stylesheet">
        <link rel='icon' href='../images/logo.png'>   
        <link href="../css/overview.css" rel="stylesheet"> 
    </head>
    <style>
        .pushin{
            margin-left: 7%;
            margin-top: 20px;
            width: 85%;
            padding: 10px;
            background-color: #879091;
            border: none;
        }
        .btn-map-field-col{
            margin-left: 7%;
            margin-top: 20px;
            width: 85%;
            padding: 10px;
            background-color: #FFFFFF;
            border: none;
        }
    </style>
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
                                    <div class="col-xl-10">
                                        <div class="row">
                                            <h3 class="text-center" style="font-family: cursive; color: #ffffff;">Your Wallet</h3>
                                        </div>
                                        <div class="card rounded-3 text-black">
                                            <div class="row g-0">
                                                <div class="col-lg-2 d-flex align-items-center" style="background-color: #c9d8c9;">
                                                    <div class="container">
                                                        <div class="row">
                                                            <form action="wallet" method="post">
                                                                <button type="submit" class="btn btn-outline-info ${act eq 'view' ? 'pushin':'btn-map-field-col'}"><h6>View</h6></button>    
                                                        </form>
                                                        <form action="fee" method="post">
                                                            <button type="submit" class="btn btn-outline-info ${act eq 'pay' ? 'pushin':'btn-map-field-col'}"name="editvalue" value="1"><h6>Pay</h6></button>    
                                                        </form>
                                                    </div>
                                                    <div style="height: 10px;"></div>
                                                </div>
                                            </div>
                                            <div class="col-lg-10">
                                                <div class="card-body p-md-9 mx-md-4">
                                                    <div class="container map">
                                                        <div class="spinner-grow text-muted"></div>
                                                        <div class="spinner-grow text-primary"></div>
                                                        <div class="spinner-grow text-success"></div>
                                                        <div class="spinner-grow text-info"></div>
                                                        <div class="spinner-grow text-warning"></div>
                                                        <div class="spinner-grow text-danger"></div>
                                                        <div class="spinner-grow text-secondary"></div>
                                                        <div class="spinner-grow text-dark"></div>
                                                        <div class="spinner-grow text-light"></div>
                                                        <div>
                                                            <div class="row">
                                                                <div class="modal-body">
                                                                    <div class="container row">
                                                                        <div class="col-md-6">
                                                                            <div class="row">
                                                                                <div class="col-md-4"style="font-family: fantasy">
                                                                                    Your name:
                                                                                </div>
                                                                                <div class="col-md-5">
                                                                                    <input class="room_information" id="chosenName" type="text" value="${boardername}" style="border: none;" disabled>                                                                        
                                                                                </div>
                                                                            </div>
                                                                            <div class="row">
                                                                                <div class="col-md-4" style="font-family: fantasy">
                                                                                    Balance:
                                                                                </div>
                                                                                <div class="col-md-5">
                                                                                    <input class="room_information" id="chosenName" type="text" value="${balance} VNĐ" style="border: none;" disabled>                                                                        
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-6 row">
                                                                            <div class="col-md-2"></div>
                                                                            <button type="button" class="btn btn-outline-info col-md-4" data-bs-toggle="modal"  data-bs-target="#topup">
                                                                                Top up
                                                                            </button>
                                                                            <div class="col-md-2"></div>
                                                                            <button type="button" class="btn btn-outline-success col-md-4" data-bs-toggle="modal"  data-bs-target="#cashout">
                                                                                Cash out
                                                                            </button>
                                                                        </div>
                                                                    </div>
                                                                    <div class="row">
                                                                        <div class="col-md-12 text-center" style="font-family: cursive;">
                                                                            <h4>Transaction history</h4>
                                                                            <c:choose>
                                                                                <c:when test="${Transactions.size()>0}">
                                                                                    <table class="table table-striped">
                                                                                        <colgroup>
                                                                                            <col width="300" span="1">
                                                                                            <col width="100" span="1">
                                                                                            <col width="300" span="1">
                                                                                            <col width="200" span="1">
                                                                                        </colgroup>
                                                                                        <thead>
                                                                                            <tr>
                                                                                                <th scope="col">Time</th>
                                                                                                <th scope="col">Type</th>
                                                                                                <th scope="col">Title</th>
                                                                                                <th scope="col">Amount</th>
                                                                                            </tr>
                                                                                        </thead>
                                                                                        <tbody>
                                                                                            <c:forEach var="i" items="${Transactions}">
                                                                                                <tr >
                                                                                                    <td style="height: 50px;">${i.getTime()} ${i.getDate()}</td>
                                                                                                    <td style="height: 50px;">${i.getType()}</td>
                                                                                                    <td style="height: 50px;">${i.getMessage()}</td>
                                                                                                    <td style="height: 50px;">${i.getAmount()}</td>
                                                                                                </tr>
                                                                                            </c:forEach>
                                                                                        </tbody>
                                                                                    </table>
                                                                                    <c:if test="${numberofpage > 1}">
                                                                                        <form action="wallet" method="post">
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
                                                                                    <div class="col-md-12" style="font-family: fantasy">
                                                                                        You don't have any transactions yet!
                                                                                    </div>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>                            
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="position: fixed;left: 0;bottom: 0;height: 100px;width: 100%;z-index: 2;border-top: 1px dotted black;">
                                    <c:import url="boarder_footer.jsp"></c:import>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
        </section>
        <div class="modal fade" id="topup" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <form action="balance" method="post" class="modal-dialog" style="margin-top: 15%;">
                <div  class="modal-content" style="z-index: 3;">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Top-up</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="container">
                            <div class="row">
                                <h5>Confirm your amount!</h5>
                            </div>
                            <div class="input-group">
                                <span class="input-group-text">VNĐ</span>
                                <input id="amount" required type="number" min="0" max="10000000" name="amount" class="form-control" aria-label="Amount (to the nearest dollar)">
                                <span class="input-group-text">.00</span>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-outline-info" name="action" value="topup">Top-up</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="modal fade" id="cashout" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <form action="balance" method="post" class="modal-dialog" style="margin-top: 15%;">
                <div  class="modal-content" style="z-index: 3;">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Cash out</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="container">
                            <div class="row">
                                <h5>Confirm to amount!</h5>
                            </div>
                            <div class="input-group">
                                <span class="input-group-text">VNĐ</span>
                                <input id="amount" required type="number" min="0" max="${balance}" name="amount" class="form-control" aria-label="Amount (to the nearest dollar)">
                                <span class="input-group-text">.00</span>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-outline-info" name="action" value="cashout">Cash out</button>
                    </div>
                </div>
            </form>
        </div>
        <script src="../js/checkJS.js"></script>
    </body>
</html>
