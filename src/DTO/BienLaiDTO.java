package DTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author HUNG
 */
public class BienLaiDTO {

    private int id;
    private String maBL;
    private String maNV_TT;
    private String maToa;
    private Date ngayTao;
    private double tongTienKham;
    private int nam;

    public BienLaiDTO() {
    }
    
    public BienLaiDTO(int id, String maBL, String maNV_TT, String maToa, Date ngayTao, double tongTienKham) {
        this.id = id;
        this.maBL = maBL;
        this.maNV_TT = maNV_TT;
        this.maToa = maToa;
        this.ngayTao = ngayTao;
        this.tongTienKham = tongTienKham;
    }
    

    public BienLaiDTO(String maNV_TT, String maToa, Date ngayTao, double tongTienKham) {
        this.maNV_TT = maNV_TT;
        this.maToa = maToa;
        this.ngayTao = ngayTao;
        this.tongTienKham = tongTienKham;
    }
    
    public String getFieldByIndex(int index) {
        switch (index) {
            case 0:
                return String.valueOf(id);
            case 1:
                return maBL;
            case 2:
                return maNV_TT;
            case 3:
                return maToa;
            case 4:
                return ngayTao.toString(); // Convert Date to String as needed
            case 5:
                return String.valueOf(tongTienKham);
            default:
                return ""; // or handle error appropriately
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getMaBL() {
        return maBL;
    }

    public void setMaBL(String maBL) {
        this.maBL = maBL;
    }

    public String getMaNV_TT() {
        return maNV_TT;
    }

    public void setMaNV_TT(String maNV_TT) {
        this.maNV_TT = maNV_TT;
    }

    public String getMaToa() {
        return maToa;
    }

    public void setMaToa(String maToa) {
        this.maToa = maToa;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public double getTongTienKham() {
        return tongTienKham;
    }

    public void setTongTienKham(double tongTienKham) {
        this.tongTienKham = tongTienKham;
    }

    @Override
    public String toString() {
        return "BienLaiDTO{" + "maBL=" + maBL + ", maNV_TT=" + maNV_TT + ", maToa=" + maToa + ", ngayTao=" + ngayTao + ", tongTienKham=" + tongTienKham + '}';
    }


    // Getters and setters for all properties

    public void setNam(int nam) {
        this.nam = nam;
    }

    public int getNam() {
        return nam;
    }
    
}