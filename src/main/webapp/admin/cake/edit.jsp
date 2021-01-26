<%--
  Created by IntelliJ IDEA.
  User: Nghia
  Date: 11/8/2020
  Time: 4:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Cake</title>
</head>

<div style="border: blueviolet; width: 80%; height: 20%; text-align: center" >
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
                <td>Name: </td>
                <td><input type="text" name="name" id="name" value="${requestScope["cake"].getName()}"></td>
            </tr>
            <tr>
                <td>Size: </td>
                <td><select name="size" value="${requestScope["cake"].getSize()}">
                    <option value="S">S</option>
                    <option value="M">M</option>
                    <option value="L">L</option>
                </select></td>
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
        <input type="submit" value="Update cake">
        </table>
    </fieldset>
</form>
<p>
    <a href="/cakeServlet">Back to Menu</a>
</p>
</div>
</body>
</html>
