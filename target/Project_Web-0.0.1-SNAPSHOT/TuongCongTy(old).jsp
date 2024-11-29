<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tường công ty</title>
<link rel="stylesheet" href="assets/css/TuongCongTy.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
	<div class="header">
		<button id="toggleButton">
			<i class="fa-solid fa-bars"></i>
		</button>
		<img id="headerlogo" alt="" src="assets/images/cvhublogo.png"
			style="border-radius: 5px; width: 80px; height: 80px; margin-left: 36px;">
		<h1>VIỆC LÀM VIỆT NAM</h1>
		<div class="header__rightside">
			<button>
				<i class="fa-solid fa-bell"></i>
				<div class="subnav">
					<a href="#">Tùy chọn 1</a> <a href="#">Tùy chọn 2</a> <a href="#">Tùy
						chọn 3</a>
				</div>
			</button>
			<button>
				<i class="fa-regular fa-user"></i>
				<div class="subnav">
					<a href="#">Tùy chọn 1</a> <a href="#">Tùy chọn 2</a> <a href="#">Tùy
						chọn 3</a>
				</div>
			</button>
		</div>
	</div>
	<div class="mainbody">
		<div class="main_content__slider" id="sidebar">
			<nav class="nav flex-column">
				<div class="customcontainer">
					<a class="nav-link" href="#"><i class="fas fa-home"></i></a>
					<h2>
						<a href="#" style="text-decoration: none;">HOME</a>
					</h2>
				</div>
				<div class="customcontainer">
					<a class="nav-link" href="#"><i class="fas fa-cog"></i></a>
					<h2>
						<a href="#" style="text-decoration: none;">SETTING</a>
					</h2>
				</div>
				<div class="customcontainer">
					<a class="nav-link" href="#"><i class="fas fa-user"></i></a>
					<h2>
						<a href="#" style="text-decoration: none;">USER</a>
					</h2>
				</div>
				<div class="customcontainer">
					<a class="nav-link" href="#"><i class="fas fa-envelope"></i></a>
					<h2>
						<a href="#" style="text-decoration: none;">CONTACT</a>
					</h2>
				</div>
				<div class="customcontainer">
					<a class="nav-link" href="#"><i class="fas fa-phone"></i></a>
					<h2>
						<a href="#" style="text-decoration: none;">PHONE</a>
					</h2>
				</div>
			</nav>
		</div>
		<div class="main_content">
			<div class="main_content__detail">
				<div class="mb16 banner main_content__detail__bannersection">
					<!-- <h1>Chào mừng đến với công ty của chúng tôi!</h1> -->
					<img class="logo" src="../assets/images/logo.jpg" alt="logo">
					<div>
						<h1>Công ty trách nhiệm hữu hạn Tấn Đạt</h1>
						<h3>Dành tất cả cơ hội cho bạn</h3>
					</div>

				</div>
				<section class="mtb24 info-section">
					<div class="container">
						<h2>Thông Tin Chung Về Công Ty</h2>
						<p>Công ty chúng tôi được thành lập vào năm 2020, với sứ mệnh
							mang đến những sản phẩm và dịch vụ chất lượng cao nhất cho khách
							hàng. Chúng tôi tự hào về đội ngũ nhân viên chuyên nghiệp và giàu
							kinh nghiệm.</p>
						<p>Chúng tôi hoạt động trong lĩnh vực công nghệ thông tin,
							cung cấp các giải pháp phần mềm tùy chỉnh, phát triển ứng dụng di
							động và dịch vụ tư vấn công nghệ.</p>
						<p>Mục tiêu của chúng tôi là trở thành một trong những công ty
							hàng đầu trong lĩnh vực công nghệ, mang đến giá trị thực cho
							khách hàng và đóng góp tích cực vào sự phát triển của cộng đồng.</p>
					</div>
				</section>
				<section class="mtb24 bgprimary wi details-section">
					<div class="container">
						<h2>Chi Tiết Về Sản Phẩm và Dịch Vụ</h2>
						<p>Chúng tôi cung cấp nhiều sản phẩm và dịch vụ đa dạng, bao
							gồm:</p>
						<ul>
							<li>Giải pháp phần mềm tùy chỉnh theo yêu cầu khách hàng.</li>
							<li>Phát triển ứng dụng di động cho Android và iOS.</li>
							<li>Dịch vụ tư vấn công nghệ thông tin và tối ưu hóa quy
								trình làm việc.</li>
							<li>Hỗ trợ kỹ thuật và bảo trì hệ thống 24/7.</li>
						</ul>
						<p>Đội ngũ của chúng tôi luôn sẵn sàng tư vấn và hỗ trợ để đảm
							bảo khách hàng có trải nghiệm tốt nhất.</p>
					</div>
				</section>

				<section class="mtb24 wi job-section">
					<div class="container">
						<h2 class="mb16">Các Công Việc Của Chúng Tôi</h2>
						<div class="mb16 filter-section">
							<select class="form-select" id="jobFilter"
								aria-label="Bộ lọc công việc">
								<option selected>Tất cả công việc</option>
								<option value="1">Công việc IT</option>
								<option value="2">Công việc Kinh doanh</option>
								<option value="3">Công việc Hỗ trợ khách hàng</option>
							</select>
						</div>
						<div class="job-cards">
							<div class="job-card">
								<h5>Công Việc 1</h5>
								<p>Mô tả công việc 1</p>
								<button class="btn btn-primary">Xem chi tiết</button>
							</div>
							<div class="job-card">
								<h5>Công Việc 2</h5>
								<p>Mô tả công việc 2</p>
								<button class="btn btn-primary">Xem chi tiết</button>
							</div>
							<div class="job-card">
								<h5>Công Việc 3</h5>
								<p>Mô tả công việc 3</p>
								<button class="btn btn-primary">Xem chi tiết</button>
							</div>
							<div class="job-card">
								<h5>Công Việc 3</h5>
								<p>Mô tả công việc 3</p>
								<button class="btn btn-primary">Xem chi tiết</button>
							</div>
							<div class="job-card">
								<h5>Công Việc 3</h5>
								<p>Mô tả công việc 3</p>
								<button class="btn btn-primary">Xem chi tiết</button>
							</div>
							<div class="job-card">
								<h5>Công Việc 3</h5>
								<p>Mô tả công việc 3</p>
								<button class="btn btn-primary">Xem chi tiết</button>
							</div>
							<div class="job-card">
								<h5>Công Việc 3</h5>
								<p>Mô tả công việc 3</p>
								<button class="btn btn-primary">Xem chi tiết</button>
							</div>
							<div class="job-card">
								<h5>Công Việc 3</h5>
								<p>Mô tả công việc 3</p>
								<button class="btn btn-primary">Xem chi tiết</button>
							</div>
							<div class="job-card">
								<h5>Công Việc 3</h5>
								<p>Mô tả công việc 3</p>
								<button class="btn btn-primary">Xem chi tiết</button>
							</div>
						</div>
					</div>
				</section>
				<section class="bgprimary wi mb24 image-section">
					<div class="container">
						<h2>Hình Ảnh Hoạt Động</h2>
						<div class="image-frame">
							<img src="../assets/images/logo.jpg" alt="Hình ảnh hoạt động 1">
							<img src="../assets/images/logo.jpg" alt="Hình ảnh hoạt động 2">
							<img src="../assets/images/logo.jpg" alt="Hình ảnh hoạt động 3">
							<img src="../assets/images/logo.jpg" alt="Hình ảnh hoạt động 4">
							<img src="../assets/images/logo.jpg" alt="Hình ảnh hoạt động 5">
						</div>
						<p class="mt-3">Hình ảnh minh họa cho hoạt động và không gian
							làm việc của chúng tôi.</p>
					</div>
				</section>

				<footer class="wi footer">
					<div class="container text-center">
						<p>&copy; 2024 Công Ty ABC. Tất cả quyền được bảo lưu.</p>
						<ul class="list-inline">
							<li class="list-inline-item"><a href="#">Liên hệ</a></li>
							<li class="list-inline-item"><a href="#">Giới thiệu</a></li>
							<li class="list-inline-item"><a href="#">Chính sách bảo
									mật</a></li>
							<li class="list-inline-item"><a href="#">Điều khoản sử
									dụng</a></li>
						</ul>
						<div class="social-media">
							<a href="#" class="me-2"><i class="fab fa-facebook"></i></a> <a
								href="#" class="me-2"><i class="fab fa-twitter"></i></a> <a
								href="#"><i class="fab fa-linkedin"></i></a>
						</div>
					</div>
				</footer>
			</div>

		</div>

	</div>


	<script>
        document.getElementById("toggleButton").addEventListener("click", function() {
            var sidebar = document.getElementById("sidebar");
            sidebar.classList.toggle("expanded");
            var togglebtn = document.getElementById("toggleButton");
            var headerlogo = document.getElementById("headerlogo");
            togglebtn.classList.toggle("expanded");
           /*  headerlogo.classList.toggle("expanded"); */
           /*  this.textContent = this.classList.contains("expanded") ? "<<" : ">>"; // Thay đổi văn bản */
        });
    </script>
</body>
</html>