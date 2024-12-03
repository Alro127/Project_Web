<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Trang Quản Lý Tài Khoản Công Ty</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">
</head>
<body class="bg-light-grey">
	<jsp:include page="fragments/topNavAcc.jsp"></jsp:include>
	<div class="d-flex mt-5">
		<!-- Sidebar -->
		<jsp:include page="fragments/sidebar_CongTy.jsp" />

		<div class="container mt-4 bg-light py-3 px-3 shadow rounded ">
			<h2 class="text-center">Quản Lý Tài Khoản</h2>
			<hr>
			<form action="updateCompany" method="POST">
				<div class="container mt-3">
					<div class="container">
						<!-- Hình ảnh -->
						<div class="row">
							<h4>Thông Tin Tài Khoản</h4>
							<div class="col-md-6">
								<div class="form-group">
									<img
										src="https://ibrand.vn/wp-content/uploads/2024/07/mbbank-logo-5.png"
										class="card-img-top img-fluid m-3" alt="Logo"
										style="width: 100px; height: 100px; object-fit: cover; border-radius: 50%;">
									<input type="file" class="form-control-file" id="logo"
										name="logo">
								</div>
							</div>
						</div>
						<hr>
						<div class="row">
							<h4>Thông Tin Chung</h4>
							<div class="col-md-6">
								<div class="form-group">
									<label for="tenCongTy">Tên Công Ty</label> <input type="text"
										class="form-control" id="tenCongTy" name="tenCongTy"
										value="${congTy.tenCongTy}" required>
								</div>
								<div class="form-group">
									<label for="sdt">Số Điện Thoại</label> <input type="text"
										class="form-control" id="sdt" name="sdt"
										value="${congTy.sdt}" required>
								</div>
								<div class="form-group">
									<label for="email">Email</label> <input type="email"
										class="form-control" id="email" name="email"
										value="${congTy.email}" required>
								</div>
								<div class="form-group">
									<label for="maSoThue">Mã Số Thuế</label> <input type="text"
										class="form-control" id="maSoThue" name="maSoThue"
										value="${congTy.maSoThue}" required>
								</div>
								<div class="form-group">
									<label for="linhVuc">Lĩnh Vực</label> <input type="text"
										class="form-control" id="linhVuc" name="linhVuc"
										value="${congTy.linhVuc}" required>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="quyMoNhanSu">Quy Mô Nhân Sự</label> <input
										type="text" class="form-control" id="quyMoNhanSu"
										name="quyMoNhanSu" value="${congTy.quyMoNhanSu}" required>
								</div>
								<div class="form-group">
									<label for="tinhThanh">Tỉnh Thành</label> <input type="text"
										class="form-control" id="tinhThanh" name="tinhThanh"
										value="${congTy.tinhThanh}" required>
								</div>
								<div class="form-group">
									<label for="diaChi">Địa Chỉ</label> <input type="text"
										class="form-control" id="diaChi" name="diaChi"
										value="${congTy.diaChi}" required>
								</div>
								<div class="form-group">
									<label for="url">URL Trang Web</label> <input type="url"
										class="form-control" id="url" name="url"
										value="${congTy.url}" required>
								</div>
								<div class="form-group">
									<label for="gioiThieu">Giới Thiệu</label>
									<textarea class="form-control" id="gioiThieu" name="gioiThieu"
										rows="4" required>${congTy.gioiThieu}</textarea>
								</div>
							</div>
						</div>
						<hr>
						<div class="row">
							<h4>Hình ảnh hoạt động</h4>
							<div class="d-flex flex-wrap" id="image-container">

								<!-- Hình ảnh sẽ được bỏ vào đây -->
							</div>
							<div class="modal fade" id="addImageModal" tabindex="-1"
								aria-labelledby="addImageModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="addImageModalLabel">Thêm
												Hình Ảnh</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											<div class="mb-3">
												<label for="imageUpload" class="form-label">Chọn
													hình ảnh:</label> <input type="file" class="form-control"
													id="imageUpload">
											</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Đóng</button>
											<button type="button" class="btn btn-primary"
												data-bs-dismiss="modal" id="saveImage">Lưu</button>
										</div>
									</div>
								</div>
							</div>

							<!-- Nút thêm hình -->
							<button class="btn btn-primary col-2" data-bs-toggle="modal"
								data-bs-target="#addImageModal">Thêm hình ảnh</button>
							<!-- Nút lưu và hủy -->
							<div class="form-group text-end">
								<button type="submit" class="btn btn-primary">Lưu Thay
									Đổi</button>
								<a href="dashboard.jsp" class="btn btn-secondary">Hủy</a>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>

	</div>

	<!-- Bootstrap JS and dependencies -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="js/ThemHinhAnh.js"></script>
</body>
</html>
