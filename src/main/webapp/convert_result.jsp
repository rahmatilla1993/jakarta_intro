<%--
  Created by IntelliJ IDEA.
  User: rahmatilla
  Date: 2/17/24
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Convert Result</title>
</head>
<body>
<h1>Result</h1>
<%if (request.getAttribute("error") != null) {%>
<p>Error <%=request.getAttribute("error")%>
</p>
<%} else %>
<p>Result is <%=request.getAttribute("result")%>
</p>
<a href="/convert.jsp">Back to convert</a>
</body>
</html>
