package gui;

import iceworld.given.IcetizenLook;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.image.BufferStrategy;
import java.nio.Buffer;

import character.Icetizen;

import tiles.IsometricPoint;
import tiles.MatrixPoint;
import utils.MathUtils;

public class GameCanvas extends Canvas implements Runnable {
	public static final int FPS = 60;
	private final long OPTIMAL_TIME = 1000000000 / FPS; // 60fps
	private final int MAX_UPDATES = 5;

	public final static int HEIGHT = 800;
	public final static int WIDTH = 900;

	IsometricMap iso = new IsometricMap();

	private volatile boolean active = true;
	private BufferStrategy buffer;
	private Graphics graphics;

	private Thread thread = null;
	//temp 
	Icetizen ice;

	public GameCanvas() {
		final Canvas gc = this;
		setup();
		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				Point p = e.getPoint();
				iso.setMousePoint(p);
				if (p.x < gc.getWidth() * 0.1) {
					iso.setMoveMapHor(IsometricMap.MOVE_RIGHT);
				} else if (p.x > gc.getWidth() * 0.9) {
					iso.setMoveMapHor(IsometricMap.MOVE_LEFT);
				} else {
					iso.setMoveMapHor(IsometricMap.MOVE_NONE);
				}

				if (p.y < gc.getHeight() * 0.1) {
					iso.setMoveMapVer(IsometricMap.MOVE_DOWN);
				} else if (p.y > gc.getHeight() * 0.9) {
					iso.setMoveMapVer(IsometricMap.MOVE_UP);
				} else {
					iso.setMoveMapVer(IsometricMap.MOVE_NONE);
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				clickAction(e.getPoint());
			}
		});
		// temp
		ice = new Icetizen();
		ice.setLocation(new MatrixPoint(0, 0), iso);
		IcetizenLook look = new IcetizenLook();
		ice.setIcetizenLook(look);
	}

	@Override
	public void run() {
		while (active) {
			long now = System.nanoTime();
			render();
			draw();
			long end = System.nanoTime();
			long diff = (OPTIMAL_TIME - (end - now)) / 1000000;
			// double fps = 1000000000.0 / (end - now);
			// System.out.println("FPS : " + fps);
			try {
				Thread.sleep((diff <= 0 ? 5 : diff));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void render() {
		graphics = buffer.getDrawGraphics();

		// Clearing old data
		graphics.setColor(Color.gray);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);

		if (graphics instanceof Graphics2D) {
			((Graphics2D) graphics).setRenderingHint(
					RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
		}
		// Renders map
		iso.draw(graphics);
		ice.draw(graphics, iso);

	}

	private void draw() {
		if (!buffer.contentsLost()) {
			buffer.show();
		}
		if (graphics != null) {
			graphics.dispose();
		}
	}

	private void setup() {
		setIgnoreRepaint(true);
		setVisible(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	public void addNotify() {
		super.addNotify();

		// Creating buffered strategy.
		createBufferStrategy(2);
		buffer = getBufferStrategy();
		requestFocus();

		// Starting
		start();
	}

	private void start() {
		if (thread == null) {
			thread = new Thread(this, "GameCanvas");
		}
		thread.start();
	}

	private void clickAction(Point mouse) {
		if (iso.mapContain(mouse)) {
			MatrixPoint mp = iso.getMatrixPoint(mouse);
			ice.setLocation(mp, iso);
		}
	}

}