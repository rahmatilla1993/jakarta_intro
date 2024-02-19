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
            <form method="post">
                <div class="mb-3">
                    <h2 class="alert alert-danger">Are you sure delete <c:out value="${username}"/> ? </h2>
                </div>
                <button type="submit" class="btn btn-danger">Yes</button>
                <a href="/users" class="btn btn-warning">Back</a>
            </form>
        </div>
    </div>
</div>
<jsp:include page="../../fragments/js/main.jsp"/>
</body>
</html>