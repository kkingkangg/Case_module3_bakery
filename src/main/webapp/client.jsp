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
    <title>$Title$</title>
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
                <a class="nav-link" href="/cakeServlet">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Introduce</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Contact</a>
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
                        <label for="username">User Name</label>
                        <input type="text" class="form-control" id="username">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password">
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Login</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="container">
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
                            – Size: ${cake.getSize()}
                        </p>
                        <div class="d-flex align-items-center justify-content-between">
                            <span class="h4 text-info m-0">₫${nf.format(cake.getPrice())}</span>
                            <button class="btn btn-success btn-md" data-toggle="modal" data-target="#buy${cake.getCakeId()}">Buy
                            </button>
                        </div>


                        <!-- Login Modal -->
                        <div class="modal fade" id="buy${cake.getCakeId()}" tabindex="-1" aria-labelledby="buy${cake.getCakeId()}Label"
                             aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="buy${cake.getCakeId()}Label">Enter Your Information</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="/cakeServlet" method="post">
                                            <input type="text" name="action" value="buy" hidden>
                                            <input type="text" name="id" value="${cake.getCakeId()}" hidden>
                                            <table>
                                                <tr>
                                                    <td>Enter Your Quantity:</td>
                                                    <td><input type="text" name="quantity"></td>
                                                </tr>
                                                <tr>
                                                    <td>Enter Your Name:</td>
                                                    <td><input type="text" name="name" id="name"></td>
                                                </tr>
                                                <tr>
                                                    <td>Enter Your Phone:</td>
                                                    <td><input type="text" name="phone"></td>
                                                </tr>
                                                <tr>
                                                    <td>Enter Your Address:</td>
                                                    <td><input type="text" name="address"></td>
                                                </tr>
                                            </table>
                                            <button type="submit" class="btn btn-primary w-100">Buy</button>
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
