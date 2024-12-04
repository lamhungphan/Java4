<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/head.jsp" %>
    <title>Online Entertainment</title>
    <script>
        // Function to set a cookie with a specific expiration time
        function setCookie(name, value, days) {
            var expires = "";
            if (days) {
                var date = new Date();
                date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000)); // Set expiration
                expires = "; expires=" + date.toUTCString();
            }
            document.cookie = name + "=" + (value || "") + expires + "; path=/";
        }

        // Function to get a cookie by name
        function getCookie(name) {
            var nameEQ = name + "=";
            var ca = document.cookie.split(';');
            for (var i = 0; i < ca.length; i++) {
                var c = ca[i];
                while (c.charAt(0) == ' ') c = c.substring(1, c.length);
                if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
            }
            return null;
        }

        // When the page loads, check if the "remember me" cookie exists
        window.onload = function() {
            var username = getCookie("username");
            var password = getCookie("password");
            if (username) {
                document.getElementById("username").value = username;
                document.getElementById("remember").checked = true;
            }
            if (password) {
                document.getElementById("password").value = password;
            }
        };

        // Save the username and password in cookies when the form is submitted
        document.querySelector("form").onsubmit = function() {
            if (document.getElementById("remember").checked) {
                setCookie("username", document.getElementById("username").value, 7); // Store for 7 days
                setCookie("password", document.getElementById("password").value, 7); // Store for 7 days
            } else {
                // If "Remember me" is unchecked, delete the cookies
                setCookie("username", "", -1);
                setCookie("password", "", -1);
            }
        };
    </script>
</head>
<body>
<%@include file="/common/header.jsp" %>
<div class="container-fluid row p-0 m-0">
    <div class="container mt-5 col-4 align-content-center">
        <h3>Login</h3>
        <c:if test="${not empty error}">
            <p class="error" style="color: red">${error}</p>
        </c:if>
        <form action="login" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="form-check mb-3">
                <input type="checkbox" class="form-check-input" id="remember" name="remember">
                <label class="form-check-label" for="remember">Remember me</label>
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
    </div>
</div>
</body>
</html>
