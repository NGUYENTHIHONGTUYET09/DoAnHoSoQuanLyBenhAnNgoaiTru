
package GUI;



import DTO.Thuoc;
import BUS.ThuocService;
import java.awt.Font;

//import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.text.SimpleDateFormat;


public class ThemThuoc extends javax.swing.JFrame {
    
    Thuoc thuoc;
    ThuocService thuocService;

    
    public ThemThuoc() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        thuocService = new ThuocService();
        thuoc = new Thuoc();
    }

   
    @SuppressWarnings("unchecked")

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        addPKBBt = new javax.swing.JButton();
        thuocID = new javax.swing.JLabel();
        backPKBBt = new javax.swing.JButton();
        tenThuocLb = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        NSXLb = new javax.swing.JLabel();
        donGiaLb = new javax.swing.JLabel();
        HSDLb = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        soLuongLb = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setText("THÊM MỚI THUỐC");
           jLabel1.setFont(new Font("Arial", Font.BOLD, 15));


        addPKBBt.setText("Thêm");
        addPKBBt.setToolTipText("");
        addPKBBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPKBBtActionPerformed(evt);
            }
        });

        thuocID.setText("ID");

        backPKBBt.setText("Quay Lại");
        backPKBBt.setToolTipText("");
        backPKBBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backPKBBtActionPerformed(evt);
            }
        });

        tenThuocLb.setText("Tên thuốc");

        NSXLb.setText("Nước sản xuất");

        donGiaLb.setText("Đơn giá");

        HSDLb.setText("HSD");

        soLuongLb.setText("Số lượng");

        jTextField1.setEditable(false);
        jTextField1.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addPKBBt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(backPKBBt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tenThuocLb)
                            .addComponent(thuocID)
                            .addComponent(NSXLb)
                            .addComponent(soLuongLb)
                            .addComponent(donGiaLb)
                            .addComponent(HSDLb))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4)
                            .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(158, 158, 158))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thuocID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenThuocLb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NSXLb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(donGiaLb))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(HSDLb))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soLuongLb))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addPKBBt)
                    .addComponent(backPKBBt))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }

    private void addPKBBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPKBBtActionPerformed
        try {
            
            thuoc.setTenThuoc(String.valueOf(jTextField2.getText()));
            thuoc.setNuocSX(String.valueOf(jTextField3.getText()));
            thuoc.setDonGia(Double.parseDouble(jTextField4.getText()));

            Date ngayTao = jDateChooser1.getDate();
            java.sql.Date sqlDate = new java.sql.Date(ngayTao.getTime());
            thuoc.setHsd(sqlDate);

            thuoc.setSoLuongTon(Integer.parseInt(jTextField5.getText()));

            thuocService.addThuoc(thuoc);

       
            new ThuocGUI().setVisible(true);
            this.dispose();
        } catch (NumberFormatException ex) {
         

        }
    }

    private void backPKBBtActionPerformed(java.awt.event.ActionEvent evt) {
       
        new ThuocGUI().setVisible(true);
        this.dispose();
    }
  
    public static void main(String args[]) {
     
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThemThuoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemThuoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemThuoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemThuoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemThuoc().setVisible(true);
            }
        });
    }


    private javax.swing.JLabel HSDLb;
    private javax.swing.JLabel NSXLb;
    private javax.swing.JButton addPKBBt;
    private javax.swing.JButton backPKBBt;
    private javax.swing.JLabel donGiaLb;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JLabel soLuongLb;
    private javax.swing.JLabel tenThuocLb;
    private javax.swing.JLabel thuocID;
    // End of variables declaration//GEN-END:variables
}