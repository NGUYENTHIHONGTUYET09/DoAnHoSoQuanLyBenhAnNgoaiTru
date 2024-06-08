package GUI;

import BUS.DSBienLaiBUS;
import DTO.BienLaiDTO;
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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Them_Sua_BienLaiGUI extends JFrame {

	private QLyBienLaiGUI qlbl;

	private JLabel jlabel_maToa;
	public JTextField jtextfield_maToa;
	private JLabel jlabel_tongTienKham;
	public JTextField jtextfield_tongTienKham;
        
	private JButton jbutton_them;
        
	private JPanel jpanel_info;

	private DSBienLaiBUS dsbl;
	private BienLaiDTO bl;
	
	public Them_Sua_BienLaiGUI(QLyBienLaiGUI jframetruoc) {
		dsbl = new DSBienLaiBUS(jframetruoc);
		qlbl = jframetruoc;
		init();
		setVisible(true);
	}

	public void init() {
		setTitle("Thêm biên lai");

		this.setSize(400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		URL urlIconNotepad = Them_Sua_BienLaiGUI.class.getResource("/ICon/iconBienLai.png");
		Image img = Toolkit.getDefaultToolkit().createImage(urlIconNotepad);
		this.setIconImage(img);

		JPanel jpanel_label = new JPanel();
		jpanel_label.setOpaque(false);
		jpanel_label.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

		Font font_1 = new Font("Arial", Font.BOLD, 20);
		Font font_2 = new Font("Arial", Font.BOLD, 15);

		JLabel jlabel_header = new JLabel("THÔNG TIN BIÊN LAI", SwingConstants.CENTER);
		jlabel_header.setFont(font_1);
		jlabel_header.setForeground(Color.BLUE);

		ImageIcon iconHeader = new ImageIcon(getClass().getResource("/ICon/iconBienLai.png"));
		JLabel imageLabel = new JLabel(iconHeader);

		jpanel_label.add(imageLabel);
		jpanel_label.add(jlabel_header);

		jpanel_info = new JPanel();
		GridLayout layout = new GridLayout(2, 2, 0, 25);
                jpanel_info.setLayout(layout);
                jpanel_info.setBorder(new EmptyBorder(10, 20, 10, 20));
                
		jlabel_maToa = new JLabel("Mã toa:");
		jlabel_maToa.setFont(font_2);
		jtextfield_maToa = new JTextField();
		jtextfield_maToa.setFont(font_2);
                jtextfield_maToa.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        double tongTienKham = dsbl.getTongTienKham(jtextfield_maToa.getText());
                        if (tongTienKham > 0) {
                            jtextfield_tongTienKham.setText(Double.toString(tongTienKham));
                            jtextfield_maToa.transferFocus();
                        }
                        else {
                            JOptionPane.showMessageDialog(qlbl, "Mã toa không tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });
                
                jlabel_tongTienKham = new JLabel("Tổng tiền(đồng):");
		jlabel_tongTienKham.setFont(font_2);
		jtextfield_tongTienKham = new JTextField();
		jtextfield_tongTienKham.setFont(font_2);
                jtextfield_tongTienKham.enable(false);

		jpanel_info.add(jlabel_maToa);
		jpanel_info.add(jtextfield_maToa);
                jpanel_info.add(jlabel_tongTienKham);
		jpanel_info.add(jtextfield_tongTienKham);            

		jbutton_them = new JButton("Thêm");
		jbutton_them.setFont(font_2);
	//	jbutton_them.setBackground(Color.WHITE);

		JPanel jpanel_bottom = new JPanel();
		jpanel_bottom.setLayout(new GridLayout(1, 2));
		jpanel_bottom.add(jbutton_them);
		jbutton_them.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addBienLaiDTOVaoTable();
				
				switchToAddBienLaiGUI();
			}
		});		
	
		
		
		this.setLayout(new BorderLayout());
		this.add(jpanel_label, BorderLayout.NORTH);
		this.add(jpanel_info, BorderLayout.CENTER);
		this.add(jpanel_bottom, BorderLayout.SOUTH);
                
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        jtextfield_maToa.setText("");
                        setVisible(false);
                    }
                });

	}
        
        public void ClearForm() {
            jtextfield_maToa.setText("");
            jtextfield_tongTienKham.setText("");
        }

	public void addBienLaiDTOVaoTable() {
	    String maToa = this.jtextfield_maToa.getText();

	    if (maToa.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập mã toa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                this.setVisible(true);
	    }
            else {
                boolean success = dsbl.addBienLaiDTO(maToa);

                if (success) {
                    qlbl.fillData(); // Sau khi them thi goi fetch data lai
                    JOptionPane.showMessageDialog(this, "Thêm biên lai thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    ClearForm();
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(this, "Biên lai đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            }
	}

	public void switchToAddBienLaiGUI() {
		qlbl.setVisible(true);
		setVisible(false);
                ClearForm();
	}

	public static void main(String agru[]) {
            QLyBienLaiGUI qlbl = new QLyBienLaiGUI();
            Them_Sua_BienLaiGUI bl = new Them_Sua_BienLaiGUI(qlbl);
	}

}