<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
:root {
	--first-color: #3d405b; /* Màu xanh đậm */
	--second-color: #ff8160; /* Màu cam */
	--third-color: #f4f1de; /* Màu sáng */
	--background-color: #f0f0f0; /* Màu nền xám trắng */
}

body {
	font-family: "Arial", sans-serif;
	background-color: var(--background-color);
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
	background-image: linear-gradient(to bottom right, rgba(61, 64, 91, 0.8),
		var(--second-color));
	color: var(--third-color);
}

.registration-container {
	background-color: white;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
	width: 90%;
	max-width: 400px; /* Chiều rộng tối đa của container */
}

h2 {
	margin-bottom: 20px;
	color: var(--first-color);
	text-align: center; /* Căn giữa tiêu đề */
}

.form-group {
	margin-bottom: 10px; /* Giảm khoảng cách giữa các ô */
	display: flex;
	flex-direction: column;
	position: relative; /* Để tooltip căn chỉnh theo form-group */
}

.form-group label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
	color: var(--first-color);
}

.form-group input {
	padding: 10px;
	border: 1px solid #ced4da;
	border-radius: 5px;
	transition: border-color 0.3s;
}

.form-group input:focus {
	border-color: var(--first-color);
	outline: none;
}

.form-group button {
	width: 100%;
	padding: 10px;
	background-color: var(--second-color);
	color: white;
	border: none;
	border-radius: 5px;
	font-weight: bold;
	cursor: pointer;
	transition: background-color 0.3s;
}

.form-group button:hover {
	background-color: #e56a4e; /* Màu tối hơn của nút khi hover */
}

.form-footer {
	margin-top: 15px;
	font-size: 0.9em;
	color: var(--first-color);
	text-align: center; /* Căn giữa footer */
}

.form-footer a {
	color: var(--second-color);
	text-decoration: none;
}

.form-footer a:hover {
	text-decoration: underline;
}

.message {
	color: red;
	margin-bottom: 15px;
	text-align: center;
}
</style>
<style>
label {
	font-weight: bold;
	display: block;
	margin-bottom: 5px;
}

select {
	width: 100%;
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 16px;
	color: #555;
	background-color: #f9f9f9;
	transition: all 0.3s ease;
}

select:focus {
	border-color: #007BFF;
	box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
	outline: none;
}

.tooltip {
	display: none; /* Ẩn mặc định */
	position: absolute;
	top: 64px;
	left: 8px;
	background-color: #f8f9fa;
	color: #333;
	padding: 8px;
	border: 1px solid #ddd;
	border-radius: 4px;
	font-size: 12px;
	width: 250px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
	z-index: 10;
}

.form-group input:focus+.tooltip, .form-group input:hover+.tooltip {
	display: block; /* Hiển thị khi hover hoặc focus */
}
</style>
<title>Đăng ký</title>
</head>
<body onload="checkMessage()">
	<div class="registration-container">
		<h2>Đăng Ký</h2>
		<c:if test="${not empty message}">
			<div class="message">${message}</div>
		</c:if>
		<c:remove var="message" />

		<form action="SignupServlet" method="POST">
			<input type="hidden" name="csrfToken" value="<c:out value='${csrfToken}'/>">
			<div class="form-group">
				<label for="username">Tên đăng nhập:</label> <input type="text"
					id="username" name="username" placeholder="Nhập tên đăng nhập"
					required />
				<div class="tooltip">Tên đăng nhập phải từ 3 đến 20 ký tự,
					không chứa ký tự đặc biệt.</div>
			</div>
			<div class="form-group">
				<label for="email">Email:</label> <input type="email" id="email"
					name="email" placeholder="Nhập email" required />
			</div>
			<div class="form-group">
				<label for="password">Mật khẩu:</label> <input type="password"
					id="password" name="password" placeholder="Nhập mật khẩu" required />
				<div class="tooltip">
					Mật khẩu phải chứa ít nhất:<br> - 8 ký tự<br> - 1 chữ
					hoa, 1 chữ thường<br> - 1 số, 1 ký tự đặc biệt.
				</div>
			</div>
			<div class="form-group">
				<label for="confirm-password">Nhập lại mật khẩu:</label> <input
					type="password" id="confirm-password" name="confirm-password"
					placeholder="Nhập lại mật khẩu" required />
			</div>
			<div class="form-group">
				<label for="role">Role:</label> <select id="role" name=role>
					<option value="UngVien">Ứng viên</option>
					<option value="CongTy">Công ty</option>
				</select>
			</div>
			<div class="form-group">
				<button type="submit" name="signup">Đăng Ký</button>
			</div>
		</form>
		<div class="form-footer">
			<p>
				Đã có tài khoản? <a href="Login.jsp">Đăng nhập</a>
			</p>
		</div>
	</div>

	<script type="text/javascript">
	function checkMessage() {
        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.get('error') === '1') {
            alert("Account alreadly existed or invalid username or password! Please try again!");
            removeErrorParam();
        }
	}     
	function removeErrorParam() {
        const url = new URL(window.location.href);
        url.searchParams.delete('error'); // Xóa tham số 'error'
        window.history.replaceState({}, document.title, url); // Cập nhật URL mà không tải lại trang
    }
	
	</script>
</body>
</html>