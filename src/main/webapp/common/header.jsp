<nav class="navbar navbar-expand navbar-light bg-dark static-top osahan-nav sticky-top">
    &nbsp;&nbsp;
    <a class="navbar-brand mr-1" href="<c:url value='/index'/>">
        <img class="img-fluid" alt="" src="<c:url value='/templates/user/img/netflix-icon.png'/>"
             width="50px">
    </a>
    <p>Visitor count: ${applicationScope.visitors}</p>
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
                <span class="ml-2">${sessionScope.currentUser.username}</span>
            </a>
            <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="userDropdown">
                <c:choose>
                    <c:when test="${not empty sessionScope.currentUser}">
                        <a class="dropdown-item" href="account">
                            <i class="fas fa-fw fa-user-circle"></i> &nbsp; My Account
                        </a>
                        <a class="dropdown-item" href="changePass">
                            <i class="fas fa-fw fa-cog"></i> &nbsp; Change password
                        </a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="<c:url value='/logout'/>" data-toggle="modal"
                           data-target="#logoutModal">
                            <i class="fas fa-fw fa-sign-out-alt"></i> &nbsp; Logout
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a class="dropdown-item" href="forgotPass">
                            <i class="fas fa-fw fa-cog"></i> &nbsp; Forgot password
                        </a>
                        <a class="dropdown-item" href="<c:url value='/register'/>">
                            <i class="fas fa-fw fa-cog"></i> &nbsp; Register
                        </a>
                        <a class="dropdown-item" href="<c:url value='/login'/>">
                            <i class="fas fa-fw fa-cog"> </i> &nbsp; Login
                        </a>
                    </c:otherwise>
                </c:choose>
            </div>
        </li>
    </ul>
</nav>
