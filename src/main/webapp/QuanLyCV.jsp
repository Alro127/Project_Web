<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="beans.CV" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý CV</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
    <style>
        .card {
            margin-bottom: 20px;
        }
        .card-title {
            font-size: 1.2rem;
            font-weight: bold;
        }
        .card-body {
            font-size: 1rem;
        }
        .btn-manage {
            margin-right: 10px;
        }
    </style>
</head>
<body>
	<jsp:include page="fragments/topNavAcc.jsp"></jsp:include>
    <div class="container mt-4">
    	<jsp:include page="fragments/sidebar_UngVien.jsp" />
        <h2 class="mb-4">Quản lý CV</h2>
		<%List<CV> cvList = (List<CV>) request.getAttribute("cvList"); %>
        <!-- Button to Create New CV -->
        <a href="CreateCVServlet?id=<%=%>" class="btn btn-primary mb-3"><i class="bi bi-file-earmark-plus"></i> Tạo CV mới</a>
		<!-- Chưa làm Create -->
        <!-- CV List -->
        <div class="row">

			<% for (int i = 0; i < cvList.size(); i++) { 
			%>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">CV - <%=cvList.get(i).getUngvien().getFullName() %></h5>
                        <p class="card-text"><%=cvList.get(i).getPosition()%></p>
                        <a href="LoadCVServlet?id=<%=cvList.get(i).getIdCV()%>&mode=view" class="btn btn-info btn-sm btn-manage"><i class="bi bi-eye"></i> Xem</a>
                        <a href="LoadCVServlet?id=<%=cvList.get(i).getIdCV()%>&mode=edit" class="btn btn-warning btn-sm btn-manage"><i class="bi bi-pencil"></i> Chỉnh sửa</a>
                        <a href="DeleteCVServlet?id=<%=cvList.get(i).getIdCV()%>" class="btn btn-danger btn-sm btn-manage"><i class="bi bi-trash"></i> Xóa</a>
                    </div>
                </div>
            </div>
			<%} %>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
