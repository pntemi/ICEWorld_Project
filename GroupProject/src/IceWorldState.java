import iceworld.given.MyIcetizen;

import java.awt.Point;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class IceWorldState {
	protected Long weatherTime;
	protected String weather;
	protected ArrayList<Icetizen> onlineUser;
	
	
	public IceWorldState(){
		this(0L,"",null);
	}
	public IceWorldState(Long weatherTime, String weather, ArrayList<Icetizen> onlineUser){
		this.weatherTime = weatherTime;
		this.weather = weather;
		this.onlineUser = onlineUser;
	}
	public void processState() throws MalformedURLException {
		ICEWorldPeek peek = new ICEWorldPeek();
		String response = peek.getResponse(new URL("http://iceworld.sls-atl.com/api/&cmd=states"));
		JSONParser parser = new JSONParser();
		JSONObject json;
		try {
			json = (JSONObject)parser.parse(response);
			JSONObject data = (JSONObject)json.get("data");
			JSONObject wther = (JSONObject)data.get("weather");
			JSONObject user = (JSONObject)data.get("icetizen");
			setWeather(wther);
			setOnlineUser(user);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void setWeather(JSONObject wther){
		weather = (String) wther.get("condition");
		weatherTime = (long) wther.get("last_change");
	}
	public void setOnlineUser(JSONObject user){
		Set userKey = user.keySet();
		System.out.println(userKey);
		Object[] uid = userKey.toArray();
		onlineUser = new ArrayList();
		for(int i=0;i<uid.length;i++){
			JSONObject info = (JSONObject)user.get(""+uid[i]);
			JSONObject ice = (JSONObject)info.get("user");
			JSONObject action = (JSONObject)info.get("last_known_destination");
			Icetizen icetizen = new Icetizen();
			if(onlineUser.add(icetizen)){
				icetizen.setUsername((String) ice.get("username"));
				System.out.println("1");
				icetizen.setType((long)ice.get("type"));
				int pid = (Integer)ice.get("pid");
				System.out.println("2");
				icetizen.setIcePortID(pid);
				System.out.println("3");
				icetizen.setIP((String)ice.get("ip"));
				icetizen.setTimeStamp((Long)action.get("timestamp"));
				Point point = (Point)action.get("position");
				icetizen.setLocation(point);
				System.out.println(icetizen.username);
			}
		}
	}
	public void printState(){
		
	}
	public String getWeather(){
		return weather;
	}
	public Long getWeatherTime(){
		return weatherTime;
	}
	public ArrayList<Icetizen> getOnlineUser(){
		return onlineUser;
	}
}
