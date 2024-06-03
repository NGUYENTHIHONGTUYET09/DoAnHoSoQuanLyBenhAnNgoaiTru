/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import connectSql.connect;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.CTDonThuoc;
import java.sql.CallableStatement;
/**
 *
 * @author phamngochoang
 */
public class CTDonThuocDAO {
        
    public int addCTDT(CTDonThuoc cTDonThuoc){
        int rs = 0;
        try {
            Connection con = connect.getConnection();
            String sql = "INSERT INTO CTDONTHUOC(MATHUOC, MATOA, SOLUONG,GHICHU) VALUES (?, ?, ?,?)";
//            String sql =  "EXEC sp_ThemThuocChiTietDonThuoc ? ? ?";
            PreparedStatement ps = con.prepareStatement(sql);
       //     ResultSet rs = ps.executeQuery();

            ps.setInt(1, cTDonThuoc.getMaThuoc());
            ps.setInt(2, cTDonThuoc.getMaToa());
            ps.setInt(3, cTDonThuoc.getSoLuong());
            ps.setString(4, cTDonThuoc.getGhiChu());
            rs = ps.executeUpdate();
            
            
            
        } catch (SQLException e) {
            // Handle or log the exception as needed
            
        }
        return rs;
    }
    
      public int addCTDT_PROC(CTDonThuoc cTDonThuoc){
    int rs = 0;
    try {
        Connection con = connect.getConnection();
        String sql =  "{call sp_ThemThuocChiTietDonThuocV1(?, ?, ?)}";
		CallableStatement cs = con.prepareCall(sql);
        
        cs.setInt(1, cTDonThuoc.getMaThuoc());
        cs.setInt(2, cTDonThuoc.getSoLuong());
        cs.setInt(3, cTDonThuoc.getMaToa());
    
        rs = cs.executeUpdate();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return rs;
}
//      
//      public int themChiTietDonThuoc_NON(CTDonThuoc cTDonThuoc) {
//    	  int rs = 0;
//          try (Connection con = connect.getConnection()) {
//              String sql = "{CALL ThemCTDonThuoc(?, ?, ?)}";
//              try (CallableStatement cs = con.prepareCall(sql)) {
//                  cs.setInt(1,  cTDonThuoc.getMaThuoc());
//                  cs.setInt(2,  cTDonThuoc.getMaToa());
//                  cs.setInt(3, cTDonThuoc.getSoLuong());
//
//                  rs = cs.executeUpdate();
//                  System.out.println("Thêm chi tiết đơn thuốc thành công.");
//              }
//          } catch (SQLException e) {
//              e.printStackTrace();
//              System.out.println("Thêm chi tiết đơn thuốc thất bại: " + e.getMessage());
//          }
//          return rs;
//      }

    
}
