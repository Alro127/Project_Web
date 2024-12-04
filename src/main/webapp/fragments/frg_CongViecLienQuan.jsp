<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<div class="container">
	<div class="row">
		<c:forEach var="congViecLienQuan" items="${congViecLienQuans}"
			begin="0" end="4">
			<div class="col-12 mb-4 py-0">
				<div class="d-flex py-3 px-3 bg-light shadow rounded">
					<img
						src="${congViec.logo}"
						class="card-img-top img-fluid" alt="Công việc"
						style="width: 100px; height: 100px; object-fit: cover;">
					<div class="card-body ms-3">
						<h5 class="card-title">${congViecLienQuan.ten}</h5>
						<p class="card-text">
							<strong>Công ty:</strong> ${congViecLienQuan.tenCongTy} <br> <strong>Lương:</strong>
							${congViecLienQuan.luong} VND <br> <strong>Địa
								điểm:</strong> ${congViecLienQuan.diaDiem}
						</p>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

