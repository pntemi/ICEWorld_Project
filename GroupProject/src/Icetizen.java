import java.awt.Point;

import iceworld.given.IcetizenLook;
import iceworld.given.MyIcetizen;


public class Icetizen implements MyIcetizen {
<<<<<<< HEAD
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
				
=======
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

>>>>>>> add new code
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
<<<<<<< HEAD
	
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
		
=======

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

>>>>>>> add new code
	}

	@Override
	public void setIcetizenLook(IcetizenLook look) {
		this.look = look;
<<<<<<< HEAD
		
=======

>>>>>>> add new code
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
<<<<<<< HEAD
	
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
=======

	public void setTimeStamp(String timeStamp){
		this.timeStamp = timeStamp;
	}

	public void setLocation(Point p){
		this.location = p;
	}
	public double getMiddle(){
		return 0;
	}
	public double getTop(){
		return 0;
	}

>>>>>>> add new code
}
