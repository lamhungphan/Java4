<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/head.jsp" %>
    <title>Admin Page</title>
</head>
<body>
<%@include file="/common/header.jsp" %>
<div class="container-fluid">
    <h3 class="mt-3">User List</h3>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Username</th>
            <th>Email</th>
            <th>Admin</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.isAdmin ? 'Yes' : 'No'}</td>
                <td>
                    <a href="<c:url value='/admin/user?action=edit&id=${user.id}' />" class="btn btn-success">Edit</a>
                    <button onclick="deleteUser(${user.id})" class="btn btn-danger">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script>
    function deleteUser(id) {
        $.ajax({
            url: '/admin/user?action=delete&id=' + id,
            type: 'POST'
        }).then(function () {
            window.location.reload();
        }).fail(function () {
            alert('Delete failed!');
        });
    }
</script>
<%@include file="/common/footer.jsp" %>
</body>
</html>
