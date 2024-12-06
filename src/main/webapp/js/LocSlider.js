const events = ['mousemove', 'touchmove'];
var kinhNghiem;
var luongKhoiDiemHienTai;
var LuongKetThucHienTai;
// Khởi tạo noUiSlider khi tài liệu sẵn sàng
document.addEventListener('DOMContentLoaded', function () {
	luongKhoiDiemHienTai = minLuong;
	LuongKetThucHienTai = maxLuong;
	
	// Gán giá trị mặc định là 0
	$('#kinhNghiemFilter').val(0);
	$('#kinhNghiem').text($('#kinhNghiemFilter').val());
	$.each(events, function(k,v) {
	  $('#kinhNghiemFilter').on(v, function() {
	    $('#kinhNghiem').text($('#kinhNghiemFilter').val());
		kinhNghiem = $('#kinhNghiemFilter').val();
		console.log(kinhNghiem);
	  });
	})
	
    var priceRangeSlider = document.getElementById('luongFilter');

    // Khởi tạo noUiSlider
    noUiSlider.create(priceRangeSlider, {
        start: [minLuong, maxLuong],    // Giá trị bắt đầu (min và max)
        connect: true,        // Kết nối 2 tay cầm (tạo vùng giữa)
        range: {
            'min': minLuong,          // Giá trị min
            'max': maxLuong        // Giá trị max
        },
        step: 500000,             // Bước nhảy mỗi lần
        //tooltips: true,       // Hiển thị tooltip
        format: {
            to: function (value) {
                return Math.round(value); // Làm tròn giá trị
            },
            from: function (value) {
                return value;
            }
        }
    });

    // Cập nhật giá trị khi thanh trượt thay đổi
    priceRangeSlider.noUiSlider.on('update', function (values, handle) {
		luongKhoiDiemHienTai = values[0];
		LuongKetThucHienTai = values[1];
		console.log(luongKhoiDiemHienTai);
		console.log(LuongKetThucHienTai);
        var value = values.join(' - ');
        document.getElementById("luong").textContent = value; // Cập nhật hiển thị giá trị
    });
});
