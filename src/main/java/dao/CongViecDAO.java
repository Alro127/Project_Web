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
	public static List<CongViec> GetListCongViec() throws SQLException, ClassNotFoundException {
		String sql = "select * from CongViec;";
		List<CongViec> congViecs = new ArrayList<CongViec>();
		try {
			Connection conn = DBConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				CongViec congViec = new CongViec();

				// Lấy dữ liệu từ ResultSet và gán vào đối tượng CongViec
				congViec.setIdCongViec(rs.getInt("IdCongViec"));
				congViec.setIdCT(rs.getInt("IdCT"));
				congViec.setTen(rs.getString("Ten"));
				congViec.setDiaDiem(rs.getString("DiaDiem"));
				congViec.setLuong(rs.getDouble("Luong"));
				congViec.setNamKinhNghiem(rs.getInt("NamKinhNghiem"));
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

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return congViecs;
	}

	public static boolean AddCongViecMoi(CongViec congViec) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO CongViec (IdCT, Ten, DiaDiem, Luong, NamKinhNghiem, LinhVuc, ThoiGianHetHan, MoTa, YeuCau, QuyenLoi) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		boolean rs = false;
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, congViec.getIdCT());
			statement.setString(2, congViec.getTen());
			statement.setString(3, congViec.getDiaDiem());
			statement.setDouble(4, congViec.getLuong());
			statement.setInt(5, congViec.getNamKinhNghiem());
			statement.setString(6, congViec.getLinhVuc());
			statement.setTimestamp(7, congViec.getThoiGianHetHan());
			statement.setString(8, congViec.getMoTa());
			statement.setString(9, congViec.getYeuCau());
			statement.setString(10, congViec.getQuyenLoi());

			rs = statement.executeUpdate() > 0;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	public static CongViec getCongViecById(int id) {
		String sql = "select * from CongViec where IdCongViec = ?;";
        
		try 
		{
			Connection conn = DBConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				CongViec congViec = new CongViec();

				// Lấy dữ liệu từ ResultSet và gán vào đối tượng CongViec
				congViec.setIdCongViec(rs.getInt("IdCongViec"));
				congViec.setIdCT(rs.getInt("IdCT"));
				congViec.setTen(rs.getString("Ten"));
				congViec.setDiaDiem(rs.getString("DiaDiem"));
				congViec.setLuong(rs.getDouble("Luong"));
				congViec.setNamKinhNghiem(rs.getInt("NamKinhNghiem"));
				congViec.setLinhVuc(rs.getString("LinhVuc"));
				congViec.setThoiGianDang(rs.getTimestamp("ThoiGianDang"));
				congViec.setThoiGianHetHan(rs.getTimestamp("ThoiGianHetHan"));
				congViec.setMoTa(rs.getString("MoTa"));
				congViec.setYeuCau(rs.getString("YeuCau"));
				congViec.setQuyenLoi(rs.getString("QuyenLoi"));
				congViec.setLuotXem(rs.getInt("LuotXem"));
				congViec.setLuotNop(rs.getInt("LuotNop"));
				
				return congViec;
			}
		}
        catch (Exception e)
		{
        	e.printStackTrace();
		}
        return null; 
    }
	public static List<CongViec> GetListCongViecByIdCT(int id) throws SQLException, ClassNotFoundException {
		String sql = "select * from CongViec where IdCT = ?;";
		List<CongViec> congViecs = new ArrayList<CongViec>();
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				CongViec congViec = new CongViec();

				// Lấy dữ liệu từ ResultSet và gán vào đối tượng CongViec
				congViec.setIdCongViec(rs.getInt("IdCongViec"));
				congViec.setIdCT(rs.getInt("IdCT"));
				congViec.setTen(rs.getString("Ten"));
				congViec.setDiaDiem(rs.getString("DiaDiem"));
				congViec.setLuong(rs.getDouble("Luong"));
				congViec.setNamKinhNghiem(rs.getInt("NamKinhNghiem"));
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

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return congViecs;
	}

	public static List<String> getListLinhVuc() {
	    List<String> linhVucs = new ArrayList<>();
	    String sqlcmd = "SELECT DISTINCT LinhVuc FROM CongViec";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sqlcmd);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            linhVucs.add(rs.getString("LinhVuc"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return linhVucs;
	}
	
	public static List<String> getListTinhThanh()
	{
		List<String> tinhThanhs = new ArrayList<>();
	    String sqlcmd = "SELECT DISTINCT DiaDiem FROM CongViec";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sqlcmd);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	        	tinhThanhs.add(rs.getString("DiaDiem"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return tinhThanhs;
	}
}
