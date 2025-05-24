package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.AuthUtil;
import utils.CSRFTokenManager;

import java.io.IOException;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import beans.CV;
import beans.TaiKhoan;
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
		//doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    if (!AuthUtil.authorizeRole(request, response, "UngVien")) return;

	    HttpSession session = request.getSession(false);
	    TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("account");

	    int idCV = Integer.parseInt(request.getParameter("id"));

	    // ✅ Kiểm tra quyền sở hữu
	    if (!CVDAO.isCVBelongsToUser(idCV, taiKhoan.getId())) {
	        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền xoá CV này.");
	        return;
	    }

	    CVDAO cvdao = new CVDAO();
	    try {
	        boolean isDeleted = cvdao.deleteCVbyId(idCV);
	        if (isDeleted) {
				LOGGER.info("CV with id = {} is deleted", idCV);
	            response.sendRedirect("QuanLyCVServlet");
	        } else {
				LOGGER.warn("Error occured during deleting CV with id = {}", idCV);
	            response.getWriter().println("Không tìm thấy CV với ID: " + idCV);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        response.getWriter().println("Lỗi khi xoá CV: " + e.getMessage());
	    }
	}


}
