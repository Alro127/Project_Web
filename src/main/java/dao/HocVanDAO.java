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
import conn.DBConnection;

public class HocVanDAO {
	public static void saveEducationList(CV cv, List<HocVan> hocVans) throws SQLException {
        String sql = "INSERT INTO HocVan (idCV, startDate, endDate, school, major, description) VALUES (?, ?, ?, ?, ?, ?)";
        try {
        	Connection conn = DBConnection.getConnection();
        	PreparedStatement ps = conn.prepareStatement(sql);
            for (HocVan hocVan : hocVans) {
                ps.setInt(1, cv.getIdCV());
                ps.setDate(2, new java.sql.Date(hocVan.getStart().getTime()));
                ps.setDate(3, hocVan.getEnd() != null ? new java.sql.Date(hocVan.getEnd().getTime()) : null);
                ps.setString(4, hocVan.getSchool());
                ps.setString(5, hocVan.getMajor());
                ps.setString(6, hocVan.getDescription());
                ps.addBatch();
            }
            ps.executeBatch();
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
    }
	
	public static List<HocVan> getEducationListByCV(CV cv) throws SQLException {
        String sql = "SELECT * FROM HocVan WHERE idCV = ?";
        try {
        	Connection conn = DBConnection.getConnection();
        	PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cv.getIdCV());
            try (ResultSet rs = ps.executeQuery()) {
                List<HocVan> hocVans = new ArrayList<>();
                while (rs.next()) {
                	int id = rs.getInt("id");
                	int idCV = rs.getInt("idCV");
                    Date start = rs.getDate("startDate");
                    Date end = rs.getDate("endDate");
                    String school = rs.getString("school");
                    String major = rs.getString("major");
                    String description = rs.getString("description");

                    HocVan hocVan = new HocVan(id, idCV, start, end, school, major, description);
                    hocVans.add(hocVan);
                }
                return hocVans;
            }
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        return null;
    }

	public static void updateEducationList(CV cv, List<HocVan> hocVans) throws ClassNotFoundException, SQLException {
		deleteEducationByCV(cv.getIdCV());
		saveEducationList(cv, hocVans);
	}
	public static void deleteEducationByCV(int idCV) throws ClassNotFoundException {
	    // Câu lệnh SQL xóa tất cả học vấn có idCV trùng với idCV được truyền vào
	    String sql = "DELETE FROM HocVan WHERE idCV = ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        // Thiết lập giá trị cho tham số idCV
	        ps.setInt(1, idCV);

	        // Thực thi câu lệnh xóa
	        int affectedRows = ps.executeUpdate();
	        if (affectedRows > 0) {
	            System.out.println("Đã xóa " + affectedRows + " bản ghi học vấn với idCV = " + idCV);
	        } else {
	            System.out.println("Không tìm thấy học vấn với idCV = " + idCV);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
