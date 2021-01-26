<%--
  Created by IntelliJ IDEA.
  User: Nghia
  Date: 11/8/2020
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delete Cake</title>
</head>
<body>

<p>
    <c:if test = '${requestScope["message"] != null}'>
        <span class = "message">${requestScope["message"]}</span>
    </c:if>
</p>
<form method="post">
    <fieldset>
        <legend>Cake information</legend>
        <table>
            <tr>
                <td>ID: </td>
                <td><input type="text" name="id" id="id" value="${requestScope["cake"].getCakeId()}"></td>
            </tr>
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name" id="name" value="${requestScope["cake"].getName()}"></td>
            </tr>
            <tr>
                <td>Size: </td>
                <td><input type="text" name="size" id="size" value="${requestScope["cake"].getSize()}"></td>
            </tr>
            <tr>
                <td>Expire Date: </td>
                <td><input type="text" name="expireDate" id="ExpireDate" value="${requestScope["cake"].getExpireDate()}"></td>
            </tr>
            <tr>
                <td>Quantity: </td>
                <td><input type="text" name="quantity" id="quantity" value="${requestScope["cake"].getQuantity()}"></td>
            </tr>
            <tr>
                <td>Price: </td>
                <td><input type="text" name="price" id="price" value="${requestScope["cake"].getPrice()}"></td>
            </tr>
            <tr>
                <td>Image: </td>
                <td><input type="text" name="image" id="image" value="${requestScope["cake"].getImage()}"></td>
            </tr>
            <tr>
                <td>Category: </td>
                <td><input type="text" name="category" id="category" value="${requestScope["cake"].getCategory().getCategoryName()}"></td>
            </tr>
        </table>
        <input type="submit" value="Delete Cake">
        </table>
    </fieldset>
</form>
<p>
    <a href="/cakeServlet">Back to Menu</a>
</p>
</body>
</html>
