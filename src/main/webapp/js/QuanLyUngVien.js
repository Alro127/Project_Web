var avatarSource;
var avatarFileName;
document.getElementById("saveAvatarImage").addEventListener("click", function()
{
		var fileInput = document.getElementById("imageAvatarUpload");
	    var file = fileInput.files[0]; // Lấy thằng đầu tiên
		console.log("file input" + fileInput);
		console.log("file" + file);
		if (file) {
			var reader = new FileReader();
			reader.onload = function(e)
			{
				var srcData = e.target.result; // URL ảnh (Base64)
				var fileName = file.name; // Lấy tên file
				avatarSource = srcData;
				avatarFileName = fileName;
				var avtImage = document.getElementById("avatarPreview");
				avtImage.src = srcData;
				// Đóng modal
				 var modal = new bootstrap.Modal(document.getElementById('addAvatarImageModal'));
				 modal.hide();
			}
			reader.readAsDataURL(file);
		}
			
});
document.getElementById("saveAllCandidateChanges").addEventListener("click", function (event) {
	event.preventDefault(); // Ngừng hành động gửi form mặc định
    var formData = {
        fullname: document.getElementById("fullname").value,
        gender: document.getElementById("gender").value,
        dob: document.getElementById("dob").value,
        phone: document.getElementById("phone").value,
        location: document.getElementById("location").value,
        address: document.getElementById("address").value,
        introduction: document.getElementById("introduction").value,
		avatarSource: avatarSource,
		avatarFileName: avatarFileName
    };
	// In ra thông tin từ formData để kiểm tra
	   console.log("Full Name: ", formData.fullname);
	   console.log("Gender: ", formData.gender);
	   console.log("Date of Birth: ", formData.dob);
	   console.log("Phone: ", formData.phone);
	   console.log("Location: ", formData.location);
	   console.log("Address: ", formData.address);
	   console.log("Introduction: ", formData.introduction);
		
	   // Kiểm tra phone (số điện thoại hợp lệ, chứa ít nhất 10 chữ số)
	   	       var phoneRegex = /^\d{10,11}$/;
	   	       if (!phoneRegex.test(formData.phone)) {
	   	           alert("Số điện thoại không hợp lệ. Vui lòng nhập lại.");
	   	           return;
	   	       }
	   // Kiểm tra tất cả các trường có hợp lệ hay không
	   if (formData.fullname === "" || formData.gender === "" || formData.dob === "" || formData.phone === "" || formData.location === "" || formData.address === "" || formData.introduction === "") {
	       alert("Vui lòng điền đầy đủ thông tin.");
	       return; // Dừng nếu có trường bị thiếu
	   }
	   
    fetch("QuanLyTaiKhoanServlet", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert("Thông tin cá nhân đã được cập nhật.");
        } else {
            alert("Có lỗi xảy ra, vui lòng thử lại.");
        }
    })
    .catch(error => console.error("Error:", error));
});
