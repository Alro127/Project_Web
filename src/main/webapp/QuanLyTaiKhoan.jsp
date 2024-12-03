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
<title>Quản lý tài khoản ứng viên</title>
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
	<!-- Sidebar -->
	<div class="d-flex mt-5">
		<jsp:include page="fragments/sidebar_UngVien.jsp" />
		<div class="container mt-5">
			<h2>Quản lý thông tin cá nhân</h2>

			<!-- Phần trên: Thông tin tài khoản -->
			<div class="card mb-4">
				<div class="card-header">Thông tin tài khoản</div>
				<div class="card-body">
					<form>
						<%
						TaiKhoan taiKhoan = (TaiKhoan) request.getAttribute("taiKhoan");
						%>
						<%
						taiKhoan = new TaiKhoan(0, "tandatAZ123", "nhat","google", "fb" ,"email","UngVien");
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
			<!-- Phần dưới: Thông tin cá nhân -->
			<div class="card">
				<div class="card-header">Thông tin cá nhân</div>
				<div class="card-body">
					<form>
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
					        	<!-- Họ tên -->
						        <div>
						            <label for="fullname" class="form-label">Họ tên</label>
						            <input type="text" id="fullname" name="fullname" class="form-control" required />
						        </div>
						        <!-- Giới tính -->
						        <div class="col-md-3">
						            <label for="gender" class="form-label">Giới tính:</label>
						            <select id="gender" name="gender" class="form-control" required>
						                <option value="">Chọn giới tính</option>
						                <option value="male">Nam</option>
						                <option value="female">Nữ</option>
						                <option value="other">Khác</option>
						            </select>
						        </div>
						        <!-- Ngày sinh -->
						        <div class="col-md-3">
						            <label for="dob" class="form-label">Ngày sinh:</label>
						            <input type="date" id="dob" name="dob" class="form-control" required />
						        </div>
					        </div>
					    </div>
					
					    <div class="mb-3 row">
					        <!-- Số điện thoại -->
					        <div class="col-md-6">
					            <label for="phone" class="form-label">Số điện thoại:</label>
					            <input type="tel" id="phone" name="phone" class="form-control" placeholder="Nhập số điện thoại" required />
					        </div>
					        <!-- Email -->
					        <div class="col-md-6">
					            <label for="email" class="form-label">Email:</label>
					            <input type="email" id="email" name="email" class="form-control" placeholder="Nhập email" readonly />
					        </div>
					    </div>
					
					    <div class="mb-3 row">
					        <!-- Tỉnh thành -->
					        <div class="col-md-6">
					            <label for="location" class="form-label">Tỉnh thành:</label>
					            <input type="text" id="location" name="location" class="form-control" placeholder="Nhập tỉnh thành" required />
					        </div>
					        <!-- Địa chỉ -->
					        <div class="col-md-6">
					            <label for="address" class="form-label">Địa chỉ:</label>
					            <input type="text" id="address" name="address" class="form-control" placeholder="Nhập địa chỉ" required />
					        </div>
					    </div>
					
					    <!-- Giới thiệu -->
					    <div class="mb-3">
					        <label for="introduction" class="form-label">Giới thiệu:</label>
					        <textarea id="introduction" name="introduction" class="form-control" placeholder="Giới thiệu bản thân" rows="4" required></textarea>
					    </div>
					
					    <!-- Nút lưu -->
					    <button type="submit" class="btn btn-primary">Lưu thông tin</button>
					</form>
				</div>
			</div>
		</div>
	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	<script src="js/QuanLyTaiKhoan.js"></script>
</body>
</html>
