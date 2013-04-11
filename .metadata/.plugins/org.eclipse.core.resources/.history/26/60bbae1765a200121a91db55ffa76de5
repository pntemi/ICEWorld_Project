import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class IceWorldState {
	protected String timeStamp;
	protected String weather;
	protected ArrayList<Icetizen> onlineUser;
	protected ICEWorldPeek peek = new ICEWorldPeek();
	
	public IceWorldState(){
		this("","",null);
	}
	public IceWorldState(String timeStamp, String weather, ArrayList<Icetizen> onlineUser){
		this.timeStamp = timeStamp;
		this.weather = weather;
		this.onlineUser = onlineUser;
	}
	public void processState() {
		setTime();
		setWeather();
		setOnlineUser();
	}
	public void setTime(){
		try{
			String response = peek.getResponse(new URL(" http://iceworld.sls-atl.com/api/&cmd=time"));
			JSONObject json = (JSONObject) JSONValue.parse(response);
			timeStamp = (String)json.get("data");
		}catch(MalformedURLException e){}
	}
	public void setWeather(){
		try{
			String response = peek.getResponse(new URL("http://iceworld.sls-atl.com/api/&cmd=states"));
			
		}catch(MalformedURLException e){}
	}
	public void setOnlineUser(){
		
	}
	public void printState(){
		
	}
}
