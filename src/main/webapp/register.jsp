<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng Ký</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #FFF5E6;
            font-family: 'Arial', sans-serif;
        }
        .navbar {
            background-color: #FF9800 !important;
        }
        .btn-custom-primary {
            background-color: #4CAF50;
            border-color: #4CAF50;
            color: white;
        }
        .btn-custom-primary:hover {
            background-color: #45a049;
            border-color: #45a049;
            color: white;
        }
        .register-form {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="http://localhost:8080/recipes">
            <img src="${pageContext.request.contextPath}/logo.png" alt="Logo" class="d-inline-block align-top" style="height: 40px;">
        </a>
    </div>
</nav>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="register-form">
                <h2 class="text-center mb-4" style="color: #FF5722;">Đăng Ký Tài Khoản</h2>
                <form action="register" method="post">
                    <div class="mb-3">
                        <label for="username" class="form-label">Tên đăng nhập:</label>
                        <input type="text" class="form-control" id="username" name="username" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Mật khẩu:</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                    <div class="mb-3">
                        <label for="confirmPassword" class="form-label">Xác nhận mật khẩu:</label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                    </div>
                    <div class="d-grid">
                        <button type="submit" class="btn btn-custom-primary">Đăng ký</button>
                    </div>
                </form>
                <% if(request.getAttribute("errorMessage") != null) { %>
                <div class="alert alert-danger mt-3" role="alert">
                    <%= request.getAttribute("errorMessage") %>
                </div>
                <% } %>
                <% if(request.getAttribute("successMessage") != null) { %>
                <div class="alert alert-success mt-3" role="alert">
                    <%= request.getAttribute("successMessage") %>
                </div>
                <% } %>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>