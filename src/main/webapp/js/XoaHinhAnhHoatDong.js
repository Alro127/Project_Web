document.addEventListener("DOMContentLoaded", function () {
    const imageContainer = document.getElementById("image-container");
    const modal = new bootstrap.Modal(document.getElementById("delete-modal"));
    let imageToDelete = null; // Biến lưu trữ ảnh cần xóa

    // Lắng nghe sự kiện click trên #image-container
    imageContainer.addEventListener("click", function (event) {
        if (event.target.tagName.toLowerCase() === "img") {
            // Đảm bảo chỉ xử lý khi nhấn vào ảnh
            imageToDelete = event.target; // Lưu ảnh cần xóa
            modal.show(); // Hiển thị modal khi nhấn vào ảnh
        }
    });

    // Lắng nghe sự kiện "Xóa" trong modal
    document.getElementById("confirm-delete").addEventListener("click", function () {
        if (imageToDelete) {
            let fileName = null;
            
            // Kiểm tra nếu là ảnh mới (Base64) hay ảnh cũ (URL)
            if (imageToDelete.src.startsWith('data:image')) {
                // Ảnh mới (Base64), không cần xóa trong CSDL, chỉ cần xóa trên giao diện
                imageToDelete.parentElement.remove(); // Xóa phần tử ảnh khỏi giao diện
                modal.hide(); // Đóng modal
                alert('Ảnh mới đã được xóa.');
            } else {
                // Ảnh cũ, lấy tên file từ data-file-name
                fileName = imageToDelete.getAttribute('data-file-name');
                
                // Gửi yêu cầu xóa ảnh tới servlet A
                fetch('XoaHinhAnhHoatDongServlet', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ fileName: fileName }) // Gửi tên file ảnh lên servlet
                })
                .then(response => response.json()) // Dự đoán servlet trả về JSON
                .then(data => {
                    if (data.success) {
                        // Xóa ảnh khỏi giao diện sau khi thành công
                        imageToDelete.parentElement.remove(); // Xóa phần tử ảnh
                        modal.hide(); // Đóng modal
                        alert(data.message);
                    } else {
                        alert(data.message);
                    }
                })
                .catch(error => {
                    console.error('Lỗi:', error);
                    alert('Có lỗi xảy ra khi xóa ảnh.');
                });
            }
        }
    });

    // Lắng nghe sự kiện "Hủy" trong modal
    document.querySelectorAll("[data-bs-dismiss='modal']").forEach(function (dismissButton) {
        dismissButton.addEventListener("click", function () {
            modal.hide(); // Đóng modal khi nhấn Hủy hoặc nút "X"
        });
    });
});
