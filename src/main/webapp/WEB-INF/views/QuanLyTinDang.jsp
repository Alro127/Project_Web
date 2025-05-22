<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách công việc đã đăng</title>
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
<style>
/* Chỉ chừa border dưới cho bảng */
table {
	border-collapse: collapse;
}

th, td {
	border: none;
	border-bottom: 1px solid #dee2e6; /* Màu viền của Bootstrap */
}

thead th {
	border-bottom: 2px solid #dee2e6;
	/* Đường viền dưới dày hơn cho tiêu đề */
}
</style>

<script src="js/PhanTrangQuanLyTin.js"></script>
<meta name="csrf-token" content="${csrfToken}">

</head>
<body class="bg-light-grey">

	<jsp:include page="fragments/topNavAcc.jsp"></jsp:include>

	<div class="d-flex mt-5">
		<!-- Sidebar -->
		<jsp:include page="fragments/sidebar_CongTy.jsp" />
		<!-- Main content -->
		<div class="container mt-4 bg-light py-3 px-3 shadow rounded ">
			<h2 class="text-center mb-4">Các Tin Đã Đăng</h2>
			<!-- Bộ lọc -->
			<div class="row mb-3">
				<div class="col-12 col-md-2">
					<select id="linhVucFilter" class="form-select text-muted"
						aria-label="Lĩnh vực">
						<option value="">Tất cả lĩnh vực</option>
						<c:forEach var="linhVuc" items="${linhVucs}">
							<option value="${linhVuc}">${linhVuc}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-12 col-md-2">
					<select id="thoiGianFilter" class="form-select text-muted"
						aria-label="Thời gian">
						<option value="">Sắp xếp theo thời gian</option>
						<option value="1">Mới nhất</option>
						<option value="2">Cũ nhất</option>
					</select>
				</div>

				<div class="col-12 col-md-2">
					<select id="luotXemFilter" class="form-select text-muted"
						aria-label="Lượt xem">
						<option value="">Sắp xếp theo lượt xem</option>
						<option value="1">Cao nhất</option>
						<option value="2">Thấp nhất</option>
					</select>
				</div>

				<div class="col-12 col-md-2">
					<select id="luotNopFilter" class="form-select text-muted"
						aria-label="Lượt nộp">
						<option value="">Sắp xếp theo lượt nộp</option>
						<option value="1">Cao nhất</option>
						<option value="2">Thấp nhất</option>
					</select>
				</div>
				<div class="col-md-4 mb-3">
					<input id="search-input" type="text" class="form-control"
						placeholder="Tìm kiếm..." />
				</div>
			</div>

			<!-- Danh sách tin  -->
			<table class="table table-bordered table-hover ">
				<thead class="table bg-dark-blue text-light">
					<tr>
						<th>Tên công việc</th>
						<th>Lĩnh vực</th>
						<th>Thời gian đăng</th>
						<th>Hết hạn</th>
						<th>Lượt nộp</th>
						<th>Lượt xem</th>
						<th>Chi tiết</th>
						<th></th>
					</tr>
				</thead>
				<tbody id="job-list">
					<!-- Công việc load tại đây -->
				</tbody>
			</table>
			<div class="row">
				<div class="col-12 text-center" id="pagination">
					<!-- Các nút phân trang sẽ được tải ở đây -->
				</div>
			</div>
		</div>

	</div>

	<!-- Link Bootstrap JavaScript -->
	<jsp:include page="modals/ChiTietCongViecModal.jsp" />
</body>
</html>