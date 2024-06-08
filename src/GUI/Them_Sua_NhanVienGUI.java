package GUI;

import BUS.DSNhanVienBUS;
import DTO.NhanVien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;
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

import com.toedter.calendar.*;

import java.util.Properties;



public class Them_Sua_NhanVienGUI extends JFrame {

	private QLyNhanVienGUI qlnv;
        //thong tin bien lai
        private JLabel jlabel_HOTEN;
	public JTextField jtextfield_HOTEN;
        private JLabel jlabel_NGAYSINH;
       JDateChooser ngaySinhChooser;
	private JLabel jlabel_DIACHI;
	public JTextField jtextfield_DIACHI;
	private JLabel jlabel_GIOITINH;
	public JComboBox<String> combobox_GIOITINH;
	private JLabel jlabel_NGAYVL;
        JDateChooser ngayVLChooser;
        private JLabel jlabel_VAITRO;
	public JComboBox<String> combobox_VAITRO;
        
	private JButton jbutton_them;
	private JButton jbutton_luu;
        
	private JPanel jpanel_info;

	private DSNhanVienBUS dsnv;
	private NhanVien nv;
	
	public Them_Sua_NhanVienGUI(QLyNhanVienGUI jframetruoc, DSNhanVienBUS dsnv) {
		this.dsnv = dsnv;
		qlnv = jframetruoc;
		init();
		setVisible(true);
		//qlnv.setVisible(false);
	}

	public void init() {
            setTitle("Thông tin nhân viên");

            this.setSize(450, 400);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);

            URL urlIconNotepad = Them_Sua_NhanVienGUI.class.getResource("/ICon/iconNhanVien.png");
            Image img = Toolkit.getDefaultToolkit().createImage(urlIconNotepad);
            this.setIconImage(img);

            JPanel jpanel_label = new JPanel();
            jpanel_label.setOpaque(false);
            jpanel_label.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

            Font font_1 = new Font("Arial", Font.BOLD, 20);
            Font font_2 = new Font("Arial", Font.BOLD, 15);

            JLabel jlabel_header = new JLabel("THÔNG TIN NHÂN VIÊN", SwingConstants.CENTER);
            jlabel_header.setFont(font_1);
            jlabel_header.setForeground(Color.BLUE);

            ImageIcon iconHeader = new ImageIcon(getClass().getResource("/ICon/iconNhanVien.png"));
            JLabel imageLabel = new JLabel(iconHeader);

            jpanel_label.add(imageLabel);
            jpanel_label.add(jlabel_header);

            jpanel_info = new JPanel();
            GridLayout layout = new GridLayout(7, 2, 0, 10);
            jpanel_info.setLayout(layout);
            jpanel_info.setBorder(new EmptyBorder(10, 20, 10, 20));


            jlabel_HOTEN = new JLabel("Họ và tên:");
            jlabel_HOTEN.setFont(font_2);
            jtextfield_HOTEN = new JTextField();
            jtextfield_HOTEN.setFont(font_2);

            jlabel_NGAYSINH = new JLabel("Ngày sinh:");
            jlabel_NGAYSINH.setFont(font_2);
            ngaySinhChooser = new JDateChooser();
            JTextFieldDateEditor editorNgaySinh = (JTextFieldDateEditor) ngaySinhChooser.getDateEditor();
            editorNgaySinh.setEditable(false);
            ngaySinhChooser.setDateFormatString("yyyy-MM-dd");

            jlabel_DIACHI = new JLabel("Địa chỉ:");
            jlabel_DIACHI.setFont(font_2);
            jtextfield_DIACHI = new JTextField();
            jtextfield_DIACHI.setFont(font_2);

            jlabel_GIOITINH = new JLabel("Giới tính:");
            jlabel_GIOITINH.setFont(font_2);
            String[] gioiTinhOptions = { "Nam", "Nữ" };
            combobox_GIOITINH = new JComboBox<>(gioiTinhOptions);
            combobox_GIOITINH.setFont(font_2);
            combobox_GIOITINH.setSelectedIndex(-1);

            jlabel_NGAYVL = new JLabel("Ngày vào làm:");
            jlabel_NGAYVL.setFont(font_2);
            ngayVLChooser = new JDateChooser();
            JTextFieldDateEditor editorNgayVL = (JTextFieldDateEditor) ngayVLChooser.getDateEditor();
            editorNgayVL.setEditable(false);
            ngayVLChooser.setDateFormatString("yyyy-MM-dd");

            jlabel_VAITRO = new JLabel("Vai trò:");
            jlabel_VAITRO.setFont(font_2);
            String[] vaiTroOptions = { "NV Thanh toán", "NV Tiếp nhận", "Bác sĩ" };
            combobox_VAITRO = new JComboBox<>(vaiTroOptions);
            combobox_VAITRO.setFont(font_2);
            combobox_VAITRO.setSelectedIndex(-1);

            jpanel_info.add(jlabel_HOTEN);
            jpanel_info.add(jtextfield_HOTEN);
            jpanel_info.add(jlabel_NGAYSINH);
            jpanel_info.add(ngaySinhChooser);
            jpanel_info.add(jlabel_DIACHI);
            jpanel_info.add(jtextfield_DIACHI);
            jpanel_info.add(jlabel_GIOITINH);
            jpanel_info.add(combobox_GIOITINH);
            jpanel_info.add(jlabel_NGAYVL);
            jpanel_info.add(ngayVLChooser);
            jpanel_info.add(jlabel_VAITRO);
            jpanel_info.add(combobox_VAITRO);


            jbutton_them = new JButton("Thêm");
            jbutton_them.setFont(font_2);
            jbutton_them.setBackground(Color.WHITE);
            jbutton_luu = new JButton("Lưu");
            jbutton_luu.setFont(font_2);
            jbutton_luu.setBackground(Color.WHITE);

            JPanel jpanel_bottom = new JPanel();
            jpanel_bottom.setLayout(new GridLayout(1, 2));
            jpanel_bottom.add(jbutton_them);
            jbutton_them.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            addNhanVienVaoTable();

//				switchToAddNhanVienGUI();
                    }
            });

            jpanel_bottom.add(jbutton_luu);
            jbutton_luu.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            editNhanVien();
                            switchToAddNhanVienGUI();
                    }
            });

            this.setLayout(new BorderLayout());
            this.add(jpanel_label, BorderLayout.NORTH);
            this.add(jpanel_info, BorderLayout.CENTER);
            this.add(jpanel_bottom, BorderLayout.SOUTH);

            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    jtextfield_HOTEN.setText("");
//                        Date date = new Date();
                    ngaySinhChooser.setDate(null);
                    jtextfield_DIACHI.setText("");
                    combobox_GIOITINH.setSelectedIndex(-1);
                    ngayVLChooser.setDate(null);
                    combobox_VAITRO.setSelectedIndex(-1);
                    setVisible(false);
                }
            });

	}

	public void addNhanVienVaoTable() {

            if (jtextfield_HOTEN.getText().isEmpty() 
                    || !ngaySinhChooser.isValid() 
                    || jtextfield_DIACHI.getText().isEmpty() 
                    || combobox_GIOITINH.getSelectedIndex() == -1
                    || !ngayVLChooser.isValid() 
                    || combobox_VAITRO.getSelectedIndex() == -1) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
	    }
            
	    String HOTEN = this.jtextfield_HOTEN.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateNgaySinh = ngaySinhChooser.getDate();
            String NGAYSINH = dateFormat.format(dateNgaySinh);
            String DIACHI = this.jtextfield_DIACHI.getText();
	    String GIOITINH = this.combobox_GIOITINH.getSelectedItem().toString();
            Date dateNgayVL = ngayVLChooser.getDate();
            String NGAYVL = dateFormat.format(dateNgayVL);
            String VAITRO = this.combobox_VAITRO.getSelectedItem().toString();
            
            
	    if (HOTEN.isEmpty() || NGAYSINH.isEmpty() || DIACHI.isEmpty() 
                    || GIOITINH.isEmpty() || NGAYVL.isEmpty() || VAITRO.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
	    }
            java.sql.Date ngaySinh = null;
                try {
//                    ngaySinh = new java.sql.Date(dateFormat.parse(this.jtextfield_NGAYSINH.getText()).getTime());
                    ngaySinh = new java.sql.Date(dateFormat.parse(NGAYSINH).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Định dạng ngày không hợp lệ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                java.sql.Date ngayVL = null;
                try {
                    ngayVL = new java.sql.Date(dateFormat.parse(NGAYVL).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Định dạng ngày không hợp lệ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                NhanVien newNV = new NhanVien();
                newNV.setHOTEN(HOTEN);
	        newNV.setNGAYSINH(ngaySinh);
	        newNV.setDIACHI(DIACHI);
                newNV.setGIOITINH(GIOITINH);
	        newNV.setNGAYVL(ngayVL);
	        newNV.setVAITRO(VAITRO);

                boolean success = dsnv.addNhanVien(newNV);

                if (success) {
                    // Thêm thông tin của nhân viên mới vào bảng
                    qlnv.insertIntoTable(newNV);
                    qlnv.fillData(); // Sau khi them thi goi fetch data lai
                    JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(this, "Nhân viên đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                this.dispose();
	}
              
        public void showNhanVienInfo(NhanVien nv) {
            setVisible(true);
            jtextfield_HOTEN.setText(nv.getHOTEN());
            ngayVLChooser.setDate(nv.getNGAYVL());
            jtextfield_DIACHI.setText(nv.getDIACHI());
            combobox_GIOITINH.setSelectedItem(nv.getGIOITINH());
            if (nv.getGIOITINH().equals("Nam")) {
                    combobox_GIOITINH.setSelectedIndex(0);
            }
            else {
                combobox_GIOITINH.setSelectedIndex(1);
            }
            ngaySinhChooser.setDate(nv.getNGAYSINH());
            if (nv.getVAITRO().equals("NV Thanh toán")) {
                    combobox_VAITRO.setSelectedIndex(0);
            }
            else if (nv.getVAITRO().equals("NV Tiếp nhận")) {
                combobox_VAITRO.setSelectedIndex(1);
            }
            else {
                combobox_VAITRO.setSelectedIndex(2);
            }
	}

	public void editNhanVien() {
	    NhanVien selectedNhanVien = qlnv.showInfoChoosing();
	    if (selectedNhanVien != null) {
	        int confirmation = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa thông tin nhân viên?", "Xác nhận sửa thông tin", JOptionPane.YES_NO_OPTION);
	        if (confirmation == JOptionPane.YES_OPTION) {
	            String HOTEN = this.jtextfield_HOTEN.getText();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateNgaySinh = ngayVLChooser.getDate();
                    String NGAYSINH = dateFormat.format(dateNgaySinh);

//                    String NGAYSINH = this.jtextfield_NGAYSINH.getText();
                    String DIACHI = this.jtextfield_DIACHI.getText();
                    String GIOITINH = this.combobox_GIOITINH.getSelectedItem().toString();
                    Date dateNgayVL = ngayVLChooser.getDate();
                    String NGAYVL = dateFormat.format(dateNgayVL);
                    String VAITRO = this.combobox_VAITRO.getSelectedItem().toString();
                    
               
                java.sql.Date ngaySinh = null;
                try {
//                    ngaySinh = new java.sql.Date(dateFormat.parse(this.jtextfield_NGAYSINH.getText()).getTime());
                    ngaySinh = new java.sql.Date(dateFormat.parse(NGAYSINH).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Định dạng ngày không hợp lệ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                   
               
                
                java.sql.Date ngayVL = null;
                try {
                    ngayVL = new java.sql.Date(dateFormat.parse(NGAYVL).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Định dạng ngày không hợp lệ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                selectedNhanVien.setHOTEN(HOTEN);
	        selectedNhanVien.setNGAYSINH(ngaySinh);
	        selectedNhanVien.setDIACHI(DIACHI);
                selectedNhanVien.setGIOITINH(GIOITINH);
	        selectedNhanVien.setNGAYVL(ngayVL);
	        selectedNhanVien.setVAITRO(VAITRO);

	            dsnv.updateNhanVien(selectedNhanVien);
	            
	            qlnv.updateTableRow(selectedNhanVien);
	            JOptionPane.showMessageDialog(this, "Sửa thông tin nhân viên thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên từ bảng", "Thông báo", JOptionPane.WARNING_MESSAGE);
	    }
	}

	public void switchToAddNhanVienGUI() {
		qlnv.setVisible(true);
		setVisible(false);
	}

}