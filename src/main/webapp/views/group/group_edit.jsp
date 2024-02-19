<%--
  Created by IntelliJ IDEA.
  User: rahmatilla
  Date: 2/18/24
  Time: 10:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
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
                        <label for="name" class="form-label">Group Name</label>
                        <input type="text" class="form-control" id="name" name="name" value="${group.getName()}">
                    </div>
                    <div class="mb-3">
                        <label for="students_count" class="form-label">Students count</label>
                        <input type="number" class="form-control" id="students_count" name="students_count"
                               value="${group.getStudentsCount()}">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
        </div>
    </div>
</div>
<jsp:include page="../../fragments/js/main.jsp"/>

</body>
</html>
