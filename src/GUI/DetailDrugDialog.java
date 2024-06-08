package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DetailDrugDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetailDrugDialog frame = new DetailDrugDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public DetailDrugDialog() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 292, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên thuốc ( Theo tên thuốc)");
		lblNewLabel.setBounds(10, 11, 256, 27);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(10, 66, 256, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Số lượng");
		lblNewLabel_1.setBounds(10, 41, 61, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 125, 256, 27);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Ghi chú");
		lblNewLabel_1_1.setBounds(10, 100, 61, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setBounds(83, 165, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(182, 165, 89, 23);
		contentPane.add(btnOk);
	}

}
