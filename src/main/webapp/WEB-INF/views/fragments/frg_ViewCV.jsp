<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="container my-5">
	<div class="card shadow">
		<div class="card-header text-center bg-success text-white">
			<h1 class="card-title">CV XIN VIỆC</h1>
		</div>
		<div class="card-body">
			<c:set var="cv" value="${requestScope.cv}" />
			<div class="row">
				<div class="col-md-4">
					<!-- Thông tin cá nhân -->
					<div class="container text-center mt-4">
						<div class="position-relative d-inline-block">
							<!-- Avatar -->
							<img id="avatarPreview" src="${cv.ungvien.avatar}" 
								alt="Avatar" class="rounded-circle border"
								style="width: 200px; height: 200px; object-fit: cover;">
						</div>

						<!-- Tên nằm dưới avatar -->
						<h2 class="mt-3">${cv.ungvien.fullName}</h2>
					</div>
					<!-- Vị trí ứng tuyển -->
					<div class="mb-3">
						<label class="form-label fw-bold">VỊ TRÍ ỨNG TUYỂN</label> 
						<input type="text" class="form-control" placeholder="Nhập vị trí ứng tuyển"
							value="${cv.position}">
					</div>

					<!-- Thông tin cá nhân -->
					<div class="mb-3 personal-info">
						<label class="form-label fw-bold">THÔNG TIN CÁ NHÂN</label>
						<!-- Giới tính -->
						<div class="mb-3">
							<label for="gender" class="form-label">Giới tính</label>
							<c:choose>
								<c:when test="${cv != null}">
									<select id="gender" name="gender" class="form-select" disabled>
										<option value="${cv.ungvien.gender}" selected>${cv.ungvien.gender}</option>
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
							<label for="dob" class="form-label">Ngày sinh</label> 
							<input type="date" id="dob" name="dob" class="form-control" value="${cv.ungvien.dob}" readonly>
						</div>
						<!-- Số điện thoại -->
						<div class="mb-3">
							<label for="phone" class="form-label">Số điện thoại</label> 
							<input type="tel" id="phone" name="phone" class="form-control" value="${cv.ungvien.phone}" readonly>
						</div>
						<!-- Email -->
						<div class="mb-3">
							<label for="email" class="form-label">Email</label> 
							<input type="email" id="email" name="email" class="form-control" value="${cv.ungvien.email}" readonly>
						</div>
						<!-- Tỉnh thành -->
						<div class="mb-3">
							<label for="location" class="form-label">Tỉnh thành</label> 
							<input type="text" id="location" name="location" class="form-control" value="${cv.ungvien.location}" readonly>
						</div>
						<!-- Địa chỉ -->
						<div class="mb-3">
							<label for="address" class="form-label">Địa chỉ</label> 
							<input type="text" id="address" name="address" class="form-control" value="${cv.ungvien.address}" readonly>
						</div>
						<!-- Giới thiệu bản thân -->
						<div class="mb-3">
							<label for="introduction" class="form-label">Giới thiệu bản thân</label>
							<textarea id="introduction" name="introduction" class="form-control" rows="4" readonly>${cv.ungvien.introduction}</textarea>
						</div>

					</div>
				</div>
				<div class="col-md-8">
					<!-- Thông tin CV -->
					<!-- Mục tiêu nghề nghiệp -->
					<div class="mb-3">
						<label for="careerGoals" class="form-label fw-bold">MỤC TIÊU</label>
						<textarea id="careerGoals" name="careerGoals" class="form-control" rows="3" readonly 
							placeholder="Nhập mục tiêu nghề nghiệp">${cv.careerGoals}</textarea>
					</div>

					<!-- Học vấn -->
					<div class="mb-3">
						<label class="form-label fw-bold">HỌC VẤN</label>
						<div id="educationContainer">
							<!-- Một mục học vấn đầu tiên -->
							<c:forEach var="education" items="${cv.hocVan}">
								<div class="education-item border rounded p-3 mb-2">
									<div class="row mb-2">
										<div class="col-md-6">
											<label class="form-label">Bắt đầu</label> 
											<input type="date" name="educationStart[]" class="form-control"
												value="${education.start}" readonly>
										</div>
										<div class="col-md-5">
											<label class="form-label">Kết thúc</label> 
											<input type="date" name="educationEnd[]" class="form-control me-2 education-end-date"
												value="${education.end}" readonly>
										</div>
									</div>
									<div class="row mb-2">
										<div class="col-md-6">
											<label class="form-label">Tên trường học</label> 
											<input type="text" name="educationSchool[]" class="form-control"
												value="${education.school}" placeholder="Tên trường học" readonly>
										</div>
										<div class="col-md-6">
											<label class="form-label">Ngành học / Môn học</label> 
											<input type="text" name="educationMajor[]" class="form-control"
												value="${education.major}" placeholder="Ngành học / Môn học" readonly>
										</div>
									</div>
									<div class="mb-2">
										<label class="form-label">Mô tả quá trình học tập hoặc thành tích của bạn</label>
										<textarea name="educationDescription[]" class="form-control" readonly rows="2" placeholder="Nhập mô tả...">${education.description}</textarea>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>

					<!-- Kinh nghiệm làm việc -->
					<label class="form-label fw-bold">KINH NGHIỆM</label>
					<div id="experienceContainer" class="mb-4">
						<c:forEach var="experience" items="${cv.kinhNghiem}">
							<div class="experience-item border rounded p-3 mb-2">
								<div class="row mb-2">
									<div class="col-md-6">
										<label class="form-label">Bắt đầu</label> 
										<input type="date" name="experienceStart[]" class="form-control"
											value="${experience.start}" readonly>
									</div>
									<div class="col-md-5">
										<label class="form-label">Kết thúc</label> 
										<input type="date" name="experienceEnd[]" class="form-control me-2 experience-end-date"
											value="${experience.end}" readonly>
									</div>
								</div>
								<div class="row mb-2">
									<div class="col-md-6">
										<label class="form-label">Tên công ty</label> 
										<input type="text" name="experienceCompany[]" class="form-control"
											value="${experience.company}" placeholder="Tên công ty" readonly>
									</div>
									<div class="col-md-6">
										<label class="form-label">Vị trí công việc</label> 
										<input type="text" name="experiencePosition[]" class="form-control"
											value="${experience.position}" placeholder="Vị trí công việc" readonly>
									</div>
								</div>
								<div class="mb-2">
									<label class="form-label">Mô tả công việc</label>
									<textarea name="experienceDescription[]" class="form-control" readonly rows="2"
										placeholder="Mô tả công việc">${experience.description}</textarea>
								</div>
							</div>
						</c:forEach>
					</div>

				</div>
			</div>

		</div>
	</div>
</div>
