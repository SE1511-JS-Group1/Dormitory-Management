<%-- 
    Document   : register
    Created on : 16-Jan-2022, 18:45:59
    Author     : lenovo_thinkpad
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Dormitory Management System</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
        <link rel='icon' href='images/logo.png'>   
        <link href="css/overview.css" rel="stylesheet">
    </head>
    <body>
        <section class="h-100" style="background-color: #eee;">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col">
                        <div class="card card-registration my-4">
                            <div class="row g-0">
                                <div class="col-xl-6 d-none d-xl-block">
                                    <img
                                        src="https://goeco.link/CpXGa"
                                        alt="Sample photo"
                                        class="img-fluid"
                                        style="border-top-left-radius: .25rem; border-bottom-left-radius: .25rem; height: 979px;"
                                        />
                                </div>
                                <div class="col-xl-6">
                                    <div class="card-body p-md-5 text-black">
                                        <h3 class="mb-5 text-uppercase">Registration form</h3>
                                        <form action="register" method="post">
                                            <input type="hidden" name="role" value="boarder"/>
                                            <div class="form-outline mb-4">
                                                <input name="fullname" type="text" id="fullname" class="form-control" required placeholder="Your name"/>
                                            </div>
                                            <div class="form-outline mb-4">
                                                <input name="username" type="text" id="form2Example12" oninput="CheckAccount('message_username',this);"  class="form-control" required placeholder="User name"/>
                                                <div class="form-outline mb-4 text-center" style="color: red;display: none;" id="message_username">
                                                    ${message_username}                                                
                                                </div>
                                            </div>
                                            <div class="form-outline mb-4">
                                                <input name="password" type="password" id="password" class="form-control" value=" " oninput="CheckPassword(this);" required placeholder="Password"/>
                                                <div class="form-outline mb-4 text-center" style="color: red;display: none;" id="message_password">
                                                    ${message_password}                                                
                                                </div>
                                            </div>
                                            <div class="form-outline mb-4">
                                                <input name="confirmpassword" type="password" id="form2Example14" oninput="CheckConfirmPassword(this);" class="form-control" required  placeholder="Confirm Password"/>
                                                <div class="form-outline mb-4 text-center" style="color: red;display: none;" id="message_confirmpassword">
                                                    ${message_confirmpassword}                                                
                                                </div>
                                            </div>
                                            <div class="form-outline mb-4">
                                                <input name="email" type="email" id="form2Example15" oninput="CheckAccount('message_email',this);" class="form-control" required placeholder="Your email"/>
                                                <div class="form-outline mb-4 text-center" style="color: red;display: none;" id="message_email">
                                                    ${message_email}                                                
                                                </div>
                                            </div>
                                            <div class="form-outline mb-4">
                                                <input name="phone" type="text" id="form2Example16" oninput="CheckAccount('message_phone',this);"  class="form-control" required placeholder="Your phone"/>
                                                <div class="form-outline mb-4 text-center" style="color: red;display: none;" id="message_phone">
                                                    ${message_phone}                                                
                                                </div>
                                            </div>
                                            <div class="d-md-flex justify-content-start align-items-center mb-4 py-2">

                                                <h6 class="mb-0 me-4">Gender: </h6>

                                                <div class="form-check form-check-inline mb-0 me-4">
                                                    <input
                                                        class="form-check-input"
                                                        type="radio"
                                                        name="gender"
                                                        id="femaleGender"
                                                        value="female"
                                                        checked
                                                        />
                                                    <label class="form-check-label" for="femaleGender">Female</label>
                                                </div>

                                                <div class="form-check form-check-inline mb-0 me-4">
                                                    <input
                                                        class="form-check-input"
                                                        type="radio"
                                                        name="gender"
                                                        id="maleGender"
                                                        value="male"
                                                        />
                                                    <label class="form-check-label" for="maleGender">Male</label>
                                                </div>
                                                <h6 class="mb-0 me-4">Birthday: </h6>
                                                <div class="form-check form-check-inline mb-0 me-4">
                                                    <input name="dateofbirth" type="date" id="form2Example18" required/>
                                                </div>
                                            </div>
                                            <div class="mb-4 pb-2">
                                                <select name="position" class="form-select" aria-label="Default select example">
                                                    <option value="Teacher">Boarder Teacher</option>
                                                    <option value="Student">Boarder Student</option>
                                                    <option value="Accountant">Staff Accountant</option>
                                                    <option value="Guardian">Staff Guardian</option>
                                                    <option value="Management_Staff">Staff Manager</option>
                                                </select>
                                            </div>
                                            <div class="text-center pt-1 mb-5 pb-1">
                                                <button class="btn btn-block btn-outline-info fa-lg mb-3" onclick="return CheckRegister();" type="submit" style="width: 100%">Register</button>
                                            </div>
                                            <div class="d-flex align-items-center justify-content-center pb-4">
                                                <p class="mb-0 me-2">Have an account?</p>
                                                <a type="button" class="btn btn-outline-danger" href="login">Login</a>
                                            </div>
                                        </form>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="js/checkJS.js"></script>
    </body>
</html>
