/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.BenhNhanService;
import BUS.CTDonThuocService;
import BUS.NhanVienService;
import DTO.PhieuKhamBenh;
import BUS.PhieuKhamBenhService;
import BUS.PhongKhamService;
import BUS.ToaThuocService;
import DTO.BenhNhan;
import DTO.CTDonThuoc;
import DTO.NhanVien;
import DTO.PhongKham;
import DTO.ToaThuoc;
import interfaces.AddListThuocInterface;
import java.awt.print.PrinterException;
import java.util.ArrayList;

//import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JTextFieldDateEditor;
/**
 *
 * @author phamngochoang
 */
public class ThemPKB extends javax.swing.JFrame {
    PhieuKhamBenhService phieuKhamBenhService;
    PhieuKhamBenh phieuKhamBenh;
    
    NhanVien nhanVien;
    NhanVienService nhanVienService;
    
    BenhNhan benhNhan;
    BenhNhanService benhNhanService;
    
    PhongKham phongKham;
    PhongKhamService phongKhamService;
    
    ToaThuoc toaThuoc;
    ToaThuocService toaThuocService;
    
    CTDonThuoc cTDonThuoc;
    CTDonThuocService cTDonThuocService;
    
    DefaultTableModel defaultTableModel;
    /**
     * Creates new form NewThemPKB
     */
    public ThemPKB() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        phieuKhamBenhService = new PhieuKhamBenhService();
        phieuKhamBenh = new PhieuKhamBenh();
               
        nhanVienService = new  NhanVienService();
        ArrayList <NhanVien> nhanViens = nhanVienService.getAllBacSis();
        
//        int nhanViensSize = nhanViens.size();
//        int[] bacSiIDs = new int[nhanViensSize];
//        for(int i = 0; i < nhanViensSize; i++){
//            bacSiIDs[i] = nhanViens.get(i).getId();
//            maBSCb.addItem(String.valueOf(bacSiIDs[i]));
//        }
        
        maBSCb.setEnabled(false);
        
        benhNhanService = new BenhNhanService();
        ArrayList <BenhNhan> benhNhans = benhNhanService.getAllBenhNhans();

        
        
//        int benhNhansSize = benhNhans.size();
//        int[] benhNhanIDs = new int[benhNhansSize];
//        for(int i = 0; i < benhNhansSize; i++){
//            benhNhanIDs[i] = benhNhans.get(i).getId();
//            maBNComboBox.addItem(String.valueOf(benhNhanIDs[i]));
//        }
        
        phongKhamService = new PhongKhamService();        
        ArrayList <PhongKham> phongKhams = phongKhamService.getAllPhongKhams();      
        
        int phongKhamsSize = phongKhams.size();
        String[] phongKhamNames = new String[phongKhamsSize];
        for(int i = 0; i < phongKhamsSize; i++){
            phongKhamNames[i] = phongKhams.get(i).getTenPK();
            PKComboBox.addItem(String.valueOf(phongKhamNames[i]));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        JTextFieldDateEditor editorNgayTao = (JTextFieldDateEditor) jDateChooser1.getDateEditor();
        editorNgayTao.setEditable(false);
        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        
        maPKLb = new javax.swing.JLabel();
        maBSCb = new javax.swing.JComboBox<>();
        PKComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        maKBLb = new javax.swing.JLabel();
        maBSLb = new javax.swing.JLabel();
        maBNLb = new javax.swing.JLabel();
        ngayTaoLb = new javax.swing.JLabel();
//        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        inPKB = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        sdtTextField = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        maBNTextField = new javax.swing.JTextField();
        tenBNTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        maPKLb.setText("Tên PK");

        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setText("THÊM MỚI PHIẾU KHÁM BỆNH");

        maKBLb.setText("Mã Khám Bệnh");

        maBSLb.setText("Mã Bác Sĩ");

        maBNLb.setText("Mã Bệnh Nhân");

        ngayTaoLb.setText("Ngày tạo");

//        jTextField1.setEditable(false);
//        jTextField1.setEnabled(false);

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Thoát");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        inPKB.setText("In PKB");
        inPKB.setSize(new java.awt.Dimension(120, 23));
        inPKB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inPKBActionPerformed(evt);
            }
        });

        jLabel2.setText(" Nhập SĐT");

        jButton3.setText("Tìm kiếm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        maBNTextField.setEditable(false);

        tenBNTextField.setEditable(false);

        jLabel3.setText("Tên bệnh nhân");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(maBNLb))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tenBNTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(maBNTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(maBSLb)
                                        .addComponent(maKBLb)
                                        .addComponent(jLabel2))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
//                                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(maBSCb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(sdtTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(inPKB, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(40, 40, 40)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(maPKLb)
                                                .addComponent(ngayTaoLb))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(PKComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(1, 1, 1))))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maKBLb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maBSLb)
                    .addComponent(maBSCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sdtTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maBNTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maBNLb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tenBNTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maPKLb)
                    .addComponent(PKComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ngayTaoLb))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(inPKB))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
//            phieuKhamBenh.setMAPKB(jTextField1.getText());
//            phieuKhamBenh.setMABS(Integer.parseInt(maBSCb.getSelectedItem().toString()));
            phieuKhamBenh.setMABS(1);
            
            phieuKhamBenh.setMABN(Integer.parseInt(maBNTextField.getText()));
            
            int phongKhamID = phongKhamService.getPhongKhamByTenPK(PKComboBox.getSelectedItem().toString()).getId();
            phieuKhamBenh.setMAPK(phongKhamID);

            Date ngayTao = jDateChooser1.getDate();
            java.sql.Date sqlDate = new java.sql.Date(ngayTao.getTime());
            phieuKhamBenh.setNGAYTAO(sqlDate);

            phieuKhamBenh.setCHANDOAN("");

            phieuKhamBenhService.addPhieuKhamBenh(phieuKhamBenh);
            System.out.println("PhieuKhamBenh added successfully."); 
            
            PhieuKhamBenh LastphieuKhamBenh = phieuKhamBenhService.getlastPhieuKhamBenh();
            System.out.println("Ma PKB: " + LastphieuKhamBenh.getMAPKB()); 

//            new QuanLyThongTinKhamBenh().setVisible(true);
//            this.dispose();
        } catch (NumberFormatException ex) {
            // Print the stack trace for debugging
            // Handle the exception (e.g., show an error message)
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    	TrangChu tc = new TrangChu();
    	tc.setVisible(true);
    	dispose();
       // this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void inPKBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inPKBActionPerformed
        // TODO add your handling code here:
        PhieuKhamBenh LastphieuKhamBenh = phieuKhamBenhService.getlastPhieuKhamBenh();
        System.out.println("Ma PKB: " + LastphieuKhamBenh.getMAPKB()); 
        try {
            new PhieuKhamBenhPrintGUI(LastphieuKhamBenh).setVisible(true);
        } catch (PrinterException ex) {
            Logger.getLogger(ThemPKB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_inPKBActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    // Retrieve the selected phone number from the text field
        String sdt = sdtTextField.getText();

        // Fetch the patient information using the phone number
        BenhNhan bn = benhNhanService.getBenhNhanBySDT(sdt);

        // Check if the patient is found
        if (bn == null) {
            // Display a message dialog to inform the user that the patient is not found
            javax.swing.JOptionPane.showMessageDialog(this, "Bệnh nhân không tồn tại!", "Thông báo", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } else {
            // If the patient is found, update the text fields with patient information
            maBNTextField.setText(String.valueOf(bn.getId()));
            tenBNTextField.setText(bn.getTenBN());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThemPKB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemPKB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemPKB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemPKB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemPKB().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> PKComboBox;
    private javax.swing.JButton inPKB;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
//    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel maBNLb;
    private javax.swing.JTextField maBNTextField;
    private javax.swing.JComboBox<String> maBSCb;
    private javax.swing.JLabel maBSLb;
    private javax.swing.JLabel maKBLb;
    private javax.swing.JLabel maPKLb;
    private javax.swing.JLabel ngayTaoLb;
    private javax.swing.JTextField sdtTextField;
    private javax.swing.JTextField tenBNTextField;
    // End of variables declaration//GEN-END:variables
}