
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
        <form action="forgot-password" method="post">
            <div class="mb-3">
                <label for="email" class="form-label">Enter your registered email</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <button type="submit" class="btn btn-primary content-center">Submit</button>
        </form>
    </div>
</div>
</body>
</html>