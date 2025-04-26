package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import beans.CongViec;
import beans.HoSo;
import dao.CongViecDAO;
import dao.HoSoDAO;

/**
 * Servlet implementation class HoSoUngTuyenServlet
 */
public class HoSoUngTuyenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoSoUngTuyenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);  
		if (session == null) {
			response.sendRedirect("Login.jsp"); 
			return;
		}
		List<String> linhVucs = new ArrayList<>();
		try {
			linhVucs = CongViecDAO.getListLinhVuc();
		} catch (Exception e) {
			e.printStackTrace();
		}	  
		request.setAttribute("linhVucs", linhVucs);
		// Kiểm tra xem session có chứa id người dùng không
		String idCTStr = (String) session.getAttribute("id");
		if (idCTStr == null) {
			response.sendRedirect("Login.jsp"); 
			return;
		}
		int idCT= Integer.parseInt(idCTStr);
		
		List<HoSo> hoSos = new ArrayList<>();
		
		try {
	    	//int id = Integer.parseInt(request.getParameter("id"));
	        hoSos = HoSoDAO.GetListHoSoByIdCT(idCT);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		// Lấy param
		String linhVuc = request.getParameter("linhVuc");
		String thoiGian = request.getParameter("thoiGian");
		String trangThai = request.getParameter("trangThai");
		String luotNop = request.getParameter("luotNop");
		String searchText = request.getParameter("searchText");
		
		hoSos = HoSo.locTheoHoTen(searchText, hoSos);
		hoSos = HoSo.LocTheoLinhVuc(linhVuc, hoSos);
		hoSos = HoSo.LocTheoLuotNop(luotNop, hoSos);
		hoSos = HoSo.LocTheoThoiGian(thoiGian, hoSos);
		hoSos = HoSo.LocTheoTrangThai(trangThai, hoSos);
		
		int pageSize = 20;
	    int page = 1;  // Mặc định là trang đầu tiên

	    // Lấy tham số 'page' từ yêu cầu
	    String pageStr = request.getParameter("page");
	    if (pageStr != null && !pageStr.isEmpty()) {
	        try {
	            page = Integer.parseInt(pageStr);
	        } catch (NumberFormatException e) {
	            page = 1;  // Nếu tham số không hợp lệ, sử dụng trang đầu tiên
	        }
	    }

	    int totalHoSos = hoSos.size();
	    int totalPages = (int) Math.ceil((double) totalHoSos / pageSize);

	    int startIndex = (page - 1) * pageSize;
	    int endIndex = Math.min(startIndex + pageSize, totalHoSos);
	    List<HoSo> pagedHoSos = hoSos.subList(startIndex, endIndex);

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	   

	    // Nếu không phải AJAX, bạn có thể chuyển hướng sang JSP
	    if (!"true".equals(request.getParameter("ajax"))) {
	        request.getRequestDispatcher("HoSoUngTuyen.jsp").forward(request, response);
	    }
	    else {
	    	 Map<String, Object> responseData = new HashMap<>();
	 	    responseData.put("hoSos", pagedHoSos);
	 	    responseData.put("totalPages", totalPages);
	 	    responseData.put("currentPage", page);

	 	    String jsonResponse = new Gson().toJson(responseData);
	 	    System.out.println(jsonResponse); 
	 	    response.getWriter().write(jsonResponse);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idCV = request.getParameter("idCV");
		String idCongViec = request.getParameter("idCongViec");
	    String trangThai = request.getParameter("trangThai");

	    // Xử lý logic cập nhật trạng thái
	    boolean updateSuccess = HoSoDAO.updateTrangThai(Integer.parseInt(idCV), Integer.parseInt(idCongViec), trangThai);

	    // Gửi phản hồi về client
	    if (updateSuccess) {
	        response.getWriter().write("success");
	    } else {
	        response.getWriter().write("fail");
	    }
	}

}
