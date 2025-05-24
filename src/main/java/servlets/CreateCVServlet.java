package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.AuthUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import beans.CV;
import beans.TaiKhoan;
import beans.UngVien;
import dao.CVDAO;
import dao.UngVienDAO;

public class CreateCVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateCVServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.authorizeRole(request, response, "UngVien")) return;
		
		HttpSession session = request.getSession(false);
		
		TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("account");
		UngVien uv = UngVienDAO.getUngVienById(taiKhoan.getId());
		// Đưa CV vào thuộc tính của request để truy cập trong JSP
		request.setAttribute("uv", uv);
		
		// Chuyển hướng tới trang JSP để hiển thị
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/ThongTinCV_create.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
