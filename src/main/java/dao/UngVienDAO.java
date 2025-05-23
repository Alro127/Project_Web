package dao;

import beans.UngVien;
import conn.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UngVienDAO {
    // Thêm ứng viên
    public boolean addUngVien(UngVien ungVien) throws SQLException {
        String sql = "INSERT INTO UngVien (fullName, gender, dob, phone, email, location, address, introduction, avatar) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
        	Connection connection = DBConnection.getConnection();
        	PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, ungVien.getFullName());
            ps.setString(2, ungVien.getGender());
            ps.setDate(3, new java.sql.Date(ungVien.getDob().getTime()));
            ps.setString(4, ungVien.getPhone());
            ps.setString(5, ungVien.getEmail());
            ps.setString(6, ungVien.getLocation());
            ps.setString(7, ungVien.getAddress());
            ps.setString(8, ungVien.getIntroduction());
            ps.setString(9, ungVien.getAvatar());

            return ps.executeUpdate() > 0;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        return false;
    }

    // Cập nhật thông tin ứng viên
    public static boolean updateUngVien(UngVien ungVien) throws SQLException {
        String sql = "UPDATE UngVien SET fullName = ?, gender = ?, dob = ?, phone = ?, location = ?, " +
                     "address = ?, introduction = ? WHERE idUV = ?";
        
        // Kiểm tra xem avatar có phải là null không
        if (ungVien.getAvatar() != null) {
            sql = "UPDATE UngVien SET fullName = ?, gender = ?, dob = ?, phone = ?, location = ?, " +
                  "address = ?, introduction = ?, avatar = ? WHERE idUV = ?";
        }

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, ungVien.getFullName());
            ps.setString(2, ungVien.getGender());
            ps.setDate(3, new java.sql.Date(ungVien.getDob().getTime()));
            ps.setString(4, ungVien.getPhone());
            ps.setString(5, ungVien.getLocation());
            ps.setString(6, ungVien.getAddress());
            ps.setString(7, ungVien.getIntroduction());
            
            // Nếu avatar không phải là null, set giá trị của avatar
            if (ungVien.getAvatar() != null) {
                ps.setString(8, ungVien.getAvatar());
                ps.setInt(9, ungVien.getIdUV());
            } else {
                ps.setInt(8, ungVien.getIdUV());
            }

            return ps.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


	/*
	 * public boolean updateAvatarUngVien(UngVien ungVien) throws SQLException {
	 * String sql = "UPDATE UngVien SET avatar = ? WHERE idUV = ?"; try{ Connection
	 * connection = DBConnection.getConnection(); PreparedStatement ps =
	 * connection.prepareStatement(sql); ps.setString(1, ungVien.getAvatar());
	 * ps.setInt(2, ungVien.getIdUV());
	 * 
	 * return ps.executeUpdate() > 0; } catch (ClassNotFoundException | SQLException
	 * e) { e.printStackTrace(); } return false; }
	 */

    // Lấy tất cả ứng viên
    public static List<UngVien> getAllUngVien() throws SQLException {
        String sql = "SELECT idUV, fullName, gender, dob, phone, email, location, address, introduction, avatar FROM UngVien";
        List<UngVien> ungVienList = new ArrayList<>();

        try{
        	Connection connection = DBConnection.getConnection();
        	Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                UngVien ungVien = new UngVien();
                ungVien.setIdUV(rs.getInt("idUV"));
                ungVien.setFullName(rs.getString("fullName"));
                ungVien.setGender(rs.getString("gender"));
                ungVien.setDob(rs.getDate("dob"));
                ungVien.setPhone(rs.getString("phone"));
                ungVien.setEmail(rs.getString("email"));
                ungVien.setLocation(rs.getString("location"));
                ungVien.setAddress(rs.getString("address"));
                ungVien.setIntroduction(rs.getString("introduction"));
                ungVien.setAvatar(rs.getString("avatar"));
                ungVienList.add(ungVien);
            }
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        return ungVienList;
    }

    // Lấy ứng viên theo ID
    public static UngVien getUngVienById(int idUV) {
        String sql = "SELECT idUV, fullName, gender, dob, phone, email, location, address, introduction, avatar FROM UngVien WHERE idUV = ?";
        try{
        	Connection connection = DBConnection.getConnection();
        	PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idUV);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UngVien ungVien = new UngVien();
                    ungVien.setIdUV(rs.getInt("idUV"));
                    ungVien.setFullName(rs.getString("fullName"));
                    ungVien.setGender(rs.getString("gender"));
                    ungVien.setDob(rs.getDate("dob"));
                    ungVien.setPhone(rs.getString("phone"));
                    ungVien.setEmail(rs.getString("email"));
                    ungVien.setLocation(rs.getString("location"));
                    ungVien.setAddress(rs.getString("address"));
                    ungVien.setIntroduction(rs.getString("introduction"));
                    ungVien.setAvatar(rs.getString("avatar")); // Lấy avatar dưới dạng BLOB
                    return ungVien;
                }
            }
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        return null;
    }

    // Xóa ứng viên
    public boolean deleteUngVien(int idUV) throws SQLException {
        String sql = "DELETE FROM UngVien WHERE idUV = ?";
        try{
        	Connection connection = DBConnection.getConnection();
        	PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idUV);
            return ps.executeUpdate() > 0;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        return false;
    }
    public static void addUngVienAfterSignUP(int id, String email)
    {
    	String sql = "insert into UngVien (IdUV, email) values (?, ?)";
    	try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, email);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}

