package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.Session;

import beans.CongTy;

import beans.CongViec;
import dao.CongTyDAO;
import dao.CongViecDAO;

/**
 * Servlet implementation class CongTyServlet
 */
public class CongTyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CongTyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		CongTy congTy = new CongTy();
	    try {
	        congTy = CongTyDAO.GetCongTyById(id);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    List<String> images = CongTyDAO.getHinhAnhHoatDong(id);
	    request.setAttribute("images", images);
	    request.setAttribute("congTy", congTy);
	    request.getRequestDispatcher("TrangChuCongTy.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
