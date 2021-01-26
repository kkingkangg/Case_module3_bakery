<%--
  Created by IntelliJ IDEA.
  User: Nghia
  Date: 11/8/2020
  Time: 3:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create new Cake</title>
    <style>
        .message {
            color: green;
        }
    </style>
</head>
<body>

<h1>Create new cake</h1>
<p>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<p>
    <a href="/cakeServlet">Back to Menu</a>
</p>
<form method="post">
    <fieldset>
        <legend>Cake information</legend>
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" id="name"></td>
            </tr>
            <tr>
                <td>Size:</td>
                <td><select name="size">
                    <option>S</option>
                    <option>M</option>
                    <option>L</option>
                </select></td>
            </tr>
            <tr>
                <td>Expire Date:</td>
                <td><input type="text" name="expireDate" id="ExpireDate"></td>
            </tr>
            <tr>
                <td>Quantity:</td>
                <td><input type="text" name="quantity" id="quantity"></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="text" name="price" id="price"></td>
            </tr>
            <tr>
                <td>Image:</td>
                <td><input type="text" name="image" id="image"></td>
            </tr>
            <tr>
                <td>Category:</td>
                <td>
                    <select name="category">
                        <c:forEach items='${requestScope["categoryList"]}' var="category" varStatus="loop">
                            <option value="${category.getCategoryName()}">${category.getCategoryName()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <input type="submit" value="Save">
    </fieldset>
</form>
</body>
</html>
