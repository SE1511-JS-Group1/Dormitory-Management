<%-- 
 /*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * boarder header.jsp
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-01-27      1.0                 HuyLQ           Update code
 */
--%>

<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="#" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <div class="logo" style="width: 70px;">                            
                    <img src="../images/logo.png" class="img-thumbnail" alt="logo">
                </div>
                <div style="width: 20px;"></div>
            </a>
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="room" class="nav-link px-2 ${page eq 'room' ? 'text-white':'text-secondary'}">Room</a></li>
                <li><a href="notice" class="nav-link px-2 ${page eq 'notice' ? 'text-white':'text-secondary'}">Notice</a></li>
                <li><a href="#" class="nav-link px-2 ${page eq 'report' ? 'text-white':'text-secondary'}">Wallet</a></li>
                <li><a href="feedback" class="nav-link px-2 ${page eq 'Feedback' ? 'text-white':'text-secondary'}">Feedback</a></li>
            </ul>
            <div class="text-end">
                <a type="button" class="btn btn-outline-light me-2" href="changepassword">${sessionScope.account.getUserName()}</a>
                <a type="button" class="btn btn-warning" href="../logout">Logout</a>
            </div>
        </div>
    </div>
</header>

