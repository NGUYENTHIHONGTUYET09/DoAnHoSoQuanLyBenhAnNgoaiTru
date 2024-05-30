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
public class PhieuKhamBenh {
private int id;
    private String MAPKB;
    private int MABS;
    private int MABN;
    private int MAPK;
    private Date NGAYTAO;
    private String CHANDOAN;

    // Constructor
    public PhieuKhamBenh() {
    }
    
    public PhieuKhamBenh(int id, String MAPKB, int MABS, int MABN, int MAPK, Date NGAYTAO, String CHANDOAN) {
        this.id = id;
        this.MAPKB = MAPKB;
        this.MABS = MABS;
        this.MABN = MABN;
        this.MAPK = MAPK;
        this.NGAYTAO = NGAYTAO;
        this.CHANDOAN = CHANDOAN;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMAPKB() {
        return MAPKB;
    }

    public void setMAPKB(String MAPKB) {
        this.MAPKB = MAPKB;
    }

    public int getMABS() {
        return MABS;
    }

    public void setMABS(int MABS) {
        this.MABS = MABS;
    }

    public int getMABN() {
        return MABN;
    }

    public void setMABN(int MABN) {
        this.MABN = MABN;
    }

    public int getMAPK() {
        return MAPK;
    }

    public void setMAPK(int MAPK) {
        this.MAPK = MAPK;
    }

    public Date getNGAYTAO() {
        return NGAYTAO;
    }

    public void setNGAYTAO(Date NGAYTAO) {
        this.NGAYTAO = NGAYTAO;
    }

    public String getCHANDOAN() {
        return CHANDOAN;
    }

    public void setCHANDOAN(String CHANDOAN) {
        this.CHANDOAN = CHANDOAN;
    }

    @Override
    public String toString() {
        return "PhieuKhamBenh{" + "id=" + id + ", MAPKB=" + MAPKB + ", MABS=" + MABS + ", MABN=" + MABN + ", MAPK=" + MAPK + ", NGAYTAO=" + NGAYTAO + ", CHANDOAN=" + CHANDOAN + '}';
    }

 
    
    
    
}