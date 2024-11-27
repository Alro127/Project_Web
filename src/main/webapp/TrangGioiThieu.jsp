<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Giới Thiệu</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link href="assets/css/color.css" rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
</head>
<body>
	<!-- Header Navigation -->
	<jsp:include page="fragments/topNav.jsp" />
	
	<!-- Section tìm kiếm -->
	<div class="bg-white rounded p-3 shadow-sm mt-4 mb-4 mx-5"
		style="background-color: rgba(255, 255, 255, 0.5);">
		<jsp:include page="fragments/frg_TimKiemVaLoc.jsp" />
		<jsp:include page="fragments/frg_DeXuat.jsp" />
	</div>
	
	<!-- Section các tin tuyển dụng -->
	<jsp:include page="fragments/frg_CongViec.jsp"/>
	
	<!-- Footer -->
	<jsp:include page="fragments/footer.jsp"/>
</body>
</html>