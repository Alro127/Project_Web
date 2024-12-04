package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.CV;
import beans.HocVan;
import beans.KinhNghiem;
import conn.DBConnection;

public class KinhNghiemDAO {
	public static void saveExperienceList(CV cv, List<KinhNghiem> kinhNghiems) throws SQLException {
        String sql = "INSERT INTO KinhNghiem (idCV, startDate, endDate, company, position, description) VALUES (?, ?, ?, ?, ?, ?)";
        try {
        	Connection conn = DBConnection.getConnection();
        	PreparedStatement ps = conn.prepareStatement(sql);
            for (KinhNghiem kinhNghiem : kinhNghiems) {
                ps.setInt(1, cv.getIdCV());
                ps.setDate(2, new java.sql.Date(kinhNghiem.getStart().getTime()));
                ps.setDate(3, kinhNghiem.getEnd() != null ? new java.sql.Date(kinhNghiem.getEnd().getTime()) : null);
                ps.setString(4, kinhNghiem.getCompany());
                ps.setString(5, kinhNghiem.getPosition());
                ps.setString(6, kinhNghiem.getDescription());
                ps.addBatch();
            }
            ps.executeBatch();
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
    }
	
	public static List<KinhNghiem> getExperienceListByCV(CV cv) throws SQLException {
        String sql = "SELECT * FROM KinhNghiem WHERE idCV = ?";
        try {
        	Connection conn = DBConnection.getConnection();
        	PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cv.getIdCV());
            try (ResultSet rs = ps.executeQuery()) {
                List<KinhNghiem> kinhNghiems = new ArrayList<>();
                while (rs.next()) {
                	int id = rs.getInt("id");
                	int idCV = rs.getInt("idCV");
                    Date start = rs.getDate("startDate");
                    Date end = rs.getDate("endDate");
                    String company = rs.getString("company");
                    String position = rs.getString("position");
                    String description = rs.getString("description");

                    KinhNghiem kinhNghiem = new KinhNghiem(id, idCV, start, end, company, position, description);
                    kinhNghiems.add(kinhNghiem);
                }
                return kinhNghiems;
            }
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        return null;
    }

	public static void updateExperienceList(CV cv, List<KinhNghiem> kinhNghiems) throws ClassNotFoundException, SQLException {
		deleteExperienceByCV(cv.getIdCV());
		saveExperienceList(cv, kinhNghiems);
	}
	public static void deleteExperienceByCV(int idCV) throws ClassNotFoundException {
	    // Câu lệnh SQL xóa tất cả học vấn có idCV trùng với idCV được truyền vào
	    String sql = "DELETE FROM KinhNghiem WHERE idCV = ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        // Thiết lập giá trị cho tham số idCV
	        ps.setInt(1, idCV);

	        // Thực thi câu lệnh xóa
	        int affectedRows = ps.executeUpdate();
	        if (affectedRows > 0) {
	            System.out.println("Đã xóa " + affectedRows + " bản ghi kinh nghiệm với idCV = " + idCV);
	        } else {
	            System.out.println("Không tìm thấy học vấn với idCV = " + idCV);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
