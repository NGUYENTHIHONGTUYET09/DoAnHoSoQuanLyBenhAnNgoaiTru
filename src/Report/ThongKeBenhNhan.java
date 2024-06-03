
package Report;


import java.util.List;

import BUS.DanhSachBNBUS;
import DTO.BenhNhan;

public class ThongKeBenhNhan extends javax.swing.JFrame {
	
	private DanhSachBNBUS dsbn;
    
	
    public ThongKeBenhNhan(DanhSachBNBUS dsbn) {
    	this.dsbn = dsbn;
   
        initComponents();
    }

    @SuppressWarnings("unchecked")
                       
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

      
        ThongKeBenhNhanJPanel thongkeJpanel1 = new ThongKeBenhNhanJPanel(dsbn);
       

       
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
       
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(thongkeJpanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(thongkeJpanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        
         
    

        pack();
    }

            
}