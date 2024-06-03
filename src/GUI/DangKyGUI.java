package GUI;

import BUS.DSNhanVienBUS;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BUS.DanhSachBNBUS;
import BUS.DanhSachTKBUS;
import DAO.NhanVienDAO;
import DTO.TaiKhoan;
import DTO.NhanVien;
import interfaces.TableInterface;

public class DangKyGUI extends JFrame {

    private TaiKhoan dk;
    private JTextField jTextField_hoten;
    private JLabel jLabel_hoten;
    public JTextField jTextField_maso;
    private JLabel jLabel_maso;
    private JLabel jLabel_dienthoai;
    private JTextField jTextField_dienthoai;
    private JLabel jLabel_mail;
    public JTextField jTextField_mail;
    private JLabel jLabel_matkhau;
    public JTextField jTextField_matkhau;
    private JLabel jLabel_golaimatkhau;
    private JRadioButton jRadioButton_xacNhan;
    private JButton jButton_dangKy;
    private JPanel jPanel_thongtin;
    private JPanel jPanel_button;
    private JTextField jTextField_golaimk;
    private DanhSachTKBUS dsdkbus;
    private JFrame qlttdk;
    private JButton jbutton_luu;

    public DanhSachBNBUS dsbnbus;
    public DSNhanVienBUS dsnvbus;
    private JRadioButton jRadioButton_NVTN;
    private JRadioButton jRadioButton_NVTT;
    private JRadioButton jRadioButton_Admin;
    private JRadioButton jRadioButton_QuanLyKho;
    private JRadioButton jRadioButton_BS;
    private String vaiTro = "";

    private Object tableInterface;
    private TaiKhoan selectedTaiKhoan;

    public DangKyGUI(JFrame jframetruoc, boolean isFromLogin, TaiKhoan selectedTaiKhoan) {
        tableInterface = (DanhSachTaiKhoan) qlttdk;
        dsnvbus = new DSNhanVienBUS();
        if (!isFromLogin) {
            this.qlttdk = jframetruoc;
            dsdkbus = new DanhSachTKBUS((TableInterface) tableInterface);
        } else {
            qlttdk = jframetruoc;
            dsdkbus = new DanhSachTKBUS(qlttdk);

        }
        // Cái này kệ nó
        init(isFromLogin);
        this.selectedTaiKhoan = selectedTaiKhoan;
        if (selectedTaiKhoan != null) {
            showPatientInfo(selectedTaiKhoan);
        }
        setVisible(true);
    }


    public void init(boolean isFromLogin) {
        this.setTitle("Đăng Ký");
        this.setSize(580, 310);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        URL urlIconNotepad = BenhNhanGUI.class.getResource("/ICon/iconDangKy.png");
        Image img = Toolkit.getDefaultToolkit().createImage(urlIconNotepad);
        this.setIconImage(img);

        jLabel_hoten = new JLabel("Họ và tên               ");
        jTextField_hoten = new JTextField(45);
        jLabel_maso = new JLabel("Mã số                      ");
        jTextField_maso = new JTextField(45);

        jLabel_dienthoai = new JLabel("Điện thoại               ");
        jTextField_dienthoai = new JTextField(45);

        jLabel_mail = new JLabel("Email                       ");
        jTextField_mail = new JTextField(45);

        jLabel_matkhau = new JLabel("Mật khẩu                 ");
        jTextField_matkhau = new JTextField(45);

        jLabel_golaimatkhau = new JLabel("Gõ lại mật khẩu       ");
        jTextField_golaimk = new JTextField(45);

        JPanel jPanel_thongtin = new JPanel();
        jPanel_thongtin.setLayout(new FlowLayout());
        // jPanel_thongtin.setBorder(new EmptyBorder(10, 10, 10, 10));

        jPanel_thongtin.add(jLabel_hoten);
        jPanel_thongtin.add(jTextField_hoten);

        jPanel_thongtin.add(jLabel_maso);
        jPanel_thongtin.add(jTextField_maso);

        jPanel_thongtin.add(jLabel_dienthoai);
        jPanel_thongtin.add(jTextField_dienthoai);

        jPanel_thongtin.add(jLabel_mail);
        jPanel_thongtin.add(jTextField_mail);

        jPanel_thongtin.add(jLabel_matkhau);
        jPanel_thongtin.add(jTextField_matkhau);

        jPanel_thongtin.add(jLabel_golaimatkhau);
        jPanel_thongtin.add(jTextField_golaimk);

        ButtonGroup group = new ButtonGroup();

        jRadioButton_NVTN = new JRadioButton("NV Tiếp Nhận");
        jRadioButton_NVTT = new JRadioButton("NV Thanh Toán");
        jRadioButton_BS = new JRadioButton("Bác Sĩ");
        jRadioButton_Admin = new JRadioButton("Admin");
        jRadioButton_QuanLyKho = new JRadioButton("Quản Lý Kho");

        group.add(jRadioButton_NVTN);
        group.add(jRadioButton_NVTT);
        group.add(jRadioButton_BS);
        group.add(jRadioButton_Admin);
        group.add(jRadioButton_QuanLyKho);

        JPanel jpanel_radio = new JPanel(new GridLayout(2, 3, 10, 10));
        jpanel_radio.setBorder(new EmptyBorder(10, 10, 10, 10));
        jpanel_radio.add(jRadioButton_NVTN);
        jpanel_radio.add(jRadioButton_NVTT);
        jpanel_radio.add(jRadioButton_BS);
        jpanel_radio.add(jRadioButton_Admin);
        jpanel_radio.add(jRadioButton_QuanLyKho);

        jButton_dangKy = new JButton("Đăng Ký");
        jButton_dangKy.setBackground(Color.green);
        jButton_dangKy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    performDangKi();
                } catch (MessagingException e1) {
                    e1.printStackTrace();
                }

            }
        });

        jbutton_luu = new JButton("Lưu");
        jbutton_luu.setBackground(Color.GREEN);
        jbutton_luu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performEdit(isFromLogin);
            }
        });

        JButton jbutton_huy = new JButton("Hủy");
        jbutton_huy.setBackground(Color.GREEN);
        jbutton_huy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchBack();

            }
        });

        JPanel jPanel_button = new JPanel(new GridLayout(1, 3));
        jPanel_button.setBorder(new EmptyBorder(10, 10, 10, 10));
        jPanel_button.add(jButton_dangKy);
        jPanel_button.add(jbutton_luu);
        jPanel_button.add(jbutton_huy);

        JPanel jpanel_bottem = new JPanel(new BorderLayout());
        jpanel_bottem.add(jpanel_radio, BorderLayout.CENTER);
        jpanel_bottem.add(jPanel_button, BorderLayout.SOUTH);

        this.setLayout(new BorderLayout());
        this.add(jPanel_thongtin, BorderLayout.CENTER);
        this.add(jpanel_bottem, BorderLayout.SOUTH);
    }

    private void performEdit(boolean isFromLogin) {
        if (isFromLogin) {
            return;
        }
        editTaiKhoan();

    }

    private void switchBack() {
        qlttdk.setVisible(true);
        dispose();
    }

    public void performDangKi() throws AddressException, MessagingException {

        if (jRadioButton_BS.isSelected() || jRadioButton_NVTN.isSelected() || jRadioButton_NVTT.isSelected()
                || jRadioButton_Admin.isSelected() || jRadioButton_QuanLyKho.isSelected()) {
            if (jTextField_mail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng điền Email!");
                return;
            }

            String maSo = jTextField_maso.getText();
            String email = jTextField_mail.getText();
            boolean maSoTonTai = false;

            for (NhanVien nv : dsnvbus.getDSFromDB()) {
                if (nv.getMANV().equals(maSo)) {
                    maSoTonTai = true;
                    break;
                }
            }

            if (!maSoTonTai) {
                JOptionPane.showMessageDialog(null, "Nhân viên chưa có hồ sơ!");
                return;
            }

            if (!dsdkbus.checkEmailNotExists(email)) {
                JOptionPane.showMessageDialog(null, "Email đã được đăng ký với 1 tài khoản khác!");
                return;
            }

            String matkhau = jTextField_matkhau.getText();

            if (jRadioButton_BS.isSelected()) {
                addTaiKhoanNhanVien("Bác Sĩ");
            } else if (jRadioButton_NVTN.isSelected()) {
                addTaiKhoanNhanVien("NV Tiếp Nhận");
            } else if (jRadioButton_NVTT.isSelected()) {
                addTaiKhoanNhanVien("NV Thanh Toán");
            } else if (jRadioButton_Admin.isSelected()) {
                addTaiKhoanNhanVien("Admin");
            } else if (jRadioButton_QuanLyKho.isSelected()) {
                addTaiKhoanNhanVien("Quản Lý Kho");
            }

          //  switchToDSDKGUI();
            TrangChuAdmin tca = new TrangChuAdmin();
            
            tca.setVisible(true);
            dispose();
            
            try{
                sendMail(email, "Bạn đã đăng ký tài khoản vào hệ thống với mật khẩu là: " + matkhau);
            }catch(AddressException ae){
                ae.printStackTrace();
            }catch(MessagingException me){
                me.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn vai trò!");
        }
    }

    public void sendMail(String email, String message) throws AddressException, MessagingException {
        String mailFrom = "viptt2004@gmail.com";
        String password = "alneedlftsrmooyz";
        Properties pros = new Properties();
        pros.put("mail.smtp.starttls.enable", "true");
        pros.put("mail.smtp.host", "smtp.gmail.com");
        pros.put("mail.smtp.port", "587");
        pros.put("mail.smtp.auth", "true");
        pros.put("mail.debug", "true");

        Authenticator authen = new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(mailFrom, password);
            }
        };

        Session ses = Session.getInstance(pros, authen);

        MimeMessage mimeMess = new MimeMessage(ses);

        mimeMess.addFrom(InternetAddress.parse(mailFrom));

        mimeMess.addRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

        mimeMess.setSubject("Xác nhận tài khỏa");

        mimeMess.setText(message);

        Transport.send(mimeMess);

    }

    public void addTaiKhoanNhanVien(String vaiTro) {
        String hoten = this.jTextField_hoten.getText();
        String manv = this.jTextField_maso.getText();
        String sdt = this.jTextField_dienthoai.getText();
        String email = this.jTextField_mail.getText();
        String matkhau = this.jTextField_matkhau.getText();
        String mkxacnhan = this.jTextField_golaimk.getText();

        if (manv.isEmpty() || email.isEmpty() || matkhau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!sdt.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải chứa 10 chữ số và không có chữ cái!", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!matkhau.equals(mkxacnhan)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không khớp!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        NhanVienDAO nvd = new NhanVienDAO();
        NhanVien nv = nvd.getByMaSoNV(manv);

        if (nv == null) {
            JOptionPane.showMessageDialog(this, "Nhân viên không tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        TaiKhoan newTK = new TaiKhoan();
        newTK.setHoTen(hoten);
        newTK.setMaSoNV(nv.getId());
        newTK.setSoDienThoai(sdt);
        newTK.setEmail(email);
        newTK.setMatKhau(matkhau);
        newTK.setMatkhauxacnhan(mkxacnhan);
        newTK.setVaiTro(vaiTro);

        boolean success = dsdkbus.addDangKy(newTK);

        if (success) {
            DanhSachTaiKhoan dstk = new DanhSachTaiKhoan();
            dstk.inserOrUpdatetData(newTK, null);
            JOptionPane.showMessageDialog(this, "Đăng ký thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void editTaiKhoan() {
        if (selectedTaiKhoan != null) {
            int confirmation = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa thông tin tài khoản?",
                    "Xác nhận sửa thông tin", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                String hoTen = jTextField_hoten.getText();
                String maSo = jTextField_maso.getText();
                String sdt = jTextField_dienthoai.getText();
                String email = jTextField_mail.getText();
                String mk = jTextField_matkhau.getText();
                String mkxn = jTextField_golaimk.getText();

                if (hoTen.isEmpty() || maSo.isEmpty() || sdt.isEmpty() || email.isEmpty() || mk.isEmpty()
                        || mkxn.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!sdt.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(this, "Số điện thoại phải chứa 10 chữ số và không có chữ cái!",
                            "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!mk.equals(mkxn)) {
                    JOptionPane.showMessageDialog(this, "Mật khẩu không khớp!", "Thông báo",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Xác định vai trò dựa trên radio button đã chọn
                String role = null;
                if (jRadioButton_NVTN.isSelected()) {
                    role = "NV Tiếp Nhận";
                } else if (jRadioButton_NVTT.isSelected()) {
                    role = "NV Thanh Toán";
                } else if (jRadioButton_BS.isSelected()) {
                    role = "Bác Sĩ";
                } else if (jRadioButton_Admin.isSelected()) {
                    role = "Admin";
                } else if (jRadioButton_QuanLyKho.isSelected()) {
                    role = "Quản Lý Kho";
                }

                if (role == null) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn vai trò!", "Thông báo",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    selectedTaiKhoan.setHoTen(hoTen);
                    selectedTaiKhoan.setSoDienThoai(sdt);
                    selectedTaiKhoan.setEmail(email);
                    selectedTaiKhoan.setMatKhau(mk);
                    selectedTaiKhoan.setMatkhauxacnhan(mkxn);

                    selectedTaiKhoan.setMaSoNV(Integer.valueOf(maSo));

                    selectedTaiKhoan.setVaiTro(role);

                    dsdkbus.updateDangKy(selectedTaiKhoan);
                    JOptionPane.showMessageDialog(this, "Sửa thông tin tài khoản thành công", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Mã số không hợp lệ!", "Thông báo",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một tài khoản từ bảng", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void showPatientInfo(TaiKhoan dk) {
        setVisible(true);

        jTextField_maso.setText(String.valueOf(dk.getMaSoNV()));
        jTextField_maso.setEnabled(false);
        jTextField_hoten.setText(dk.getHoTen());
        jTextField_dienthoai.setText(dk.getSoDienThoai());
        jTextField_mail.setText(dk.getEmail());
        jTextField_matkhau.setText(dk.getMatKhau());
        jTextField_golaimk.setText(dk.getMatkhauxacnhan());

        switch (dk.getVaiTro()) {
            case "NV Tiếp Nhận":
                jRadioButton_NVTN.setSelected(true);
                break;
            case "NV Thanh Toán":
                jRadioButton_NVTT.setSelected(true);
                break;
            case "Admin":
                jRadioButton_Admin.setSelected(true);
                break;
            case "Quản Lý Kho":
                jRadioButton_QuanLyKho.setSelected(true);
                break;
            case "Bác Sĩ":
                jRadioButton_BS.setSelected(true);
                break;
            default:
                break;
        }
    }
//
//    public void switchToDSDKGUI() {
//        if (qlttdk instanceof DanhSachTaiKhoan) {
//            qlttdk.setVisible(true);
//            setVisible(false);
//        } else {
//            DangNhapGUI dn = new DangNhapGUI(null);
//            dn.setVisible(true);
//            setVisible(false);
//        }
//
//    }

}
