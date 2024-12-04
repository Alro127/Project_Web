package beans;

import java.sql.Timestamp;

public class HoSo {
	private int idCV;
	private int idCongViec;
	private Timestamp thoiGianGui;
	private String trangThai;
	
	public int getIdCV() {
		return idCV;
	}
	public void setIdCV(int idCV) {
		this.idCV = idCV;
	}
	public int getIdCongViec() {
		return idCongViec;
	}
	public void setIdCongViec(int idCongViec) {
		this.idCongViec = idCongViec;
	}
	public Timestamp getThoiGianGui() {
		return thoiGianGui;
	}
	public void setThoiGianGui(Timestamp thoiGianGui) {
		this.thoiGianGui = thoiGianGui;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	
	public HoSo(int idCV, int idCongViec, Timestamp thoiGianGui, String trangThai) {
		super();
		this.idCV = idCV;
		this.idCongViec = idCongViec;
		this.thoiGianGui = thoiGianGui;
		this.trangThai = trangThai;
	}
	
}
