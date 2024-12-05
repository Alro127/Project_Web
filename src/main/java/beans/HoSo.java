package beans;

import java.sql.Timestamp;

public class HoSo {
	private int idCV;
	private int idCongViec;
	private Timestamp thoiGianGui;
	private String trangThai;
	
	private CV cv;
	private CongViec congViec;
	
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
	
	
	public CV getCv() {
		return cv;
	}
	public void setCv(CV cv) {
		this.cv = cv;
	}
	public CongViec getCongViec() {
		return congViec;
	}
	public void setCongViec(CongViec congViec) {
		this.congViec = congViec;
	}
	
	public HoSo(int idCV, int idCongViec, Timestamp thoiGianGui, String trangThai) {
		super();
		this.idCV = idCV;
		this.idCongViec = idCongViec;
		this.thoiGianGui = thoiGianGui;
		this.trangThai = trangThai;
	}
	
	
	public HoSo(int idCV, int idCongViec, Timestamp thoiGianGui, String trangThai, CV cv, CongViec congViec) {
		super();
		this.idCV = idCV;
		this.idCongViec = idCongViec;
		this.thoiGianGui = thoiGianGui;
		this.trangThai = trangThai;
		this.cv = cv;
		this.congViec = congViec;
	}
	public HoSo() {
		// TODO Auto-generated constructor stub
	}
	
}
