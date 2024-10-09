<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Đề Xuất Công Thức Nấu Ăn</title>
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
        .card {
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .card-header {
            background-color: #FF9800;
            color: white;
            border-radius: 10px 10px 0 0;
        }
        .form-control {
            border-radius: 5px;
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
    <h1 class="text-center mb-4" style="color: #FF5722;">Đề Xuất Công Thức Nấu Ăn</h1>
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h2 class="text-center">Đề Xuất Công Thức Mới</h2>
                </div>
                <div class="card-body">
                    <form action="suggestRecipe" method="post">
                        <input type="hidden" name="action" value="suggestRecipe">
                        <div class="mb-3">
                            <label for="name" class="form-label">Tên Món Ăn:</label>
                            <input type="text" class="form-control" id="name" name="name" required>
                        </div>
                        <div class="mb-3">
                            <label for="cooktime" class="form-label">Thời Gian Nấu:</label>
                            <input type="text" class="form-control" id="cooktime" name="cooktime" required>
                        </div>
                        <div class="mb-3">
                            <label for="ingredient" class="form-label">Nguyên Liệu:</label>
                            <textarea class="form-control" id="ingredient" name="ingredient" rows="3" required></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="inscription" class="form-label">Mô Tả:</label>
                            <textarea class="form-control" id="inscription" name="inscription" rows="3" required></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="image" class="form-label">Ảnh URL:</label>
                            <input type="text" class="form-control" id="image" name="image">
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-custom-primary">Đề Xuất Công Thức</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="text-center mt-4">
        <a href="recipes" class="btn btn-custom-primary">Quay lại danh sách</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>