package Report;

import DAO.TinhDAO;
import DTO.BenhNhan;
import DTO.BienLaiDTO;
import DTO.Tinh;
import Report.dtos.ReportDTO;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class QuanLyThongKeController {

    private ThongKeServiceImpl thongKeService;
    private ThongKeServiceImplDoanhThu thongKeServiceDoanhThu;

    public QuanLyThongKeController(ThongKeServiceImpl thongKeService) {
        this.thongKeService = thongKeService;
    }

    public QuanLyThongKeController(ThongKeServiceImplDoanhThu thongKeServiceDoanhThu) {
        this.thongKeServiceDoanhThu = thongKeServiceDoanhThu;
    }

    public void setDataToColChart(JPanel jpnItem) {
        List<ReportDTO> listItem = thongKeService.getDSGroupedByQueQuan();
        
        if (listItem != null && !listItem.isEmpty()) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (ReportDTO bnItem : listItem) {
                dataset.addValue(bnItem.getValue(), "Bệnh Nhân", bnItem.getName());
            }

            JFreeChart chart = ChartFactory.createBarChart(
                "THỐNG KÊ SỐ LƯỢNG BỆNH NHÂN", 
                "Quê Quán", 
                "Số Lượng", 
                dataset
            );
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 300));

            jpnItem.removeAll();
            jpnItem.setLayout(new CardLayout());
            jpnItem.add(chartPanel);
            jpnItem.validate();
            jpnItem.repaint();
        }
    }

    private PieDataset createPieChartDataSet(List<ReportDTO> listBN) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (ReportDTO item : listBN) {
            dataset.setValue(item.getName(), item.getValue());
        }
        return dataset;
    }

    public void setDataToPieChart(JPanel jpnItem) {
        PieDataset dataset = createPieChartDataSet(thongKeService.getDSGroupedByQueQuan());
        JFreeChart piechart = ChartFactory.createPieChart(
                "Pie Chart Example", 
                dataset, 
                true, 
                true, 
                false
        );

        // Lấy PiePlot từ PieChart để cấu hình hiển thị phần trăm
        PiePlot plot = (PiePlot) piechart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})"));

        ChartPanel chartPanel = new ChartPanel(piechart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 300));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }
    public void setDataDoanhThuToColChart(JPanel jpnItem) {
        List<ReportDTO> listItem = thongKeServiceDoanhThu.getDoanhThuByYear();
        System.out.println(listItem);
        if (listItem != null && !listItem.isEmpty()) {

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (ReportDTO blItem : listItem) {
                dataset.addValue(blItem.getMoney(), "Doanh Thu", blItem.getYear());
            }

            JFreeChart chart = ChartFactory.createBarChart(
                "THỐNG KÊ DOANH THU THEO NĂM",
                "Năm",
                "Doanh Thu (VND)",
                dataset
            );

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 500));
            jpnItem.removeAll();
            jpnItem.setLayout(new BorderLayout());
            jpnItem.add(chartPanel, BorderLayout.CENTER);
            jpnItem.validate();
            jpnItem.repaint();
        } else {
            // Nếu không có số liệu, chèn hình ảnh vào panel
            ImageIcon iconHeader = new ImageIcon(getClass().getResource("/ICon/iconNoMoney.png"));
            JLabel noDataLabel = new JLabel("CHƯA CÓ DOANH THU", iconHeader, JLabel.CENTER);

            noDataLabel.setIcon(iconHeader);

            jpnItem.removeAll();
            jpnItem.setLayout(new BorderLayout());
            jpnItem.add(noDataLabel, BorderLayout.NORTH); // Đặt JLabel ở vị trí NORTH
            jpnItem.validate();
            jpnItem.repaint();
        }
    }

}
