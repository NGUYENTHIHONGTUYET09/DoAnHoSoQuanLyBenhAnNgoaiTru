package DTO;

import java.io.Serializable;
import java.sql.Date;

public class BenhNhan implements Serializable {
    private int id;
    private String maBN;
    private String tenBN;
    private String sdt;
    private Date ngaySinh;
    private String diaChi;
    private String gioiTinh;
    private int queQuan; // Thay đổi từ String sang int để lưu trữ ID của tỉnh
    private String ghiChu;
    private int soLuong;  // New field for grouping

    public BenhNhan() {
    }

    public BenhNhan(String tenBN, String sdt, Date ngaySinh, String diaChi, int queQuan, String gioiTinh, String ghiChu) {
        this.tenBN = tenBN;
        this.sdt = sdt;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.queQuan = queQuan;
        this.gioiTinh = gioiTinh;
        this.ghiChu = ghiChu;
    }

    // Add getter and setter for soLuong
    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(int queQuan) {
        this.queQuan = queQuan;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getMaBN() {
        return maBN;
    }

    public void setMaBN(String maBN) {
        this.maBN = maBN;
    }

    public String getTenBN() {
        return tenBN;
    }

    public void setTenBN(String tenBN) {
        this.tenBN = tenBN;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "BenhNhan{" + "id=" + id + ", maBN=" + maBN + ", tenBN=" + tenBN + ", sdt=" + sdt + ", ngaySinh=" + ngaySinh + ", diaChi=" + diaChi + ", gioiTinh=" + gioiTinh + ", queQuan=" + queQuan + ", ghiChu=" + ghiChu + ", soLuong=" + soLuong + '}';
    }
    
    
    
}
