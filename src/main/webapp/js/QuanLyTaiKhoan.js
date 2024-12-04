// Hàm tạo form Đổi Mật Khẩu
function createChangePasswordForm() {
    const container = document.getElementById('changePasswordForm');
    
    // Kiểm tra nếu phần Đổi Mật Khẩu đã tồn tại và hiển thị
    if (container.innerHTML === "") {
        // Tạo và thêm form Đổi Mật Khẩu nếu chưa có
        const newItem = document.createElement("div");

        newItem.innerHTML = `
        <div class="card">
            <div class="card-header">
                Thay đổi mật khẩu
            </div>
            <div class="card-body">
                <form id="changePasswordFormId">
                    <div class="mb-3">
                        <label for="oldPassword" class="form-label">Mật khẩu cũ</label>
                        <input type="password" id="oldPassword" name="oldPassword" class="form-control" placeholder="Nhập mật khẩu cũ" required />
                    </div>
                    <div class="mb-3">
                        <label for="newPassword" class="form-label">Mật khẩu mới</label>
                        <input type="password" id="newPassword" name="newPassword" class="form-control" placeholder="Nhập mật khẩu mới" required />
                    </div>
                    <div class="mb-3">
                        <label for="confirmPassword" class="form-label">Xác nhận mật khẩu mới</label>
                        <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" placeholder="Nhập lại mật khẩu mới" required />
                    </div>
                    <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                </form>
            </div>
        </div>
        `;
        
        container.appendChild(newItem);
    }

    // Toggle hiển thị phần Đổi Mật Khẩu
    if (container.style.display === "none" || container.style.display === "") {
        container.style.display = "block"; // Hiển thị form
    } else {
        container.style.display = "none"; // Ẩn form
    }	
}

// Hàm xử lý sự kiện submit đổi mật khẩu
function handleChangePasswordSubmit(event) {
    event.preventDefault(); // Ngừng form gửi đi (để xử lý thủ công)

    const oldPassword = document.getElementById("oldPassword").value;
    const newPassword = document.getElementById("newPassword").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    const username = document.getElementById("username").value; 
    
    // Kiểm tra mật khẩu xác nhận
    if (newPassword !== confirmPassword) {
        alert("Mật khẩu mới và mật khẩu xác nhận không khớp.");
        return;
    }

    // Gửi yêu cầu AJAX tới Servlet để thay đổi mật khẩu
    fetch('ChangePasswordServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `oldPassword=${encodeURIComponent(oldPassword)}&newPassword=${encodeURIComponent(newPassword)}&username=${encodeURIComponent(username)}`
    })
    .then(response => {
        if (response.ok) {
            return response.json();  // Chỉ parse JSON nếu phản hồi hợp lệ
        } else {
            return response.text();  // Nếu không phải JSON, trả về text (HTML)
        }
    })
    .then(data => {
        if (typeof data === 'string') {
            console.error("Phản hồi từ server là HTML, không phải JSON:", data);
            alert("Có lỗi xảy ra khi thay đổi mật khẩu.");
        } else {
            if (data.status === "success") {
                alert(data.message);
            } else {
                alert(data.message);
            }
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert("Đã có lỗi xảy ra trong quá trình thay đổi mật khẩu.");
    });
}
// Lắng nghe sự kiện click vào nút Toggle để mở form đổi mật khẩu
document.getElementById('toggleChangePassword').addEventListener('click', function(event) {
    event.preventDefault();  
    createChangePasswordForm();
});
document.getElementById("changePasswordFormId")?.addEventListener("submit", handleChangePasswordSubmit);
