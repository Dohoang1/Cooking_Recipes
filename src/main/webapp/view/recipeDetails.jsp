<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Chi Tiết Công Thức</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        p {
            white-space: pre-wrap;
            overflow-y: auto;
        }
    </style>

</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Chi Tiết Công Thức</h1>
    <c:if test="${not empty recipe}">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title"><c:out value="${recipe.name}"/></h5>
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
        <a href="recipes" class="btn btn-primary">Quay lại danh sách</a>
    </div>
</div>
</body>
</html>