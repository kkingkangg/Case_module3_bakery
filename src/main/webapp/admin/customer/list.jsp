<%--
  Created by IntelliJ IDEA.
  User: Nghia
  Date: 11/8/2020
  Time: 9:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
<div>
    <table style="border: solid">
        <tr>
            <td colspan="8" align="center">Customer</td>
        </tr>
        <tr>
            <td>ID</td>
            <td>Name</td>
            <td>Phone</td>
            <td>Address</td>
        </tr>
        <tr>
            <c:forEach items='${requestScope["customerList"]}' var="customer" varStatus="loop">
        <tr>
            <td>${customer.getCustomerId()}</td>
            <td>${customer.getName()}</td>
            <td>${customer.getPhone()}</td>
            <td>${customer.getAddress()}</td>
            <td><a href="/customerServlet?action=edit&id=${customer.getCustomerId()}">Edit</a></td>
            <td><a href="/customerServlet?action=delete&id=${customer.getCustomerId()}">Delete</a></td>
        </tr>
            <%--                <td><img src="${item.getImg}" alt="${item.getName}"></td>--%>
        </c:forEach>
        </tr>
    </table>
    <a href="/customerServlet?action=create">Create new Customer</a><br/>
    <a href="index.jsp">Home</a>
</div>
</center>
</body>
</html>
