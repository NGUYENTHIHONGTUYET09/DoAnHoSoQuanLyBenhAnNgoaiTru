package DAO;

import DTO.Tinh;
import connectSql.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TinhDAO {

    public static TinhDAO getInstance() {
        return new TinhDAO();
    }

    public Tinh getTinhById(int id) {
        Connection conn = connect.getConnection();
        String sql = "SELECT * FROM TINH WHERE id =?";
        Tinh tinh = null;
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int idi = rs.getInt("id");

                String tenTinh = rs.getString("tenTinh");

                tinh = new Tinh(idi, tenTinh);

                return tinh;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return tinh;
    }

    public Tinh getTinhByName(String tenTinh) {
        Connection conn = connect.getConnection();
        String sql = "SELECT * FROM TINH WHERE tenTinh = ?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, tenTinh);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("tenTinh");

                Tinh tinh = new Tinh(id, name);
                return tinh;
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
        return null;
    }

     public ArrayList<Tinh> fetchAllTinh() {
        ArrayList<Tinh> listTinh = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connect.getConnection();
            String sql = "SELECT * from Tinh";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
               
                String tentinh = rs.getString("tenTinh");
              
              
                Tinh tinh = new Tinh(id, tentinh);

                listTinh.add(tinh);
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

        return listTinh;
    }
     
     
}
