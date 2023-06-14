<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ClimaxStore Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
          integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />
    <link href="css/styles.css" rel="stylesheet" />
    <script src="js/scripts.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
    <script
            src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
            crossorigin="anonymous"></script>
    <style>
        .text-underline:hover {
            cursor: pointer;
            text-decoration: underline;
        }
    </style>
</head>
<body>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <img class="mb-3 me-3" src="https://i.ibb.co/f4FWK77/gamepad.png" alt="gamepad" style="height: 3%; width: 3%">
        <a class="navbar-brand me-5 h3" href="home">CLIMAX STORE</a>
        <button aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"
                class="navbar-toggler"
                data-bs-target="#navbarSupportedContent" data-bs-toggle="collapse" type="button">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse h5" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item me-3 ms-2">
                    <a aria-current="page" class="nav-link active text-underline" href="home">Product</a>
                </li>
                <li class="nav-item dropdown me-2 ms-2">
                    <a aria-expanded="false" class="nav-link active dropdown-toggle btn-outline-warning"
                       data-bs-toggle="dropdown"
                       href="#"
                       id="navbarDropdown" role="button">
                        Categories
                    </a>
                    <ul aria-labelledby="navbarDropdown" class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Action</a></li>
                        <li><a class="dropdown-item" href="#">Role-play</a></li>
                        <li><a class="dropdown-item" href="#">Something else here</a></li>
                    </ul>
                </li>
                <li class="nav-item me-2 ms-2">
                    <a class="nav-link active text-underline" href="#">About</a>
                </li>
                <c:if test="${userLogged.getPosition_id() == 2}">
                    <li class="nav-item me-2 ms-2">
                        <a class="nav-link active text-underline" href="#">Wallet</a>
                    </li>
                    <li class="nav-item me-2 ms-2">
                        <a class="nav-link active text-underline" href="#">Library</a>
                    </li>
                </c:if>
                <c:if test="${userLogged.getPosition_id() == 1}">
                    <li class="nav-item me-2 ms-2">
                        <a class="nav-link active text-underline" href="#">Product list</a>
                    </li>
                    <li class="nav-item me-2 ms-2">
                        <a class="nav-link active text-underline" href="#">User list</a>
                    </li>
                </c:if>

            </ul>
            <div class="d-flex">
                <c:if test="${userLogged == null}">
                    <div class="btn-group">
                        <div class="d-flex flex-column justify-content-center d-inline-block">
                            <button class="btn btn-warning me-3 text-underline" data-bs-target="#loginModal"
                                    data-bs-toggle="modal" id="loginBtn"
                                    type="button">
                                Log In
                            </button>
                        </div>
                        <div class="d-flex flex-column justify-content-center d-inline-block">
                            <button class="btn btn-warning me-3 text-underline " data-bs-target="#registerModal"
                                    data-bs-toggle="modal" id="registerBtn"
                                    type="button">
                                Register
                            </button>
                        </div>
                    </div>
                </c:if>
                <c:if test="${userLogged != null}">
                    <div class="btn-group me-2 ms-2">
                        <a aria-expanded="false" class="nav-link active dropdown-toggle btn-outline-warning"
                           data-bs-toggle="dropdown"
                           href="#"
                           id="userDropdown" role="button">
                            <i class="fa-solid fa-user fa-xl" style="color: #e66100;"></i>
                        </a>
                        <ul aria-labelledby="navbarDropdown" class="dropdown-menu">
                            <li><a class="dropdown-item" href="/home?action=profile">Profile</a></li>
                            <li><a class="dropdown-item" href="/home?action=logout">Logout</a></li>
                        </ul>
                        <div class="btn btn-warning" type="button">
                            <i class="bi-cart-fill me-2 me-2"></i>
                            <a href="/home?action=cart">Cart</a>
                            <span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
                        </div>
                    </div>
                </c:if>

            </div>


            <form class="d-flex mb-0 justify-content-center" method="get">
                <input name="action" type="hidden" value="search">
                <input aria-label="Search" class="form-control me-2" placeholder="Search" type="search" id="searchBar" name="searchBar">
                <button class="btn btn-warning text-underline" type="submit">Search</button>
            </form>
        </div>

    </div>
</nav>

<!-- Login Modal -->

<div aria-hidden="true" aria-labelledby="loginModal" class="modal fade" id="loginModal" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="card-body p-4">
                <h5 class="card-title text-center mb-5 fw-bold fs-5 ">Log In</h5>
                <form action="home" id="loginForm" method="post" name="loginForm">
                    <input name="purpose" type="hidden" value="login">
                    <div class="form-floating mb-3">
                        <input class="form-control" id="loginString"
                               name="loginString" placeholder="name@example.com, 279467389, name_cp2" required>
                        <label for="loginString">Email, Phone or Username</label>
                    </div>
                    <div class="form-floating mb-3 input-group-addon" id="showHidePass">
                        <input class="form-control" id="loginPass" name="loginPass" placeholder="Password" type="password" required>
                        <label for="loginPass">Password</label>
                        <span class="input-group-addon" role="button" title="veiw password" id="passBtn">
                            <i class="fa fa-eye fa-fw" aria-hidden="true"></i>
                        </span>
                    </div>
                    <div class="form-check mb-3">
                        <input class="form-check-input" id="rememberPasswordCheck" type="checkbox" value="">
                        <label class="form-check-label" for="rememberPasswordCheck">
                            Remember password
                        </label>
                    </div>

                    <hr class="my-4">


                    <div class="d-grid mt-2">
                        <button class="btn btn-warning btn-login text-uppercase fw-bold" type="submit">
                            Login
                        </button>
                        <c:if test="${loginStatus.equals('failed')}">
                            <script>
                                document.getElementById("loginBtn").click();
                            </script>
                            <p class="text-danger">Login unsuccessfully</p>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Register Modal -->
<div aria-hidden="true" aria-labelledby="registerModal" class="modal fade" id="registerModal" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="card-body p-4">
                <h5 class="card-title text-center mb-5 fw-bold fs-5">Register</h5>
                <form action="home" id="registerFrom" method="post" name="registerFrom">
                    <input name="purpose" type="hidden" value="register">
                    <div class="form-floating mb-3">
                        <input class="form-control" id="Username"
                               name="Username" placeholder="Username" required>
                        <label for="Username">Username</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input class="form-control" id="Email"
                               name="Email" placeholder="Email" required>
                        <label for="Email">Email</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input class="form-control" id="PhoneNumbers"
                               name="PhoneNumbers" placeholder="PhoneNumbers" required>
                        <label for="PhoneNumbers">Phone numbers</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input class="form-control" id="registerPass" name="registerPass" placeholder="Password" type="password" required>
                        <label for="registerPass">Password</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input class="form-control" id="registerConfirmPass" name="registerConfirmPass" placeholder="Confirm Password" type="password" required>
                        <label for="registerConfirmPass">Confirm Password</label>
                    </div>
                    <div class="form-check mb-3">
                        <input class="form-check-input" id="agreement" type="checkbox" required>
                        <label class="form-check-label" for="agreement">
                            Agreement to our policies
                        </label>
                    </div>
                    <hr class="my-4">
                    <div class="d-grid mt-2">
                        <button class="btn btn-warning btn-register text-uppercase fw-bold" type="submit">
                            Register
                        </button>
                        <c:if test="${registerStatus.equals('failed')}">
                            <script>
                                document.getElementById("registerBtn").click();
                            </script>
                            <p class="text-danger">register unsuccessfully</p>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    const PassBtn = document.querySelector('#passBtn');
    PassBtn.addEventListener('click', () => {
        const input = document.querySelector('#loginPass');
        input.getAttribute('type') === 'password' ? input.setAttribute('type', 'text') : input.setAttribute('type', 'password');

        if (input.getAttribute('type') === 'text'){
            PassBtn.innerHTML = '<i class="fa fa-eye-slash"></i>';
        } else{
            PassBtn.innerHTML = '<i class="fa fa-eye fa-fw" aria-hidden="true"></i>';
        }

    });
</script>
</body>
</html>
