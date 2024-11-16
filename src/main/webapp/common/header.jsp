<%--<c:url var="root" value="/user"/>--%>
<nav class="navbar navbar-expand navbar-light bg-dark static-top osahan-nav sticky-top">
    &nbsp;&nbsp;
    <a class="navbar-brand mr-1" href="#">
        <img class="img-fluid" alt="" src="<c:url value='/templates/user/img/netflix-icon.png'/>"
             width="50px">
    </a>
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
                <img alt="Avatar" src="<c:url value='/templates/user/img/user.png'/>" width="30px" height="30px" class="rounded-circle"/>
                <span class="ml-2">Osahan</span>
            </a>
            <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="${root}/account">
                    <i class="fas fa-fw fa-user-circle"></i> &nbsp; My Account
                </a>
                <a class="dropdown-item" href="${root}/changePass">
                    <i class="fas fa-fw fa-cog"></i> &nbsp; Change password
                </a>
                <a class="dropdown-item" href="${root}/forgetPass">
                    <i class="fas fa-fw fa-cog"></i> &nbsp; Forget password
                </a>
                <a class="dropdown-item" href="${root}/register">
                    <i class="fas fa-fw fa-cog"></i> &nbsp; Registration
                </a>
                <a class="dropdown-item" href="${root}/login">
                    <i class="fas fa-fw fa-cog"> </i> &nbsp; Login
                </a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="${root}/login" data-toggle="modal" data-target="#logoutModal">
                    <i class="fas fa-fw fa-sign-out-alt"></i> &nbsp; Logout
                </a>
            </div>
        </li>
    </ul>
</nav>
