package beans;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
	public static List<HoSo> LocTheoLinhVuc(String linhVuc, List<HoSo> hoSos) {
		if (linhVuc != null && !linhVuc.isEmpty()) {
			hoSos = hoSos.stream()
	                             .filter(hs -> linhVuc.equals(hs.getCongViec().getLinhVuc()))
	                             .collect(Collectors.toList());
	    }
	 return hoSos;
	}
	public static List<HoSo> LocTheoThoiGian(String thoiGian, List<HoSo> hoSos) {
		if (thoiGian != null && !thoiGian.isEmpty()) {
			 if ("1".equals(thoiGian)) {
			        // Sắp xếp theo thời gian từ mới nhất đến cũ nhất
			        hoSos = hoSos.stream()
			                             .sorted(Comparator.comparing(HoSo::getThoiGianGui).reversed()) // Sắp xếp theo thời gian đăng
			                             .collect(Collectors.toList());
			    } else if ("2".equals(thoiGian)) {
			        // Sắp xếp theo thời gian từ cũ nhất đến mới nhất
			        hoSos = hoSos.stream()
			                             .sorted(Comparator.comparing(HoSo::getThoiGianGui)) // Sắp xếp theo thời gian đăng
			                             .collect(Collectors.toList());
			    }
		}
		return hoSos;
	}
	public static List<HoSo> LocTheoTrangThai(String trangThai, List<HoSo> hoSos) {
		if (trangThai != null && !trangThai.isEmpty()) {
			hoSos = hoSos.stream()
	                             .filter(hs -> trangThai.equals(hs.getTrangThai()))
	                             .collect(Collectors.toList());
	    }
	 return hoSos;
	}
	public static List<HoSo> LocTheoLuotNop(String luotNop, List<HoSo> hoSos)
	{
		if (luotNop != null && !luotNop.isEmpty()) {
		    if ("1".equals(luotNop)) {
		        // Sắp xếp theo lượt nộp cao nhất tới thấp nhất
		        hoSos.sort(Comparator.comparing(hoSo -> hoSo.getCongViec().getLuotNop(), Comparator.reverseOrder()));
		    } else if ("2".equals(luotNop)) {
		        // Sắp xếp theo lượt nộp thấp nhất tới cao nhất
		        hoSos.sort(Comparator.comparing(hoSo -> hoSo.getCongViec().getLuotNop()));
		    }
		}
		return hoSos;
	}
	public static List<HoSo> locTheoHoTen(String searchText, List<HoSo> hoSos) {
		if (searchText != null && !searchText.isEmpty()) {
	        hoSos = hoSos.stream()
	                             .filter(hs -> hs.getCv().getUngvien().getFullName().toLowerCase().contains(searchText.toLowerCase())) // So sánh không phân biệt chữ hoa chữ thường
	                             .collect(Collectors.toList());
		}
		return hoSos;
	}
}
