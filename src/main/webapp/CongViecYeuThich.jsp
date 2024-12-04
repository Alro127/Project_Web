<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Công việc yêu thích</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
</head>
<body class="bg-light-grey">

	<jsp:include page="fragments/topNavAcc.jsp"></jsp:include>

	<div class="d-flex mt-5">
		<!-- Sidebar -->
		<jsp:include page="fragments/sidebar_UngVien.jsp" />
		<!-- Main content -->
		<div class="container mt-5">
			<div class="bg-white rounded p-3 shadow-sm mt-4 mb-4"
				style="background-color: rgba(255, 255, 255, 0.5);">
				<jsp:include page="fragments/frg_TimKiemVaLoc.jsp" />
			</div>
			
			<h4 class="col-12 mb-4">Công việc bạn đã thích</h4>
			<jsp:include page="fragments/frg_CongViec.jsp" />

		</div>
		<script src="js/PhanTrangCongViecYeuThich.js"></script>
	</div>

</body>
</html>
