<%--
  Created by IntelliJ IDEA.
  User: Nghia
  Date: 11/9/2020
  Time: 10:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Create new Customer</title>
    <style>
        .message{
            color: green;
        }
    </style>
</head>
<body>

<h1>Create new Customer</h1>
<p>
    <c:if test = '${requestScope["message"] != null}'>
        <span class = "message">${requestScope["message"]}</span>
    </c:if>
</p>
<form method="post">
    <fieldset>
        <legend>Detail Bill information</legend>
        <table>
            <tr>
                <td>Bill ID: </td>
                <td><input type="text" name="billId"></td>
            </tr>
            <tr>
                <td>Cake ID: </td>
                <td><input type="text" name="cakeId"></td>
            </tr>
            <tr>
                <td>Bill Quantity: </td>
                <td><input type="text" name="billQuantity"></td>
            </tr>
            <tr>
                <td>Bill Price: </td>
                <td><input type="text" name="billPrice"></td>
            </tr>
        </table>
        <input type="submit" value="Create">
    </fieldset>
    <p>
        <a href="/detailBillServlet">Back to list</a>
    </p>
</form>
</body>
</html>
