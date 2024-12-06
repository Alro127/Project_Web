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
	
	// In tất cả giá trị ra console để kiểm tra
	    console.log("Tên công ty: ", tenCongTy);
	    console.log("Số điện thoại: ", sdt);
	    console.log("Tỉnh thành: ", tinhThanh);
	    console.log("Địa chỉ: ", diaChi);
	    console.log("Mã số thuế: ", maSoThue);
	    console.log("Lĩnh vực: ", linhVuc);
	    console.log("Quy mô nhân sự: ", quyMoNhanSu);
	    console.log("URL: ", url);
	    console.log("Giới thiệu: ", gioiThieu);
		var phoneRegex = /^\d{10,11}$/;
				    if (!phoneRegex.test(sdt)) {
				        alert("Số điện thoại không hợp lệ. Vui lòng nhập lại.");
				        return; // Dừng nếu số điện thoại không hợp lệ
				    }

	    // Kiểm tra tất cả các trường có hợp lệ hay không
	    if (tenCongTy === "" || sdt === "" || tinhThanh === "" || diaChi === "" || maSoThue === "" || linhVuc === "" || quyMoNhanSu === "" || url === "" || gioiThieu === "") {
	        alert("Vui lòng điền đầy đủ thông tin.");
	        return; // Dừng nếu có trường bị thiếu
	    }
		
		   
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
		fileNames: fileNames,
		backGroundSource: backGroundSource,
		backGroundFileName: backGroundFileName
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
