package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.CV;
import beans.KyNang;
import conn.DBConnection;

public class KyNangDAO {
	public static void saveSkillList(CV cv, List<KyNang> kyNangs) throws SQLException {
        String sql = "INSERT INTO KyNang (idCV, name, level) VALUES (?, ?, ?)";
        try {
        	Connection conn = DBConnection.getConnection();
        	PreparedStatement ps = conn.prepareStatement(sql);
            for (KyNang kyNang : kyNangs) {
                ps.setInt(1, cv.getIdCV());
                ps.setString(2, kyNang.getName());
                ps.setString(3, kyNang.getLevel());
                ps.addBatch();
            }
            ps.executeBatch();
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
    }
	
	public static List<KyNang> getSkillListByCV(CV cv) throws SQLException {
        String sql = "SELECT * FROM KyNang WHERE idCV = ?";
        try {
        	Connection conn = DBConnection.getConnection();
        	PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cv.getIdCV());
            try (ResultSet rs = ps.executeQuery()) {
                List<KyNang> kyNangs = new ArrayList<>();
                while (rs.next()) {
                	int id = rs.getInt("id");
                	int idCV = rs.getInt("idCV");
                    String name = rs.getString("skillName");
                    String level = rs.getString("skillLevel");
                    KyNang kyNang = new KyNang(id, idCV, name, level);
                    kyNangs.add(kyNang);
                }
                return kyNangs;
            }
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        return null;
    }
}
