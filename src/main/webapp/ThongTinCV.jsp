<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CV Form</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    
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
                <h1 class="card-title">MINH NHẬT</h1>
                <p class="mb-0">Vị trí ứng tuyển</p>
            </div>
            <div class="card-body">
                <div class="text-center mb-4">
                    <img src="https://storage.evrimagaci.org/old/mi_media/afcae823e61eefb077e1f223594b1e7f.jpeg" alt="Avatar" class="rounded-circle border border-success" style="width: 200px; height: 200px;">
                </div>
                <form id="cvForm">
                    <!-- Mục tiêu nghề nghiệp -->
                    <div class="mb-3">
                        <label for="careerGoals" class="form-label fw-bold">Mục tiêu nghề nghiệp</label>
                        <textarea id="careerGoals" name="careerGoals" class="form-control" rows="3" placeholder="Nhập mục tiêu nghề nghiệp"></textarea>
                    </div>

                    <!-- Học vấn -->
					<div class="mb-3">
					    <label class="form-label fw-bold">HỌC VẤN</label>
					    <div id="educationContainer">
					        <!-- Một mục học vấn đầu tiên -->
					        <div class="education-item border rounded p-3 mb-2">
		                    	<div class="row mb-2">
						            <div class="col-md-6">
						                <label class="form-label">Bắt đầu</label>
						                <input type="date" name="educationStart[]" class="form-control">
						            </div>
						            <div class="col-md-5">
						                <label class="form-label">Kết thúc</label>
						                <input type="date" name="educationEnd[]" class="form-control me-2 education-end-date">
						            </div>
									<div class="col-md-1 d-flex flex-column justify-content-end px-0">
										<div class="form-check d-flex justify-content-center align-items-center mb-3">
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
						                <label class="form-label">Tên trường học</label>
						                <input type="text" name="educationSchool[]" class="form-control" placeholder="Tên trường học">
						            </div>
						            <div class="col-md-6">
						                <label class="form-label">Ngành học / Môn học</label>
						                <input type="text" name="educationMajor[]" class="form-control" placeholder="Ngành học / Môn học">
						            </div>
						        </div>
						        <div class="mb-2">
						            <label class="form-label">Mô tả quá trình học tập hoặc thành tích của bạn</label>
						            <textarea name="educationDescription[]" class="form-control" rows="2" placeholder="Nhập mô tả..."></textarea>
						        </div>
						        <button type="button" class="btn btn-outline-danger btn-sm" onclick="removeEducationItem(this)">
						            <i class="bi bi-x-circle"></i> Xóa
						        </button>
					        </div>
					    </div>
					    <!-- Nút thêm mới -->
					    <button type="button" class="btn btn-outline-success btn-sm" onclick="addEducationItem()">
					        <i class="bi bi-plus-circle"></i> Thêm mới
					    </button>
					</div>


                    <!-- Kinh nghiệm làm việc -->
                    <div class="mb-3">
                        <label for="workExperience" class="form-label fw-bold">Kinh nghiệm làm việc</label>
                        <textarea id="workExperience" name="workExperience" class="form-control" rows="4" placeholder="Nhập kinh nghiệm làm việc"></textarea>
                    </div>

                    <!-- Chứng chỉ -->
					<div class="mb-3">
					    <label class="form-label fw-bold">CHỨNG CHỈ</label>
					    <div id="certificatesContainer">
					        <!-- Một chứng chỉ đầu tiên -->
					        <div class="input-group mb-2">
						        <input type="text" name="certificates[]" class="form-control" placeholder="Nhập tên chứng chỉ">
						        <input type="file" name="certificateFiles[]" class="form-control" accept=".pdf,image/*">
						        <button type="button" class="btn btn-outline-danger btn-sm" onclick="removeCertificateRow(this)">
						            <i class="bi bi-x-circle"></i>
						        </button>
					        </div>       
					    </div>
					    <!-- Nút thêm chứng chỉ -->
					    <button type="button" class="btn btn-outline-success btn-sm" onclick="addCertificateRow()">
					        <i class="bi bi-plus-circle"></i> Thêm chứng chỉ
					    </button>
					</div>
					
                    <!-- Kỹ năng -->
					<div class="mb-3">
					    <label class="form-label fw-bold">KỸ NĂNG</label>
					    <div id="skillsContainer">
					        <!-- Một kỹ năng đầu tiên -->
					        <div class="skill-row d-flex align-items-center mb-2">
					            <input type="text" name="skills[]" class="form-control me-3" placeholder="Tên kỹ năng">
					            <div class="circle-rating d-flex">
					                <span data-value="1" onclick="setCircleRating(this)" class="circle"></span>
					                <span data-value="2" onclick="setCircleRating(this)" class="circle"></span>
					                <span data-value="3" onclick="setCircleRating(this)" class="circle"></span>
					                <span data-value="4" onclick="setCircleRating(this)" class="circle"></span>
					                <span data-value="5" onclick="setCircleRating(this)" class="circle"></span>
					            </div>
					            <input type="hidden" name="skillLevels[]" value="0">
					            <button type="button" class="btn btn-outline-danger btn-sm ms-3" onclick="removeSkillRow(this)">
					                <i class="bi bi-x-circle"></i>
					            </button>
					        </div>
					    </div>
					    <!-- Nút thêm kỹ năng -->
					    <button type="button" class="btn btn-outline-success btn-sm" onclick="addSkillRow()">
					        <i class="bi bi-plus-circle"></i> Thêm kỹ năng
					    </button>
					</div>

                    <!-- Sở thích -->
                    <div class="mb-3">
                        <label for="hobbies" class="form-label fw-bold">Sở thích</label>
                        <textarea id="hobbies" name="hobbies" class="form-control" rows="3" placeholder="Nhập sở thích"></textarea>
                    </div>

                    <!-- Button -->
                    <div class="text-center">
                        <button type="button" class="btn btn-success" onclick="submitForm()">GỬI CV</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="js/ThongTinCV.js"></script>
</body>
</html>
