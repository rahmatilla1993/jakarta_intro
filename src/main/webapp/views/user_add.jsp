<%--
  Created by IntelliJ IDEA.
  User: rahmatilla
  Date: 2/19/24
  Time: 3:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Users</title>
    <jsp:include page="../resources/style.jsp"/>
</head>
<body>
<jsp:include page="/views/navbar.jsp"/>
<div class="container">
    <div class="rows">
        <div class="col-10 offset-1">
            <form method="post" action="/user/add">
                <div class="mb-3">
                    <label for="name" class="form-label">User Name</label>
                    <input type="text" class="form-control" id="name" name="name">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../resources/main.jsp"/>
</body>
</html>
