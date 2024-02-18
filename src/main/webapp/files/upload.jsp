<%--
  Created by IntelliJ IDEA.
  User: rahmatilla
  Date: 2/17/24
  Time: 11:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload</title>
    <jsp:include page="../resources/css/styles.jsp"/>
</head>
<body>
<form enctype="multipart/form-data" method="post" action="/upload">
    <div class="mb-3">
        <label for="file" class="form-label">File</label>
        <input type="file" class="form-control" id="file" name="file" placeholder="Choose a file...">
    </div>
    <button class="btn btn-primary" type="submit">Upload</button>
</form>

<jsp:include page="../resources/js/main.jsp"/>
</body>
</html>
