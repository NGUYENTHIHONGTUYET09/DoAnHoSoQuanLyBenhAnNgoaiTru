package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class TrangChuDangNhapGUI extends JFrame {

    public TrangChuDangNhapGUI() {
        init();
        this.setVisible(true);
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
                ImageIcon icon_1 = new ImageIcon(getClass().getResource("/ICon/iconBenhVien.png"));
                Image img = icon_1.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                g.setColor(new Color(255, 255, 255, 50));
                g.fillRect(0, 0, getWidth(), getHeight());
            }

        };

        URL urlIconNotepad = BenhNhanGUI.class.getResource("/ICon/iconDangNhap.png");
        Image img = Toolkit.getDefaultToolkit().createImage(urlIconNotepad);
        this.setIconImage(img);
        imagePanel.setLayout(new BorderLayout());

        JLabel jLabel_trangchu = new JLabel("Login", SwingConstants.CENTER);
        jLabel_trangchu.setForeground(Color.WHITE);
        jLabel_trangchu.setFont(new Font("Arial", Font.BOLD, 24));
        imagePanel.add(jLabel_trangchu, BorderLayout.NORTH);

        JButton jButton_xemthongtin = new JButton("Đăng nhập");
        jButton_xemthongtin.setBackground(new Color(200, 200, 200, 10));
        jButton_xemthongtin.setFont(new Font("Arial", Font.BOLD, 20));
        jButton_xemthongtin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToDangNhapGUI();
            }
        });

        JPanel jPanel_south = new JPanel();
        jPanel_south.setOpaque(false);
        jPanel_south.setBorder(new EmptyBorder(0, 0, 20, 0));
        jPanel_south.add(jButton_xemthongtin);

        add(imagePanel);

        imagePanel.add(jPanel_south, BorderLayout.SOUTH);
    }

    public void switchToDangNhapGUI() {
        DangNhapGUI dng = new DangNhapGUI(this);
        dng.setVisible(true);
        dispose();
    }

    public void switchToListRegisterGUI() {
        DanhSachTaiKhoan qlttdk = new DanhSachTaiKhoan();

        qlttdk.setVisible(true);
    }

    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            TrangChuDangNhapGUI dn = new TrangChuDangNhapGUI();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
