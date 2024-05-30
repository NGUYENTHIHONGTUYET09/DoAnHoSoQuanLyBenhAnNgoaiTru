
package Report;

import BUS.DanhSachBNBUS;
import GUI.QuanLyThongTinBenhNhan;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;

public class ThongKeBenhNhanJPanel extends javax.swing.JPanel {

 
   private QuanLyThongKeController controller;
   private DanhSachBNBUS dsbn;
    public ThongKeBenhNhanJPanel(DanhSachBNBUS dsbn) {
    	this.dsbn = dsbn;
        initComponents();
        ThongKeServiceImpl thongKeService = new ThongKeServiceImpl(dsbn.getDSGroupedByQueQuan());
        controller = new QuanLyThongKeController(thongKeService);
        controller.setDataToColChart(jpnView1);
        controller.setDataToPieChart(jpnView2);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jpnRoot = new javax.swing.JPanel();
        jpnView1 = new javax.swing.JPanel();
        jpnView2 = new javax.swing.JPanel();

        javax.swing.GroupLayout jpnView1Layout = new javax.swing.GroupLayout(jpnView1);
        jpnView1Layout.setHorizontalGroup(
        	jpnView1Layout.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 420, Short.MAX_VALUE)
        );
        jpnView1Layout.setVerticalGroup(
        	jpnView1Layout.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 167, Short.MAX_VALUE)
        );
        jpnView1.setLayout(jpnView1Layout);

        javax.swing.GroupLayout jpnView2Layout = new javax.swing.GroupLayout(jpnView2);
        jpnView2.setLayout(jpnView2Layout);
        jpnView2Layout.setHorizontalGroup(
            jpnView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 643, Short.MAX_VALUE)
        );
        jpnView2Layout.setVerticalGroup(
            jpnView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 168, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnRootLayout = new javax.swing.GroupLayout(jpnRoot);
        jpnRoot.setLayout(jpnRootLayout);
        jpnRootLayout.setHorizontalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnRootLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpnView2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnView1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnRootLayout.setVerticalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRootLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnView1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnView2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnRoot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>                        


                    
    private javax.swing.JPanel jpnRoot;
    private javax.swing.JPanel jpnView1;
    private javax.swing.JPanel jpnView2;
}