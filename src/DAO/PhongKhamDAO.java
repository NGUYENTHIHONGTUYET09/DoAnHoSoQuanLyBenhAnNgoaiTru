/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.PhongKham;
import connectSql.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author phamngochoang
 */
public class PhongKhamDAO {
    public PhongKham timPKbyID(int id)
    {
        try {
            Connection con = connect.getConnection();
            String sql = "SELECT * FROM PHONGKHAM WHERE id = ?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhongKham phongKham = new PhongKham();
                
                phongKham.setId(rs.getInt("id"));
                phongKham.setMaPK(rs.getString("MAPK"));
                phongKham.setTenPK(rs.getString("TENPK"));
                phongKham.setTenKhoa(rs.getString("TENKHOA"));
                phongKham.setThoiGianBD(rs.getTime("THOIGIANBD"));
                phongKham.setThoiGianKT(rs.getTime("THOIGIANKT"));
                
                
                return phongKham;
            }
        } catch (SQLException e) {
            // Handle or log the exception as needed           
        }
        return null;
    }
        
    public ArrayList<PhongKham> getAllPhongKhams() {
        ArrayList<PhongKham> phongKhams = new ArrayList<>();
        
        try {
            Connection con = connect.getConnection();
            String sql = "SELECT * FROM PHONGKHAM";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PhongKham phongKham = new PhongKham();
                
                phongKham.setId(rs.getInt("id"));
                phongKham.setMaPK(rs.getString("MAPK"));
                phongKham.setTenPK(rs.getString("TENPK"));
                phongKham.setTenKhoa(rs.getString("TENKHOA"));
                phongKham.setThoiGianBD(rs.getTime("THOIGIANBD"));
                phongKham.setThoiGianKT(rs.getTime("THOIGIANKT"));

                // Add populated object to list
                phongKhams.add(phongKham);
            }
        } catch (SQLException e) {
            // Handle or log the exception as needed
            
        }
        return phongKhams;
    }
         
    public PhongKham getPKByTenPK(String tenPK)
    {
        try {
            Connection con = connect.getConnection();
            String sql = "SELECT * FROM PHONGKHAM WHERE TENPK = ?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenPK);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhongKham phongKham = new PhongKham();
                
                phongKham.setId(rs.getInt("id"));
                phongKham.setMaPK(rs.getString("MAPK"));
                phongKham.setTenPK(rs.getString("TENPK"));
                phongKham.setTenKhoa(rs.getString("TENKHOA"));
                phongKham.setThoiGianBD(rs.getTime("THOIGIANBD"));
                phongKham.setThoiGianKT(rs.getTime("THOIGIANKT"));
                
                
                return phongKham;
            }
        } catch (SQLException e) {
            // Handle or log the exception as needed           
        }
        return null;
    }
}
