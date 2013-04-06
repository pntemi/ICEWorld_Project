import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.applet.Applet;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class SplashScreenS extends Applet {
	public SplashScreenS() throws MalformedURLException {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel
				.setIcon(new ImageIcon(
						new URL(
								"https://github.com/nichada/Project/blob/master/sunny2.jpg?raw=true")));
		lblNewLabel.setBounds(0, 0, 900, 600);
		add(lblNewLabel);
	}

	public void paint(Graphics g) {
		Font a = a = new Font("Algerian", Font.BOLD, 50);

		g.setColor(Color.BLUE);
		g.setFont(a);
		g.drawString("WELCOME TO ICE WORLD!", 180, 350);

		setSize(1000, 1000);
		// setBackground(Color.BLACK);

	}
}
