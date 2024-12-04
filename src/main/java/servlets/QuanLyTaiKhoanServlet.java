package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import beans.CV;
import beans.TaiKhoan;
import beans.UngVien;
import dao.CVDAO;
import dao.TaiKhoanDAO;
import dao.UngVienDAO;

public class QuanLyTaiKhoanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QuanLyTaiKhoanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Gọi phương thức từ CVDAO để lấy danh sách CV
		HttpSession session = request.getSession();
		String idTaiKhoan= (String) session.getAttribute("id");
		int id = Integer.parseInt(idTaiKhoan);
		TaiKhoan taiKhoan = TaiKhoanDAO.getTaiKhoanById(id);
		UngVien uv = UngVienDAO.getUngVienById(taiKhoan.getId());
		request.setAttribute("taiKhoan", taiKhoan); // Đưa vào request để hiển thị trong JSP
		request.setAttribute("uv", uv);
		request.getRequestDispatcher("QuanLyTaiKhoan.jsp").forward(request, response);
	}

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Lấy thông tin từ form
	        String fullname = request.getParameter("fullname");
	        String gender = request.getParameter("gender");
	        String dobString = request.getParameter("dob");  // Lấy ngày sinh dưới dạng chuỗi
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  // Định dạng ngày của bạn (yyyy-MM-dd)
	        Date dob = null;

	        try {
	            dob = (Date) sdf.parse(dobString);  // Chuyển chuỗi thành Date
	        } catch (ParseException e) {
	            e.printStackTrace();
	            // Xử lý lỗi nếu chuỗi ngày không hợp lệ
	        }
	        String phone = request.getParameter("phone");
	        String email = request.getParameter("email");
	        String location = request.getParameter("location");
	        String address = request.getParameter("address");
	        String introduction = request.getParameter("introduction");
	        int idTaiKhoan = Integer.parseInt(request.getParameter("idTaiKhoan"));
	        // Bạn có thể thêm mã xử lý ảnh (avatar) nếu cần, ví dụ: lưu avatar vào thư mục hoặc cơ sở dữ liệu

	        // Tạo đối tượng UserAccount để lưu thông tin người dùng
	        UngVien userAccount = new UngVien();
	        userAccount.setFullName(fullname);
	        userAccount.setGender(gender);
	        userAccount.setDob(dob);
	        userAccount.setPhone(phone);
	        userAccount.setEmail(email);
	        userAccount.setLocation(location);
	        userAccount.setAddress(address);
	        userAccount.setIntroduction(introduction);
	        userAccount.setIdUV(idTaiKhoan);
	        
	        try {
				UngVienDAO.updateUngVien(userAccount);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        doGet(request,response);
	    }
}
