<%--
  Created by IntelliJ IDEA.
  User: Nghia
  Date: 11/8/2020
  Time: 10:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create new Bill</title>
    <style>
        .message{
            color: green;
        }
    </style>
</head>
<body>
<center>
<h1>Create new Bill</h1>
<p>
    <c:if test = '${requestScope["message"] != null}'>
        <span class = "message">${requestScope["message"]}</span>
    </c:if>
</p>
<p>
    <a href="/billServlet">Back to Bill list</a>
</p>
<form method="post">
    <fieldset>
        <legend>Bill Information</legend>
        <table>
            <tr>
                <td>Bill Date: </td>
                <td><input type="text" name="billDate" id="billDate"></td>
            </tr>
            <tr>
                <td>Customer ID: </td>
                <td><input type="text" name="customerId" id="customerId"></td>
            </tr>
        </table>
        <input type="submit" value="Create">
    </fieldset>
</form>
    <a href="/billServlet">Back to Customer list</a><br/>
    <a href="index.jsp">Home</a>
</center>
</body>
</html>
