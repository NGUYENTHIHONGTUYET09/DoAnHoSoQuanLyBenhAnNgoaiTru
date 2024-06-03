
package BUS;
import DAO.CTDonThuocDAO;
import DTO.CTDonThuoc;

import java.util.ArrayList;
/**
 *
 * @author phamngochoang
 */
public class CTDonThuocService {
    private CTDonThuocDAO cTDonThuocDAO;

    public CTDonThuocService() {
        cTDonThuocDAO = new CTDonThuocDAO();
    }
    
        
    public int addCTDT(CTDonThuoc cTDonThuoc)
    {
        return cTDonThuocDAO.addCTDT(cTDonThuoc);
    }
    
    public int addCTDT_PROC(CTDonThuoc cTDonThuoc) {
    	return cTDonThuocDAO.addCTDT_PROC(cTDonThuoc);
    }
    
//    public int themChiTietDonThuoc_NON(CTDonThuoc cTDonThuoc) {
//    	return cTDonThuocDAO.themChiTietDonThuoc_NON(cTDonThuoc);
//    }
    
}
