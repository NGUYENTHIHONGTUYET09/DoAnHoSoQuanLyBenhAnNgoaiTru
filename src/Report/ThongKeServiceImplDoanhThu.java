/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Report;

import DAO.BienLaiDAO;
import DTO.BienLaiDTO;
import Report.dtos.ReportDTO;
import java.util.List;

public class ThongKeServiceImplDoanhThu {

    private List<ReportDTO> dsbl;

    
    public ThongKeServiceImplDoanhThu(List<ReportDTO> dsbl) {
    	this.dsbl = dsbl;
    }
    
    public List<ReportDTO> getDoanhThuByYear() {
        return dsbl;
    }
}
