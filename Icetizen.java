import iceworld.given.IcetizenLook;
import iceworld.given.MyIcetizen;

<<<<<<< HEAD
public class Icetizen implements MyIcetizen {
	int IcePortID;
	IcetizenLook look;
	int listenPort;
	String username;
	IcetizenLook look1;
	int port;
	String username1;
	int iceportID;
	String username2;

	public Icetizen() {

	}

	public int getIcePortID() {
		return IcePortID;
	}

	public IcetizenLook getIcetizenLook() {
		return look;
	}

	public int getListeningPort() {
		return listenPort;
	}

	public String getUsername() {
		return username;

	}

	public void setIcetizenLook(IcetizenLook e) {
		look1 = e;

	}

	public void setListeningPort(int e) {
		port = e;
	}
	public void setUserName(String e){
		username1=e;
	}

	@Override
	public void setIcePortID(int e) {
		// TODO Auto-generated method stub
		iceportID=e;
	}

	@Override
	public void setUsername(String e) {
		// TODO Auto-generated method stub
		username2=e;
	}
=======

public class Icetizen implements MyIcetizen {

	int icePortID;
	IcetizenLook look;
	int listenPort;
	String username;
	
	int port;
	
	int iceportID;
	
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
		return port;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public void setIcePortID(int arg0) {
		// TODO Auto-generated method stub
		icePortID = arg0;
		
	}

	@Override
	public void setIcetizenLook(IcetizenLook arg0) {
		look = arg0;
		
	}

	@Override
	public void setListeningPort(int arg0) {
		// TODO Auto-generated method stub
		listenPort=arg0;
	}

	@Override
	public void setUsername(String arg0) {
		// TODO Auto-generated method stub
		username=arg0;
	}

>>>>>>> add new iceport
}
