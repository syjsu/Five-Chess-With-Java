import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Change extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JButton btnOk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Change frame = new Change();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	JRadioButton radioButton,radioButton_1,radioButton_2,radioButton_3,radioButton_4,radioButton_5,radioButton_6,radioButton_7;
	private JButton btnExit;
	private JButton btnBack;
	/**
	 * Create the frame.
	 */
	public Change() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u6570\u636E\u8F6C\u6362");
		label.setBounds(126, 13, 72, 18);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(38, 37, 277, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		radioButton = new JRadioButton("2");
		buttonGroup.add(radioButton);
		radioButton.setBounds(48, 70, 37, 27);
		contentPane.add(radioButton);
		
		radioButton_1 = new JRadioButton("8");
		buttonGroup.add(radioButton_1);
		radioButton_1.setBounds(91, 70, 37, 27);
		contentPane.add(radioButton_1);
		
		radioButton_2 = new JRadioButton("10");
		buttonGroup.add(radioButton_2);
		radioButton_2.setBounds(126, 70, 45, 27);
		contentPane.add(radioButton_2);
		
		radioButton_3 = new JRadioButton("16");
		buttonGroup.add(radioButton_3);
		radioButton_3.setBounds(168, 70, 45, 27);
		contentPane.add(radioButton_3);
		
		JLabel label_1 = new JLabel("\u8F6C\u6362\u6210");
		label_1.setBounds(126, 106, 72, 18);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(38, 135, 277, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		radioButton_4 = new JRadioButton("16");
		buttonGroup_1.add(radioButton_4);
		radioButton_4.setBounds(168, 168, 45, 27);
		contentPane.add(radioButton_4);
		
		radioButton_5 = new JRadioButton("8");
		buttonGroup_1.add(radioButton_5);
		radioButton_5.setBounds(91, 168, 37, 27);
		contentPane.add(radioButton_5);
		
		radioButton_6 = new JRadioButton("10");
		buttonGroup_1.add(radioButton_6);
		radioButton_6.setBounds(126, 168, 45, 27);
		contentPane.add(radioButton_6);
		
		radioButton_7 = new JRadioButton("2");
		buttonGroup_1.add(radioButton_7);
		radioButton_7.setBounds(48, 168, 37, 27);
		contentPane.add(radioButton_7);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(this);
		btnOk.setBounds(58, 213, 82, 27);
		contentPane.add(btnOk);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(this);
		btnExit.setBounds(154, 213, 82, 27);
		contentPane.add(btnExit);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		btnBack.setBounds(250, 213, 113, 27);
		contentPane.add(btnBack);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBack) {
			do_btnBack_actionPerformed(e);
		}
		if (e.getSource() == btnExit) {
			actionPerformed(e);
		}
		if (e.getSource() == btnOk) {
			do_btnOk_actionPerformed(e);
		}
	}
	protected void do_btnOk_actionPerformed(ActionEvent e) {
		//write code here
		String s = textField.getText();
		int n = 0;
		if(radioButton.isSelected()){
			//2
			n = Integer.parseInt(s,2);
		}else if(radioButton_1.isSelected()){
			//8
			n = Integer.parseInt(s,8);
		}else if(radioButton_2.isSelected()){
			//10
			n = Integer.parseInt(s);
		}else if(radioButton_3.isSelected()){
			//16
			n = Integer.parseInt(s,16);
		}else{
			
		}
		System.out.println(n);
		String s1 = "";
		if(radioButton_4.isSelected()){
			//2
			s1 = Integer.toBinaryString(n);
		}else if(radioButton_5.isSelected()){
			//8
			s1 = Integer.toOctalString(n);
		}else if(radioButton_6.isSelected()){
			//10
			s1 = Integer.toString(n);
		}else if(radioButton_7.isSelected()){
			//16
			s1 = Integer.toHexString(n);
		}else{
			
		}
		textField_1.setText(s1);
	}
	protected void do_btnBack_actionPerformed(ActionEvent e) {
		MainMenu mm = new MainMenu();
		mm.setVisible(true);
		this.setVisible(false);
	}
}