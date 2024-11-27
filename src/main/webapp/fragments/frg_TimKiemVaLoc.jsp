<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body class="bg-light-grey">
	<div class="container mt-3">
		<div class="row">
			<div class="col-md-4 mb-3">
				<input type="text" class="form-control" placeholder="Tìm kiếm..." />
			</div>

			<div class="col-md-3 mb-3">
				<select class="form-select">
					<option value="">Chọn nghề nghiệp</option>
					<option value="dev">Lập trình viên</option>
					<option value="design">Thiết kế</option>
					<option value="manager">Quản lý</option>
				</select>
			</div>

			<div class="col-md-3 mb-3">
				<select class="form-select">
					<option value="">Chọn tỉnh thành</option>
					<option value="hn">Hà Nội</option>
					<option value="hcm">TP. Hồ Chí Minh</option>
					<option value="dn">Đà Nẵng</option>
				</select>
			</div>

			<div class="col-md-2 mb-3">
				<button class="btn bg-coral text-light w-100">Tìm kiếm</button>
			</div>
		</div>
	</div>


</body>
</html>