package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.CongViec;
import conn.DBConnection;

public class CongViecDAO {
	public static List<CongViec> GetListCongViec () throws SQLException, ClassNotFoundException
	{
		String sql = "select * from CongViec;";
		List <CongViec> congViecs = new ArrayList<CongViec>();
		try 
		{
			Connection conn = DBConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
			    CongViec congViec = new CongViec();

			    // Lấy dữ liệu từ ResultSet và gán vào đối tượng CongViec
			    congViec.setIdCongViec(rs.getInt("IdCongViec"));
			    congViec.setIdCT(rs.getString("IdCT"));
			    congViec.setTen(rs.getString("Ten"));
			    congViec.setDiaDiem(rs.getString("DiaDiem"));
			    congViec.setLuong(rs.getDouble("Luong"));
			    congViec.setNamKinhNghiemToiThieu(rs.getInt("NamKinhNghiemToiThieu"));
			    congViec.setNamKinhNghiemToiDa(rs.getInt("NamKinhNghiemToiDa"));
			    congViec.setLinhVuc(rs.getString("LinhVuc"));
			    congViec.setThoiGianDang(rs.getTimestamp("ThoiGianDang"));
			    congViec.setThoiGianHetHan(rs.getTimestamp("ThoiGianHetHan"));
			    congViec.setMoTa(rs.getString("MoTa"));
			    congViec.setYeuCau(rs.getString("YeuCau"));
			    congViec.setQuyenLoi(rs.getString("QuyenLoi"));
			    congViec.setLuotXem(rs.getInt("LuotXem"));
			    congViec.setLuotNop(rs.getInt("LuotNop"));

			    // Thêm đối tượng vào danh sách
			    congViecs.add(congViec);
			}

		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return congViecs;
	}

}
