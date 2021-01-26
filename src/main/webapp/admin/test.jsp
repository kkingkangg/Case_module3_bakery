<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/4/2020
  Time: 11:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Trang chủ</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
<body>




<nav class="navbar navbar-expand-sm bg-dark navbar-dark" style="height: 80px">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
            <i class="fad fa-rocket-launch"></i>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="#"><h1 style="font-family: monospace"><img src="image/logoXu.jpg" alt="Xuka's Shop"></h1></a>
        </li>
    </ul>
    <form class="form-inline my-2 my-lg-0" action="/home" method="post">
        <input type="submit" value="Đăng xuất" class="btn btn-outline-secondary">
    </form>
    <form class="form-inline my-2 my-lg-0" action="/home?action=searchByAdmin" method="post">
        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="search">
        <button type="submit" class="btn btn-outline-secondary">TÌM KIẾM</button>
    </form>
</nav>

<div>
    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="d-block w-100" src="image/banner2.jpg" alt="First slide" style="width: 50% ; height: 200px">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="image/banner1.jpg" alt="Second slide" style="width: 50% ; height: 200px">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="image/banner3.jpg" alt="Third slide" style="width: 50%; height: 200px">
            </div>
        </div>
    </div>
    <div class="container">
        <p style="margin-top: 50px">SẢN PHẨM <i style="color: red">Xuka's shop</i></p>
        <a href="/customer?action=listCustomer" style="float: right"  class="btn btn-primary">Danh sách khách hàng</a>
        <button style="background-color: red; float: left" type="button" class="btn btn-outline-secondary" data-toggle="modal" data-target="#exampleModal">Thêm mới</button>
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="container">
                            <h2>Thêm sản phẩm</h2>
                            <form action="/home?action=create"  method="post">
                                <label>ID:</label>
                                <input type="text" class="form-control" name="id" value="1">
                                <label>Name</label>
                                <input type="text" class="form-control" name="name">
                                <label>Price</label>
                                <input type="text" class="form-control" name="price">
                                <label>Image</label>
                                <input type="text" class="form-control" name="image">
                                <label>Type</label>
                                <select name="type">
                                    <option value="1">Nail</option>
                                    <option value="2">lipstick</option>
                                    <option value="3">Perfume</option>
                                    <option value="4">other</option>
                                </select>
                                <br>
                                <br>
                                <label>Brand</label>
                                <select name="brand">
                                    <option value="1">MAC</option>
                                    <option value="2">Olay</option>
                                    <option value="3">Local Brand</option>
                                    <option value="4">Gucci</option>
                                    <option value="5">Dior</option>
                                    <option value="6">Marc Jacobs</option>
                                </select>
                                <br>
                                <br>
                                <label>Origin</label>
                                <select name="origin">
                                    <option value="1">Việt Nam</option>
                                    <option value="2">USA</option>
                                    <option value="3">Singapore</option>
                                    <option value="4">france</option>
                                    <option value="5">Korea</option>
                                    <option value="6">Japan</option>
                                </select>
                                <br>
                                <label>Desc</label>
                                <input type="text" class="form-control" name="desc">
                                <div class="modal-footer">
                                    <input type="submit" class="btn btn-primary" value="Create">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr>


<%--        Hiển thị--%>
        <div class="row">
            <c:forEach var="product" items='${requestScope["product"]}'>
                <div class="col-md-6 col-lg-4" style="margin-bottom: 0px">
                    <div class="single_service">
                        <div class="thumb">

                            <div><img src="image/${product.image}.jpg" style="height: 150px"></div>
                        </div>
                        <div class="service_infor">
                            <p>Tên sản phẩm: <c:out value="${product.name}"/></p>
                            <p>Giá tiền: <c:out value="${product.price}"/></p>
                            <p>Nhãn hiệu: <c:out value="${product.brand.brand}"/></p>
                            <p>Xuất xứ: <c:out value="${product.origin.country}"/></p>
                            <p>Mô tả: <c:out value="${product.desc}"/></p>
                            <div class="row">
<%--                                sửa ở dưới đây--%>
                                <div class="col">
                                    <button type="button" class="btn btn-outline-secondary" data-toggle="modal" data-target="#exampleModalUpdate${product.id}">Chỉnh sửa</button>
                                    <div class="modal fade" id="exampleModalUpdate${product.id}" tabindex="-1" role="dialog"
                                         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <div class="container">
                                                        <h2>Chỉnh sửa thông tin sản phẩm</h2>
                                                        <form action="/home?action=edit"  method="post">
                                                            <label>ID:</label>
                                                            <input type="text" class="form-control" name="id" value="${product.id}">
                                                            <label>Name</label>
                                                            <input type="text" class="form-control" name="name" value="${product.name}">
                                                            <label>Price</label>
                                                            <input type="text" class="form-control" name="price" value="${product.price}">
                                                            <label>Image</label>
                                                            <input type="text" class="form-control" name="image" value="${product.image}">
                                                            <img src="image/${product.image}.jpg" alt="image product">
                                                            <br>
                                                                <%--                                                            <label>Type_id</label>--%>
                                                                <%--                                                            <input type="text" class="form-control" name="type" value="${product.type.id}">--%>
                                                                <%--                                                            <label>Brand_id</label>--%>
                                                                <%--                                                            <input type="text" class="form-control" name="brand" value="${product.brand.id}">--%>
                                                                <%--                                                            <label>Origin_id</label>--%>
                                                                <%--                                                            <input type="text" class="form-control" name="origin" value="${product.origin.id}">--%>
                                                            <label>Type</label>
                                                            <select name="type">
                                                                <option value="1">Nail</option>
                                                                <option value="2">lipstick</option>
                                                                <option value="3">Perfume</option>
                                                                <option value="4">Other</option>
                                                            </select>
                                                            <br>
                                                            <label>Brand</label>
                                                            <select name="brand">
                                                                <option value="1">MAC</option>
                                                                <option value="2">Olay</option>
                                                                <option value="3">Local Brand</option>
                                                                <option value="4">Gucci</option>
                                                                <option value="5">Dior</option>
                                                                <option value="6">Marc Jacobs</option>
                                                            </select>
                                                            <br>
                                                            <label>Origin</label>
                                                            <select name="origin">
                                                                <option value="1">Việt Nam</option>
                                                                <option value="2">USA</option>
                                                                <option value="3">Singapore</option>
                                                                <option value="4">france</option>
                                                                <option value="5">Korea</option>
                                                                <option value="6">Japan</option>
                                                            </select>
                                                            <label>Desc</label>
                                                            <input type="text" class="form-control" name="desc" value=" ${product.desc}">
                                                            <div class="modal-footer">
                                                                <input type="submit" class="btn btn-primary" value="update">
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
<%--    dưới dòng này là xóa--%>
                                <div class="col">
                                    <button type="button" class="btn btn-outline-secondary" data-toggle="modal" data-target="#exampleModalUpdate${product.name}">Xóa</button>
                                    <div class="modal fade" id="exampleModalUpdate${product.name}" tabindex="-1" role="dialog"
                                         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <div class="container">
                                                        <h2>ARE YOU SURE?</h2>
                                                        <form method="post" action="/home?action=delete">
                                                            label>ID:</label>
                                                            <input type="text" class="form-control" name="id" value="${product.id}">
                                                            <label>Name</label>
                                                            <input type="text" class="form-control" name="name" value="${product.name}">
                                                            <label>Price</label>
                                                            <input type="text" class="form-control" name="price" value="${product.price}">
                                                            <label>Image</label>
                                                            <input type="text" class="form-control" name="image" value="${product.image}">
                                                            <img src="image/${product.image}.jpg" alt="image product">
                                                            <br>
                                                            <label>Type_id</label>
                                                            <input type="text" class="form-control" name="type" value="${product.type.id}">
                                                            <label>Brand_id</label>
                                                            <input type="text" class="form-control" name="brand" value="${product.brand.id}">
                                                            <label>Origin_id</label>
                                                            <input type="text" class="form-control" name="origin" value="${product.origin.id}">
                                                            <label>Desc</label>
                                                            <input type="text" class="form-control" name="desc" ${product.desc}>
                                                            <div class="modal-footer">
                                                                <input type="submit" value="Xóa">
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                                                </div>
                                            </div>
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
</div>
<footer>
    <hr>
    <div class="footer-copyright text-center py-3">© 2020 Copyright:
        <a href="#">khanh Nobi</a>
        HOTLINE:<a href="$">0345541750</a>
    </div>
</footer>
</body>
</html>
