package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.AuthUtil;
import utils.CSRFTokenManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import beans.CongViec;
import beans.HoSo;
import beans.TaiKhoan;
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
		
		if (!AuthUtil.authorizeRole(request, response, "CongTy")) return;
		
		HttpSession session = request.getSession(false);
		
		TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("account");
		
		List<String> linhVucs = new ArrayList<>();
		try {
			linhVucs = CongViecDAO.getListLinhVuc();
		} catch (Exception e) {
			e.printStackTrace();
		}	  
		request.setAttribute("linhVucs", linhVucs);

		int idCT= taiKhoan.getId();
		
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
	        request.getRequestDispatcher("/WEB-INF/views/HoSoUngTuyen.jsp").forward(request, response);
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
	    if (!AuthUtil.authorizeRole(request, response, "CongTy")) return;

	    HttpSession session = request.getSession(false);
	    TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("account");

	    String idCV = request.getParameter("idCV");
	    String idCongViec = request.getParameter("idCongViec");
	    String trangThai = request.getParameter("trangThai");

	    int cvId = Integer.parseInt(idCV);
	    int congViecId = Integer.parseInt(idCongViec);

	    if (!CongViecDAO.isCongViecBelongsToCompany(congViecId, taiKhoan.getId())) {
	        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền thay đổi trạng thái hồ sơ này.");
	        return;
	    }

	    boolean updateSuccess = HoSoDAO.updateTrangThai(cvId, congViecId, trangThai);

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    if (updateSuccess) {
	        String newToken = (String) request.getSession().getAttribute("csrfToken");
	        response.getWriter().write("{\"status\":\"success\", \"newToken\":\"" + newToken + "\"}");
	    } else {
	        response.getWriter().write("{\"status\":\"fail\"}");
	    }
	}

}
