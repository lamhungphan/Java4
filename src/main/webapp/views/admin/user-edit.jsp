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
<div class="container mt-5">
    <h3 class="text-center">Add/Edit User</h3>
    <div class="card mt-4">
        <div class="card-header bg-primary text-white" id="message">Add User</div>
        <div class="card-body">
            <form enctype="application/x-www-form-urlencoded">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" value="${user != null ? user.username : ''}"
                           class="form-control" placeholder="Enter full name">
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" value="${user != null ? user.email : ''}"
                           class="form-control" placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" value="${user != null ? user.password : ''}"
                           class="form-control" placeholder="Enter password">
                </div>
                <div class="form-group">
                    <label for="isAdmin">Admin</label>
                    <input type="checkbox" id="isAdmin"
                    ${user != null && user.isAdmin ? 'checked' : ''} />
                </div>
                <div class="text-center">
                    <button type="button" id="resetBtn" class="btn btn-info">Reset</button>
                    <button type="button" id="submitBtn" class="btn btn-success">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    var originalData = {};

    $(document).ready(function () {
        originalData = {
            username: $('#username').val(),
            email: $('#email').val(),
            password: $('#password').val(),
            isAdmin: $('#isAdmin').is(':checked')
        };
    });

    $('#resetBtn').click(function () {
        $('#username').val(originalData.username);
        $('#email').val(originalData.email);
        $('#password').val(originalData.password);
        $('#isAdmin').prop('checked', originalData.isAdmin);
    });

    $('#submitBtn').click(function () {
        const formData = {
            username: $('#username').val(),
            email: $('#email').val(),
            password: $('#password').val(),
            isAdmin: $('#isAdmin').is(':checked')
        };

        const isEdit = ${isEdit};
        const action = isEdit ? 'edit' : 'add';
        $.ajax({
            url: '/admin/user?action=' + action,
            type: 'POST',
            data: formData,
        }).then(function () {
            window.location.href = '/admin/user?action=view';
        }).fail(function () {
            $('#message').text('Operation failed. Please try again!');
        });
    });
</script>
<%@include file="/common/footer.jsp" %>
</body>
</html>
