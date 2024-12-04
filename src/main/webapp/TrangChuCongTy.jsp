<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Trang chủ Công Ty</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<!-- Bootstrap Icon -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.5/font/bootstrap-icons.min.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="assets/css/style.css" rel="stylesheet">

<style>
.image-container {
	height: 300px;
	/* background-image: linear-gradient(to top, #3d405b, rgba(61, 64, 91, 0)),
		url("https://scontent.fsgn5-12.fna.fbcdn.net/v/t39.30808-6/402094294_1774466179683868_1113741505075362781_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=127cfc&_nc_ohc=9b2c3efvWzkQ7kNvgEIJbIV&_nc_zt=23&_nc_ht=scontent.fsgn5-12.fna&_nc_gid=AdKPjrB1rFBJi7CZXDd25iq&oh=00_AYDJK9INSQonmITAgEBekgp-TIRYBj7iIwWyd3L8FqtT0Q&oe=674E4E80");
 */	background-size: cover;
	background-position: center;
	border-radius: 10px;
	position: relative;
}

.company-info {
	position: absolute;
	bottom: 10px;
	left: 10px;
	color: white;
	padding: 10px;
	border-radius: 5px;
	display: flex;
	align-items: flex-end;
}
</style>
</head>
<body class="bg-light-grey">
	<!-- Navbar -->
	<c:choose>
		<c:when test="${not empty sessionScope.id}">
			<!-- Đã đăng nhập -->
			<jsp:include page="fragments/topNavAcc.jsp"></jsp:include>
		</c:when>
		<c:otherwise>
			<!-- Chưa đăng nhập -->
			<jsp:include page="fragments/topNav.jsp"></jsp:include>
		</c:otherwise>
	</c:choose>

	<!-- Main Content -->
	<div class="container mt-5">
		<section class="container mb-5">
			<div class="row position-relative" id="company-info">
				<!-- Phần tên công ty và logo -->
				<div class="image-container mb-5" 
				     style="background-image: linear-gradient(to top, #3d405b, rgba(61, 64, 91, 0)), 
							url('${pageContext.request.contextPath}/${congTy.getBackground()}');
							">
				    <div class="company-info">
				        <img src="${pageContext.request.contextPath}/${congTy.getLogo()}" 
				             class="img-fluid rounded-circle img-logo" alt="Logo">
				        <div class="mx-3">
				            <h4>${congTy.tenCongTy}</h4>
				            <p>
				                Lĩnh vực: ${congTy.linhVuc} <br> Địa chỉ: ${congTy.diaChi}
				            </p>
				        </div>
				    </div>
				</div>

				<!-- Phần thông tin chung của công ty -->
				<div class="company-info-wrapper p-4 bg-light shadow-sm rounded">
					<h2 class="text-center mb-4 fw-bold">Thông Tin Chung</h2>
					<p class="text-justify">${congTy.gioiThieu}</p>
				</div>
			</div>
		</section>



		<!-- Sản phẩm và dịch vụ -->
		<!-- <section class="py-5 rounded mb-5 bg-light shadow-sm rounded">
			<div class="container">
				<h2 class="text-center mb-4 fw-bold">Giới Thiệu Về Công Ty</h2>
				<div class="row gy-4">
					Sản phẩm
					<div class="col-md-4">
						<div class="text-center p-4 shadow-sm rounded bg-body">
							<div class="mb-3">
								<i class="bi bi-box-seam fs-1 text-primary"></i>
							</div>
							<h5 class="mb-3">Sản Phẩm</h5>
							<p class="text-muted">Chúng tôi cung cấp các sản phẩm chất
								lượng cao, đáp ứng mọi nhu cầu của khách hàng.</p>
						</div>
					</div>
					Dịch vụ
					<div class="col-md-4">
						<div class="text-center p-4 shadow-sm rounded bg-body">
							<div class="mb-3">
								<i class="bi bi-wrench-adjustable-circle fs-1 text-success"></i>
							</div>
							<h5 class="mb-3">Dịch Vụ</h5>
							<p class="text-muted">Chúng tôi cung cấp các dịch vụ tận tâm
								và chuyên nghiệp, mang lại trải nghiệm tuyệt vời.</p>
						</div>
					</div>
					Đội ngũ
					<div class="col-md-4">
						<div class="text-center p-4 shadow-sm rounded bg-body">
							<div class="mb-3">
								<i class="bi bi-people fs-1 text-warning"></i>
							</div>
							<h5 class="mb-3">Đội Ngũ</h5>
							<p class="text-muted">Đội ngũ nhân viên giàu kinh nghiệm,
								luôn sẵn sàng hỗ trợ khách hàng.</p>
						</div>
					</div>
				</div>
			</div>
		</section> -->

		<!-- Hình ảnh hoạt động -->
		<!--Sau sẽ dùng vòng lặp để load hình lên-->
		<section
			class="activity-images-section py-5 my-5 bg-light shadow-sm rounded">
			<div class="container">
				<h2 class="text-center mb-4 fw-bold">Hình Ảnh Hoạt Động</h2>
				<div id="activityCarousel" class="carousel slide"
					data-bs-ride="carousel">
					<!-- 
					Indicators
					<div class="carousel-indicators">
						<button type="button" data-bs-target="#activityCarousel"
							data-bs-slide-to="0" class="active" aria-current="true"
							aria-label="Slide 1"></button>
						<button type="button" data-bs-target="#activityCarousel"
							data-bs-slide-to="1" aria-label="Slide 2"></button>
						<button type="button" data-bs-target="#activityCarousel"
							data-bs-slide-to="2" aria-label="Slide 3"></button>
					</div> -->

					<!-- Carousel Items -->
					<div class="carousel-inner">
						<%
							//HttpSession session2 = request.getSession(true);
							List<String> images = (List<String>)request.getAttribute("images");
							int length = images.size();
							int col = length / 3;
							int excess = length - col * 3;
							%>
								<div class="carousel-item active">
									<div class="row justify-content-center">
											<%
												for (int j = 0; j < 3; j ++ )
												{
													%>
														<div class="col-4">
															<img
																src="${pageContext.request.contextPath}/<%= images.get(j) %>"
																class="d-block w-100 rounded" alt="Hình ảnh 1">
														</div>
													<%
												}
											%>
										</div>
								</div>
							<%
							for (int i = 1; i < col; i++)
							{
								%>
									<div class="carousel-item">
										<div class="row justify-content-center">
											<%
												for (int j = 0; j < 3; j ++ )
												{
													%>
														<div class="col-4">
															<img
																src="${pageContext.request.contextPath}/<%= images.get(i * 3 + j) %>"
																class="d-block w-100 rounded" alt="Hình ảnh 1">
														</div>
													<%
												}
											%>
										</div>
									</div>
								<%
							}
							if (excess > 0)
							{
								%>
									<div class="carousel-item">
										<div class="row justify-content-center">
											<%
												for (int i = 0; i < excess ; i++)
												{
													%>
														<div class="col-4">
															<img
																src="${pageContext.request.contextPath}/<%= images.get(col * 3 + i) %>"
																class="d-block w-100 rounded" alt="Hình ảnh 1">
														</div>
													<%
												}
											%>
										</div>
									</div>
								<%
							}
						%>
					</div>

					<!-- Controls -->
					<button class="carousel-control-prev" type="button"
						data-bs-target="#activityCarousel" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#activityCarousel" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
			</div>
		</section>



		<!-- Công Việc -->
		<section class="py-4 wi job-section bg-light shadow-sm rounded ">
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
					<jsp:include page="fragments/frg_CongViec.jsp" />
				</div>
			</section>
		</section>
	</div>

	<!-- Footer -->
	<jsp:include page="fragments/footer.jsp" />

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript">
		var idCT = "${congTy.idCT}"; 
		console.log(idCT);// Lưu idCT từ servlet vào một biến JavaScript
	</script>

	<script src="js/PhanTrangCongViecByCongTy.js"></script>
</body>

</html>
