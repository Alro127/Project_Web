package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.CongTy;
import conn.DBConnection;

public class CongTyDAO {
	public static CongTy GetCongTyById(int id) {
	    String sql = "SELECT * FROM CongTy WHERE IdCT = ?;";

	    try {
	        Connection conn = DBConnection.getConnection();
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setInt(1, id);
	        ResultSet rs = statement.executeQuery();

	        while (rs.next()) {
	            CongTy congTy = new CongTy(
	                rs.getInt("IdCT"),
	                rs.getString("TenCongTy"),
	                rs.getString("SDT"),
	                rs.getString("Email"),
	                rs.getString("MaSoThue"),
	                rs.getString("LinhVuc"),
	                rs.getString("QuyMoNhanSu"),
	                rs.getString("TinhThanh"),
	                rs.getString("DiaChi"),
	                rs.getString("URL"),
	                rs.getString("GioiThieu"),
	                rs.getString("Logo"),
	                rs.getString("Background")
	            );

	            return congTy;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static List<String> getHinhAnhHoatDong(int id)
	{
		String sqlcmd = "select duongDan from HinhAnhHoatDong where idCongTy = ?";
		List<String> images = new ArrayList<>();
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sqlcmd);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				images.add(rs.getString(1));
			}
			return images;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void addHinhAnhHoatDong(int id, String duongDan)
	{
		String sqlcmd = "insert into HinhAnhHoatDong (idCongTy, duongDan) values (?, ?)";
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sqlcmd);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, duongDan);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateThongtinCongTy(CongTy ct) {
	    String sqlcmd = "UPDATE CongTy SET TenCongTy = ?, SDT = ?, "
	            + "MaSoThue = ?, LinhVuc = ?, QuyMoNhanSu = ?, TinhThanh = ?, "
	            + "DiaChi = ?, URL = ?, GioiThieu = ? ";

	    // Nếu có logo, thêm phần logo vào câu lệnh SQL
	    if (ct.getLogo() != null) {
	        sqlcmd += ", Logo = ?";
	    }

	    // Thêm phần WHERE vào câu lệnh SQL
	    sqlcmd += " WHERE idCT = ?";

	    try {
	        Connection connection = DBConnection.getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(sqlcmd);
	        preparedStatement.setString(1, ct.getTenCongTy());
	        preparedStatement.setString(2, ct.getSdt());
	        preparedStatement.setString(3, ct.getMaSoThue());
	        preparedStatement.setString(4, ct.getLinhVuc());
	        preparedStatement.setString(5, ct.getQuyMoNhanSu());
	        preparedStatement.setString(6, ct.getTinhThanh());
	        preparedStatement.setString(7, ct.getDiaChi());
	        preparedStatement.setString(8, ct.getUrl());
	        preparedStatement.setString(9, ct.getGioiThieu());

	        // Nếu Logo không phải null, gán giá trị của Logo vào PreparedStatement
	        if (ct.getLogo() != null) {
	            preparedStatement.setString(10, ct.getLogo());
	            preparedStatement.setInt(11, ct.getIdCT());
	        }
	        else {
	        	preparedStatement.setInt(10, ct.getIdCT());
			}
	        preparedStatement.executeUpdate();
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void updateBackGround(String backGround, int id)
	{
		String sqlcmd = "update CongTy set Background = ? where IdCT = ?";
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sqlcmd);
			preparedStatement.setString(1, backGround);
			preparedStatement.setInt(2, id);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void addCongTyAfterSignUP(int id, String email)
    {
    	String sql = "insert into CongTy (IdCT, Email) values (?, ?)";
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
	public static List<String> getEmailOfEmployeesFromCompany(int idCT)
	{
		 List<String> emails = new ArrayList<String>();
		 String sql = "SELECT UV.email "
                 + "FROM UngVien UV "
                 + "JOIN CV cv ON UV.IdUV = cv.IdUV "
                 + "join HoSo hs on cv.IdCV = hs.IdCongViec "
                 + "JOIN CongViec ON HS.IdCongViec = CongViec.IdCongViec "
                 + "JOIN CongTy CT ON CongViec.IdCT = CT.IdCT "
                 + "WHERE CT.IdCT = ? and hs.TrangThai = N'Phỏng vấn'"; // Sử dụng tham số cho IdCT
		 try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idCT);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				emails.add(rs.getString(1));
			}
			return emails;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
