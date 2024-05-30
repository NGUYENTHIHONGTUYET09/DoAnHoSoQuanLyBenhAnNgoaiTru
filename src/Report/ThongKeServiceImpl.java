package Report;

import BUS.DanhSachBNBUS;
import DTO.BenhNhan;
import DTO.BienLaiDTO;
import Report.dtos.ReportDTO;
import java.util.ArrayList;
import java.util.List;

public class ThongKeServiceImpl {
    private List<ReportDTO> dsbn;
   
    public ThongKeServiceImpl(List<ReportDTO> dsbn) {
    	this.dsbn = dsbn;
    }
    
    
    public List<ReportDTO> getDSGroupedByQueQuan() {
        return dsbn;
    }
    
 
}
