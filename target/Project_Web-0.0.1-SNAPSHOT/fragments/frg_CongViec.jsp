<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="https://jakarta.ee/jstl/core" %>

<!-- <!DOCTYPE html>
<div class="bg-white rounded p-3 shadow-sm mt-4 mb-4">
	<div class="row">
		<div class="col-md-4 mb-4">
			<div class="card">
				Icon yêu thích
				<div class="position-absolute top-0 end-0 m-2">
					<i class="fas fa-heart text-danger"></i>
				</div>
				Ảnh công ty
				<img src="anh.jpg" class="card-img-top img-fluid" alt="Marketing">
				Nội dung thẻ
				<div class="card-body">
					<h5 class="card-title">Tên công việc</h5>
					<p class="card-text">
						<strong>Công ty:</strong> Tên công ty <br> <strong>Lương:</strong>
						10,000,000 VND <br> <strong>Địa điểm:</strong> Hà Nội
					</p>
				</div>
			</div>
		</div>
	</div>
</div>  -->
<c:choose>
	<c:when test="${not empty congViecs}">
		<c:forEach var="job" items="${congViecs}">
			<div class="col-md-4 mb-4">
				<div class="card">
					<img src="anh.jpg" class="card-img-top img-fluid" alt="Công việc">
					<div class="card-body">
						<h5 class="card-title">${job.ten}</h5>
						<p class="card-text">
							<strong>Công ty:</strong> ${job.idCT} <br> <strong>Lương:</strong>
							${job.luong} VND <br> <strong>Địa điểm:</strong>
							${job.diaDiem}
						</p>
					</div>
				</div>
			</div>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<p>Không có công việc nào để hiển thị.</p>
	</c:otherwise>
</c:choose>
