<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Chỉnh Sửa Công Thức Nấu Ăn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap" rel="stylesheet">
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
        .pacifico-font {
            font-family: 'Pacifico', cursive;
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
    <h1 class="pacifico-font text-center mb-4" style="color: #FF5722;">Chỉnh Sửa Công Thức Nấu Ăn</h1>
    <div class="text-center mb-4">
        <a href="recipes?action=recipes" class="btn btn-custom-primary">Quay Lại Danh Sách</a>
    </div>

    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h2 class="text-center">Chỉnh Sửa Công Thức</h2>
                </div>
                <div class="card-body">
                    <form method="post">
                        <c:if test="${recipe != null}">
                            <input type="hidden" name="id" value="<c:out value='${recipe.id}' />"/>
                        </c:if>
                        <div class="mb-3">
                            <label for="name" class="form-label">Tên Món Ăn:</label>
                            <input type="text" class="form-control" id="name" name="name" value="<c:out value='${recipe.name}' />" required/>
                        </div>
                        <div class="mb-3">
                            <label for="cooktime" class="form-label">Thời Gian Nấu:</label>
                            <input type="text" class="form-control" id="cooktime" name="cooktime" value="<c:out value='${recipe.cooktime}' />" required/>
                        </div>
                        <div class="mb-3">
                            <label for="ingredient" class="form-label">Nguyên Liệu:</label>
                            <textarea class="form-control" id="ingredient" name="ingredient" rows="5" required><c:out value='${recipe.ingredient}' /></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="inscription" class="form-label">Mô Tả:</label>
                            <textarea class="form-control" id="inscription" name="inscription" rows="5" required><c:out value='${recipe.inscription}' /></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="image" class="form-label">Ảnh URL:</label>
                            <input type="text" class="form-control" id="image" name="image" value="<c:out value='${recipe.image}' />"/>
                        </div>
                        <div class="d-grid">
                            <input type="submit" class="btn btn-custom-primary" value="Lưu"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>