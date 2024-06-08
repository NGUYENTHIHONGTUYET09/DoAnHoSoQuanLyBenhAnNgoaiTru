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
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.sql.Date;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.common.PDRectangle;
//import org.apache.pdfbox.pdmodel.font.PDType0Font;
//import org.apache.pdfbox.pdmodel.font.PDType1Font;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import BUS.DanhSachBNBUS;
import DAO.TinhDAO;
import DTO.BenhNhan;
import DTO.Tinh;
import Report.ThongKeBenhNhan;
//import Report.ThongKeJPanel;
import javax.swing.border.EmptyBorder;

public class QuanLyThongTinBenhNhan extends JFrame implements interfaces.TableInterface {

    public DanhSachBNBUS dsbn;
    public JTable jtable_table;
    private JTextField jextfield_sdt;
    private JButton jbutton_timkiem;
    private JButton jbutton_xoa;
    private JButton jbutton_thoat;
    private JButton jbutton_sua;

    public JTable getJtable_table() {
        return jtable_table;
    }

    public void setJtable_table(JTable jtable_table) {
        this.jtable_table = jtable_table;
    }

    private JButton jbutton_them;
    private Them_Sua_BenhNhan tsbn;
    private JComboBox<String> jComboBox_QueQuan;
    private Timer timer;
    private int dy;
    private JButton jbutton_indanhsach;
    public BenhNhan bn;

    private TinhDAO tinhdao;

    public QuanLyThongTinBenhNhan() {
        dsbn = new DanhSachBNBUS(this);
        tinhdao = new TinhDAO();
        init();
        // hideTableData();
        fillData();

//        tsbn = new Them_Sua_BenhNhan(this); // Initialize tsbn here
//        tsbn.setVisible(false);
        setVisible(true);
    }

    public void fillData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("Mã bệnh nhân");
        model.addColumn("Tên bệnh nhân");
        model.addColumn("Sdt");
        model.addColumn("Ngày sinh");
        model.addColumn("Địa chỉ");
        model.addColumn("Quê quán");
        model.addColumn("Giới tính");
        model.addColumn("Ghi chú");
        jtable_table.setModel(model);
        for (BenhNhan bn : dsbn.getDSFromDB()) {
            int id = bn.getQueQuan();
            Tinh tinh = tinhdao.getTinhById(id);
            insertIntoTable(bn, tinh);
        }
    }

    public void hideTableData() {
        DefaultTableModel model_table = (DefaultTableModel) jtable_table.getModel();
        model_table.setRowCount(0);
    }

    public void init() {
        setTitle("Quản lý thông tin bệnh nhân");

        this.setSize(900, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        URL urlIconNotepad = DangNhapGUI.class.getResource("/ICon/iconHospital.png");
        Image img = Toolkit.getDefaultToolkit().createImage(urlIconNotepad);
        this.setIconImage(img);

        JPanel jpanel_label = new JPanel();
        jpanel_label.setOpaque(false);
        jpanel_label.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        Font font_1 = new Font("Arial", Font.BOLD, 20);

        JLabel jlabel_header = new JLabel("TRANG QUẢN LÝ THÔNG TIN BỆNH NHÂN", SwingConstants.CENTER);
        jlabel_header.setFont(font_1);
        jlabel_header.setForeground(Color.BLUE);

        ImageIcon iconHeader = new ImageIcon(getClass().getResource("/ICon/iconHospital.png"));
        JLabel imageLabel = new JLabel(iconHeader);

        jpanel_label.add(imageLabel);
        jpanel_label.add(jlabel_header);

        JPanel jpanel_timkiem = new JPanel(new BorderLayout());

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        jextfield_sdt = new JTextField(20); // Độ rộng của JTextField
        jextfield_sdt.getDocument().addDocumentListener(new DocumentListener() {
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
        jbutton_timkiem = new JButton("Tìm Kiếm theo số điện thoại");
       // jbutton_timkiem.setBackground(Color.WHITE);

        JButton jbutton_quaylai = new JButton("Làm mới");
    //    jbutton_quaylai.setBackground(Color.WHITE);
        jbutton_quaylai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTable();
            }
        });

        tinhdao = new TinhDAO();
        jComboBox_QueQuan = new JComboBox<String>();

        ArrayList<Tinh> listTinh = tinhdao.fetchAllTinh();
        jComboBox_QueQuan.addItem("");
        for (Tinh tinh : listTinh) {
            jComboBox_QueQuan.addItem(tinh.getTenTinh());
        }
        JButton jbutton_look = new JButton();
      //  jbutton_look.setBackground(Color.WHITE);

        ImageIcon icon = new ImageIcon(getClass().getResource("/ICon/iconFilter.png")); // Thay đổi đường dẫn thành

        jbutton_look.setIcon(icon);

        jbutton_look.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thucHienTim();
            }
        });

        leftPanel.add(jextfield_sdt);
        leftPanel.add(jbutton_timkiem);
        leftPanel.add(jbutton_quaylai);
        leftPanel.add(jComboBox_QueQuan);
        leftPanel.add(jbutton_look);

        JButton jbutton_trangchu = new JButton("Trang chủ");
     //   jbutton_trangchu.setBackground(Color.WHITE);
        ImageIcon iconTrangchu = new ImageIcon(getClass().getResource("/ICon/iconTrangchu.png"));
        jbutton_trangchu.setIcon(iconTrangchu);
        jbutton_trangchu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToAddTrangChuGUI();
            }
        });

        jpanel_timkiem.add(leftPanel, BorderLayout.WEST);
        jpanel_timkiem.add(jbutton_trangchu, BorderLayout.EAST);

        jbutton_timkiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timKiem();
            }
        });

        JPanel jpanel_header = new JPanel();
        jpanel_header.setLayout(new BorderLayout());
        jpanel_header.add(jpanel_label, BorderLayout.NORTH);
        jpanel_header.add(jpanel_timkiem, BorderLayout.CENTER);

        jtable_table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JMenuBar jmenubar = new JMenuBar();
        JMenu jmenu = new JMenu("Menu");

        JMenuItem jitem_openfile = new JMenuItem("Open File😊", KeyEvent.VK_O);
        jitem_openfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_DOWN_MASK));
        ImageIcon iconOpen = new ImageIcon(getClass().getResource("/ICon/iconOpenFile.png"));
        jitem_openfile.setIcon(iconOpen);

        jitem_openfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thucHienOpenFile();
            }
        });
        jitem_openfile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jitem_openfile.setForeground(Color.CYAN); // Thay đổi màu chữ khi chuột vào
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jitem_openfile.setForeground(UIManager.getColor("Menu.foreground")); // Khôi phục màu chữ khi chuột rời
                // đi
            }
        });

        JMenuItem jitem_savefile = new JMenuItem("Save File😊", KeyEvent.VK_S);
        jitem_savefile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
        jitem_savefile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thucHienSaveFile();
            }
        });
        ImageIcon iconSave = new ImageIcon(getClass().getResource("/ICon/iconSave.png"));
        jitem_savefile.setIcon(iconSave);

        jitem_savefile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jitem_savefile.setForeground(Color.orange); // Thay đổi màu chữ khi chuột vào
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jitem_savefile.setForeground(UIManager.getColor("Menu.foreground")); // Khôi phục màu chữ khi chuột rời
       
            }
        });

        JMenuItem jitem_xuatds = new JMenuItem("Xuất Danh Sách😊", KeyEvent.VK_E);
        jitem_xuatds.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_DOWN_MASK));

        ImageIcon iconExport = new ImageIcon(getClass().getResource("/ICon/iconExport.png"));
        jitem_xuatds.setIcon(iconExport);
        jitem_xuatds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inDanhSach();
            }

        });

        jitem_xuatds.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jitem_xuatds.setForeground(Color.RED); // Thay đổi màu chữ khi chuột vào
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jitem_xuatds.setForeground(UIManager.getColor("Menu.foreground")); // Khôi phục màu chữ khi chuột rời đi
            }
        });

        JMenuItem jitem_thonngke = new JMenuItem("Thống kê", KeyEvent.VK_A);
        jitem_thonngke.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK));
//		ImageIcon iconOpen = new ImageIcon(getClass().getResource("/ICon/iconOpenFile.png"));
//		jitem_openfile.setIcon(iconOpen);
        jitem_thonngke.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ThongKeBenhNhan(dsbn).setVisible(true);
                } catch (Exception ex) {
                    // TODO: handle exception
                    ex.printStackTrace();
                }
                dispose();
            }
        });

        jmenu.add(jitem_openfile);
        jmenu.addSeparator();
        jmenu.add(jitem_savefile);
        jmenu.addSeparator();
        jmenu.add(jitem_xuatds);
        jmenu.addSeparator();
       // jmenu.add(jitem_thonngke);

        jmenubar.add(jmenu);

        JPanel jpanel_menu = new JPanel();
        jpanel_menu.setLayout(new BorderLayout());
        jpanel_menu.add(jmenubar, BorderLayout.NORTH);
        jpanel_menu.add(jpanel_header, BorderLayout.CENTER);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("Mã bệnh nhân");
        model.addColumn("Tên bệnh nhân");
        model.addColumn("Sdt");
        model.addColumn("Ngày sinh");
        model.addColumn("Địa chỉ");
        model.addColumn("Quê quán");
        model.addColumn("Giới tính");
        model.addColumn("Ghi chú");

        jtable_table.setModel(model);
        jtable_table.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        JScrollPane jscrollpane_table = new JScrollPane(jtable_table);
        jscrollpane_table.setBorder(new EmptyBorder(10, 10, 10, 10));

        jbutton_them = new JButton("Thêm");
     //   jbutton_them.setBackground(Color.WHITE);
        jbutton_them.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToAddPatientGUI();
            }
        });

        jbutton_xoa = new JButton("Xóa");
      //  jbutton_xoa.setBackground(Color.WHITE);
        jbutton_xoa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBenhNhan();
            }
        });

        jbutton_sua = new JButton("Sửa");
      //  jbutton_sua.setBackground(Color.WHITE);
        jbutton_sua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performEdit();
            }
        });

        jbutton_thoat = new JButton("Thoát");
      //  jbutton_thoat.setBackground(Color.WHITE);
        jbutton_thoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitFrame();
            }
        });

        JPanel jpanel_button = new JPanel();
        jpanel_button.setLayout(new GridLayout(1, 4, 10, 0));
        jpanel_button.add(jbutton_them);
        jpanel_button.add(jbutton_xoa);
        jpanel_button.add(jbutton_sua);
        jpanel_button.add(jbutton_thoat);

        this.setLayout(new BorderLayout());
        this.add(jpanel_menu, BorderLayout.NORTH);
        this.add(jscrollpane_table, BorderLayout.CENTER);
        this.add(jpanel_button, BorderLayout.SOUTH);
    }

    private void performEdit() {
        BenhNhan bn = showInfoChoosing();
        if (bn != null) {
            tsbn = new Them_Sua_BenhNhan(this);
            tsbn.showPatientInfo(bn);
            tsbn.setVisible(true);
        }
    }

    public void insertOrUpdateData(BenhNhan bn) {
        if (!dsbn.checkNotExists(bn)) {
            Tinh tinh = tinhdao.getTinhById(bn.getQueQuan());
            insertIntoTable(bn, tinh);
        } else {
            System.out.println("Updating patient with maBN: " + bn.getMaBN());
            dsbn.updateBenhNhan(bn);
            updateTableRow(bn);
        }
    }


    public void insertIntoTable(BenhNhan bn, Tinh tinh) {
        DefaultTableModel modelTable = (DefaultTableModel) jtable_table.getModel();
        Object[] rowData = {bn.getId(), bn.getMaBN(), bn.getTenBN(), bn.getSdt(), bn.getNgaySinh(), bn.getDiaChi(),
            tinh.getTenTinh(), bn.getGioiTinh(), bn.getGhiChu()};
        modelTable.addRow(rowData);
    }

    public void updateTableRow(BenhNhan bn) {
        DefaultTableModel modelTable = (DefaultTableModel) jtable_table.getModel();
        int rowCount = modelTable.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            if (modelTable.getValueAt(i, 3).equals(bn.getSdt())) {
                modelTable.setValueAt(bn.getId(), i, 0);
                modelTable.setValueAt(bn.getTenBN(), i, 2);
                modelTable.setValueAt(bn.getMaBN(), i, 1);
                modelTable.setValueAt(bn.getNgaySinh(), i, 4);
                modelTable.setValueAt(bn.getDiaChi(), i, 5);
                Tinh tinh = tinhdao.getTinhById(bn.getQueQuan());
                if (tinh != null) {
                    modelTable.setValueAt(tinh.getTenTinh(), i, 6);
                }
                modelTable.setValueAt(bn.getGioiTinh(), i, 7);
                modelTable.setValueAt(bn.getGhiChu(), i, 8);
                break;
            }
        }
    }


    public BenhNhan showInfoChoosing() {
        DefaultTableModel modelTable = (DefaultTableModel) jtable_table.getModel();
        int selectedRow = jtable_table.getSelectedRow();

        if (selectedRow != -1 && selectedRow < modelTable.getRowCount()) {
            int id = (int) modelTable.getValueAt(selectedRow, 0);
            String mabn = modelTable.getValueAt(selectedRow, 1).toString();
            String tenbn = modelTable.getValueAt(selectedRow, 2).toString();
            String sdt = modelTable.getValueAt(selectedRow, 3).toString();
            Date ngaysinh = (Date) modelTable.getValueAt(selectedRow, 4);
            String diachi = modelTable.getValueAt(selectedRow, 5).toString();
            String tenTinh = modelTable.getValueAt(selectedRow, 6).toString();
            String gioitinh = modelTable.getValueAt(selectedRow, 7).toString();
            String ghichu = modelTable.getValueAt(selectedRow, 8) != null ? modelTable.getValueAt(selectedRow, 8).toString() : "";

            BenhNhan bn = new BenhNhan();
            bn.setId(id);
            bn.setMaBN(mabn);
            bn.setTenBN(tenbn);
            bn.setSdt(sdt);
            bn.setNgaySinh(ngaysinh);
            bn.setDiaChi(diachi);

            Tinh tinh = tinhdao.getTinhByName(tenTinh);
            if (tinh != null) {
                bn.setQueQuan(tinh.getId());
            } else {
                bn.setQueQuan(-1);
            }

            bn.setGioiTinh(gioitinh);
            bn.setGhiChu(ghichu);

            return bn;
        } else {
            return null;
        }
    }


    public void deleteBenhNhan() {
        DefaultTableModel model_table = (DefaultTableModel) jtable_table.getModel();
        int i_row = jtable_table.getSelectedRow();
        int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa dòng đã chọn?");
        if (luaChon == JOptionPane.YES_OPTION) {
            BenhNhan bn = showInfoChoosing();
            if (bn != null && dsbn.xoaBenhNhan(bn.getSdt())) {
                model_table.removeRow(i_row);
            }
        }
    }

//	public void timKiem() {
//	    String sdtInput = jextfield_sdt.getText().trim();
//	    DefaultTableModel model_table = (DefaultTableModel) jtable_table.getModel();
//	    boolean found = false; 
//	    model_table.setRowCount(0);
//	    if (!sdtInput.isEmpty()) {
//	        for (BenhNhan bn : dsbn.getDsbn()) {
//	            if (bn.getSdt().equals(sdtInput)) {
//	                insertIntoTable(bn);
//	                found = true;
//	                break; 
//	            }
//	        }
//	    }
//	    
//	    if (!found) {
//	        JOptionPane.showMessageDialog(this, "Không tìm thấy bệnh nhân!", "Thông báo", JOptionPane.WARNING_MESSAGE);
//	    }
//	}
//	
    public void timKiem() {

        String sdtInput = jextfield_sdt.getText().trim();
        DefaultTableModel model_table = (DefaultTableModel) jtable_table.getModel();
        model_table.setRowCount(0);
        if (sdtInput.isEmpty() == false) {
            for (BenhNhan bn : dsbn.getDsbn()) {
                if (bn.getSdt().contains(sdtInput)) {
                    Tinh tinh = tinhdao.getTinhById(bn.getQueQuan());
                    insertIntoTable(bn, tinh);
                }
            }
        } else {
            fillData();
        }
    }

    public void thucHienTim() {
        resetTable();
        int queQuan = this.jComboBox_QueQuan.getSelectedIndex(); // số quê quán người dùng đã chọn
        DefaultTableModel model_table = (DefaultTableModel) jtable_table.getModel();
        ArrayList<BenhNhan> dsBenhNhanHienThi = new ArrayList<>(); // Danh sách bệnh nhân cần hiển thị

        if (queQuan >= 0) {
            Tinh tinhDaChon = tinhdao.getTinhById(queQuan); // trả về vị trí tỉnh và tên
            System.out.println("Tên tỉnh: " + tinhDaChon);

            for (BenhNhan bn : dsbn.getDsbn()) {
                Tinh tinh = tinhdao.getTinhById(bn.getQueQuan());
                String tenTinh = tinh.getTenTinh();
                if (tenTinh.equals(tinhDaChon.getTenTinh())) {
                    dsBenhNhanHienThi.add(bn); // Thêm bệnh nhân vào danh sách hiển thị
                }
            }
        }

        // Xóa tất cả các dòng trong bảng
        model_table.setRowCount(0);

        // Thêm các bệnh nhân phù hợp vào bảng
        for (BenhNhan bn : dsBenhNhanHienThi) {
            Tinh tinh = tinhdao.getTinhById(bn.getQueQuan());
            insertIntoTable(bn, tinh);
        }

        if (dsBenhNhanHienThi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy bệnh nhân!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
//    public void resetTable() {
//        DefaultTableModel modelTable = (DefaultTableModel) jtable_table.getModel();
//        modelTable.setRowCount(0);
//        for (BenhNhan bn : dsbn.getDsbn()) {
//            Tinh tinh = tinhdao.getTinhById(bn.getQueQuan());
//            insertIntoTable(bn, tinh);
//        }
//    }
    
    public void resetTable() {
        DefaultTableModel modelTable = (DefaultTableModel) jtable_table.getModel();
        modelTable.setRowCount(0);  // Clear the table

        List<BenhNhan> allBenhNhan = dsbn.getDSFromDB();  // Fetch all records from the database
        for (BenhNhan bn : allBenhNhan) {
            Tinh tinh = tinhdao.getTinhById(bn.getQueQuan());
            insertIntoTable(bn, tinh);
        }
    }


    public void exitFrame() {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thoát?", "Xác nhận thoát",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void switchToAddPatientGUI() {
        Them_Sua_BenhNhan themSuaBenhNhan = new Them_Sua_BenhNhan(this);
        themSuaBenhNhan.setVisible(true);
        setVisible(false);
    }

    public void switchToAddTrangChuGUI() {
        TrangChu tc = new TrangChu();
        tc.setVisible(true);
        setVisible(false);
    }

    public void openFile(File file) {
        ArrayList<BenhNhan> listsach = new ArrayList<BenhNhan>();
        try {
            this.dsbn.setTenFile(file.getAbsolutePath());
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            BenhNhan bn = null;
            while ((bn = (BenhNhan) ois.readObject()) != null) {
                listsach.add(bn);
            }

            ois.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.dsbn.setDsbn(listsach);
    }

    public void thucHienOpenFile() {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            openFile(file);
            thucHienTaiLaiDuLieu();
        }
    }

    public void thucHienTaiLaiDuLieu() {
        DefaultTableModel model_table = (DefaultTableModel) jtable_table.getModel();
        model_table.setRowCount(0);

        for (BenhNhan bn : dsbn.getDsbn()) {
            Tinh tinh = tinhdao.getTinhById(bn.getQueQuan());
            insertIntoTable(bn, tinh);
        }
    }

    public void saveFile(String path) {
        try {
            this.dsbn.setTenFile(path);
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (BenhNhan bn : this.dsbn.getDsbn()) {
                oos.writeObject(bn);
            }

            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void thucHienSaveFile() {
        String tenFile = this.dsbn.getTenFile();
        if (tenFile != null && tenFile.length() > 0) {
            saveFile(tenFile);
        } else {
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                saveFile(file.getAbsolutePath());
            }
        }
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

            thongtin = "Thông tin của bệnh nhân " + tenbn;
        } else {
            thongtin = "Danh sách bệnh nhân";
        }

        MessageFormat header = new MessageFormat(thongtin);
        MessageFormat footer = new MessageFormat("Trang {0,number,integer}");

        try {
            jtable_table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }

    public BenhNhan getPatientDataById(String maBenhNhan) {

        return dsbn.getBenhNhanByMaBN(maBenhNhan);
    }

    
    public static void main(String args[]) {

        try {
        	for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	  new QuanLyThongTinBenhNhan().setVisible(true);
            }
        });

    }

}
