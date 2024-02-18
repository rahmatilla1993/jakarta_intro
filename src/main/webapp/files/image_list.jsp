<%--
  Created by IntelliJ IDEA.
  User: rahmatilla
  Date: 2/18/24
  Time: 10:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Image List</title>
    <jsp:include page="../resources/css/styles.jsp"/>
</head>
<body>

<div class="container">
    <c:forEach items="${images}" var="image">
        <img src="${"data:image/jpg; base64, %s".formatted(image)}" style="width: 25rem; height: 20rem;"
            class="rounded mr-2 mb-2" alt="...">
    </c:forEach>
</div>


<jsp:include page="../resources/js/main.jsp"/>
</body>
</html>
