import iceworld.given.ICEWorldImmigration;
import iceworld.given.MyIcetizen;

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
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

import javax.swing.JPasswordField;

public class ICEPort {

	private JFrame frmIceWorld;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTabbedPane tabbedPane;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	public JTextField username;
	private JDialog dialog;

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
	 * 
	 * @throws MalformedURLException
	 */
	public ICEPort() throws MalformedURLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws MalformedURLException
	 */
	private void initialize() throws MalformedURLException {
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
			public void actionPerformed(ActionEvent arg0) {
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
		JMenuItem author = new JMenuItem("Author");
		mnAbout.add(author);
		author.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame pic = new JFrame();
				BufferedImage myImage = null;
				try {
					myImage = ImageIO.read(new URL(
							"http://dl.dropbox.com/u/107759697/about.jpg"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					// System.out.println("Cannot find image");
				}

				myImage = resize(myImage, 1000, 700);

				ImageIcon imageIcon = new ImageIcon(myImage);

				JPanel imagePanel = new JPanel(new GridLayout(1, 1, 10, 10));

				JLabel author_image = new JLabel(imageIcon);
				pic.setLayout(new BorderLayout());
				imagePanel.add(author_image);
				pic.add(imagePanel);
				pic.setPreferredSize(new Dimension(1000, 700));
				pic.pack();
				pic.setVisible(true);
			}
		});

		JMenuItem mntmHelp = new JMenuItem("Help");
		mnAbout.add(mntmHelp);
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				dialog = new JDialog(frame,"Dialog");
				// NOTE: to reduce the amount of code in this example, it uses
				// panels with a NULL layout. This is NOT suitable for
				// production code since it may not display correctly for
				// a look-and-feel.

				//setTitle("Tabbed Pane Application");
				//setSize(300, 200);
				//setBackground(Color.gray);

				JPanel panel = new JPanel();
				dialog.setLayout(new BorderLayout());
				//frame.add(frame);

				// Create the tab pages
				createPage1();
				createPage2();
				createPage3();

				// Create a tabbed pane
				tabbedPane = new JTabbedPane();
				tabbedPane.addTab("Page 1", panel1);
				tabbedPane.addTab("Page 2", panel2);
				tabbedPane.addTab("Page 3", panel3);
				panel.add(tabbedPane, BorderLayout.CENTER);
				dialog.setModal(true);
				dialog.add(panel,BorderLayout.CENTER);
				dialog.setPreferredSize(new Dimension(1000,600));
				dialog.pack();
				dialog.setVisible(true);
				
				
			}

		});

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
		String username = textField.getText();

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(22, 60, 66, 14);
		panel.add(lblPassword);
		String password;

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new Action() );
		btnLogin.setBounds(108, 98, 94, 23);
		panel.add(btnLogin);
		//btnLogin.addActionListener(new Action() );
			/*public void actionPerformed(ActionEvent e) {
				/*
				 * MyIcetizen icetizen = new MyIcetizen() ICEWorldImmigration
				 * immigration = new ICEWorldImmigration(new
				 * MyIcetizen(username)); if (immigration.login(<<(String) the
				 * password of the Icetizen>>)) System.out.println("Login OK");
				 
			}
		}); */

		JButton btnLoginAsAlien = new JButton("Login as Alien");
		btnLoginAsAlien.addActionListener(new Action() );
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
		lblNewLabel
				.setIcon(new ImageIcon(
						new URL(
								"https://github.com/nichada/Project/blob/master/ice1.png?raw=true")));
		lblNewLabel.setBounds(40, 102, 180, 238);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1
				.setIcon(new ImageIcon(
						new URL(
								"https://github.com/nichada/Project/blob/master/ice2.png?raw=true")));
		lblNewLabel_1.setBounds(614, 102, 174, 238);
		panel_1.add(lblNewLabel_1);
	}
	class Action implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String username = textField.getText();
			String pass= new String(passwordField.getPassword());
			//String = passwordField.getPassword();
			//for(int i = 0;i<a.length;i++){
			//	pass = pass+a[i];
			//}
			System.out.print(pass);
			Icetizen tester = new Icetizen();
			
			tester.setListeningPort(256);
			ICEWorldImmigration immigration = new ICEWorldImmigration(tester);
			JButton b = (JButton) arg0.getSource();
			String name = b.getText();
			if(name.equals("Login")){
				tester.setUsername(username);
				if(immigration.login(pass)){
					//log in laew tum arai
					System.out.println("login to iceworld");
					PanelExample a = new PanelExample();
					frmIceWorld.dispose();
					//System.out.println(immigration.walk(10,10));
				}
				else{
					//password or username pid 
					System.out.println("wrong password");
					
				}
			}else{
				if(immigration.loginAlien()){
					//log in as alien
					System.out.println("login to iceworld  as alien");
					PanelExample a = new PanelExample();
					frmIceWorld.dispose();
					
				}else{
				// log in mai dai 
					System.out.println("cant login");
				}
			}
		}
		
	}

	public static BufferedImage resize(BufferedImage image, int width,
			int height) {
		int type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image
				.getType();
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.setComposite(AlphaComposite.Src);

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();
		return resizedImage;
	}

	public void createPage1() {
		panel1 = new JPanel();
		panel1.setLayout(null);

		JLabel label1 = new JLabel("Username:");
		label1.setBounds(10, 15, 150, 20);
		panel1.add(label1);

		JTextField field = new JTextField();
		field.setBounds(10, 35, 150, 20);
		panel1.add(field);

		JLabel label2 = new JLabel("Password:");
		label2.setBounds(10, 60, 150, 20);
		panel1.add(label2);

		JPasswordField fieldPass = new JPasswordField();
		fieldPass.setBounds(10, 80, 150, 20);
		panel1.add(fieldPass);
	}

	public void createPage2() {
		panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());

		panel2.add(new JButton("North"), BorderLayout.NORTH);
		panel2.add(new JButton("South"), BorderLayout.SOUTH);
		panel2.add(new JButton("East"), BorderLayout.EAST);
		panel2.add(new JButton("West"), BorderLayout.WEST);
		panel2.add(new JButton("Center"), BorderLayout.CENTER);
	}

	public void createPage3() {
		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(3, 2));

		panel3.add(new JLabel("Field 1:"));
		panel3.add(new TextArea());
		panel3.add(new JLabel("Field 2:"));
		panel3.add(new TextArea());
		panel3.add(new JLabel("Field 3:"));
		panel3.add(new TextArea());
	}

	// Main method to get things started

}
