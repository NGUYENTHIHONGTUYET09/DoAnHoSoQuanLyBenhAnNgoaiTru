/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Date;
/**
 *
 * @author phamngochoang
 */
public class Thuoc {
    private int id;
    private String tenThuoc;
    private String nuocSX;
    private double donGia;
    private Date hsd;
    private int soLuongTon;
    private String trangThai;

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
   
    // Constructor
    public Thuoc(){};
    
    public Thuoc(int id, String tenThuoc, String nuocSX, double donGia, Date hsd, int soLuongTon, String trangThai) {
        this.id = id;
        this.tenThuoc = tenThuoc;
        this.nuocSX = nuocSX;
        this.donGia = donGia;
        this.hsd = hsd;
        this.soLuongTon = soLuongTon;
        this.trangThai = trangThai;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public String getNuocSX() {
        return nuocSX;
    }

    public void setNuocSX(String nuocSX) {
        this.nuocSX = nuocSX;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public Date getHsd() {
        return hsd;
    }

    public void setHsd(Date hsd) {
        this.hsd = hsd;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }
}