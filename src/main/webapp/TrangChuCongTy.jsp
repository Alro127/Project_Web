<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Trang chủ Công Ty</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js">
</script> 
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js">	
</script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
	<div class="container mt-5 py-2">
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

		<section
			class="activity-images-section py-5 my-5 bg-light shadow-sm rounded">
			<div class="container">
				<h2 class="text-center mb-4 fw-bold">Hình Ảnh Hoạt Động</h2>
				<div id="activityCarousel" class="carousel slide"
					data-bs-ride="carousel">

					<!-- Carousel Items -->
					<div class="carousel-inner">
						<c:set var="length" value="${fn:length(images)}" />
						<c:if test="${length != 0}">
							<c:set var="col" value="${length / 3}" />
							<c:set var="excess" value="${length % 3}" />

							<!-- Carousel active item -->
							<div class="carousel-item active">
								<div class="row justify-content-center">
									<c:forEach var="j" begin="0" end="${col > 0 ? 2 : col - 1}">
										<div class="col-4">
											<img src="${pageContext.request.contextPath}/${images[j]}"
												class="d-block w-100 rounded" alt="Hình ảnh ${j + 1}" style="object-fit: cover;">
										</div>
									</c:forEach>
								</div>
							</div>

							<!-- Other carousel items -->
							<c:forEach var="i" begin="1" end="${col - 1}">
								<div class="carousel-item">
									<div class="row justify-content-center">
										<c:forEach var="j" begin="0" end="2">
											<div class="col-4">
												<img
													src="${pageContext.request.contextPath}/${images[i * 3 + j]}"
													class="d-block w-100 rounded"
													alt="Hình ảnh ${(i * 3 + j) + 1}" style="object-fit: cover;">
											</div>
										</c:forEach>
									</div>
								</div>
							</c:forEach>

							<!-- Excess images -->
							<c:if test="${excess > 0}">
								<div class="carousel-item">
									<div class="row justify-content-center">
										<c:forEach var="i" begin="0" end="${excess - 1}">
											<div class="col-4">
												<img
													src="${pageContext.request.contextPath}/${images[col * 3 + i]}"
													class="d-block w-100 rounded"
													alt="Hình ảnh ${(col * 3 + i) + 1}" style="object-fit: cover;">
											</div>
										</c:forEach>
									</div>
								</div>
							</c:if>
						</c:if>
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
				</div>
				<div class="container">
					<jsp:include page="fragments/frg_CongViec.jsp" />
				</div>
			</section>
		</section>
	</div>

	<!-- Footer -->
	<jsp:include page="fragments/footer.jsp" />

	<script type="text/javascript">
		var idCT = "${congTy.idCT}"; 
		console.log(idCT);// Lưu idCT từ servlet vào một biến JavaScript
	</script>

	<script src="js/PhanTrangCongViecByCongTy.js"></script>
</body>

</html>
