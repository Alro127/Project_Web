package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

import beans.CongViec;
import dao.CongViecDAO;

/**
 * Servlet implementation class DangCongViecServlet
 */
/* @WebServlet("/Project_Web/DangCongViecServlet") */
public class DangCongViecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DangCongViecServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Nếu cần xử lý GET request, bạn có thể bổ sung ở đây.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Tạo đối tượng công việc và gán giá trị từ request
		CongViec congViec = new CongViec();

		// Kiểm tra các tham số request để đảm bảo không rỗng
		String ten = request.getParameter("ten");
		String diaDiem = request.getParameter("diaDiem");
		String luongStr = request.getParameter("luong");
		String namKinhNghiemStr = request.getParameter("namKinhNghiem");
		String linhVuc = request.getParameter("linhVuc");
		String thoiGianHetHanStr = request.getParameter("thoiGianHetHan"); // yyyy-MM-ddTHH:mm
		String moTa = request.getParameter("moTa");
		String yeuCau = request.getParameter("yeuCau");
		String quyenLoi = request.getParameter("quyenLoi");
		try {
			// Kiểm tra giá trị đầu vào
			if (ten != null && !ten.isEmpty() && diaDiem != null && !diaDiem.isEmpty() && luongStr != null
					&& !luongStr.isEmpty() && namKinhNghiemStr != null && !namKinhNghiemStr.isEmpty()
					&& thoiGianHetHanStr != null && !thoiGianHetHanStr.isEmpty()) {

				// Gán giá trị cho đối tượng congViec
				congViec.setIdCT(Integer.parseInt("1"));
				congViec.setTen(ten);
				congViec.setDiaDiem(diaDiem);
				congViec.setLuong(Double.parseDouble(luongStr));
				congViec.setNamKinhNghiem(Integer.parseInt(namKinhNghiemStr));
				congViec.setLinhVuc(linhVuc);

				// Chuyển đổi thoiGianHetHanStr từ định dạng datetime-local
				String formattedDateTime = thoiGianHetHanStr.replace("T", " ") + ":00"; // yyyy-MM-dd HH:mm:ss
				congViec.setThoiGianHetHan(Timestamp.valueOf(formattedDateTime)); // Chuyển thành Timestamp

				congViec.setMoTa(moTa);
				congViec.setYeuCau(yeuCau);
				congViec.setQuyenLoi(quyenLoi);

				// Thêm công việc mới vào cơ sở dữ liệu
				if (CongViecDAO.AddCongViecMoi(congViec)) {
					// Đặt thông báo thành công
					request.setAttribute("message", "Đăng công việc thành công!");
				} else {
					// Đặt thông báo lỗi
					request.setAttribute("message", "Có lỗi xảy ra, vui lòng thử lại.");
				}
			} else {
				// Nếu tham số yêu cầu không hợp lệ
				request.setAttribute("message", "Dữ liệu không hợp lệ. Vui lòng kiểm tra lại.");
			}
		} catch (Exception e) {
			// Xử lý lỗi và gửi thông báo về phía người dùng
			request.setAttribute("message", "Đã xảy ra lỗi: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Chuyển tiếp đến trang DangCongViec.jsp để hiển thị thông báo
			request.getRequestDispatcher("DangCongViec.jsp").forward(request, response);
		}
	}

}
