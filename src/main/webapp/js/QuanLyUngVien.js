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
document.getElementById("saveAllCandidateChanges").addEventListener("click", function () {
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
