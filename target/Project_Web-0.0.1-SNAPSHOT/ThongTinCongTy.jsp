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

.company-info-container {
	background-color: white;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
	width: 90%;
	max-width: 800px; /* Chiều rộng tối đa của container */
}

h2 {
	margin-bottom: 20px;
	color: var(--first-color);
	text-align: center; /* Căn giữa tiêu đề */
}

.form-row {
	display: flex;
	flex-wrap: wrap; /* Cho phép các cột xếp chồng khi không đủ chỗ */
	margin-bottom: 15px; /* Khoảng cách giữa các hàng */
}

.form-group {
	flex: 1;
	margin: 10px 40px 0px 20px; /* Tăng khoảng cách giữa các ô nhập */
	min-width: 200px; /* Đảm bảo kích thước tối thiểu cho các ô nhập */
}

.form-group label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
	color: var(--first-color);
}

.form-group input, .form-group select, .form-group textarea {
	width: 100%;
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
	margin: 10px;
}

.form-group button:hover {
	background-color: #e56a4e; /* Màu tối hơn của nút khi hover */
}
</style>
<title>Thông tin công ty</title>
</head>
<body>
	<div class="company-info-container">
		<h2>Thông Tin Công Ty</h2>
		<form action="your-company-info-endpoint" method="POST">
			<div class="form-row">
				<div class="form-group">
					<label for="companyName">Tên Công Ty:</label> <input type="text"
						id="companyName" name="companyName" placeholder="Nhập tên công ty"
						required />
				</div>
				<div class="form-group">
					<label for="phone">Số Điện Thoại:</label> <input type="tel"
						id="phone" name="phone" placeholder="Nhập số điện thoại" required />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group">
					<label for="email">Email:</label> <input type="email" id="email"
						name="email" placeholder="Nhập email" required />
				</div>
				<div class="form-group">
					<label for="taxId">Mã Số Thuế:</label> <input type="text"
						id="taxId" name="taxId" placeholder="Nhập mã số thuế" required />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group">
					<label for="industry">Lĩnh Vực:</label> <input type="text"
						id="industry" name="industry"
						placeholder="Nhập lĩnh vực hoạt động" required />
				</div>
				<div class="form-group">
					<label for="employeeScale">Quy Mô Nhân Sự:</label> <input
						type="text" id="employeeScale" name="employeeScale"
						placeholder="Nhập quy mô nhân sự" required />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group">
					<label for="location">Tỉnh Thành:</label> <input type="text"
						id="location" name="location" placeholder="Nhập tỉnh thành"
						required />
				</div>
				<div class="form-group">
					<label for="address">Địa Chỉ:</label> <input type="text"
						id="address" name="address" placeholder="Nhập địa chỉ" required />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group" style="flex-basis: 100%">
					<!-- Chiếm toàn bộ chiều rộng -->
					<label for="url">URL:</label> <input type="url" id="url" name="url"
						placeholder="Nhập URL công ty" />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group" style="flex-basis: 100%">
					<label for="introduction">Giới Thiệu:</label>
					<textarea id="introduction" name="introduction"
						placeholder="Giới thiệu về công ty" rows="4" required></textarea>
				</div>
			</div>
			<div class="form-group">
				<button type="submit">Lưu Thông Tin</button>
			</div>
		</form>
	</div>
</body>
</html>