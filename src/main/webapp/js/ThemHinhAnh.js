document.getElementById("saveImage").addEventListener("click", function () {
    var fileInput = document.getElementById("imageUpload");
    var file = fileInput.files[0];
    
    console.log("file input" + fileInput);
    console.log("file" + file);
    
    if (file) {
        var reader = new FileReader();
        
        reader.onload = function (e) {
            var srcData = e.target.result; // URL ảnh (Base64)
            var fileName = file.name; // Lấy tên file

            // Gửi dữ liệu qua AJAX
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
                console.log("Phản hồi từ server:", data);
                
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
            })
            .catch(error => {
                console.error("Lỗi:", error);
                alert("Không thể lưu hình ảnh!");
            });
        };

        reader.readAsDataURL(file);
    }
});
