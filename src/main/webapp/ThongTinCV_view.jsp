<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="beans.CV" %>
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
    .hide-button {
    display: none; /* Ẩn các nút */
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
	            <% CV cv = (CV) request.getAttribute("cv"); %>
	            	<div class=row>
	            		<div class="col-md-4">
	            		<!-- Thông tin cá nhân -->
							<div class="container text-center mt-4">
							<div class="position-relative d-inline-block">
							    <!-- Avatar -->
							    <img id="avatarPreview" 
							        src="<%=cv.getUngvien().getAvatar()%>" 
							        alt="Avatar" 
							        class="rounded-circle border" 
							        style="width: 200px; height: 200px; object-fit: cover;">
							</div>
							
							    <!-- Tên nằm dưới avatar -->
							    <h2 class="mt-3"><%= cv.getUngvien().getFullName() %></h2>
							</div>
							<!-- Vị trí ứng tuyển -->
	                        <div class="mb-3">
	                            <label class="form-label fw-bold">VỊ TRÍ ỨNG TUYỂN</label>
	                            <input type="text" class="form-control" placeholder="Nhập vị trí ứng tuyển" value="<%= cv.getPosition()%>">
	                        </div>
	                        
	                        <!-- Thông tin cá nhân -->
							<div class="mb-3 personal-info">
						    <label class="form-label fw-bold">THÔNG TIN CÁ NHÂN</label>
						    <!-- Giới tính -->
						    <div class="mb-3">
						        <label for="gender" class="form-label">Giới tính</label>
						        <% if (cv != null) {%>
						        <select id="gender" name="gender" class="form-select" disabled>
						            <option value="<%=cv.getUngvien().getGender() %>" selected><%=cv.getUngvien().getGender() %></option>
						        </select>
						        <%} else { %>
						        <select id="gender" name="gender" class="form-select">
						            <option value="male" selected>Nam</option>
						            <option value="female">Nữ</option>
						            <option value="other">Khác</option>
						        </select>
						        <%} %>
						    </div>
						    <!-- Ngày sinh -->
						    <div class="mb-3">
						        <label for="dob" class="form-label">Ngày sinh</label>
						        <input type="date" id="dob" name="dob" class="form-control" value="<%=cv.getUngvien().getDob() %>" readonly>
						    </div>
						    <!-- Số điện thoại -->
						    <div class="mb-3">
						        <label for="phone" class="form-label">Số điện thoại</label>
						        <input type="tel" id="phone" name="phone" class="form-control" value="<%=cv.getUngvien().getPhone() %>" readonly>
						    </div>
						    <!-- Email -->
						    <div class="mb-3">
						        <label for="email" class="form-label">Email</label>
						        <input type="email" id="email" name="email" class="form-control" value="<%=cv.getUngvien().getEmail()%>" readonly>
						    </div>
						    <!-- Tỉnh thành -->
						    <div class="mb-3">
						        <label for="location" class="form-label">Tỉnh thành</label>
						        <input type="text" id="location" name="location" class="form-control" value="<%=cv.getUngvien().getLocation()%>" readonly>
						    </div>
						    <!-- Địa chỉ -->
						    <div class="mb-3">
						        <label for="address" class="form-label">Địa chỉ</label>
						        <input type="text" id="address" name="address" class="form-control" value="<%=cv.getUngvien().getAddress()%>" readonly>
						    </div>
						    <!-- Giới thiệu bản thân -->
						    <div class="mb-3">
						        <label for="introduction" class="form-label">Giới thiệu bản thân</label>
						        <textarea id="introduction" name="introduction" class="form-control" rows="4" readonly><%=cv.getUngvien().getIntroduction()%> </textarea>
						    </div>
						
						    <!-- Nút Chỉnh sửa -->
						    <div class="text-end">
						        <button type="button" id="editPersonalInfoBtn" class="btn btn-outline-primary ${mode == 'view' ? 'hide-button' : ''}" >
						        	<a href="ThongTinUngVien.jsp" style = "text-decoration: none;"><i class="bi bi-pencil-square"></i> Chỉnh sửa thông tin</a>
						        </button>
						    </div>
						</div>
	            		</div>
	            		<div class="col-md-8">
	            		<!-- Thông tin CV -->
		                    <!-- Mục tiêu nghề nghiệp -->
		                    <div class="mb-3">
		                        <label for="careerGoals" class="form-label fw-bold">MỤC TIÊU</label>
		                        <textarea id="careerGoals" name="careerGoals" class="form-control" rows="3" readonly placeholder="Nhập mục tiêu nghề nghiệp">${cv.getCareerGoals()}</textarea>
		                    </div>
		
		                    <!-- Học vấn -->
							<div class="mb-3">
							    <label class="form-label fw-bold">HỌC VẤN</label>
							    <div id="educationContainer">
							        <!-- Một mục học vấn đầu tiên -->
								<% for (int i = 0; i < cv.getHocVan().size(); i++) { 
								%>
								    <div class="education-item border rounded p-3 mb-2">
								        <div class="row mb-2">
								            <div class="col-md-6">
								                <label class="form-label">Bắt đầu</label>
								                <input type="date" name="educationStart[]" class="form-control" value="<%= cv.getHocVan().get(i).getStart() %>" readonly>
								            </div>
								            <div class="col-md-5">
								                <label class="form-label">Kết thúc</label>
								                <input type="date" name="educationEnd[]" class="form-control me-2 education-end-date" value="<%= cv.getHocVan().get(i).getEnd() %>" readonly>
								            </div>
								        </div>
								        <div class="row mb-2">
								            <div class="col-md-6">
								                <label class="form-label">Tên trường học</label>
								                <input type="text" name="educationSchool[]" class="form-control" 
								                       value="<%= cv.getHocVan().get(i).getSchool()%>" placeholder="Tên trường học" readonly>
								            </div>
								            <div class="col-md-6">
								                <label class="form-label">Ngành học / Môn học</label>
								                <input type="text" name="educationMajor[]" class="form-control" 
								                       value="<%= cv.getHocVan().get(i).getMajor()%>" placeholder="Ngành học / Môn học" readonly>
								            </div>
								        </div>
								        <div class="mb-2">
								            <label class="form-label">Mô tả quá trình học tập hoặc thành tích của bạn</label>
								            <textarea name="educationDescription[]" class="form-control" readonly rows="2" placeholder="Nhập mô tả..."><%= cv.getHocVan().get(i).getDescription() %></textarea>
								        </div>
								    </div>
								
								<% } %>
							    </div>
							</div>
		
		
		                    <!-- Kinh nghiệm làm việc -->
		                    <label class="form-label fw-bold">KINH NGHIỆM</label>
							<div id="experienceContainer" class="mb-4">
							<% for (int i = 0; i < cv.getKinhNghiem().size(); i++) { 
							%>
							
							    <div class="experience-item border rounded p-3 mb-2">
							        <div class="row mb-2">
							            <div class="col-md-6">
							                <label class="form-label">Bắt đầu</label>
							                <input type="date" name="experienceStart[]" class="form-control" value="<%=  cv.getKinhNghiem().get(i).getStart() %>" readonly>
							            </div>
							            <div class="col-md-5">
							                <label class="form-label">Kết thúc</label>
							                <input type="date" name="experienceEnd[]" class="form-control me-2 experience-end-date" value="<%= cv.getKinhNghiem().get(i).getEnd() %>" readonly>
							            </div>
							        </div>
							        <div class="row mb-2">
							            <div class="col-md-6">
							                <label class="form-label">Tên công ty</label>
							                <input type="text" name="experienceCompany[]" class="form-control" value="<%= cv.getKinhNghiem().get(i).getCompany() %>" placeholder="Tên công ty" readonly>
							            </div>
							            <div class="col-md-6">
							                <label class="form-label">Vị trí công việc</label>
							                <input type="text" name="experiencePosition[]" class="form-control" value="<%= cv.getKinhNghiem().get(i).getPosition()  %>" placeholder="Vị trí công việc" readonly>
							            </div>
							        </div>
							        <div class="mb-2">
							            <label class="form-label">Mô tả kinh nghiệm làm việc</label>
							            <textarea name="experienceDescription[]" class="form-control" readonly rows="2" placeholder="Nhập mô tả..."><%= cv.getKinhNghiem().get(i).getDescription()  %></textarea>
							        </div>
							    </div>
							
							<% } %>
							</div>
		
		                    <!-- Chứng chỉ -->
							<div class="mb-3">
							    <label class="form-label fw-bold">CHỨNG CHỈ</label>
							    <div id="certificatesContainer">
							        <!-- Một chứng chỉ đầu tiên -->
							        <% for (int i = 0; i < cv.getChungChi().size(); i++) { 
									%>
							        <div class="input-group mb-2">
								        <input type="text" name="certificates[]" class="form-control" placeholder="Nhập tên chứng chỉ" value="<%= cv.getChungChi().get(i).getName()  %>" readonly>
							        </div>   
							        <%} %>    
							    </div>
							</div>
							
		                    <!-- Kỹ năng -->
							<div class="mb-3">
							    <label class="form-label fw-bold">KỸ NĂNG</label>
							    <div id="skillsContainer">
							        <!-- Một kỹ năng đầu tiên -->
							    <%
				                    if (cv != null) {
				                    	 for (int i = 0; i < cv.getKyNang().size(); i++) {
				                %>
				                    <div class="skill-row d-flex align-items-center mb-2">
				                        <input type="text" name="skills[]" class="form-control me-3" placeholder="Tên kỹ năng" value="<%= cv.getKyNang().get(i).getName() %>" readonly>
				                        <div class="circle-rating d-flex">
				                            <% 
				                                // Hiển thị các vòng tròn tương ứng với mức độ kỹ năng
				                                for (int j = 1; j <= 5; j++) {
				                                    String activeClass = (j <= Integer.parseInt(cv.getKyNang().get(i).getLevel())) ? "active" : "";
				                            %>
				                                <span data-value="<%= j %>" onclick="setCircleRating(this)" class="circle <%= activeClass %>"></span>
				                            <% } %>
				                        </div>
				                        <input type="hidden" name="skillLevels[]" value="<%= cv.getKyNang().get(i).getLevel() %>">
				                    </div>
				                <%      
				                        }
				                    }
				                %>
							    </div>
							</div>
	            		</div>
	            	</div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="js/ThongTinCV.js"></script>
</body>
</html>
