package BUS;

import DAO.BenhNhanDAO;
import DAO.BienLaiDAO;
import DTO.BenhNhan;
import DTO.BienLaiDTO;
import GUI.QLyBienLaiGUI;
import Report.dtos.ReportDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class DSBienLaiBUS {

    private ArrayList<BienLaiDTO> dsbl = new ArrayList<BienLaiDTO>();
    private QLyBienLaiGUI qlbl;
    private BienLaiDAO bldao = new BienLaiDAO();
    public String tenFile;

    public DSBienLaiBUS(QLyBienLaiGUI qlbl) {
        this.qlbl = qlbl;
    }

    public ArrayList<BienLaiDTO> getDsbl() {
        return dsbl;
    }

    public void setDsbl(ArrayList<BienLaiDTO> dsbl) {
        this.dsbl = dsbl;
    }

    public ArrayList<BienLaiDTO> getDSFromDB() {
        dsbl.clear();
        dsbl.addAll(bldao.getAll());
        return dsbl;
    }

    public String getTenFile() {
        return tenFile;
    }

    public void setTenFile(String tenFile) {
        this.tenFile = tenFile;
    }

    public boolean checkNotExists(String maToa) {
        ArrayList<BienLaiDTO> blList = bldao.getAll();
        // Lấy tất cả dữ liệu trong db gán vào ds.
        // Dò trong danh sách nếu có thì trả về false nếu không thì trả về true
        for (BienLaiDTO bl : blList) {

            if (bl.getMaToa().equals(maToa)) {
                return false;
            }
        }
        return true;
        // Có thể thử lại với Iterator 
    }

    public boolean addBienLaiDTO(String maToa) {
        boolean check = checkNotExists(maToa);
        if (check && bldao.insert(maToa) == true) {
            BienLaiDAO blDao = new BienLaiDAO();
            BienLaiDTO bl = blDao.getByMaToa(maToa);
            dsbl.add(bl);
            return true;
        }
        return false;
    }

    public void xoaBienLaiDTO(String maBL) {
        for (int i = 0; i < dsbl.size(); i++) {
            BienLaiDTO bl = dsbl.get(i);
            if (bl.getMaBL().equals(maBL) && bldao.delete(bl.getMaBL()) == true) {
                dsbl.remove(i);
                System.out.println(dsbl);
                JOptionPane.showMessageDialog(qlbl, "Đã xóa thành công", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
    }

    public BienLaiDTO getBienLaiDTOByMaBL(String mabl) {
        for (BienLaiDTO bl : dsbl) {
            if (bl.getMaBL().equals(mabl)) {
                return bl;
            }
        }
        return null; // Trả về null nếu không tìm thấy biên lai có mã số tương ứng
    }

    public BenhNhan getBenhNhanByMaBL(String mabl) {
        BenhNhanDAO bnd = new BenhNhanDAO();
        BenhNhan bn = bnd.getBenhNhanByMaBL(mabl);
        return bn;
    }

    public double getTongTienKham(String maToa) {
        double tongTienKham = bldao.tinhTongTienKham(maToa);
        return tongTienKham;
    }

    public List<ReportDTO> getDoanhThuGroupByYear() {
        List<ReportDTO> doanhThuByYear = new ArrayList<>();
//        List<BienLaiDTO> allBienLais = bldao.getAll(); // dm
        List<BienLaiDTO> allBienLais = bldao.getDoanhThuGroupByYear();
        Map<String, Double> groupedData = new HashMap<>();

        for (BienLaiDTO bl : allBienLais) {
            
            System.out.print("Bien lai DTO"+bl);
            int nam = bl.getNam();
            double tongTienKham = bl.getTongTienKham();
         //   groupedData.put(String.valueOf(nam), groupedData.get(String.valueOf(nam)) + tongTienKham); // nam, so tien
         groupedData.put(String.valueOf(nam), tongTienKham);
        }

        for (Map.Entry<String, Double> entry : groupedData.entrySet()) {

            ReportDTO rp = new ReportDTO(
                    String.valueOf(entry.getKey()),
                    entry.getValue()
            );
            doanhThuByYear.add(rp);
        }

        return doanhThuByYear;
    }
}
