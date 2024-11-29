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
 * Servlet implementation class CongViecServlet
 */
public class CongViecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CongViecServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<CongViec> congViecs = new ArrayList<>();
        try
        {
        	congViecs = CongViecDAO.GetListCongViec();
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        
        // Đưa dữ liệu vào request để truyền vào JSP
        request.setAttribute("congViecs", congViecs);
        
        // Chuyển tiếp đến trang JSP
        request.getRequestDispatcher("/TrangGioiThieu.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
