package custom;

import javax.swing.*;

import DTO.Thuoc;

import java.awt.*;
import java.util.function.Supplier;

public class CustomComboRenderer extends DefaultListCellRenderer {
    public static final Color background = new Color(225, 240, 255);
    private static final Color defaultBackground = (Color) UIManager.get("List.background");
    private static final Color defaultForeground = (Color) UIManager.get("List.foreground");
    private Supplier<String> highlightTextSupplier;

    public CustomComboRenderer(Supplier<String> highlightTextSupplier) {
        this.highlightTextSupplier = highlightTextSupplier;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
    		boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        Thuoc drug = (Thuoc) value;
        if (drug == null) { 
        	

        	
            return this;
        }
        String text = getThuocDisplayText(drug);
        text = HtmlHighlighter.highlightText(text, highlightTextSupplier.get());
        this.setText(text);
        if (!isSelected) {
            this.setBackground(index % 2 == 0 ? background : defaultBackground);
        }
        this.setForeground(defaultForeground);
        return this;
    }

    public static String getThuocDisplayText(Thuoc drug) {
        if (drug == null) {
            return "";
        }
        return String.format("%s [%s]", drug.getTenThuoc(), drug.getId());
    }
}