package DTO;

public class TaiKhoan {

   private String hoTen;
    private int maSoNV;
    private String soDienThoai;
    private String email;
    private String matKhau;
    private String matkhauxacnhan;
    private String vaiTro;
    private String trangThai;
    
    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getMaSoNV() {
        return maSoNV;
    }

    public void setMaSoNV(int maSoNV) {
        this.maSoNV = maSoNV;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTRo) {
        this.vaiTro = vaiTRo;
    }

    public String getMatkhauxacnhan() {
        return matkhauxacnhan;
    }

    public void setMatkhauxacnhan(String matkhauxacnhan) {
        this.matkhauxacnhan = matkhauxacnhan;
    }

    // Constructors
    public TaiKhoan() {
    }

     public TaiKhoan(String hoTen, int maSoNV, String soDienThoai, String email, String matKhau, String matkhauxacnhan, String vaiTro, String trangThai) {
        this.hoTen = hoTen;
        this.maSoNV = maSoNV;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.matKhau = matKhau;
        this.matkhauxacnhan = matkhauxacnhan;
        this.vaiTro = vaiTro;
        this.trangThai = trangThai;
    }

   
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" + "hoTen=" + hoTen + ", maSoNV=" + maSoNV + ", soDienThoai=" + soDienThoai + ", email=" + email + ", matKhau=" + matKhau + ", matkhauxacnhan=" + matkhauxacnhan + ", vaiTro=" + vaiTro + ", trangThai=" + trangThai + '}';
    }

  

}
