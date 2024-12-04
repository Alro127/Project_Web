package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import conn.DBConnection;

public class HoSoDAO {
	public static boolean AddHoSo(int idCV, int idCongViec) throws SQLException, ClassNotFoundException {
	    String sql = "INSERT INTO HoSo (IdCV, IdCongViec, ThoiGianGui, TrangThai) VALUES (?, ?, ?, ?)";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement statement = conn.prepareStatement(sql)) {
	         
	        statement.setInt(1, idCV);
	        statement.setInt(2, idCongViec);
	        Timestamp currentTimestamp = Timestamp.from(Instant.now());
	        statement.setTimestamp(3, currentTimestamp);
	        statement.setString(4, "Chờ");

	        // Thực hiện câu lệnh và kiểm tra số dòng bị ảnh hưởng
	        int rowsAffected = statement.executeUpdate();
	        return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng được thêm
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        return false; 
	    }
	}

}
