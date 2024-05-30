package GUI;

import BUS.DSBienLaiBUS;
import BUS.DanhSachBNBUS;
import Report.ThongKeBenhNhan;
import Report.ThongKeDoanhThu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TrangChuAdmin extends JFrame implements ActionListener {
    private JButton jbutton_qlytaikhoan;
    private JButton jbutton_qlynhanvien;
    private JLabel label_header;
    private DSBienLaiBUS dsbl;
    private QLyBienLaiGUI qlttbl;
    private DanhSachBNBUS dsbn;
     private QuanLyThongTinBenhNhan qlttbn;

    public TrangChuAdmin() {
        qlttbl = new QLyBienLaiGUI();
        qlttbl.setVisible(false);
        this.dsbl = new DSBienLaiBUS(qlttbl);
        qlttbn = new QuanLyThongTinBenhNhan();
        qlttbn.setVisible(false);
        dsbn = new DanhSachBNBUS(qlttbn);
        
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

        Font font = new Font("Arial", Font.BOLD, 20);
        JButton jbutton_taiKhoan = new JButton("Danh sách tài khoản");
        ImageIcon iconTK = new ImageIcon(getClass().getResource("/ICon/iconAccount.png"));
        jbutton_taiKhoan.setIcon(iconTK);
        jbutton_taiKhoan.setFont(font);
        jbutton_taiKhoan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DanhSachTaiKhoan qltttk = new DanhSachTaiKhoan();
				qltttk.setVisible(true);
				dispose();
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
        
        JButton jbutton_thongkequequan = new JButton("Thông kê quê quán");
        ImageIcon iconthongke = new ImageIcon(getClass().getResource("/ICon/iconChart.png"));
        jbutton_thongkequequan.setIcon(iconthongke);
        jbutton_thongkequequan.setFont(font);
         jbutton_thongkequequan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ThongKeBenhNhan(dsbn).setVisible(true);
				dispose();
			}
		});		
        
        
        JButton jbutton_thongkedoanhthu = new JButton("Thông kê doanh thu");
        ImageIcon iconthongke1 = new ImageIcon(getClass().getResource("/ICon/iconChart1.png"));
        jbutton_thongkedoanhthu.setIcon(iconthongke1);
        jbutton_thongkedoanhthu.setFont(font);
            jbutton_thongkedoanhthu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ThongKeDoanhThu(dsbl).setVisible(true);
                } catch (Exception ex) {
                    // TODO: handle exception
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        JPanel jpanel_button = new JPanel(new GridLayout(2, 2, 10, 10));
        jpanel_button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add buttons with images to the panel
        jpanel_button.add(jbutton_taiKhoan);
        jpanel_button.add(jbutton_nhanvien);
        jpanel_button.add(jbutton_thongkequequan);
        jpanel_button.add(jbutton_thongkedoanhthu);

        // Add components to frame
        add(jpanel_header, BorderLayout.NORTH);
        add(jpanel_button, BorderLayout.CENTER);
    }



    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new TrangChuAdmin();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implement action listener if needed
    }
}
