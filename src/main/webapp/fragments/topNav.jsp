<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
.navbar {
	position: fixed; /* Cố định nav */
	top: 0; /* Đặt ở đầu */
	left: 0;
	width: 100%; /* Chiếm toàn bộ chiều ngang */
	z-index: 1030; /* Đảm bảo nav nằm trên các thành phần khác */
}
</style>
<nav class="navbar navbar-expand-lg navbar-light bg-dark-blue">
	<div class="container">
		<div class="d-flex align-items-center">
			<a href="CongViecServlet" class="text-decoration-none">
				<h1 class="m-0 text-light">Logo</h1>
			</a>

			<h2 class="m-0 text-coral ms-3">Cơ hội việc làm</h2>
		</div>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div>
			<a href="Login.jsp?role=UngVien" class="btn bg-coral text-light me-2 hover-coral">Ứng
				viên</a> 
				<a href="Login.jsp?role=CongTy"
				class="btn btn-outline-coral bg-dark-blue text-light hover-coral">Nhà
				tuyển dụng</a>
		</div>
	</div>
</nav>