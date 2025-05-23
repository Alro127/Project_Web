package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CSRFTokenManager;

import java.io.IOException;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import beans.CV;
import dao.CVDAO;

/**
 * Servlet implementation class DeleteCVServlet
 */
public class DeleteCVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteCVServlet.class);
    public DeleteCVServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Gọi phương thức từ CVDAO để lấy danh sách CV
    	int IdCV = Integer.parseInt(request.getParameter("id"));
    	CVDAO cvdao = new CVDAO();
    	try {
    	    boolean isDeleted = cvdao.deleteCVbyId(IdCV);
    	    if (isDeleted) {
    	    	LOGGER.info("CV with id = {} is deleted", IdCV);
    	    	//CSRFTokenManager.generateToken(request);
    	        // Xóa thành công, chuyển hướng đến trang danh sách CV
    	        response.sendRedirect("QuanLyCVServlet"); // Hoặc trang phù hợp khác
    	    } else {
    	    	LOGGER.warn("Error occured during deleting CV with id = {}", IdCV);
    	        // Nếu không có CV nào bị xóa, hiển thị thông báo lỗi
    	        response.getWriter().println("No CV found with ID: " + IdCV);
    	    }
    	} catch (SQLException e) {
    	    // Xử lý lỗi trong quá trình xóa CV
    	    e.printStackTrace();
    	    response.getWriter().println("Error deleting CV: " + e.getMessage());
    	}
	}

}
