
package GUI;


import BUS.BenhNhanService;
import BUS.NhanVienService;
import DTO.PhieuKhamBenh;
import BUS.PhieuKhamBenhService;
import BUS.PhongKhamService;

import java.awt.event.MouseEvent;

import DTO.NhanVien;

import DTO.BenhNhan;
import DTO.PhongKham;
import java.awt.print.PrinterException;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;


public class QuanLyThongTinKhamBenh extends javax.swing.JFrame {
    PhieuKhamBenhService phieuKhamBenhService;
    DefaultTableModel defaultTableModel;
      
private void setTableData(ArrayList<PhieuKhamBenh> phieuKhamBenhs) {
    NhanVienService nhanVienService = new NhanVienService();
    BenhNhanService benhNhanService = new BenhNhanService();
    PhongKhamService phongKhamService = new PhongKhamService();          
    
    for (PhieuKhamBenh phieuKhamBenh : phieuKhamBenhs) {        
        System.out.println(phieuKhamBenh);
        NhanVien nhanVien = nhanVienService.getNhanVienByIDNhanVien(phieuKhamBenh.getMABS());
        BenhNhan benhNhan = benhNhanService.getBenhNhanByID(phieuKhamBenh.getMABN());
        PhongKham phongKham = phongKhamService.getPhongKhamByID(phieuKhamBenh.getMAPK());
        
        defaultTableModel.addRow(new Object[]{
            phieuKhamBenh.getId(),
            phieuKhamBenh.getMAPKB(),
            nhanVien.getMANV(),
            benhNhan.getMaBN(),
            phongKham.getMaPK(),
            phieuKhamBenh.getNGAYTAO(),
            phieuKhamBenh.getCHANDOAN()
        });              
    }
}
    
    
    
    public QuanLyThongTinKhamBenh() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        phieuKhamBenhService = new PhieuKhamBenhService();
        
        defaultTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }           
        };
        
        PKBTable.setModel(defaultTableModel);
        
        String[] columns = {"ID", "MaPKB", "MaBS", "maBN", "maPK", "Ngay Tao", "Chan Doan"};
        defaultTableModel.setColumnIdentifiers(columns);
        
        setTableData(phieuKhamBenhService.getAllPhieuKhamBenhs());
     
        
        
    }

 
    @SuppressWarnings("unchecked")
  
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        chiTietPKB = new javax.swing.JMenuItem();
        PKBPrint = new javax.swing.JMenuItem();
        xuatPKB = new javax.swing.JMenuItem();
        TitleLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PKBTable = new javax.swing.JTable();
        searchField = new javax.swing.JTextField();
        refreshButton = new javax.swing.JButton();
        removeBt = new javax.swing.JButton();
        updateBt = new javax.swing.JButton();
        exitBt = new javax.swing.JButton();
        findBt = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        chiTietPKB.setText("Xem phiếu khám bệnh");
        chiTietPKB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chiTietPKBActionPerformed(evt);
            }
        });
        jPopupMenu1.add(chiTietPKB);

        PKBPrint.setText("In Phieu Kham Benh");
        PKBPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PKBPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(PKBPrint);

        xuatPKB.setText("Xuất phiếu khám bệnh");
        xuatPKB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xuatPKBActionPerformed(evt);
            }
        });
        jPopupMenu1.add(xuatPKB);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TitleLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        TitleLabel.setForeground(new java.awt.Color(0, 51, 204));
        TitleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconHospital.png"))); // NOI18N
        TitleLabel.setText(" QUẢN LÝ THÔNG TIN KHÁM BỆNH");
        TitleLabel.setToolTipText("");

        PKBTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
            		 "Số thứ tự", "Tên thuốc", "Số lượng ", "Ghi chú"
            }
        ));
        PKBTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        PKBTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        PKBTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PKBTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(PKBTable);

        searchField.setFont(new java.awt.Font("Helvetica Neue", 2, 13)); // NOI18N
        searchField.setForeground(new java.awt.Color(204, 204, 204));
        searchField.setText("Nhập tên bệnh nhân");
        searchField.setToolTipText("Nhập chính xác tên bệnh nhân ");
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });

        refreshButton.setText("Làm Mới");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        removeBt.setText("Xoá");
        removeBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtActionPerformed(evt);
            }
        });

        updateBt.setText("Kê toa");
        updateBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtActionPerformed(evt);
            }
        });

        exitBt.setText("Thoát");
        exitBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtActionPerformed(evt);
            }
        });

        findBt.setText("Tìm Kiếm");
        findBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(removeBt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateBt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103)
                        .addComponent(exitBt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(TitleLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(findBt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(198, 198, 198)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(TitleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(findBt, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeBt)
                    .addComponent(updateBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exitBt))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }

    private void removeBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtActionPerformed
      
        int row = PKBTable.getSelectedRow();
        
        if (row == -1){
            JOptionPane.showMessageDialog(QuanLyThongTinKhamBenh.this, "Vui lòng chọn dữ liệu cần xoá", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        else {
            int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa dòng đã chọn?");
            
            if (luaChon == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(String.valueOf(PKBTable.getValueAt(row, 0)));
                
                
//                PhieuKhamBenh phieuKhamBenh = phieuKhamBenhService.getPKBbyID(id);
                
                phieuKhamBenhService.xoaPhieuKhamBenh(id);
            }
        }
        
        defaultTableModel.setRowCount(0);
        setTableData(phieuKhamBenhService.getAllPhieuKhamBenhs());       
    }

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        
        defaultTableModel.setRowCount(0);
        setTableData(phieuKhamBenhService.getAllPhieuKhamBenhs());
    }
    private void updateBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtActionPerformed
     
        int row = PKBTable.getSelectedRow();
        
        if (row == -1){
            JOptionPane.showMessageDialog(QuanLyThongTinKhamBenh.this, "Vui lòng chọn dữ liệu cần sửa", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        else {     
            int id = Integer.parseInt(String.valueOf(PKBTable.getValueAt(row, 0)));

         
            new KeToa(id).setVisible(true);
//            this.dispose();           
        }  
    }

    private void exitBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtActionPerformed
  
        TrangChuDangNhapGUI tcdng = new TrangChuDangNhapGUI();
        tcdng.setVisible(true);
        dispose();
    }

    private void findBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findBtActionPerformed
      
        String searchText = searchField.getText().trim();
        ArrayList<PhieuKhamBenh> phieuKhamBenhs = new ArrayList<>();       

        boolean found = false;        

        if (!searchText.isEmpty()) {
            phieuKhamBenhs = phieuKhamBenhService.getPhieuKhamBenhByTenBN(searchText);
            defaultTableModel.setRowCount(0);
            setTableData(phieuKhamBenhs);

      
            if (!phieuKhamBenhs.isEmpty()) {
                found = true;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu khám!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
     
    }

    private void PKBTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PKBTableMouseClicked

        if (evt.getButton() == MouseEvent.BUTTON3) {

        int rowIndex = PKBTable.rowAtPoint(evt.getPoint());
 
        if (rowIndex >= 0) {
       
            PKBTable.setRowSelectionInterval(rowIndex, rowIndex);
   
            jPopupMenu1.show(PKBTable, evt.getX(), evt.getY());
        }
    }
    }

    private void chiTietPKBActionPerformed(java.awt.event.ActionEvent evt) {
   
        
        int row = PKBTable.getSelectedRow();
        int id = Integer.parseInt(String.valueOf(PKBTable.getValueAt(row, 0)));

        new ChiTietPkbGUI(id).setVisible(true);
    }

    private void PKBPrintActionPerformed(java.awt.event.ActionEvent evt) {
     
        int row = PKBTable.getSelectedRow();
        int id = Integer.parseInt(String.valueOf(PKBTable.getValueAt(row, 0)));
        PhieuKhamBenh phieuKhamBenh = phieuKhamBenhService.getPhieuKhamBenhByID(id);
        try {
            new PhieuKhamBenhPrintGUI(phieuKhamBenh).setVisible(true);
        } catch (PrinterException ex) {
            Logger.getLogger(QuanLyThongTinKhamBenh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void xuatPKBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xuatPKBActionPerformed
    
        int row = PKBTable.getSelectedRow();
        int id = Integer.parseInt(String.valueOf(PKBTable.getValueAt(row, 0)));
        PhieuKhamBenh phieuKhamBenh = phieuKhamBenhService.getPhieuKhamBenhByID(id);
        try {
            new XuatPhieuKhamBenh(phieuKhamBenh).setVisible(true);
        } catch (PrinterException ex) {
            Logger.getLogger(QuanLyThongTinKhamBenh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    private javax.swing.JMenuItem PKBPrint;
    private javax.swing.JTable PKBTable;
    private javax.swing.JLabel TitleLabel;
    private javax.swing.JMenuItem chiTietPKB;
    private javax.swing.JButton exitBt;
    private javax.swing.JButton findBt;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton removeBt;
    private javax.swing.JTextField searchField;
    private javax.swing.JButton updateBt;
    private javax.swing.JMenuItem xuatPKB;

    
    public static void main(String args[]) {

        try {
        	for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyThongTinKhamBenh().setVisible(true);
            }
        });

    }
    

}
