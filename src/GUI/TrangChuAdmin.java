package GUI;

import BUS.DSBienLaiBUS;
import BUS.DanhSachBNBUS;
import Report.ThongKeBenhNhan;
import Report.ThongKeDoanhThu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class TrangChuAdmin extends JFrame implements ActionListener {
    private JButton jbutton_qlytaikhoan;
    private JButton jbutton_qlynhanvien;
    private JLabel label_header;
    private DSBienLaiBUS dsbl;
    private DanhSachBNBUS dsbn;

    public TrangChuAdmin() {
        this.dsbl = new DSBienLaiBUS();
        dsbn = new DanhSachBNBUS(null);
        init();
        setVisible(true);
    }

    public void init() {
        this.setTitle("Trang chủ Admin");
        this.setSize(700, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10, 10));

        // Header Panel
        JPanel jpanel_header = new JPanel(new BorderLayout());
        jpanel_header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        label_header = new JLabel("Trang chủ Admin", SwingConstants.CENTER);
        label_header.setFont(new Font("Arial", Font.BOLD, 24));
        label_header.setForeground(Color.BLUE);
        jpanel_header.add(label_header, BorderLayout.CENTER);

        Font font = new Font("Arial", Font.BOLD, 10);
        JButton jbutton_taiKhoan = new JButton("Danh sách tài khoản");
        ImageIcon iconTK = new ImageIcon(getClass().getResource("/ICon/iconAccount.png"));
        jbutton_taiKhoan.setIcon(iconTK);
        jbutton_taiKhoan.setFont(font);
        jbutton_taiKhoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DanhSachTaiKhoan qltttk = new DanhSachTaiKhoan();
                qltttk.setVisible(true);
            }
        });

        JButton jbutton_nhanvien = new JButton("Danh sách nhân viên");
        ImageIcon iconNV = new ImageIcon(getClass().getResource("/ICon/iconNhanVien.png"));
        jbutton_nhanvien.setIcon(iconNV);
        jbutton_nhanvien.setFont(font);
        jbutton_nhanvien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QLyNhanVienGUI qlttnv = new QLyNhanVienGUI();
                qlttnv.setVisible(true);
                dispose();
            }
        });

        JButton jbutton_thongkequequan = new JButton(
                "<html><center>" + "Thống kê " + "<br>" + "quê quán" + "</center></html>");
        ImageIcon iconthongke = new ImageIcon(getClass().getResource("/ICon/iconChart.png"));
        jbutton_thongkequequan.setIcon(iconthongke);
        jbutton_thongkequequan.setFont(font);
        jbutton_thongkequequan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // chuyen tkdt = new ThongKeDoanhThu(dsbl); len tren và bỏ dispose()
                    ThongKeBenhNhan tkbn = new ThongKeBenhNhan(dsbn);
                    tkbn.setVisible(true);
                } catch (Exception ex) {
                    // TODO: handle exception
                    ex.printStackTrace();
                }
            }
        });

        JButton jbutton_thongkedoanhthu = new JButton(
                "<html><center>" + "Thống kê " + "<br>" + "doanh thu năm" + "</center></html>");
        ImageIcon iconthongke1 = new ImageIcon(getClass().getResource("/ICon/iconChart1.png"));
        jbutton_thongkedoanhthu.setIcon(iconthongke1);
        jbutton_thongkedoanhthu.setFont(font);
        jbutton_thongkedoanhthu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // chuyen tkdt = new ThongKeDoanhThu(dsbl); len tren và bỏ dispose()
                    ThongKeDoanhThu tkdt = new ThongKeDoanhThu(dsbl);
                    tkdt.setVisible(true);
                } catch (Exception ex) {
                    // TODO: handle exception
                    ex.printStackTrace();
                }
            }
        });

        JButton jbutton_thongkedoanhthuthang = new JButton(
                "<html><center>" + "Thống kê " + "<br>" + "doanh thu tháng" + "</center></html>");
        jbutton_thongkedoanhthuthang.setIcon(iconthongke1);
        jbutton_thongkedoanhthuthang.setFont(font);
        jbutton_thongkedoanhthuthang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ThongKeDoanhThuThangGUI tkdtThang = new ThongKeDoanhThuThangGUI();
                    tkdtThang.setVisible(true);
                } catch (Exception ex) {
                    // TODO: handle exception
                    ex.printStackTrace();
                }
            }
        });

        JPanel jpanel_button = new JPanel(new GridLayout(3, 2, 10, 10));
        jpanel_button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add buttons with images to the panel
        jpanel_button.add(jbutton_taiKhoan);
        jpanel_button.add(jbutton_nhanvien);
        jpanel_button.add(jbutton_thongkequequan);
        jpanel_button.add(jbutton_thongkedoanhthu);
        jpanel_button.add(jbutton_thongkedoanhthuthang);

        // Create and configure the Home button
        JButton jbutton_trangChu = new JButton("Đăng xuất");
        jbutton_trangChu.setFont(new Font("Arial", Font.BOLD, 20));
        jbutton_trangChu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TrangChuDangNhapGUI  tcdn = new TrangChuDangNhapGUI();
                tcdn.setVisible(true);
                dispose();
            }
        });

        // Add the Home button to the button panel
        JPanel jpanel_button2 = new JPanel(new BorderLayout());
        jpanel_button2.add(jbutton_trangChu, BorderLayout.SOUTH);

        // Add components to frame
        add(jpanel_header, BorderLayout.NORTH);
        add(jpanel_button, BorderLayout.CENTER);
        add(jpanel_button2, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implement action listener if needed
    }

  
}
