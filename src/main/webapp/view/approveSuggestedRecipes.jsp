<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Duyệt Công Thức Đề Xuất</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Duyệt Công Thức Đề Xuất</h1>
    <div class="table-responsive">
        <table class="table table-striped">
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
                            <button type="submit" name="status" value="approved" class="btn btn-success btn-sm">Duyệt</button>
                            <button type="submit" name="status" value="rejected" class="btn btn-danger btn-sm">Từ Chối</button>
                        </form>
                        <a href="${pageContext.request.contextPath}/recipes?action=details&id=${recipe.id}" class="btn btn-sm btn-info">Chi Tiết</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="text-center mt-4">
        <a href="recipes" class="btn btn-primary">Quay lại danh sách</a>
    </div>
</div>
</body>
</html>