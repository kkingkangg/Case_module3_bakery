<%--
  Created by IntelliJ IDEA.
  User: Nghia
  Date: 11/8/2020
  Time: 11:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Bill</title>
</head>
<body>
<center>
<p>
    <c:if test = '${requestScope["message"] != null}'>
        <span class = "message">${requestScope["message"]}</span>
    </c:if>
</p>
<form method="post">
    <fieldset>
        <legend>Bill information</legend>
        <table>
            <tr>
                <td>Bill Date: </td>
                <td><input type="text" name="billDate" id="billDate" value="${requestScope["bill"].getBillDate()}"></td>
            </tr>
            <tr>
                <td>Customer ID: </td>
                <td><input type="text" name="customerId" id="customerId" value="${requestScope["bill"].getCustomer().getCustomerId()}"></td>
            </tr>
        </table>
        <input type="submit" value="Update Bill">
        </table>
    </fieldset>
</form>
<a href="/billServlet">Back to Customer list</a><br/>
<a href="index.jsp">Home</a>
</center>
</body>
</html>
