// Hàm tạo form Đổi Mật Khẩu
function createChangePasswordForm() {
    const container = document.getElementById('changePasswordForm');

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
	
	const csrfToken = document.querySelector("meta[name='csrf-token']").content;

    // Gửi yêu cầu AJAX tới Servlet để thay đổi mật khẩu
    fetch('ChangePasswordServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
			'X-CSRF-TOKEN': csrfToken
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
				if (data.newToken) {
		              document.querySelector("meta[name='csrf-token']").setAttribute("content", data.newToken);
                }
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
// Lắng nghe sự kiện submit của form thay đổi mật khẩu
const changePasswordForm = document.getElementById("changePasswordForm");
if (changePasswordForm) {
    changePasswordForm.addEventListener("submit", function(event) {
        handleChangePasswordSubmit(event);
    });
}