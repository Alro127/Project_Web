function loadJobs(page) {
	let linhVuc = $('#linhVucFilter').val();  // Lấy giá trị lĩnh vực
	let thoiGian = $('#thoiGianFilter').val();  // Lấy giá trị thời gian
	let luotXem = $('#luotXemFilter').val();  // Lấy giá trị lượt xem
	let luotNop = $('#luotNopFilter').val();  // Lấy giá trị lượt nộp
	let searchText = $('#search-input').val();
    $.ajax({
        url: 'QuanLyTinDangServlet',  // URL của servlet hoặc API
        method: 'GET',
        data: 
		{ 
			page: page,  // Truyền tham số page
			linhVuc: linhVuc,  // Truyền tham số lọc lĩnh vực
			thoiGian: thoiGian,  // Truyền tham số lọc thời gian
			luotXem: luotXem,  // Truyền tham số lọc lượt xem
			luotNop: luotNop,  // Truyền tham số lọc lượt nộp
			searchText: searchText,
			ajax: true  // Để xác định là AJAX request
		},  
		// Truyền tham số page vào backend
        success: function(response) {
            // Kiểm tra dữ liệu trả về
            console.log("Dữ liệu trả về từ server: ", response);
            if (!response || !response.congViecs || !response.totalPages) {
                /*alert('Dữ liệu không hợp lệ!');
                return;*/
            }

            // In ra thông tin phản hồi để kiểm tra
            console.log(response);  

            // Cập nhật danh sách công việc
            let jobListHtml = '';
            response.congViecs.forEach(function(congViec) {
				let maxTitleLength = 30;  
				let jobTitle = congViec.ten;
				if (jobTitle.length > maxTitleLength) {
				    jobTitle = jobTitle.substring(0, maxTitleLength) + '...';  // Cắt và thêm ba chấm
				}
                let thoiGianDang = new Date(congViec.thoiGianDang).toLocaleDateString('vi-VN');
                let thoiGianHetHan = new Date(congViec.thoiGianHetHan).toLocaleDateString('vi-VN');

                jobListHtml += `
                    <tr>
                        <td> <strong>${jobTitle}</strong><br>Mã công việc: ${congViec.idCongViec}</td>
                        <td class="text-muted">${congViec.linhVuc}</td>
                        <td class="text-muted">${thoiGianDang}</td>
                        <td class="text-muted">${thoiGianHetHan}</td>
                        <td class="text-muted">${congViec.luotNop}</td>
                        <td class="text-muted">${congViec.luotXem}</td>
                        <td><button type="button" class="btn btn-outline-coral btn-sm" onclick="showJobDetail(${congViec.idCongViec})">Chi tiết</button></td>
                    </tr>
                `;
            });

            $('#job-list').fadeOut(300, function() {
                $(this).html(jobListHtml).fadeIn(500);
            });

            let totalPages = response.totalPages; 
            let paginationHtml = '';
            for (let i = 1; i <= totalPages; i++) {
                paginationHtml += `<a href="javascript:void(0);" onclick="loadJobs(${i})" class="btn btn-primary ${i == response.currentPage ? 'active' : ''}">${i}</a>`;
            }
            $('#pagination').fadeOut(300, function() {
                $(this).html(paginationHtml).fadeIn(500);
            });
			
        },
        error: function(xhr, status, error) {
            console.error("Error details:", status, error);  // In ra chi tiết lỗi
            alert('Lỗi tải dữ liệu nè!');
        }
    });
}

function showJobDetail(idCongViec) {
    $.ajax({
        url: 'ChiTietCongViecServlet',  // Đường dẫn servlet
        method: 'GET',
        data: { id: idCongViec, ajax: true },  // Gửi ID công việc
        success: function(response) {
            if (!response) {
                alert('Không thể tải dữ liệu chi tiết!');
                return;
            }

            try {
                // Hiển thị dữ liệu vào modal với kiểm tra null/undefined
                $('#job-name').text(response.congViec.ten || 'Không có tên công việc');
                $('#job-location').text(response.congViec.diaDiem || 'Không có địa điểm');
                $('#job-salary').text(
                    response.congViec.luong !== undefined && response.congViec.luong !== null
                        ? response.congViec.luong.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })
                        : 'Không có lương'
                );
                $('#job-experience').text(
                    response.congViec.namKinhNghiem !== undefined && response.congViec.namKinhNghiem !== null
                        ? response.congViec.namKinhNghiem + ' năm'
                        : 'Không yêu cầu kinh nghiệm'
                );
                $('#job-field').text(response.congViec.linhVuc || 'Không có lĩnh vực');
                $('#job-post-time').text(
                    response.congViec.thoiGianDang
                        ? new Date(response.congViec.thoiGianDang).toLocaleDateString('vi-VN')
                        : 'Không rõ'
                );
                $('#job-expiry-time').text(
                    response.congViec.thoiGianHetHan
                        ? new Date(response.congViec.thoiGianHetHan).toLocaleDateString('vi-VN')
                        : 'Không rõ'
                );
                $('#job-description').text(response.congViec.moTa || 'Không có mô tả');
                $('#job-requirements').text(response.congViec.yeuCau || 'Không có yêu cầu');
                $('#job-benefits').text(response.congViec.quyenLoi || 'Không có quyền lợi');
                $('#job-views').text(response.congViec.luotXem !== undefined ? response.congViec.luotXem : '0');
                $('#job-applications').text(response.congViec.luotNop !== undefined ? response.congViec.luotNop : '0');

                // Mở modal
                $('#jobDetailModal').modal('show');
            } catch (error) {
                console.error("Lỗi khi xử lý dữ liệu: ", error);
                alert('Dữ liệu trả về không hợp lệ!');
            }
        },
        error: function(xhr, status, error) {
            console.error("Error details:", status, error);
            alert('Lỗi tải chi tiết công việc!');
        }
    });
}

// Tải trang đầu tiên khi trang được load
$(document).ready(function() {
	$('#linhVucFilter, #thoiGianFilter, #luotXemFilter, #luotNopFilter').change(function() {
	   loadJobs(1);  // Tải lại các công việc với các filter mới
	});

	// Lắng nghe sự kiện thay đổi của input tìm kiếm
	$('#search-input').keyup(function() {
	       loadJobs(1);  // Tải lại các công việc với từ khóa tìm kiếm mới
	});
	
    loadJobs(1); // Tải dữ liệu cho trang đầu tiên
});
