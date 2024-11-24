
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/head.jsp" %>
    <title>Online Entertainment</title>
</head>
<body>
<%@include file="/common/header.jsp" %>
<div class="container-fluid row p-0 m-0">
    <div class="container mt-5 col-4 align-content-center">
        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>
        <h3>Change Password</h3>
<%--        <form action="change-password" method="post">--%>
            <div class="mb-3">
                <label for="currPass" class="form-label">Current Password</label>
                <input type="password" class="form-control" id="currPass" name="currPass" required>
            </div>
            <div class="mb-3">
                <label for="newPass" class="form-label">New Password</label>
                <input type="password" class="form-control" id="newPass" name="newPass" required>
            </div>
            <div class="mb-3">
                <label for="confirmPass" class="form-label">Confirm Password</label>
                <input type="password" class="form-control" id="confirmPass" name="confirmPass" required>
            </div>
            <button type="submit" class="btn btn-primary align-content-center" id="changePassBtn">Change Password</button>
        <h5 style="color: red" id="messageChangePass"></h5>
    <%--        </form>--%>
    </div>
</div>
</body>
<script>
    $('#changePassBtn').click(function (e) {
        e.preventDefault(); // Ngăn gửi form mặc định
        $('#messageChangePass').text('').css('color', ''); // Xóa thông báo cũ
        $('#changePassBtn').prop('disabled', true).text('Changing...');

        var currPass = $('#currPass').val();
        var newPass = $('#newPass').val();
        var confirmPass = $('#confirmPass').val();

        $.ajax({
            url: 'changePass',
            type: 'POST',
            data: {
                currPass: currPass,
                newPass: newPass,
                confirmPass: confirmPass
            }
        }).done(function (data) {
            $('#messageChangePass').css('color', 'green').text(data.message);
            $('#currPass, #newPass, #confirmPass').val(''); // Clear input fields
        }).fail(function (jqXHR) {
            var errorMessage = jqXHR.responseJSON ? jqXHR.responseJSON.message : 'An unknown error occurred';
            $('#messageChangePass').css('color', 'red').text(errorMessage);
        }).always(function () {
            $('#changePassBtn').prop('disabled', false).text('Change Password');
        });
    });
</script>
</html>