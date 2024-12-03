package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import beans.CongViec;
import dao.CongViecDAO;

/**
 * Servlet implementation class ChiTietCongViecServlet
 */
public class ChiTietCongViecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChiTietCongViecServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idStr = request.getParameter("id");
		try
		{
			int id = Integer.parseInt(idStr);
			CongViec congViec = CongViecDAO.getCongViecById(id);
			if (congViec == null) {
	            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Công việc không tồn tại");
	            return;
	        }
			request.setAttribute("congViec", congViec);
			
			//Chèn Công việc liên quan
			List<CongViec> congViecs = new ArrayList<>();
			congViecs = CongViecDAO.GetListCongViec();
			
			List<CongViec> congViecLienQuans = new ArrayList<>();
			congViecLienQuans = CongViec.sortBySimilarity(congViec, congViecs);
			
			request.setAttribute("congViecLienQuans", congViecLienQuans);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		request.getRequestDispatcher("ChiTietCongViec.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
