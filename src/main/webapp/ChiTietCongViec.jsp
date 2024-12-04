<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết công việc</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light-grey">
	<!-- Navigation -->
	<c:choose>
		<c:when test="${not empty sessionScope.id}">
			<!-- Đã đăng nhập -->
			<jsp:include page="fragments/topNavAcc.jsp"></jsp:include>
		</c:when>
		<c:otherwise>
			<!-- Chưa đăng nhập -->
			<jsp:include page="fragments/topNav.jsp"></jsp:include>
		</c:otherwise>
	</c:choose>

	<!-- Main Content -->
	<div class="container mt-5">
		<!--  Tìm kiếm -->
		<div class="bg-white rounded p-3 shadow-sm mt-4 mb-4"
			style="background-color: rgba(255, 255, 255, 0.5);">
			<jsp:include page="fragments/frg_TimKiemVaLoc.jsp" />
		</div>

		<!-- Content -->
		<div class="container">
			<div class="row">
				<!-- Chi tiết công việc -->
				<div class="bg-white rounded p-3 shadow-sm mb-4 col-md-8"
					style="background-color: rgba(255, 255, 255, 0.5);">
					<div class="container">
						<div class="row">
							<div class="card-header bg-white text-dark">
								<h2>
									<span class="fw-bold">${congViec.ten}</span>
								</h2>
								<div class="d-flex justify-content-between text-center mt-3">
									<!-- Địa điểm -->
									<div class="d-flex flex-column align-items-center col-3">
										<div class="d-flex align-items-start">
											<i class="bi bi-geo-alt-fill text-coral fs-2"></i>
											<div class="ms-3 text-start align-items-center ">
												<p class="mb-0 text-muted">Địa điểm</p>
												<p>
													<strong>${congViec.diaDiem}</strong>
												</p>
											</div>
										</div>
									</div>
									<!-- Lương -->
									<div class="d-flex flex-column align-items-center col-3">
										<div class="d-flex align-items-start">
											<i class="bi bi-currency-dollar text-coral fs-2"></i>
											<div class="ms-3 text-start align-items-center">
												<p class="mb-0 text-muted">Lương</p>
												<p>
													<strong>${congViec.luong} VNĐ</strong>
												</p>
											</div>
										</div>
									</div>
									<!-- Năm kinh nghiệm -->
									<div class="d-flex flex-column align-items-center col-3">
										<div class="d-flex align-items-start">
											<i class="bi bi-briefcase-fill text-coral fs-2"></i>
											<div class="ms-3 text-start align-items-center">
												<p class="mb-0 text-muted">Năm kinh nghiệm</p>
												<p>
													<strong>${congViec.namKinhNghiem} năm</strong>
												</p>
											</div>
										</div>
									</div>
									<!-- Lĩnh vực -->
									<div class="d-flex flex-column align-items-center col-3">
										<div class="d-flex align-items-start">
											<i class="bi bi-clipboard-fill text-coral fs-2"></i>
											<div class="ms-3 text-start align-items-center">
												<p class="mb-0 text-muted">Lĩnh vực</p>
												<p>
													<strong>${congViec.linhVuc}</strong>
												</p>
											</div>
										</div>
									</div>
								</div>
								<!-- Hạn nộp hồ sơ -->
								<div
									class="align-items-start d-flex mt-3 bg-light-grey col-xs-12 col-md-5 p-2">
									<i class="bi bi-clock-fill text-coral fs-6"></i>
									<p class="mb-0 text-muted mx-3">Hạn nộp hồ sơ:</p>
									<p class="mb-0 ">${congViec.thoiGianHetHan}</p>
								</div>
								<!-- Ứng tuyển và thêm vào yêu thích  -->
								<div class="container mt-3 px-0">
									<div class="row">

										<div class="col-md-9 mb-3">
											<form action="">
												<button type="submit"
													class="btn bg-dark-blue text-light form-control">Ứng
													tuyển</button>
											</form>
										</div>

										<div class="col-md-3 mb-3">
											<form action="CongViecYeuThichServlet" method="POST">
												<input type="hidden" name="idCongViec" value="${congViec.idCongViec}" />
												<button type="submit"
													class="btn btn-outline-coral form-control">
													<c:choose>
														<c:when test="${trangThai}">
										                    Đã thích
										                </c:when>
														<c:otherwise>
										                    Lưu tin
										                </c:otherwise>
													</c:choose>
												</button>
											</form>
										</div>

									</div>

								</div>
							</div>

							<div class="card-body">
								<hr class="m-0">
								<h5 class="mt-4">Mô tả công việc:</h5>
								<p>${congViec.moTa}</p>
								<h5 class="mt-4">Yêu cầu:</h5>
								<p>${congViec.yeuCau}</p>
								<h5 class="mt-4">Quyền lợi:</h5>
								<p>${congViec.quyenLoi}</p>
								<hr>
							</div>
						</div>
					</div>
				</div>

				<!-- Khác-->
				<div class="ps-4 md-4 pe-0 mb-4 col-md-4">
					<!-- Công ty -->
					<div class="bg-white rounded shadow-sm p-3 mb-4"
						style="background-color: rgba(255, 255, 255, 0.5);">
						<h5>
							<strong>${congViec.tenCongTy}</strong>
						</h5>
						<div class="d-flex">
							<img
								src="https://ibrand.vn/wp-content/uploads/2024/07/mbbank-logo-5.png"
								class="card-img-top img-fluid" alt="Công việc"
								style="width: 100px; height: 100px; object-fit: cover;">
							<div class="card-body ms-3">
								<p class="card-text">
									<strong>Quy mô:</strong> ${congViec.idCT} <br> <strong>Lĩnh
										vực:</strong> ${congViec.diaDiem} VND <br> <strong>Địa
										điểm:</strong> ${congViec.diaDiem}
								</p>
							</div>
						</div>
						<a href="CongTyServlet?id=${congViec.idCT}"
							class="btn bg-coral text-light form-control mt-3">Xem trang
							công ty</a>
					</div>

					<!-- Công Việc liên quan -->
					<div class="bg-white rounded shadow-sm p-3"
						style="background-color: rgba(255, 255, 255, 0.5);">
						<h5>
							<strong>Công việc liên quan</strong>
						</h5>
						<jsp:include page="fragments/frg_CongViecLienQuan.jsp" />
						<a href="CongViecLienQuanServlet?id=${congViec.idCongViec}" >Xem thêm</a>
					</div>

				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<jsp:include page="fragments/footer.jsp" />
</body>
</html>