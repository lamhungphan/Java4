<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/head.jsp" %>
    <!-- DataTables CSS -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.min.css">
    <title>Online Entertainment</title>
</head>
<body>
<%@include file="/common/header.jsp" %>
<div class="container-fluid row p-0 m-0">
    <div class="nav nav-pills bg-dark flex-column col-md-2 p-0 sticky-top" style="max-height: 100vh; font-size: 1.2rem">
        <li class="nav-item w-100">
            <a class="nav-link text-white rounded-0 p-3 w-100"
               href="<c:url value='/admin/dashboard'/>">
                <i class="fa-solid fa-house"></i> Dashboard
            </a>
        </li>
        <li class="nav-item w-100">
            <a class="nav-link text-white w-100 p-3 rounded-0"
               href="#">
                <i class="fa-solid fa-users"></i> Users
            </a>
        </li>
        <li class="nav-item dropdown w-100">
            <a class="nav-link text-white rounded-0 dropdown-toggle w-100 p-3"
               href="#" data-bs-toggle="dropdown">
                <i class="fa-solid fa-photo-film"></i> Videos
            </a>
            <ul class="dropdown-menu w-100 p-0 m-0 rounded-0">
                <li><a class="dropdown-item w-100 p-2" href="#">Manage Videos</a></li>
                <li><a class="dropdown-item w-100 p-2" href="#">Video Detail</a></li>
            </ul>
        </li>
        <li class="nav-item w-100">
            <a class="nav-link text-white w-100 p-3 rounded-0"
               href="<c:url value='/admin/favorite'/> ">
                <i class="fa-solid fa-heart"></i> Favorites
            </a>
        </li>
    </div>

    <div class="col-md-10 p-0">
        <main class="pb-2" style="min-height: 93vh">
            <jsp:include page="${view}"/>
        </main>
    </div>
</div>

<%@include file="/common/footer.jsp" %>
<script>
    $(function () {
        $("#example2").DataTable({
            "paging": true, // Kích hoạt phân trang
            "lengthChange": false, // Không cho phép thay đổi số lượng bản ghi hiển thị
            "searching": false,
            "ordering": true,
            "info": true, // Hiển thị thông tin tổng số trang, bản ghi
            "autoWidth": false,
            "responsive": true
        });
    });
</script>
</body>
</html>