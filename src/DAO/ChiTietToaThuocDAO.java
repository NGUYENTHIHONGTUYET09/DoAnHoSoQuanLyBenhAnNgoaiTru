///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package DAO;
//
//import DTO.ChiTietToaThuoc;
//import connectSql.connect;
//import java.util.ArrayList;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
///**
// *
// * @author phamngochoang
// */
//public class ChiTietToaThuocDAO {
//    public ArrayList<ChiTietToaThuoc> getCTTT(int maPKB) {
//        ArrayList<ChiTietToaThuoc> chiTietToaThuocs = new ArrayList<>();
//        try {
//            Connection con = connect.getConnection();
//            String sql = " SELECT TOATHUOC.MATOA, CTDONTHUOC.MATHUOC, THUOC.TENTHUOC, CTDONTHUOC.SOLUONG, THUOC.DONGIA FROM TOATHUOC, THUOC, CTDONTHUOC WHERE TOATHUOC.id = CTDONTHUOC.MATOA AND CTDONTHUOC.MATHUOC = THUOC.id AND TOATHUOC.MaPKB = ?";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, maPKB);
//            
//            
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                ChiTietToaThuoc chiTietToaThuoc = new ChiTietToaThuoc();
//                
//                chiTietToaThuoc.setMaToa(rs.getString("MATOA"));
//                chiTietToaThuoc.setMaThuoc(rs.getInt("MATHUOC"));
//                chiTietToaThuoc.setTenThuoc(rs.getString("TENTHUOC"));
//                chiTietToaThuoc.setSoLuong(rs.getInt("SOLUONG"));
//                chiTietToaThuoc.setDonGia(rs.getDouble("DONGIA"));               
//
//                // Create an instance of ChiTietToaThuoc and add it to the list               
//                chiTietToaThuocs.add(chiTietToaThuoc);
////                System.out.println("1 query is executed");
//            }
//        } catch (SQLException e) {
//            // Handle or log the exception as needed
//        }
//        return chiTietToaThuocs;
//    }
//}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietToaThuoc;
import connectSql.connect;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author phamngochoang
 */
public class ChiTietToaThuocDAO {
    public ArrayList<ChiTietToaThuoc> getCTTT(int maPKB) {
        ArrayList<ChiTietToaThuoc> chiTietToaThuocs = new ArrayList<>();
        try {
            Connection con = connect.getConnection();
            String sql = " SELECT TOATHUOC.MATOA, CTDONTHUOC.MATHUOC, THUOC.TENTHUOC, CTDONTHUOC.SOLUONG, THUOC.DONGIA, CTDONTHUOC.GHICHU FROM TOATHUOC, THUOC, CTDONTHUOC WHERE TOATHUOC.id = CTDONTHUOC.MATOA AND CTDONTHUOC.MATHUOC = THUOC.id AND TOATHUOC.MaPKB = ?";
                        
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, maPKB);
            
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ChiTietToaThuoc chiTietToaThuoc = new ChiTietToaThuoc();
                
                chiTietToaThuoc.setMaToa(rs.getString("MATOA"));
                chiTietToaThuoc.setMaThuoc(rs.getInt("MATHUOC"));
                chiTietToaThuoc.setTenThuoc(rs.getString("TENTHUOC"));
                chiTietToaThuoc.setSoLuong(rs.getInt("SOLUONG"));
                chiTietToaThuoc.setDonGia(rs.getDouble("DONGIA"));
                chiTietToaThuoc.setGhiChu(rs.getString("GHICHU"));

                // Create an instance of ChiTietToaThuoc and add it to the list               
                chiTietToaThuocs.add(chiTietToaThuoc);
//                System.out.println("1 query is executed");
            }
        } catch (SQLException e) {
            // Handle or log the exception as needed
        }
        return chiTietToaThuocs;
    }
}
