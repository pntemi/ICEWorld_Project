import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FetchState implements Runnable{
	protected IceWorldState state;
	protected int REFRESH_INTERVAL;
	protected Thread mainThread;
	
	public FetchState(IceWorldState state){
		this.state = state;
		REFRESH_INTERVAL = 1000;
	}
	public void init(){
		JFrame mainFrame = new JFrame("Adjusting the refresh interval");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(180,80));
		panel.setLayout(new GridLayout(1,3,20,20));
		JButton inc = new JButton("+");
		JButton dec = new JButton("-");
		inc.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(REFRESH_INTERVAL<10000)REFRESH_INTERVAL += 1000;
			}
		});
		dec.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(REFRESH_INTERVAL>1000)REFRESH_INTERVAL -= 1000;
			}
		});
		panel.add(inc);
		panel.add(new JLabel(""+(REFRESH_INTERVAL/1000)));
		panel.add(dec);
		mainFrame.add(panel);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	public void startFetch(){
		mainThread = new Thread(this);
		mainThread.start();
	}
	public void run(){
		while(true){
			try{
				Thread.sleep(REFRESH_INTERVAL);
			}catch(InterruptedException e){}
			System.out.println("fetching");
		}
	}
	
	public static void main(String[] args){
		FetchState test = new FetchState(new IceWorldState());
		test.init();
		test.startFetch();
	}
	
}
