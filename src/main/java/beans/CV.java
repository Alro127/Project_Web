package beans;

import java.sql.Date;
import java.util.List;

public class CV {
	public int getIdCV() {
		return idCV;
	}

	public void setIdCV(int idCV) {
		this.idCV = idCV;
	}

	public int getIdUV() {
		return idUV;
	}

	public void setIdUV(int idUV) {
		this.idUV = idUV;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCareerGoals() {
		return careerGoals;
	}

	public void setCareerGoals(String careerGoals) {
		this.careerGoals = careerGoals;
	}

	public List<HocVan> getHocVan() {
		return hocVan;
	}

	public void setHocVan(List<HocVan> hocVan) {
		this.hocVan = hocVan;
	}

	public List<KinhNghiem> getKinhNghiem() {
		return kinhNghiem;
	}

	public void setKinhNghiem(List<KinhNghiem> kinhNghiem) {
		this.kinhNghiem = kinhNghiem;
	}

	public List<ChungChi> getChungChi() {
		return chungChi;
	}

	public void setChungChi(List<ChungChi> chungChi) {
		this.chungChi = chungChi;
	}

	public List<KyNang> getKyNang() {
		return kyNang;
	}

	public void setKyNang(List<KyNang> kyNang) {
		this.kyNang = kyNang;
	}

	public UngVien getUngvien() {
		return ungvien;
	}

	public void setUngvien(UngVien ungvien) {
		this.ungvien = ungvien;
	}

	private int idCV;
	private int idUV;
	private UngVien ungvien;
	private String position;
	private String careerGoals;

	// Học vấn
	private List<HocVan> hocVan;

	// Kinh nghiệm
	private List<KinhNghiem> kinhNghiem;

	// Chứng chỉ
	private List<ChungChi> chungChi; 
	// Kỹ năng
	private List<KyNang> kyNang;       



	// Constructor không tham số
	public CV() {
		super();
	}

	public CV(int idUV, String position, String careerGoals) {
		super();
		this.idUV = idUV;
		this.position = position;
		this.careerGoals = careerGoals;
	}


}
