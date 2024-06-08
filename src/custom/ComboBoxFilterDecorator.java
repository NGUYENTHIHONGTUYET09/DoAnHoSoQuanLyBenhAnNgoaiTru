package custom;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import DTO.CTDonThuoc;
import DTO.Thuoc;
import interfaces.AddListThuocInterface;
import interfaces.NewTableInterface;

public class ComboBoxFilterDecorator<Thuoc> {
    private JComboBox<Thuoc> comboBox;
    private BiPredicate<Thuoc, String> userFilter;
    private Function<Thuoc, String> comboDisplayTextMapper;
    java.util.List<Thuoc> originalItems;
    private Thuoc selectedItem;
    private FilterEditor filterEditor;
    private NewTableInterface listThuocInterface;

    public ComboBoxFilterDecorator(JComboBox<Thuoc> comboBox,
                                   BiPredicate<Thuoc, String> userFilter,
                                   Function<Thuoc, String> comboDisplayTextMapper,
                                   NewTableInterface listThuocInterface) {
        this.comboBox = comboBox;
        this.userFilter = userFilter;
        this.comboDisplayTextMapper = comboDisplayTextMapper;
        this.listThuocInterface = listThuocInterface;
    }

    public static <T> ComboBoxFilterDecorator<T> decorate(JComboBox<T> comboBox,
                                                          Function<T, String> comboDisplayTextMapper,
                                                          BiPredicate<T, String> userFilter,
                                                          NewTableInterface listThuocInterface) {
        ComboBoxFilterDecorator decorator =
                new ComboBoxFilterDecorator(comboBox, userFilter, comboDisplayTextMapper,listThuocInterface);
        decorator.init();
        return decorator;
    }

    private void init() {
        prepareComboFiltering();
        initComboPopupListener();
        initComboKeyListener();
    }

    private void prepareComboFiltering() {
        DefaultComboBoxModel<Thuoc> model = (DefaultComboBoxModel<Thuoc>) comboBox.getModel();
        this.originalItems = new ArrayList<>();
        for (int i = 0; i < model.getSize(); i++) {
            this.originalItems.add(model.getElementAt(i));
        }


        filterEditor = new FilterEditor(comboDisplayTextMapper, new Consumer<Boolean>() {
            //editing mode (commit/cancel) change listener
            @Override
            public void accept(Boolean aBoolean) {
                if (aBoolean) {//commit
                    selectedItem = (Thuoc) comboBox.getSelectedItem();
                } else {//rollback to the last one
                    comboBox.setSelectedItem(selectedItem);
                    filterEditor.setItem(selectedItem);
                }
            }
        });

        JLabel filterLabel = filterEditor.getFilterLabel();
        filterLabel.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                filterLabel.setBorder(BorderFactory.createLoweredBevelBorder());
            }

            @Override
            public void focusLost(FocusEvent e) {
                filterLabel.setBorder(UIManager.getBorder("TextField.border"));
                resetFilterComponent();
            }
        });
        comboBox.setEditor(filterEditor);
        comboBox.setEditable(true);
    }

    private void initComboKeyListener() {
        filterEditor.getFilterLabel().addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        char keyChar = e.getKeyChar();
                        if (!Character.isDefined(keyChar)) {
                            return;
                        }
                        int keyCode = e.getKeyCode();
                        
                        switch (keyCode) {
                            case KeyEvent.VK_DELETE:
                                return;
                            case KeyEvent.VK_ENTER:
                            	selectedItem = (Thuoc) comboBox.getSelectedItem();
                                resetFilterComponent();
                                listThuocInterface.addThuocToCreateDoNThuoc((DTO.Thuoc) selectedItem); //Cho nay ok
                                return;
                            case KeyEvent.VK_ESCAPE:
                                resetFilterComponent();
                                return;
                            	
                            case KeyEvent.VK_BACK_SPACE:
                                filterEditor.removeCharAtEnd();
                                break;
                            
                            default:
                                filterEditor.addChar(keyChar);
                        }
                        if (!comboBox.isPopupVisible()) {
                            comboBox.showPopup();
                        }
                        if (filterEditor.isEditing() && filterEditor.getText().length() > 0) {
                            applyFilter();
                        } else {
                            comboBox.hidePopup();
                            resetFilterComponent();
                        }
                    }
                }
        );
        }
    

    public Supplier<String> getFilterTextSupplier() {
        return () -> {
            if (filterEditor.isEditing()) {
                return filterEditor.getFilterLabel().getText();
            }
            return "";
        };
    }

    private void initComboPopupListener() {
        comboBox.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                resetFilterComponent();
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                resetFilterComponent();
            }
        });
        
        comboBox.addActionListener(a -> {
        	selectedItem = (Thuoc) comboBox.getSelectedItem();
            resetFilterComponent();
            listThuocInterface.addThuocToCreateDoNThuoc((DTO.Thuoc) selectedItem); //Cho nay ok
        });
    }

    private void resetFilterComponent() {
        if (!filterEditor.isEditing()) {
            return;
        }
        //restore original order
        DefaultComboBoxModel<Thuoc> model = (DefaultComboBoxModel<Thuoc>) comboBox.getModel();
        model.removeAllElements();
        for (Thuoc item : originalItems) {
            model.addElement(item);
        }
        filterEditor.reset();
    }

    private void applyFilter() {
        DefaultComboBoxModel<Thuoc> model = (DefaultComboBoxModel<Thuoc>) comboBox.getModel();
        model.removeAllElements();
        java.util.List<Thuoc> filteredItems = new ArrayList<>();
        //add matched items at top
        for (Thuoc item : originalItems) {
            if (userFilter.test(item, filterEditor.getFilterLabel().getText())) {
                model.addElement(item);
            } else {
                filteredItems.add(item);
            }
        }

        //red color when no match
        filterEditor.getFilterLabel()
                    .setForeground(model.getSize() == 0 ?
                            Color.red : UIManager.getColor("Label.foreground"));
        //add unmatched items
        filteredItems.forEach(model::addElement);
    }
    
}