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

//    

    public boolean xoaDangKy(String ma) {
        boolean deleted = false;

        // Iterate over the list to find the matching record
        for (int i = 0; i < dsdk.size(); i++) {
            TaiKhoan dk = dsdk.get(i);

            // Check if the provided ID matches the record's ID
            if (dk.getMaSoNV() == Integer.valueOf(ma)) {
                // Attempt to delete the record from the database
                if (dkdao.deleteDangKy(dk) > 0) {
                    // Remove the record from the list
                    dsdk.remove(i);
                    // Reset the table interface if needed
                    tableInterface.resetTable();
                    JOptionPane.showMessageDialog(null, "Deleted successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    deleted = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Deletion failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
        }

        // If the record was not found in the list
        if (!deleted) {
            JOptionPane.showMessageDialog(null, "Record not found.", "Warning", JOptionPane.WARNING_MESSAGE);
        }

        return deleted;
    }


}
