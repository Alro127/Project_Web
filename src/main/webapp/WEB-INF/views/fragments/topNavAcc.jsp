<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<h1 class="m-0 text-light">CVHub</h1>
			</a>

			<h2 class="m-0 text-coral ms-3">Cơ hội việc làm</h2>
		</div>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ms-auto">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle text-light" href="#"
					data-bs-toggle="dropdown">Tùy chọn</a>
					<ul class="dropdown-menu">
						<li><a class="dropdown-item" href="#">Action</a></li>
						<li><a class="dropdown-item" href="#">Another action</a></li>
					</ul></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle text-light" href="#"
					data-bs-toggle="dropdown"> <i class="fa-solid fa-bell"></i>
				</a>
					<ul class="dropdown-menu dropdown-menu-end">
						<li><a class="dropdown-item" href="#">Thông báo 1</a></li>
					</ul></li>
				<li class="nav-item dropdown "><a
					class="nav-link dropdown-toggle text-light" href="#"
					data-bs-toggle="dropdown"> <i class="fa-regular fa-user"> </i> ${sessionScope.name} 
				</a>
					<ul class="dropdown-menu dropdown-menu-end">
						<li><a class="dropdown-item" href="DieuHuongServlet">Quản lý</a></li>
						<li><a class="dropdown-item" href="LogoutServlet">Đăng xuất</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>