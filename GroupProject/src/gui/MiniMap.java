package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MiniMap extends JPanel {
	private static final long serialVersionUID = -3606066128709012029L;

	public static final int MINI_HEIGHT = 150;
	public static final int MINI_WIDTH = (int) (MINI_HEIGHT * Math.sqrt(3));

	GameCanvas gc;

	public MiniMap(GameCanvas gc) {
		super();
		this.gc = gc;
		setup();
	}

	private void setup() {
		setBounds(20, 20, MINI_WIDTH, MINI_HEIGHT);
		setBackground(Color.YELLOW);
		// setOpaque(true);
		setVisible(true);
	}

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
	}
}
