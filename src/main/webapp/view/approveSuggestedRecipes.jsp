<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Duyệt Công Thức Đề Xuất</title>
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
        .btn-custom-secondary {
            background-color: #FF5722;
            border-color: #FF5722;
            color: white;
        }
        .btn-custom-secondary:hover {
            background-color: #f4511e;
            border-color: #f4511e;
            color: white;
        }
        .table {
            background-color: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .table thead {
            background-color: #FF9800;
            color: white;
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
    <h1 class="pacifico-font text-center mb-4" style="color: #FF5722;">Duyệt Công Thức Đề Xuất</h1>
    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Tên Món Ăn</th>
                <th>Người Đề Xuất</th>
                <th>Thao Tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="recipe" items="${suggestedRecipes}">
                <tr>
                    <td><c:out value="${recipe.id}" /></td>
                    <td><c:out value="${recipe.name}" /></td>
                    <td><c:out value="${recipe.suggestedBy}" /></td>
                    <td>
                        <form action="approveSuggestedRecipes" method="post" style="display: inline;">
                            <input type="hidden" name="action" value="approveSuggestedRecipe">
                            <input type="hidden" name="id" value="${recipe.id}">
                            <button type="submit" name="status" value="approved" class="btn btn-custom-primary btn-sm">Duyệt</button>
                            <button type="submit" name="status" value="rejected" class="btn btn-custom-secondary btn-sm">Từ Chối</button>
                        </form>
                        <a href="${pageContext.request.contextPath}/recipes?action=details&id=${recipe.id}&type=suggested" class="btn btn-sm btn-info">Chi Tiết</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="text-center mt-4">
        <a href="recipes" class="btn btn-custom-primary">Quay lại danh sách</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>