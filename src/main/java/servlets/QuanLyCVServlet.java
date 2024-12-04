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
import dao.CVDAO;


public class QuanLyCVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public QuanLyCVServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
    		HttpSession session = request.getSession();
    		String idTaiKhoan= (String) session.getAttribute("id");
    		int id = Integer.parseInt(idTaiKhoan);
        	CVDAO cvdao = new CVDAO();
            List<CV> cvList= cvdao.getAllCVbyIdUV(id);
            // Đưa CV vào thuộc tính của request để truy cập trong JSP
            request.setAttribute("cvList", cvList);
            
            // Chuyển hướng tới trang JSP để hiển thị
            RequestDispatcher dispatcher = request.getRequestDispatcher("QuanLyCV.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // Hiển thị lỗi nếu có vấn đề trong quá trình lấy dữ liệu
            response.getWriter().println("Error loading CV data: " + e.getMessage());
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
