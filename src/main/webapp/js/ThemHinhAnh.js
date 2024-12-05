var imageSources = []; // Mảng lưu trữ dữ liệu Base64
var fileNames = []; // Mảng lưu trữ tên tệp ảnh
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
document.getElementById("saveImage").addEventListener("click", function () {
	// Đây là cái modal ở đầu trang khi hiện lên
	var fileInput = document.getElementById("imageUpload");
    var file = fileInput.files[0]; // Lấy thằng đầu tiên
    
    console.log("file input" + fileInput);
    console.log("file" + file);
    
    if (file) { // Nếu có file
        var reader = new FileReader();
        
        reader.onload = function (e) { // Sau khi đọc xong
            var srcData = e.target.result; // URL ảnh (Base64)
            var fileName = file.name; // Lấy tên file
			imageSources.push(srcData);
			fileNames.push(fileName);

            /*// Gửi dữ liệu qua AJAX
            fetch("TaiKhoanCongTyServlet", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ 
                    imageSrc: srcData,  // Dữ liệu Base64 của ảnh
                    fileName: fileName  // Tên file
                }) // Đóng gói dữ liệu JSON
            })
            .then(response => {
                if (response.ok) {
                    return response.json(); // Giả định servlet trả JSON phản hồi
                } else {
                    throw new Error("Có lỗi xảy ra khi gửi dữ liệu!");
                }
            })
            .then(data => {
                console.log("Phản hồi từ server:", data);*/
                
                // Hiển thị ảnh trên giao diện
                var imageContainer = document.getElementById("image-container");
                var newImage = document.createElement("div");
                newImage.classList.add("mb-3");
                
                var imgElement = document.createElement("img");
                imgElement.src = srcData;
                imgElement.classList.add("img-fluid", "img-thumbnail");
                imgElement.style.maxWidth = "100%";
                imgElement.style.maxHeight = "200px";
                imgElement.style.objectFit = "cover";

                newImage.appendChild(imgElement);
                imageContainer.appendChild(newImage);

                // Đóng modal
                var modal = new bootstrap.Modal(document.getElementById('addImageModal'));
                modal.hide();
           /* })
            .catch(error => {
                console.error("Lỗi:", error);
                alert("Không thể lưu hình ảnh!");
            });
        };*/
	}
        reader.readAsDataURL(file); // Đọc file để kích hoạt hàm
    }
});