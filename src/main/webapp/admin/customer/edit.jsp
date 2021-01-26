<%--
  Created by IntelliJ IDEA.
  User: Nghia
  Date: 11/8/2020
  Time: 9:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update Customer</title>
</head>
<body>

<p>
    <c:if test = '${requestScope["message"] != null}'>
        <span class = "message">${requestScope["message"]}</span>
    </c:if>
</p>
<form method="post">
    <fieldset>
        <legend>Customer Information</legend>
        <table>
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name" id="name" value="${requestScope["customer"].getName()}"></td>
            </tr>
            <tr>
                <td>Phone: </td>
                <td><input type="text" name="phone" id="phone" value="${requestScope["customer"].getPhone()}"></td>
            </tr>
            <tr>
                <td>Address: </td>
                <td><input type="text" name="address" id="address" value="${requestScope["customer"].getAddress()}"></td>
            </tr>
        </table>
        <input type="submit" value="Update customer">
        </table>
    </fieldset>
</form>
<p>
    <a href="/customerServlet">Back to Customer list</a><br/>
    <a href="index.jsp">Home</a>
</p>
</body>
</html>
