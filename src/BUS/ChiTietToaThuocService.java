/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietToaThuocDAO;
import DTO.ChiTietToaThuoc;
import java.util.ArrayList;

/**
 *
 * @author phamngochoang
 */
public class ChiTietToaThuocService {
    private final ChiTietToaThuocDAO chiTietToaThuocDAO;
    
    public ChiTietToaThuocService() {
        chiTietToaThuocDAO = new ChiTietToaThuocDAO();
    }
    
    public ArrayList<ChiTietToaThuoc> getChiTietToaThuocs(int MaPKB)
    {
        return chiTietToaThuocDAO.getCTTT(MaPKB);
    }
}
