<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TuongCongTy</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet" href="../assets/css/customcss.css">
<script>
    $(document).ready(function() {
        $('#toggleSidebar').click(function() {
            $('#sidebar').toggleClass('collapsed');
            $('#mainContent').toggleClass('collapsed');
        });
    });
</script>
</head>
<body>
	<!-- Header -->
	<nav class="custom-header navbar navbar-expand-lg">
    <div class="container">
        <a class="navbar-brand" href="#">
        	<div class="csheader">
        		
        	</div>
            <img src="../assets/images/logo.jpg" alt="Logo" class="maxWidth100px align-text-top">
            <h1>Công ty TNHH Tấn Đạt</h1>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <!-- <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link text-white" href="#">Trang Chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="#">Giới Thiệu</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="#">Dịch Vụ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="#">Liên Hệ</a>
                </li>
            </ul>
        </div> -->
    </div>
</nav>

<div class="sidebar" id="sidebar">
	<button class="btn btn-primary toggle-btn" id="toggleSidebar">></button>
    <nav class="nav flex-column">
        <a class="nav-link" href="#"><i class="fas fa-home"></i></a>
        <a class="nav-link" href="#"><i class="fas fa-user"></i></a>
        <a class="nav-link" href="#"><i class="fas fa-cog"></i></a>
        <a class="nav-link" href="#"><i class="fas fa-envelope"></i></a>
        <a class="nav-link" href="#"><i class="fas fa-phone"></i></a>
    </nav>
</div>

<div class="main-content" id="mainContent">
    <div class="banner">
        <h1>Chào mừng đến với Công ty của chúng tôi</h1>
    </div>
    <section class="info-section">
        <div class="container">
            <h2>Thông Tin Chung Về Công Ty</h2>
            <p>Công ty chúng tôi được thành lập vào năm 2020, với sứ mệnh mang đến những sản phẩm và dịch vụ chất lượng cao nhất cho khách hàng. Chúng tôi tự hào về đội ngũ nhân viên chuyên nghiệp và giàu kinh nghiệm.</p>
            <p>Chúng tôi hoạt động trong lĩnh vực công nghệ thông tin, cung cấp các giải pháp phần mềm tùy chỉnh, phát triển ứng dụng di động và dịch vụ tư vấn công nghệ.</p>
            <p>Mục tiêu của chúng tôi là trở thành một trong những công ty hàng đầu trong lĩnh vực công nghệ, mang đến giá trị thực cho khách hàng và đóng góp tích cực vào sự phát triển của cộng đồng.</p>
        </div>
    </section>

    <section class="wi mb24 details-section">
        <div class="container">
            <h2>Chi Tiết Về Sản Phẩm và Dịch Vụ</h2>
            <p>Chúng tôi cung cấp nhiều sản phẩm và dịch vụ đa dạng, bao gồm:</p>
            <ul>
                <li>Giải pháp phần mềm tùy chỉnh theo yêu cầu khách hàng.</li>
                <li>Phát triển ứng dụng di động cho Android và iOS.</li>
                <li>Dịch vụ tư vấn công nghệ thông tin và tối ưu hóa quy trình làm việc.</li>
                <li>Hỗ trợ kỹ thuật và bảo trì hệ thống 24/7.</li>
            </ul>
            <p>Đội ngũ của chúng tôi luôn sẵn sàng tư vấn và hỗ trợ để đảm bảo khách hàng có trải nghiệm tốt nhất.</p>
        </div>
    </section>

   <section class="wi mb24 image-section">
        <div class="container">
            <h2>Hình Ảnh Hoạt Động</h2>
            <div class="image-frame">
                <img src="../assets/images/logo.jpg" alt="Hình ảnh hoạt động 1">
                <img src="../assets/images/logo.jpg" alt="Hình ảnh hoạt động 2">
                <img src="../assets/images/logo.jpg" alt="Hình ảnh hoạt động 3">
                <img src="../assets/images/logo.jpg" alt="Hình ảnh hoạt động 4">
                <img src="../assets/images/logo.jpg" alt="Hình ảnh hoạt động 5">
            </div>
            <p class="mt-3">Hình ảnh minh họa cho hoạt động và không gian làm việc của chúng tôi.</p>
        </div>
    </section>
    
    <section class="job-section">
    <div class="container">
        <h2>Các Công Việc Của Chúng Tôi</h2>
        <div class="filter-section">
            <select class="form-select" id="jobFilter" aria-label="Bộ lọc công việc">
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
        </div>
    </div>
		</section>
    <footer class="footer">
	    <div class="container text-center">
	        <p>&copy; 2024 Công Ty ABC. Tất cả quyền được bảo lưu.</p>
	        <ul class="list-inline">
	            <li class="list-inline-item"><a href="#">Liên hệ</a></li>
	            <li class="list-inline-item"><a href="#">Giới thiệu</a></li>
	            <li class="list-inline-item"><a href="#">Chính sách bảo mật</a></li>
	            <li class="list-inline-item"><a href="#">Điều khoản sử dụng</a></li>
	        </ul>
	        <div class="social-media">
	            <a href="#" class="me-2"><i class="fab fa-facebook"></i></a>
	            <a href="#" class="me-2"><i class="fab fa-twitter"></i></a>
	            <a href="#"><i class="fab fa-linkedin"></i></a>
	        </div>
	    </div>
	</footer>
    
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function() {
        $('#toggleSidebar').click(function() {
            $('#sidebar').toggleClass('collapsed');
            $('#mainContent').toggleClass('collapsed');
        });
    });
</script>

	
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>