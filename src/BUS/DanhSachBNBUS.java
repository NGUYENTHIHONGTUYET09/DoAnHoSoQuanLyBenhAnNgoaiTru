package BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import DTO.BenhNhan;
import GUI.QuanLyThongTinBenhNhan;
import DAO.BenhNhanDAO;
import DAO.TinhDAO;
import DTO.Tinh;
import Report.dtos.ReportDTO;
import interfaces.TableInterface;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DanhSachBNBUS {

    private ArrayList<BenhNhan> dsbn = new ArrayList<BenhNhan>();
    private TableInterface tbInterface;
    private BenhNhanDAO bndao = new BenhNhanDAO();
    public String tenFile;
    private TinhDAO tinhdao;

    public DanhSachBNBUS(TableInterface tbInterface) {
        // Không tạo mới dsbn nữa
//    	dsbn = new ArrayList<BenhNhan>();
        this.tbInterface = tbInterface;
        this.tinhdao = TinhDAO.getInstance();
    }

    public ArrayList<BenhNhan> getDsbn() {
        return dsbn;
    }

    public void setDsbn(ArrayList<BenhNhan> dsbn) {
        this.dsbn = dsbn;
    }

    public ArrayList<BenhNhan> getDSFromDB() {
        dsbn.clear();
        dsbn.addAll(bndao.fetchAllBenhNhan());
        return dsbn;
    }

    public String getTenFile() {
        return tenFile;
    }

    public void setTenFile(String tenFile) {
        this.tenFile = tenFile;
    }

    public boolean checkNotExists(BenhNhan s) {
        ArrayList<BenhNhan> bnList = bndao.fetchAllBenhNhan();

        for (BenhNhan bn : bnList) {
            if (bn.getSdt().equals(s.getSdt())) {
                return false;
            }
        }
        return true;

    }

    public boolean addBenhNhan(BenhNhan bn) {
        boolean check = checkNotExists(bn);
        if (check && bndao.insertBenhNhan(bn) > 0) {
            dsbn.add(bn);
            return true;
        }
        return false;
    }

    public void updateBenhNhan(BenhNhan bn) {

        if (!checkNotExists(bn) && bndao.updateBenhNhan(bn) > 0) {
            dsbn.removeIf(existingBN -> existingBN.getSdt().equals(bn.getSdt()));
            dsbn.add(bn);
            tbInterface.resetTable();
        }
    }

    public boolean xoaBenhNhan(String sdt) {
        for (int i = 0; i < dsbn.size(); i++) {
            BenhNhan bn = dsbn.get(i);
            if (bn.getSdt().equals(sdt) && bndao.deleteBenhNhan(bn) > 0) {
                dsbn.remove(i);
                JOptionPane.showMessageDialog(null, "Đã xóa thành công", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Đã xóa thất bại", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return false;

    }

    public BenhNhan getBenhNhanByMaBN(String mabn) {
        for (BenhNhan bn : dsbn) {
            if (bn.getMaBN().equals(mabn)) {
                return bn;
            }
        }
        return null;
    }

    public List<ReportDTO> getDSGroupedByQueQuan() {
        List<BenhNhan> allPatients = bndao.fetchAllBenhNhan();
        Map<String, Integer> groupedData = new HashMap<>();
        List<ReportDTO> result = new ArrayList<>();
        // Gom nhóm dữ liệu bệnh nhân theo quê quán
        for (BenhNhan bn : allPatients) {
            Tinh tinh = tinhdao.getTinhById(bn.getQueQuan());  // Lay tinh 
            String queQuan = tinh.getTenTinh();  // lay ten tinh
            groupedData.put(queQuan, groupedData.getOrDefault(queQuan, 0) + 1);  // Lấy ds bn theo tỉnh nếu không có thì default 0
        } // Trả về map
        for (Map.Entry<String, Integer> entry : groupedData.entrySet()) {
            result.add(new ReportDTO(entry.getKey(), entry.getValue()));
            System.out.print(result);
        }
        return result;
    }

   
}


