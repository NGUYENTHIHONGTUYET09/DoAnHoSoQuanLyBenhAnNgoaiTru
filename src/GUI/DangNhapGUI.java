package GUI;

//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import DTO.BenhNhan;
import DTO.TaiKhoan;
import BUS.DanhSachTKBUS;
import DAO.BenhNhanDAO;
import DAO.BienLaiDAO;
import DAO.TaiKhoanDAO;
import javax.swing.border.EmptyBorder;

public class DangNhapGUI extends JFrame implements ActionListener {

    private JLabel jalbel_matKhau;
    private JLabel jlabel_taiKhoan;
    private JTextField jextfield_taiKhoan;
    private JTextField jextfield_matKhau;
    private JButton jbutton_dangNhap;
    private JButton jbutton_huy;
    private JButton jbutton_dangky;
    public JRadioButton jradio_NVTN;
    private JRadioButton jradio_NVTT;
    private JRadioButton jradio_DangKy;

    public DanhSachTKBUS dsdk;

    public JRadioButton jradio_BacSi;
    public JRadioButton jradio_QuanLy;
    public JRadioButton jradio_Admin;

    public static String email;
    public static int idNhanVien;
    private JFrame frameTruoc;
    private TaiKhoanDAO dkd;

    public DangNhapGUI(JFrame frameTruoc) {
        this.frameTruoc = frameTruoc;

        this.dsdk = new DanhSachTKBUS();
        init();
        dkd = TaiKhoanDAO.getInstance();

        this.setVisible(true);
    }

    public void init() {
        this.setTitle("Đăng nhập");
        this.setSize(550, 450);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        URL urlIconNotepad = DangNhapGUI.class.getResource("/ICon/iconHospital.png");
        Image img = Toolkit.getDefaultToolkit().createImage(urlIconNotepad);
        this.setIconImage(img);

        Font font_1 = new Font("Arial", Font.BOLD, 30);
        Font font_2 = new Font("Arial", Font.BOLD, 20);
        Font font_3 = new Font("Arial", Font.BOLD, 17);
        Font font_4 = new Font("Arial", Font.PLAIN, 15);

        JPanel jpanel_header = new JPanel();
        jpanel_header.setOpaque(false);
        jpanel_header.setLayout(new BorderLayout());

        JLabel jlabel_header = new JLabel("Xin chào!", SwingConstants.CENTER);
        jlabel_header.setFont(font_1);
        jlabel_header.setForeground(Color.red);

        jlabel_taiKhoan = new JLabel("Tài Khoản");
        jlabel_taiKhoan.setFont(font_3);

        JLabel jlabel_matKhau = new JLabel("Mật Khẩu");
        jlabel_matKhau.setFont(font_3);

        jextfield_taiKhoan = new JTextField();
        jextfield_taiKhoan.setFont(font_2);

        jextfield_matKhau = new JTextField();
        jextfield_matKhau.setFont(font_2);

        JPanel jpanel_dangNhap = new JPanel();
        jpanel_dangNhap.setBorder(new EmptyBorder(5, 100, 5, 110));
        jpanel_dangNhap.setOpaque(false);
        jpanel_dangNhap.setLayout(new GridLayout(4, 1, 0, 3));

        jpanel_dangNhap.add(jlabel_taiKhoan);
        jpanel_dangNhap.add(jextfield_taiKhoan);
        jpanel_dangNhap.add(jlabel_matKhau);
        jpanel_dangNhap.add(jextfield_matKhau);

        jpanel_header.add(jlabel_header, BorderLayout.NORTH);
        jpanel_header.add(jpanel_dangNhap, BorderLayout.CENTER);

        jbutton_dangNhap = new JButton("Đăng Nhập");
        jbutton_dangNhap.setBorder(new EmptyBorder(0, 10, 0, 10));
        jbutton_dangNhap.setFont(font_2);
        jbutton_dangNhap.setBackground(new Color(183, 226, 250));
        jbutton_dangNhap.setOpaque(true);
        jbutton_dangNhap.setBorderPainted(false);
        jbutton_dangNhap.setPreferredSize(new Dimension(10, 30));
        jbutton_dangNhap.addActionListener(this);

        jbutton_dangky = new JButton("Đăng ký");
        jbutton_dangky.setBorder(new EmptyBorder(0, 10, 0, 10));
        jbutton_dangky.setFont(font_2);
        jbutton_dangky.setBackground(new Color(183, 226, 250));
        jbutton_dangky.setOpaque(true);
        jbutton_dangky.setBorderPainted(false);
        jbutton_dangky.setPreferredSize(new Dimension(10, 30));
        jbutton_dangky.addActionListener(this);

        jbutton_huy = new JButton("Hủy");
        jbutton_huy.setBorder(new EmptyBorder(0, 10, 0, 10));
        jbutton_huy.setFont(font_2);
        jbutton_huy.setBackground(new Color(183, 226, 250));
        jbutton_huy.setOpaque(true);
        jbutton_huy.setBorderPainted(false);
        jbutton_huy.setPreferredSize(new Dimension(10, 30));
        jbutton_huy.addActionListener(this);

        JPanel jpanel_button = new JPanel();
        jpanel_button.setLayout(new GridLayout(1, 3, 5, 10));
        jpanel_button.setOpaque(false);
        jpanel_button.add(jbutton_dangNhap);
        jpanel_button.add(jbutton_huy);
        jpanel_button.add(jbutton_dangky);

        jradio_BacSi = new JRadioButton("Bác sĩ");
        jradio_BacSi.setFont(font_4);

        jradio_QuanLy = new JRadioButton("Quản lý kho");
        jradio_QuanLy.setFont(font_4);

        jradio_NVTN = new JRadioButton("NV Tiếp Nhận");
        jradio_NVTN.setFont(font_4);

        jradio_NVTT = new JRadioButton("NV Thanh Toán");
        jradio_NVTT.setFont(font_4);

        jradio_DangKy = new JRadioButton("Đăng ký tài khoản");
        jradio_DangKy.setFont(font_4);

        jradio_Admin = new JRadioButton("Admin");
        jradio_Admin.setFont(font_4);

        ButtonGroup group = new ButtonGroup();
        group.add(jradio_BacSi);
        group.add(jradio_Admin);
        group.add(jradio_QuanLy);
        group.add(jradio_NVTN);
        group.add(jradio_NVTT);
        group.add(jradio_DangKy);

        JPanel jpanel_radiobutton = new JPanel();
        jpanel_radiobutton.setBorder(new EmptyBorder(5, 100, 6, 100));
        jpanel_radiobutton.setLayout(new GridLayout(5, 1, 6, 6));
        jpanel_radiobutton.setOpaque(false);
        jpanel_radiobutton.add(jradio_BacSi);
        jpanel_radiobutton.add(jradio_Admin);
        jpanel_radiobutton.add(jradio_QuanLy);
        jpanel_radiobutton.add(jradio_NVTN);
        jpanel_radiobutton.add(jradio_NVTT);
        jpanel_radiobutton.add(jradio_DangKy);

        JPanel jpanel_bottom = new JPanel();
        jpanel_bottom.setBorder(new EmptyBorder(5, 30, 5, 30));
        jpanel_bottom.setOpaque(false);
        jpanel_bottom.setLayout(new BorderLayout());
        jpanel_bottom.add(jpanel_radiobutton, BorderLayout.CENTER);
        jpanel_bottom.add(jpanel_button, BorderLayout.SOUTH);

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
                g.setColor(new Color(255, 255, 255, 50));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        imagePanel.setLayout(new BorderLayout());

        JPanel jPanel_thongtin = new JPanel();
        jPanel_thongtin.setOpaque(false);
        jPanel_thongtin.setLayout(new BorderLayout());
        jPanel_thongtin.add(jpanel_header, BorderLayout.NORTH);
        jPanel_thongtin.add(jpanel_bottom, BorderLayout.SOUTH);

        // Thêm jPanel_thongtin lên trên imagePanel
        imagePanel.add(jPanel_thongtin, BorderLayout.CENTER);

        // Thêm imagePanel vào JFrame
        getContentPane().add(imagePanel, BorderLayout.CENTER);
    }

    public boolean kiemTraTaiKhoan(String taiKhoan, String matKhau, String vaiTro) {
        for (TaiKhoan tk : dsdk.getDSFromDB()) {
            System.out.print(tk);
            if (tk.getEmail().equals(taiKhoan) && tk.getMatKhau().equals(matKhau) && tk.getVaiTro().equals(vaiTro)) {
                return true;
            }
        }
        return false; // Trả về false nếu không có tài khoản nào khớp
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == jbutton_dangNhap) {
            if (jradio_Admin.isSelected()) {

                boolean isValid = kiemTraTaiKhoan(jextfield_taiKhoan.getText(), jextfield_matKhau.getText(), "Admin");
                if (isValid) {
                    TrangChuAdmin admin = new TrangChuAdmin();
                    admin.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không đúng!");
                }
            } else if (jradio_NVTN.isSelected()) {
                boolean isValid = kiemTraTaiKhoan(jextfield_taiKhoan.getText(), jextfield_matKhau.getText(), "NV Tiếp Nhận");
                if (isValid) {
                    TrangChu tc = new TrangChu();
                    tc.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không đúng!");
                }
            } else if (jradio_NVTT.isSelected()) {
                boolean isValid = kiemTraTaiKhoan(jextfield_taiKhoan.getText(), jextfield_matKhau.getText(), "NV Thanh Toán");
                if (isValid) {

                    QLyBienLaiGUI qlbl = new QLyBienLaiGUI();
                    qlbl.setVisible(true);
                    BienLaiDAO blDao = new BienLaiDAO();
                    email = jextfield_taiKhoan.getText();
                    System.out.println(email);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không đúng!");
                }

            } else if (jradio_BacSi.isSelected()) {
                boolean isValid = kiemTraTaiKhoan(jextfield_taiKhoan.getText(), jextfield_matKhau.getText(), "Bác sĩ");
                if (isValid) {
                    QuanLyThongTinKhamBenh qlttkh = new QuanLyThongTinKhamBenh();
                    qlttkh.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không đúng!");
                }

            } else if (jradio_QuanLy.isSelected()) {
                boolean isValid = kiemTraTaiKhoan(jextfield_taiKhoan.getText(), jextfield_matKhau.getText(), "Quản lý kho");
                if (isValid) {
                    ThuocGUI thuoc = new ThuocGUI();
                    thuoc.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không đúng!");
                }
            }
        }

        if (ae.getSource() == jbutton_dangky) {
            if (jradio_DangKy.isSelected()) {
                DangKyGUI dkg = new DangKyGUI(this, true, null);
                dkg.setVisible(true);
                dispose();
            }
        }
        if (ae.getSource() == jbutton_huy) {
            frameTruoc.setVisible(true);
            dispose();
        }
    }
    
    public static void main (String[] args){
        DangNhapGUI d = new DangNhapGUI(null);
        d.setVisible(true);
    }
    
}
