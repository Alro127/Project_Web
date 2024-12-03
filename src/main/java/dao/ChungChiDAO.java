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
                    String name = rs.getString("certificateName");
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
}
