import iceworld.given.IcetizenLook;
import iceworld.given.MyIcetizen;

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
}
