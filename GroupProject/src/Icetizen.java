import java.awt.Point;

import iceworld.given.IcetizenLook;
import iceworld.given.MyIcetizen;


public class Icetizen implements MyIcetizen {
	int icePortID;
	IcetizenLook look;
	int listeningPort;
	String username;
	String timeStamp;
	Point location;
	
	@Override
	public int getIcePortID() {
		// TODO Auto-generated method stub
		return icePortID;
				
	}

	@Override
	public IcetizenLook getIcetizenLook() {
		// TODO Auto-generated method stub
		return look;
	}

	@Override
	public int getListeningPort() {
		// TODO Auto-generated method stub
		return listeningPort;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	
	public String getTimeStamp(){
		return timeStamp;
	}
	
	public Point getLocation(){
		return location;
	}
	@Override
	public void setIcePortID(int icePortID) {
		// TODO Auto-generated method stub
		this.icePortID = icePortID;
		
	}

	@Override
	public void setIcetizenLook(IcetizenLook look) {
		this.look = look;
		
	}

	@Override
	public void setListeningPort(int port) {
		// TODO Auto-generated method stub
		listeningPort=port;
	}

	@Override
	public void setUsername(String arg0) {
		// TODO Auto-generated method stub
		username=arg0;
	}
	
	public void setTimeStamp(String timeStamp){
		this.timeStamp = timeStamp;
	}
	
	public void setLocation(Point p){
		this.location = p;
	}

}
