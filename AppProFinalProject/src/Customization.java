import iceworld.given.ICEWorldImmigration;
import iceworld.given.IcetizenLook;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import org.json.simple.parser.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Customization extends JFrame {
	static String[] Head, Body, Shirt, Weapon;
	static int IndexHead = 0, IndexBody = 0, IndexShirt = 0, IndexWeapon = 0;
	
	Icetizen user;
	ICEWorldImmigration imm;
	
	JPanel paint;
	JLabel LBody = new JLabel("Body");
	JLabel LHead = new JLabel("Head Gear");
	JLabel LShirt = new JLabel("Shirt");
	JLabel LWeapon = new JLabel("Weapon");
	
	JButton preHead, nextHead; 
	JButton preBody, nextBody; 
	JButton preShirt, nextShirt;
	JButton preWeapon, nextWeapon;
	JButton cancelButton, okbutton;
	ButtonListener b = new ButtonListener();
	
	ImageIcon IcBody = new ImageIcon();
	ImageIcon IcHead = new ImageIcon();
	ImageIcon IcShirt = new ImageIcon();
	ImageIcon IconWeapon = new ImageIcon();
	
	
	static JSONParser jp = new JSONParser();
	static ContainerFactory containerFactory = new ContainerFactory() {
		public LinkedList creatArrayContainer() {
			return new LinkedList();
		}

		public Map createObjectContainer() {
			return new LinkedHashMap();
		}

	};
	

public Customization(Icetizen user, ICEWorldImmigration imm) throws IOException {
		this.user = user;
		this.imm = imm;
	
		setTitle("Customize your Character");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(800, 800);
		getContentPane().setLayout(null);

		getGraphicsArray();

		paint = new JPanel();
		paint.setBounds(6, 6, 409, 523);
		getContentPane().add(paint);
		paint.setLayout(null);

		JPanel headPanel = new JPanel();
		headPanel.setLayout(null);
		headPanel.setBounds(420, 50, 240, 100);
		getContentPane().add(headPanel);

		JTextPane txtpnHeadGear = new JTextPane();
		txtpnHeadGear.setText("Head");
		txtpnHeadGear.setOpaque(false);
		txtpnHeadGear.setFont(new Font("monospaced", Font.PLAIN, 18));
		txtpnHeadGear.setBackground(Color.WHITE);
		txtpnHeadGear.setBounds(100, 6, 120, 50);
		headPanel.add(txtpnHeadGear);

		preHead = new JButton("Previous Head");
		preHead.setBounds(0, 25, 120, 50);
		preHead.addActionListener(b);
		headPanel.add(preHead);

		nextHead = new JButton("Next Head");
		nextHead.setBounds(120, 25, 120, 50);
		nextHead.addActionListener(b);
		headPanel.add(nextHead);
		
		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(null);
		bodyPanel.setBounds(420, 150, 240, 100);
		getContentPane().add(bodyPanel);

		JTextPane txtpnBody = new JTextPane();
		txtpnBody.setText("Body");
		txtpnBody.setOpaque(false);
		txtpnBody.setFont(new Font("monospaced", Font.PLAIN, 18));
		txtpnBody.setBackground(Color.WHITE);
		txtpnBody.setBounds(100, 6, 120, 19);
		bodyPanel.add(txtpnBody);
		
		preBody = new JButton("Previous Body");
		preBody.addActionListener(b);
		preBody.setBounds(0, 25, 120, 50);
		bodyPanel.add(preBody);

		nextBody = new JButton("Next Body");
		nextBody.setBounds(120, 25, 120, 50);
		nextBody.addActionListener(b);
		bodyPanel.add(nextBody);

	
		JPanel shirtPanel = new JPanel();
		shirtPanel.setLayout(null);
		shirtPanel.setBounds(420, 250, 240, 100);
		getContentPane().add(shirtPanel);

		JTextPane txtpnShirt = new JTextPane();
		txtpnShirt.setText("Shirt");
		txtpnShirt.setOpaque(false);
		txtpnShirt.setFont(new Font("monospaced", Font.PLAIN, 18));
		txtpnShirt.setBackground(Color.WHITE);
		txtpnShirt.setBounds(90, 6, 120, 32);
		shirtPanel.add(txtpnShirt);

		preShirt = new JButton("Previous Shirt");
		preShirt.setBounds(0, 25, 120, 50);
		preShirt.addActionListener(b);
		shirtPanel.add(preShirt);
		
		nextShirt = new JButton("Next Shirt");
		nextShirt.setBounds(120, 25, 120, 50);
		nextShirt.addActionListener(b);
		shirtPanel.add(nextShirt);


		JPanel weaponPanel = new JPanel();
		weaponPanel.setLayout(null);
		weaponPanel.setBounds(420, 350, 240, 100);
		getContentPane().add(weaponPanel);

		JTextPane txtpnWeapon = new JTextPane();
		txtpnWeapon.setText("Weapon");
		txtpnWeapon.setOpaque(false);
		txtpnWeapon.setFont(new Font("monospaced", Font.PLAIN, 18));
		txtpnWeapon.setBackground(Color.WHITE);
		txtpnWeapon.setBounds(90, 6, 120, 60);
		weaponPanel.add(txtpnWeapon);


		preWeapon = new JButton("Previous Weapon");
		preWeapon.setBounds(0, 30, 120, 50);
		preWeapon.addActionListener(b);
		weaponPanel.add(preWeapon);

		
		nextWeapon = new JButton("Next Weapon");
		nextWeapon.setBounds(125, 30, 110, 50);
		nextWeapon.addActionListener(b);
		weaponPanel.add(nextWeapon);

		okbutton = new JButton("OK");
		okbutton.setBounds(440, 460, 90, 40);
		okbutton.addActionListener(b);
		getContentPane().add(okbutton);
				
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(550, 460, 90, 40);
		cancelButton.addActionListener(b);
		getContentPane().add(cancelButton);

		paintChr();
		this.setVisible(true);

	}

	public static void getGraphicsArray() throws IOException {
		String inputLine;
		URL url = null;
		url = new URL("http://iceworld.sls-atl.com/api/&cmd=gresources&uid=0");
		// {"status":1,"data":["B001","B002","B003","B004","B005","B006","B007",......
		URLConnection connection = url.openConnection();
		connection.connect();
		BufferedReader temp = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		int b = 0, h = 0, s = 0, w = 0;
		while ((inputLine = temp.readLine()) != null) {
			
			String datArray = inputLine.substring(inputLine.lastIndexOf("["));
			
			for (int i = 0; i < datArray.length(); i++) {
				if (datArray.charAt(i) == 'B')
					b++;
				if (datArray.charAt(i) == 'H')
					h++;
				if (datArray.charAt(i) == 'S')
					s++;
				if (datArray.charAt(i) == 'W')
					w++;
			}

			Body = new String[b];
			Head = new String[h];
			Shirt = new String[s];
			Weapon = new String[w];
			int bcount = 0, hcount = 0, scount = 0, wcount = 0;

			for (int i = 0; i < datArray.length(); i++) {
				if (datArray.charAt(i) == 'B') {
					String body = datArray.substring(i, i + 4);
					System.out.println("Body is "+body);
					Body[bcount] = body;
					bcount++;
				}
				if (datArray.charAt(i) == 'H') {
					Head[hcount] = datArray.substring(i, i + 4);
					hcount++;
				}
				if (datArray.charAt(i) == 'S') {
					Shirt[scount] = datArray.substring(i, i + 4);
					scount++;
				}
				if (datArray.charAt(i) == 'W') {
					Weapon[wcount] = datArray.substring(i, i + 4);
					wcount++;
				}
			}
			
		}

	}

	public static ImageIcon getBody(int i) throws IOException {
		URL url = null;
		String webICE = "http://iceworld.sls-atl.com/api/&cmd=gurl&gid="
				+ Body[i];
		String s, ImageURL = "";
		;
		ImageIcon bodyimg;
		url = new URL(webICE);
		URLConnection connection = url.openConnection();
		connection.connect();
		BufferedReader temp = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		while ((s = temp.readLine()) != null) {

			try {
				Map jsonMap = (Map) jp.parse(s, containerFactory);
				Map jsonData = (Map) jsonMap.get("data");

				ImageURL = (String) jsonData.get("location");

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Image image = null;
		URL blink = new URL("http://iceworld.sls-atl.com/" + ImageURL);
		image = ImageIO.read(blink);

		bodyimg = new ImageIcon(image);

		return bodyimg;
	}

	public static ImageIcon getHead(int i) throws IOException {
		URL url = null;
		String webICE = "http://iceworld.sls-atl.com/api/&cmd=gurl&gid="
				+ Head[i];
		String s, ImageURL = "";
		;
		ImageIcon headimg;
		url = new URL(webICE);
		URLConnection connection = url.openConnection();
		connection.connect();
		BufferedReader temp = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		while ((s = temp.readLine()) != null) {

			try {
				Map jsonMap = (Map) jp.parse(s, containerFactory);
				Map jsonData = (Map) jsonMap.get("data");

				ImageURL = (String) jsonData.get("location");

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Image image = null;
		URL hlink = new URL("http://iceworld.sls-atl.com/" + ImageURL);
		image = ImageIO.read(hlink);
		headimg = new ImageIcon(image);

		return headimg;
	}

	public static ImageIcon getShirt(int i) throws IOException {
		URL url = null;
		String webICE = "http://iceworld.sls-atl.com/api/&cmd=gurl&gid="
				+ Shirt[i];
		String s, ImageURL = "";
		;
		ImageIcon shirtimg;
		url = new URL(webICE);
		URLConnection connection = url.openConnection();
		connection.connect();
		BufferedReader temp = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		while ((s = temp.readLine()) != null) {

			try {
				Map jsonMap = (Map) jp.parse(s, containerFactory);
				Map jsonData = (Map) jsonMap.get("data");

				ImageURL = (String) jsonData.get("location");

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Image image = null;
		URL slink = new URL("http://iceworld.sls-atl.com/" + ImageURL);
		image = ImageIO.read(slink);
		shirtimg = new ImageIcon(image);

		return shirtimg;
	}

	public static ImageIcon getWeapon(int i) throws IOException {
		URL url = null;
		String webICE = "http://iceworld.sls-atl.com/api/&cmd=gurl&gid="
				+ Weapon[i];
		String s, ImageURL = "";
		;
		ImageIcon weaponimg;
		url = new URL(webICE);
		URLConnection connection = url.openConnection();
		connection.connect();
		BufferedReader temp = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		while ((s = temp.readLine()) != null) {

			try {
				Map jsonMap = (Map) jp.parse(s, containerFactory);
				Map jsonData = (Map) jsonMap.get("data");

				ImageURL = (String) jsonData.get("location");

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Image image = null;
		URL wlink = new URL("http://iceworld.sls-atl.com/" + ImageURL);
		image = ImageIO.read(wlink);
		weaponimg = new ImageIcon(image);

		return weaponimg;
	}
	
	public void paintChr() throws IOException {
		paintWeapon();
	    paintHead();
		paintShirt();
		paintBody();

	}

	private void paintBody() throws IOException {
		IcBody = getBody(IndexBody);
		LBody.setIcon(IcBody);
		LBody.setBounds(10, 10, IcBody.getIconWidth(),
				IcBody.getIconHeight());
		paint.add(LBody);

	}

	private void paintShirt() throws IOException {
		IcShirt = getShirt(IndexShirt);
		LShirt.setIcon(IcShirt);
		LShirt.setBounds(10, 10, IcShirt.getIconWidth(),
				IcShirt.getIconHeight());
		paint.add(LShirt);

	}

	private void paintHead() throws IOException {
		IcHead = getHead(IndexHead);
		LHead.setIcon(IcHead);
		LHead.setBounds(10, 10, IcHead.getIconWidth(),
				IcHead.getIconHeight());
		paint.add(LHead);

	}

	private void paintWeapon() throws IOException {
		IconWeapon = getWeapon(IndexWeapon);
		LWeapon.setIcon(IconWeapon);
		LWeapon.setBounds(10, 10, IconWeapon.getIconWidth(),
				IconWeapon.getIconHeight());
		paint.add(LWeapon);

	}


	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			if (source == nextBody) {
				if (IndexBody == Body.length - 1)
					IndexBody = 0;
				++IndexBody;
				try {
					paintChr();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (source == preBody) {
				if (IndexBody == 0)
					IndexBody = Body.length - 1;
				--IndexBody;
				try {
					paintChr();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (source == nextHead) {
				if (IndexHead == Head.length - 1)
					IndexHead = 0;
				++IndexHead;
				try {
					paintChr();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (source == preHead) {
				if (IndexHead == 0)
					IndexHead = Head.length - 1;
				--IndexHead;
				try {
					paintChr();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (source == nextShirt) {
				if (IndexShirt == Shirt.length - 1)
					IndexShirt = 0;
				++IndexShirt;
				try {
					paintChr();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (source == preShirt) {
				if (IndexShirt == 0)
					IndexShirt = Shirt.length - 1;
				--IndexShirt;
				try {
					paintChr();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (source == nextWeapon) {
				if (IndexWeapon == Weapon.length - 1)
					IndexWeapon = 0;
				++IndexWeapon;
				try {
					paintChr();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (source == preWeapon) {
				if (IndexWeapon == 0)
					IndexWeapon= Weapon.length - 1;
				--IndexWeapon;
				try {
					paintChr();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (source == okbutton) {
				if(Body==null)System.out.println("NULLLLLLLLLLL");
				System.out.println("B: "+Body[IndexBody]);
				System.out.println(IndexShirt);
				System.out.println(IndexWeapon);
				IcetizenLook look = new IcetizenLook();
				look.gidB = Body[IndexBody];
				look.gidH = Head[IndexHead];
				look.gidS = Shirt[IndexShirt];
				look.gidW = Weapon[IndexWeapon];
				
				user.setIcetizenLook(look);
				if(imm.customization(user.look))
					JOptionPane.showMessageDialog(new JPanel(),"Your look has changed!");
				
				dispose();

			} else if (source == cancelButton) {
				dispose();
			}

		}

	}

}