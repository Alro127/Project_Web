<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
</div>