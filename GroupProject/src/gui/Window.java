
package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {
        
        private GameCanvas canvas = null;
        
        private final int HEIGHT = 800;
        private final int WIDTH = 800;
        
        private JPanel jMain = null;

        public Window() {
                setup();
                addItems();
        }
        
        private void setup() {
                setVisible(true);
                setSize(WIDTH, HEIGHT);
                setResizable(true);
                setVisible(true);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setContentPane(getMain());
        }
        
        private JPanel getMain() {
                if (jMain == null) {
                        jMain = new JPanel(true);
                        jMain.setLayout(new BorderLayout());
                }
                return jMain;
        }
        
        private void addItems() {
                getMain().add(getCanvas());
        }
        
        private GameCanvas getCanvas() {
                if (canvas == null) {
                        canvas = new GameCanvas();
                }
                return canvas;
        }
}