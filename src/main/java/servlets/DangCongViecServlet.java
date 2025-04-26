package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import org.eclipse.tags.shaded.org.apache.xpath.operations.And;

import com.mysql.cj.Session;

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
		HttpSession session = request.getSession();
		
		if (session.getAttribute("id") == null) {
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			return;
		}
			
		
		if (session.getAttribute("role").equals("CongTy")) {
			request.getRequestDispatcher("/WEB-INF/views/DangCongViec.jsp").forward(request, response);	
			return;
		}
		
		request.getRequestDispatcher("CongViecServlet").forward(request, response);
		
		}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);  
		if (session == null) {
			response.sendRedirect("Login.jsp"); 
			return;
		}

		// Kiểm tra xem session có chứa id người dùng không
		String idCTStr = (String) session.getAttribute("id");
		if (idCTStr == null) {
			response.sendRedirect("Login.jsp"); 
			return;
		}
		int idCT= Integer.parseInt(idCTStr);
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
				congViec.setIdCT(idCT);
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
	                Message.alertAndRedirect(response, "Đăng công việc thành công!", "QuanLyTinDangServlet");
	            } else {
	            	Message.alertAndRedirect(response, "Có lỗi xảy ra, vui lòng thử lại.", "/WEB-INF/views/DangCongViec.jsp");
	            }
	        } else {
	        	Message.alertAndRedirect(response, "Dữ liệu không hợp lệ. Vui lòng kiểm tra lại.", "/WEB-INF/views/DangCongViec.jsp");
	        }
		} catch (Exception e) {
	        e.printStackTrace();
	        Message.alertAndRedirect(response, "Đã xảy ra lỗi: " + e.getMessage(), "/WEB-INF/views/DangCongViec.jsp");
	    }
	}
	
}
