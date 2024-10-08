<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Công Thức Nấu Ăn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .form-control {
            white-space: pre-wrap;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Quản Lý Công Thức Nấu Ăn</h1>
    <div class="text-center mb-4">
        <a href="recipes?action=recipes" class="btn btn-primary">Quay Lại Danh Sách</a>
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
                            <input type="text" class="form-control" id="name" name="name" value="<c:out value='${recipe.name}' />"/>
                        </div>
                        <div class="mb-3">
                            <label for="cooktime" class="form-label">Thời Gian Nấu:</label>
                            <input type="text" class="form-control" id="cooktime" name="cooktime" value="<c:out value='${recipe.cooktime}' />"/>
                        </div>
                        <div class="mb-3">
                            <label for="ingredient" class="form-label">Nguyên Liệu:</label>
                            <textarea class="form-control" id="ingredient" name="ingredient" rows="5"><c:out value='${recipe.ingredient}' /></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="inscription" class="form-label">Mô Tả:</label>
                            <textarea class="form-control" id="inscription" name="inscription" rows="5"><c:out value='${recipe.inscription}' /></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="image" class="form-label">Ảnh URL:</label>
                            <input type="text" class="form-control" id="image" name="image" value="<c:out value='${recipe.image}' />"/>
                        </div>
                        <div class="d-grid">
                            <input type="submit" class="btn btn-primary" value="Lưu"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>