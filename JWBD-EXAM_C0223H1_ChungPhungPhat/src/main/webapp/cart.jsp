<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: reyon
  Date: 13/06/2023
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
</head>
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
        color: #f8f9fa !important
    }
</style>
<body>
<div class="container">
    <div class="row">
        <div>
            <div class="table-responsive">
                <table class="table table-striped">
                    <tbody>
                    <c:forEach var="item" items="${cartList}">
                        <tr>
                            <td><c:out value="${item.getProductName()}"/></td>
                            <td><c:out value="${item.getQuantity()}"/></td>
                            <td><c:out value="${item.getPrice()}"/></td>

                        </tr>

                    </c:forEach>
                    <tr>
                        <td><c:out value="${total}"/> </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col mb-2">
            <div class="row">
                <div class="col-sm-12  col-md-6">
                    <button class="btn btn-block btn-light">Continue Shopping</button>
                </div>
                <div class="col-sm-12 col-md-6 text-right">
                    <button class="btn btn-lg btn-block btn-success text-uppercase">Checkout</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
