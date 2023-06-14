<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: reyon
  Date: 12/06/2023
  Time: 08:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Detail</title>
</head>
<body>
<jsp:include page="navbar.jsp"/>

<section class="py-5">
    <div class="container px-4 px-lg-5 my-5">
        <div class="row gx-4 gx-lg-5 align-items-center">
            <div class="col-md-6">
                <img class="card-img-top mb-5 mb-md-0"
                     src="${viewGame.getGameImage()}" alt="..."/>
            </div>
            <div class="col-md-6">
                <h1 class="display-5 fw-bolder">
                    <c:out value="${viewGame.getGameName()}"/>
                </h1>
                <div class="small mb-1">
                    Category:
                    <c:out value="${viewGameCate}"/>
                </div>
                <div class="small mb-1">
                    Developer:
                    <c:out value="${viewGameDev}"/>
                </div>
                <div class="fs-5 mb-5">
                    <span>
                        $
                        <c:out value="${viewGame.getPrice()}"/>
                    </span>
                </div>
                <p class="lead">
                    <c:out value="${viewGame.getGameDescription()}"/>
                </p>
                <div class="d-flex">
                    <c:if test="${userLogged != null}">
                        <button class="btn btn-outline-dark flex-shrink-0" type="button" id="addToCart"
                                name="addToCart">
                            <i class="bi-cart-fill me-1"></i>
                            Add to cart
                        </button>
                    </c:if>
                    <c:if test="${userLogged.getPosition_id() == 1}">
                        <button class="btn btn-outline-dark flex-shrink-0 me-2 ms-2" type="button" id="editInfo"
                                name="editInfo">
                            Edit Info
                        </button>
                        <button class="btn btn-outline-dark flex-shrink-0 me-2 ms-2" type="button" id="deleteProduct"
                                name="deleteProduct">
                            Delete Product
                        </button>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
<jsp:include page="footer.jsp"/>
</html>
