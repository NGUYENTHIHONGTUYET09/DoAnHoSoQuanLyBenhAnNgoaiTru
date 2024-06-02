/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DTO.PhieuKhamBenh;
import java.util.ArrayList;
import DAO.PhieuKhamBenhDAO;
import DTO.NhanVien;

/**
 *
 * @author phamngochoang
 */
public class PhieuKhamBenhService {
    private final PhieuKhamBenhDAO phieuKhamBenhDao;
    
    public PhieuKhamBenhService(){
        phieuKhamBenhDao = new PhieuKhamBenhDAO();
    }
    
    public ArrayList <PhieuKhamBenh> getAllPhieuKhamBenhs(){
        return phieuKhamBenhDao.getAllPhieuKhamBenhs();
    }
    
    public int addPhieuKhamBenh(PhieuKhamBenh phieuKhamBenh)
    {
        return phieuKhamBenhDao.addPKB(phieuKhamBenh);
    }
    
    public void xoaPhieuKhamBenh(int id){
        phieuKhamBenhDao.xoaPKB(id);
    }
    
    
 
    public PhieuKhamBenh getPhieuKhamBenhByID(int id){
        return phieuKhamBenhDao.getPKBbyID(id);
    }
    
    public void suaPhieuKhamBenh(PhieuKhamBenh phieuKhamBenh)
    {
        phieuKhamBenhDao.suaPKB(phieuKhamBenh);
    }
    
    public PhieuKhamBenh getPhieuKhamBenhByMAPKB(String maPKB){
        return phieuKhamBenhDao.timPKBbyMaPKB(maPKB);
    }
    
    public ArrayList <PhieuKhamBenh> getPhieuKhamBenhByTenBN(String tenBN){
        return phieuKhamBenhDao.timPKBbyTenBN(tenBN);
    }
    
    public ArrayList <PhieuKhamBenh> getPhieuKhamBenhByBnID(int id){
        return phieuKhamBenhDao.timPKBbyBnID(id);
    }
    ////// --------- CHI TIET PKB GUI
    public PhieuKhamBenh getlastPhieuKhamBenh(){
        return phieuKhamBenhDao.getlastRowDataPKB();
    }
}
