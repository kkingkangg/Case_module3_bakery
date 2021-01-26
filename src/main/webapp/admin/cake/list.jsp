<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %><%--
  Created by IntelliJ IDEA.
  User: Nghia
  Date: 11/6/2020
  Time: 9:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
      integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

<!-- jQuery and JS bundle w/ Popper.js -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>
<html>
<head>
    <title>Menu Cake</title>
    <style>
        body {
            padding-bottom: 25px;
        }

        .card {
            margin-top: 25px;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <!-- Image and text -->
    <nav class="navbar navbar-light bg-light">
        <a class="navbar-brand" href="http://localhost:8080/">
            <img src="https://i.pinimg.com/originals/44/fa/b7/44fab70348c532174bf51070ec9fbd25.jpg" width="30"
                 height="30" class="d-inline-block align-top" alt="" loading="lazy">
            Bakery
        </a>
    </nav>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/cakeServlet?action=list">Menu Cake</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/customerServlet">List Customer</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/categoryServlet">List Category</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/billServlet">List Bill</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/detailBillServlet">List Detail Bill</a>
            </li>
        </ul>

        <form class="form-inline my-2 my-lg-0" action="/cakeServlet" method="post">
            <input type="text" name="action" value="search" hidden>
            <input class="form-control mr-sm-2" type="search" placeholder="Search" name="search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>

        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary ml-2" data-toggle="modal" data-target="#loginModal">
            Login
        </button>
    </div>
</nav>

<!-- Login Modal -->
<div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Login</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="username">Login</label>
                        <input type="text" class="form-control" id="username">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password">
                    </div>
                    <button class="btn btn-primary w-100">Login</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="container">

<%--    mesage--%>
    <center>
        <p>
            <c:if test='${requestScope["message"] != null}'>
        <div class="alert alert-danger" role="alert"
             style="color: #721c24; background-color: #f8d7da; border-color: #f5c6cb;text-align: center; width: 35%;">
            <span class="message">${requestScope["message"]}</span>
        </div>
        </c:if>
        </p>
    </center>

    <button class="btn btn-success btn-md" data-toggle="modal"
            data-target="#addCake">Add New Cake
    </button>

    <div class="row">
        <c:forEach items='${requestScope["cakeList"]}' var="cake" varStatus="loop">
            <div class="col-4">
                <div class="card">
                    <img src="${cake.getImage()}"
                         class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title"><strong>${cake.getCakeId()} – ${cake.getName()}</strong></h5>
                        <p class="card-text">
                                <%--                        <strong>In</strong><br>--%>
                            – Category: ${cake.getCategory().getCategoryName()}<br>
                            – Size: ${cake.getSize()}<br>
                            - Quantity: ${cake.getQuantity()}<br>
                            - Expire Date: ${cake.getExpireDate()}
                        </p>
                        <div class="d-flex align-items-center justify-content-between">
                            <span class="h4 text-info m-0">₫${nf.format(cake.getPrice())}</span>
                            <button class="btn btn-success btn-md" data-toggle="modal"
                                    data-target="#edit${cake.getCakeId()}">Update
                            </button>
                            <button class="btn btn-success btn-md" data-toggle="modal"
                                    data-target="#delete${cake.getCakeId()}">Delete
                            </button>
                        </div>


                        <!-- Modal -->

                        <div class="modal fade" id="addCake" tabindex="-1"
                             aria-labelledby="addModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="addCakeLabel">Enter Your Information</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="/cakeServlet" method="post">
                                            <input type="text" name="action" value="create" hidden>
                                            <table>
                                                <tr>
                                                    <td>Name:</td>
                                                    <td><input type="text" name="name"></td>
                                                </tr>
                                                <tr>
                                                    <td>Size:</td>
                                                    <td><select name="size">
                                                        <option value="S">S</option>
                                                        <option value="M">M</option>
                                                        <option value="L">L</option>
                                                    </select></td>
                                                </tr>
                                                <tr>
                                                    <td>Expire Date:</td>
                                                    <td><input type="text" name="expireDate"></td>
                                                </tr>
                                                <tr>
                                                    <td>Quantity:</td>
                                                    <td><input type="text" name="quantity"></td>
                                                </tr>
                                                <tr>
                                                    <td>Price:</td>
                                                    <td><input type="text" name="price"></td>
                                                </tr>
                                                <tr>
                                                    <td>Image:</td>
                                                    <td><input type="text" name="image"></td>
                                                </tr>
                                                <tr>
                                                    <td>Category:</td>
                                                    <td>
                                                        <select name="category">
                                                            <c:forEach items='${requestScope["categoryList"]}'
                                                                       var="category" varStatus="loop">
                                                                <option value="${category.getCategoryName()}">${category.getCategoryName()}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                </tr>
                                            </table>
                                            <button type="submit" class="btn btn-primary w-100">Edit</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="modal fade" id="edit${cake.getCakeId()}" tabindex="-1"
                             aria-labelledby="editModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="editModalLabel">Enter Your Information</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="/cakeServlet" method="post">
                                            <input type="text" name="action" value="edit" hidden>
                                            <table>
                                                <tr>
                                                    <td>ID:</td>
                                                    <td><input type="text" value="${cake.getCakeId()}" disabled></td>
                                                    <input type="hidden" name="id" value="${cake.getCakeId()}">
                                                </tr>
                                                <tr>
                                                    <td>Name:</td>
                                                    <td><input type="text" name="name" id="name"
                                                               value="${cake.getName()}"></td>
                                                </tr>
                                                <tr>
                                                    <td>Size:</td>
                                                    <td><input type="text" name="size" value="${cake.getSize()}"></td>
                                                </tr>
                                                <tr>
                                                    <td>Expire Date:</td>
                                                    <td><input type="text" name="expireDate"
                                                               value="${cake.getExpireDate()}"></td>
                                                </tr>
                                                <tr>
                                                    <td>Quantity:</td>
                                                    <td><input type="text" name="quantity"
                                                               value="${cake.getQuantity()}"></td>
                                                </tr>
                                                <tr>
                                                    <td>Price:</td>
                                                    <td><input type="text" name="price" value="${cake.getPrice()}"></td>
                                                </tr>
                                                <tr>
                                                    <td>Image:</td>
                                                    <td><input type="text" name="image" value="${cake.getImage()}"></td>
                                                </tr>
                                                <tr>
                                                    <td>Category:</td>
                                                    <td>
                                                        <select name="category" value="${cake.getCategory().getCategoryName()}">
                                                            <c:forEach items='${requestScope["categoryList"]}'
                                                                       var="category" varStatus="loop">
                                                                <option value="${category.getCategoryName()}">${category.getCategoryName()}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                </tr>
                                            </table>
                                            <button type="submit" class="btn btn-primary w-100">Edit</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="modal fade" id="delete${cake.getCakeId()}" tabindex="-1"
                             aria-labelledby="deleteModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="deleteModalLabel">Enter Your Information</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="/cakeServlet" method="post">
                                            <input type="hidden" name="action" value="delete" hidden>
                                            <table>
                                                <tr>
                                                    <td>ID:</td>
                                                    <td><input type="text" value="${cake.getCakeId()}" disabled></td>
                                                    <input type="hidden" name="id" value="${cake.getCakeId()}">
                                                </tr>
                                                <tr>
                                                    <td>Name:</td>
                                                    <td><input type="text" name="name" value="${cake.getName()}"
                                                               disabled></td>
                                                </tr>
                                                <tr>
                                                    <td>Size:</td>
                                                    <td><input type="text" name="size" value="${cake.getSize()}"
                                                               disabled></td>
                                                </tr>
                                                <tr>
                                                    <td>Expire Date:</td>
                                                    <td><input type="text" name="expireDate"
                                                               value="${cake.getExpireDate()}" disabled></td>
                                                </tr>
                                                <tr>
                                                    <td>Quantity:</td>
                                                    <td><input type="text" name="quantity" value="${cake.getQuantity()}"
                                                               disabled></td>
                                                </tr>
                                                <tr>
                                                    <td>Price:</td>
                                                    <td><input type="text" name="price" value="${cake.getPrice()}"
                                                               disabled></td>
                                                </tr>
                                                <tr>
                                                    <td>Image:</td>
                                                    <td><input type="text" name="image" value="${cake.getImage()}"
                                                               disabled></td>
                                                </tr>
                                                <tr>
                                                    <td>Category:</td>
                                                    <td><input type="text" name="category"
                                                               value="${cake.getCategory().getCategoryName()}" disabled>
                                                    </td>
                                                </tr>
                                            </table>
                                            <button type="submit" class="btn btn-primary w-100">Delete</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
</div>
</body>
</html>

