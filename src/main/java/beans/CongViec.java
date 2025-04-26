package beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.checkerframework.common.value.qual.StaticallyExecutable;

public class CongViec {

	private int idCongViec;
	private int idCT;
	private String ten;
	private String diaDiem;
	private double luong;
	private int namKinhNghiem;
	private String linhVuc;
	private Timestamp thoiGianDang;
	private Timestamp thoiGianHetHan;
	private String moTa;
	private String yeuCau;
	private String quyenLoi;
	private int luotXem;
	private int luotNop;
	
	private String tenCongTy;
	private String logo;
	
	public String getTenCongTy() {
		return tenCongTy;
	}

	public void setTenCongTy(String tenCongTy) {
		this.tenCongTy = tenCongTy;
	}

	public int getIdCongViec() {
		return idCongViec;
	}

	public void setIdCongViec(int idCongViec) {
		this.idCongViec = idCongViec;
	}

	public int getIdCT() {
		return idCT;
	}

	public void setIdCT(int idCT) {
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

	public int getNamKinhNghiem() {
		return namKinhNghiem;
	}

	public void setNamKinhNghiem(int namKinhNghiem) {
		this.namKinhNghiem = namKinhNghiem;
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

	public CongViec(int idCongViec, int idCT, String ten, String diaDiem, double luong, int namKinhNghiem,
			String linhVuc, Timestamp thoiGianDang, Timestamp thoiGianHetHan, String moTa, String yeuCau,
			String quyenLoi, int luotXem, int luotNop) {
		super();
		this.idCongViec = idCongViec;
		this.idCT = idCT;
		this.ten = ten;
		this.diaDiem = diaDiem;
		this.luong = luong;
		this.namKinhNghiem = namKinhNghiem;
		this.linhVuc = linhVuc;
		this.thoiGianDang = thoiGianDang;
		this.thoiGianHetHan = thoiGianHetHan;
		this.moTa = moTa;
		this.yeuCau = yeuCau;
		this.quyenLoi = quyenLoi;
		this.luotXem = luotXem;
		this.luotNop = luotNop;
	}

	public CongViec() {
	}

	// Phương thức tính độ tương đồng giữa công việc đã chọn và công việc hiện tại
	public double calculateSimilarity(CongViec selectedJob) {
		double similarityScore = 0.0;

		// Tính tương đồng về lĩnh vực (linhVuc)
		if (this.linhVuc.equalsIgnoreCase(selectedJob.linhVuc)) {
			similarityScore += 0.4; // Trọng số 40%
		}

		// Tính tương đồng về mức lương (luong)
		double salaryDifference = Math.abs(this.luong - selectedJob.luong);
		if (salaryDifference <= selectedJob.luong * 0.2) {
			similarityScore += 0.3; // Trọng số 30%
		}

		// Tính tương đồng về kinh nghiệm (namKinhNghiem)
		int experienceDifference = Math.abs(this.namKinhNghiem - selectedJob.namKinhNghiem);
		if (experienceDifference <= 2) {
			similarityScore += 0.3; // Trọng số 30%
		}

		return similarityScore;
	}

	// Phương thức sắp xếp các công việc theo độ tương đồng
	public static List<CongViec> sortBySimilarity(CongViec selectedJob, List<CongViec> jobs) {
		Collections.sort(jobs, new Comparator<CongViec>() {
			@Override
			public int compare(CongViec job1, CongViec job2) {
				double similarity1 = job1.calculateSimilarity(selectedJob);
				double similarity2 = job2.calculateSimilarity(selectedJob);
				return Double.compare(similarity2, similarity1); // Sắp xếp từ cao đến thấp
			}
		});
		return jobs;
	}

	@Override
	public String toString() {
		return "CongViec{" +
				"idCongViec=" + idCongViec +
				", ten='" + ten + '\'' +
				", idCT='" + idCT + '\'' +
				", luong=" + luong +
				", diaDiem='" + diaDiem + '\'' +
				", namKinhNghiem=" + namKinhNghiem +
				", linhVuc='" + linhVuc + '\'' +
				", thoiGianDang='" + thoiGianDang + '\'' +
				", thoiGianHetHan='" + thoiGianHetHan + '\'' +
				", moTa='" + moTa + '\'' +
				", yeuCau='" + yeuCau + '\'' +
				", quyenLoi='" + quyenLoi + '\'' +
				", luotXem=" + luotXem +
				", luotNop=" + luotNop +
				", tenCongTy=" + tenCongTy +
				", logo=" + logo +
				'}';
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public static List<CongViec> LocLinhVuc(List<CongViec> congViecs, String linhVuc) {
		 if (linhVuc != null && !linhVuc.isEmpty()) {
		        congViecs = congViecs.stream()
		                             .filter(cv -> linhVuc.equals(cv.getLinhVuc()))
		                             .collect(Collectors.toList());
		    }
		 return congViecs;
	}
	public static List<CongViec> LocTinhThanh(List<CongViec> congViecs, String tinhThanh) {
		if (tinhThanh != null && !tinhThanh.isEmpty()) {
	        congViecs = congViecs.stream()
	                             .filter(cv -> tinhThanh.equals(cv.getDiaDiem()))
	                             .collect(Collectors.toList());
	    }

		 return congViecs;
	}
	public static List<CongViec> LocTen(List<CongViec> congViecs, String ten) {
		if (ten != null && !ten.isEmpty()) {
	        congViecs = congViecs.stream()
	                             .filter(cv -> cv.getTen().toLowerCase().contains(ten.toLowerCase())) // So sánh không phân biệt chữ hoa chữ thường
	                             .collect(Collectors.toList());
	    }
		 return congViecs;
	}
	public static double findMaxLuong(List<CongViec> congViecs)
	{
		double luongMax = 0;
		for (CongViec congViec : congViecs) {
			if (congViec.getLuong() > luongMax)
			{
				luongMax = congViec.getLuong();
			}
		}
		return luongMax;
	}
	public static double findMinLuong(List<CongViec> congViecs)
	{
		double luongMin = CongViec.findMaxLuong(congViecs);
		for (CongViec congViec : congViecs) {
			if (congViec.getLuong() < luongMin)
			{
				luongMin = congViec.getLuong();
			}
		}
		return luongMin;
	}
	public static List<CongViec> findInRangeLuong(List<CongViec> congViecs, double minValue, double maxValue)
	{
		List<CongViec> result = new ArrayList<CongViec>();
		for (CongViec congViec : congViecs) {
			if (congViec.getLuong() >= minValue && congViec.getLuong() <= maxValue) {
				result.add(congViec);
			}
		}
		return result;
	}
	public static List<CongViec> findInExperince(List<CongViec> congViecs, int kinhNghiem)
	{
		List<CongViec> result = new ArrayList<CongViec>();
		for (CongViec congViec : congViecs) {
			if (congViec.getNamKinhNghiem() >= kinhNghiem) {
				result.add(congViec);
			}
		}
		return result;
	}
}
