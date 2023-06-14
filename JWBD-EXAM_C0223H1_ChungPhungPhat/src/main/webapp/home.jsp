<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: reyon
  Date: 13/06/2023
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
          integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
    <script
            src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
            crossorigin="anonymous"></script>

    <style>
        .bloc_left_price {
            color: #c01508;
            text-align: center;
            font-weight: bold;
            font-size: 150%;
        }
        .category_block li:hover {
            background-color: #007bff;
        }
        .category_block li:hover a {
            color: #ffffff;
        }
        .category_block li a {
            color: #343a40;
        }
        .add_to_cart_block .price {
            color: #c01508;
            text-align: center;
            font-weight: bold;
            font-size: 200%;
            margin-bottom: 0;
        }
        .add_to_cart_block .price_discounted {
            color: #343a40;
            text-align: center;
            text-decoration: line-through;
            font-size: 140%;
        }
        .product_rassurance {
            padding: 10px;
            margin-top: 15px;
            background: #ffffff;
            border: 1px solid #6c757d;
            color: #6c757d;
        }
        .product_rassurance .list-inline {
            margin-bottom: 0;
            text-transform: uppercase;
            text-align: center;
        }
        .product_rassurance .list-inline li:hover {
            color: #343a40;
        }
        .reviews_product .fa-star {
            color: gold;
        }
        .pagination {
            margin-top: 20px;
        }
        footer {
            background: #343a40;
            padding: 40px;
        }
        footer a {
            color: #f8f9fa!important
        }
        .card-img-top {
            width: 100%;
            height: 15vw;
            object-fit: cover;
        }
    </style>
</head>
<body>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">PRODUCT</h1>
    </div>
</section>
<div class="col">
    <div class="row">
        <c:forEach var="product" items="${productList}">
            <div class="col-12 col-md-6 col-lg-4">
                <div class="card">
                    <img class="card-img-top" src="${product.getImage_url()}" alt="Card image cap">
                    <div class="card-body">
                        <h4 class="card-title"><a href="product.html" title="View Product"><c:out value="${product.getName()}"/></a></h4>
                        <p class="card-text"><c:out value="${product.getDescription()}"/></p>
                        <div class="row">
                            <div class="col">
                                <p class="btn btn-danger btn-block"><c:out value="${product.getPrice()}"/> $</p>
                            </div>
                            <div class="col">
                                <a href="/home?action=select&id=${product.getId()}" class="btn btn-success btn-block">Add to cart</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <a href="/home?action=cart" class="btn btn-success btn-block">BUY NOW</a>
</div>
</body>
</html>
