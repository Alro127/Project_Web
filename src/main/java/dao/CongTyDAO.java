package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

}
