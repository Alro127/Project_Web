package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.HoSoDAO;

/**
 * Servlet implementation class UngTuyenServlet
 */
public class UngTuyenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UngTuyenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            // Lấy tham số từ URL
            String idCVStr = request.getParameter("idCV");
            String idCongViecStr = request.getParameter("idCongViec");

            // Kiểm tra tham số
            if (idCVStr == null || idCongViecStr == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu tham số idCV hoặc idCongViec.");
                return;
            }

            int idCV = Integer.parseInt(idCVStr);
            int idCongViec = Integer.parseInt(idCongViecStr);

            // Gọi DAO để xử lý logic ứng tuyển

            boolean isSuccessful = HoSoDAO.AddHoSo(idCV, idCongViec);

            if (isSuccessful) {
                // Hiển thị thông báo thành công và quay lại trang trước đó
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("<script>alert('Ứng tuyển thành công!'); window.location.href=document.referrer;</script>");
            } else {
                // Hiển thị thông báo thất bại và quay lại trang trước đó
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("<script>alert('Bạn đã gửi hồ sơ này rồi!'); window.location.href=document.referrer;</script>");
            }


            //request.getRequestDispatcher("CongViecServlet").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "idCV hoặc idCongViec không hợp lệ.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống.");
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
