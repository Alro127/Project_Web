<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<style>
.noUi-connect {
	background-color: #ff8160;
}

.noUi-origin {
	box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
}
</style>
<div class="container mt-3">
	<div class="row">
		<div class="col-md-4 mb-3">
			<input type="text" id="tenFilter" class="form-control"
				placeholder="Tìm kiếm..." />
		</div>
		<div class="col-md-3 mb-3">
			<select id="linhVucFilter" class="form-select">
				<option value="">Chọn lĩnh vực</option>
				<c:forEach var="linhVuc" items="${linhVucs}">
					<option value="${linhVuc}">${linhVuc}</option>
				</c:forEach>
			</select>
		</div>

		<div class="col-md-3 mb-3">
			<select id="tinhThanhFilter" class="form-select">
				<option value="">Chọn tỉnh thành</option>
				<c:forEach var="tinhThanh" items="${tinhThanhs}">
					<option value="${tinhThanh}">${tinhThanh}</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-md-2 mb-3">
			<button id="searchBtn" class="btn bg-coral text-light w-100">Tìm
				kiếm</button>
		</div>
	</div>
	<div class="row">
		<div class="col-md-5 mb-3 ">
			<label for="kinhNghiemFilter">Chọn năm kinh nghiệm: <span
				id="kinhNghiem"> 1</span> năm
			</label>
			<!-- Thêm thẻ input cho slider -->
			<div>
				<input id="kinhNghiemFilter" type="range" min="0" max="10" step="1"
					data-slider-value="[0,10]" />
			</div>

		</div>
		<div class="col-md-5 mb-3">
			<div class="form-control">
				<label for="luongFilter">Chọn khoảng lương: <span id="luong"></span>
					<!-- Hiển thị giá trị tại đây --></label>
				<div class="m-3" id="luongFilter"></div>
			</div>
		</div>
	</div>
</div>
<script>
    var minLuong = ${minLuong};
    var maxLuong = ${maxLuong};
</script>
