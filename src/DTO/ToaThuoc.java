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
public class ToaThuoc {
    private int id;
    private String maToa;
    private int maBS;
    private int maBN;
    private double tongTien;
    private Date ngayLap;
    private int maPKB;



    // Constructor
    public ToaThuoc() {
        // Default constructor
    }
    
    public ToaThuoc(int id, String maToa, int maBS, int maBN, double tongTien, Date ngayLap, int maPKB) {
        this.id = id;
        this.maToa = maToa;
        this.maBS = maBS;
        this.maBN = maBN;
        this.tongTien = tongTien;
        this.ngayLap = ngayLap;
        this.maPKB = maPKB;
    }
        
    public int getMaPKB() {
        return maPKB;
    }

    public void setMaPKB(int maPKB) {
        this.maPKB = maPKB;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaToa() {
        return maToa;
    }

    public void setMaToa(String maToa) {
        this.maToa = maToa;
    }

    public int getMaBS() {
        return maBS;
    }

    public void setMaBS(int maBS) {
        this.maBS = maBS;
    }

    public int getMaBN() {
        return maBN;
    }

    public void setMaBN(int maBN) {
        this.maBN = maBN;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }
}
