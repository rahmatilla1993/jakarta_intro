<%--
  Created by IntelliJ IDEA.
  User: rahmatilla
  Date: 2/18/24
  Time: 10:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Navbar</title>
    <jsp:include page="../fragments/css/styles.jsp"/>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/users">User List</a>
            <a class="navbar-brand" href="/user/add">User Add</a>
            <a class="navbar-brand" href="/group/list">Group List</a>
            <a class="navbar-brand" href="/group/add">Group add</a>
            <a class="navbar-brand" href="/student/list">Student List</a>
            <a class="navbar-brand" href="/student/add">Student add</a>
        </div>
    </nav>
</div>
<jsp:include page="../fragments/js/main.jsp"/>
</body>
</html>
