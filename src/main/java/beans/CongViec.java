package beans;

import java.sql.Timestamp;

public class CongViec {
	
	private int idCongViec;
    private String idCT; 
    private String ten; 
    private String diaDiem; 
    private double luong; 
    private int namKinhNghiemToiThieu; 
    private int namKinhNghiemToiDa; 
    private String linhVuc; 
    private Timestamp thoiGianDang; 
    private Timestamp thoiGianHetHan; 
    private String moTa; 
    private String yeuCau; 
    private String quyenLoi; 
    private int luotXem; 
    private int luotNop;
	public int getIdCongViec() {
		return idCongViec;
	}
	public void setIdCongViec(int idCongViec) {
		this.idCongViec = idCongViec;
	}
	public String getIdCT() {
		return idCT;
	}
	public void setIdCT(String idCT) {
		this.idCT = idCT;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getDiaDiem() {
		return diaDiem;
	}
	public void setDiaDiem(String diaDiem) {
		this.diaDiem = diaDiem;
	}
	public double getLuong() {
		return luong;
	}
	public void setLuong(double luong) {
		this.luong = luong;
	}
	public int getNamKinhNghiemToiThieu() {
		return namKinhNghiemToiThieu;
	}
	public void setNamKinhNghiemToiThieu(int namKinhNghiemToiThieu) {
		this.namKinhNghiemToiThieu = namKinhNghiemToiThieu;
	}
	public int getNamKinhNghiemToiDa() {
		return namKinhNghiemToiDa;
	}
	public void setNamKinhNghiemToiDa(int namKinhNghiemToiDa) {
		this.namKinhNghiemToiDa = namKinhNghiemToiDa;
	}
	public String getLinhVuc() {
		return linhVuc;
	}
	public void setLinhVuc(String linhVuc) {
		this.linhVuc = linhVuc;
	}
	public Timestamp getThoiGianDang() {
		return thoiGianDang;
	}
	public void setThoiGianDang(Timestamp thoiGianDang) {
		this.thoiGianDang = thoiGianDang;
	}
	public Timestamp getThoiGianHetHan() {
		return thoiGianHetHan;
	}
	public void setThoiGianHetHan(Timestamp thoiGianHetHan) {
		this.thoiGianHetHan = thoiGianHetHan;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public String getYeuCau() {
		return yeuCau;
	}
	public void setYeuCau(String yeuCau) {
		this.yeuCau = yeuCau;
	}
	public String getQuyenLoi() {
		return quyenLoi;
	}
	public void setQuyenLoi(String quyenLoi) {
		this.quyenLoi = quyenLoi;
	}
	public int getLuotXem() {
		return luotXem;
	}
	public void setLuotXem(int luotXem) {
		this.luotXem = luotXem;
	}
	public int getLuotNop() {
		return luotNop;
	}
	public void setLuotNop(int luotNop) {
		this.luotNop = luotNop;
	}
	public CongViec(int idCongViec, String idCT, String ten, String diaDiem, double luong, int namKinhNghiemToiThieu,
			int namKinhNghiemToiDa, String linhVuc, Timestamp thoiGianDang, Timestamp thoiGianHetHan, String moTa, String yeuCau,
			String quyenLoi, int luotXem, int luotNop) {
		super();
		this.idCongViec = idCongViec;
		this.idCT = idCT;
		this.ten = ten;
		this.diaDiem = diaDiem;
		this.luong = luong;
		this.namKinhNghiemToiThieu = namKinhNghiemToiThieu;
		this.namKinhNghiemToiDa = namKinhNghiemToiDa;
		this.linhVuc = linhVuc;
		this.thoiGianDang = thoiGianDang;
		this.thoiGianHetHan = thoiGianHetHan;
		this.moTa = moTa;
		this.yeuCau = yeuCau;
		this.quyenLoi = quyenLoi;
		this.luotXem = luotXem;
		this.luotNop = luotNop;
	} 
    public CongViec()
    {
    }
}
