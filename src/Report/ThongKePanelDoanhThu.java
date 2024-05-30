
package Report;

import BUS.DSBienLaiBUS;
import GUI.QLyBienLaiGUI;


public class ThongKePanelDoanhThu extends javax.swing.JPanel {
    
    private QuanLyThongKeController controller;
    private DSBienLaiBUS dsbl;


    public ThongKePanelDoanhThu(DSBienLaiBUS dsbl) {
            this.dsbl = dsbl;
        initComponents();
         QLyBienLaiGUI qlttbl = new QLyBienLaiGUI();
        DSBienLaiBUS danhSachBLBUS = new DSBienLaiBUS(qlttbl);
        ThongKeServiceImplDoanhThu thongKeService = new ThongKeServiceImplDoanhThu(danhSachBLBUS.getDoanhThuGroupByYear());
        controller = new QuanLyThongKeController(thongKeService);
        controller.setDataDoanhThuToColChart(jpnView1);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jpnRoot = new javax.swing.JPanel();
        jpnView1 = new javax.swing.JPanel();

        javax.swing.GroupLayout jpnView1Layout = new javax.swing.GroupLayout(jpnView1);
        jpnView1.setLayout(jpnView1Layout);
        jpnView1Layout.setHorizontalGroup(
            jpnView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 617, Short.MAX_VALUE)
        );
        jpnView1Layout.setVerticalGroup(
            jpnView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnRootLayout = new javax.swing.GroupLayout(jpnRoot);
        jpnRoot.setLayout(jpnRootLayout);
        jpnRootLayout.setHorizontalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRootLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jpnView1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jpnRootLayout.setVerticalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRootLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jpnView1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnRoot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private javax.swing.JPanel jpnRoot;
    private javax.swing.JPanel jpnView1;
    // End of variables declaration                   
}
