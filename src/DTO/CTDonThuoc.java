/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author phamngochoang
 */
public class CTDonThuoc {
    private int maThuoc;
    private int maToa;
    private int soLuong;
    private String ghiChu;
    private String tenThuoc;

    // Constructors
    public CTDonThuoc() {
    }
    
    public CTDonThuoc(int maThuoc, String tenThuoc) {
    	this.tenThuoc = tenThuoc;
    	this.maThuoc = maThuoc;
    	this.soLuong = 1;
    	this.ghiChu = "";
    }

    public CTDonThuoc(int maThuoc, int maToa, int soLuong, String ghiChu) {
        this.maThuoc = maThuoc;
        this.maToa = maToa;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
    }

    // Getters and setters
    public int getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    public int getMaToa() {
        return maToa;
    }

    public void setMaToa(int maToa) {
        this.maToa = maToa;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }
}
