package Report;

import BUS.DSBienLaiBUS;
import java.awt.BorderLayout;
import javax.swing.*;

public class ThongKeDoanhThuJPanel extends JPanel {
    private QuanLyThongKeController controller;
    private DSBienLaiBUS dsbl;

    public ThongKeDoanhThuJPanel(DSBienLaiBUS dsbl) {
        this.dsbl = dsbl;
        initComponents();
        ThongKeServiceImplDoanhThu thongKeService = new ThongKeServiceImplDoanhThu(dsbl.getDoanhThuGroupByYear());
        controller = new QuanLyThongKeController(thongKeService);
        controller.setDataDoanhThuToColChart(jpnView1);
    }

    private void initComponents() {
        jpnView1 = new JPanel();
        jpnView1.setLayout(new BorderLayout());

        setLayout(new BorderLayout());
        add(jpnView1, BorderLayout.CENTER);
    }

    private JPanel jpnView1;
}
