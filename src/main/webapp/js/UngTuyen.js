function openCVModal() {
    // Gửi yêu cầu AJAX để lấy danh sách CV
    fetch('QuanLyCVServlet', {
        method: 'GET',
        headers: {
            'X-Requested-With': 'XMLHttpRequest'
        }
    })
    .then(response => response.json())
    .then(data => {
        console.log('Dữ liệu nhận được:', data);  // In ra dữ liệu trả về từ API
        let cvModalBody = document.getElementById('cvModalBody');
        let htmlContent = "";

        // Kiểm tra dữ liệu trả về (data là mảng CV trực tiếp)
        if (Array.isArray(data)) {
            data.forEach(cv => {
                htmlContent += `
                    
                        <div class="col-md-4">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">CV - ${cv.ungvien.fullName}</h5>
                                    <p class="card-text">${cv.position}</p>
									<button type="button" 
										class="btn btn-info btn-sm btn-eye"
										data-bs-toggle="modal" 
										data-bs-target="#cvModal" 
										onclick="loadCVContent(${cv.idCV})">
									  Xem CV
									</button> 
									<a href="UngTuyenServlet?idCV=${cv.idCV}&idCongViec=${idCongViec}" class="btn btn-success btn-sm">
									    <i class="bi bi-check"></i> Chọn
									</a>
                                </div>
                            </div>
						</div>
                    
                `;
            });
        } else {
            htmlContent = "<p>Không có dữ liệu CV nào.</p>";
        }

        cvModalBody.innerHTML = htmlContent;
    })
    .catch(error => {
        console.error('Error fetching CV data:', error);
        let cvModalBody = document.getElementById('cvModalBody');
        cvModalBody.innerHTML = "<p>Đã xảy ra lỗi khi tải dữ liệu.</p>";
    });
}