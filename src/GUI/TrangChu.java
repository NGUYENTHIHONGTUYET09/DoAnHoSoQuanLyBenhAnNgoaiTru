package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class TrangChu extends JFrame {

//    private QuanLyThongTinBenhNhan qlttbn;

    public TrangChu() {
        init();
    }

    public void init() {
        setTitle("Trang Chủ");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo một JPanel để chứa hình ảnh và lớp nền bóng
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Vẽ hình ảnh
                ImageIcon icon_1 = new ImageIcon(getClass().getResource("/ICon/iconBenhVien.png"));
                Image img = icon_1.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                // Vẽ lớp nền bóng
                g.setColor(new Color(255, 255, 255, 50)); // Màu trắng với độ trong suốt 100
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        imagePanel.setLayout(new BorderLayout());

        URL urlIconNotepad = BenhNhanGUI.class.getResource("/ICon/iconTrangChuNV.png");
        Image img = Toolkit.getDefaultToolkit().createImage(urlIconNotepad);
        this.setIconImage(img);
        // JLabel "Trang Chủ"
        JLabel jLabel_trangchu = new JLabel("Trang Chủ", SwingConstants.CENTER);
        jLabel_trangchu.setForeground(Color.WHITE);
        jLabel_trangchu.setFont(new Font("Arial", Font.BOLD, 24));
        imagePanel.add(jLabel_trangchu, BorderLayout.NORTH);

        // Tạo nút và đặt vị trí
        JButton jButton_xemthongtin = new JButton("Xem thông tin bệnh nhân");
        jButton_xemthongtin.setBackground(new Color(200, 200, 200, 10)); // Màu xám nhạt với độ trong suốt 150
        jButton_xemthongtin.setFont(new Font("Arial", Font.BOLD, 10));
        jButton_xemthongtin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToAddPatientGUI();
            }
        });

        JButton jButton_dangky = new JButton("Thêm phiếu khám bệnh");
        jButton_dangky.setBackground(new Color(200, 200, 200, 10)); // Màu xám nhạt với độ trong suốt 150
        jButton_dangky.setFont(new Font("Arial", Font.BOLD, 10));
        jButton_dangky.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToThemPKBUGI();
            }
        });
        
        JButton jButton_dangxuat = new JButton("Đăng xuất");
        jButton_dangxuat.setBackground(new Color(200, 200, 200, 10)); // Màu xám nhạt với độ trong suốt 150
        jButton_dangxuat.setFont(new Font("Arial", Font.BOLD, 10));
        jButton_dangxuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             TrangChuDangNhapGUI tcdng = new TrangChuDangNhapGUI();
             tcdng.setVisible(true);
             dispose();
            }
        });


       
        // Thiết lập kích thước mong muốn cho JButton
        Dimension buttonSize = new Dimension(200, 50);
        jButton_xemthongtin.setPreferredSize(buttonSize);
        jButton_dangky.setPreferredSize(buttonSize);
        jButton_dangxuat.setPreferredSize(buttonSize);
        
        JPanel jPanel_south = new JPanel();
        jPanel_south.setOpaque(false); // Đặt nền trong suốt
        jPanel_south.setBorder(new EmptyBorder(0, 0, 20, 0)); // Đặt khoảng cách trên là 10
        jPanel_south.add(jButton_xemthongtin);
        jPanel_south.add(jButton_dangky);
        jPanel_south.add(jButton_dangxuat);



        // Đặt JPanel chứa hình ảnh và lớp nền bóng lên frame
        add(imagePanel);

        // Đặt JPanel chứa nút lên JPanel chứa hình ảnh và lớp nền bóng
        imagePanel.add(jPanel_south, BorderLayout.SOUTH);
    }

    public void switchToAddPatientGUI() {
        QuanLyThongTinBenhNhan qlttbn = new QuanLyThongTinBenhNhan();
        qlttbn.setVisible(true);
        setVisible(false);
    }

    public void switchToThemPKBUGI() {
      ThemPKB thempkb = new ThemPKB();

        thempkb.setVisible(true);
        dispose();
    }

// 
//    public static void main(String args[]) {
//
//        try {// giao diện
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            TrangChu dn = new TrangChu();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//    }
}
