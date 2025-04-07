package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import beans.CV;
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
        HttpSession session = request.getSession();
		String idTaiKhoan= (String) session.getAttribute("id");
		int id = Integer.parseInt(idTaiKhoan);
		UngVien uv = UngVienDAO.getUngVienById(id);
		// Đưa CV vào thuộc tính của request để truy cập trong JSP
		request.setAttribute("uv", uv);
		
		// Chuyển hướng tới trang JSP để hiển thị
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/ThongTinCV_create.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
