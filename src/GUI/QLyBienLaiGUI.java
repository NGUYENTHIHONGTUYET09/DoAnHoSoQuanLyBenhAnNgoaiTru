package GUI;

import BUS.DSBienLaiBUS;
import DAO.BienLaiDAO;
import DAO.TinhDAO;
import DTO.BenhNhan;
import DTO.BienLaiDTO;
import DTO.Tinh;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.sql.Date;
import java.text.MessageFormat;
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
import java.awt.print.*;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Date;
import java.time.Year;
import java.util.Calendar;
import javax.swing.JPopupMenu;

/**
 *
 * @author HUNG
 */
public class QLyBienLaiGUI extends JFrame {
	public DSBienLaiBUS dsbl;
	public JTable jtable_table;
	private JTextField jextfield_timTheoMaBL;
	private JButton jbutton_timkiem;
	private JButton jbutton_xoa;
	private JButton jbutton_thoat;
	private JButton jbutton_them;
	private Them_Sua_BienLaiGUI tsbl;
	private ChiTietBienLaiGUI chiTietBL;
	private TinhDAO tinhdao;

	public QLyBienLaiGUI() {
		dsbl = new DSBienLaiBUS(this);

		init();
		fillData();
		setVisible(true);
		tsbl = new Them_Sua_BienLaiGUI(this); // Initialize tsbl here
		tsbl.setVisible(false);
		chiTietBL = new ChiTietBienLaiGUI();
		chiTietBL.setVisible(false);
		tinhdao = new TinhDAO();

	}

	public JTable getJtable_table() {
		return jtable_table;
	}

	public void setJtable_table(JTable jtable_table) {
		this.jtable_table = jtable_table;
	}

	public void fillData() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("Mã biên lai");
		model.addColumn("Mã NVTT");
		model.addColumn("Mã toa");
		model.addColumn("Ngày tạo");
		model.addColumn("Tổng tiền khám");

		jtable_table.setModel(model);
		for (BienLaiDTO bl : dsbl.getDSFromDB()) {
			insertIntoTable(bl);
		}
	}

	public void hideTableData() {
		DefaultTableModel model_table = (DefaultTableModel) jtable_table.getModel();
		model_table.setRowCount(0);
	}

	public void init() {
		setTitle("Quản lý thông tin biên lai");

		this.setSize(800, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		URL urlIconNotepad = QLyBienLaiGUI.class.getResource("/ICon/iconHospital.png");
		Image img = Toolkit.getDefaultToolkit().createImage(urlIconNotepad);
		this.setIconImage(img);

		JPanel jpanel_label = new JPanel();
		jpanel_label.setOpaque(false);
		jpanel_label.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

		Font font_1 = new Font("Arial", Font.BOLD, 20);

		JLabel jlabel_header = new JLabel("TRANG QUẢN LÝ THÔNG TIN BIÊN LAI", SwingConstants.CENTER);
		jlabel_header.setFont(font_1);
		jlabel_header.setForeground(Color.BLUE);

		ImageIcon iconHeader = new ImageIcon(getClass().getResource("/ICon/iconBienLai.png"));
		JLabel imageLabel = new JLabel(iconHeader);

		jpanel_label.add(imageLabel);
		jpanel_label.add(jlabel_header);

		JPanel jpanel_timkiem = new JPanel(new BorderLayout());

		JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		jextfield_timTheoMaBL = new JTextField(20); // Độ rộng của JTextField
		jextfield_timTheoMaBL.getDocument().addDocumentListener(new DocumentListener() {
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
			
				timKiem();
			}
		});
		jbutton_timkiem = new JButton("Tìm kiếm theo mã biên lai");
		//jbutton_timkiem.setBackground(Color.WHITE);

		JButton jbutton_quaylai = new JButton("Hiển thị tất cả");
		//jbutton_quaylai.setBackground(Color.WHITE);
		jbutton_quaylai.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jextfield_timTheoMaBL.setText("");
				resetTable();
			}
		});

		leftPanel.add(jextfield_timTheoMaBL);
		leftPanel.add(jbutton_timkiem);
		leftPanel.add(jbutton_quaylai);
		JButton jbutton_trangchu = new JButton("Đăng xuất");
	//	jbutton_trangchu.setBackground(Color.WHITE);
		ImageIcon iconTrangchu = new ImageIcon(getClass().getResource("/ICon/iconTrangchu.png"));
		jbutton_trangchu.setIcon(iconTrangchu);
		jbutton_trangchu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TrangChuDangNhapGUI tcdn = new TrangChuDangNhapGUI();
                                tcdn.setVisible(true);
                                dispose();
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
		JMenuItem jitem_openfile = new JMenuItem("Open File😊");
		jitem_openfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				thucHienOpenFile();
			}
		});

		JMenuItem jitem_savefile = new JMenuItem("Save File😊");
		jitem_savefile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				thucHienSaveFile();
			}
		});
		JMenuItem jitem_xuatds = new JMenuItem("Xuất Biên Lai😊");
		jitem_xuatds.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<BienLaiDTO> danhSachBienLaiDTO = dsbl.getDSFromDB();
				try {
					InBienLai();
				} catch (PrinterException ex) {
					Logger.getLogger(QLyBienLaiGUI.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

		});

		jmenu.add(jitem_openfile);
		jmenu.add(jitem_savefile);
		jmenu.add(jitem_xuatds);

		jmenubar.add(jmenu);

		JPanel jpanel_menu = new JPanel();
		jpanel_menu.setLayout(new BorderLayout());
		jpanel_menu.add(jmenubar, BorderLayout.NORTH);
		jpanel_menu.add(jpanel_header, BorderLayout.CENTER);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Mã biên lai");
		model.addColumn("Mã NVTT");
		model.addColumn("Mã toa");
		model.addColumn("Ngày tạo");
		model.addColumn("Tổng tiền khám");

		jtable_table.setModel(model);
		jtable_table.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		JScrollPane jscrollpane_table = new JScrollPane(jtable_table);
		jscrollpane_table.setBorder(new EmptyBorder(10, 10, 10, 10));

		jbutton_them = new JButton("Thêm");
	//	jbutton_them.setBackground(Color.WHITE);
		jbutton_them.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchToAddtBLGUI();
			}
		});

		jbutton_xoa = new JButton("Xóa");
		//jbutton_xoa.setBackground(Color.WHITE);
		jbutton_xoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteBienLaiDTO();
			}
		});

		jbutton_thoat = new JButton("Thoát");
	//	jbutton_thoat.setBackground(Color.WHITE);
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
		jpanel_button.add(jbutton_thoat);

		this.setLayout(new BorderLayout());
		this.add(jpanel_menu, BorderLayout.NORTH);
		this.add(jscrollpane_table, BorderLayout.CENTER);
		this.add(jpanel_button, BorderLayout.SOUTH);

		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem hienThiChiTiet = new JMenuItem("Hiển thị chi tiết");
		JMenuItem xuatBienLai = new JMenuItem("Xuất biên lai");
		popupMenu.add(hienThiChiTiet);
		popupMenu.add(xuatBienLai);

		hienThiChiTiet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HienThiChiTiet();
			}
		});

		xuatBienLai.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					InBienLai();
				} catch (PrinterException ex) {
					Logger.getLogger(QLyBienLaiGUI.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

		jtable_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showPopup(e);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showPopup(e);
				}
			}

			private void showPopup(MouseEvent e) {
				int row = jtable_table.rowAtPoint(e.getPoint());
				// If a row is clicked
				if (row >= 0 && row < jtable_table.getRowCount()) {
					jtable_table.setRowSelectionInterval(row, row);
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}

	public void HienThiChiTiet() {
		int selectedRow = jtable_table.getSelectedRow();
		BienLaiDTO bl = new BienLaiDTO((int) jtable_table.getValueAt(selectedRow, 0),
				jtable_table.getValueAt(selectedRow, 1).toString(), jtable_table.getValueAt(selectedRow, 2).toString(),
				jtable_table.getValueAt(selectedRow, 3).toString(), (Date) jtable_table.getValueAt(selectedRow, 4),
				(double) jtable_table.getValueAt(selectedRow, 5));
		chiTietBL.ShowBienLaiInfo(bl);
		chiTietBL.setLocationRelativeTo(this);

	}

	public void insertIntoTable(BienLaiDTO bl) {
		DefaultTableModel modelTable = (DefaultTableModel) jtable_table.getModel();
		Object[] rowData = { bl.getId(), bl.getMaBL(), bl.getMaNV_TT(), bl.getMaToa(), bl.getNgayTao(),
				bl.getTongTienKham() };
		modelTable.addRow(rowData);
	}

	public void updateTableRow(BienLaiDTO bl) {
		DefaultTableModel modelTable = (DefaultTableModel) jtable_table.getModel();
		int rowCount = modelTable.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			if (modelTable.getValueAt(i, 1).equals(bl.getMaBL())) {
				modelTable.setValueAt(bl.getId(), i, 0);
				modelTable.setValueAt(bl.getMaNV_TT(), i, 2);
				modelTable.setValueAt(bl.getMaToa(), i, 3);
				modelTable.setValueAt(bl.getNgayTao(), i, 4);
				modelTable.setValueAt(bl.getTongTienKham(), i, 5);
				break;
			}
		}
	}

	public BienLaiDTO showInfoChoosing() {
		DefaultTableModel modelTable = (DefaultTableModel) jtable_table.getModel();
		int selectedRow = jtable_table.getSelectedRow();

		if (selectedRow != -1 && selectedRow < modelTable.getRowCount()) {
			int id = (int) modelTable.getValueAt(selectedRow, 0); // Chuyển đổi kiểu dữ liệu của id từ String sang int
			String mabl = modelTable.getValueAt(selectedRow, 1).toString();
			String maNV_TT = modelTable.getValueAt(selectedRow, 2).toString();
			String maToa = modelTable.getValueAt(selectedRow, 3).toString();
			Date ngayTao = (Date) modelTable.getValueAt(selectedRow, 4);
			double tongTienKham = Double.parseDouble(modelTable.getValueAt(selectedRow, 5).toString());

			BienLaiDTO bl = new BienLaiDTO();

			bl.setId(id);
			bl.setMaBL(mabl);
			bl.setMaNV_TT(maNV_TT);
			bl.setMaToa(maToa);
			bl.setNgayTao(ngayTao);
			bl.setTongTienKham(tongTienKham);

			return bl;
		} else {
			return null;
		}
	}

	public void deleteBienLaiDTO() {
		DefaultTableModel model_table = (DefaultTableModel) jtable_table.getModel();
		int i_row = jtable_table.getSelectedRow();
		if (i_row > 0) {
			int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa dòng đã chọn?");
			if (luaChon == JOptionPane.YES_OPTION) {
				BienLaiDTO bl = showInfoChoosing();
				if (bl != null) {
					dsbl.xoaBienLaiDTO(bl.getMaBL());
					model_table.removeRow(i_row);
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn biên lai cần xóa", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void timKiem() {
		String maBLinput = jextfield_timTheoMaBL.getText().trim();
		DefaultTableModel model_table = (DefaultTableModel) jtable_table.getModel();
		model_table.setRowCount(0);
		if (maBLinput.isEmpty() == false) {
			for (BienLaiDTO bl : dsbl.getDSFromDB()) {
				if (bl.getMaBL().contains(maBLinput.toUpperCase())) {
					insertIntoTable(bl);
				}
			}
		} else {
			fillData();
		}
	}

	public void resetTable() {
		DefaultTableModel modelTable = (DefaultTableModel) jtable_table.getModel();
		modelTable.setRowCount(0);
		for (BienLaiDTO bl : dsbl.getDsbl()) {
			insertIntoTable(bl);
		}
	}

	public void exitFrame() {
		int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thoát?", "Xác nhận thoát",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public void switchToAddtBLGUI() {
		tsbl.setVisible(true);
		// setVisible(false);
	}

	public void switchToAddTrangChuGUI() {
		TrangChu tc = new TrangChu();
		tc.setVisible(true);
		setVisible(false);
	}

	public void openFile(File file) {
		ArrayList<BienLaiDTO> listsach = new ArrayList<BienLaiDTO>();
		try {
			this.dsbl.setTenFile(file.getAbsolutePath());
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			BienLaiDTO bl = null;
			while ((bl = (BienLaiDTO) ois.readObject()) != null) {
				listsach.add(bl);
			}

			ois.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.dsbl.setDsbl(listsach);
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

		for (BienLaiDTO bl : dsbl.getDsbl()) {
			insertIntoTable(bl);
		}
	}

	public void saveFile(String path) {
		try {
			this.dsbl.setTenFile(path);
			FileOutputStream fos = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			for (BienLaiDTO bl : this.dsbl.getDsbl()) {
				oos.writeObject(bl);
			}

			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void thucHienSaveFile() {
		String tenFile = this.dsbl.getTenFile();
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

	public void InBienLai() throws PrinterException {
		// Lấy hàng được chọn
		int selectedRow = jtable_table.getSelectedRow();

		if (selectedRow != -1) {
			// Lấy mô hình bảng và sau đó lấy thông tin biên lai từ hàng được chọn
			BienLaiDTO blPrint = new BienLaiDTO((int) jtable_table.getValueAt(selectedRow, 0),
					jtable_table.getValueAt(selectedRow, 1).toString(),
					jtable_table.getValueAt(selectedRow, 2).toString(),
					jtable_table.getValueAt(selectedRow, 3).toString(), (Date) jtable_table.getValueAt(selectedRow, 4),
					(double) jtable_table.getValueAt(selectedRow, 5));
			BienLaiPrintGUI blPrintGUI = new BienLaiPrintGUI();

			BenhNhan bn = dsbl.getBenhNhanByMaBL(blPrint.getMaBL());

			String tenbn = bn.getTenBN();
			Date ngaySinh = bn.getNgaySinh();

			// Sử dụng Calendar để lấy năm từ biến Date
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(ngaySinh);
			int namSinh = calendar.get(Calendar.YEAR);
			int tuoi = Year.now().getValue() - namSinh;
			String diaChi = bn.getDiaChi();
			Tinh tinh = tinhdao.getTinhById(bn.getQueQuan());
			String queQuan = tinh.getTenTinh();
			blPrintGUI.InBienLai(blPrint, tenbn, namSinh, tuoi, diaChi, queQuan);
			return;
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn biên lai cần xuất");
		}
	}

	public static void main(String agru[]) {
		try {
			QLyBienLaiGUI bl = new QLyBienLaiGUI();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}