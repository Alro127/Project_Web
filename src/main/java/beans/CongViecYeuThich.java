package beans;

public class CongViecYeuThich {
	private int idUV;
	private int idCongViec;
	
	public int getIdUV() {
		return idUV;
	}
	public void setIdUV(int idUV) {
		this.idUV = idUV;
	}
	public int getIdCongViec() {
		return idCongViec;
	}
	public void setIdCongViec(int idCongViec) {
		this.idCongViec = idCongViec;
	}
	
	public CongViecYeuThich(int idUV, int idCongViec) {
		super();
		this.idUV = idUV;
		this.idCongViec = idCongViec;
	}
}
