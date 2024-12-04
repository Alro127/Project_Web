function saveData() {
    const form = document.getElementById('cvForm');
    const formData = new FormData(form);
	const mode = document.getElementById('mode'); 

    // Xử lý thông tin cá nhân (Ảnh đại diện)
    const avatar = document.getElementById('avatarPreview').src;
    if (avatar) {
        formData.append('avatar', avatar);
    }

    // Lấy dữ liệu từ form
	const position = document.getElementById('position').value;
    const careerGoals = document.getElementById('careerGoals').value;

    const educationStartDates = document.getElementsByName('educationStart[]');
    const educationEndDates = document.getElementsByName('educationEnd[]');
    const educationSchools = document.getElementsByName('educationSchool[]');
    const educationMajors = document.getElementsByName('educationMajor[]');
    const educationDescriptions = document.getElementsByName('educationDescription[]');

    const educationData = [];
    for (let i = 0; i < educationStartDates.length; i++) {
        educationData.push({
            start: educationStartDates[i].value,
            end: educationEndDates[i].value,
            school: educationSchools[i].value,
            major: educationMajors[i].value,
            description: educationDescriptions[i].value
        });
    }

    const experienceStartDates = document.getElementsByName('experienceStart[]');
    const experienceEndDates = document.getElementsByName('experienceEnd[]');
    const experienceCompanies = document.getElementsByName('experienceCompany[]');
    const experiencePositions = document.getElementsByName('experiencePosition[]');
    const experienceDescriptions = document.getElementsByName('experienceDescription[]');

    const experienceData = [];
    for (let i = 0; i < experienceStartDates.length; i++) {
        experienceData.push({
            start: experienceStartDates[i].value,
            end: experienceEndDates[i].value,
            company: experienceCompanies[i].value,
            position: experiencePositions[i].value,
            description: experienceDescriptions[i].value
        });
    }

    const certificates = document.getElementsByName('certificates[]');
    const certificateData = [];
    for (let i = 0; i < certificates.length; i++) {
        certificateData.push({ name: certificates[i].value});
    }

    const skills = document.getElementsByName('skills[]');
    const skillLevels = document.getElementsByName('skillLevels[]');
    const skillData = [];
    for (let i = 0; i < skills.length; i++) {
        skillData.push({
            name: skills[i].value,
            level: skillLevels[i].value
        });
    }

    // Tạo đối tượng JSON để gửi
    const dataToSend = {
		position: position,
        careerGoals: careerGoals,
        educationData: educationData,
        experienceData: experienceData,
        certificateData: certificateData,
        skillData: skillData,
		mode: mode.value
    };

    // Chuyển đối tượng JSON thành chuỗi
    const jsonData = JSON.stringify(dataToSend);

    // Gửi dữ liệu JSON đến máy chủ
    const xhr = new XMLHttpRequest();
    xhr.open('POST', 'SaveCVServlet', true);
    xhr.setRequestHeader('Content-Type', 'application/json');  // Cần đặt header đúng kiểu
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            alert('Gửi CV thành công rồi nhé');
        }
    };
    xhr.send(jsonData);  // Gửi dữ liệu JSON
}




//Avatar
// Kích hoạt cửa sổ chọn tệp
function triggerAvatarUpload() {
	document.getElementById("avatarUpload").click();
}

// Hiển thị ảnh được chọn và thay thế Avatar
function previewAvatar(event) {
	const file = event.target.files[0];

	if (file) {
		// Kiểm tra định dạng file (chỉ chấp nhận ảnh)
		if (file.type.startsWith("image/")) {
			const reader = new FileReader();
			reader.onload = function(e) {
				document.getElementById("avatarPreview").src = e.target.result; // Thay thế Avatar
			};
			reader.readAsDataURL(file);
		} else {
			alert("Vui lòng chọn một tệp hình ảnh hợp lệ (JPEG, PNG, hoặc GIF).");
		}
	}
}
// Gửi ảnh đã chọn lên server
function uploadAvatarToServer() {
	const fileInput = document.getElementById("avatarUpload");
	const file = fileInput.files[0];

	if (file) {
		// Tạo FormData và thêm file
		const formData = new FormData();
		formData.append("avatar", file);

		// Gửi file qua AJAX
		fetch("uploadAvatar", {
			method: "POST",
			body: formData,
		})
			.then((response) => response.json())
			.then((data) => {
				if (data.success) {
					alert("Ảnh đã được lưu thành công!");
				} else {
					alert("Lỗi khi lưu ảnh: " + data.message);
				}
			})
			.catch((error) => {
				console.error("Lỗi:", error);
				alert("Đã xảy ra lỗi trong quá trình upload.");
			});
	} else {
		alert("Vui lòng chọn một ảnh trước khi lưu!");
	}
}


//Chứng chỉ
function addCertificateRow() {
	const container = document.getElementById("certificatesContainer");

	// Tạo một dòng mới
	const newRow = document.createElement("div");
	newRow.classList.add("input-group", "mb-2");

	newRow.innerHTML = `
        <input type="text" name="certificates[]" class="form-control" placeholder="Nhập tên chứng chỉ">
        <button type="button" class="btn btn-outline-danger btn-sm" onclick="removeCertificateRow(this)">
            <i class="bi bi-x-circle"></i>
        </button>
    `;

	// Thêm dòng vào container
	container.appendChild(newRow);
}

function removeCertificateRow(button) {
	const row = button.parentElement;
	row.remove(); // Xóa dòng chứng chỉ
}
// Kỹ năng
// Thiết lập mức đánh giá kỹ năng với vòng tròn
function setCircleRating(element) {
	const level = element.getAttribute("data-value");
	const siblings = element.parentElement.querySelectorAll(".circle");

	// Đặt trạng thái active cho các vòng tròn phù hợp
	siblings.forEach((circle) => {
		if (circle.getAttribute("data-value") <= level) {
			circle.classList.add("active");
		} else {
			circle.classList.remove("active");
		}
	});

	// Cập nhật giá trị hidden input
	const hiddenInput = element.parentElement.nextElementSibling;
	hiddenInput.value = level;
}

// Thêm dòng kỹ năng mới
function addSkillRow() {
	const container = document.getElementById("skillsContainer");

	const newRow = document.createElement("div");
	newRow.classList.add("skill-row", "d-flex", "align-items-center", "mb-2");

	newRow.innerHTML = `
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
    `;

	container.appendChild(newRow);
}

// Xóa dòng kỹ năng
function removeSkillRow(button) {
	const row = button.parentElement;
	row.remove();
}

// Học vấn
// Thêm một mục học vấn mới
function addEducationItem() {
	const container = document.getElementById("educationContainer");

	const newItem = document.createElement("div");
	newItem.classList.add("education-item", "border", "rounded", "p-3", "mb-2");

	newItem.innerHTML = `
	<!-- Một mục học vấn đầu tiên -->
        <div class="education-item border rounded p-3 mb-2">
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
    `;

	container.appendChild(newItem);
}


// Xóa một mục học vấn
function removeEducationItem(button) {
	const item = button.parentElement;
	item.remove();
}
function toggleEndDate(checkbox) {
	const dateInput = checkbox.closest('.d-flex').querySelector('.education-end-date');
	if (checkbox.checked) {
		dateInput.value = ""; // Xóa giá trị nếu có
		dateInput.disabled = true; // Ẩn trường nhập
	} else {
		dateInput.disabled = false; // Hiển thị lại trường nhập
	}
}
//Kinh nghiệm làm việc
// Ẩn/hiện trường "Ngày kết thúc"
function toggleExperienceEndDate(checkbox) {
	const dateInput = checkbox.closest('.d-flex').querySelector('.experience-end-date');
	if (checkbox.checked) {
		dateInput.value = ""; // Xóa giá trị nếu có
		dateInput.disabled = true; // Ẩn trường nhập
	} else {
		dateInput.disabled = false; // Hiển thị lại trường nhập
	}
}

// Thêm mới mục kinh nghiệm làm việc
function addExperienceItem() {
	const container = document.getElementById("experienceContainer");

	const newItem = document.createElement("div");
	newItem.classList.add("experience-item", "border", "rounded", "p-3", "mb-2");

	newItem.innerHTML = `
	    <div class="row mb-2">
	    	<div class="col-md-6">
	            <label class="form-label">Bắt đầu</label>
	            <input type="date" name="experienceStart[]" class="form-control">
	        </div>
	        <div class="col-md-5">
	            <label class="form-label">Kết thúc</label>
	            <input type="date" name="experienceEnd[]" class="form-control me-2 experience-end-date">
	        </div>
			<div class="col-md-1 d-flex flex-column justify-content-end px-0">
				<div class="form-check d-flex justify-content-center align-items-center mb-3">
					<input type="checkbox"
						class="form-check-input experience-current-checkbox"
						onchange="toggleExperienceEndDate(this)"> <label
						class="form-check-label">Hiện tại</label>
				</div>
			</div>
	    </div>
	    <div class="row mb-2">
	        <div class="col-md-6">
	            <label class="form-label">Tên công ty</label>
	            <input type="text" name="experienceCompany[]" class="form-control" placeholder="Tên công ty">
	        </div>
	        <div class="col-md-6">
	            <label class="form-label">Vị trí công việc</label>
	            <input type="text" name="experiencePosition[]" class="form-control" placeholder="Vị trí công việc">
	        </div>
	    </div>
	    <div class="mb-2">
	        <label class="form-label">Mô tả kinh nghiệm làm việc</label>
	        <textarea name="experienceDescription[]" class="form-control" rows="2" placeholder="Nhập mô tả..."></textarea>
	    </div>
	    <button type="button" class="btn btn-outline-danger btn-sm" onclick="removeExperienceItem(this)">
	        <i class="bi bi-x-circle"></i> Xóa
	    </button>
    `;

	container.appendChild(newItem);
}

// Xóa mục kinh nghiệm làm việc
function removeExperienceItem(button) {
	const item = button.closest('.experience-item');
	item.remove();
}

