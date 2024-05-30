package BUS;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import DAO.TaiKhoanDAO;
import DTO.BenhNhan;
import DTO.TaiKhoan;
import GUI.DangKyGUI;
import GUI.DanhSachTaiKhoan;
import interfaces.TableInterface;
import javax.swing.JFrame;

public class DanhSachTKBUS {

    private ArrayList<TaiKhoan> dsdk = new ArrayList<TaiKhoan>();
    private TaiKhoanDAO dkdao = TaiKhoanDAO.getInstance();
    public String tenFile;
    private TableInterface tableInterface;
    private JFrame frameTruoc;
   
    public DanhSachTKBUS(){}

    public DanhSachTKBUS(JFrame frameTruoc){
        this.frameTruoc = frameTruoc;
    }
    
    public DanhSachTKBUS(TableInterface tableInterface) {
        this.tableInterface = tableInterface;
    }
    


    public ArrayList<TaiKhoan> getDsdk() {
        return dsdk;
    }

    public void setDsdk(ArrayList<TaiKhoan> dsdk) {
        this.dsdk = dsdk;
    }

    public ArrayList<TaiKhoan> getDSFromDB() {
        dsdk.clear();
        dsdk.addAll(dkdao.fetchAllDangKy());
        return dsdk;
    }

    public String getTenFile() {
        return tenFile;
    }

    public void setTenFile(String tenFile) {
        this.tenFile = tenFile;
    }

    public boolean checkNotExists(TaiKhoan dk) {
        ArrayList<TaiKhoan> dkList = dkdao.fetchAllDangKy();

        for (TaiKhoan dki : dkList) {
            if (dki.getMaSoNV() == dk.getMaSoNV() && dk.getMaSoNV() != 0) {
                System.out.println("Duplicate MaSoNV found: " + dki.getMaSoNV());
                return false;
            }
        }

        return true;
    }

    public boolean checkEmailNotExists(String email) {

        ArrayList<TaiKhoan> dkList = getDSFromDB();
        for (TaiKhoan dk : dkList) {
            if (dk.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public boolean addDangKy(TaiKhoan dk) {
        boolean check = checkNotExists(dk);
        if (check && dkdao.insert(dk) > 0) {
            dsdk.add(dk);
            if(tableInterface != null){
                tableInterface.resetTable();
            }
            return true;
        }
        return false;
    }

    public void updateDangKy(TaiKhoan dk) {
        if (!checkNotExists(dk) && dkdao.update(dk) > 0) {
            if (dk.getMaSoNV() > 0) {
                dsdk.removeIf(existingDK -> existingDK.getMaSoNV() == dk.getMaSoNV());
            }
            dsdk.add(dk);
            tableInterface.resetTable();
        }
    }

    public void xoaDangKy(String ma) {
        // Tìm kiếm bệnh nhân trong danh sách
        for (int i = 0; i < dsdk.size(); i++) {
            TaiKhoan dk = dsdk.get(i);
            int maSo = 0; // Mã sẽ được sử dụng để xóa

            // Kiểm tra xem ma là mã của maSoBN hay maSoNV
            if (dk.getMaSoNV() == Integer.valueOf(ma)) {
                maSo = dk.getMaSoNV();
            }

            // Nếu maSo khác 0 và hàm deleteDangKy trả về kết quả lớn hơn 0, tức là xóa thành công
            if (maSo != 0 && dkdao.deleteDangKy(dk) > 0) {
                dsdk.remove(i);
                tableInterface.resetTable();
                JOptionPane.showMessageDialog(null, "Đã xóa thành công", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            } else {
                JOptionPane.showMessageDialog(null, "Xóa không thành công", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        // Nếu không tìm thấy bệnh nhân trong danh sách
        JOptionPane.showMessageDialog(null, "Không tìm thấy bệnh nhân", "Thông báo", JOptionPane.WARNING_MESSAGE);
    }

}
