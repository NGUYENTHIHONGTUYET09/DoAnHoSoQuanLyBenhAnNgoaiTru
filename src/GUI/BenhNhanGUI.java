package GUI;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import DTO.BenhNhan;
import DTO.TaiKhoan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import DAO.TaiKhoanDAO;
public class BenhNhanGUI extends JFrame {
    public JTable jtable_table;
    private BenhNhan bn;
public TaiKhoanDAO dkd;

    public BenhNhanGUI(BenhNhan bn) {
    	this.bn = bn;
        init();
        setVisible(true);
    }

    public void init() {
        setTitle("Thông tin bệnh nhân");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        URL urlIconNotepad = BenhNhanGUI.class.getResource("/ICon/iconPeople.png");
        Image img = Toolkit.getDefaultToolkit().createImage(urlIconNotepad);
        this.setIconImage(img);

        JPanel jpanel_label = new JPanel();
        jpanel_label.setOpaque(false);
        jpanel_label.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        Font font_1 = new Font("Arial", Font.BOLD, 30);

        JLabel jlabel_header = new JLabel("Thông tin bệnh nhân", SwingConstants.CENTER);
        jlabel_header.setFont(font_1);
        jlabel_header.setForeground(Color.RED);

        ImageIcon iconHeader = new ImageIcon(getClass().getResource("/ICon/iconPeople.png"));
        JLabel imageLabel = new JLabel(iconHeader);

        jpanel_label.add(imageLabel);
        jpanel_label.add(jlabel_header);

        Font font = new Font("Arial", Font.BOLD, 20);

        JButton jbutton_pkb = new JButton("Phiếu khám bệnh");
        ImageIcon iconPKB = new ImageIcon(getClass().getResource("/ICon/iconPKB.png"));
        jbutton_pkb.setIcon(iconPKB);
        jbutton_pkb.setBackground(Color.WHITE);
        jbutton_pkb.setFont(font);

        jbutton_pkb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                try {
//                    String filePath = "danhsachbenh_nhan.pdf";
//                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
//                        Desktop.getDesktop().open(new File(filePath));
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Máy tính không hỗ trợ mở tệp PDF.");
//                    }
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(null, "Lỗi khi mở tệp PDF.");
//                }
                new CTBenhNhanGUI(bn).setVisible(true); 
            }
        });

        JButton jbutton_toathuoc = new JButton("Toa thuốc");
        jbutton_toathuoc.setBackground(Color.WHITE);
        ImageIcon iconToathuoc = new ImageIcon(getClass().getResource("/ICon/iconThuoc.png"));
        jbutton_toathuoc.setIcon(iconToathuoc);
        jbutton_toathuoc.setFont(font);

        JButton jbutton_bienlai = new JButton("Biên lai");
        jbutton_bienlai.setBackground(Color.WHITE);
        ImageIcon iconBienlai = new ImageIcon(getClass().getResource("/ICon/iconBienlai.png"));
        jbutton_bienlai.setIcon(iconBienlai);
        jbutton_bienlai.setFont(font);

        JPanel jpanel_jbutton = new JPanel();
        jpanel_jbutton.setLayout(new GridLayout(1, 3));
        jpanel_jbutton.add(jbutton_pkb);
        jpanel_jbutton.add(jbutton_toathuoc);
        jpanel_jbutton.add(jbutton_bienlai);

        // Tạo JTable và JScrollPane
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã bệnh nhân");
        model.addColumn("Tên bệnh nhân");
        model.addColumn("Sdt");
        model.addColumn("Ngày sinh");
        model.addColumn("Địa chỉ");
        model.addColumn("Quê quán");
        model.addColumn("Giới tính");
        model.addColumn("Ghi chú");

        model.addRow(new Object[] {
        		bn.getMaBN(),
        		bn.getTenBN(),
        		bn.getSdt(),
        		bn.getNgaySinh().toString(),
        		bn.getDiaChi(),
        		bn.getQueQuan(),
        		bn.getGioiTinh(),
        		bn.getGhiChu()
        });
        
        jtable_table = new JTable(model);
        jtable_table.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        JScrollPane jscrollpane_table = new JScrollPane(jtable_table);

        JPanel jpanel_info = new JPanel(new BorderLayout());



        jpanel_info.add(jscrollpane_table, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        add(jpanel_label, BorderLayout.NORTH);
        add(jpanel_info, BorderLayout.CENTER);
        add(jpanel_jbutton, BorderLayout.SOUTH);

    }
    
   
}


