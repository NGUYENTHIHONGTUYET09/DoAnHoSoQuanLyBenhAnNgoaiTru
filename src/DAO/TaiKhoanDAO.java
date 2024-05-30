package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import static GUI.DangNhapGUI.email;
import static GUI.DangNhapGUI.idNhanVien;

import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

import DTO.BenhNhan;
import DTO.TaiKhoan;

import GUI.BenhNhanGUI;
import GUI.DangKyGUI;
import GUI.DanhSachTaiKhoan;
import connectSql.connect;

public class TaiKhoanDAO {

    private static TaiKhoanDAO instance = new TaiKhoanDAO();
    
    private TaiKhoanDAO(){}
    
    public static TaiKhoanDAO getInstance(){
        return instance;
    }

    public int insert(TaiKhoan dk) {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = connect.getConnection();
            String sql = "INSERT INTO TaiKhoan (hoTen,maSoNV, soDienThoai, email, matKhau, matkhauxacnhan, vaiTro,trangThai) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, dk.getHoTen());
            ps.setInt(2, dk.getMaSoNV());

            ps.setString(3, dk.getSoDienThoai());
            ps.setString(4, dk.getEmail());
            ps.setString(5, dk.getMatKhau());
            ps.setString(6, dk.getMatkhauxacnhan());
            ps.setString(7, dk.getVaiTro());
            ps.setString(8, dk.getTrangThai());

            result = ps.executeUpdate();

            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có : " + result + " bản ghi đã thay đổi");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public ArrayList<TaiKhoan> fetchAllDangKy() {
    Connection con = null;
    PreparedStatement ps = null;
    ArrayList<TaiKhoan> listDk = new ArrayList<>();
    try {
        con = connect.getConnection();
        String sql = "SELECT * FROM TaiKhoan";
        ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String hoTen = rs.getString("hoTen");
            int maSoNV = rs.getInt("maSoNV");
            String soDienThoai = rs.getString("soDienThoai");
            String email = rs.getString("email");
            String matKhau = rs.getString("matKhau");
            String matkhauxacnhan = rs.getString("matkhauxacnhan");
            String vaiTro = rs.getString("vaiTro");
            String trangThai = rs.getString("trangThai");
            listDk.add(new TaiKhoan(hoTen, maSoNV, soDienThoai, email, matKhau, matkhauxacnhan, vaiTro, trangThai));
        }
        rs.close();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return listDk;
}

    public int deleteDangKy(TaiKhoan dk) {
        int result = 0;
        try {
            Connection con = connect.getConnection();
            String sql = "";
            PreparedStatement st;

            sql = "DELETE FROM TaiKhoan WHERE maSoNV=?";
            st = con.prepareStatement(sql);
            st.setInt(1, dk.getMaSoNV());

            result = st.executeUpdate();
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có : " + result + " bản ghi đã thay đổi");
            connect.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(TaiKhoan dk) {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = connect.getConnection();
            String sql = "";
          
                sql = "UPDATE TaiKhoan SET hoTen=?, soDienThoai=?, email=?, matKhau=?, matkhauxacnhan=?, vaiTro=?, trangThai=? WHERE maSoNV=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, dk.getHoTen());
                ps.setString(2, dk.getSoDienThoai());
                ps.setString(3, dk.getEmail());
                ps.setString(4, dk.getMatKhau());
                ps.setString(5, dk.getMatkhauxacnhan());
                ps.setString(6, dk.getVaiTro());
                ps.setInt(7, dk.getMaSoNV());
                ps.setString(7, dk.getTrangThai());
            

            result = ps.executeUpdate();

            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có : " + result + " bản ghi đã thay đổi");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public ArrayList<TaiKhoan> fetchAllDangKyEmail(String requestEmail, String requestPassword) {
        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<TaiKhoan> listDk = new ArrayList<TaiKhoan>();
        try {
            con = connect.getConnection();
            String sql = "SELECT * FROM TaiKhoan where email=? and matKhau=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, requestEmail);
            ps.setString(2, requestPassword);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String vaiTro = rs.getString("vaiTro");
                if ("Bác sĩ".equals(vaiTro) || "NV Tiếp Nhận".equals(vaiTro)||"NV Thanh Toán".equals(vaiTro)||"Admin".equals(vaiTro)||"Quản lý kho".equals(vaiTro)) {
                    JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
                    email = rs.getString("email");
                    idNhanVien = rs.getInt("maSoBN");
                    //  dispose();
                }
            }
            return listDk;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<TaiKhoan>();
    }

}
