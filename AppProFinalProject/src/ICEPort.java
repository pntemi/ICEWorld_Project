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
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Panel;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
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
import javax.swing.JLayeredPane;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

public class ICEPort {

	private JFrame frmIceWorld;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTabbedPane tabbedPane;
	private ScrollPane panel1;
	private ScrollPane panel2;
	private ScrollPane panel3;
	private ScrollPane panel4;
	private ScrollPane panel5;
	private ScrollPane panel6;
	static int offsetMultiplier = 0;
	static final int x=10, y=10;
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
					window.frmIceWorld.setResizable(false);
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
				++offsetMultiplier;
				int X = window.frmIceWorld.getX();
				int Y = window.frmIceWorld.getY();
				window.frmIceWorld.setLocation(X+(offsetMultiplier*x), Y+(offsetMultiplier*y));
				window.frmIceWorld.setVisible(true);
				window.frmIceWorld.setResizable(false);
			}
		});
		mnFile.add(mntmNewWindow);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmIceWorld.dispose();
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
				try {
					createPage1();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				createPage2();
				createPage3();
				createPage4();
				createPage5();
				createPage6();

				// Create a tabbed pane
				tabbedPane = new JTabbedPane();
				tabbedPane.addTab("Login to ICE world", panel1);
				tabbedPane.addTab("Walk/Zoom in ICE world", panel2);
				tabbedPane.addTab("Talk/yell in ICE world", panel3);
				tabbedPane.addTab("File Transfering in ICE world", panel4);
				tabbedPane.addTab("Customization in ICE world", panel5);
				tabbedPane.addTab("Sound in ICE world", panel6);
				tabbedPane.setPreferredSize(new Dimension(600,500));
				panel.add(tabbedPane, new FlowLayout());
				dialog.setModal(true);
				dialog.add(panel,null);
				dialog.setPreferredSize(new Dimension(600,500));
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

		JLabel lblTagline = new JLabel("Live in your world, play in ours");
		lblTagline.setForeground(new Color(147, 112, 219));
		lblTagline.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 13));
		lblTagline.setHorizontalAlignment(SwingConstants.CENTER);
		lblTagline.setBounds(0, 53, 834, 24);
		frmIceWorld.getContentPane().add(lblTagline);

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
		panel_1.setBounds(0, 0, 844, 451);
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
					SoundB.playSound("DustyF1-break3kick.wav");
					SoundB.playSound("DustyF1-break3kick.wav");
					SoundB.playSound("DustyF1-break3kick.wav");
				
					try {
						PanelExample a = new PanelExample(tester,immigration);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
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
					try {
						PanelExample a = new PanelExample(tester,immigration);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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

	public void createPage1() throws MalformedURLException{
		//URL url = getClass().getClassLoader().getResource("rainani.gif");
        //Icon icon = new ImageIcon(url);
        //JLabel label = new JLabel(icon);
        //JFrame f = new JFrame("Animation");
        //f.getContentPane().add(label);
		panel1 = new ScrollPane();
		URL url = getClass().getClassLoader().getResource("login.png" );
		String p ="";
		if (url != null)
			p =url.toString();
		 
        JLabel code=new JLabel("<html><head><title>Login</title><style>a{font-size:30px;}</style></head><body bgcolor=FFCCCC> <br> <br><center><font color=purple><h1> How to login to ICE world <br> </h1></font><br> <br> <table><tr><td><ul><font size=\\\\\"5\\\\\"><li>There are two types of Icetizen which are Inhabitants and Aliens.The regulation is that only students in 2143231(2012/2) can be inhabitant Icetizen logging to ICE world using username and password. However, anyone can also loggin as Alien Icetizen which is no username and password required. </li><li> An instance of ICE port will only allow 1 logged-in username at a time.The logged in Icetizen is called the active Icetizen of that ICE port instance which is said to host that active Icetizen. </li><li> Multiple instances of any ICE port programs can be launched at the same time on the same machine. </li><li> Via an instance of ICE port, the controller can control only the associated active Icetizen,not other Icetizen. </li><li> The user can log out of ICE world.</li></tr></td></table><br><br><center> <img src="+p+" height=250 width=250>  </body></html>");
		code.setPreferredSize(new Dimension(600,600));
		
		code.setVisible(true);
        panel1.add(code);
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.pack();
        //f.setLocationRelativeTo(null);
        //f.setVisible(true);
//		panel1.setBackground(Color.BLACK);
        /*JLabel label1 = new JLabel("Username:");
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
		panel1.add(fieldPass);*/
	}

	public void createPage2() {
		panel2 = new ScrollPane();
		
        JLabel code=new JLabel("<html><head><title> Walk in ICE world</title><style>a{font-size:30px;}style></head><body bgcolor=FFCCCC> <br> <br><font color=purple><h1><center> Walk/Zoom in ICE world <br> </h1></font><table><tr><td><ul><font size=\"6\" style='font-weight:bold'> Walk </font><br> <li> <font size=\"5\"> An Icetizen can walk from the current position to a destination position which is determined by a mouse click. </li> </font> <br> <font size=\"6\" style ='font-weight:bold'> Zoom</font> <li> <font size=\"5\">Scroll up scroll down to zoom in or zoom out</body></html>");
		code.setPreferredSize(new Dimension(300,300));
		
		code.setVisible(true);
        panel2.add(code);
		
	}

	public void createPage3() {
		panel3 = new ScrollPane();
		
		 
        JLabel code=new JLabel("<html><head><title> Talk/yell in ICE world</title><style>a{font-size:30px} </style></head><body bgcolor=FFCCCC> <br> <br><center><font size=\\\"6\\\" style='font-weight:bold'>Talk/yell in ICE world <br> </h1></font></center><br> <table><tr><td> <ul> <li> <font size=\\\"5\\\">An Icetizen can talk for at most 100 characters for each message</li>  <br><li>An Icetizen can yell for at most 10 characters for each massage.</li></body></html>");
		code.setPreferredSize(new Dimension(300,300));
		
		code.setVisible(true);
        panel3.add(code);
		
	}
	public void createPage4() {
		panel4 = new ScrollPane();
		
		 
        JLabel code=new JLabel("<html><head><title> File transfering in ICE world</title><style>a{font-size:30px;}</style></head><body bgcolor=FFCCCC> <br> <br><center><font size=\"6\" style='font-weight:bold'>File transfer in ICE world <br> </h1></font></center><font size=\"5\" ><li>The user can click on an Icetizen that is not the active Icetizen and activates a file transfer procedure to send a file to the ICE Port instance hosting the target Icetizen </li><li> The receiver of the file can choose to either accept or decline the file transfer</li><li> After the completion of the file transfer, the transferred file will be saved in the RECEIVE_FILES directory.</li></font> <br> </body></html>");
		code.setPreferredSize(new Dimension(600,600));
		
		code.setVisible(true);
        panel4.add(code);
		
	}
	public void createPage5() {
		panel5 = new ScrollPane();
		
		 
        JLabel code=new JLabel("<html><head><title> ICE-tizen customization</title><style>a{font-size:30px;}</style></head><body bgcolor=FFCCCC> <br> <br><center><font size=\"6\"style='font-weight:bold'>Icetizen customization </font> <br>  </h1></font></center><font size=\"5\"><li>The instance of Icetizen have 4 different parts-head,body,shirt and weapon.</li><li> Users of ICE world can customize thier icetizen look by clicking on the next buttton until they find the look that they like.</li><li> By clicking save ,the look of customized icetizen will appear on ICE port.</li><br> </body></html>");
		code.setPreferredSize(new Dimension(600,600));
		
		code.setVisible(true);
        panel5.add(code);
		
	}
	
	public void createPage6() {
		panel6 = new ScrollPane();
		
		 
        JLabel code=new JLabel("<html><head><title> Sound in ICE world</title><style>a{font-size:30px;}</style></head><body bgcolor=FFCCCC> <br> <br><center><font size=\"6\"style='font-weight:bold'> Sound in ICE world <br> </h1></font><br> <table><tr><td> <ul> <li> <font size=\"5\"> The user can adjust the volume of the background music.</li> </font></body></html>");
		code.setPreferredSize(new Dimension(600,600));
		
		code.setVisible(true);
        panel6.add(code);
	}

	// Main method to get things started

}