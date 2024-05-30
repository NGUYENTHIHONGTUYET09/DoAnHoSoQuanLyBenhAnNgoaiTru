/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.ToaThuocDAO;
import DTO.ToaThuoc;
import java.util.ArrayList;
/**
 *
 * @author phamngochoang
 */
public class ToaThuocService {
    private final ToaThuocDAO toaThuocDAO;
    
    public ToaThuocService() {
        toaThuocDAO = new ToaThuocDAO();
    }
    
    public ArrayList <ToaThuoc> getAllToaThuocs()
    {
        return toaThuocDAO.getAllToaThuocs();
    }
    
    public ArrayList <ToaThuoc> getToaThuocsByBenhNhanID(int id)
    {
        return toaThuocDAO.getToaThuocsByBenhNhanID(id);
    }
    
    public int addToaThuoc(ToaThuoc toaThuoc)
    {
        return toaThuocDAO.addToaThuoc(toaThuoc);
    }
    
    public ToaThuoc getlastToaThuoc(){
        return toaThuocDAO.getlastRowDataToaThuoc();
    }
    
    public double tinhTongTien(int toaThuocId){
        return toaThuocDAO.tinhTongToaThuoc(toaThuocId);
    }
    
    public void updateTongTien(int toaThuocId, double tongTien)
    {
        toaThuocDAO.updateTongTien(toaThuocId, tongTien);
    }
}
