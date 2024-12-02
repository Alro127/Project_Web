package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import beans.CongTy;
import beans.CongViec;
import beans.User;
import dao.CongTyDAO;
import dao.CongViecDAO;

/**
 * Servlet implementation class TaiKhoanCongTy
 */
public class TaiKhoanCongTyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaiKhoanCongTyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		CongTy congTy = new CongTy();
	    try {
	        congTy = CongTyDAO.GetCongTyById(User.Id);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    request.setAttribute("congTy", congTy);
	    request.getRequestDispatcher("/TaiKhoanCongTy.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
