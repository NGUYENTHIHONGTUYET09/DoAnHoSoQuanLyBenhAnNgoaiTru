/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.BenhNhanDAO;
import DTO.BenhNhan;

import java.util.ArrayList;

/**
 *
 * @author phamngochoang
 */
public class BenhNhanService {
    private final BenhNhanDAO benhNhanDAO;
    
    public BenhNhanService(){
        benhNhanDAO = new BenhNhanDAO();
    }
    
    public BenhNhan getBenhNhanByID(int id)
    {
        return benhNhanDAO.getById(id);
    }
    
    public ArrayList <BenhNhan> getAllBenhNhans(){
        return benhNhanDAO.fetchAllBenhNhan();
    }
    
    public BenhNhan getBenhNhanBySDT(String sdt)
    {
        return benhNhanDAO.timBNbySDT(sdt);
    }
}