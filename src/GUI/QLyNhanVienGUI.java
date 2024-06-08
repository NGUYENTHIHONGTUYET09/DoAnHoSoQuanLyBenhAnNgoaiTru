package GUI;

import BUS.DSNhanVienBUS;
import DTO.NhanVien;
import interfaces.TableInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.sql.Date;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class QLyNhanVienGUI extends JFrame implements TableInterface {

    public DSNhanVienBUS dsnv;
    public JTable jtable_table;
    private JTextField jtextfield_timTheoMaNV;
    private JButton jbutton_timkiem;
    private JButton jbutton_xoa;
    private JButton jbutton_thoat;
    private JButton jbutton_sua;
    private JButton jbutton_them;
    private Them_Sua_NhanVienGUI tsnv;

    public QLyNhanVienGUI() {
        dsnv = new DSNhanVienBUS(this);
        init();
        fillData();
        setVisible(true);
        tsnv = new Them_Sua_NhanVienGUI(this, dsnv); // Initialize tsnv here
        tsnv.setVisible(false);

    }

    public JTable getJtable_table() {
        return jtable_table;
    }

    public void setJtable_table(JTable jtable_table) {
        this.jtable_table = jtable_table;
    }

    public void fillData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Mã nhân viên");
        model.addColumn("Họ tên");
        model.addColumn("Ngày sinh");
        model.addColumn("Địa chỉ");
        model.addColumn("Giới tính");
        model.addColumn("Ngày vào làm");
        model.addColumn("Vai trò");
        model.addColumn("Trạng thái");

        jtable_table.setModel(model);
        for (NhanVien nv : dsnv.getDSFromDB()) {
            insertIntoTable(nv);
        }
    }

    public void hideTableData() {
        DefaultTableModel model_table = (DefaultTableModel) jtable_table.getModel();
        model_table.setRowCount(0);
    }

    public void init() {
        setTitle("Quản lý thông tin nhân viên");

        this.setSize(1200, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        URL urlIconNotepad = QLyNhanVienGUI.class.getResource("/ICon/iconHospital.png");
        Image img = Toolkit.getDefaultToolkit().createImage(urlIconNotepad);
        this.setIconImage(img);

        JPanel jpanel_label = new JPanel();
        jpanel_label.setOpaque(false);
        jpanel_label.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        Font font_1 = new Font("Arial", Font.BOLD, 20);

        JLabel jlabel_header = new JLabel("TRANG QUẢN LÝ THÔNG TIN NHÂN VIÊN", SwingConstants.CENTER);
        jlabel_header.setFont(font_1);
        jlabel_header.setForeground(Color.BLUE);

        ImageIcon iconHeader = new ImageIcon(getClass().getResource("/ICon/iconNhanVien.png"));
        JLabel imageLabel = new JLabel(iconHeader);

        jpanel_label.add(imageLabel);
        jpanel_label.add(jlabel_header);

        JPanel jpanel_timkiem = new JPanel(new BorderLayout());

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        jtextfield_timTheoMaNV = new JTextField(20); // Độ rộng của JTextField
        jtextfield_timTheoMaNV.getDocument().addDocumentListener(new DocumentListener() {
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
        jbutton_timkiem = new JButton("Tìm kiếm theo mã nhân viên");
     //   jbutton_timkiem.setBackground(Color.WHITE);

        JButton jbutton_showAll = new JButton("Hiển thị tất cả");
      //  jbutton_showAll.setBackground(Color.WHITE);
        jbutton_showAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtextfield_timTheoMaNV.setText("");
                resetTable();
            }
        });

        leftPanel.add(jtextfield_timTheoMaNV);
        leftPanel.add(jbutton_timkiem);
        leftPanel.add(jbutton_showAll);
        JButton jbutton_trangchu = new JButton("Trang chủ");
       // jbutton_trangchu.setBackground(Color.WHITE);
        ImageIcon iconTrangchu = new ImageIcon(getClass().getResource("/ICon/iconTrangchu.png"));
        jbutton_trangchu.setIcon(iconTrangchu);
        jbutton_trangchu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToAddTrangChuAdmin();
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

     
    

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Mã nhân viên");
        model.addColumn("Họ tên");
        model.addColumn("Ngày sinh");
        model.addColumn("Địa chỉ");
        model.addColumn("Giới tính");
        model.addColumn("Ngày vào làm");
        model.addColumn("Vai trò");
        model.addColumn("Trạng thái");

        jtable_table.setModel(model);
        jtable_table.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        JScrollPane jscrollpane_table = new JScrollPane(jtable_table);
        jscrollpane_table.setBorder(new EmptyBorder(10, 10, 10, 10));

        jbutton_them = new JButton("Thêm");
   
        jbutton_them.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToAddtNVGUI();
            }
        });

        jbutton_xoa = new JButton("Xóa");
      //  jbutton_xoa.setBackground(Color.WHITE);
        jbutton_xoa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deleteNhanVien();
            }
        });

        jbutton_sua = new JButton("Sửa");
       // jbutton_sua.setBackground(Color.WHITE);
        jbutton_sua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhanVien nv = showInfoChoosing();
                if (nv != null) {
                    tsnv.showNhanVienInfo(nv);
                }
            }
        });

        jbutton_thoat = new JButton("Thoát");
       // jbutton_thoat.setBackground(Color.WHITE);
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
        this.add(jpanel_header, BorderLayout.NORTH);
        this.add(jscrollpane_table, BorderLayout.CENTER);
        this.add(jpanel_button, BorderLayout.SOUTH);
    }

    public void ClearForm() {
        jtextfield_timTheoMaNV.setText("");
    }

    public void inserOrUpdatetData(NhanVien nv) {
        if (!dsnv.checkNotExists(nv)) {
            insertIntoTable(nv);
        } else {
            dsnv.updateNhanVien(nv);
            updateTableRow(nv);
        }
    }

    public void insertIntoTable(NhanVien nv) {
        DefaultTableModel modelTable = (DefaultTableModel) jtable_table.getModel();
        Object[] rowData = {
            nv.getId(),
            nv.getMANV(),
            nv.getHOTEN(),
            nv.getNGAYSINH(),
            nv.getDIACHI(),
            nv.getGIOITINH(),
            nv.getNGAYVL(),
            nv.getVAITRO(),
            nv.getTRANGTHAI(),};
        modelTable.addRow(rowData);
    }

    public void updateTableRow(NhanVien nv) {
        DefaultTableModel modelTable = (DefaultTableModel) jtable_table.getModel();
        int rowCount = modelTable.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            if (modelTable.getValueAt(i, 1).equals(nv.getMANV())) {
                modelTable.setValueAt(nv.getId(), i, 0);
                modelTable.setValueAt(nv.getHOTEN(), i, 2);
                modelTable.setValueAt(nv.getNGAYSINH(), i, 3);
                modelTable.setValueAt(nv.getDIACHI(), i, 4);
                modelTable.setValueAt(nv.getGIOITINH(), i, 5);
                modelTable.setValueAt(nv.getNGAYVL(), i, 6);
                modelTable.setValueAt(nv.getVAITRO(), i, 7);
                modelTable.setValueAt(nv.getTRANGTHAI(), i, 8);
                break;
            }
        }
    }

    public NhanVien showInfoChoosing() {
        DefaultTableModel modelTable = (DefaultTableModel) jtable_table.getModel();
        int selectedRow = jtable_table.getSelectedRow();

        if (selectedRow != -1 && selectedRow < modelTable.getRowCount()) {
            int ID = Integer.parseInt(modelTable.getValueAt(selectedRow, 0).toString());
            String MANV = modelTable.getValueAt(selectedRow, 1).toString();
            String HOTEN = modelTable.getValueAt(selectedRow, 2).toString();
            Date NGAYSINH = (Date) modelTable.getValueAt(selectedRow, 3);
            String DIACHI = modelTable.getValueAt(selectedRow, 4).toString();
            String GIOITINH = modelTable.getValueAt(selectedRow, 5).toString();
            Date NGAYVL = (Date) modelTable.getValueAt(selectedRow, 6);
            String VAITRO = modelTable.getValueAt(selectedRow, 7).toString();
            String TRANGTHAI = modelTable.getValueAt(selectedRow, 8).toString();

            NhanVien nv = new NhanVien();
            
            nv.setId(ID);
            nv.setMANV(MANV);
            nv.setHOTEN(HOTEN);
            nv.setNGAYSINH(NGAYSINH);
            nv.setDIACHI(DIACHI);
            nv.setGIOITINH(GIOITINH);
            nv.setNGAYVL(NGAYVL);
            nv.setVAITRO(VAITRO);
            nv.setTRANGTHAI(TRANGTHAI);
            return nv;
        } else {
            return null;
        }
    }

    public void deleteNhanVien() {
        DefaultTableModel model_table = (DefaultTableModel) jtable_table.getModel();
        int i_row = jtable_table.getSelectedRow();
        int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa dòng đã chọn?");
        if (luaChon == JOptionPane.YES_OPTION) {
            NhanVien nv = showInfoChoosing();
            if (nv != null) {
                System.out.println(nv.getMANV());
                if (dsnv.xoaNhanVien(nv.getMANV())) {
                    model_table.removeRow(i_row);
                }
            }
        }
    }

    public void timKiem() {
        String maNVinput = jtextfield_timTheoMaNV.getText().trim();
        DefaultTableModel model_table = (DefaultTableModel) jtable_table.getModel();
        model_table.setRowCount(0);
        if (maNVinput.isEmpty() == false) {
            for (NhanVien nv : dsnv.getDsnv()) {
                if (nv.getMANV().contains(maNVinput.toUpperCase())) {
                    insertIntoTable(nv);
                }
            }
        } else {
            fillData();
        }
    }

    public void resetTable() {
        DefaultTableModel modelTable = (DefaultTableModel) jtable_table.getModel();
        modelTable.setRowCount(0);
        for (NhanVien nv : dsnv.getDsnv()) {
            insertIntoTable(nv);
        }
    }

    public void exitFrame() {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thoát?", "Xác nhận thoát",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void switchToAddtNVGUI() {
        tsnv.setVisible(true);
    
    }

    public void switchToAddTrangChuAdmin() {
        TrangChuAdmin tc = new TrangChuAdmin();
        tc.setVisible(true);
        setVisible(false);
    }

    public void openFile(File file) {
        ArrayList<NhanVien> listsach = new ArrayList<NhanVien>();
        try {
            this.dsnv.setTenFile(file.getAbsolutePath());
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            NhanVien nv = null;
            while ((nv = (NhanVien) ois.readObject()) != null) {
                listsach.add(nv);
            }

            ois.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.dsnv.setDsnv(listsach);
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

        for (NhanVien nv : dsnv.getDsnv()) {
            insertIntoTable(nv);
        }
    }

    public void saveFile(String path) {
        try {
            this.dsnv.setTenFile(path);
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (NhanVien nv : this.dsnv.getDsnv()) {
                oos.writeObject(nv);
            }

            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void thucHienSaveFile() {
        String tenFile = this.dsnv.getTenFile();
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

    public NhanVien getNhanVienDataById(String maNhanVien) {
     
        return dsnv.getNhanVienByMaNV(maNhanVien);
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
                new QLyNhanVienGUI().setVisible(true);
            }
        });

    }

}