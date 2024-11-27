<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang chủ Công Ty</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <!-- Bootstrap Icon -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.5/font/bootstrap-icons.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="assets/css/TuongCongTy.css">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand d-flex align-items-center" href="#">
                <img src="assets/images/cvhublogo.png" alt="Logo" class="rounded me-2" style="width: 50px; height: 50px;">
                <span>Việc Làm Việt Nam</span>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">Tùy chọn</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="#">Action</a></li>
                            <li><a class="dropdown-item" href="#">Another action</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">
                            <i class="fa-solid fa-bell"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end">
                            <li><a class="dropdown-item" href="#">Thông báo 1</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">
                            <i class="fa-regular fa-user"></i> Đặng Minh Nhật
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end">
                            <li><a class="dropdown-item" href="#">Hồ sơ</a></li>
                            <li><a class="dropdown-item" href="#">Đăng xuất</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container mt-4">
	<section class="container mb-5">
	    <div class="row position-relative" id="company-info">
	        <!-- Phần tên công ty và logo -->
	        <div class="d-flex align-items-center justify-content-center text-center p-4">
	            <div>
	                <img src="assets/images/logo.jpg" class="img-fluid rounded-circle" alt="Logo">
	                <h1 class="mt-3 company-name text-uppercase">Công Ty TNHH Tấn Đạt</h1>
	                <p class="text-muted">Dành tất cả cơ hội cho bạn</p>
	            </div>
	        </div>
	
	        <!-- Phần thông tin chung của công ty -->
	        <div class="company-info-wrapper p-4 bg-light shadow-sm rounded">
	            <h2 class="text-justify mb-4 fw-bold">Thông Tin Chung</h2>
	            <p class="text-justify">Công ty chúng tôi được thành lập vào năm 2024, chuyên cung cấp các dịch vụ công nghệ thông tin và giải pháp doanh nghiệp. Chúng tôi luôn nỗ lực mang lại các sản phẩm và dịch vụ chất lượng nhất để đáp ứng nhu cầu của khách hàng...</p>
	        </div>
	    </div>
	</section>



        <!-- Sản phẩm và dịch vụ -->
        <section class="py-5 rounded mb-5 bg-light">
		    <div class="container">
		        <h2 class="text-center mb-4 fw-bold">Giới Thiệu Về Công Ty</h2>
		        <div class="row gy-4">
		            <!-- Sản phẩm -->
		            <div class="col-md-4">
		                <div class="text-center p-4 shadow-sm rounded bg-body">
		                    <div class="mb-3">
		                        <i class="bi bi-box-seam fs-1 text-primary"></i>
		                    </div>
		                    <h5 class="mb-3">Sản Phẩm</h5>
		                    <p class="text-muted">Chúng tôi cung cấp các sản phẩm chất lượng cao, đáp ứng mọi nhu cầu của khách hàng.</p>
		                </div>
		            </div>
		            <!-- Dịch vụ -->
		            <div class="col-md-4">
		                <div class="text-center p-4 shadow-sm rounded bg-body">
		                    <div class="mb-3">
		                        <i class="bi bi-wrench-adjustable-circle fs-1 text-success"></i>
		                    </div>
		                    <h5 class="mb-3">Dịch Vụ</h5>
		                    <p class="text-muted">Chúng tôi cung cấp các dịch vụ tận tâm và chuyên nghiệp, mang lại trải nghiệm tuyệt vời.</p>
		                </div>
		            </div>
		            <!-- Đội ngũ -->
		            <div class="col-md-4">
		                <div class="text-center p-4 shadow-sm rounded bg-body">
		                    <div class="mb-3">
		                        <i class="bi bi-people fs-1 text-warning"></i>
		                    </div>
		                    <h5 class="mb-3">Đội Ngũ</h5>
		                    <p class="text-muted">Đội ngũ nhân viên giàu kinh nghiệm, luôn sẵn sàng hỗ trợ khách hàng.</p>
		                </div>
		            </div>
		        </div>
		    </div>
		</section>
        
        <!-- Hình ảnh hoạt động -->
        <!--Sau sẽ dùng vòng lặp để load hình lên-->
		<section class="activity-images-section my-5">
		    <div class="container">
		        <h2 class="text-center mb-4">Hình Ảnh Hoạt Động</h2>
		        <div id="activityCarousel" class="carousel slide" data-bs-ride="carousel">
		            <!-- Indicators -->
		            <div class="carousel-indicators">
		                <button type="button" data-bs-target="#activityCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
		                <button type="button" data-bs-target="#activityCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
		                <button type="button" data-bs-target="#activityCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
		            </div>
		
		            <!-- Carousel Items -->
		            <div class="carousel-inner">
		                <div class="carousel-item active">
		                    <div class="row justify-content-center">
		                        <div class="col-4">
		                            <img src="assets/images/testBanner.jpg" class="d-block w-100 rounded" alt="Hình ảnh 1">
		                        </div>
		                        <div class="col-4">
		                            <img src="assets/images/testBanner.jpg" class="d-block w-100 rounded" alt="Hình ảnh 2">
		                        </div>
		                        <div class="col-4">
		                            <img src="assets/images/testBanner.jpg" class="d-block w-100 rounded" alt="Hình ảnh 3">
		                        </div>
		                    </div>
		                </div>
		                <div class="carousel-item active">
		                    <div class="row justify-content-center">
		                        <div class="col-4">
		                            <img src="assets/images/testBanner.jpg" class="d-block w-100 rounded" alt="Hình ảnh 1">
		                        </div>
		                        <div class="col-4">
		                            <img src="assets/images/testBanner.jpg" class="d-block w-100 rounded" alt="Hình ảnh 2">
		                        </div>
		                        <div class="col-4">
		                            <img src="assets/images/testBanner.jpg" class="d-block w-100 rounded" alt="Hình ảnh 3">
		                        </div>
		                    </div>
		                </div>
		                <div class="carousel-item active">
		                    <div class="row justify-content-center">
		                        <div class="col-4">
		                            <img src="assets/images/testBanner.jpg" class="d-block w-100 rounded" alt="Hình ảnh 1">
		                        </div>
		                        <div class="col-4">
		                            <img src="assets/images/testBanner.jpg" class="d-block w-100 rounded" alt="Hình ảnh 2">
		                        </div>
		                        <div class="col-4">
		                            <img src="assets/images/testBanner.jpg" class="d-block w-100 rounded" alt="Hình ảnh 3">
		                        </div>
		                    </div>
		                </div>
		            </div>
		
		            <!-- Controls -->
		            <button class="carousel-control-prev" type="button" data-bs-target="#activityCarousel" data-bs-slide="prev">
		                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		                <span class="visually-hidden">Previous</span>
		            </button>
		            <button class="carousel-control-next" type="button" data-bs-target="#activityCarousel" data-bs-slide="next">
		                <span class="carousel-control-next-icon" aria-hidden="true"></span>
		                <span class="visually-hidden">Next</span>
		            </button>
		        </div>
		    </div>
		</section>



        <!-- Công Việc -->
        <section class="mtb24 wi job-section">
	        <section class="mb-5">
				<div class="container mb-2">
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
				</div>
				<div class="container">
				    <div class="row g-3"> <!-- Gán khoảng cách giữa các thẻ -->
				        <div class="col-12 col-sm-6 col-md-4"> <!-- 3 thẻ trên mỗi dòng với màn hình lớn -->
				            <div class="job-card p-3 border rounded">
				                <h5>Công Việc 1</h5>
				                <p>Mô tả công việc 1</p>
				                <button class="btn btn-primary">Xem chi tiết</button>
				            </div>
				        </div>
				        <div class="col-12 col-sm-6 col-md-4">
				            <div class="job-card p-3 border rounded">
				                <h5>Công Việc 2</h5>
				                <p>Mô tả công việc 2</p>
				                <button class="btn btn-primary">Xem chi tiết</button>
				            </div>
				        </div>
				        <div class="col-12 col-sm-6 col-md-4">
				            <div class="job-card p-3 border rounded">
				                <h5>Công Việc 3</h5>
				                <p>Mô tả công việc 3</p>
				                <button class="btn btn-primary">Xem chi tiết</button>
				            </div>
				        </div>
				        <!-- Thêm các thẻ khác -->
				    </div>
				</div>
	        </section>
        </section>
    </div>

    <!-- Footer -->
	<footer class="footer bg-light py-4 mt-5">
	    <div class="container text-center">
	        <!-- Copyright -->
	        <p class="mb-3">&copy; 2024 Công Ty ABC. Tất cả quyền được bảo lưu.</p>
	        
	        <!-- Links -->
	        <ul class="list-inline mb-3">
	            <li class="list-inline-item"><a href="#" class="hover-text-primary">Liên hệ</a></li>
	            <li class="list-inline-item"><a href="#" class="hover-text-primary">Giới thiệu</a></li>
	            <li class="list-inline-item"><a href="#" class="hover-text-primary">Chính sách bảo mật</a></li>
	            <li class="list-inline-item"><a href="#" class="hover-text-primary">Điều khoản sử dụng</a></li>
	        </ul>
	        
	        <!-- Social Media -->
	        <div class="social-media">
	            <a href="#" class="me-3 hover-text-primary"><i class="fab fa-facebook fa-lg"></i></a>
	            <a href="#" class="me-3 hover-text-primary"><i class="fab fa-twitter fa-lg"></i></a>
	            <a href="#" class="hover-text-primary"><i class="fab fa-linkedin fa-lg"></i></a>
	        </div>
	    </div>
	</footer>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
