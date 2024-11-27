<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	background: linear-gradient(to right, var(--first-color),
		var(--second-color)); /* Nền gradient từ xanh sang cam */
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.candidate-info-container {
	background-color: white;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
	width: 90%;
	max-width: 600px; /* Chiều rộng tối đa của container */
}

h2 {
	margin-bottom: 20px;
	color: var(--first-color);
	text-align: center; /* Căn giữa tiêu đề */
}

.form-group {
	margin: 10px; /* Giảm khoảng cách giữa các ô */
	display: flex;
	flex-direction: column;
}

.form-group label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
	color: var(--first-color);
}

.form-group input, .form-group select, .form-group textarea {
	padding: 10px;
	border: 1px solid #ced4da;
	border-radius: 5px;
	transition: border-color 0.3s;
}

.form-group input:focus, .form-group select:focus, .form-group textarea:focus
	{
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
</style>
<title>Thông tin ứng viên</title>
</head>
<body>
	<div class="candidate-info-container">
		<h2>Thông Tin Ứng Viên</h2>
		<form action="your-candidate-info-endpoint" method="POST">
			<div class="form-group">
				<label for="fullname">Họ tên:</label> <input type="text"
					id="fullname" name="fullname" placeholder="Nhập họ tên" required />
			</div>
			<div class="form-group">
				<label for="gender">Giới tính:</label> <select id="gender"
					name="gender" required>
					<option value="">Chọn giới tính</option>
					<option value="male">Nam</option>
					<option value="female">Nữ</option>
					<option value="other">Khác</option>
				</select>
			</div>
			<div class="form-group">
				<label for="dob">Ngày sinh:</label> <input type="date" id="dob"
					name="dob" required />
			</div>
			<div class="form-group">
				<label for="phone">Số điện thoại:</label> <input type="tel"
					id="phone" name="phone" placeholder="Nhập số điện thoại" required />
			</div>
			<div class="form-group">
				<label for="email">Email:</label> <input type="email" id="email"
					name="email" placeholder="Nhập email" required />
			</div>
			<div class="form-group">
				<label for="location">Tỉnh thành:</label> <input type="text"
					id="location" name="location" placeholder="Nhập tỉnh thành"
					required />
			</div>
			<div class="form-group">
				<label for="address">Địa chỉ:</label> <input type="text"
					id="address" name="address" placeholder="Nhập địa chỉ" required />
			</div>
			<div class="form-group">
				<label for="introduction">Giới thiệu:</label>
				<textarea id="introduction" name="introduction"
					placeholder="Giới thiệu bản thân" rows="4" required></textarea>
			</div>
			<div class="form-group">
				<button type="submit">Đăng ký</button>
			</div>
		</form>
	</div>
</body>
</html>