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
        <link rel='icon' href='images/logo.png'>   
        <link href="css/overview.css" rel="stylesheet">
    </head>
    <body>
        <section class="h-100 gradient-form" style="background-color: #eee;">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-xl-10">
                        <div class="card rounded-3 text-black">
                            <div class="row g-0">
                                <div class="col-lg-4 d-flex align-items-center gradient-custom-2">
                                    <div>
                                        <a type="button" class="btn btn-outline-info btn-field-col " href="register_boarder.jsp"><h2>Boarder</h2></a>
                                        <div style="height: 80px;">

                                        </div>
                                        <a type="button" class="btn btn-outline-info btn-field-col pushin" href="register_staff.jsp"><h2>Staff</h2></a>
                                    </div>
                                </div>
                                <div class="col-lg-1">
                                </div>
                                <div class="col-lg-6">
                                    <div class="card-body p-md-5 mx-md-4">
                                        <div class="text-center">
                                            <img src="images/logo.png" style="width: 185px;" alt="logo">
                                        </div>
                                        <form action="login" method="post">
                                            <p>Please register your account</p>
                                            <div class="form-outline mb-4">
                                                <input type="text" id="form2Example11" class="form-control" placeholder="Your name"/>
                                            </div>
                                            <div class="form-outline mb-4">
                                                <input type="email" id="form2Example22" class="form-control" placeholder="Your email"/>
                                            </div>
                                            <div class="form-outline mb-4">
                                                <input type="email" id="form2Example22" class="form-control" placeholder="Your email"/>
                                            </div>
                                            <div class="text-center pt-1 mb-5 pb-1">
                                                <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="submit" style="width: 100%">Log in</button>
                                                <a class="text-muted" href="#!">Forgot password?</a>
                                            </div>

                                            <div class="d-flex align-items-center justify-content-center pb-4">
                                                <p class="mb-0 me-2">Don't have an account?</p>
                                                <a type="button" class="btn btn-outline-danger" href="register">Register</a>
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
