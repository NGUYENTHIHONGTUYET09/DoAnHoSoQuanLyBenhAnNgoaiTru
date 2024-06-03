package Report;

import BUS.DSBienLaiBUS;
import GUI.QLyBienLaiGUI;
import GUI.TrangChuAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThongKePanelDoanhThu extends JPanel {
    
    private QuanLyThongKeController controller;
    private DSBienLaiBUS dsbl;

    private JButton closeButton;  // Thêm nút đóng

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
    private void initComponents() {
        jpnRoot = new JPanel(new BorderLayout());
        jpnView1 = new JPanel();
        closeButton = new JButton("X");  // Khởi tạo nút đóng

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý sự kiện đóng
                System.out.println("Close button clicked");
                // Đóng panel hoặc thực hiện hành động khác
             ((JFrame) SwingUtilities.getWindowAncestor(ThongKePanelDoanhThu.this)).dispose();
              
            }
        });

        // Tạo một panel cho nút đóng
        JPanel closePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        closePanel.add(closeButton);

        jpnRoot.add(closePanel, BorderLayout.NORTH);
        jpnRoot.add(jpnView1, BorderLayout.CENTER);

        GroupLayout jpnView1Layout = new GroupLayout(jpnView1);
        jpnView1.setLayout(jpnView1Layout);
        jpnView1Layout.setHorizontalGroup(
            jpnView1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 617, Short.MAX_VALUE)
        );
        jpnView1Layout.setVerticalGroup(
            jpnView1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jpnRoot, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnRoot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );
    }

    // Variables declaration - do not modify                     
    private JPanel jpnRoot;
    private JPanel jpnView1;
    // End of variables declaration                   
}
