/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhanVienDAO;

import DTO.NhanVien;
import java.util.ArrayList;

/**
 *
 * @author phamngochoang
 */
public class NhanVienService {
    private final NhanVienDAO nhanVienDAO;
    
    public NhanVienService(){
        nhanVienDAO = new NhanVienDAO();
    }
    
    public NhanVien getNhanVienByIDNhanVien(int id)
    {
        return nhanVienDAO.getById(id);
    }
    
    public ArrayList <NhanVien> getAllNhanViens(){
        return nhanVienDAO.getAll();
    }
    
    public ArrayList <NhanVien> getAllBacSis(){
        return nhanVienDAO.getAllBacSis();
    }
}