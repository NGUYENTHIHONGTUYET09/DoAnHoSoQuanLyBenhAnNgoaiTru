package GUI;

import javax.swing.JFrame;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import BUS.ThuocService;
import DTO.CTDonThuoc;
import DTO.Thuoc;
import custom.ComboBoxFilterDecorator;
import custom.CustomComboRenderer;
import interfaces.AddListThuocInterface;
import interfaces.NewTableInterface;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class CreateDonThuoc extends JFrame implements NewTableInterface{

    private static final long serialVersionUID = 1L;
    private JTable table;
    private JButton btnCancel, btnOk;
    private JComboBox<Thuoc> cbThuoc;
    private List<Thuoc> listThuoc;
    private ThuocService thuocService;
    private Thuoc selectedThuoc;
    private List<CTDonThuoc> listCTDonThuoc = new ArrayList<CTDonThuoc>();
    private AddListThuocInterface addlistThuocInterface;

    /**
     * Launch the application.
     */
    /**
     * Create the frame.
     */
    public CreateDonThuoc(AddListThuocInterface addlistThuocInterface) {

        this.addlistThuocInterface = addlistThuocInterface;
        thuocService = new ThuocService();
        listThuoc = thuocService.getAllThuocs();
        initView();
    }

    private void initView() {
        setBounds(100, 100, 450, 440);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Tìm thuốc");
        lblNewLabel.setBounds(24, 14, 66, 17);
        getContentPane().add(lblNewLabel);

        JLabel lblDanhSch = new JLabel("Danh sách");
        lblDanhSch.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblDanhSch.setBounds(24, 63, 390, 30);
        getContentPane().add(lblDanhSch);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(24, 104, 390, 252);
        getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"STT", "Tên thuốc", "Số lượng", "Ghi chú"}
        ) {
            boolean[] columnEditables = new boolean[]{false, false, true, true};

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });

        btnCancel = new JButton("cancel");
        btnCancel.setBounds(217, 367, 89, 23);
        getContentPane().add(btnCancel);

        btnOk = new JButton("ok");
        btnOk.setBounds(325, 367, 89, 23);
        getContentPane().add(btnOk);

        cbThuoc = new JComboBox<Thuoc>(listThuoc.toArray(new Thuoc[listThuoc.size()]));
        cbThuoc.setEditable(true);
        cbThuoc.setBounds(79, 11, 335, 22);

        ComboBoxFilterDecorator<Thuoc> decorate = ComboBoxFilterDecorator.decorate(cbThuoc,
                CustomComboRenderer::getThuocDisplayText, this::thuocFilter,this);

        cbThuoc.setRenderer(new CustomComboRenderer(decorate.getFilterTextSupplier()));
//        cbThuoc.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            
//                selectedThuoc = (Thuoc) cbThuoc.getSelectedItem();
//                listCTDonThuoc.add(new CTDonThuoc(selectedThuoc.getId(), selectedThuoc.getTenThuoc()));
//                resetTable();
//            }
//        });
       

        getContentPane().add(cbThuoc);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setResizable(false);

        listenEvents();

    }

    private boolean thuocFilter(Thuoc thuoc, String textToFilter) {
        if (textToFilter.isEmpty()) {
            return true;
        }
        return CustomComboRenderer.getThuocDisplayText(thuoc).toLowerCase().contains(textToFilter.toLowerCase());
    }

    private void resetTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (int i = 0; i < listCTDonThuoc.size(); i++) {
            model.addRow(new Object[]{String.valueOf(i), listCTDonThuoc.get(i).getTenThuoc(), listCTDonThuoc.get(i).getSoLuong(), listCTDonThuoc.get(i).getGhiChu()});
        }
    }

    private void listenEvents() {
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performInsert();
            }
        });

        table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                TableModel model = (TableModel) e.getSource();
                if (row >= 0 && column >= 0) {
                    Object data = model.getValueAt(row, column);
                    CTDonThuoc ctDonThuoc = listCTDonThuoc.get(row);
                    switch (column) {
                        case 2:
                            try {
                                int soLuong = Integer.parseInt(data.toString());
                                ctDonThuoc.setSoLuong(soLuong);
                            } catch (NumberFormatException ex) {
                                System.err.println("Invalid number format");
                            }
                            break;
                        case 3:
                            ctDonThuoc.setGhiChu(data.toString());
                            break;
                    }
                }
            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void performInsert() {

        if (checkValid(listCTDonThuoc)) {
            addlistThuocInterface.addListThuoc(listCTDonThuoc);
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Khong du so luong thuoc", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    boolean checkValid(List<CTDonThuoc> listDT){
        for(Thuoc thuoc : listThuoc){
            for(CTDonThuoc thuocKe : listCTDonThuoc){
                if(thuocKe.getMaThuoc() == thuoc.getId() && thuocKe.getSoLuong() > thuoc.getSoLuongTon()){
                    return false;
                }
            }
        }
        return true;
    }


	@Override
	public void addThuocToCreateDoNThuoc(Thuoc thuoc) {
		listCTDonThuoc.add(new CTDonThuoc(thuoc.getId(),thuoc.getTenThuoc()));
		listCTDonThuoc.forEach(t -> System.out.println(t.toString()));
        resetTable();
	}
}