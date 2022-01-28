<%-- 
    Document   : home
    Created on : 16-Jan-2022, 16:39:49
    Author     : lenovo_thinkpad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="viewdom">
                <div class="logo" style="width: 70px;">                            
                    <img src="https://by.com.vn/xQTXSg" class="img-thumbnail" alt="logo">
                </div>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Home</a>
                    </li>
                </ul>
                <p class="text-center align-items-center fs-4" style="margin: auto 5px;">${sessionScope.account.getUserName()}</p>

                <a class="btn btn-outline-danger" type="button" style="width: 80px;" href="../logout">Logout</a>

            </div>
        </div>
    </nav>
</div>
