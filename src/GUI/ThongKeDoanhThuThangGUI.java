package GUI;

import BUS.DSBienLaiBUS;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author HUNG
 */
public class ThongKeDoanhThuThangGUI extends javax.swing.JFrame {
    
    
    private DSBienLaiBUS blBUS;
    private DefaultCategoryDataset dataset;
    private JFreeChart chart;
    private CategoryPlot p;
    private ChartPanel chartPanel;
    private boolean isCoDoanhThu;
    private String nam;
    
    public ThongKeDoanhThuThangGUI() {
        initComponents();
        blBUS = new DSBienLaiBUS();
        dataset = new DefaultCategoryDataset();
        p = new CategoryPlot();
        
        
        ResetUI();
        
        
        //lam cho trang chu admin khong bi close
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    public void ResetUI() {
        ResetChart();
        if (isCoDoanhThu) {
            // Có doanh thu
            jPanel1.remove(lblNoMoney);
            jPanel1.remove(imageLabel);
            jPanel1.add(chartPanel);
        } else {
            // Không có doanh thu
            jPanel1.removeAll();
            jPanel1.setSize(1100, 928);
            lblNoMoney.setText("CHƯA CÓ DOANH THU TRONG NĂM " + nam);
            ImageIcon iconHeader = new ImageIcon(getClass().getResource("/ICon/iconNoMoney.png"));
            imageLabel.setIcon(iconHeader);
            jPanel1.add(lblNoMoney);
            jPanel1.add(imageLabel);// Thêm imageLabel
        }
    }
    
    public void ResetChart() {
        nam = cbboxNamSelection.getSelectedItem().toString();
        lblTieuDe.setText("THỐNG KÊ DOANH THU NĂM " + nam);
        ArrayList<Double> doanhThuTungThang = blBUS.getDoanhThuThang(Integer.parseInt(nam));
        
        isCoDoanhThu = false;
        int thang = 1;
        for (double dt : doanhThuTungThang) {
            dataset.setValue(dt, "Doanh thu", "" + thang);
            thang++;
            if (dt - 0 > 1e-9) isCoDoanhThu = true;
        }
        
        
        if (isCoDoanhThu == true) {
            chart = ChartFactory.createBarChart(
                    "",
                    "Tháng",
                    "Doanh thu(VNĐ)",
                    dataset,
                    PlotOrientation.VERTICAL,
                    false,
                    true,
                    false);
            p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
            chartPanel = new ChartPanel(chart);
            chartPanel.setDomainZoomable(true);
            chartPanel.setVisible(true);
            chartPanel.setSize(990, 540);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        cbboxNamSelection = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        lblNoMoney = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1050, 700));

        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblTieuDe.setText("THỐNG KÊ DOANH THU NĂM ");

        cbboxNamSelection.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cbboxNamSelection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2021", "2022", "2023", "2024" }));
        cbboxNamSelection.setSelectedIndex(3);
        cbboxNamSelection.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbboxNamSelectionItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbboxNamSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(172, 172, 172)
                .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(251, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbboxNamSelection, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(lblTieuDe))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 928));

        lblNoMoney.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblNoMoney.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNoMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(264, 264, 264))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(234, 234, 234))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblNoMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(389, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbboxNamSelectionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbboxNamSelectionItemStateChanged
        ResetUI();
    }//GEN-LAST:event_cbboxNamSelectionItemStateChanged

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKeDoanhThuThangGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbboxNamSelection;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblNoMoney;
    private javax.swing.JLabel lblTieuDe;
    // End of variables declaration//GEN-END:variables
}