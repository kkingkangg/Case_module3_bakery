<%--
  Created by IntelliJ IDEA.
  User: Nghia
  Date: 11/8/2020
  Time: 10:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bill List</title>
</head>
<body>
<center>
<a href="/billServlet?action=create">Create new Bill</a>
<div>
    <table style="border: solid">
        <tr>
            <h1><td colspan="8" align="center">Bill</td></h1>
        </tr>
        <tr>
            <td>ID</td>
            <td>Bill Date</td>
            <td>Customer ID</td>

        </tr>
        <tr>
            <c:forEach items='${requestScope["billList"]}' var="bill" varStatus="loop">
        <tr>
            <td>${bill.getBillId()}</td>
            <td>${bill.getBillDate()}</td>
            <td>${bill.getCustomer().getCustomerId()}</td>
            <td><a href="/billServlet?action=edit&id=${bill.getBillId()}">Edit</a></td>
            <td><a href="/billServlet?action=delete&id=${bill.getBillId()}">Delete</a></td>
        </tr>

            <%--                <td><img src="${item.getImg}" alt="${item.getName}"></td>--%>
        </c:forEach>
        </tr>
    </table>
</div>
<a href="/billServlet">Back to Customer list</a><br/>
<a href="index.jsp">Home</a>
</center>
</body>
</html>
