/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.BenhNhanService;
import BUS.ChiTietToaThuocService;
import BUS.NhanVienService;
import BUS.PhongKhamService;

import BUS.ToaThuocService;
import DTO.BenhNhan;
import DTO.ChiTietToaThuoc;

import DTO.PhieuKhamBenh;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import DTO.PhongKham;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author phamngochoang
 */
public class XuatPhieuKhamBenh extends javax.swing.JFrame {

    /**
     * Creates new form XuatPhieuKhamBenh
     */
    BenhNhanService benhNhanService;
    PhongKhamService phongKhamService;
    NhanVienService nhanVienService;
    DefaultTableModel defaultTableModel;
    ChiTietToaThuocService chiTietToaThuocService;
    
    
    public XuatPhieuKhamBenh(PhieuKhamBenh phieuKhamBenh) throws PrinterException {
        initComponents();
        this.setLocationRelativeTo(null);
        
 
        benhNhanService = new BenhNhanService();
        
        phongKhamService = new PhongKhamService();
        
        nhanVienService = new NhanVienService();
        
        chiTietToaThuocService = new ChiTietToaThuocService();
        
        defaultTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }           
        };
        
        int maBN = phieuKhamBenh.getMABN();
        
        BenhNhan benhNhan = benhNhanService.getBenhNhanByID(maBN);
        
        if (benhNhan != null) {
            String tenbn = benhNhan.getTenBN();

            Date ngaySinhDate = benhNhan.getNgaySinh(); 

            int namSinh = ngaySinhDate.getYear() + 1900;
            String gioiTinh = benhNhan.getGioiTinh();

            String diaChi = benhNhan.getDiaChi();
//            String queQuan = benhNhan.getQueQuan().getTenTinh();
            
            InPKB(phieuKhamBenh, tenbn, namSinh, gioiTinh, diaChi);
            
        } else {
            System.err.println("Error: BenhNhan not found for MABN " + maBN);
        }
    }
    
    private void setTableData(ArrayList<ChiTietToaThuoc> chiTietToaThuocs){
        for (ChiTietToaThuoc chiTietToaThuoc : chiTietToaThuocs){
            defaultTableModel.addRow(new Object[]{chiTietToaThuoc.getMaToa(), chiTietToaThuoc.getMaThuoc(), chiTietToaThuoc.getTenThuoc(), chiTietToaThuoc.getSoLuong(), chiTietToaThuoc.getDonGia(), chiTietToaThuoc.getGhiChu()});
        }
    }
    
    void InPKB(PhieuKhamBenh phieuKhamBenh, String tenbn, int namSinh, String gioiTinh, String diaChi) throws PrinterException {   
        lbname.setText(tenbn);
        lbNamSinh.setText(String.valueOf(namSinh));
        lbGender.setText(gioiTinh);
        lbAddress.setText(diaChi);
        lbYCK.setText(phongKhamService.getPhongKhamByID(phieuKhamBenh.getMAPK()).getTenPK());
        lbBs.setText(nhanVienService.getNhanVienByIDNhanVien(phieuKhamBenh.getMABS()).getHOTEN());
        lbChanDoan.setText(phieuKhamBenh.getCHANDOAN());
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String ngayTaoStr = sdf.format(phieuKhamBenh.getNGAYTAO());
        sdf = new SimpleDateFormat("MM");
        String thangTaoStr = sdf.format(phieuKhamBenh.getNGAYTAO());
        sdf = new SimpleDateFormat("YYYY");
        String namTaoStr = sdf.format(phieuKhamBenh.getNGAYTAO());
        
        lblngay.setText("Ngày   " + ngayTaoStr + "   tháng   " + thangTaoStr + "   năm   " + namTaoStr);
        
        ArrayList<ChiTietToaThuoc> chiTietToaThuocs;
        chiTietToaThuocs = chiTietToaThuocService.getChiTietToaThuocs(phieuKhamBenh.getId());
        toaThuocCTTable.setModel(defaultTableModel);
        String[] columns = {"Mã Toa", "Mã Thuốc", "Tên Thuốc", "Số Lượng", "Đơn Giá", "Ghi chú"};
        defaultTableModel.setColumnIdentifiers(columns);
        defaultTableModel.setRowCount(0);
        setTableData(chiTietToaThuocs);
        
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Phieu Kham Benh " + phieuKhamBenh.getMAPKB());
        
        job.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pf, int pageIndex) throws PrinterException {
                
                if (pageIndex > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                
                Graphics2D g2 = (Graphics2D)graphics;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(1, 1);
                
                jPanel1.paint(g2);
                return Printable.PAGE_EXISTS;
            }
            
        });
        
        if (job.printDialog() == true) {
            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        toaThuocCTTable = new javax.swing.JTable();
        lblngay = new javax.swing.JLabel();
        lbname = new javax.swing.JLabel();
        lbNamSinh = new javax.swing.JLabel();
        lbGender = new javax.swing.JLabel();
        lbAddress = new javax.swing.JLabel();
        lbYCK = new javax.swing.JLabel();
        lbChanDoan = new javax.swing.JLabel();
        lbBs = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("PHIẾU KẾT QUẢ KHÁM BỆNH");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Mã ĐVQHNS: 3569");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("BỆNH VIỆN QUỐC TẾ");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("BỘ Y TẾ");

        jLabel2.setText("Họ tên:");

        jLabel5.setText("Địa chỉ:");

        jLabel6.setText("Năm sinh:");

        jLabel7.setText("Giới tính:");

        jLabel8.setText("Yêu cầu khám:");

        jLabel9.setText("Bác sĩ điều trị:");

        jLabel10.setText("Chẩn đoán:");

        jLabel12.setText("Toa thuốc");

        toaThuocCTTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(toaThuocCTTable);

        lblngay.setText("NGÀY");

        lbname.setText("jLabel13");

        lbNamSinh.setText("jLabel13");

        lbGender.setText("jLabel13");

        lbAddress.setText("jLabel13");

        lbYCK.setText("jLabel13");

        lbChanDoan.setText("jLabel13");

        lbBs.setText("jLabel13");

        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbChanDoan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(197, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblngay, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(192, 192, 192))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbYCK)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lbBs)
                                    .addGap(184, 184, 184))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbname)
                                    .addGap(155, 155, 155)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbNamSinh)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbGender)
                                    .addGap(221, 221, 221))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel12)
                                            .addGap(46, 46, 46)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lbAddress)))
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(46, 46, 46)
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jLabel1)
                .addGap(141, 141, 141)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lbChanDoan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 296, Short.MAX_VALUE)
                .addComponent(lblngay)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(jLabel3))
                        .addComponent(jButton1))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(75, 75, 75)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(lbname))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(lbAddress))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9)
                                .addComponent(lbYCK)
                                .addComponent(lbBs)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7)
                                .addComponent(lbNamSinh)
                                .addComponent(lbGender))
                            .addGap(70, 70, 70)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(35, 35, 35))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(148, 148, 148)
                            .addComponent(jLabel12)
                            .addGap(188, 188, 188)))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(XuatPhieuKhamBenh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(XuatPhieuKhamBenh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(XuatPhieuKhamBenh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(XuatPhieuKhamBenh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new XuatPhieuKhamBenh().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbAddress;
    private javax.swing.JLabel lbBs;
    private javax.swing.JLabel lbChanDoan;
    private javax.swing.JLabel lbGender;
    private javax.swing.JLabel lbNamSinh;
    private javax.swing.JLabel lbYCK;
    private javax.swing.JLabel lblngay;
    private javax.swing.JLabel lbname;
    private javax.swing.JTable toaThuocCTTable;
    // End of variables declaration//GEN-END:variables

}
