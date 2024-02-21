<%--
  Created by IntelliJ IDEA.
  User: rahmatilla
  Date: 2/19/24
  Time: 10:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Users</title>
    <jsp:include page="../../fragments/css/styles.jsp"/>
</head>
<body>
<jsp:include page="/views/navbar.jsp"/>
<div class="container">
    <div class="rows">
        <div class="col-10 offset-1">
            <table class="table mt-3">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Username</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <th scope="row">${user.getId()}</th>
                        <td>${user.getUsername()}</td>
                        <td><a href="/user/edit/${user.getId()}" class="btn btn-warning">Edit</a> || <a
                                href="/user/delete/${user.getId()}"
                                class="btn btn-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="../../fragments/js/main.jsp"/>
</body>
</html>