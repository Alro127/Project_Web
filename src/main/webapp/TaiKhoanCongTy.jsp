<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="beans.TaiKhoan"%>
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
			<form action="updateCompany" method="POST">
				<div class="container mt-3">
					<div class="container">
					<!-- Phần trên: Thông tin tài khoản -->
					<div class="card mb-4">
						<div class="card-header">Thông tin tài khoản</div>
						<div class="card-body">
							<form>
								<%
								TaiKhoan taiKhoan = (TaiKhoan) request.getAttribute("taiKhoan");
								%>
								<%
								taiKhoan = new TaiKhoan(0, "nhat", "nhat","google", "fb" ,"email","UngVien");
								%>
			
								<div class="row mb-3">
									<!-- Username -->
									<div class="col-6">
										<label for="username" class="form-label">Username</label> <input
											type="text" class="form-control" id="username"
											value="<%=taiKhoan.getUsername()%>" readonly>
									</div>
									<!-- Password -->
									<div class="col-6">
										<label for="password" class="form-label">Password</label> <input
											type="password" class="form-control" id="password"
											value="<%=taiKhoan.getPassword()%>" readonly>
									</div>
								</div>
							</form>
							<!-- Nút Toggle để thêm phần Đổi Mật Khẩu -->
							<button id="toggleChangePassword" class="btn btn-secondary mb-4">Đổi
								mật khẩu</button>
			
							<!-- Phần giao diện Đổi Mật Khẩu sẽ được thêm vào tại đây -->
							<div id="changePasswordForm" class="mt-3" style="display: none;"></div>
			
						</div>
					</div>
						<hr>
						<div class="row">
							<h4>Thông Tin Chung</h4>
						    <div class="mb-3 row">
					        <!-- Avatar -->
					        <div class="col-md-6">
								<div class="position-relative d-flex justify-content-center align-items-center">
									<!-- Avatar -->
									<img id="avatarPreview"
										src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYDLLaxgOsud5O32KbTu-bnPjbkBNbYXePWQ&s"
										alt="Avatar" class="rounded-circle border"
										style="width: 200px; height: 200px; object-fit: cover;">

									<!-- Hidden file input -->
									<input type="file" id="avatarUpload" class="d-none"
										accept="image/*" onchange="previewAvatar(event)">

									<!-- Button container -->
									<div class="position-absolute d-flex align-items-center"
										style="bottom: 0; right: 0; transform: translate(50%, 50%);">
										<!-- Upload button -->
										<button type="button"
											class="btn btn-outline-primary btn-sm me-1"
											onclick="triggerAvatarUpload()">
											<i class="bi bi-upload"></i>
										</button>
									</div>
								</div>
					        </div>
					        <div class="col-md-6">
						        <div class="row">
       					        	<!-- Họ tên -->
							        <div>
										<label for="tenCongTy">Tên Công Ty</label> <input type="text"
											class="form-control" id="tenCongTy" name="tenCongTy"
											value="${congTy.tenCongTy}">
							        </div>
							        <!-- Giới tính -->
							        <div class="col-md-6">
							            <label for="gender" class="form-label">Giới tính:</label>
							            <select id="gender" name="gender" class="form-control" required>
							                <option value="">Chọn giới tính</option>
							                <option value="male">Nam</option>
							                <option value="female">Nữ</option>
							                <option value="other">Khác</option>
							            </select>
							        </div>
							        <!-- Ngày sinh -->
							        <div class="col-md-6">
							            <label for="dob" class="form-label">Ngày sinh:</label>
							            <input type="date" id="dob" name="dob" class="form-control" required />
							        </div>
						        </div>
					        </div>
					    </div>
					
					    <div class="mb-3 row">
					        <!-- Số điện thoại -->
					        <div class="col-md-6">
					            <label for="phone" class="form-label">Số điện thoại:</label>
					            <input type="tel" id="phone" name="phone" class="form-control" placeholder="Nhập số điện thoại" value="${congTy.sdt}"  required />
					        </div>
					        <!-- Email -->
					        <div class="col-md-6">
					            <label for="email" class="form-label">Email:</label>
					            <input type="email" id="email" name="email" class="form-control" placeholder="Nhập email" value="${congTy.email}" readonly />
					        </div>
					    </div>
					
					    <div class="mb-3 row">
					        <!-- Tỉnh thành -->
					        <div class="col-md-6">
					            <label for="location" class="form-label">Tỉnh thành:</label>
					            <input type="text" id="location" name="location" class="form-control" placeholder="Nhập tỉnh thành" value="${congTy.tinhThanh}" required />
					        </div>
					        <!-- Địa chỉ -->
					        <div class="col-md-6">
					            <label for="address" class="form-label">Địa chỉ:</label>
					            <input type="text" id="address" name="address" class="form-control" placeholder="Nhập địa chỉ" value="${congTy.diaChi}" required />
					        </div>
					        							<div class="col-md-6">
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
									<label for="url">URL Trang Web</label> <input type="url"
										class="form-control" id="url" name="url"
										value="${congTy.url}" required>
								</div>
							</div>
					    </div>
					
					    <!-- Giới thiệu -->
					    <div class="mb-3">
					        <label for="introduction" class="form-label">Giới thiệu:</label>
					        <textarea id="introduction" name="introduction" class="form-control" placeholder="Giới thiệu bản thân" rows="4" required>${congTy.gioiThieu}</textarea>
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
	<script src="js/QuanLyTaiKhoan.js"></script>
</body>
</html>
