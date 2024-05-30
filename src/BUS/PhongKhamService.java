/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.PhongKhamDAO;

import DTO.PhongKham;
import java.util.ArrayList;

/**
 *
 * @author phamngochoang
 */
public class PhongKhamService {
    private final PhongKhamDAO phongKhamDAO;
    
    public PhongKhamService() {
        phongKhamDAO = new PhongKhamDAO();
    }
    
    public PhongKham getPhongKhamByID(int id)
    {
        return phongKhamDAO.timPKbyID(id);
    }
    
    public ArrayList <PhongKham> getAllPhongKhams(){
        return phongKhamDAO.getAllPhongKhams();
    }
    
    public PhongKham getPhongKhamByTenPK(String tenPK)
    {
        return phongKhamDAO.getPKByTenPK(tenPK);
    }
}
