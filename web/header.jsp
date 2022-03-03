<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
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
        <header class="p-3 bg-dark text-white">
            <div class="container">
                <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                    <a href="#" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                        <div class="logo" style="width: 70px;">                            
                            <img src="images/logo.png" class="img-thumbnail" alt="logo">
                        </div>
                        <div style="width: 20px;"></div>
                    </a>
                    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        <li><a href="home" class="nav-link px-2 ${page eq 'home' ? 'text-white':'text-secondary'}">Home</a></li>
                        <li><a href="#" class="nav-link px-2 ${page eq 'dom' ? 'text-white':'text-secondary'}">Dom</a></li>
                        <li><a href="#" class="nav-link px-2 ${page eq 'room' ? 'text-white':'text-secondary'}">Room</a></li>
                        <li><a href="#" class="nav-link px-2 ${page eq 'contact' ? 'text-white':'text-secondary'}">Contact</a></li>
                        <li><a href="#" class="nav-link px-2 ${page eq 'about' ? 'text-white':'text-secondary'}">About</a></li>
                    </ul>
                    <div class="text-end">
                        <a type="button" class="btn btn-outline-light me-2" href="login">Login</a>
                        <a type="button" class="btn btn-warning" href="register">Sign-up</a>
                    </div>
                </div>
            </div>
        </header>
    </body>
</html>
