<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Share Video</title>
</head>
<body>
<h1>Share Video</h1>
<form action="<c:url value='/video?action=share&id=${video.href}'/>" method="post">
    <input type="hidden" name="action" value="share"/>
    <input type="hidden" name="id" value="${video.href}"/>
    <label for="email">Enter recipient's email:</label>
    <input type="email" id="email" name="email" required/>
    <button type="submit">Share</button>
</form>
</body>
</html>
