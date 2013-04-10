import iceworld.given.IcetizenLook;
import iceworld.given.MyIcetizen;


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

}
