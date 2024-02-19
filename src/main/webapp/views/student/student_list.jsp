<%--
  Created by IntelliJ IDEA.
  User: rahmatilla
  Date: 2/18/24
  Time: 10:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Group List</title>
    <jsp:include page="/fragments/css/styles.jsp"/>
</head>
<body>
<jsp:include page="../navbar.jsp"/>


<div class="container">
    <div class="rows">
        <div class="col-10 offset-1">
            <table class="table mt-3">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">FirstName</th>
                    <th scope="col">LastName</th>
                    <th scope="col">Age</th>
                    <th scope="col">GroupId</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${students}" var="student">
                    <tr>
                        <th scope="row">${student.getId()}</th>
                        <td>${student.getFirstName()}</td>
                        <td>${student.getLastName()}</td>
                        <td>${student.getAge()}</td>
                        <td>${student.getGroupId()}</td>
                        <td><a href="/student/edit/${student.getId()}" class="btn btn-warning">Edit</a> || <a
                                href="/student/delete/${student.getId()}"
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
