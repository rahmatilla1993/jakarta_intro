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
                    <label for="firstName" class="form-label">FirstName</label>
                    <input type="text" class="form-control" id="firstName" name="firstName">
                </div>
                <div class="mb-3">
                    <label for="lastName" class="form-label">LastName</label>
                    <input type="text" class="form-control" id="lastName" name="lastName">
                </div>
                <div class="mb-3">
                    <label for="age" class="form-label">Age</label>
                    <input type="text" class="form-control" id="age" name="age">
                </div>
                <div class="mb-3">
                    <label for="group" class="form-label">Group</label>
                    <select name="group" id="group">
                        <c:forEach items="${groups}" var="group">
                            <option value="${group.getId()}">${group.getName()}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="../../fragments/js/main.jsp"/>

</body>
</html>
