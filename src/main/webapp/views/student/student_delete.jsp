<%--
  Created by IntelliJ IDEA.
  User: rahmatilla
  Date: 2/18/24
  Time: 10:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Group Add</title>
    <jsp:include page="/fragments/css/styles.jsp"/>
</head>
<body>
<jsp:include page="../navbar.jsp"/>

<div class="container">
    <div class="rows">
        <div class="col-8 offset-2">
            <form method="post">
                <div class="mb-3">
                    <h2 class="alert alert-danger">Are you sure delete <c:out value="${student.getFirstName()}"/> ? </h2>
                </div>
                <button type="submit" class="btn btn-danger">Yes</button>
                <a href="/student/list" class="btn btn-warning">Back</a>
            </form>
        </div>
    </div>
</div>
<jsp:include page="../../fragments/js/main.jsp"/>

</body>
</html>
