<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Công Thức Nấu Ăn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .recipe-image {
            max-width: 100px;
            max-height: 100px;
        }
        .recipe-content {
            white-space: pre-wrap;
            overflow-y: auto;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/recipes">Công Thức Nấu Ăn</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="d-flex align-items-center">
            <c:choose>
                <c:when test="${empty sessionScope.admin}">
                    <a href="${pageContext.request.contextPath}/login" class="btn btn-outline-primary">Đăng nhập Admin</a>
                </c:when>
                <c:otherwise>
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="adminDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                                ${sessionScope.admin}
                        </button>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="adminDropdown">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/recipes?action=create">Thêm Công Thức Mới</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
                        </ul>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="collapse navbar-collapse" id="navbarContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            </ul>
            <form class="d-flex align-items-center me-2" action="${pageContext.request.contextPath}/recipes" method="get">
                <span class="me-2">Tìm kiếm</span>
                <select class="form-select me-2" name="searchtype" style="width: auto;">
                    <option value="id">ID</option>
                    <option value="name">Tên Món Ăn</option>
                    <option value="ingredient">Nguyên Liệu</option>
                </select>
                <input class="form-control me-2" type="search" placeholder="Nhập từ khóa" name="keyword" style="width: auto;">
                <button class="btn btn-outline-success" type="submit">Tìm</button>
            </form>
            <form class="d-flex align-items-center me-2" action="${pageContext.request.contextPath}/recipes" method="get">
                <span class="me-2">Sắp xếp</span>
                <select class="form-select me-2" name="sortby" style="width: auto;">
                    <option value="ID">ID</option>
                    <option value="Name">Tên Món Ăn</option>
                </select>
                <select class="form-select me-2" name="order" style="width: auto;">
                    <option value="asc">Tăng dần</option>
                    <option value="desc">Giảm dần</option>
                </select>
                <button class="btn btn-outline-primary" type="submit">Sắp xếp</button>
            </form>
        </div>
    </div>
</nav>

<div class="container-fluid mt-4">
    <h2>Danh sách Công Thức Nấu Ăn</h2>
    <div class="table-responsive">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Tên Món Ăn</th>
                <th>Thời Gian</th>
                <th>Nguyên Liệu</th>
                <th>Mô Tả</th>
                <th>Ảnh</th>
                <c:if test="${not empty sessionScope.admin}">
                    <th>Thao Tác</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="recipe" items="${listRecipe}">
                <tr>
                    <td><c:out value="${recipe.id}"/></td>
                    <td><c:out value="${recipe.name}"/></td>
                    <td><c:out value="${recipe.cooktime}"/></td>
                    <td><div class="recipe-content"><c:out value="${recipe.ingredient}"/></div></td>
                    <td><div class="recipe-content"><c:out value="${recipe.inscription}"/></div></td>
                    <td>
                        <img src="<c:out value="${recipe.image}"/>" alt="Recipe Image" class="recipe-image">
                    </td>
                    <c:if test="${not empty sessionScope.admin}">
                        <td>
                            <a href="${pageContext.request.contextPath}/recipes?action=edit&id=${recipe.id}" class="btn btn-sm btn-primary">Sửa</a>
                            <a href="${pageContext.request.contextPath}/recipes?action=delete&id=${recipe.id}" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa công thức này?')">Xóa</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <c:if test="${currentPage != 1}">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/recipes?page=${currentPage - 1}&searchtype=${searchtype}&keyword=${keyword}&sortby=${sortby}&order=${order}">Trước</a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${totalPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active">
                            <span class="page-link">${i}</span>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link" href="${pageContext.request.contextPath}/recipes?page=${i}&searchtype=${searchtype}&keyword=${keyword}&sortby=${sortby}&order=${order}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt totalPages}">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/recipes?page=${currentPage + 1}&searchtype=${searchtype}&keyword=${keyword}&sortby=${sortby}&order=${order}">Sau</a>
                </li>
            </c:if>
        </ul>
    </nav>

    <p class="text-center">
        Hiển thị ${(currentPage - 1) * recordsPerPage + 1} đến ${currentPage * recordsPerPage > totalRecords ? totalRecords : currentPage * recordsPerPage} trong tổng số ${totalRecords} công thức
    </p>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>