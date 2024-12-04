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
// Lắng nghe sự kiện submit của form thay đổi mật khẩu
const changePasswordForm = document.getElementById("changePasswordForm");
if (changePasswordForm) {
    changePasswordForm.addEventListener("submit", function(event) {
        handleChangePasswordSubmit(event);
    });
}
document.getElementById("saveAllChanges").addEventListener("click", function(event)
{
	event.preventDefault();
	var tenCongTy = document.getElementById("tenCongTy").value;
	var sdt = document.getElementById("phone").value;
	var tinhThanh = document.getElementById("location").value;
	var diaChi = document.getElementById("address").value;
	var maSoThue = document.getElementById("maSoThue").value;
	var linhVuc = document.getElementById("linhVuc").value;
	var quyMoNhanSu = document.getElementById("quyMoNhanSu").value;
	var url = document.getElementById("url").value;
	var gioiThieu = document.getElementById("introduction").value;
	
	
	var dataToSend = {
	    tenCongTy: tenCongTy,
	    sdt: sdt,
	    tinhThanh: tinhThanh,
	    diaChi: diaChi,
	    maSoThue: maSoThue,
	    linhVuc: linhVuc,
	    quyMoNhanSu: quyMoNhanSu,
	    url: url,
	    gioiThieu: gioiThieu,
		avatarSource: avatarSource,
		avatarFileName: avatarFileName,
		imageSources: imageSources,
		fileNames: fileNames
	};
	
	// Gửi yêu cầu POST với fetch
	fetch("TaiKhoanCongTyServlet", {
	    method: "POST",
	    headers: {
	        "Content-Type": "application/json"
	    },
	    body: JSON.stringify(dataToSend) // Chuyển đối tượng thành JSON
	})
	.then(response => {
	    if (response.ok) {
	        return response.json(); // Giả sử server trả về dữ liệu JSON
	    } else {
	        alert(response.text);
	    }
	})
	.then(data => {
	    console.log("Phản hồi từ server:", data);
	    if (data.status === "success") {
	        // Hiển thị thông báo khi thành công
	        alert("Cập nhật thành công!");
	    } else {
	        // Hiển thị thông báo khi có lỗi
	        alert("Lỗi khi cập nhật thông tin.");
	    }
	})
	.catch(error => {
	    console.error("Lỗi khi gửi dữ liệu:", error);
	    alert("Đã xảy ra lỗi khi gửi yêu cầu.");
	});
})