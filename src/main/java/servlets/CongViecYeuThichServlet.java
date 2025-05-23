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
import java.util.stream.Collectors;

import com.google.gson.Gson;

import beans.CongViec;
import beans.CongViecYeuThich;
import beans.TaiKhoan;
import dao.CongViecDAO;
import dao.CongViecYeuThichDAO;

/**
 * Servlet implementation class CongViecYeuThichDAO
 */
public class CongViecYeuThichServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CongViecYeuThichServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if (!AuthUtil.authorizeRole(request, response, "UngVien")) return;
		
		HttpSession session = request.getSession(false);
		
		TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("account");

		int idUV = taiKhoan.getId();
		
		List<CongViec> congViecs = new ArrayList<>();
	    try {
	        congViecs = CongViecYeuThichDAO.GetListCongViecYeuThich(idUV);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    List<String> linhVucs = new ArrayList<>();
		try {
			linhVucs = CongViecDAO.getListLinhVuc();
		} catch (Exception e) {
			e.printStackTrace();
		}	  
		request.setAttribute("linhVucs", linhVucs);
		
		List<String> tinhThanhs = new ArrayList<>();
		try {
			tinhThanhs = CongViecDAO.getListTinhThanh();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("tinhThanhs", tinhThanhs);
		double minLuong = CongViec.findMinLuong(congViecs);
		double maxLuong = CongViec.findMaxLuong(congViecs);
		request.setAttribute("minLuong", minLuong);
		request.setAttribute("maxLuong", maxLuong);
		
		
		// Lọc theo lĩnh vực và tỉnh thành (nếu có)
	    String linhVuc = request.getParameter("linhVuc");
	    String tinhThanh = request.getParameter("tinhThanh");
	    String ten = request.getParameter("ten");  // Lấy giá trị tìm kiếm theo tên công việc
	    String kinhNghiem = request.getParameter("kinhNghiem");
	    String luongKhoiDiemHienTai = request.getParameter("luongKhoiDiemHienTai");
	    String LuongKetThucHienTai = request.getParameter("LuongKetThucHienTai");
	    congViecs = CongViec.LocLinhVuc(congViecs, linhVuc);
	    congViecs = CongViec.LocTinhThanh(congViecs, tinhThanh);
	    congViecs = CongViec.LocTen(congViecs, ten);
	    if (kinhNghiem != null) {
	    	congViecs = CongViec.findInExperince(congViecs, Integer.parseInt(kinhNghiem));
		}
	    if (luongKhoiDiemHienTai != null && LuongKetThucHienTai != null) {
	    	 congViecs = CongViec.findInRangeLuong(congViecs, Double.parseDouble(luongKhoiDiemHienTai), Double.parseDouble(LuongKetThucHienTai));
		}
	    int pageSize = 9;
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

	    // Tính toán số trang (tổng số công việc / số công việc mỗi trang)
	    int totalCongViecs = congViecs.size();
	    int totalPages = (int) Math.ceil((double) totalCongViecs / pageSize);

	    // Lấy công việc cho trang hiện tại
	    int startIndex = (page - 1) * pageSize;
	    int endIndex = Math.min(startIndex + pageSize, totalCongViecs);
	    List<CongViec> pagedCongViecs = congViecs.subList(startIndex, endIndex);

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    Map<String, Object> responseData = new HashMap<>();
	    responseData.put("congViecs", pagedCongViecs);
	    responseData.put("totalPages", totalPages);
	    responseData.put("currentPage", page);

	    // Chỉ trả về JSON một lần
	    String jsonResponse = new Gson().toJson(responseData);
	    response.getWriter().write(jsonResponse);
	    
	    if (!"true".equals(request.getParameter("ajax"))) {
	        request.getRequestDispatcher("/WEB-INF/views/CongViecYeuThich.jsp").forward(request, response);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Lấy id công việc từ request
		
		if (!AuthUtil.authorizeRole(request, response, "UngVien")) return;
		
		HttpSession session = request.getSession(false);
		
		TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("account");
		
		int idCongViec = Integer.parseInt(request.getParameter("idCongViec"));

		int idUV = taiKhoan.getId();

		try {
			// Kiểm tra xem công việc đã được yêu thích chưa
			CongViecYeuThich congViecYeuThich = CongViecYeuThichDAO.GetCongViecYeuThich(idUV, idCongViec);
			if (congViecYeuThich != null) {
				CongViecYeuThichDAO.DeleteCongViecYeuThich(idUV, idCongViec);
			} else {
				CongViecYeuThichDAO.AddCongViecYeuThich(idUV, idCongViec);
			}
			//CSRFTokenManager.generateToken(request);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống");
			return;
		}

		response.sendRedirect(request.getContextPath() + "/ChiTietCongViecServlet?id=" + idCongViec);

	}


}
