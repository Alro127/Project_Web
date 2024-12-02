document.getElementById("saveImage").addEventListener("click", function () {
    var fileInput = document.getElementById("imageUpload");
    var file = fileInput.files[0];
    
    if (file) {
        var reader = new FileReader();
        
        reader.onload = function (e) {
            var imageContainer = document.getElementById("image-container"); // Thêm hình vào phần này
            var newImage = document.createElement("div");
            newImage.classList.add("mb-3");
            
            // Tạo thẻ <img> với kích thước giới hạn
            var imgElement = document.createElement("img");
            imgElement.src = e.target.result;
            imgElement.classList.add("img-fluid", "img-thumbnail");
            imgElement.style.maxWidth = "100%";  // Giới hạn chiều rộng tối đa
            imgElement.style.maxHeight = "200px";  // Giới hạn chiều cao tối đa
            imgElement.style.objectFit = "cover";  // Cắt ảnh nếu ảnh không đúng tỷ lệ

            newImage.appendChild(imgElement);
            imageContainer.appendChild(newImage);
            
            var modal = new bootstrap.Modal(document.getElementById('addImageModal'));
            modal.hide(); // Đóng modal sau khi thêm hình
        };
        
        reader.readAsDataURL(file);
    }
});
