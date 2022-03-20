<%-- 
    Document   : boarder_information
    Created on : Mar 13, 2022, 2:38:06 PM
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
                                            <h3 class="text-center" style="font-family: cursive; color: #ffffff;">Your Information</h3>
                                        </div>
                                        <div class="card" style="border-radius: 1rem;">
                                            <div class="row g-0">
                                                <div class="col-md-12 col-lg-12 d-flex align-items-center">
                                                    <form action="infor" method="post" class="card-body p-4 p-lg-5 text-black">
                                                        <input type="hidden" name="boarderid" value="${boarder.getBoarderID()}"/>
                                                    <div class="input-group mb-3">
                                                        <span class="input-group-text" id="inputGroup-sizing-default">Name</span>
                                                        <input required maxlength="32" onchange="showButton()" id="name" name="name" type="text" style="background-color: #ffffff;" value="${boarder.getBoarderName()}" disabled class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                                                        <button type="button" class="btn btn-outline-secondary" onclick="changeStatus(this, 'name');">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                                            <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                                            </svg>
                                                        </button>                                            
                                                    </div>
                                                    <div class="input-group mb-3">
                                                        <span class="input-group-text" id="inputGroup-sizing-default">Username</span>
                                                        <input type="text" class="form-control" placeholder="Username" aria-label="Username" value="${sessionScope.account.getUserName()}" disabled style="background-color: #ffffff">
                                                        <span class="input-group-text">Password</span>
                                                        <input id="password" type="password" class="form-control" value="${sessionScope.account.getPassWord().substring(0,sessionScope.account.getPassWord().length()-4)}****">
                                                        <a type="button" class="btn btn-outline-secondary" href="changepassword">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                                            <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                                            </svg>
                                                        </a> 
                                                    </div>
                                                    <div class="input-group mb-3">
                                                        <label class="input-group-text" for="inputGroupSelect01">Gender</label>
                                                        <select onchange="showButton()" id="gender" name="gender" class="form-select" id="inputGroupSelect01" style="background-color: #ffffff;" disabled>
                                                            <option value="1" ${boarder.isGender()?"selected":""}>Male</option>
                                                            <option value="0" ${boarder.isGender()?"":"selected"}>Female</option>
                                                        </select>
                                                        <button type="button" class="btn btn-outline-secondary" onclick="changeStatus(this, 'gender');">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                                            <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                                            </svg>
                                                        </button> 
                                                    </div>
                                                    <div class="input-group mb-3">
                                                        <span class="input-group-text" id="inputGroup-sizing-default">Birthday</span>
                                                        <input required onchange="showButton()" id="dob" name="dob" type="date" style="background-color: #ffffff;" value="${boarder.getDateOfBirth()}" disabled class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                                                        <button type="button" class="btn btn-outline-secondary" onclick="changeStatus(this, 'dob');">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                                            <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                                            </svg>
                                                        </button>
                                                    </div>
                                                    <div class="input-group mb-3">
                                                        <span class="input-group-text" id="inputGroup-sizing-default">Phone</span>
                                                        <input required maxlength="10" oninput="trimData(this);" onchange="showButton()" id="phone" name="phone" type="text" style="background-color: #ffffff;" value="${boarder.getPhoneNumber()}" disabled class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                                                        <button type="button" class="btn btn-outline-secondary" onclick="changeStatus(this, 'phone');">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                                            <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                                            </svg>
                                                        </button>
                                                    </div>
                                                    <p id="phone-txt" class="text-danger" style="display: none;"></p>
                                                    <div class="input-group mb-3">
                                                        <span class="input-group-text" id="inputGroup-sizing-default">Email</span>
                                                        <input required maxlength="48" oninput="trimData(this);" onchange="showButton()" id="email" name="email" type="email" style="background-color: #ffffff;" value="${boarder.getEmail()}" disabled class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                                                        <button type="button" class="btn btn-outline-secondary" onclick="changeStatus(this, 'email');">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                                            <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                                            </svg>
                                                        </button>
                                                    </div>
                                                    <p id="email-txt" class="text-danger"style="display: none;"></p>
                                                    <div class="input-group mb-3">
                                                        <label class="input-group-text" for="inputGroupSelect01">Job</label>
                                                        <select onchange="showButton()" id="job" name="job" class="form-select" id="inputGroupSelect01" style="background-color: #ffffff;" disabled>
                                                            <option value="Student" ${boarder.getJob().equals(Jobs.Student)?"selected":""}>Student</option>
                                                            <option value="Teacher" ${boarder.getJob().equals(Jobs.Teacher)?"selected":""}>Teacher</option>
                                                        </select>
                                                        <button type="button" class="btn btn-outline-secondary" onclick="changeStatus(this, 'job');">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                                            <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                                            </svg>
                                                        </button>
                                                    </div>
                                                    <div id="saveinfor" style="display: none;">
                                                        <button id="btnSave" type="submit" class="btn btn-outline-success" onclick="return CheckInfor();">Save</button>
                                                        <button type="button" class="btn btn-outline-danger" onclick="location.reload();">Cancel</button>
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
                                                            document.getElementById('phone').oninput = function () {
                                                                document.getElementById('phone-txt').style.display = 'none';
                                                                $.ajax({
                                                                    url: "checkphone",
                                                                    type: "get", //send it through get method
                                                                    data: {
                                                                        phone: document.getElementById('phone').value
                                                                    },
                                                                    success: function (data) {
                                                                        var row = document.getElementById('phone-txt');
                                                                        row.innerHTML = data;
                                                                    },
                                                                    error: function (xhr) {
                                                                        //Do Something to handle error
                                                                    }
                                                                });
                                                            };
        </script>
        <script>
            document.getElementById('email').oninput = function () {
                document.getElementById('email-txt').style.display = 'none';
                $.ajax({
                    url: "checkemail",
                    type: "get", //send it through get method
                    data: {
                        email: document.getElementById('email').value
                    },
                    success: function (data) {
                        var row = document.getElementById('email-txt');
                        row.innerHTML = data;
                    },
                    error: function (xhr) {
                        //Do Something to handle error
                    }
                });
            }
            ;
        </script>
        <script>
            function CheckInfor() {
                document.getElementById('phone-txt').style.display = 'block';
                document.getElementById('email-txt').style.display = 'block';
                var x = document.getElementById('phone-txt').innerHTML.trim()
                        + document.getElementById('email-txt').innerHTML.trim();
                return x.length === 0;
            }
            function showInput() {
                document.getElementById('name').removeAttribute('disabled');
                document.getElementById('gender').removeAttribute('disabled');
                document.getElementById('dob').removeAttribute('disabled');
                document.getElementById('phone').removeAttribute('disabled');
                document.getElementById('email').removeAttribute('disabled');
                document.getElementById('job').removeAttribute('disabled');
                return true;
            }
            function trimData(param) {
                let val = param.value.trim();
                if (param.id === 'phone') {
                    val = val.split(/[^0-9]/).join("");
                }
                param.value = val;
            }
            function showButton() {
                document.getElementById('saveinfor').style.display = 'block';
            }
            function changeStatus(tag, id) {
                if (document.getElementById(id).hasAttribute('disabled')) {
                    document.getElementById(id).style.border = '2px solid black';
                    document.getElementById(id).removeAttribute('disabled');
                    tag.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check2-square" viewBox="0 0 16 16"><path d="M3 14.5A1.5 1.5 0 0 1 1.5 13V3A1.5 1.5 0 0 1 3 1.5h8a.5.5 0 0 1 0 1H3a.5.5 0 0 0-.5.5v10a.5.5 0 0 0 .5.5h10a.5.5 0 0 0 .5-.5V8a.5.5 0 0 1 1 0v5a1.5 1.5 0 0 1-1.5 1.5H3z"/><path d="m8.354 10.354 7-7a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0z"/></svg>';
                } else {
                    document.getElementById(id).disabled = true;
                    document.getElementById(id).style.border = '1px solid #ced4da';
                    tag.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16"><path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/><path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/></svg>';
                }
            }
        </script>
    </body>
</html>
