
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.*;

import org.json.simple.parser.ParseException;

public class FetchState implements Runnable{
	protected IceWorldState state;
	protected int REFRESH_INTERVAL;
	protected Thread mainThread;
	protected GameCanvas gc;
	protected Icetizen user;
	JLabel output = new JLabel("             1"); 
	
	public FetchState(IceWorldState state, GameCanvas gc){
		this.state = state;
		REFRESH_INTERVAL = 1000;
		this.gc = gc;
	}
	public void init(){
		JFrame mainFrame = new JFrame("Adjusting the refresh interval");
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(200,50));
		panel.setLayout(new GridLayout(1,3,10,10));
		JButton inc = new JButton("+");
		JButton dec = new JButton("-");
		inc.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(REFRESH_INTERVAL<10000){
					REFRESH_INTERVAL += 1000;
					output.setText("             "+(REFRESH_INTERVAL/1000));
				}
				else output.setText("             "+(REFRESH_INTERVAL/1000));
			
			}
		});
		dec.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(REFRESH_INTERVAL>1000){
					REFRESH_INTERVAL -= 1000;
					output.setText("             "+(REFRESH_INTERVAL/1000));
				}
				else output.setText("             "+(REFRESH_INTERVAL/1000));
				
			}
		});
		panel.add(inc);
		panel.add(output);
		panel.add(dec);
		mainFrame.add(panel);
		mainFrame.pack();
		mainFrame.setSize(300, 75);
		mainFrame.setLocation(100, 10);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
			long start = System.nanoTime();
			if(peek.isConnected()){
				try {
					state.processState();
					System.out.println(state.weather);
					state.setAction();
					setUser();
					updateUserState();
					updateUserAction();
					SoundB.playSound("Dusty1-break1.wav");
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println(state.weatherTime);
				//System.out.println(state.weather);
				//System.out.println(state.latestTime);
				//System.out.println(state.onlineUser);		
			}else{
				//pop up a model dialog
				JOptionPane.showMessageDialog(new JPanel(), "ICE World cannot be reached");
			}
			long end = System.nanoTime();
			System.out.println("time taken: "+(end-start)/1.0e9);
			SoundB.playSound("Dusty1-break1.wav");
		}
	}
	
	public void setGameCanvas(GameCanvas gc){
			this.gc = gc;
	}
	public void setUser(){
		int index = -1;
		for(int i=0; i<state.onlineUser.size();i++){
			if(state.onlineUser.get(i).username.equals(gc.ice.username))
				index = i;
		}
		if(index>=0){
			gc.ice = state.onlineUser.get(index);
			state.onlineUser.remove(index);
		}else{
			System.out.println(gc.ice.uid);
		}
		
	}
	
	public void updateUserState(){
		if(gc != null){
			ArrayList<Icetizen> onlineUser = gc.onlineUser;
			System.out.println("before: "+state.onlineUser.size());
			for(int i=0; i<state.onlineUser.size(); i++){
				Icetizen current = state.onlineUser.get(i);
				if(!onlineUser.contains(current)){
					onlineUser.add(current);
				}else{
					
					Icetizen ice = onlineUser.get(onlineUser.indexOf(current));
					ice.setLocation(new MatrixPoint(current.location.x, current.location.y));
					ice.setIcetizenLook(current.look);
					ice.setTimestamp(current.timestamp);
					//System.out.println(ice.uid);
				}
			}
			System.out.println("after: "+gc.onlineUser.size());
		}
	}
	public void updateUserAction(){
		for(int i=0; i<state.currentAction.size();i++){
			Action act = state.currentAction.get(i);
			System.out.println("update action");
			switch(act.actionType){
				case 1: break;
				case 2: 
					Icetizen ice = new Icetizen(act.uid);
					int index = gc.onlineUser.indexOf(ice);
					if(index >= 0){
						gc.onlineUser.get(index).talk(act.word);
					}else{
//						if (gc.ice.equals(ice)){
//							gc.ice.talk(act.word);
//						}
					}
				case 3: 
					ice = new Icetizen(act.uid);
					index = gc.onlineUser.indexOf(ice);
					if(index >= 0){
						gc.onlineUser.get(index).yell(act.word);
					}else{
//						if (gc.ice.equals(ice)){
//							gc.ice.yell(act.word);
//						}
					}
			}
		}
	}
	
}

