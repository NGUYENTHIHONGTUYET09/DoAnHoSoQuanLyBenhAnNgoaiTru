/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhanVien;
import DTO.PhieuKhamBenh;

import java.sql.Connection;
import java.util.ArrayList;
import connectSql.connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;
/**
 *
 * @author phamngochoang
 */
public class PhieuKhamBenhDAO {
    public ArrayList<PhieuKhamBenh> getAllPhieuKhamBenhs() {
        ArrayList<PhieuKhamBenh> phieuKhamBenhs = new ArrayList<>();
        
        try {
            Connection con = connect.getConnection();
            String sql = "SELECT * FROM PhieuKhamBenh";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PhieuKhamBenh phieuKhamBenh = new PhieuKhamBenh();
                
                phieuKhamBenh.setId(rs.getInt("id"));
                phieuKhamBenh.setMAPKB(rs.getString("maPKB"));
                phieuKhamBenh.setMABS(rs.getInt("maBS"));
                phieuKhamBenh.setMABN(rs.getInt("maBN"));
                phieuKhamBenh.setMAPK(rs.getInt("maBN"));
                phieuKhamBenh.setNGAYTAO(rs.getDate("ngayTao"));
                phieuKhamBenh.setCHANDOAN(rs.getString("chanDoan"));               

                // Add populated object to list
                phieuKhamBenhs.add(phieuKhamBenh);
            }
        } catch (SQLException e) {
            // Handle or log the exception as needed
            
        }
        return phieuKhamBenhs;
    }
    
    public int addPKB(PhieuKhamBenh phieuKhamBenh){
        int generatedId = 0;
        try {
            Connection con = connect.getConnection();
            String sql = "INSERT INTO PhieuKhamBenh(maBS, maBN, maPK, NGAYTAO, CHANDOAN) VALUES (?, ? ,? , ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, phieuKhamBenh.getMABS());
            ps.setInt(2, phieuKhamBenh.getMABN());
            ps.setInt(3, phieuKhamBenh.getMAPK());
            ps.setDate(4, phieuKhamBenh.getNGAYTAO());
            ps.setString(5, phieuKhamBenh.getCHANDOAN());
            
            ps.executeUpdate();
            
            
        } catch (SQLException e) {
            // Handle or log the exception as needed
            e.printStackTrace();
        }
        return generatedId;
    }

    public int xoaPKB(int id){
        int result = 0;
        try {
            Connection con = connect.getConnection();
            String sql = "DELETE from PhieuKhamBenh WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setInt(1, id);
            
            result = st.executeUpdate();
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có : " + result + " bản ghi đã thay đổi");
//            connect.closeConnection(con);
        } catch (SQLException e) {
        }

        return result;
    }
    
    public PhieuKhamBenh getPKBbyID(int id)
    {
//        ArrayList<PhieuKhamBenh> phieuKhamBenhs = new ArrayList<>();
        
        try {
            Connection con = connect.getConnection();
            String sql = "SELECT * FROM PhieuKhamBenh Where id = ?;";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PhieuKhamBenh phieuKhamBenh = new PhieuKhamBenh();
                
                phieuKhamBenh.setId(rs.getInt("id"));
                phieuKhamBenh.setMAPKB(rs.getString("maPKB"));
                phieuKhamBenh.setMABS(rs.getInt("maBS"));
                phieuKhamBenh.setMABN(rs.getInt("maBN"));
                phieuKhamBenh.setMAPK(rs.getInt("maPK"));
                phieuKhamBenh.setNGAYTAO(rs.getDate("ngayTao"));
                phieuKhamBenh.setCHANDOAN(rs.getString("chanDoan"));               

                
                return phieuKhamBenh;
            }
        } catch (SQLException e) {
            // Handle or log the exception as needed
            
        }
        return null;
    }
    
    public void suaPKB(PhieuKhamBenh phieuKhamBenh){
                
        try {
            Connection con = connect.getConnection();
            String sql = "UPDATE PhieuKhamBenh SET MaBS = ?, MaBN = ?, MaPK = ?, ngayTao = ?, chanDoan = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

//            ps.setString(1, phieuKhamBenh.getMAPKB());
            ps.setInt(1, phieuKhamBenh.getMABS());
            ps.setInt(2, phieuKhamBenh.getMABN());
            ps.setInt(3, phieuKhamBenh.getMAPK());
            ps.setDate(4, phieuKhamBenh.getNGAYTAO());
            ps.setString(5, phieuKhamBenh.getCHANDOAN());
            ps.setInt(6, phieuKhamBenh.getId());
            
            int rs = ps.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            // Handle or log the exception as needed           
        }
    }
    
    public PhieuKhamBenh timPKBbyMaPKB(String maPKB)
    {
        try {
            Connection con = connect.getConnection();
            String sql = "SELECT * FROM PhieuKhamBenh WHERE MAPKB = ?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maPKB);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhieuKhamBenh phieuKhamBenh = new PhieuKhamBenh();
                
                phieuKhamBenh.setId(rs.getInt("id"));
                phieuKhamBenh.setMAPKB(rs.getString("maPKB"));
                phieuKhamBenh.setMABS(rs.getInt("maBS"));
                phieuKhamBenh.setMABN(rs.getInt("maBN"));
                phieuKhamBenh.setMAPK(rs.getInt("maPK"));
                phieuKhamBenh.setNGAYTAO(rs.getDate("ngayTao"));
                phieuKhamBenh.setCHANDOAN(rs.getString("chanDoan"));               

                
                return phieuKhamBenh;
            }
        } catch (SQLException e) {
            // Handle or log the exception as needed           
        }
        return null;
    }
    
    public ArrayList<PhieuKhamBenh> timPKBbyTenBN(String tenBN)
    {
        ArrayList<PhieuKhamBenh> phieuKhamBenhs = new ArrayList<>();
        try {
            Connection con = connect.getConnection();
            String sql = "SELECT PHIEUKHAMBENH.* FROM PHIEUKHAMBENH, BenhNhan WHERE PHIEUKHAMBENH.MABN = BenhNhan.id And BenhNhan.tenBN = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenBN);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhieuKhamBenh phieuKhamBenh = new PhieuKhamBenh();
                
                phieuKhamBenh.setId(rs.getInt("id"));
                phieuKhamBenh.setMAPKB(rs.getString("maPKB"));
                phieuKhamBenh.setMABS(rs.getInt("maBS"));
                phieuKhamBenh.setMABN(rs.getInt("maBN"));
                phieuKhamBenh.setMAPK(rs.getInt("maPK"));
                phieuKhamBenh.setNGAYTAO(rs.getDate("ngayTao"));
                phieuKhamBenh.setCHANDOAN(rs.getString("chanDoan"));               

                
                phieuKhamBenhs.add(phieuKhamBenh);
                
                
            }

        } catch (SQLException e) {
            // Handle or log the exception as needed           
        }
        return phieuKhamBenhs;
    }
    
//    public ArrayList<PhieuKhamBenh> getAllPKBs() {
//        ArrayList<PhieuKhamBenh> phieuKhamBenhs = new ArrayList<>();
//        
//        try {
//            Connection con = connect.getConnection();
//            String sql = """
//                         SELECT PHIEUKHAMBENH.id, PHIEUKHAMBENH.MAPKB, NhanVien.MANV, BenhNhan.maBN, PHONGKHAM.MAPK, PHIEUKHAMBENH.NGAYTAO, PHIEUKHAMBENH.CHANDOAN
//                         FROM PHIEUKHAMBENH, NhanVien, BenhNhan, PHONGKHAM
//                         WHERE PHIEUKHAMBENH.MABS = NhanVien.id AND PHIEUKHAMBENH.MABN = BenhNhan.id AND PHIEUKHAMBENH.MAPK = PHONGKHAM.id""";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                PhieuKhamBenh phieuKhamBenh = new PhieuKhamBenh();
//                
//                int id = rs.getInt(1);
//                String maPKB = rs.getString(2);
//                String maNV = rs.getString(3);
//                String maBN = rs.getString(4);
//                String maPK = rs.getString(5);
//                Date ngayTao = rs.getDate(6);
//                String chanDoan = rs.getString(7);
//                // Add populated object to list
//                
//
//            }
//        } catch (SQLException e) {
//            // Handle or log the exception as needed
//            
//        }
//        return phieuKhamBenhs;
//    }
    public ArrayList<PhieuKhamBenh> timPKBbyBnID(int id)
    {
        ArrayList<PhieuKhamBenh> phieuKhamBenhs = new ArrayList<>();
        try {
            Connection con = connect.getConnection();
            String sql = "SELECT PHIEUKHAMBENH.* FROM PHIEUKHAMBENH, BenhNhan WHERE PHIEUKHAMBENH.MABN = BenhNhan.id And BenhNhan.id = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhieuKhamBenh phieuKhamBenh = new PhieuKhamBenh();
                
                phieuKhamBenh.setId(rs.getInt("id"));
                phieuKhamBenh.setMAPKB(rs.getString("maPKB"));
                phieuKhamBenh.setMABS(rs.getInt("maBS"));
                phieuKhamBenh.setMABN(rs.getInt("maBN"));
                phieuKhamBenh.setMAPK(rs.getInt("maPK"));
                phieuKhamBenh.setNGAYTAO(rs.getDate("ngayTao"));
                phieuKhamBenh.setCHANDOAN(rs.getString("chanDoan"));               

                
                phieuKhamBenhs.add(phieuKhamBenh);
                
                
            }

        } catch (SQLException e) {
            // Handle or log the exception as needed           
        }
        return phieuKhamBenhs;
    }
    
    public PhieuKhamBenh getlastRowDataPKB()
    {   
        try {
            Connection con = connect.getConnection();
            String sql = "SELECT TOP 1 * FROM PhieuKhamBenh ORDER BY id DESC;";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PhieuKhamBenh phieuKhamBenh = new PhieuKhamBenh();

                phieuKhamBenh.setId(rs.getInt("id"));
                phieuKhamBenh.setMAPKB(rs.getString("MAPKB"));
                phieuKhamBenh.setMABS(rs.getInt("MABS"));
                phieuKhamBenh.setMABN(rs.getInt("MABN"));
                phieuKhamBenh.setMAPK(rs.getInt("MAPK"));
                phieuKhamBenh.setNGAYTAO(rs.getDate("NGAYTAO"));
                phieuKhamBenh.setCHANDOAN(rs.getString("CHANDOAN"));   
                
                return phieuKhamBenh;
            }                   
        }
        catch (SQLException e) {
            // Handle or log the exception as needed           
        }
        return null;
    }
}
