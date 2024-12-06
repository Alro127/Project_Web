<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Add image  -->
<div class="modal fade" id="addImageModal" tabindex="-1"
	aria-labelledby="addImageModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="addImageModalLabel">Thêm Hình Ảnh</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="mb-3">
					<label for="imageUpload" class="form-label">Chọn hình ảnh:</label>
					<input type="file" class="form-control" id="imageUpload">
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Đóng</button>
				<button type="button" class="btn btn-primary"
					data-bs-dismiss="modal" id="saveImage">Lưu</button>
			</div>
		</div>
	</div>
</div>

<!-- Add avatar  -->
<div class="modal fade" id="addAvatarImageModal" tabindex="-1"
	aria-labelledby="addAvatarImageModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="addAvatarImageModalLabel">Thêm Hình
					Ảnh</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="mb-3">
					<label for="imageAvatarUpload" class="form-label">Chọn hình
						ảnh:</label> <input type="file" class="form-control"
						id="imageAvatarUpload">
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Đóng</button>
				<button type="button" class="btn btn-primary"
					data-bs-dismiss="modal" id="saveAvatarImage">Lưu</button>
			</div>
		</div>
	</div>
</div>

<!-- Add Background  -->
<div class="modal fade" id="addBackGroundImageModal" tabindex="-1"
	aria-labelledby="addBackGroundImageModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="addBackGroundImageModalLabel">Thêm
					Hình Ảnh</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="mb-3">
					<label for="imageBackGroundUpload" class="form-label">Chọn
						hình ảnh:</label> <input type="file" class="form-control"
						id="imageBackGroundUpload">
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Đóng</button>
				<button type="button" class="btn btn-primary"
					data-bs-dismiss="modal" id="saveBackGroundImage">Lưu</button>
			</div>
		</div>
	</div>
</div>