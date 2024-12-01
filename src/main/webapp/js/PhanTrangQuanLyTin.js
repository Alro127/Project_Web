function loadJobs(page) {
    $.ajax({
        url: 'QuanLyTinDangServlet',  // URL của servlet hoặc API
        method: 'GET',
        data: { page: page, ajax: true },  // Truyền tham số page vào backend
        success: function(response) {
            // Kiểm tra dữ liệu trả về
            console.log("Dữ liệu trả về từ server: ", response);
            if (!response || !response.congViecs || !response.totalPages) {
                alert('Dữ liệu không hợp lệ!');
                return;
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
                        <td><a href="jobDetail?id=${congViec.idCongViec}" class="btn btn-outline-coral btn-sm">Chi tiết</a></td>
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

// Tải trang đầu tiên khi trang được load
$(document).ready(function() {
    loadJobs(1); // Tải dữ liệu cho trang đầu tiên
});
