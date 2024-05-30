/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Time;

/**
 *
 * @author phamngochoang
 */
public class PhongKham {
    private int id;
    private String maPK;
    private String tenPK;
    private String tenKhoa;
    private Time thoiGianBD;
    private Time thoiGianKT;

    // Constructors
    public PhongKham() {
    }

    public PhongKham(String maPK, String tenPK, String tenKhoa, Time thoiGianBD, Time thoiGianKT) {
        this.maPK = maPK;
        this.tenPK = tenPK;
        this.tenKhoa = tenKhoa;
        this.thoiGianBD = thoiGianBD;
        this.thoiGianKT = thoiGianKT;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaPK() {
        return maPK;
    }

    public void setMaPK(String maPK) {
        this.maPK = maPK;
    }

    public String getTenPK() {
        return tenPK;
    }

    public void setTenPK(String tenPK) {
        this.tenPK = tenPK;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public Time getThoiGianBD() {
        return thoiGianBD;
    }

    public void setThoiGianBD(Time thoiGianBD) {
        this.thoiGianBD = thoiGianBD;
    }

    public Time getThoiGianKT() {
        return thoiGianKT;
    }

    public void setThoiGianKT(Time thoiGianKT) {
        this.thoiGianKT = thoiGianKT;
    }
}