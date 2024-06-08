
package GUI;



import BUS.ThuocService;
import DTO.Thuoc;
import java.awt.Font;
import java.util.Date;
import javax.swing.JOptionPane;


public class SuaThuoc extends javax.swing.JFrame {
    private ThuocService thuocService;
    private Thuoc thuoc;
  
    public SuaThuoc(int id) {
        thuocService = new ThuocService();       
        thuoc = thuocService.getThuocByID(id);
        
        initComponents();
        this.setLocationRelativeTo(null);
            
        jTextField1.setText(String.valueOf(thuoc.getId()));
        jTextField2.setText(String.valueOf(thuoc.getTenThuoc()));
        jTextField3.setText(String.valueOf(thuoc.getNuocSX()));
        jTextField4.setText(String.valueOf(thuoc.getDonGia()));
        jDateChooser1.setDate(thuoc.getHsd());
        jTextField5.setText(String.valueOf(thuoc.getSoLuongTon()));
    }

 
    @SuppressWarnings("unchecked")

    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        hsdLb = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        sltLb = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        maThuocLb = new javax.swing.JLabel();
        updatePKBBt = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        backPKBBt = new javax.swing.JButton();
        tenThuocLb = new javax.swing.JLabel();
        donGiaLb = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        nsxLb = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        hsdLb.setText("HSD");

        sltLb.setText("Số lượng tồn");

        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setText("SỬA THÔNG TIN THUỐC");
           jLabel1.setFont(new Font("Arial", Font.BOLD, 15));

        maThuocLb.setText("ID");
      

        updatePKBBt.setText("Lưu");
        updatePKBBt.setToolTipText("");
        updatePKBBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePKBBtActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setEnabled(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        backPKBBt.setText("Quay Lại");
        backPKBBt.setToolTipText("");
        backPKBBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backPKBBtActionPerformed(evt);
            }
        });

        tenThuocLb.setText("Tên thuốc");

        donGiaLb.setText("Đơn giá");

        nsxLb.setText("Nước sản xuất");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(updatePKBBt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(backPKBBt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(hsdLb))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(sltLb)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField5)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tenThuocLb)
                            .addComponent(maThuocLb)
                            .addComponent(nsxLb)
                            .addComponent(donGiaLb))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField1)
                                .addComponent(jTextField3)
                                .addComponent(jTextField4)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maThuocLb)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenThuocLb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nsxLb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(donGiaLb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hsdLb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sltLb)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updatePKBBt)
                    .addComponent(backPKBBt))
                .addGap(29, 29, 29))
        );

        pack();
    }

    private void updatePKBBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePKBBtActionPerformed
        try {

            thuoc.setTenThuoc(String.valueOf(jTextField2.getText()));
            thuoc.setNuocSX(String.valueOf(jTextField3.getText()));
            thuoc.setDonGia(Double.parseDouble(jTextField4.getText()));

            Date hsd = jDateChooser1.getDate();
            java.sql.Date sqlDate = new java.sql.Date(hsd.getTime());
            thuoc.setHsd(sqlDate);

            thuoc.setSoLuongTon(Integer.parseInt(jTextField5.getText()));

            thuocService.suaThuoc(thuoc);
          // thuocService.suaThuoc1(thuoc);
            

         
            new ThuocGUI().setVisible(true);
            this.dispose();
            JOptionPane.showMessageDialog(this, "Sửa thông tin thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
          
        }
    }
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
    
    }
    private void backPKBBtActionPerformed(java.awt.event.ActionEvent evt) {
    
        new ThuocGUI().setVisible(true);
        this.dispose();
    }

 
    private javax.swing.JButton backPKBBt;
    private javax.swing.JLabel donGiaLb;
    private javax.swing.JLabel hsdLb;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JLabel maThuocLb;
    private javax.swing.JLabel nsxLb;
    private javax.swing.JLabel sltLb;
    private javax.swing.JLabel tenThuocLb;
    private javax.swing.JButton updatePKBBt;

}