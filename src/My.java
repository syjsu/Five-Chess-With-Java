import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class My extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					My frame = new My();
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
	public My() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setBounds(178, 13, 72, 18);
		contentPane.add(lblWelcome);
		
		btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(this);
		btnNewButton.setIcon(new ImageIcon(My.class.getResource("/picture/picture.jpg")));
		btnNewButton.setBounds(0, 44, 432, 209);
		contentPane.add(btnNewButton);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			do_btnNewButton_actionPerformed(e);
		}
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		MainMenu mm = new MainMenu();
		mm.setVisible(true);
		this.setVisible(false);
	}

}
