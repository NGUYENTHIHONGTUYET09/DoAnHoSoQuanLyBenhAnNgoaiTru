package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.net.URL;
import java.text.MessageFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import BUS.DanhSachTKBUS;
import DAO.NhanVienDAO;
import DTO.NhanVien;
import DTO.TaiKhoan;
import interfaces.TableInterface;

public class DanhSachTaiKhoan extends JFrame implements TableInterface {

    public JTable jtable_table;
    public DanhSachTKBUS dsdkbus;
    private JTextField jTextField_timkiemSDT;
    private NhanVienDAO nvd = new NhanVienDAO();
    private TableInterface tableInterface;

    public DanhSachTaiKhoan() {

        tableInterface = this;
        dsdkbus = new DanhSachTKBUS(tableInterface);
        init();
        fillData();
    }

    public void fillData() {
        for (TaiKhoan dk : dsdkbus.getDSFromDB()) {
            NhanVien nv = null;
            if (dk.getMaSoNV() > 0) {
                nv = nvd.getById(dk.getMaSoNV());
            }
            if (nv != null) {
                insertIntoTable(dk, nv);
            }
            System.out.println(dk);
        }

    }

    public void init() {
        this.setTitle("Danh Sách Đăng Ký");
        this.setSize(1200, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel jpanel_header = new JPanel();
 
        jpanel_header.setLayout(new BorderLayout());

        JLabel jLabel_dsdk = new JLabel("Danh sách tài khoản", SwingConstants.CENTER);
        jLabel_dsdk.setForeground(Color.BLUE);
        jLabel_dsdk.setFont(new Font("Arial", Font.BOLD, 18));

        URL urlIconNotepad = BenhNhanGUI.class.getResource("/ICon/iconDanhSachDK.png");
        Image img = Toolkit.getDefaultToolkit().createImage(urlIconNotepad);
        this.setIconImage(img);

        jtable_table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Họ tên");
        //  model.addColumn("Mã bệnh nhân");
        model.addColumn("Mã nhân viên");
        model.addColumn("Sdt");
        model.addColumn("Email");
        model.addColumn("Mật khẩu");
        model.addColumn("Mật khẩu xác nhận");
        model.addColumn("Vai trò");
        model.addColumn("Trạng thái");
        jtable_table.setModel(model);
        jtable_table.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        JScrollPane jscrollpane_table = new JScrollPane(jtable_table);

        // ----------
        jTextField_timkiemSDT = new JTextField(15);
        jTextField_timkiemSDT.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleTextChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleTextChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleTextChange();
            }

            private void handleTextChange() {
                // Xử lý khi văn bản trong JTextField thay đổi
                timKiem();
            }
        });

        JButton jButton_timkiem = new JButton("Tìm kiếm theo số điện thoại");
      //  jButton_timkiem.setBackground(new Color(183, 226, 250));
        jButton_timkiem.setOpaque(true);
        jButton_timkiem.setBorderPainted(false);
//        jButton_timkiem.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                timKiem();
//            }
//        });

        JButton jButton_quaylai = new JButton("Làm mới");
       // jButton_quaylai.setBackground(new Color(183, 226, 250));
        jButton_quaylai.setOpaque(true);
        jButton_quaylai.setBorderPainted(false);
        jButton_quaylai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTable();
            }
        });

        JButton jButton_trangChu = new JButton("Trang Chủ");
     //   jButton_trangChu.setBackground(new Color(183, 226, 250));
        jButton_trangChu.setLayout(new BorderLayout());
        jButton_trangChu.setOpaque(true);
        jButton_trangChu.setBorderPainted(false);
        ImageIcon iconTrangChu = new ImageIcon(getClass().getResource("/ICon/iconTrangChu.png"));
        jButton_trangChu.setIcon(iconTrangChu);
        jButton_trangChu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TrangChuAdmin tc = new TrangChuAdmin();
                tc.setVisible(true);
                dispose();
            }
        });

        JPanel jPanel_timkiem = new JPanel();
        jPanel_timkiem.setLayout(new FlowLayout());

        jPanel_timkiem.add(jTextField_timkiemSDT);
        jPanel_timkiem.add(jButton_timkiem);
        jPanel_timkiem.add(jButton_quaylai);
        jPanel_timkiem.add(jButton_trangChu);

        JMenuBar jMenuBar_north = new JMenuBar();
        jMenuBar_north.setLayout(new BorderLayout());

        JMenu jMenu_menu = new JMenu("Menu");

        JMenuItem jMenuItem_save = new JMenuItem("Save file");
        ImageIcon iconSave = new ImageIcon(getClass().getResource("/ICon/iconSave.png"));
        jMenuItem_save.setIcon(iconSave);

        JMenuItem jMenuItem_open = new JMenuItem("Open file");
        ImageIcon iconOpen = new ImageIcon(getClass().getResource("/ICon/iconOpenFile.png"));
        jMenuItem_open.setIcon(iconOpen);

        JMenuItem jMenuItem_xuatds = new JMenuItem("Xuất Danh sách");
        ImageIcon iconExport = new ImageIcon(getClass().getResource("/ICon/iconExport.png"));
        jMenuItem_xuatds.setIcon(iconExport);

       
        jMenu_menu.add(jMenuItem_save);
        jMenu_menu.add(jMenuItem_open);
        jMenu_menu.add(jMenuItem_xuatds);
   

        jMenuBar_north.add(jMenu_menu);

        JPanel jPanel_timkiem_dsdk = new JPanel();
        ;
        jPanel_timkiem_dsdk.setLayout(new BorderLayout());

        jPanel_timkiem_dsdk.add(jLabel_dsdk, BorderLayout.NORTH);

        jPanel_timkiem_dsdk.add(jPanel_timkiem, BorderLayout.WEST);

        JPanel ipanel_button = new JPanel();
        ipanel_button.setLayout(new GridLayout(1, 4, 10, 10));

        JButton jButton_them = new JButton("Thêm");
   //     jButton_them.setBackground(new Color(183, 226, 250));
        jButton_them.setOpaque(true);
        jButton_them.setBorderPainted(false);
        jButton_them.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToDKGUI();
            }
        });

        JButton jButton_xoa = new JButton("Xóa");
     //   jButton_xoa.setBackground(new Color(183, 226, 250));
        jButton_them.setOpaque(true);
        jButton_xoa.setOpaque(true);
        jButton_xoa.setBorderPainted(false);

        jButton_xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteDangKy();
            }
        });

        JButton jButton_sua = new JButton("Sửa");
    //    jButton_sua.setBackground(new Color(183, 226, 250));
        jButton_them.setOpaque(true);
        jButton_sua.setOpaque(true);
        jButton_sua.setBorderPainted(false);
        jButton_sua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performEdit();
            }
        });

        JButton jButton_thoat = new JButton("Thoát");
    //    jButton_thoat.setBackground(new Color(183, 226, 250));


        jButton_thoat.setOpaque(true);
        jButton_thoat.setBorderPainted(false);
        jButton_thoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitFrame();
            }
        });

        ipanel_button.add(jButton_them);
        ipanel_button.add(jButton_xoa);
        ipanel_button.add(jButton_sua);
        ipanel_button.add(jButton_thoat);

        JPanel jPanel_timkiem_dsdk_jscTable = new JPanel();
        jPanel_timkiem_dsdk_jscTable.setLayout(new BorderLayout());

        jPanel_timkiem_dsdk_jscTable.add(jPanel_timkiem_dsdk, BorderLayout.NORTH);
        jPanel_timkiem_dsdk_jscTable.add(jscrollpane_table, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(jMenuBar_north, BorderLayout.NORTH);

        this.add(jPanel_timkiem_dsdk_jscTable, BorderLayout.CENTER);

        this.add(ipanel_button, BorderLayout.SOUTH);

    }

    private void performEdit() {
        TaiKhoan dk = showInfoChoosing();
        if (dk != null) {
            new DangKyGUI(this, false, dk);
        }
    }

    public void inserOrUpdatetData(TaiKhoan dk, NhanVien nhanVien) {
        if (!dsdkbus.checkNotExists(dk)) {
            NhanVien nvien = nvd.getById(dk.getMaSoNV());
            if (nvien != null) {
                insertIntoTable(dk, nvien);
            }
        } else {
            dsdkbus.updateDangKy(dk);
            updateTableRow(dk);
        }

    }

    public void insertIntoTable(TaiKhoan dk, NhanVien nv) {
        DefaultTableModel modelTable = (DefaultTableModel) jtable_table.getModel();
        Object[] rowData = {dk.getHoTen(), nv.getMANV(), dk.getSoDienThoai(), dk.getEmail(), dk.getMatKhau(),
            dk.getMatkhauxacnhan(), dk.getVaiTro(),dk.getTrangThai() // Thêm dữ liệu của vai trò vào rowData
    };
        modelTable.addRow(rowData);

        dsdkbus.addDangKy(dk);
    }

    public void updateTableRow(TaiKhoan dk) {
        DefaultTableModel modelTable = (DefaultTableModel) jtable_table.getModel();
        int rowCount = modelTable.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            if (modelTable.getValueAt(i, 1).equals(dk.getMaSoNV())) {
                modelTable.setValueAt(dk.getHoTen(), i, 0);
                modelTable.setValueAt(dk.getSoDienThoai(), i, 2);
                modelTable.setValueAt(dk.getEmail(), i, 3);
                modelTable.setValueAt(dk.getMatKhau(), i, 4);
                modelTable.setValueAt(dk.getMatkhauxacnhan(), i, 5);
                modelTable.setValueAt(dk.getVaiTro(), i, 6);
                 modelTable.setValueAt(dk.getTrangThai(), i, 7);


                break;
            }
        }
    }

    public TaiKhoan showInfoChoosing() {
        DefaultTableModel modelTable = (DefaultTableModel) jtable_table.getModel();
        int selectedRow = jtable_table.getSelectedRow();

        if (selectedRow != -1 && selectedRow < modelTable.getRowCount()) {
            String hoten = modelTable.getValueAt(selectedRow, 0).toString();
            String manv = modelTable.getValueAt(selectedRow, 1).toString();
            String sdt = modelTable.getValueAt(selectedRow, 2).toString();
            String email = modelTable.getValueAt(selectedRow, 3).toString();
            String matkhau = modelTable.getValueAt(selectedRow, 4).toString();
            String matkhauxn = modelTable.getValueAt(selectedRow, 5).toString();
            String vaitro = modelTable.getValueAt(selectedRow, 6).toString();
            String trangthai = modelTable.getValueAt(selectedRow, 7).toString();


         
            TaiKhoan newDK = new TaiKhoan();

            NhanVien nv = null;

            nv = nvd.getByMaSoNV(manv);

            newDK.setMaSoNV(nv.getId());

            newDK.setHoTen(hoten);

            newDK.setSoDienThoai(sdt);
            newDK.setEmail(email);
            newDK.setMatKhau(matkhau);
            newDK.setMatkhauxacnhan(matkhauxn);
            newDK.setVaiTro(vaitro);
             newDK.setTrangThai(trangthai);
            return newDK;

        } else {
            return null;
        }
    }


    
    public void deleteDangKy() {
        DefaultTableModel model_table = (DefaultTableModel) jtable_table.getModel();
        int i_row = jtable_table.getSelectedRow();
        
        if (i_row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa dòng đã chọn?");
        if (luaChon == JOptionPane.YES_OPTION) {
            TaiKhoan dk = showInfoChoosing();
            if (dk != null) {
                if (dsdkbus.xoaDangKy(String.valueOf(dk.getMaSoNV()))) {
                    model_table.removeRow(i_row);
                    JOptionPane.showMessageDialog(this, "Deleted successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Deletion failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Could not retrieve selected entry.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    @Override
    public void resetTable() {
        DefaultTableModel modelTable = (DefaultTableModel) jtable_table.getModel();
        modelTable.setRowCount(0);
        for (TaiKhoan dk : dsdkbus.getDSFromDB()) {
            NhanVien nv = nvd.getById(dk.getMaSoNV());
            if (nv != null) {
                insertIntoTable(dk, nv);
            }
        }
    }

    public void exitFrame() {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thoát?", "Xác nhận thoát",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void timKiem() {
        String sdtInput = jTextField_timkiemSDT.getText().trim();
        DefaultTableModel model_table = (DefaultTableModel) jtable_table.getModel();
        boolean found = false;

        model_table.setRowCount(0);

        if (!sdtInput.isEmpty()) {
            for (TaiKhoan dk : dsdkbus.getDSFromDB()) {
                if (dk.getSoDienThoai().contains(sdtInput)) {

                    NhanVien nv = nvd.getById(dk.getMaSoNV());
                    insertIntoTable(dk, nv);
                    found = true;
                    break; // Once found, exit the loop
                }
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy tài khoản!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void switchToDKGUI() {
        DangKyGUI dkg = new DangKyGUI(this, false, null);
        dkg.setVisible(true);
        setVisible(false);
    }

    public void inDanhSach() {

        int selectedRow = jtable_table.getSelectedRow();
        String thongtin;
        if (jtable_table.getRowCount() <= 0) {
            return;
        }
        if (selectedRow != -1) {

            DefaultTableModel model = (DefaultTableModel) jtable_table.getModel();
            String tenbn = model.getValueAt(selectedRow, 2).toString();

            thongtin = "Thông tin tài khoản" + tenbn;
        } else {
            thongtin = "Danh sách tài khoản";
        }

        MessageFormat header = new MessageFormat(thongtin);
        MessageFormat footer = new MessageFormat("Trang {0,number,integer}");

        try {
            jtable_table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }

}