import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.*;

public class FetchState implements Runnable{
	protected IceWorldState state;
	protected int REFRESH_INTERVAL;
	protected Thread mainThread;
	JLabel output = new JLabel("        1"); 
	
	public FetchState(IceWorldState state){
		this.state = state;
		REFRESH_INTERVAL = 1000;
	}
	public void init(){
		JFrame mainFrame = new JFrame("Adjusting the refresh interval");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(200,50));
		panel.setLayout(new GridLayout(1,3,10,10));
		JButton inc = new JButton("+");
		JButton dec = new JButton("-");
		inc.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(REFRESH_INTERVAL<10000){
					REFRESH_INTERVAL += 1000;
					output.setText("        "+(REFRESH_INTERVAL/1000));
				}
				else output.setText("  Too Slow");
			
			}
		});
		dec.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(REFRESH_INTERVAL>1000){
					REFRESH_INTERVAL -= 1000;
					output.setText("         "+(REFRESH_INTERVAL/1000));
				}
				else output.setText("  Too Fast");
				
			}
		});
		panel.add(inc);
		panel.add(output);
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
			ICEWorldPeek peek = new ICEWorldPeek();
			if(peek.isConnected()){
				try {
					state.processState();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(state.weatherTime);
				System.out.println(state.weather);
			}else{
				//pop up a model dialog
				JOptionPane.showMessageDialog(new JPanel(), "ICE World cannot be reached");
			}
		}
	}
	
	public static void main(String[] args){
		FetchState test = new FetchState(new IceWorldState());
		test.init();
		test.startFetch();
	}
	
}
