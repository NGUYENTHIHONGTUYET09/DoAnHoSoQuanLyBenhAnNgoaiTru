/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ThuocDAO;
import DTO.Thuoc;
import java.util.ArrayList;
/**
 *
 * @author phamngochoang
 */
public class ThuocService {
    private final ThuocDAO thuocDAO;
    
    public ThuocService(){
        thuocDAO = new ThuocDAO();
    }
    
    public ArrayList <Thuoc> getAllThuocs(){
        return thuocDAO.getAllThuocs();
    }
    
    public ArrayList <Thuoc> getThuocsByTenThuocs(String tenThuoc){
        return thuocDAO.timThuocbyTenThuocs(tenThuoc);
    }
    
    public void addThuoc(Thuoc thuoc)
    {
        thuocDAO.addThuoc(thuoc);
    }
    
    public void removeThuoc(int id){
        thuocDAO.xoaThuoc(id);
    }
    
    public Thuoc getThuocByID(int id){
        return thuocDAO.getThuocByID(id);
    }
    
    public void suaThuoc(Thuoc thuoc)
    {
        thuocDAO.suaThuoc(thuoc);
    }
    
    public void suaThuoc1(Thuoc thuoc)
    {
        thuocDAO.suaThuoc1(thuoc);
    }
}