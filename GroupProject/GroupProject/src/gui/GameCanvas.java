
package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.nio.Buffer;

import tiles.IsometricPoint;

public class GameCanvas extends Canvas implements Runnable {

        private final long OPTIMAL_TIME = 1000000000 / 60; //60fps
        private final int MAX_UPDATES = 5;
        
        private final int HEIGHT = 800;
        private final int WIDTH = 800;

        IsometricMap iso = new IsometricMap();
        
        private volatile boolean active = true;
        private BufferStrategy buffer;
        private Graphics graphics;
        
        private Thread thread = null;
        
        public GameCanvas() {
                setup();
                addMouseMotionListener(new MouseMotionListener() {
                        
                        @Override
                        public void mouseMoved(MouseEvent e) {
                                iso.setMousePoint(e.getPoint());
                        }
                        
                        @Override
                        public void mouseDragged(MouseEvent e) {
                                // TODO Auto-generated method stub
                                
                        }
                });
        }
        
        @Override
        public void run() {
                while (active) {
                        long now = System.nanoTime();
                        render();
                        draw();
                        long end = System.nanoTime();
                        long diff = (OPTIMAL_TIME - (end - now)) / 1000000000;
                        try {
//                              Thread.sleep((diff <= 0 ? 5 : diff));
                                Thread.sleep(5);
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
                graphics.fillRect(0, 0, WIDTH*WIDTH, HEIGHT*HEIGHT);

                if (graphics instanceof Graphics2D) {
                        ((Graphics2D) graphics).setRenderingHint(
                                        RenderingHints.KEY_ANTIALIASING,
                                        RenderingHints.VALUE_ANTIALIAS_ON);
                }
                // Renders map
                iso.draw(graphics);
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

}