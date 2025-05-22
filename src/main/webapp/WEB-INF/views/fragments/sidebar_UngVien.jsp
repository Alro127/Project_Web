<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String nonce = (String) request.getAttribute("cspNonce");
%>
<!DOCTYPE html>

<!-- STYLE CSP-SAFE -->
<style nonce="<%= nonce %>">
.sidebar {
	height: 100vh;
	width: 250px;
	position: fixed;
	top: 0;
	left: -250px;
	background-color: #343a40;
	transition: all 0.3s ease-in-out;
	z-index: 1050;
}
.sidebar.active {
	left: 0;
}
.sidebar .nav-link {
	color: #343a40;
	display: flex;
	align-items: center;
	gap: 20px;
	font-size: 16px;
	font-weight: bold;
	text-decoration: none;
	padding: 10px 20px;
}
.sidebar .nav-link:hover {
	background-color: #495057;
}
.btn-toggle {
	position: absolute;
	top: 70px;
	right: -40px;
	background-color: #343a40;
	color: #fff;
	border: none;
	border-radius: 50%;
	width: 35px;
	height: 35px;
	display: flex;
	align-items: center;
	justify-content: center;
	z-index: 1100;
	cursor: pointer;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
}
.btn-toggle:hover {
	background-color: #495057;
}
</style>

<!-- SIDEBAR HTML -->
<div id="sidebar" class="sidebar d-flex flex-column bg-white shadow">
	<button id="btnToggle" class="btn-toggle">☰</button>

	<a href="CongViecDaUngTuyenServlet" class="nav-link hover-coral">
		<i class="fs-4 fw-bold bi bi-file-earmark-post"></i> 
		<span>Công việc đã ứng tuyển</span>
	</a>

	<a href="CongViecYeuThichServlet" class="nav-link hover-coral">
    	<i class="fs-4 fw-bold bi bi-heart-fill"></i>
    	<span>Công việc đã thích</span>
	</a>

	<a href="CongViecServlet" class="nav-link hover-coral">
		<i class="fs-4 fw-bold bi bi-search"></i> 
		<span>Tìm việc</span>
	</a>

	<a href="QuanLyCVServlet" class="nav-link hover-coral"> 
		<i class="fs-4 fw-bold bi bi-briefcase"></i> 
		<span>Quản lý CV</span>
	</a>

	<a href="GoogleCalendarEventsServlet" class="nav-link hover-coral"> 
		<i class="fs-4 fw-bold bi bi-calendar-check"></i> 
		<span>Lịch hẹn phỏng vấn</span>
	</a>

	<a href="QuanLyTaiKhoanServlet" class="nav-link hover-coral"> 
		<i class="fs-4 fw-bold bi bi-person-gear"></i> 
		<span>Quản lý tài khoản</span>
	</a>
</div>

<!-- SCRIPT CSP-SAFE -->
<script nonce="<%= nonce %>">
document.getElementById('btnToggle').addEventListener('click', function () {
    document.getElementById('sidebar').classList.toggle('active');
});
</script>
