<%--
  Created by IntelliJ IDEA.
  User: Nghia
  Date: 11/9/2020
  Time: 10:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Detail Bill List</title>
</head>
<body>
<a href="/detailBillServlet?action=create">Create new Detail Bill</a>
<div>
    <table style="border: solid">
        <tr>
            <h1><td colspan="8" align="center">Bill</td></h1>
        </tr>
        <tr>
            <td>Detail Bill ID</td>
            <td>Bill ID</td>
            <td>Cake ID</td>
            <td>Bill Quantity</td>
            <td>Bill Price</td>
        </tr>
        <tr>
            <c:forEach items='${requestScope["detailBillList"]}' var="detailBill" varStatus="loop">
        <tr>
            <td>${detailBill.getDetailId()}</td>
            <td>${detailBill.getBill().getBillId()}</td>
            <td>${detailBill.getCake().getCakeId()}</td>
            <td>${detailBill.getBillQuantity()}</td>
            <td>${detailBill.getBillPrice()}</td>
            <td><a href="/detailBillServlet?action=edit&id=${detailBill.getDetailId()}">Edit</a></td>
            <td><a href="/detailBillServlet?action=delete&id=${detailBill.getDetailId()}">Delete</a></td>
        </tr>
            <%--                <td><img src="${item.getImg}" alt="${item.getName}"></td>--%>
        </c:forEach>
        </tr>
    </table>
</div>
</body>
</html>
