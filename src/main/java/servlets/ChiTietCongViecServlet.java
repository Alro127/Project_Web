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
        HttpSession session = request.getSession(true);
        String idStr = request.getParameter("id");

        try {
            int id = Integer.parseInt(idStr);

            // Lấy thông tin công việc từ DAO
            CongViec congViec = CongViecDAO.getCongViecById(id);
            if (congViec == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Công việc không tồn tại");
                return;
            }

            // Lấy danh sách công việc liên quan
            List<CongViec> congViecs = CongViecDAO.GetListCongViec();
            List<CongViec> congViecLienQuans = CongViec.sortBySimilarity(congViec, congViecs);

            // Cập nhật lượt xem nếu cần thiết
            if (session.getAttribute("id") != null &&
                congViec.getIdCT() != Integer.parseInt((String) session.getAttribute("id"))) {
                CongViecDAO.updateLuotXem(id);
            }

            // Kiểm tra nếu yêu cầu là AJAX
            if ("true".equals(request.getParameter("ajax"))) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                // Tạo JSON từ dữ liệu công việc
                Map<String, Object> jsonResponse = new HashMap<>();
                jsonResponse.put("congViec", congViec);

                // Sử dụng Gson để chuyển đổi đối tượng Java sang JSON
                Gson gson = new Gson();
                String json = gson.toJson(jsonResponse);

                response.getWriter().write(json);
            } else {
                request.setAttribute("congViec", congViec);
                request.setAttribute("congViecLienQuans", congViecLienQuans);
                request.getRequestDispatcher("ChiTietCongViec.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống");
        }
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
