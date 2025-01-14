

package GUI;

import BUS.BenhNhanService;
import BUS.CTDonThuocService;
import BUS.NhanVienService;
import DTO.PhieuKhamBenh;
import BUS.PhieuKhamBenhService;
import BUS.PhongKhamService;
import BUS.ThuocService;
import BUS.ToaThuocService;
import DTO.BenhNhan;
import DTO.CTDonThuoc;
import DTO.NhanVien;
import DTO.PhongKham;
import DTO.Thuoc;
import DTO.ToaThuoc;
import interfaces.AddListThuocInterface;
import java.util.ArrayList;

//import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class KeToa extends javax.swing.JFrame implements AddListThuocInterface{
    PhieuKhamBenhService phieuKhamBenhService;
    PhieuKhamBenh phieuKhamBenh;
    
    NhanVien nhanVien;
    NhanVienService nhanVienService;
    
    BenhNhan benhNhan;
    BenhNhanService benhNhanService;
    
    PhongKham phongKham;
    PhongKhamService phongKhamService;
    
    ToaThuoc toaThuoc;
    ToaThuocService toaThuocService;
    
    CTDonThuoc cTDonThuoc;
    CTDonThuocService cTDonThuocService;
    
    DefaultTableModel defaultTableModel;
    
    ThuocService thuocService;
    
 
    public KeToa(int pkbID) {
        initComponents();
        this.setLocationRelativeTo(null);
        
        phieuKhamBenhService = new PhieuKhamBenhService();       
        phieuKhamBenh = phieuKhamBenhService.getPhieuKhamBenhByID(pkbID);
        
        toaThuoc = new ToaThuoc();
        toaThuocService = new ToaThuocService(); 
        
        cTDonThuoc = new CTDonThuoc();
        cTDonThuocService = new CTDonThuocService();
        
        thuocService = new ThuocService();
        
        phongKhamService = new PhongKhamService();
        
        String maPKB = phieuKhamBenh.getMAPKB();
        System.out.println(maPKB);
        
        int maBS = phieuKhamBenh.getMABS();
        System.out.println(maBS);
        
        benhNhanService = new BenhNhanService();
        int maBN = phieuKhamBenh.getMABN();
        benhNhan = benhNhanService.getBenhNhanByID(maBN);
        String tenBN = benhNhan.getTenBN();
        
        int maPK = phieuKhamBenh.getMAPK();
        PhongKham phongKham = phongKhamService.getPhongKhamByID(maPK);
        String tenPK = phongKham.getTenPK();
        
        Date ngayTao = phieuKhamBenh.getNGAYTAO();
        
        jTextField1.setText(phieuKhamBenh.getMAPKB());
        
//        maBSCb.addItem(String.valueOf(maBS));
//        maBSCb.setSelectedItem(maBS);
        nhanVienService = new  NhanVienService();
        ArrayList <NhanVien> nhanViens = nhanVienService.getAllBacSis();
        
        int nhanViensSize = nhanViens.size();
        int[] bacSiIDs = new int[nhanViensSize];
        for(int i = 0; i < nhanViensSize; i++){
            bacSiIDs[i] = nhanViens.get(i).getId();
            maBSCb.addItem(String.valueOf(bacSiIDs[i]));
        }

        maBNTextField.setText(String.valueOf(maBN));       
//        maBNComboBox.setSelectedItem(phieuKhamBenh.getMABN());
        tenBNTextField.setText(tenBN);
        PKComboBox.addItem(tenPK);
//        PKComboBox.setSelectedItem(phieuKhamBenh.getMAPK());

        jDateChooser1.setDate(phieuKhamBenh.getNGAYTAO());
        jTextArea1.setText(phieuKhamBenh.getCHANDOAN());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backPKBBt = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        maPKLb = new javax.swing.JLabel();
        maBSCb = new javax.swing.JComboBox<>();
        PKComboBox = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ctToaThuocTable = new javax.swing.JTable();
        tongHoaDonLB = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        maKBLb = new javax.swing.JLabel();
        maBSLb = new javax.swing.JLabel();
        maBNLb = new javax.swing.JLabel();
        ngayTaoLb = new javax.swing.JLabel();
        chanDoanLb = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        addPKBBt = new javax.swing.JButton();
        tongHoaDonTextField = new javax.swing.JTextField();
        taoToaThuocBT = new javax.swing.JButton();
        pkbIDTextField = new javax.swing.JTextField();
        pknIDLB = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        toaThuocID = new javax.swing.JLabel();
        toaThuocIDTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tenBNTextField = new javax.swing.JTextField();
        maBNTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        backPKBBt.setText("Quay Lại");
        backPKBBt.setToolTipText("");
        backPKBBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backPKBBtActionPerformed(evt);
            }
        });

        jDateChooser1.setBackground(new java.awt.Color(204, 204, 204));
        jDateChooser1.setEnabled(false);

        maPKLb.setText("Mã PK");

        PKComboBox.setBackground(new java.awt.Color(204, 204, 204));

        jButton1.setText("Thêm Toa Thuốc");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ctToaThuocTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null,null},
                {null, null, null, null,null},
                {null, null, null, null,null},
                {null, null, null, null,null}
            },
            new String [] {
            		"STT", "Ma Thuoc", "Ten Thuoc", "So luong", "Ghi Chu"
            }
        ));
        jScrollPane2.setViewportView(ctToaThuocTable);

        tongHoaDonLB.setText("Tổng hoá đơn");

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setText("Kê Toa Thuốc");

        maKBLb.setText("Mã Khám Bệnh");

        maBSLb.setText("Mã Bác Sĩ");

        maBNLb.setText("Mã Bệnh Nhân");

        ngayTaoLb.setText("Ngày tạo");

        chanDoanLb.setText("Chẩn đoán");

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(204, 204, 204));
        jTextField1.setEnabled(false);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        addPKBBt.setText("Thêm chẩn đoán ");
        addPKBBt.setToolTipText("");
        addPKBBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPKBBtActionPerformed(evt);
            }
        });

        taoToaThuocBT.setText("Tạo toa thuốc");
        taoToaThuocBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taoToaThuocBTActionPerformed(evt);
            }
        });

        pknIDLB.setText("PKB ID");

        jButton2.setText("Tạo CTToaThuoc");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        toaThuocID.setText("Mã Toa");

        jLabel2.setText("Tên bệnh nhân");

        tenBNTextField.setEditable(false);
        tenBNTextField.setBackground(new java.awt.Color(204, 204, 204));

        maBNTextField.setEditable(false);
        maBNTextField.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(237, 237, 237))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(108, 108, 108)
                                    .addComponent(maPKLb))
                                .addComponent(ngayTaoLb, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(PKComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(taoToaThuocBT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(chanDoanLb)
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(pknIDLB)
                                    .addGap(18, 18, 18)
                                    .addComponent(pkbIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(tongHoaDonLB)
                                    .addGap(18, 18, 18)
                                    .addComponent(tongHoaDonTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(toaThuocID)
                                        .addComponent(jButton1))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(toaThuocIDTextField)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(backPKBBt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addPKBBt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(maBNLb)
                            .addComponent(maBSLb)
                            .addComponent(maKBLb)
                            .addComponent(jLabel2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(maBSCb, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tenBNTextField)
                                    .addComponent(maBNTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))))))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maKBLb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maBSCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maBSLb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maBNTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maBNLb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tenBNTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PKComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maPKLb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chanDoanLb)))
                    .addComponent(ngayTaoLb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addPKBBt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pkbIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pknIDLB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(taoToaThuocBT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toaThuocIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toaThuocID))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tongHoaDonTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tongHoaDonLB))
                .addGap(15, 15, 15)
                .addComponent(backPKBBt)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }

    private void backPKBBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backPKBBtActionPerformed

        this.dispose();
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        defaultTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String[] columns = {"STT", "Ma Thuoc", "Ten Thuoc", "So luong", "Ghi Chu"};
        defaultTableModel.setColumnIdentifiers(columns);

        ctToaThuocTable.setModel(defaultTableModel);

        new CreateDonThuoc(this).setVisible(true);
    }

    private void addPKBBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPKBBtActionPerformed
       try {
            
            phieuKhamBenh.setMABS(Integer.parseInt(maBSCb.getSelectedItem().toString()));
            phieuKhamBenh.setMABN(Integer.parseInt(maBNTextField.getText()));
//            phieuKhamBenh.setMAPK(Integer.parseInt(PKComboBox.getSelectedItem().toString()));

            Date ngayTao = jDateChooser1.getDate();
            java.sql.Date sqlDate = new java.sql.Date(ngayTao.getTime());
            phieuKhamBenh.setNGAYTAO(sqlDate);

            phieuKhamBenh.setCHANDOAN(jTextArea1.getText());

            phieuKhamBenhService.suaPhieuKhamBenh(phieuKhamBenh);


//            new QuanLyThongTinKhamBenh().setVisible(true);
//            this.dispose();
            JOptionPane.showMessageDialog(this, "Thêm chần đoán bệnh thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            
//            phieuKhamBenh = phieuKhamBenhService.getlastPhieuKhamBenh();
            
            pkbIDTextField.setText(String.valueOf(phieuKhamBenh.getId()));
            
        } catch (NumberFormatException ex) {
       
            
        }
    }

    private void taoToaThuocBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taoToaThuocBTActionPerformed
        // TODO add your handling code here:
        toaThuoc = new ToaThuoc();
        toaThuocService = new ToaThuocService();
        
        int maBs = Integer.parseInt(maBSCb.getSelectedItem().toString());
        int maBn = Integer.parseInt(maBNTextField.getText());
        int maPKB = Integer.parseInt(pkbIDTextField.getText());

        //        double tongTien = Double.parseDouble(tongHoaDonTextField.getText());
        Date ngayTao = jDateChooser1.getDate();
        java.sql.Date sqlDate = new java.sql.Date(ngayTao.getTime());

        toaThuoc.setMaBS(maBs);
        toaThuoc.setMaBN(maBn);
        toaThuoc.setTongTien(0);
        toaThuoc.setNgayLap(sqlDate);
        toaThuoc.setMaPKB(maPKB);

        // Add this line in your constructor or before use
        int rs = toaThuocService.addToaThuoc(toaThuoc);
        if (rs != 0){
            System.out.println("ToaThuoc added successfully.");
        }
        else
        {
            System.out.println("ToaThuoc added fail.");
        }

        toaThuoc = toaThuocService.getlastToaThuoc();
        int toaThuocID = toaThuoc.getId();

        toaThuocIDTextField.setText(String.valueOf(toaThuocID));
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        DefaultTableModel ctDonThuocModel = (DefaultTableModel)ctToaThuocTable.getModel();

        if (ctDonThuocModel.getRowCount() == 0){
            JOptionPane.showMessageDialog(this, "Bảng đơn thuốc trống");
        }
        else {
            for (int i = 0; i < ctDonThuocModel.getRowCount(); i++)
            {
                int maThuoc = Integer.parseInt(ctDonThuocModel.getValueAt(i, 1).toString());
                cTDonThuoc.setMaThuoc(maThuoc);

                int maToa = Integer.parseInt(toaThuocIDTextField.getText());
                cTDonThuoc.setMaToa(maToa);

                int soLuong = Integer.parseInt(ctDonThuocModel.getValueAt(i, 3).toString());
                cTDonThuoc.setSoLuong(soLuong);

                cTDonThuocService.addCTDT(cTDonThuoc);
                System.out.println("Add 1 to CTDonThuoc");
            }
        }

        int maToa = Integer.parseInt(toaThuocIDTextField.getText());
        double tongTien = toaThuocService.tinhTongTien(maToa);

        tongHoaDonTextField.setText(String.valueOf(tongTien));

        toaThuocService.updateTongTien(maToa, tongTien);
    }

 


   
    private javax.swing.JComboBox<String> PKComboBox;
    private javax.swing.JButton addPKBBt;
    private javax.swing.JButton backPKBBt;
    private javax.swing.JLabel chanDoanLb;
    private javax.swing.JTable ctToaThuocTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel maBNLb;
    private javax.swing.JTextField maBNTextField;
    private javax.swing.JComboBox<String> maBSCb;
    private javax.swing.JLabel maBSLb;
    private javax.swing.JLabel maKBLb;
    private javax.swing.JLabel maPKLb;
    private javax.swing.JLabel ngayTaoLb;
    private javax.swing.JTextField pkbIDTextField;
    private javax.swing.JLabel pknIDLB;
    private javax.swing.JButton taoToaThuocBT;
    private javax.swing.JTextField tenBNTextField;
    private javax.swing.JLabel toaThuocID;
    private javax.swing.JTextField toaThuocIDTextField;
    private javax.swing.JLabel tongHoaDonLB;
    private javax.swing.JTextField tongHoaDonTextField;

    private List<CTDonThuoc> selectedListThuoc;
    @Override
    public void addListThuoc(List<CTDonThuoc> listThuoc) {
        selectedListThuoc = listThuoc;
	    DefaultTableModel defaultTableModel = (DefaultTableModel) ctToaThuocTable.getModel();
	    defaultTableModel.setRowCount(0);
	    for (int i = 0; i < selectedListThuoc.size(); i++) {
                Thuoc thuoc = thuocService.getThuocByID(selectedListThuoc.get(i).getMaThuoc());
                
	        defaultTableModel.addRow(new Object[] {
	            String.valueOf(i),
	            selectedListThuoc.get(i).getMaThuoc(),
                    thuoc.getTenThuoc(),
	            selectedListThuoc.get(i).getSoLuong(),
	            selectedListThuoc.get(i).getGhiChu()
	        });
	    }
    }


}
