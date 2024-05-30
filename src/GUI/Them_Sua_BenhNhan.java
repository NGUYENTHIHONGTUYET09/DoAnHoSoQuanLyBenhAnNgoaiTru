package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DTO.BenhNhan;
import BUS.DanhSachBNBUS;
import DAO.TinhDAO;
import DTO.Tinh;
import com.toedter.calendar.JDateChooser;
import java.util.Date;

public class Them_Sua_BenhNhan extends JFrame {

    private QuanLyThongTinBenhNhan qlttbn;

    public javax.swing.JComboBox<String> jComboBox_QueQuan;
    private JLabel jlabel_mabn;
    public JTextField jtextfield_mabn;
    private JLabel jlabel_tenbn;
    public JTextField jtextfield_tenbn;
    private JLabel jlabel_sdt;
    public JTextField jtextfield_sdt;
    private JLabel jlabel_ngaysinh;
    public JDateChooser ngaySinhChooser;
    private JLabel jlabel_diachi;
    public JTextField jtextfield_diachi;
    private JLabel jlabel_ghichu;
    public JTextField jtextfield_ghichu;
    private JLabel jlabel_gioitinh;
    public JComboBox<String> combobox_GIOITINH;
    public JTextField jtextfield_gioitinh;
    private JLabel jlabel_quequan;
    private JButton jbutton_them;
    private JButton jbutton_luu;
    private JPanel jpanel_info;

    private DanhSachBNBUS dsbn;
    private BenhNhan bn;
    private TinhDAO tinhdao;
    private JTextField jtextfield_id;

    public Them_Sua_BenhNhan(QuanLyThongTinBenhNhan jframetruoc) {
        dsbn = new DanhSachBNBUS(jframetruoc);
        tinhdao = TinhDAO.getInstance();
        qlttbn = jframetruoc;
        init();
        setVisible(true);
        qlttbn.setVisible(false);
    }

    public void init() {
        setTitle("Thông tin bệnh nhân");

        this.setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.WHITE);
        URL urlIconNotepad = DangNhapGUI.class.getResource("/ICon/iconPatient.png");
        Image img = Toolkit.getDefaultToolkit().createImage(urlIconNotepad);
        this.setIconImage(img);

        JPanel jpanel_label = new JPanel();
        jpanel_label.setOpaque(false);
        jpanel_label.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        Font font_1 = new Font("Arial", Font.BOLD, 20);
        Font font_2 = new Font("Arial", Font.BOLD, 15);

        JLabel jlabel_header = new JLabel("THÔNG TIN BỆNH NHÂN", SwingConstants.CENTER);
        jlabel_header.setBorder(new EmptyBorder(10, 10, 10, 10)); // Đặt khoảng cách trên là 10

        jlabel_header.setFont(font_1);
        jlabel_header.setForeground(Color.BLUE);

        jpanel_label.add(jlabel_header);

        jpanel_info = new JPanel();
        jpanel_info.setLayout(new GridLayout(14, 1, 0, 3));

        jComboBox_QueQuan = new JComboBox<String>();

        ArrayList<Tinh> listTinh = tinhdao.fetchAllTinh();
        jComboBox_QueQuan.addItem("");
        for (Tinh tinh : listTinh) {
            jComboBox_QueQuan.addItem(tinh.getTenTinh());
        }

        jlabel_quequan = new JLabel("Quê quán");
        jlabel_quequan.setBorder(new EmptyBorder(0, 0, 0, 0)); // Đặt khoảng cách trên là 10
        jlabel_quequan.setFont(font_2);

        jtextfield_mabn = new JTextField();
        jtextfield_mabn.setFont(font_2);
        jtextfield_id = new JTextField("Id");
        jtextfield_id.setFont(font_2);
        jlabel_tenbn = new JLabel("Tên bệnh nhân");
        jlabel_tenbn.setBorder(new EmptyBorder(0, 0, 0, 0)); // Đặt khoảng cách trên là 10

        jlabel_tenbn.setFont(font_2);

        jtextfield_tenbn = new JTextField();
        jtextfield_tenbn.setFont(font_2);
        jlabel_sdt = new JLabel("Số điện thoại");
        jlabel_sdt.setBorder(new EmptyBorder(0, 0, 0, 0)); // Đặt khoảng cách trên là 10

        jlabel_sdt.setFont(font_2);
        jtextfield_sdt = new JTextField();
        jtextfield_sdt.setFont(font_2);

        jlabel_ngaysinh = new JLabel("Ngày sinh");
        jlabel_ngaysinh.setBorder(new EmptyBorder(0, 0, 0, 0)); // Đặt khoảng cách trên là 10

        jlabel_ngaysinh.setFont(font_2);
        ngaySinhChooser = new JDateChooser();
        ngaySinhChooser.setDateFormatString("yyyy-MM-dd");

        jlabel_diachi = new JLabel("Địa chỉ");
        jlabel_diachi.setBorder(new EmptyBorder(0, 0, 0, 0)); // Đặt khoảng cách trên là 10

        jlabel_diachi.setFont(font_2);
        jtextfield_diachi = new JTextField();
        jtextfield_diachi.setFont(font_2);
        jlabel_ghichu = new JLabel("Ghi chú");
        jlabel_ghichu.setBorder(new EmptyBorder(0, 0, 0, 0)); // Đặt khoảng cách trên là 10

        jlabel_ghichu.setFont(font_2);
        jtextfield_ghichu = new JTextField();
        jtextfield_ghichu.setFont(font_2);

        jlabel_gioitinh = new JLabel("Giới tính");
        jlabel_gioitinh.setBorder(new EmptyBorder(0, 0, 0, 0)); // Đặt khoảng cách trên là 10

        jlabel_gioitinh = new JLabel("Giới tính:");
        jlabel_gioitinh.setFont(font_2);
        String[] gioiTinhOptions = {"Nam", "Nữ"};
        combobox_GIOITINH = new JComboBox<>(gioiTinhOptions);
        combobox_GIOITINH.setFont(font_2);
        combobox_GIOITINH.setSelectedIndex(-1);

        jpanel_info.add(jlabel_tenbn);
        jpanel_info.add(jtextfield_tenbn);
        jpanel_info.add(jlabel_sdt);
        jpanel_info.add(jtextfield_sdt);
        jpanel_info.add(jlabel_ngaysinh);
        jpanel_info.add(ngaySinhChooser);
        jpanel_info.add(jlabel_diachi);
        jpanel_info.add(jtextfield_diachi);
        jpanel_info.add(jlabel_quequan);
        jpanel_info.add(jComboBox_QueQuan);
        jpanel_info.add(jlabel_gioitinh);
        jpanel_info.add(combobox_GIOITINH);
        jpanel_info.add(jlabel_ghichu);
        jpanel_info.add(jtextfield_ghichu);

        jpanel_info.setBorder(new EmptyBorder(0, 0, 0, 30)); // Đặt khoảng cách trên là 10

        jbutton_them = new JButton("Thêm");
        jbutton_them.setPreferredSize(new Dimension(270, 30)); // Set preferred size for "Lưu" button dài - cao
        jbutton_them.setFont(font_2);
        //jbutton_them.setBackground(Color.WHITE);
        jbutton_them.setBackground(new Color(183, 226, 250)); // Đặt màu nền xanh nhạt cho nút "Lưu"

        jbutton_luu = new JButton("Lưu");
        jbutton_luu.setPreferredSize(new Dimension(270, 30)); // Set preferred size for "Lưu" button dài - cao
        jbutton_luu.setFont(font_2);
        //jbutton_luu.setBackground(Color.WHITE);
        jbutton_luu.setBackground(new Color(183, 226, 250)); // Đặt màu nền xanh nhạt cho nút "Lưu"

        JPanel jpanel_bottom = new JPanel();
        jpanel_bottom.setBorder(new EmptyBorder(20, 10, 10, 13)); // Đặt khoảng cách trên là 10
        jpanel_bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0)); // 20 là khoảng cách giữa các component

        //	jpanel_bottom.setLayout(new GridLayout(1, 2));
        jpanel_bottom.add(jbutton_them);

        jbutton_them.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBenhNhanVaoTable();

                switchToAddPatientGUI();
            }
        });

        jpanel_bottom.add(jbutton_luu);
        jbutton_luu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editBenhNhan();
                switchToAddPatientGUI();
            }
        });

        ImageIcon hinhanh = new ImageIcon(getClass().getResource("/ICon/iconBenhNhan.png"));
        JLabel imageLabel_hinhanh = new JLabel(hinhanh);
        imageLabel_hinhanh.setBorder(new EmptyBorder(0, 10, 20, 0)); // Đặt khoảng cách trên là 10

        JPanel jPanel_Center = new JPanel();
        jPanel_Center.setLayout(new GridLayout());
        jPanel_Center.add(imageLabel_hinhanh, BorderLayout.WEST);
        jPanel_Center.add(jpanel_info, BorderLayout.EAST);

        this.setLayout(new BorderLayout());
        this.add(jpanel_label, BorderLayout.NORTH);
        this.add(jPanel_Center, BorderLayout.CENTER);
        //this.add(jpanel_info, BorderLayout.CENTER);// lát xóa

        this.add(jpanel_bottom, BorderLayout.SOUTH);

    }

    public void addBenhNhanVaoTable() {
        String tenbn = this.jtextfield_tenbn.getText();
        String sdt = this.jtextfield_sdt.getText();
        String diachi = this.jtextfield_diachi.getText();
        String ghichu = this.jtextfield_ghichu.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateNgaySinh = ngaySinhChooser.getDate();
        String NGAYSINH = dateFormat.format(dateNgaySinh);

        if (tenbn.isEmpty() || sdt.isEmpty() || diachi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!sdt.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải chứa 10 chữ số và không có chữ cái!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int queQuanIndex = this.jComboBox_QueQuan.getSelectedIndex();
        if (queQuanIndex < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn quê quán!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            java.sql.Date ngaySinh = null;
            try {
                ngaySinh = new java.sql.Date(dateFormat.parse(NGAYSINH).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Định dạng ngày không hợp lệ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Tinh tinh = null;
            if (queQuanIndex > 0) {
                ArrayList<Tinh> listTinh = tinhdao.fetchAllTinh();
                tinh = listTinh.get(queQuanIndex - 1);
            }

             String gioiTinh = this.combobox_GIOITINH.getSelectedItem().toString();
            BenhNhan newBN = new BenhNhan();
            newBN.setTenBN(tenbn);
            newBN.setSdt(sdt);
            newBN.setNgaySinh(ngaySinh);
            newBN.setDiaChi(diachi);
            newBN.setGioiTinh(gioiTinh);
            if (tinh != null) {
                newBN.setQueQuan(tinh.getId());
            }
            newBN.setGhiChu(ghichu);

            boolean success = dsbn.addBenhNhan(newBN);

            if (success) {
                // Thêm thông tin của bệnh nhân mới vào bảng với cả hai tham số BenhNhan và Tinh
                qlttbn.insertIntoTable(newBN, tinh);
                qlttbn.fillData(); // Sau khi thêm thì gọi fetch data lại
                JOptionPane.showMessageDialog(this, "Thêm bệnh nhân thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Bệnh nhân đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
            this.dispose();
        }
    }

    public void editBenhNhan() {
        BenhNhan selectedBenhNhan = qlttbn.showInfoChoosing();
        if (selectedBenhNhan != null) {
            int confirmation = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa thông tin bệnh nhân?", "Xác nhận sửa thông tin", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                String mabn = jtextfield_mabn.getText();
                String tenbn = jtextfield_tenbn.getText();
                String sdt = jtextfield_sdt.getText();
                String diachi = jtextfield_diachi.getText();
                String gioitinh = this.combobox_GIOITINH.getSelectedItem().toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dateNgaySinh = ngaySinhChooser.getDate();
                String NGAYSINH = dateFormat.format(dateNgaySinh);
                java.sql.Date ngaySinh = null;
                try {
                    ngaySinh = new java.sql.Date(dateFormat.parse(NGAYSINH).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Định dạng ngày không hợp lệ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int queQuanIndex = jComboBox_QueQuan.getSelectedIndex();
                if (queQuanIndex < 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn quê quán!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int queQuanId = -1;
                if (queQuanIndex > 0) {
                    ArrayList<Tinh> listTinh = tinhdao.fetchAllTinh(); // Sử dụng phương thức fetchAllTinh từ tinhdao
                    queQuanId = listTinh.get(queQuanIndex - 1).getId();
                }

                String ghichu = jtextfield_ghichu.getText();

                selectedBenhNhan.setMaBN(mabn);
                selectedBenhNhan.setTenBN(tenbn);
                selectedBenhNhan.setSdt(sdt);
                selectedBenhNhan.setNgaySinh(ngaySinh);
                selectedBenhNhan.setDiaChi(diachi);
                selectedBenhNhan.setGioiTinh(gioitinh);
                selectedBenhNhan.setQueQuan(queQuanId); // Sử dụng id của tỉnh thay vì đối tượng tỉnh
                selectedBenhNhan.setGhiChu(ghichu);

                dsbn.updateBenhNhan(selectedBenhNhan);

                qlttbn.updateTableRow(selectedBenhNhan);
                JOptionPane.showMessageDialog(this, "Sửa thông tin bệnh nhân thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một bệnh nhân từ bảng", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void showPatientInfo(BenhNhan bn) {
        setVisible(true);
        jtextfield_mabn.setText(bn.getMaBN());
        jtextfield_mabn.setEnabled(false);
        jtextfield_tenbn.setText(bn.getTenBN());
        jtextfield_sdt.setText(bn.getSdt());
        ngaySinhChooser.setDate(bn.getNgaySinh());
        jtextfield_diachi.setText(bn.getDiaChi());
        jtextfield_ghichu.setText(bn.getGhiChu());
        combobox_GIOITINH.setSelectedItem(bn.getGioiTinh());
            if (bn.getGioiTinh().equals("Nam")) {
                    combobox_GIOITINH.setSelectedIndex(0);
            }
            else {
                combobox_GIOITINH.setSelectedIndex(1);
            }
        Tinh tinh = tinhdao.getTinhById(bn.getQueQuan());
        if (tinh != null) {
            jComboBox_QueQuan.setSelectedItem(tinh.getTenTinh());
        } else {
            jComboBox_QueQuan.setSelectedItem(null);
        }
    }

    public void switchToAddPatientGUI() {
        qlttbn.setVisible(true);
        setVisible(false);
    }

    public static void main(String agru[]) {
        QuanLyThongTinBenhNhan qlttbn = new QuanLyThongTinBenhNhan();
        Them_Sua_BenhNhan bn = new Them_Sua_BenhNhan(qlttbn);
    }

}
