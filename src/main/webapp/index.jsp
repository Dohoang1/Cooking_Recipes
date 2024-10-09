<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Công thức nấu ăn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap" rel="stylesheet">
    <style>
        body {
            background-image: url('<%= request.getContextPath() %>/crbg1.jpg');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            min-height: 100vh;
        }
        .pacifico-font {
            font-family: 'Pacifico', cursive;
        }
        .beige-text {
            color: #F5F5DC;
        }
    </style>
</head>
<body class="d-flex align-items-center">
<div class="container text-center">
    <h1 class="pacifico-font display-3 beige-text mb-5">Danh sách công thức nấu ăn</h1>
    <a href="recipes" class="btn btn-outline-warning btn-lg pacifico-font position-relative px-5 py-3 beige-text">
        <span class="position-absolute start-0 top-50 translate-middle-y ms-2">&gt;&gt;</span>
        Truy cập ngay
        <span class="position-absolute end-0 top-50 translate-middle-y me-2"><<</span>
    </a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>