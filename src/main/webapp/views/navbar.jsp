<%--
  Created by IntelliJ IDEA.
  User: rahmatilla
  Date: 2/19/24
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Navbar</title>
    <jsp:include page="../resources/style.jsp"/>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/users">User List</a>
            <a class="navbar-brand" href="/login">Login</a>
            <a class="navbar-brand" href="/user/add">User add</a>
        </div>
    </nav>
</div>
<jsp:include page="../resources/main.jsp"/>
</body>
</html>
