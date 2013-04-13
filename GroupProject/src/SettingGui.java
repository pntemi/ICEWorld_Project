import java.awt.Container;

import javax.swing.*;

public class SettingGui extends JFrame {

	public static void main(String[] args) {
		JLabel box1;
		JLabel box2;
		JPanel one = new JPanel();
		JTextField first = new JTextField();
		JTextField two = new JTextField();

		JFrame b = new JFrame();
		b.setSize(500, 500);
		box1 = new JLabel("Insert refresh interval");
		box2 = new JLabel("Insert talk visible duration");
		JButton savebutton1 = new JButton("Save");
		JButton cancelbutton1 = new JButton("Cancel");
		JButton savebutton2 = new JButton("Save");
		JButton cancelbutton2 = new JButton("Cancel");
		Container c = new Container();
		c.setLayout(null);
		one.setLayout(null);
		box1.setBounds(0, 0, 200, 50);
		box2.setBounds(0, 100, 400, 50);
	    first.setBounds(0,30,200,80);
	    two.setBounds(0,130,200,80);
		savebutton1.setBounds(200, 50, 100, 50);
		cancelbutton1.setBounds(300, 50, 100, 50);
		savebutton2.setBounds(200, 150, 100, 50);
		cancelbutton2.setBounds(300, 150, 100, 50);
		one.add(box1);
		one.add(box2);
		one.add(savebutton1);
		one.add(cancelbutton1);
		one.add(savebutton2);
		one.add(cancelbutton2);
		
		one.add(first);
		one.add(two);
		c.add(one);
		b.getContentPane().add(one);
		b.setVisible(true);

	}

}
