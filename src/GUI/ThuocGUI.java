/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DTO.Thuoc;
import BUS.ThuocService;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ThuocGUI extends javax.swing.JFrame {
    ThuocService thuocService;
    DefaultTableModel defaultTableModel;
    
    
    private void setTableData(ArrayList<Thuoc> thuocs){
        for (Thuoc thuoc : thuocs){
            defaultTableModel.addRow(new Object[]{thuoc.getId(), thuoc.getTenThuoc(), thuoc.getNuocSX(), thuoc.getDonGia(),thuoc.getHsd(),thuoc.getSoLuongTon(), thuoc.getTrangThai()});
        }
    }
    public ThuocGUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        defaultTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }           
        };
        
        ThuocTable.setModel(defaultTableModel);
        
        thuocService = new ThuocService();
        
        String[] columns = {"ID", "Tên Thuốc", "Nước sản xuất", "Đơn Giá", "Hạn sử dụng", "Số lượng tồn", "Trạng thái"};
        defaultTableModel.setColumnIdentifiers(columns);
        
        ArrayList<Thuoc> thuocs = thuocService.getAllThuocs();
        setTableData(thuocs);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        addBt = new javax.swing.JButton();
        removeBt = new javax.swing.JButton();
        updateBt = new javax.swing.JButton();
        exitBt = new javax.swing.JButton();
        TitleLabel = new javax.swing.JLabel();
        searchBt = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ThuocTable = new javax.swing.JTable();
        trangChuButton = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        refreshButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        backButton.setText("Quay Lại");

        addBt.setText("Thêm");
        addBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtActionPerformed(evt);
            }
        });

        removeBt.setText("Xoá");
        removeBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtActionPerformed(evt);
            }
        });

        updateBt.setText("Sửa");
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

        TitleLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        TitleLabel.setForeground(new java.awt.Color(0, 51, 204));
        TitleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconThuoc.png"))); // NOI18N
        TitleLabel.setText(" QUẢN LÝ TỒN KHO THUỐC");
        TitleLabel.setToolTipText("");

        searchBt.setText("Tìm Kiếm");
        searchBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtActionPerformed(evt);
            }
        });

        ThuocTable.setModel(new javax.swing.table.DefaultTableModel(
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
        ThuocTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        ThuocTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(ThuocTable);

        trangChuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconTrangchu.png"))); // NOI18N
        trangChuButton.setText("Trang Chủ");
        trangChuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trangChuButtonActionPerformed(evt);
            }
        });

        searchField.setFont(new java.awt.Font("Helvetica Neue", 2, 13)); // NOI18N
        searchField.setForeground(new java.awt.Color(204, 204, 204));
        searchField.setText("Nhập tên thuốc");
        searchField.setToolTipText("Nhập chính xác tên thuốc");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addBt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(removeBt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(updateBt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(exitBt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchField)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(trangChuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TitleLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchBt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TitleLabel)
                    .addComponent(trangChuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchBt, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBt)
                    .addComponent(removeBt)
                    .addComponent(updateBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exitBt))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtActionPerformed
        // TODO add your handling code here:
        new ThemThuoc().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_addBtActionPerformed

    private void removeBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtActionPerformed
        // TODO add your handling code here:
        int row = ThuocTable.getSelectedRow();
        
        if (row == -1){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dữ liệu cần xoá", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        else {
            int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa dòng đã chọn?");
            
            if (luaChon == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(String.valueOf(ThuocTable.getValueAt(row, 0)));
                
                
//                PhieuKhamBenh phieuKhamBenh = phieuKhamBenhService.getPKBbyID(id);
                
                thuocService.removeThuoc(id);
            }
        }
        
        defaultTableModel.setRowCount(0);
        setTableData(thuocService.getAllThuocs());  
    }//GEN-LAST:event_removeBtActionPerformed

    private void updateBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtActionPerformed
        // TODO add your handling code here:
        int row = ThuocTable.getSelectedRow();
        
        if (row == -1){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dữ liệu cần sửa", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        else {     
            int id = Integer.parseInt(String.valueOf(ThuocTable.getValueAt(row, 0)));

            new SuaThuoc(id).setVisible(true);
            this.dispose();           
        }  
        
    }//GEN-LAST:event_updateBtActionPerformed

    private void exitBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitBtActionPerformed

    private void searchBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtActionPerformed
        // TODO add your handling code here:

        String searchText = searchField.getText().trim();
        ArrayList<Thuoc> thuocs = new ArrayList<>();       

        boolean found = false;        

        if (!searchText.isEmpty()) {
            thuocs = thuocService.getThuocsByTenThuocs(searchText);
            defaultTableModel.setRowCount(0);
            setTableData(thuocs);

            // Check if any records were foundtr
            if (!thuocs.isEmpty()) {
                found = true;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy loại thuốc!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_searchBtActionPerformed

    private void trangChuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trangChuButtonActionPerformed
        // TODO add your handling code here:
        //        new TrangChu().setVisible(true);

    }//GEN-LAST:event_trangChuButtonActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
        defaultTableModel.setRowCount(0);
        ArrayList<Thuoc> thuocs = thuocService.getAllThuocs();
        setTableData(thuocs);
    }//GEN-LAST:event_refreshButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ThuocGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThuocGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThuocGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThuocGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThuocGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ThuocTable;
    private javax.swing.JLabel TitleLabel;
    private javax.swing.JButton addBt;
    private javax.swing.JButton backButton;
    private javax.swing.JButton exitBt;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton removeBt;
    private javax.swing.JButton searchBt;
    private javax.swing.JTextField searchField;
    private javax.swing.JButton trangChuButton;
    private javax.swing.JButton updateBt;
    // End of variables declaration//GEN-END:variables
}