<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Number Conversion</h1>
<form action="/convert" method="post">
    <label for="number">Enter a number:</label>
    <input type="text" id="number" name="number">
    <label for="fromBase">From base:</label>
    <select id="fromBase" name="fromBase">
        <option value="2">Binary</option>
        <option value="8">Octal</option>
        <option value="10" selected>Decimal</option>
        <option value="16">Hexadecimal</option>
    </select>
    <label for="toBase">To base:</label>
    <select id="toBase" name="toBase">
        <option value="2">Binary</option>
        <option value="8">Octal</option>
        <option value="10" selected>Decimal</option>
        <option value="16">Hexadecimal</option>
    </select>
    <input type="submit" value="Convert">
</form>

<br/>
</body>
</html>