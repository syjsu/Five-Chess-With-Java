import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;


public class Cale extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cale frame = new Cale();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cale() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(14, 27, 86, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("+");
		label.setBounds(107, 30, 13, 18);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.addActionListener(this);
		textField_1.setBounds(131, 27, 86, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_1 = new JLabel("=");
		label_1.setBounds(227, 30, 20, 18);
		contentPane.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(249, 27, 86, 24);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		btnBack = new JButton("BACK");
		btnBack.addActionListener(this);
		btnBack.setBounds(121, 176, 113, 27);
		contentPane.add(btnBack);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBack) {
			do_btnBack_actionPerformed(e);
		}
		if (e.getSource() == textField_1) {
			do_textField_1_actionPerformed(e);
		}
	}
	protected void do_textField_1_actionPerformed(ActionEvent e) {
		//write code here
		String s1 = textField.getText();
		String s2 = textField_1.getText();
		int a = Integer.parseInt(s1)+Integer.parseInt(s2);
		textField_2.setText(a+"");
	}
	protected void do_btnBack_actionPerformed(ActionEvent e) {
		MainMenu mm = new MainMenu();
		mm.setVisible(true);
		this.setVisible(false);
	}
}
