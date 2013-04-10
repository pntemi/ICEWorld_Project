
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
 
public class PanelExample {
        private void createAndDisplayGUI() {
                JFrame frame = new JFrame("Welcome to ICEWorld!");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
                frame.setContentPane(new ImagePanel());
                frame.setLayout(null);
                frame.setVisible(true);
                frame.pack();
                
        }
 
        public static void main(String[] args) {
                SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                                new PanelExample().createAndDisplayGUI();
                        }
                });
        }
}
 
class ImagePanel extends JPanel {
        private Image image;
 
        public ImagePanel() {
        		try {
                		this.setLayout(null);
                        image = ImageIO.read(new URL(
                        		"https://github.com/nichada/Project/blob/master/new1sunny.jpg?raw=true"));
    
                } catch (Exception e) {
                        e.printStackTrace();
                }
                
        }
        public static BufferedImage resizeImage(final Image image, int width, int height) {
        	final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        	final Graphics2D graphics2D = bufferedImage.createGraphics();
        	graphics2D.setComposite(AlphaComposite.Src);
        	graphics2D.drawImage(image, 0, 0, width, height, null);
        	graphics2D.dispose();
        	return bufferedImage;
        	}
 
      
 
        @Override
        public Dimension getPreferredSize() {
                return (new Dimension(800, 533));
        }
 
       public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, this);
                Dimension d = this.getSize();
                g.setColor(Color.BLUE);
                Font a = a = new Font("Algerian", Font.BOLD, 50);
                g.setFont(a);
                g.drawString("Welcome to ICE World!", d.width/5, d.height/2);
                
        }
}