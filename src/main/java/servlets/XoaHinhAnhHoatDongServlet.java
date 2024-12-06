package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

import beans.CongTy;
import dao.CongTyDAO;

/**
 * Servlet implementation class XoaHinhAnhHoatDongServlet
 */
public class XoaHinhAnhHoatDongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XoaHinhAnhHoatDongServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession(false);
	    int id = Integer.parseInt((String)session.getAttribute("id"));
	    BufferedReader reader = request.getReader();
	    StringBuilder jsonString = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        jsonString.append(line);
	    }

	    try {
	        JSONObject json = new JSONObject(jsonString.toString());
	        // Lấy các trường thông tin từ JSON
	        String fileName = json.getString("fileName");

	        // Đường dẫn thư mục act trong project
	        String absolutePath = "D:\\Project\\Project_Web\\src\\main\\webapp\\assets\\images\\act";

	        // Đường dẫn thư mục deploy
	        String deployedPath = request.getServletContext().getRealPath("/assets/images/act");

	        // Xóa dữ liệu trong cơ sở dữ liệu
	        CongTyDAO.XoaHinhAnhHoatDong(id, "assets/images/act/" + fileName);

	        // Tạo đối tượng File từ đường dẫn thư mục trong project
	        File absoluteFile = new File(absolutePath, fileName);

	        // Tạo đối tượng File từ đường dẫn thư mục đã deploy
	        File deployedFile = new File(deployedPath, fileName);

	        // Tạo một đối tượng JSONObject để chứa phản hồi
	        JSONObject jsonResponse = new JSONObject();

	        boolean deletedFromAbsolute = false;
	        boolean deletedFromDeployed = false;

	        // Xóa ảnh trong thư mục gốc (project)
	        if (absoluteFile.exists() && absoluteFile.isFile()) {
	            if (absoluteFile.delete()) {
	                deletedFromAbsolute = true;
	            }
	        }

	        // Xóa ảnh trong thư mục deploy
	        if (deployedFile.exists() && deployedFile.isFile()) {
	            if (deployedFile.delete()) {
	                deletedFromDeployed = true;
	            }
	        }

	     // Phản hồi về kết quả
	        if (deletedFromAbsolute && deletedFromDeployed) {
	            jsonResponse.put("success", true);
	            jsonResponse.put("message", "Xóa ảnh thành công từ cả hai thư mục.");
	        } else if (deletedFromAbsolute) {
	            jsonResponse.put("success", false);
	            jsonResponse.put("message", "Xóa ảnh thành công từ thư mục gốc, nhưng không thể xóa trong thư mục deploy.");
	        } else if (deletedFromDeployed) {
	            jsonResponse.put("success", false);
	            jsonResponse.put("message", "Xóa ảnh thành công từ thư mục deploy, nhưng không thể xóa trong thư mục gốc.");
	        } else {
	            jsonResponse.put("success", false);
	            jsonResponse.put("message", "Không thể xóa ảnh trong cả hai thư mục.");
	        }


	        // Gửi phản hồi về client
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(jsonResponse.toString());

	    } catch (Exception e) {
	        e.printStackTrace();

	        JSONObject jsonResponse = new JSONObject();
	        jsonResponse.put("status", "error");
	        jsonResponse.put("message", "Lỗi khi xử lý ảnh!");

	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(jsonResponse.toString());
	    }
	}


}
