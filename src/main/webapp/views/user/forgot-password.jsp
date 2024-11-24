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
        <h3>Forgot Password</h3>
        <%--        <form action="forgot-password" method="post">--%>
        <div class="mb-3">
            <label for="email" class="form-label">Enter your registered email</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <button type="submit" class="btn btn-primary content-center" id="sendBtn">Submit</button>
        <h5 style="color: red" id="messageForgotPass"></h5>
        <%--        </form>--%>
    </div>
</div>
</body>
<script>
    $('#sendBtn').click(function () {
        $('#messageForgotPass').text('');
        var email = $('#email').val()
        var formData = {'email': email};
        $.ajax({
            url: 'forgotPass',
            type: 'POST',
            data: formData
        }).then(function (data) {
            $('#messageForgotPass').text('Password has been reset, please check your email');
            setTimeout(function () {
                window.location.href = 'http://localhost:8080/Java4_Youtube_war/index';
            }, 5 * 1000);
        }).fail(function (error) {
            $('#messageForgotPass').text('Your information is not correct, try again');
        });
    });
</script>
</html>