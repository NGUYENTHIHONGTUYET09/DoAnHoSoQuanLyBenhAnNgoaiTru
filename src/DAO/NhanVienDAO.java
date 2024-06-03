package DAO;

import DTO.NhanVien;
import connectSql.connect;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class NhanVienDAO {
	  Connection conn = connect.getConnection();

	  public ArrayList<NhanVien> getAll() {
		    ArrayList<NhanVien> listNV = new ArrayList<>();
		    String sql = "SELECT * FROM NHANVIEN WHERE TRANGTHAI=N'Đang tồn tại'";
		    try {
		        PreparedStatement preStmt = conn.prepareStatement(sql);
		        ResultSet rs = preStmt.executeQuery();
		        while (rs.next()) {
		            listNV.add(new NhanVien(
		                rs.getInt("ID"),
		                rs.getString("MANV"),
		                rs.getString("HOTEN"),
		                rs.getDate("NGAYSINH"),
		                rs.getString("DIACHI"),
		                rs.getString("GIOITINH"),
		                rs.getDate("NGAYVL"),
		                rs.getString("VAITRO"),
		                rs.getString("TRANGTHAI")
		            ));
		        }
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		    return listNV;
		}
    
    public Optional<NhanVien> get(String manv){
        NhanVien nv = new NhanVien();
        String sql = "SELECT * FROM NHANVIEN WHERE MANV = ?";
        try {
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, manv);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()){
                nv.setId(rs.getInt("ID"));
                nv.setMANV(rs.getString("MANV"));
                nv.setHOTEN(rs.getString("HOTEN"));
                nv.setNGAYSINH(rs.getDate("NGAYSINH"));
                nv.setDIACHI(rs.getString("DIACHI"));
                nv.setGIOITINH(rs.getString("GIOITINH"));
                nv.setNGAYVL(rs.getDate("NGAYVL"));
                nv.setVAITRO(rs.getString("VAITRO"));
                nv.setTRANGTHAI(rs.getString("TRANGTHAI"));
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(nv);
    }
    
    public boolean insert(NhanVien nv){
        String sql = "INSERT INTO NHANVIEN (HOTEN, NGAYSINH, DIACHI, GIOITINH, NGAYVL, VAITRO, TRANGTHAI) VALUES(?, ?, ?, ?, ?, ?,?)"; 
        try {
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, nv.getHOTEN());
            preStmt.setDate(2, nv.getNGAYSINH());
            preStmt.setString(3, nv.getDIACHI());
            preStmt.setString(4, nv.getGIOITINH());
            preStmt.setDate(5, nv.getNGAYVL());
            preStmt.setString(6, nv.getVAITRO());
            preStmt.setString(7, nv.getTRANGTHAI());
            if (preStmt.executeUpdate() > 0)
                return true;
            else
                return false;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean update(NhanVien nv){
        String sql = "UPDATE NHANVIEN SET HOTEN = ?, NGAYSINH = ?, DIACHI = ?, GIOITINH = ?, NGAYVL = ?, VAITRO = ?, TRANGTHAI=?"
                + " WHERE MANV = ?";
        try {
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setString(7, nv.getMANV());
            preStmt.setString(1, nv.getHOTEN());
            preStmt.setDate(2, nv.getNGAYSINH());
            preStmt.setString(3, nv.getDIACHI());
            preStmt.setString(4, nv.getGIOITINH());
            preStmt.setDate(5, nv.getNGAYVL());
            preStmt.setString(6, nv.getVAITRO());
            preStmt.setString(7, nv.getTRANGTHAI());
            if (preStmt.executeUpdate() > 0)
                return true;
            else
                return false;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean delete(String maNV){
        String sql = "DELETE FROM NHANVIEN WHERE MANV = ?";
        try {
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, maNV);
            if (preStmt.executeUpdate() > 0)
                return true;
            else
                return false;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public NhanVien getById(int idNV) {

		Connection conn = connect.getConnection();
		String sql = "SELECT * FROM NhanVien WHERE id =?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, idNV);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String MANV = rs.getString("MANV");
				String HOTEN = rs.getString("HOTEN");
				Date NGAYSINH = rs.getDate("NGAYSINH");
				String DIACHI = rs.getString("DIACHI");
				String GIOITINH = rs.getString("GIOITINH");
				Date NGAYVL = rs.getDate("NGAYVL");
				String VAITRO = rs.getString("VAITRO");
                                String TRANGTHAI = rs.getString("TRANGTHAI");
				return new NhanVien(id, MANV, HOTEN, NGAYSINH, DIACHI, GIOITINH, NGAYVL, VAITRO,TRANGTHAI);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
    
    public NhanVien getByMaSoNV(String maNV) {

                Connection conn = connect.getConnection();
                String sql = "SELECT * FROM NHANVIEN WHERE MANV =?";
                try {
                                PreparedStatement st = conn.prepareStatement(sql);
                                st.setString(1, maNV);
                                ResultSet rs = st.executeQuery();
                                while(rs.next()) {
                                        int id = rs.getInt("id");
                                        String MANV = rs.getString("MANV");
                                        String HOTEN = rs.getString("HOTEN");
                                        Date NGAYSINH = rs.getDate("NGAYSINH");
                                        String DIACHI = rs.getString("DIACHI");
                                        String GIOITINH = rs.getString("GIOITINH");
                                        Date NGAYVL = rs.getDate("NGAYVL");
                                        String VAITRO = rs.getString("VAITRO");
                                         String TRANGTHAI = rs.getString("TRANGTHAI");
                                        return new NhanVien(id, MANV, HOTEN, NGAYSINH, DIACHI, GIOITINH, NGAYVL, VAITRO,TRANGTHAI);
                                }

                        } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }

                return null;
        }
   
    
    public ArrayList<NhanVien> getAllBacSis() {
        ArrayList<NhanVien> nhanViens = new ArrayList<>();
        
        try {
            Connection con = connect.getConnection();
            String sql = "SELECT * FROM NhanVien WHERE VAITRO = N'Bác sĩ' ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                
                nhanVien.setId(rs.getInt("id"));
                nhanVien.setMANV(rs.getString("MANV"));
                nhanVien.setHOTEN(rs.getString("HOTEN"));
                nhanVien.setNGAYSINH(rs.getDate("NGAYSINH"));
                nhanVien.setDIACHI(rs.getString("DIACHI"));
                nhanVien.setGIOITINH(rs.getString("GIOITINH"));
                nhanVien.setNGAYVL(rs.getDate("NGAYVL"));               
                nhanVien.setVAITRO(rs.getString("VAITRO"));
                nhanVien.setTRANGTHAI(rs.getString("TRANGTHAI"));

                // Add populated object to list
                nhanViens.add(nhanVien);
            }
        } catch (SQLException e) {
            // Handle or log the exception as needed
            
        }
        return nhanViens;
    }
    
    public NhanVien timNVbyID(int id)
    {
        try {
            Connection con = connect.getConnection();
            String sql = "SELECT * FROM NhanVien WHERE ID = ?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                
                nhanVien.setId(rs.getInt("id"));
                nhanVien.setMANV(rs.getString("MANV"));
                nhanVien.setHOTEN(rs.getString("HOTEN"));
                nhanVien.setNGAYSINH(rs.getDate("NGAYSINH"));
                nhanVien.setDIACHI(rs.getString("DIACHI"));
                nhanVien.setGIOITINH(rs.getString("GIOITINH"));
                nhanVien.setNGAYVL(rs.getDate("NGAYVL"));               
                nhanVien.setVAITRO(rs.getString("VAITRO"));
                nhanVien.setTRANGTHAI(rs.getString("TRANGTHAI"));
                
                return nhanVien;
            }
        } catch (SQLException e) {
            // Handle or log the exception as needed           
        }
        return null;
    }
    
    public static void main(String[] args) {
        NhanVienDAO nvDao = new NhanVienDAO();
        
    }
    
    
}