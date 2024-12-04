package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.CV;
import beans.ChungChi;
import conn.DBConnection;

public class ChungChiDAO {
	public static void saveCertificateList(CV cv, List<ChungChi> chungChis) throws SQLException {
        String sql = "INSERT INTO ChungChi (idCV, name) VALUES (?, ?)";
        try {
        	Connection conn = DBConnection.getConnection();
        	PreparedStatement ps = conn.prepareStatement(sql);
            for (ChungChi chungChi : chungChis) {
                ps.setInt(1, cv.getIdCV());
                ps.setString(2, chungChi.getName());
                ps.addBatch();
            }
            ps.executeBatch();
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
    }
	
	public static List<ChungChi> getCertificateListByCV(CV cv) throws SQLException {
        String sql = "SELECT * FROM ChungChi WHERE idCV = ?";
        try {
        	Connection conn = DBConnection.getConnection();
        	PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cv.getIdCV());
            try (ResultSet rs = ps.executeQuery()) {
                List<ChungChi> chungChis = new ArrayList<>();
                while (rs.next()) {
                	int id = rs.getInt("id");
                	int idCV = rs.getInt("idCV");
                    String name = rs.getString("name");
                    ChungChi chungChi = new ChungChi(id, idCV, name);
                    chungChis.add(chungChi);
                }
                return chungChis;
            }
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        return null;
    }

	public static void updateCertificateList(CV cv, List<ChungChi> chungChis) throws ClassNotFoundException, SQLException {
		deleteCertificateByCV(cv.getIdCV());
		saveCertificateList(cv, chungChis);
	}
	public static void deleteCertificateByCV(int idCV) throws ClassNotFoundException {
	    // Câu lệnh SQL xóa tất cả học vấn có idCV trùng với idCV được truyền vào
	    String sql = "DELETE FROM ChungChi WHERE idCV = ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        // Thiết lập giá trị cho tham số idCV
	        ps.setInt(1, idCV);

	        // Thực thi câu lệnh xóa
	        int affectedRows = ps.executeUpdate();
	        if (affectedRows > 0) {
	            System.out.println("Đã xóa " + affectedRows + " bản ghi chứng chỉ với idCV = " + idCV);
	        } else {
	            System.out.println("Không tìm thấy chứng chỉ với idCV = " + idCV);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
