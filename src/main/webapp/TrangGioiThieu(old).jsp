<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
:root {
	--ground-color: #3d405b; /* Màu xanh đậm cho header và footer */
	--title-color: #ff8160; /* Màu cam cho tiêu đề */
	--light-color: #f4f1de; /* Màu sáng sẽ dùng sau */
	--background-color: #f0f0f0; /* Màu nền xám trắng */
	--search-background-color: rgba(255, 255, 255, 0.5);
	/* Nền trắng 50% trong suốt */
}

body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: var(--background-color); /* Màu nền xám trắng */
	color: black; /* Màu chữ đen */
}

header {
	background-color: var(--ground-color); /* Nền màu xanh đậm */
	color: white; /* Màu chữ trắng */
	padding: 10px 20px; /* Thêm padding cho header */
	display: flex; /* Sử dụng flexbox */
	justify-content: space-between; /* Căn lề trái và phải */
	align-items: center; /* Căn giữa theo chiều dọc */
}

.header-left {
	display: flex; /* Căn ngang cho logo và tiêu đề */
	align-items: center; /* Căn giữa theo chiều dọc */
}

.header-left h1 {
	margin-right: 20px; /* Khoảng cách giữa logo và tiêu đề */
}

nav {
	margin: 0; /* Bỏ margin cho nav */
}

nav a {
	color: white;
	margin: 0 15px;
	text-decoration: none;
}

nav a:hover {
	text-decoration: underline;
}

main {
	padding: 20px;
}

.banner-container {
	display: flex; /* Sử dụng flexbox cho banner */
	justify-content: space-between; /* Căn giữa các banner */
	margin-top: 20px; /* Khoảng cách từ header */
}

.banner {
	flex: 1; /* Mỗi banner chiếm không gian bằng nhau */
	margin: 0 10px; /* Khoảng cách giữa các banner */
	background-color: var(--light-color); /* Màu nền cho banner */
	padding: 20px;
	text-align: center; /* Căn giữa nội dung trong banner */
	border-radius: 5px; /* Bo góc cho banner */
}

.search-container, .jobs-container {
	display: flex; /* Sử dụng flexbox cho thanh tìm kiếm */
	flex-direction: column; /* Đặt chiều hướng thành cột */
	margin-top: 20px; /* Khoảng cách từ banner */
	padding: 20px; /* Thêm padding cho thanh tìm kiếm */
	background-color: var(--search-background-color);
	/* Nền trắng trong suốt 50% */
	border-radius: 5px; /* Bo góc cho thanh tìm kiếm */
}

.search-input-container {
	display: flex; /* Sử dụng flexbox cho container */
	justify-content: flex-start; /* Căn trái cho các thành phần */
	margin-bottom: 20px; /* Khoảng cách giữa container và thẻ ngành nghề */
}

.search-input-container input {
	padding: 10px; /* Padding cho input */
	border: 1px solid #ccc; /* Đường viền cho input */
	border-radius: 5px; /* Bo góc */
	margin-right: 5px; /* Khoảng cách giữa các thành phần */
	width: 30%; /* Đặt chiều rộng cho input là 30% */
}

.search-input-container select {
	padding: 10px; /* Padding cho select */
	border: 1px solid #ccc; /* Đường viền cho select */
	border-radius: 5px; /* Bo góc */
	margin-right: 5px; /* Khoảng cách giữa các thành phần */
	width: 30%; /* Đặt chiều rộng cho select là 30% */
}

.search-input-container button {
	padding: 10px 20px; /* Padding cho nút */
	background-color: var(--title-color); /* Màu nền nút */
	color: white; /* Màu chữ trắng */
	border: none; /* Không viền */
	border-radius: 5px; /* Bo góc */
	cursor: pointer; /* Con trỏ chuột dạng tay */
	width: 10%; /* Đặt chiều rộng cho nút là 10% */
}

.search-input-container button:hover {
	background-color: darkorange; /* Màu nền khi hover */
}

.job-category-container {
	display: flex; /* Sử dụng flexbox cho thẻ ngành nghề */
	flex-wrap: wrap; /* Gói các thẻ khi không đủ chỗ */
	width: 100%; /* Đặt chiều rộng cho container */
}

.job-category {
	background-color: var(--light-color); /* Màu nền cho thẻ ngành nghề */
	border: 1px solid #ccc; /* Đường viền cho thẻ ngành nghề */
	border-radius: 5px; /* Bo góc cho thẻ ngành nghề */
	padding: 10px; /* Padding cho thẻ ngành nghề */
	margin: 10px; /* Khoảng cách giữa các thẻ ngành nghề */
	flex: 1 1 200px; /* Thẻ ngành nghề có chiều rộng tối thiểu là 200px */
	text-align: center; /* Căn giữa nội dung trong thẻ */
}

.job-category img {
	width: 50px; /* Đặt kích thước cho logo ngành nghề */
	height: auto; /* Tự động điều chỉnh chiều cao */
	margin-bottom: 5px; /* Khoảng cách dưới logo */
}

.job-card {
	border: 1px solid #ccc;
	position: relative;
	/* Đặt container thành phần tương đối để icon có thể định vị tuyệt đối */
	padding: 20px;
	width: auto; /* Cố định chiều rộng để dễ căn chỉnh */
	align-items: center;
	display: flex;
}

.job-container span {
	margin-right: 10px; /* Khoảng cách giữa tiêu đề và trái tim */
}

.job-card-body {
	margin-left: 20px;
	margin-top: 0px;
}

.heart-icon {
	position: absolute;
	top: 10px; /* Căn trên cùng */
	right: 10px; /* Căn bên phải */
	color: red;
	font-size: 1.5em; /* Điều chỉnh kích thước icon */
}

.logo-company {
	width: 100px;
	height: 100px;
}

.dl-job-card {
	margin: 10px;
}

.lbl-company-name {
	font-size: large;
	font-weight: 700;
	margin-bottom: 10px;
}

h2 {
	color: var(--title-color); /* Màu cam cho tiêu đề */
}

footer {
	background-color: var(--ground-color); /* Nền màu xanh đậm */
	color: white; /* Màu chữ trắng */
	text-align: center;
	padding: 10px 0;
	position: relative;
	bottom: 0;
	width: 100%;
}
</style>
<title>CV Hub</title>
</head>
<body>
	<header>
		<div class="header-left">
			<h1>Logo</h1>
			<h2>Cơ hội việc làm</h2>
		</div>
		<nav>
			<a href="#candidates">Ứng viên</a> <a href="#employers">Nhà tuyển
				dụng</a>
		</nav>
	</header>

	<main>
		<div class="banner-container">
			<div class="banner">
				<h2>Banner 1</h2>
				<p>Nội dung cho Banner 1.</p>
			</div>
			<div class="banner">
				<h2>Banner 2</h2>
				<p>Nội dung cho Banner 2.</p>
			</div>
			<div class="banner">
				<h2>Banner 3</h2>
				<p>Nội dung cho Banner 3.</p>
			</div>
		</div>

		<div class="search-container">
			<div class="search-input-container">
				<input type="text" placeholder="Tìm kiếm..." /> <select>
					<option value="">Chọn nghề nghiệp</option>
					<option value="dev">Lập trình viên</option>
					<option value="design">Thiết kế</option>
					<option value="manager">Quản lý</option>
				</select> <select>
					<option value="">Chọn tỉnh thành</option>
					<option value="hn">Hà Nội</option>
					<option value="hcm">TP. Hồ Chí Minh</option>
					<option value="dn">Đà Nẵng</option>
				</select>

				<button>Tìm kiếm</button>
			</div>

			<!-- Container chứa các thẻ ngành nghề -->
			<div class="job-category-container">
				<div class="job-category">
					<img src="logo-dev.png" alt="Lập trình viên" />
					<h3>Lập trình viên</h3>
					<p>Công việc: 120</p>
				</div>
				<div class="job-category">
					<img src="logo-design.png" alt="Thiết kế" />
					<h3>Thiết kế</h3>
					<p>Công việc: 80</p>
				</div>
				<div class="job-category">
					<img src="logo-manager.png" alt="Quản lý" />
					<h3>Quản lý</h3>
					<p>Công việc: 50</p>
				</div>
				<div class="job-category">
					<img src="logo-marketing.png" alt="Marketing" />
					<h3>Marketing</h3>
					<p>Công việc: 30</p>
				</div>
			</div>
		</div>
		<div class="jobs-container">
			<div class="job-card">
				<i class="fas fa-heart heart-icon"></i> <img class="logo-company"
					src="anh.jpg" alt="Marketing" />
				<div class="job-card-body">
					<label class="lbl-company-name">Tên công việc</label> <br> <label>Tên
						công ty</label> <br> <label>Lương</label> <br> <label>Địa
						điểm</label>
				</div>
			</div>
		</div>
		</div>
	</main>

	<footer>
		<p>&copy; 2024 Trang Web Mẫu. Tất cả các quyền được bảo lưu.</p>
	</footer>
	
	<script src="js/PhanTrangGioiThieu.js"></script>
</body>
</html>