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

        <%--        <form class="form-inline my-2 my-lg-0">--%>
        <%--            <input class="form-control mr-sm-2" type="search" placeholder="Từ khóa" aria-label="Search">--%>
        <%--            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Tìm kiếm</button>--%>
        <%--        </form>--%>

        <!-- Button trigger modal -->
    </div>
</nav>


<div>
    <center>
    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="d-block w-100" src="img/slider1.jpg" alt="First slide" style="width: 50% ; height: 500px">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="img/slider2.jpg" alt="Second slide" style="width: 50% ; height: 500px">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="img/slider3.jpg" alt="Third slide" style="width: 50%; height: 500px">
            </div>
        </div>
    </div>


    <div class="container">
        <div class="container-fluid" style="height: 20px">
            <h3>Please let we know who you are!</h3>
            <button type="button" class="btn btn-primary ml-2" data-toggle="modal" data-target="#loginModal" style="position: relative; top: 5px; left: -60px; width: 90px"  >
                Admin
            </button>

            <form action="/cakeServlet" style="height: 10px">
                <button type="submit" class="btn btn-primary ml-2" style="position: relative; top: -32px; left: 40px; width: 90px" >
                    Customer
                </button>
            </form>
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
        </div>
        <div class="row">

            <!-- Login Modal -->
            <div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Đăng nhập</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form action="/cakeServlet" method="post">
                                <input type="text" name="action" value="login" hidden>
                                <div class="form-group">
                                    <label >Tên đăng nhập</label>
                                    <input type="text" class="form-control" name="username">
                                </div>
                                <div class="form-group">
                                    <label >Mật khẩu</label>
                                    <input type="password" class="form-control" name="password">
                                </div>
                                <button type="submit" class="btn btn-primary w-100" >Đăng nhập</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </center>
</div>


</body>
</html>
