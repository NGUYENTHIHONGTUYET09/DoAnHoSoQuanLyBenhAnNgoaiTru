package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import DTO.BenhNhan;
import DTO.Tinh;

import connectSql.connect;
import GUI.QuanLyThongTinBenhNhan;

public class BenhNhanDAO {

    private QuanLyThongTinBenhNhan qlttbn;
    private TinhDAO tinhdao;
   public BenhNhanDAO() {
         this.tinhdao =TinhDAO.getInstance();
    }
//
//    public static BenhNhanDAO getInstance() {
//        return new BenhNhanDAO();
//    }
//  
      public int insertBenhNhan(BenhNhan bn) {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = connect.getConnection();
             String sql = "INSERT INTO BenhNhan (tenBN, sdt, ngaySinh, diaChi, gioiTinh, queQuan, ghichu) VALUES (?, ?, ?, ?, ?, ?, ?)";
               ps = con.prepareStatement(sql);
            ps.setString(1, bn.getTenBN());
            ps.setString(2, bn.getSdt());
            ps.setDate(3, new java.sql.Date(bn.getNgaySinh().getTime())); // Chuyển đổi java.util.Date thành java.sql.Date
            ps.setString(4, bn.getDiaChi());
            ps.setString(5, bn.getGioiTinh());
            ps.setInt(6, bn.getQueQuan()); // Sử dụng getQueQuan() để lấy ID của tỉnh
            ps.setString(7, bn.getGhiChu());

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

    public ArrayList<BenhNhan> fetchAllBenhNhan() {
        ArrayList<BenhNhan> listBN = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connect.getConnection();
            String sql = "SELECT BenhNhan.id, maBN, tenBN, sdt, ngaySinh, diaChi, gioiTinh, queQuan, ghichu "
                    + "FROM BenhNhan";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String maBN = rs.getString("maBN");
                String tenBN = rs.getString("tenBN");
                String sdt = rs.getString("sdt");
                java.sql.Date ngaySinh = rs.getDate("ngaySinh");
                String diaChi = rs.getString("diaChi");
                String gioiTinh = rs.getString("gioiTinh");
                int queQuan = rs.getInt("queQuan");
                String ghiChu = rs.getString("ghichu");

                BenhNhan bn = new BenhNhan(tenBN, sdt, ngaySinh, diaChi, queQuan, gioiTinh, ghiChu);
                bn.setId(id);
                bn.setMaBN(maBN);

                listBN.add(bn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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

        return listBN;
    }

    public int deleteBenhNhan(BenhNhan bn) {
        int result = 0;
        try {
            Connection con = connect.getConnection();
            String sql = "DELETE from BenhNhan WHERE sdt=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, bn.getSdt());
            result = st.executeUpdate();
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có : " + result + " bản ghi đã thay đổi");
            connect.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int updateBenhNhan(BenhNhan bn) {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = connect.getConnection();
            String sql = "UPDATE BenhNhan SET tenBN=?, sdt=?, ngaySinh=?, diaChi=?, gioiTinh=?, queQuan=?, ghichu=? WHERE sdt=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, bn.getTenBN());
            ps.setString(2, bn.getSdt());
            ps.setDate(3, new java.sql.Date(bn.getNgaySinh().getTime()));
            ps.setString(4, bn.getDiaChi());
            ps.setString(5, bn.getGioiTinh());
            ps.setInt(6, bn.getQueQuan()); // Lưu ID của tỉnh
            ps.setString(7, bn.getGhiChu());
            ps.setString(8, bn.getSdt()); // Thiết lập giá trị cho tham số WHERE

            result = ps.executeUpdate();

            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có : " + result + " bản ghi đã thay đổi");

            // Nếu update thành công, lấy lại thông tin bệnh nhân từ database
            if (result > 0) {
                // Thực hiện truy vấn để lấy lại thông tin bệnh nhân đã cập nhật
                String selectSql = "SELECT * FROM BenhNhan WHERE sdt=?";
                PreparedStatement selectPs = con.prepareStatement(selectSql);
                selectPs.setString(1, bn.getSdt());
                rs = selectPs.executeQuery();
                if (rs.next()) {
                    bn.setId(rs.getInt("id"));
                    bn.setMaBN(rs.getString("maBN"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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

    public BenhNhan getByMaSoBN(String maBN) {

        Connection conn = connect.getConnection();
        String sql = "SELECT * FROM BENHNHAN WHERE maBN =?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, maBN);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String maSoBN = rs.getString("maBN");
                String tenBN = rs.getString("tenBN");
                String sdt = rs.getString("sdt");
                java.sql.Date ngaySinh = rs.getDate("ngaySinh");
                String diaChi = rs.getString("diaChi");
                String gioiTinh = rs.getString("gioiTinh");
                int queQuan = rs.getInt("queQuan");
                String ghiChu = rs.getString("ghichu");
              
               

                BenhNhan bn = new BenhNhan(tenBN, sdt, ngaySinh, diaChi, queQuan, gioiTinh, ghiChu);
                bn.setId(id);
                bn.setMaBN(maBN);
                return bn;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public BenhNhan getById(int idBN) {

        Connection conn = connect.getConnection();
        String sql = "SELECT * FROM BENHNHAN WHERE id =?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, idBN);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String maSoBN = rs.getString("maBN");
                String tenBN = rs.getString("tenBN");
                String sdt = rs.getString("sdt");
                java.sql.Date ngaySinh = rs.getDate("ngaySinh");
                String diaChi = rs.getString("diaChi");
                String gioiTinh = rs.getString("gioiTinh");
                int queQuan = rs.getInt("queQuan");
                String ghiChu = rs.getString("ghichu");
             

                BenhNhan bn = new BenhNhan(tenBN, sdt, ngaySinh, diaChi, queQuan, gioiTinh, ghiChu);
                bn.setId(id);
                bn.setMaBN(maSoBN);
                return bn;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public BenhNhan timBNbyID(int id) {
        try {
            Connection con = connect.getConnection();
            String sql = "SELECT * FROM BenhNhan WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BenhNhan benhNhan = new BenhNhan();

           

                benhNhan.setId(rs.getInt("id"));
                benhNhan.setMaBN(rs.getString("maBN"));
                benhNhan.setTenBN(rs.getString("tenBN"));
                benhNhan.setSdt(rs.getString("sdt"));
                benhNhan.setNgaySinh(rs.getDate("ngaySinh"));
                benhNhan.setDiaChi(rs.getString("diaChi"));
                benhNhan.setGioiTinh(rs.getString("gioiTinh"));
                benhNhan.setQueQuan(rs.getInt("queQuan"));
                benhNhan.setGhiChu(rs.getString("ghichu"));

                return benhNhan;

            }
        } catch (SQLException e) {
            // Handle or log the exception as needed           
        }
        return null;
    }

  public BenhNhan getBenhNhanByMaBL(String mabl) {
    BenhNhan bn = new BenhNhan();
    Connection conn = connect.getConnection();
    String sql = "SELECT bn.tenBN, bn.ngaySinh, bn.diaChi, t.tenTinh as queQuan " +
                 "FROM BenhNhan bn " +
                 "JOIN TOATHUOC tt ON bn.id = tt.maBN " +
                 "JOIN BIENLAI bl ON tt.id = bl.maToa " +
                 "JOIN Tinh t ON bn.queQuan = t.id " +
                 "WHERE bl.maBL = ?";
    try {
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, mabl);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            String tenBN = rs.getString("tenBN");
            java.sql.Date ngaySinh = rs.getDate("ngaySinh");
            String diaChi = rs.getString("diaChi");
            String queQuanName = rs.getString("queQuan");

            Tinh queQuanTinh = tinhdao.getTinhByName(queQuanName);
            if (queQuanTinh != null) {
                bn.setQueQuan(queQuanTinh.getId());
            } else {
                bn.setQueQuan(-1); // Giá trị mặc định nếu không tìm thấy Tinh
            }

            bn.setTenBN(tenBN);
            bn.setNgaySinh(ngaySinh);
            bn.setDiaChi(diaChi);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return bn;
}

}
