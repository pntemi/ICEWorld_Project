package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TransparentPanel extends JPanel {
	private static final long serialVersionUID = -220182824893201481L;

	public TransparentPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.drawOval(20, 20, 20, 20);
    }
}