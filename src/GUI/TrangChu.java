package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class TrangChu extends JFrame {



    public TrangChu() {
        init();
    }

    public void init() {
        setTitle("Trang Chủ");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
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
        imagePanel.setLayout(new BorderLayout());

        URL urlIconNotepad = BenhNhanGUI.class.getResource("/ICon/iconTrangChuNV.png");
        Image img = Toolkit.getDefaultToolkit().createImage(urlIconNotepad);
        this.setIconImage(img);

        JLabel jLabel_trangchu = new JLabel("Trang Chủ", SwingConstants.CENTER);
        jLabel_trangchu.setForeground(Color.WHITE);
        jLabel_trangchu.setFont(new Font("Arial", Font.BOLD, 24));
        imagePanel.add(jLabel_trangchu, BorderLayout.NORTH);


        JButton jButton_xemthongtin = new JButton("Xem thông tin bệnh nhân");
        jButton_xemthongtin.setBackground(new Color(200, 200, 200, 10)); 
        jButton_xemthongtin.setFont(new Font("Arial", Font.BOLD, 10));
        jButton_xemthongtin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToAddPatientGUI();
            }
        });

        JButton jButton_dangky = new JButton("Thêm phiếu khám bệnh");
        jButton_dangky.setBackground(new Color(200, 200, 200, 10));
        jButton_dangky.setFont(new Font("Arial", Font.BOLD, 10));
        jButton_dangky.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToThemPKBUGI();
            }
        });
        
        JButton jButton_dangxuat = new JButton("Đăng xuất");
        jButton_dangxuat.setBackground(new Color(200, 200, 200, 10));
        jButton_dangxuat.setFont(new Font("Arial", Font.BOLD, 10));
        jButton_dangxuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             TrangChuDangNhapGUI tcdng = new TrangChuDangNhapGUI();
             tcdng.setVisible(true);
             dispose();
            }
        });


       
      
        Dimension buttonSize = new Dimension(200, 50);
        jButton_xemthongtin.setPreferredSize(buttonSize);
        jButton_dangky.setPreferredSize(buttonSize);
        jButton_dangxuat.setPreferredSize(buttonSize);
        
        JPanel jPanel_south = new JPanel();
        jPanel_south.setOpaque(false);
        jPanel_south.setBorder(new EmptyBorder(0, 0, 20, 0)); 
        jPanel_south.add(jButton_xemthongtin);
        jPanel_south.add(jButton_dangky);
        jPanel_south.add(jButton_dangxuat);



        
        add(imagePanel);

       
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

}
