<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Đề Xuất Công Thức Nấu Ăn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Đề Xuất Công Thức Nấu Ăn</h1>
    <div class="row justify-content-center">
        <div class="col-md-8">
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
                    <button type="submit" class="btn btn-primary">Đề Xuất Công Thức</button>
                </div>
            </form>
        </div>
    </div>
    <div class="text-center mt-4">
        <a href="recipes" class="btn btn-primary">Quay lại danh sách</a>
    </div>
</div>
</body>
</html>