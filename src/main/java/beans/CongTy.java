package beans;

public class CongTy {
    private int idCT;                    
    private String tenCongTy;            
    private String sdt;                  
    private String email;               
    private String maSoThue;             
    private String linhVuc;              
    private String quyMoNhanSu;          
    private String tinhThanh;            
    private String diaChi;               
    private String url;                  
    private String gioiThieu;            
    private String logo;                
    private String background;           

    // Constructor
    
    public CongTy()
    {
    	
    }
    
    public CongTy(int idCT, String tenCongTy, String sdt, String email, String maSoThue, 
                  String linhVuc, String quyMoNhanSu, String tinhThanh, String diaChi, 
                  String url, String gioiThieu, String logo, String background) {
        this.idCT = idCT;
        this.tenCongTy = tenCongTy;
        this.sdt = sdt;
        this.email = email;
        this.maSoThue = maSoThue;
        this.linhVuc = linhVuc;
        this.quyMoNhanSu = quyMoNhanSu;
        this.tinhThanh = tinhThanh;
        this.diaChi = diaChi;
        this.url = url;
        this.gioiThieu = gioiThieu;
        this.logo = logo;
        this.background = background;
    }

    // Getters and Setters
    public int getIdCT() {
        return idCT;
    }

    public void setIdCT(int idCT) {
        this.idCT = idCT;
    }

    public String getTenCongTy() {
        return tenCongTy;
    }

    public void setTenCongTy(String tenCongTy) {
        this.tenCongTy = tenCongTy;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMaSoThue() {
        return maSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        this.maSoThue = maSoThue;
    }

    public String getLinhVuc() {
        return linhVuc;
    }

    public void setLinhVuc(String linhVuc) {
        this.linhVuc = linhVuc;
    }

    public String getQuyMoNhanSu() {
        return quyMoNhanSu;
    }

    public void setQuyMoNhanSu(String quyMoNhanSu) {
        this.quyMoNhanSu = quyMoNhanSu;
    }

    public String getTinhThanh() {
        return tinhThanh;
    }

    public void setTinhThanh(String tinhThanh) {
        this.tinhThanh = tinhThanh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGioiThieu() {
        return gioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        this.gioiThieu = gioiThieu;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    // ToString method
    @Override
    public String toString() {
        return "CongTy{" +
                "idCT=" + idCT +
                ", tenCongTy='" + tenCongTy + '\'' +
                ", sdt='" + sdt + '\'' +
                ", email='" + email + '\'' +
                ", maSoThue='" + maSoThue + '\'' +
                ", linhVuc='" + linhVuc + '\'' +
                ", quyMoNhanSu='" + quyMoNhanSu + '\'' +
                ", tinhThanh='" + tinhThanh + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", url='" + url + '\'' +
                ", gioiThieu='" + gioiThieu + '\'' +
                ", logo='" + logo + '\'' +
                ", background='" + background + '\'' +
                '}';
    }
}

