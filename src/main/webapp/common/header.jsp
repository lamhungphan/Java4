<nav class="navbar navbar-expand navbar-light bg-dark text-white static-top osahan-nav sticky-top">
    &nbsp;&nbsp;
    <a class="navbar-brand mr-1" href="<c:url value='/index'/>">
        <img class="img-fluid" alt="" src="<c:url value='/templates/user/img/netflix-icon.png'/>"
             width="50px">
    </a>
    <ul class="list-unstyled d-flex flex-row align-items-center mb-0">
        <!-- Mục My Favorite -->
        <li class="nav-item me-4"> <!-- Thêm lớp me-4 để tạo khoảng cách -->
            <a class="nav-link text-white rounded-0" href="#">
                My Favorite
            </a>
        </li>
        <!-- Mục Share Video -->
        <li class="nav-item me-4"> <!-- Thêm lớp me-4 để tạo khoảng cách -->
            <a class="nav-link text-white rounded-0" href="#">
                Share Video
            </a>
        </li>
        <!-- Mục Visitor Count -->
        <li class="nav-item text-white rounded-0">Visitor count: ${applicationScope.visitors}</li>
    </ul>
    <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-5 my-2 my-md-0 osahan-navbar-search">
        <div class="input-group">
            <input type="text" class="form-control" placeholder="Search for...">
            <div class="input-group-append">
                <button class="btn btn-light" type="button">
                    <i class="fas fa-search"></i>
                </button>
            </div>
        </div>
    </form>
    <ul class="navbar-nav ml-auto ml-md-0 osahan-right-navbar">
        <li class="nav-item dropdown no-arrow osahan-right-navbar-user">
            <a class="nav-link dropdown-toggle user-dropdown-link" href="#" id="userDropdown" role="button"
               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <img alt="Avatar" src="<c:url value='/templates/user/img/user.png'/>" width="30px" height="30px"
                     class="rounded-circle"/>
                <span class="ml-2 text-white">${sessionScope.currentUser.username}</span>
            </a>
            <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="userDropdown">
                <c:choose>
                    <c:when test="${not empty sessionScope.currentUser}">
                        <c:if test="${sessionScope.currentUser.isAdmin == true}">
                            <a class="dropdown-item" href="<c:url value='/admin/dashboard'/>">
                                <i class="fa-solid fa-film"></i> &nbsp; Admin Page
                            </a>
                        </c:if>
                        <a class="dropdown-item" href="<c:url value='/updateAccount'/>">
                            <i class="fa-solid fa-user"></i> &nbsp; My Account
                        </a>
                        <a class="dropdown-item" href="<c:url value='/changePass'/>">
                            <i class="fa-solid fa-lock"></i>&nbsp; Change password
                        </a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="<c:url value='/logout'/>" data-toggle="modal"
                           data-target="#logoutModal">
                            <i class="fas fa-fw fa-sign-out-alt"></i> &nbsp; Logout
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a class="dropdown-item" href="<c:url value='/forgotPass'/>">
                            <i class="fa-solid fa-envelope"></i> Forgot password
                        </a>
                        <a class="dropdown-item" href="<c:url value='/register'/>">
                            <i class="fa-solid fa-fingerprint"></i> Register
                        </a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/login">
                            <i class="fa-solid fa-right-to-bracket"></i> Login
                        </a>
                    </c:otherwise>
                </c:choose>
            </div>
        </li>
    </ul>
</nav>
