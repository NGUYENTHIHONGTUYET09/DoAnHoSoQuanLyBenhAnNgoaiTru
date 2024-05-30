package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVien;
import GUI.QLyNhanVienGUI;
import interfaces.TableInterface;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class DSNhanVienBUS {
    private ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
    private TableInterface tbInterface;
    private NhanVienDAO nvdao = new NhanVienDAO();
    public String tenFile;
    
    // No parameter consrtuctor
    public DSNhanVienBUS(){}
    
    // constructor dependency injection
    public DSNhanVienBUS(TableInterface tbInterface) {
        this.tbInterface = tbInterface;
    }
    
    public ArrayList<NhanVien> getDsnv() {
            return dsnv;
    }

    public void setDsnv(ArrayList<NhanVien> dsnv) {
            this.dsnv = dsnv;
    }

    public ArrayList<NhanVien> getDSFromDB(){
            dsnv.clear();
            dsnv.addAll(nvdao.getAll());
            return dsnv;
    }

    public String getTenFile() {
            return tenFile;
    }

    public void setTenFile(String tenFile) {
            this.tenFile = tenFile;
    }

    public boolean checkNotExists(NhanVien s) {  
        ArrayList<NhanVien > nvList = nvdao.getAll();
        // Lấy tất cả dữ liệu trong db gán vào ds.
        // Dò trong danh sách nếu có thì trả về false nếu không thì trả về true
        for(NhanVien nv : nvList) {                                     
                if(nv.getMANV().equals(s.getMANV())) return false;
        }
        return true;
        // Có thể thử lại với Iterator 
    }

    public boolean addNhanVien(NhanVien nv) {
        System.out.println(nv);
        boolean check = checkNotExists(nv);
        if (check && nvdao.insert(nv) == true) {
            dsnv.add(nv);
            return true;
        }
        return false;
    }

    public void updateNhanVien(NhanVien nv) {
        if (!checkNotExists(nv) && nvdao.update(nv) == true) {
            dsnv.removeIf(existingNV -> existingNV.getMANV().equals(nv.getMANV())); 
            dsnv.add(nv);
            tbInterface.resetTable(); 
        }
    }
  
  
  

    public boolean xoaNhanVien(String sdt) {
        for (int i = 0; i < dsnv.size(); i++) {
            NhanVien nv = dsnv.get(i);
            if (nv.getMANV().equals(sdt) && nvdao.delete(nv.getMANV()) == true) {
                dsnv.remove(i);
                JOptionPane.showMessageDialog(null, "Đã xóa thành công", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return true; 
            }
        }
        return false;
    }


   public NhanVien getNhanVienByMaNV(String manv) {
       for (NhanVien nv : dsnv) {
           if (nv.getMANV().equals(manv)) {
               return nv;
           }
       }
       return null; // Trả về null nếu không tìm thấy biên lai có mã số tương ứng
   }
}