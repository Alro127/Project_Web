<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div class="container mt-4">
	<div class="row">
		<!-- Công nghệ thông tin -->
		<div class="col-4 col-md-2 mb-4">
			<a href="#" class="text-decoration-none text-dark">
				<div class="card text-center">
					<video class="card-img-top img-fluid py-2 px-5" preload="none"
						autoplay loop muted playsinline>
						<source src="https://cdn-icons-mp4.flaticon.com/512/13470/13470976.mp4" type="video/mp4">
					</video>
					<div class="card-body">
						<h6 class="card-title">Công nghệ thông tin</h6>
						<p class="card-text">Công việc: ${jobCountsByField["Công nghệ thông tin"]}</p>
					</div>
				</div>
			</a>
		</div>

		<!-- Xây dựng -->
		<div class="col-4 col-md-2 mb-4">
			<a href="#" class="text-decoration-none text-dark">
				<div class="card text-center">
					<video class="card-img-top img-fluid py-2 px-5" preload="none"
						autoplay loop muted playsinline>
						<source src="https://cdn-icons-mp4.flaticon.com/512/13470/13470919.mp4" type="video/mp4">
					</video>
					<div class="card-body">
						<h6 class="card-title">Xây dựng</h6>
						<p class="card-text">Công việc: ${jobCountsByField["Xây dựng"]}</p>
					</div>
				</div>
			</a>
		</div>

		<!-- Logistics -->
		<div class="col-4 col-md-2 mb-4">
			<a href="#" class="text-decoration-none text-dark">
				<div class="card text-center">
					<video class="card-img-top img-fluid py-2 px-5" preload="none"
						autoplay loop muted playsinline>
						<source src="https://cdn-icons-mp4.flaticon.com/512/13471/13471023.mp4" type="video/mp4">
					</video>
					<div class="card-body">
						<h6 class="card-title">Logistics</h6>
						<p class="card-text">Công việc: ${jobCountsByField["Logistic"]}</p>
					</div>
				</div>
			</a>
		</div>

		<!-- Kỹ thuật -->
		<div class="col-4 col-md-2 mb-4">
			<a href="#" class="text-decoration-none text-dark">
				<div class="card text-center">
					<video class="card-img-top img-fluid py-2 px-5" preload="none"
						autoplay loop muted playsinline>
						<source src="https://cdn-icons-mp4.flaticon.com/512/13470/13470972.mp4" type="video/mp4">
					</video>
					<div class="card-body">
						<h6 class="card-title">Kỹ thuật</h6>
						<p class="card-text">Công việc: ${jobCountsByField["Kỹ thuật"]}</p>
					</div>
				</div>
			</a>
		</div>

		<!-- Tài chính -->
		<div class="col-4 col-md-2 mb-4">
			<a href="#" class="text-decoration-none text-dark">
				<div class="card text-center">
					<video class="card-img-top img-fluid py-2 px-5" preload="none"
						autoplay loop muted playsinline>
						<source src="https://cdn-icons-mp4.flaticon.com/512/13471/13471004.mp4" type="video/mp4">
					</video>
					<div class="card-body">
						<h6 class="card-title">Tài chính</h6>
						<p class="card-text">Công việc: ${jobCountsByField["Tài chính"]}</p>
					</div>
				</div>
			</a>
		</div>

		<!-- Khác -->
		<div class="col-4 col-md-2 mb-4">
			<a href="#" class="text-decoration-none text-dark">
				<div class="card text-center">
					<video class="card-img-top img-fluid py-2 px-5 video-placeholder" preload="none"
						autoplay loop muted playsinline>
						<source src="https://cdn-icons-mp4.flaticon.com/512/16046/16046406.mp4" type="video/mp4">
					</video>
					<div class="card-body">
						<h6 class="card-title">Khác</h6>
						<p class="card-text">...</p>
					</div>
				</div>
			</a>
		</div>
	</div>
</div>
