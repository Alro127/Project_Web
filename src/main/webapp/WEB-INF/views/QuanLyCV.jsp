<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.List"%>
<%@ page import="beans.CV"%>
<%
    String nonce = (String) request.getAttribute("cspNonce");
%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Quản lý CV</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	integrity="sha384-Bk5cbLkZQ5raZ0+H2/+VbfYx3WpvxvQK4zqXZr7sYODuaX7bKXoSOnipQxkaS8sv"
	crossorigin="anonymous">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
	integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
	integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha384-vtXRMe3mGCbOeY7l30aIg8H9p3GdeSe4IFlP6G8JMa7o7lXvnz3GFKzPxzJdPfGK"
	crossorigin="anonymous"></script>
<link href="assets/css/style.css" rel="stylesheet">
<style nonce="<%= nonce %>">
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

		<!-- Button to Create New CV -->
		<a href="CreateCVServlet" class="btn btn-primary mb-3"> <i
			class="bi bi-file-earmark-plus"></i> Tạo CV mới
		</a>

		<!-- CV List -->
		<div class="row">
			<!-- Sử dụng JSTL để lặp qua danh sách cvList -->
			<c:forEach var="cv" items="${cvList}">
				<div class="col-md-4">
					<div class="card">
						<div class="card-body">
							<!-- Hiển thị thông tin CV -->
							<h5 class="card-title">CV - ${cv.ungvien.fullName}</h5>
							<p class="card-text">${cv.position}</p>
							<button type="button" class="btn btn-primary btn-sm btn-eye"
								data-bs-toggle="modal" data-bs-target="#cvModal"
								data-id="${cv.idCV}">Xem CV</button>

							<a href="LoadCVServlet?id=${cv.idCV}&mode=edit"
								class="btn btn-warning btn-sm btn-manage"> <i
								class="bi bi-pencil"></i> Chỉnh sửa
							</a> <a href="DeleteCVServlet?id=${cv.idCV}"
								class="btn btn-danger btn-sm btn-manage"> <i
								class="bi bi-trash"></i> Xóa
							</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<!-- Bootstrap JS and dependencies -->
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="js/CV.js"></script>

	<jsp:include page="modals/ViewCVModal.jsp"></jsp:include>
	<script nonce="<%= nonce %>">
	document.addEventListener("DOMContentLoaded", function () {
		const buttons = document.querySelectorAll('.btn-eye');
		buttons.forEach(function (btn) {
			btn.addEventListener('click', function () {
				const id = btn.getAttribute('data-id');
				loadCVContent(id);
			});
		});
	});
</script>
</body>
</html>
