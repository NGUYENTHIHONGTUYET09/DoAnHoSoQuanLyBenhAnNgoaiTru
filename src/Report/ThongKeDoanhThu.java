package Report;

import BUS.DSBienLaiBUS;
import java.awt.BorderLayout;
import javax.swing.*;

public class ThongKeDoanhThu extends JFrame {
    private DSBienLaiBUS dsbl;

    public ThongKeDoanhThu(DSBienLaiBUS dsbl) {
        this.dsbl = dsbl;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        ThongKeDoanhThuJPanel thongkeJpanel = new ThongKeDoanhThuJPanel(dsbl);
       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
       
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(thongkeJpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(thongkeJpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        
         
    

        pack();
    }
}
      