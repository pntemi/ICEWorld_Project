import java.awt.Point;

import iceworld.given.IcetizenLook;
import iceworld.given.MyIcetizen;


public class Icetizen implements MyIcetizen {
	String username;
	int icePortID;
	IcetizenLook look;
	int listeningPort;
	long timeStamp;
	Point location;
	String ip;
	long type;
	
	@Override
	public int getIcePortID() {
		// TODO Auto-generated method stub
		return (int)icePortID;
				
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
	
	public Long getTimeStamp(){
		return timeStamp;
	}
	
	public Point getLocation(){
		return location;
	}
	
	public String getIP(){
		return ip;
	}
	public long getType(){
		return type;
	}
	@Override
	public void setIcePortID(int l) {
		// TODO Auto-generated method stub
		this.icePortID = l;
		
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
	
	public void setTimeStamp(Long timeStamp){
		this.timeStamp = timeStamp;
	}
	
	public void setLocation(Point p){
		this.location = p;
	}
	
	public void setIP(String ip){
		this.ip = ip;
	}
	public void setType(long type){
		this.type = type;
	}
}
