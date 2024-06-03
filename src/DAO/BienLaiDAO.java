package DAO;
import DTO.BienLaiDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import DTO.DangNhap;
import DTO.NhanVien;
import static GUI.DangNhapGUI.email;
import connectSql.connect;
import java.util.List;

public class BienLaiDAO {
  
                Connection conn = connect.getConnection();

    public ArrayList<BienLaiDTO> getAll(){
        ArrayList<BienLaiDTO> listBL = new ArrayList<>();
        String sql = "SELECT BIENLAI.ID AS ID, MABL, NHANVIEN.MANV AS MANV, TOATHUOC.MATOA AS MATOA, NGAYTAO, TONGTIENKHAM\n" +
                        "FROM ((BIENLAI\n" +
                        "INNER JOIN NHANVIEN ON NHANVIEN.ID = BIENLAI.MANV_TT)\n" +
                        "INNER JOIN TOATHUOC ON TOATHUOC.ID = BIENLAI.MATOA);";
        try {
            PreparedStatement preStmt = conn.prepareStatement(sql);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()){
                listBL.add(new BienLaiDTO(rs.getInt("ID"), rs.getString("MABL"), rs.getString("MANV"), 
                        rs.getString("MATOA"), rs.getDate("NGAYTAO"), rs.getLong("TONGTIENKHAM")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listBL;
    }
    
    public BienLaiDTO get(String mabl){
        BienLaiDTO bl = new BienLaiDTO();
        String sql = "SELECT * FROM BIENLAI WHERE MABL = ?";
        try {
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, mabl);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()){
                bl.setId(rs.getInt("id"));
                bl.setMaBL(rs.getString("MABL"));
                bl.setMaNV_TT(rs.getString("MANV_TT"));
                bl.setMaToa(rs.getString("MATOA"));
                bl.setNgayTao(rs.getDate("NGAYTAO"));
                bl.setTongTienKham(rs.getLong("TONGTIENKHAM"));
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bl;
    }
    
    public BienLaiDTO getByMaToa(String maToa){
        BienLaiDTO bl = new BienLaiDTO();
        String sql = "SELECT BIENLAI.id AS ID, MABL, MANV_TT, BIENLAI.MATOA, NGAYTAO, TONGTIENKHAM \n" +
                        "FROM BIENLAI INNER JOIN TOATHUOC ON TOATHUOC.id = BIENLAI.MATOA \n" +
                        "WHERE TOATHUOC.MATOA= ?";
        try {
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, maToa);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()){
                bl.setMaBL(rs.getString("MABL"));
                bl.setMaNV_TT(rs.getString("MANV_TT"));
                bl.setMaToa(rs.getString("MATOA"));
                bl.setNgayTao(rs.getDate("NGAYTAO"));
                bl.setTongTienKham(rs.getLong("TONGTIENKHAM"));
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bl;
    }
    
    public boolean delete(String maBL){
        String sql = "DELETE FROM BIENLAI WHERE MABL = ?";
        try {
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, maBL);
            if (preStmt.executeUpdate() > 0)
                return true;
            else
                return false;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
        
    public NhanVien GetNV_TTByTaiKhoan() {
        String sql = "SELECT NHANVIEN.id as id, NHANVIEN.MANV AS MANV, NHANVIEN.HOTEN AS HOTEN\n" +
                    "FROM TaiKhoan INNER JOIN NHANVIEN\n" +
                    "	ON TaiKhoan.maSoNV = NHANVIEN.id\n" +
                    "WHERE TaiKhoan.email = (?)";
        NhanVien NV_TT = new NhanVien();
        try {
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, email);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                NV_TT.setId(rs.getInt("id"));
                NV_TT.setMANV(rs.getString("MANV"));
                NV_TT.setHOTEN(rs.getString("HOTEN"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return NV_TT;
    }
    
    public boolean insert(String maToa){
        String sql = "INSERT INTO BIENLAI(MANV_TT, MATOA, TONGTIENKHAM) VALUES(?, ?, ?)";
        try {
            PreparedStatement preStmt = conn.prepareStatement(sql);
            NhanVien NV_TT = GetNV_TTByTaiKhoan();
            preStmt.setInt(1, NV_TT.getId());
            ToaThuocDAO toaThuoc_DAO = new ToaThuocDAO();
            preStmt.setInt(2, toaThuoc_DAO.getIDToaThuocByMaToa(maToa));
            preStmt.setDouble(3, tinhTongTienKham(maToa));
            if (preStmt.executeUpdate() > 0) {
                return true;
            }
            else {
                return false;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public double tinhTongTienKham(String maToa) {
        double tt = 0;
        String sql = "SELECT TONGTIEN FROM TOATHUOC WHERE MATOA = (?)";
        try {
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, maToa);
            ResultSet rs = preStmt.executeQuery();

            if (rs.next()) {
                tt = rs.getDouble("TONGTIEN");
            }
            else {
                return 0;
            }

            rs.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tt + 300000;
    }

    
  public List<BienLaiDTO> getDoanhThuGroupByYear() {
        List<BienLaiDTO> doanhThuByYear = new ArrayList<>();
        Connection con = connect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // SQL query to sum up the revenue grouped by year
            String sql = "SELECT YEAR(NGAYTAO) AS Nam, SUM(TONGTIENKHAM) AS DoanhThu FROM BIENLAI GROUP BY YEAR(NGAYTAO)";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                BienLaiDTO bl = new BienLaiDTO();
                bl.setNam(rs.getInt("Nam"));
                bl.setTongTienKham(rs.getDouble("DoanhThu"));
                doanhThuByYear.add(bl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return doanhThuByYear;
    }
  
  public ArrayList<Double> getDoanhThuThang(int nam) {
      ArrayList<Double> doanhThuTungThang= new ArrayList<Double>();
      String sql = "WITH Months AS (\n" +
                  "    SELECT 1 AS MonthNumber\n" +
                  "    UNION ALL\n" +
                  "    SELECT MonthNumber + 1\n" +
                  "    FROM Months\n" +
                  "    WHERE MonthNumber < 12\n" +
                  ")\n" +
                  "\n" +
                  "SELECT \n" +
                  "    m.MonthNumber AS Thang,\n" +
                  "    ISNULL(SUM(b.TONGTIENKHAM), 0) AS DoanhThu\n" +
                  "FROM \n" +
                  "    Months m\n" +
                  "LEFT JOIN \n" +
                  "    BIENLAI b ON m.MonthNumber = MONTH(b.NGAYTAO) AND YEAR(b.NGAYTAO) = (?)\n" +
                  "GROUP BY \n" +
                  "    m.MonthNumber\n" +
                  "ORDER BY \n" +
                  "    Thang";
      try {
          PreparedStatement preStmt = conn.prepareStatement(sql);
          preStmt.setInt(1, nam);
          ResultSet rs = preStmt.executeQuery();
          
          int thang = 1;
          while (rs.next()) {
              doanhThuTungThang.add(rs.getDouble("DoanhThu"));
              thang++;
          }

          rs.close();
          
      } catch (SQLException ex) {
          ex.printStackTrace();
      }
      return doanhThuTungThang;
  }
  
    public static void main(String[] args) {
        BienLaiDAO blDao = new BienLaiDAO();
      
    }
}