import iceworld.given.ICEWorldImmigration;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class PanelExample extends JFrame {
	public Icetizen user;
	public ICEWorldImmigration imm;

	public PanelExample(final Icetizen user, final ICEWorldImmigration imm)
			throws IOException {
		getContentPane().setLayout(null);
		this.setContentPane(new ImagePanel(user, imm));
		this.setTitle("Welcome to ICEWorld!");
		this.setVisible(true);
		this.setLocation(100, 100);
		this.setSize(701, 361);
		this.setResizable(false);
		this.pack();
		this.user = user;
		this.imm = imm;

		JButton btnCustomize = new JButton("Customize");
		btnCustomize.setBounds(825, 100, 150, 23);
		btnCustomize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Customization custom = new Customization(user, imm);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		this.add(btnCustomize);

		JButton btnLogout = new JButton("Log Out and Quit");
		btnLogout.setBounds(825, 500, 150, 23);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imm.logout();
				System.exit(0);
			}
		});
		this.add(btnLogout);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	private void createAndDisplayGUI() throws IOException {
		JFrame frame = new JFrame("Welcome to ICEWorld!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new ImagePanel(user, imm));
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.pack();

	}

}

class ImagePanel extends JPanel {
	private Image image;
	Icetizen user;
	ICEWorldImmigration imm;

	public ImagePanel(Icetizen user, ICEWorldImmigration imm)
			throws IOException {
		try {
			this.setLayout(null);
			image = ImageIO
					.read(new URL(
							"https://github.com/nichada/Project/blob/master/new1sunny.jpg?raw=true"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		JButton btnTalk = new JButton("Talk");
		btnTalk.setBounds(825, 250, 150, 23);
		this.add(btnTalk);

		JButton btnYell = new JButton("Yell");
		btnYell.setBounds(825, 300, 150, 23);
		this.add(btnYell);

		JButton btnFileTrans = new JButton("File Transfer");
		btnFileTrans.setBounds(825, 350, 150, 23);
		btnFileTrans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ADD ACTION HERE!!!
			}
		});
		this.add(btnFileTrans);
		
		final JTextField text = new JTextField();
		text.setBounds(825,150, 150, 50);
		text.setDocument(new JTextFieldLimit(100));
		this.add(text);

		GameCanvas gc = new GameCanvas(btnTalk, btnYell,text);
		gc.ice = this.user = user;
		gc.imm = this.imm = imm;
		gc.ice.setLocation(new MatrixPoint(0, 0));
		gc.setBounds(0, 0, 800, 600);
		gc.setVisible(true);
		this.add(gc);
		FetchState test = new FetchState(new IceWorldState(), gc);
		test.init();
		test.startFetch();
	}

	public static BufferedImage resizeImage(final Image image, int width,
			int height) {
		final BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		final Graphics2D graphics2D = bufferedImage.createGraphics();
		graphics2D.setComposite(AlphaComposite.Src);
		graphics2D.drawImage(image, 0, 0, width, height, null);
		graphics2D.dispose();
		return bufferedImage;
	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(1000, 590));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
		Dimension d = this.getSize();
		g.setColor(Color.BLUE);
		Font a = new Font("Algerian", Font.BOLD, 30);
		g.setFont(a);
		g.drawString("Welcome to ICE World!", d.width / 5, d.height / 2);
	}
}