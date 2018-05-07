import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainMenu extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JMenu mnAbout;
	private JMenuItem mntmCal;
	private JButton btnBack;
	private JMenuItem mntmFinish;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Function");
		menuBar.add(mnNewMenu);
		
		mntmCal = new JMenuItem("cal");
		mntmCal.addActionListener(this);
		mnNewMenu.add(mntmCal);
		
		mntmFinish = new JMenuItem("Change");
		mntmFinish.addActionListener(this);
		mnNewMenu.add(mntmFinish);
		
		mnAbout = new JMenu("About");
		mnAbout.addMouseListener(this);
		mnAbout.addActionListener(this);
		menuBar.add(mnAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		contentPane.add(btnBack, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmFinish) {
			do_mntmFinish_actionPerformed(e);
		}
		if (e.getSource() == btnBack) {
			do_btnBack_actionPerformed(e);
		}
		if (e.getSource() == mntmCal) {
			do_mntmCal_actionPerformed(e);
		}
		if (e.getSource() == mnAbout) {
			do_mnAbout_actionPerformed(e);
		}
	}
	protected void do_mnAbout_actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null,"My Name:Deng");
	}
	protected void do_mntmCal_actionPerformed(ActionEvent e) {
		Cale c = new Cale();//新建一个对象
		c.setVisible(true);
		this.setVisible(false);
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == mnAbout) {
			do_mnAbout_mouseClicked(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void do_mnAbout_mouseClicked(MouseEvent e) {
		JOptionPane.showMessageDialog(null,"My Name:Deng Shaowen(邓邵文) 172018063 这是我写的第一个多界面编程！");
	}
	protected void do_btnBack_actionPerformed(ActionEvent e) {
		My my = new My();
		my.setVisible(true);
		this.setVisible(false);
	}
	protected void do_mntmFinish_actionPerformed(ActionEvent e) {
		Change change = new Change();
		change.setVisible(true);
		this.setVisible(false);

	}
}
