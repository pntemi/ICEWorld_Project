import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Panel;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
//import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLayeredPane;
import java.awt.Component;
import java.awt.SystemColor;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JPasswordField;


public class ICEPort {

	private JFrame frmIceWorld;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ICEPort window = new ICEPort();
					window.frmIceWorld.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws MalformedURLException 
	 */
	public ICEPort() throws MalformedURLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws MalformedURLException 
	 */
	private void initialize() throws MalformedURLException{
		frmIceWorld = new JFrame();
		frmIceWorld.setTitle("ICE World");
		frmIceWorld.setBounds(100, 100, 850, 500);
		frmIceWorld.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmIceWorld.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewWindow = new JMenuItem("New window");
		mntmNewWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				ICEPort window = null;
				try {
					window = new ICEPort();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				window.frmIceWorld.setVisible(true);
			}
		});
		mnFile.add(mntmNewWindow);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mnAbout.add(mntmHelp);
		frmIceWorld.getContentPane().setLayout(null);
		
		JLabel lblIceWorld = new JLabel("ICE World");
		lblIceWorld.setBounds(0, 0, 834, 56);
		lblIceWorld.setForeground(new Color(0, 191, 255));
		lblIceWorld.setBackground(Color.RED);
		lblIceWorld.setHorizontalAlignment(SwingConstants.CENTER);
		lblIceWorld.setFont(new Font("Cooper Black", Font.BOLD, 48));
		frmIceWorld.getContentPane().add(lblIceWorld);
		
		JLabel lblItsATrap = new JLabel("Live in your world, play in ours");
		lblItsATrap.setForeground(new Color(147, 112, 219));
		lblItsATrap.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 13));
		lblItsATrap.setHorizontalAlignment(SwingConstants.CENTER);
		lblItsATrap.setBounds(0, 53, 834, 24);
		frmIceWorld.getContentPane().add(lblItsATrap);
		
		JPanel panel = new JPanel();
		panel.setBounds(263, 127, 306, 173);
		frmIceWorld.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(22, 34, 66, 14);
		panel.add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(98, 31, 164, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(22, 60, 66, 14);
		panel.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(108, 98, 94, 23);
		panel.add(btnLogin);
		
		JButton btnLoginAsAlien = new JButton("Login as Alien");
		btnLoginAsAlien.setBounds(98, 127, 114, 23);
		panel.add(btnLoginAsAlien);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(98, 57, 164, 20);
		panel.add(passwordField);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 433, 834, -428);
		frmIceWorld.getContentPane().add(layeredPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(0, 0, 834, 441);
		frmIceWorld.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(new URL("https://github.com/nichada/Project/blob/master/ice1.png?raw=true")));
		lblNewLabel.setBounds(40, 102, 180, 238);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(new URL("https://github.com/nichada/Project/blob/master/ice2.png?raw=true")));
		lblNewLabel_1.setBounds(614, 102, 174, 238);
		panel_1.add(lblNewLabel_1);
	}
}
