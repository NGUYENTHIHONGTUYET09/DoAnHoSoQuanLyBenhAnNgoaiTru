/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Thuoc;
import DTO.CTDonThuoc;
import DTO.ToaThuoc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import connectSql.connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author phamngochoang
 */
public class ThuocDAO {
    public ArrayList<Thuoc> getAllThuocs() {
        ArrayList<Thuoc> thuocs = new ArrayList<>();
        
        try {
            Connection con = connect.getConnection();
            String sql = "SELECT * FROM Thuoc";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Thuoc thuoc = new Thuoc();
                
                thuoc.setId(rs.getInt("id"));
                thuoc.setTenThuoc(rs.getString("TENTHUOC"));
                thuoc.setNuocSX(rs.getString("NUOCSX"));
                thuoc.setDonGia(rs.getDouble("DONGIA"));
                thuoc.setHsd(rs.getDate("HSD"));
                thuoc.setSoLuongTon(rs.getInt("SOLUONGTON"));
                thuoc.setTrangThai(rs.getString("TRANGTHAI"));
                           

                // Add populated object to list
                thuocs.add(thuoc);
            }
        } catch (SQLException e) {
            // Handle or log the exception as needed
            
        }
        return thuocs;
    }
    
    public ArrayList<Thuoc> timThuocbyTenThuocs(String tenThuoc)
    {
        ArrayList<Thuoc> thuocs = new ArrayList<>();
        try {
            Connection con = connect.getConnection();
            String sql = "SELECT * FROM THUOC  WHERE THUOC.TENTHUOC = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenThuoc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Thuoc thuoc = new Thuoc();
                
                thuoc.setId(rs.getInt("id"));
                thuoc.setTenThuoc(rs.getString("TENTHUOC"));
                thuoc.setNuocSX(rs.getString("NUOCSX"));
                thuoc.setDonGia(rs.getDouble("DONGIA"));
                thuoc.setHsd(rs.getDate("HSD"));
                thuoc.setSoLuongTon(rs.getInt("SOLUONGTON"));
                thuoc.setTrangThai(rs.getString("TRANGTHAI"));
                               
                
                thuocs.add(thuoc);
            }
        } catch (SQLException e) {
            // Handle or log the exception as needed           
        }
        return thuocs;
    }
    
    public int addThuoc(Thuoc thuoc){
        int rs = 0;
        try {
            Connection con = connect.getConnection();
            String sql = "INSERT INTO THUOC(TENTHUOC, NUOCSX, DONGIA, HSD, SOLUONGTON,TRANGTHAI) VALUES (?, ? ,? , ?, ?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, thuoc.getTenThuoc());
            ps.setString(2, thuoc.getNuocSX());
            ps.setDouble(3, thuoc.getDonGia());
            ps.setDate(4, thuoc.getHsd());
            ps.setInt(5, thuoc.getSoLuongTon());
            ps.setString(6, thuoc.getTrangThai());
            
            rs = ps.executeUpdate();
        } catch (SQLException e) {
               e.printStackTrace();
       
            
        }
        return rs;
    }
    
    public boolean xoaThuoc(int id) {
        int result = 0;
        try {
            Connection con = connect.getConnection();
            String sql = "DELETE FROM Thuoc WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            
            result = st.executeUpdate();
            connect.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }
    
    public Thuoc getThuocByID(int id)
    {
//        ArrayList<PhieuKhamBenh> phieuKhamBenhs = new ArrayList<>();
        
        try {
            Connection con = connect.getConnection();
            String sql = "SELECT * FROM Thuoc Where id = ?;";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Thuoc thuoc = new Thuoc();
                
                thuoc.setId(rs.getInt("id"));
                thuoc.setTenThuoc(rs.getString("TENTHUOC"));
                thuoc.setNuocSX(rs.getString("NUOCSX"));
                thuoc.setDonGia(rs.getDouble("DONGIA"));
                thuoc.setHsd(rs.getDate("HSD"));
                thuoc.setSoLuongTon(rs.getInt("SOLUONGTON"));  
                thuoc.setTrangThai(rs.getString("TRANGTHAI"));
                
                return thuoc;
            }
        } catch (SQLException e) {
            // Handle or log the exception as needed
            
        }
        return null;
    }
    
    public void suaThuoc(Thuoc thuoc){
                
        try {
            Connection con = connect.getConnection();
            String sql = "UPDATE THUOC SET TENTHUOC = ?, NUOCSX = ?, DONGIA = ?, HSD = ?, SOLUONGTON = ?, TRANGTHAI = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            
//            ps.setString(1, phieuKhamBenh.getMAPKB());
            ps.setString(1, thuoc.getTenThuoc());
            ps.setString(2, thuoc.getNuocSX());
            ps.setDouble(3, thuoc.getDonGia());
            ps.setDate(4, thuoc.getHsd());
            ps.setInt(5, thuoc.getSoLuongTon());
            ps.setString(6, thuoc.getTrangThai());
            ps.setInt(7, thuoc.getId());
            
            int rs = ps.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            // Handle or log the exception as needed           
        }
    }
    
    public void suaThuoc1(Thuoc thuoc) {
        try {
        	   Connection con = connect.getConnection();
            String sql = "{CALL sp_CapNhatSLT(?, ?)}"; // Gọi đúng tên stored procedure
            CallableStatement cs = con.prepareCall(sql);

            // Đặt các giá trị tham số
            cs.setInt(1, thuoc.getId()); // Đặt id của thuốc
            cs.setInt(2, thuoc.getSoLuongTon()); // Đặt số lượng tồn mới

            // Thực thi stored procedure
            cs.executeUpdate();
            System.out.println("Số lượng tồn đã được cập nhật thành công.");
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý hoặc ghi log cho ngoại lệ khi cần thiết
        }
    }

    // Phần còn lại của lớp bạn
}


