<%-- 
    Document   : register
    Created on : 16-Jan-2022, 18:45:59
    Author     : lenovo_thinkpad
--%>

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
        <link href="css/overview.css" rel="stylesheet">
    </head>
    <body>
        <section class="h-100 gradient-form" style="background-color: #eee;">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-md-10">
                        <div class="card rounded-3 text-black">
                            <div class="row g-0">
                                <div class="col-md-4 d-flex align-items-center gradient-custom-2">
                                    <div class="container">
                                        <div class="row">
                                            <div class="col-md-10" style="margin-left: 5%;">
                                                <a type="button" class="btn btn-outline-info btn-field-col pushin" href="register_boarder.jsp"><h2>Boarder</h2></a>
                                            </div>
                                        </div>                           
                                        <div style="height: 30px;"></div>
                                        <div class="row">
                                            <div class="col-md-10" style="margin-left: 5%;">                                                
                                                <a type="button" class="btn btn-outline-info btn-field-col" href="register_staff.jsp"><h2>Staff</h2></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-1">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body p-md-5 mx-md-4">
                                        <div class="text-center">
                                            <img src="https://by.com.vn/xQTXSg" style="width: 185px;" alt="logo">
                                        </div>
                                        <form action="login" method="post" onsubmit="return CheckRegister()">
                                            <p>Please register your account</p>
                                            <div class="form-outline mb-4">
                                                <input name="fullname" type="text" id="fullname" class="form-control" required placeholder="Your name"/>
                                            </div>
                                            <div class="form-outline mb-4">
                                                <input name="username" type="text" id="form2Example12" class="form-control" required placeholder="User name"/>
                                            </div>
                                            <div class="form-outline mb-4">
                                                <input name="password" type="password" id="form2Example13" class="form-control" required placeholder="Password"/>
                                            </div>
                                            <div class="form-outline mb-4">
                                                <input name="confirmpassword" type="password" id="form2Example14" class="form-control" required  placeholder="Confirm Password"/>
                                            </div>
                                            <div class="form-outline mb-4">
                                                <input name="email" type="email" id="form2Example15" class="form-control" required placeholder="Your email"/>
                                            </div>
                                            <div class="form-outline mb-4">
                                                <input name="phone" type="text" id="form2Example16" class="form-control" required placeholder="Your phone"/>
                                            </div>
                                            <div class="form-outline mb-4">
                                                <input name="gender" type="radio" id="form2Example17" class="left" checked value="male"/> <p class="left">&nbsp;Male&nbsp;</p>
                                                <input name="gender" type="radio" id="form2Example17" class="left" value="female"/> <p class="left">&nbsp;Female&nbsp;</p>
                                                <h6><input class="right" name="dateofbirth" type="date" id="form2Example18" required/></h6>
                                            </div>
                                            <div class="form-outline mb-4 row" style="clear: both;"> 
                                                <div class="col-md-5">
                                                    <p class="right ecol-center">&nbsp;Teacher&nbsp;</p><input  name="job" type="radio" id="form2Example18" class="right ecol-center" value="teacher"/>
                                                </div>
                                                <div class="col-md-2"></div>
                                                <div class="col-md-5">
                                                    <input name="job" type="radio" id="form2Example18" class="left ecol-center" checked value="student"/><p class="left ecol-center">&nbsp;Student&nbsp;</p> 
                                                </div>
                                            </div>
                                            <div class="text-center pt-1 mb-5 pb-1">
                                                <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="submit" style="width: 100%">Register</button>
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
    </body>
</html>
