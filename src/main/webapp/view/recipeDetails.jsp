<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Chi Tiết Công Thức</title>
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
        p {
            white-space: pre-wrap;
            overflow-y: auto;
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
    <h1 class="text-center mb-4" style="color: #FF5722;">Chi Tiết Công Thức</h1>
    <c:if test="${not empty recipe}">
        <div class="card">
            <div class="card-header">
                <h2 class="text-center"><c:out value="${recipe.name}"/></h2>
            </div>
            <div class="card-body">
                <p class="card-text"><strong>Thời Gian Nấu:</strong> <c:out value="${recipe.cooktime}"/></p>

                <div class="fw-bold mb-2">Nguyên Liệu:</div>
                <p class="border rounded p-2 bg-light"><c:out value="${recipe.ingredient}"/></p>

                <div class="fw-bold mb-2">Mô Tả:</div>
                <p class="border rounded p-2 bg-light"><c:out value="${recipe.inscription}"/></p>

                <img src="<c:out value='${recipe.image}'/>" alt="Recipe Image" class="img-fluid rounded shadow mt-3"/>
            </div>
        </div>
    </c:if>
    <div class="text-center mt-4">
        <a href="recipes" class="btn btn-custom-primary">Quay lại danh sách</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>