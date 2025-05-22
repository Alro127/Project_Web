<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>CV Form</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	integrity="sha384-Bk5cbLkZQ5raZ0+H2/+VbfYx3WpvxvQK4zqXZr7sYODuaX7bKXoSOnipQxkaS8sv"
	crossorigin="anonymous">

</head>

<style>
/* Định dạng nút tròn */
.circle-rating {
	display: flex;
	gap: 10px;
}

.circle {
	width: 20px;
	height: 20px;
	border-radius: 50%;
	background-color: #ddd; /* Màu xám nhạt cho nút chưa chọn */
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.circle.active {
	background-color: #28a745; /* Màu xanh lá khi được chọn */
}

.circle:hover {
	background-color: #6c757d; /* Màu xám đậm khi hover */
}

.skill-row .form-control {
	max-width: 200px; /* Đặt độ rộng tối đa cho ô nhập tên kỹ năng */
}
</style>
<body class="bg-light">
	<div class="container my-5">
		<div class="card shadow">
			<div class="card-header text-center bg-success text-white">
				<h1 class="card-title">CV XIN VIỆC</h1>
			</div>
			<div class="card-body">
				<form id="cvForm">
					<input type="hidden" name="csrfToken"
						value="<c:out value='${csrfToken}'/>">
					<div class=row>
						<c:set var="uv" value="${requestScope.uv}" />
						<div class="col-md-4">
							<!-- Thông tin cá nhân -->
							<div class="container text-center mt-4">
								<div class="position-relative d-inline-block ">
									<!-- Avatar -->
									<img id="avatarPreview" src="${uv.avatar}" alt="Avatar"
										class="rounded-circle border avatar-preview"
										>
								</div>

								<!-- Tên nằm dưới avatar -->
								<h2 class="mt-3">${uv.fullName}</h2>
							</div>
							<div>
								<!-- Vị trí ứng tuyển -->
								<div class="mb-3">
									<label class="form-label fw-bold">VỊ TRÍ ỨNG TUYỂN</label> <input
										type="text" class="form-control" id="position"
										placeholder="Nhập vị trí ứng tuyển">
								</div>


								<!-- Thông tin cá nhân -->
								<div class="mb-3 personal-info">
									<label class="form-label fw-bold">THÔNG TIN CÁ NHÂN</label>
									<!-- Giới tính -->
									<div class="mb-3">
										<label for="gender" class="form-label">Giới tính</label>
										<c:choose>
											<c:when test="${not empty uv}">
												<select id="gender" name="gender" class="form-select"
													disabled>
													<option value="${uv.gender}" selected>${uv.gender}</option>
												</select>
											</c:when>
											<c:otherwise>
												<select id="gender" name="gender" class="form-select">
													<option value="male" selected>Nam</option>
													<option value="female">Nữ</option>
													<option value="other">Khác</option>
												</select>
											</c:otherwise>
										</c:choose>
									</div>

									<!-- Ngày sinh -->
									<div class="mb-3">
										<label for="dob" class="form-label">Ngày sinh</label> <input
											type="date" id="dob" name="dob" class="form-control"
											value="${uv.dob}" readonly>
									</div>

									<!-- Số điện thoại -->
									<div class="mb-3">
										<label for="phone" class="form-label">Số điện thoại</label> <input
											type="tel" id="phone" name="phone" class="form-control"
											value="${uv.phone}" readonly>
									</div>

									<!-- Email -->
									<div class="mb-3">
										<label for="email" class="form-label">Email</label> <input
											type="email" id="email" name="email" class="form-control"
											value="${uv.email}" readonly>
									</div>

									<!-- Tỉnh thành -->
									<div class="mb-3">
										<label for="location" class="form-label">Tỉnh thành</label> <input
											type="text" id="location" name="location"
											class="form-control" value="${uv.location}" readonly>
									</div>

									<!-- Địa chỉ -->
									<div class="mb-3">
										<label for="address" class="form-label">Địa chỉ</label> <input
											type="text" id="address" name="address" class="form-control"
											value="${uv.address}" readonly>
									</div>

									<!-- Giới thiệu bản thân -->
									<div class="mb-3">
										<label for="introduction" class="form-label">Giới
											thiệu bản thân</label>
										<textarea id="introduction" name="introduction"
											class="form-control" rows="4" readonly>${uv.introduction}</textarea>
									</div>


									<!-- Nút Chỉnh sửa -->
									<div class="text-end">
										<button type="button" id="editPersonalInfoBtn"
											class="btn btn-outline-primary">
											<a href="QuanLyTaiKhoanServlet"
												class= "no-underline"><i
												class="bi bi-pencil-square"></i> Chỉnh sửa thông tin</a>
										</button>
									</div>
								</div>
							</div>

						</div>
						<div class="col-md-8">
							<!-- Thông tin CV -->
							<!-- Mục tiêu nghề nghiệp -->
							<div class="mb-3">
								<label for="careerGoals" class="form-label fw-bold">MỤC
									TIÊU</label>
								<textarea id="careerGoals" name="careerGoals"
									class="form-control" rows="3"
									placeholder="Nhập mục tiêu nghề nghiệp"></textarea>
							</div>

							<!-- Học vấn -->
							<div class="mb-3">
								<label class="form-label fw-bold">HỌC VẤN</label>
								<div id="educationContainer">
									<!-- Một mục học vấn đầu tiên -->
									<div class="education-item border rounded p-3 mb-2">
										<div class="row mb-2">
											<div class="col-md-6">
												<label class="form-label">Bắt đầu</label> <input type="date"
													name="educationStart[]" class="form-control">
											</div>
											<div class="col-md-5">
												<label class="form-label">Kết thúc</label> <input
													type="date" name="educationEnd[]"
													class="form-control me-2 education-end-date">
											</div>
											<div
												class="col-md-1 d-flex flex-column justify-content-end px-0">
												<div
													class="form-check d-flex justify-content-center align-items-center mb-3">
													<input type="checkbox"
														class="form-check-input education-current-checkbox"
														onchange="toggleEndDate(this)"> <label
														class="form-check-label" for="educationCurrent">Hiện
														tại</label>
												</div>
											</div>
										</div>
										<div class="row mb-2">
											<div class="col-md-6">
												<label class="form-label">Tên trường học</label> <input
													type="text" name="educationSchool[]" class="form-control"
													placeholder="Tên trường học">
											</div>
											<div class="col-md-6">
												<label class="form-label">Ngành học / Môn học</label> <input
													type="text" name="educationMajor[]" class="form-control"
													placeholder="Ngành học / Môn học">
											</div>
										</div>
										<div class="mb-2">
											<label class="form-label">Mô tả quá trình học tập
												hoặc thành tích của bạn</label>
											<textarea name="educationDescription[]" class="form-control"
												rows="2" placeholder="Nhập mô tả..."></textarea>
										</div>
										<button type="button" class="btn btn-outline-danger btn-sm"
											onclick="removeEducationItem(this)">
											<i class="bi bi-x-circle"></i> Xóa
										</button>
									</div>
								</div>
								<!-- Nút thêm mới -->
								<button type="button" class="btn btn-outline-success btn-sm"
									onclick="addEducationItem()">
									<i class="bi bi-plus-circle"></i> Thêm mới
								</button>
							</div>


							<!-- Kinh nghiệm làm việc -->
							<div id="experienceContainer" class="mb-4">
								<label class="form-label fw-bold">KINH NGHIỆM</label>
								<!-- Mục kinh nghiệm làm việc mặc định -->
								<div class="experience-item border rounded p-3 mb-2">
									<div class="row mb-2">
										<div class="col-md-6">
											<label class="form-label">Bắt đầu</label> <input type="date"
												name="experienceStart[]" class="form-control">
										</div>
										<div class="col-md-5">
											<label class="form-label">Kết thúc</label> <input type="date"
												name="experienceEnd[]"
												class="form-control me-2 experience-end-date">
										</div>
										<div
											class="col-md-1 d-flex flex-column justify-content-end px-0">
											<div
												class="form-check d-flex justify-content-center align-items-center mb-3">
												<input type="checkbox"
													class="form-check-input experience-current-checkbox"
													onchange="toggleExperienceEndDate(this)"> <label
													class="form-check-label">Hiện tại</label>
											</div>
										</div>
									</div>
									<div class="row mb-2">
										<div class="col-md-6">
											<label class="form-label">Tên công ty</label> <input
												type="text" name="experienceCompany[]" class="form-control"
												placeholder="Tên công ty">
										</div>
										<div class="col-md-6">
											<label class="form-label">Vị trí công việc</label> <input
												type="text" name="experiencePosition[]" class="form-control"
												placeholder="Vị trí công việc">
										</div>
									</div>
									<div class="mb-2">
										<label class="form-label">Mô tả kinh nghiệm làm việc</label>
										<textarea name="experienceDescription[]" class="form-control"
											rows="2" placeholder="Nhập mô tả..."></textarea>
									</div>
									<button type="button" class="btn btn-outline-danger btn-sm"
										onclick="removeExperienceItem(this)">
										<i class="bi bi-x-circle"></i> Xóa
									</button>
								</div>
							</div>

							<!-- Nút thêm mới -->
							<button type="button" class="btn btn-outline-success btn-sm"
								onclick="addExperienceItem()">
								<i class="bi bi-plus-circle"></i> Thêm kinh nghiệm
							</button>


							<!-- Chứng chỉ -->
							<div class="mb-3">
								<label class="form-label fw-bold">CHỨNG CHỈ</label>
								<div id="certificatesContainer">
									<!-- Một chứng chỉ đầu tiên -->
									<div class="input-group mb-2">
										<input type="text" name="certificates[]" class="form-control"
											placeholder="Nhập tên chứng chỉ">
										<button type="button" class="btn btn-outline-danger btn-sm"
											onclick="removeCertificateRow(this)">
											<i class="bi bi-x-circle"></i>
										</button>
									</div>
								</div>
								<!-- Nút thêm chứng chỉ -->
								<button type="button" class="btn btn-outline-success btn-sm"
									onclick="addCertificateRow()">
									<i class="bi bi-plus-circle"></i> Thêm chứng chỉ
								</button>
							</div>

							<!-- Kỹ năng -->
							<div class="mb-3">
								<label class="form-label fw-bold">KỸ NĂNG</label>
								<div id="skillsContainer">
									<!-- Một kỹ năng đầu tiên -->
									<div class="skill-row d-flex align-items-center mb-2">
										<input type="text" name="skills[]" class="form-control me-3"
											placeholder="Tên kỹ năng">
										<div class="circle-rating d-flex">
											<span data-value="1" onclick="setCircleRating(this)"
												class="circle"></span> <span data-value="2"
												onclick="setCircleRating(this)" class="circle"></span> <span
												data-value="3" onclick="setCircleRating(this)"
												class="circle"></span> <span data-value="4"
												onclick="setCircleRating(this)" class="circle"></span> <span
												data-value="5" onclick="setCircleRating(this)"
												class="circle"></span>
										</div>
										<input type="hidden" name="skillLevels[]" value="0">
										<button type="button"
											class="btn btn-outline-danger btn-sm ms-3"
											onclick="removeSkillRow(this)">
											<i class="bi bi-x-circle"></i>
										</button>
									</div>
								</div>
								<!-- Nút thêm kỹ năng -->
								<button type="button" class="btn btn-outline-success btn-sm"
									onclick="addSkillRow()">
									<i class="bi bi-plus-circle"></i> Thêm kỹ năng
								</button>
							</div>
						</div>

						<!-- Lưu CV-->
						<textarea type="hidden" id="mode" class = "hidden">create</textarea>
						<div class="text-center">
							<button type="button" class="btn btn-success"
								onClick="saveData()">Lưu CV</button>
							<button type="button" class="btn"
								onclick="submitAndGoToQuanLyCV()">Quay Lại</button>
						</div>
						<input type="hidden" name="csrfToken"
							value="<c:out value='${csrfToken}'/>">
				</form>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/ThongTinCV.js"></script>
	<script>
		function submitAndGoToQuanLyCV() {
			// Gửi form đến SaveCVServlet trước
			document.getElementById("cvForm").submit();

			// Sau khi gửi xong, điều hướng sang QuanLyCVServlet
			window.location.href = "QuanLyCVServlet";
		}
	</script>
</body>
</html>