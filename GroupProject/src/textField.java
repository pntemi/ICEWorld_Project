import iceworld.given.ICEWorldImmigration;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.AttributeSet;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class textField extends JFrame {
	Icetizen tester;
	JTextField a;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame b = new JFrame();
		b.setSize(500, 500);
		  a = new JTextField();
		JButton button = new JButton("Enter");
		button.addActionListener(new Action());
		Container c = new Container();
		c.setLayout(null);
		a.setBounds(0, 0, 500, 100);
		button.setBounds(4, 100, 50, 50);
		a.setDocument(new JTextFieldLimit(100));
		c.add(button);
		c.add(a);
		b.getContentPane().add(c);
 	    

	}

	class Action implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
           String text=a.getText();
           ICEWorldImmigration s = new ICEWorldImmigration(tester);
			s.talk(text);
			
		}

	}
	
}
