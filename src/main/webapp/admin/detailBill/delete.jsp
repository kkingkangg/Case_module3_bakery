<%--
  Created by IntelliJ IDEA.
  User: Nghia
  Date: 11/9/2020
  Time: 10:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delete Detail Bill</title>
</head>
<body>
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
                <td>Detail Bill ID: </td>
                <td><input type="text" name="id" id="id" value="${requestScope["detailBill"].getDetailId()}"></td>
            </tr>
            <tr>
                <td>Bill ID: </td>
                <td><input type="text" name="billId" id="billId" value="${requestScope["detailBill"].getBill().getBillId()}"></td>
            </tr>
            <tr>
                <td>Cake ID: </td>
                <td><input type="text" name="cakeId" id="cakeId" value="${requestScope["detailBill"].getCake().getCakeId()}"></td>
            </tr>
            <tr>
                <td>Bill Quantity: </td>
                <td><input type="text" name="billQuantity" id="billQuantity" value="${requestScope["detailBill"].getBillQuantity()}"></td>
            </tr>
            <tr>
                <td>Bill Price: </td>
                <td><input type="text" name="billPrice" id="billPrice" value="${requestScope["detailBill"].getBillPrice()}"></td>
            </tr>

        </table>
        <input type="submit" value="Delete Cake">
        </table>
    </fieldset>
</form>
<p>
    <a href="/detailBillServlet">Back to Menu</a>
</p>
</body>
</html>
