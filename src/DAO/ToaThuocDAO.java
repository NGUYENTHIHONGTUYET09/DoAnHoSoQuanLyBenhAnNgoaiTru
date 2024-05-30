/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ToaThuoc;
import java.util.List;
import connectSql.connect;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author phamngochoang
 */
public class ToaThuocDAO {

    public ArrayList<ToaThuoc> getAllToaThuocs() {
        ArrayList<ToaThuoc> toaThuocs = new ArrayList<>();

        try {
            Connection con = connect.getConnection();
            String sql = "SELECT * FROM TOATHUOC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ToaThuoc toaThuoc = new ToaThuoc();

                toaThuoc.setId(rs.getInt("id"));
                toaThuoc.setMaToa(rs.getString("maToa"));
                toaThuoc.setMaBS(rs.getInt("maBS"));
                toaThuoc.setMaBN(rs.getInt("maBN"));
                toaThuoc.setTongTien(rs.getDouble("tongTien"));
                toaThuoc.setNgayLap(rs.getDate("ngayLap"));

                // Add populated object to list
                toaThuocs.add(toaThuoc);
            }
        } catch (SQLException e) {
            // Handle or log the exception as needed

        }
        return toaThuocs;
    }

    public int addToaThuoc(ToaThuoc toaThuoc) {
        int rs = 0;
        try {
            Connection con = connect.getConnection();
            String sql = "INSERT INTO TOATHUOC(MABS, MABN, TONGTIEN, NGAYLAP, MaPKB) VALUES (?, ? , ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            // ResultSet rs = ps.executeQuery();

            ps.setInt(1, toaThuoc.getMaBS());
            ps.setInt(2, toaThuoc.getMaBN());
            ps.setDouble(3, toaThuoc.getTongTien());
            ps.setDate(4, toaThuoc.getNgayLap());
            ps.setInt(5, toaThuoc.getMaPKB());

            rs = ps.executeUpdate();

            
        } catch (SQLException e) {
            // Handle or log the exception as needed
            e.printStackTrace();
        }
        return rs;
    }

    public int getIDToaThuocByMaToa(String maToa) {
        int id = 0;

        try {
            Connection con = connect.getConnection();
            String sql = "SELECT id FROM TOATHUOC where matoa= (?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maToa);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            // Handle or log the exception as needed
        }
        return id;
    }

    public ArrayList<ToaThuoc> getToaThuocsByBenhNhanID(int id) {
        ArrayList<ToaThuoc> toaThuocs = new ArrayList<>();

        try {
            Connection con = connect.getConnection();
            String sql = "SELECT * FROM TOATHUOC WHERE maBN = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ToaThuoc toaThuoc = new ToaThuoc();

                toaThuoc.setId(rs.getInt("id"));
                toaThuoc.setMaToa(rs.getString("maToa"));
                toaThuoc.setMaBS(rs.getInt("maBS"));
                toaThuoc.setMaBN(rs.getInt("maBN"));
                toaThuoc.setTongTien(rs.getDouble("tongTien"));
                toaThuoc.setNgayLap(rs.getDate("ngayLap"));
                toaThuoc.setMaPKB(rs.getInt("MaPKB"));

                // Add populated object to list
                toaThuocs.add(toaThuoc);
            }
        } catch (SQLException e) {
            // Handle or log the exception as needed

        }
        return toaThuocs;
    }

    public ToaThuoc getlastRowDataToaThuoc() {
        try {
            Connection con = connect.getConnection();
            String sql = "SELECT TOP 1 * FROM ToaThuoc ORDER BY id DESC;";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ToaThuoc toaThuoc = new ToaThuoc();

                toaThuoc.setId(rs.getInt("id"));
                toaThuoc.setMaToa(rs.getString("maToa"));
                toaThuoc.setMaBS(rs.getInt("maBS"));
                toaThuoc.setMaBN(rs.getInt("maBN"));
                toaThuoc.setTongTien(rs.getDouble("tongTien"));
                toaThuoc.setNgayLap(rs.getDate("ngayLap"));
                toaThuoc.setMaPKB(rs.getInt("MaPKB"));

                return toaThuoc;
            }
        } catch (SQLException e) {
            // Handle or log the exception as needed           
        }
        return null;
    }

    public double tinhTongToaThuoc(int toaThuocID) {
        double tongTien = 0;
        try {
            Connection con = connect.getConnection();
            String sql = " SELECT SUM(CTDONTHUOC.SOLUONG * THUOC.DONGIA) FROM CTDONTHUOC, THUOC WHERE CTDONTHUOC.MATHUOC = THUOC.ID AND CTDONTHUOC.MATOA = ?                            ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, toaThuocID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tongTien = rs.getDouble(1);
            }
        } catch (SQLException e) {
            // Handle or log the exception as needed           
        }
        return tongTien;
    }

    public void updateTongTien(int toaThuocID, double tongTien) {
        try {
            Connection con = connect.getConnection();
            String sql = "UPDATE TOATHUOC SET TOATHUOC.TONGTIEN = ? WHERE TOATHUOC.ID = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, tongTien);
            ps.setInt(2, toaThuocID);
            ps.executeUpdate();
            System.out.println("Update total Bill successfull");
        } catch (SQLException e) {
            // Handle or log the exception as needed           
        }
    }
}
