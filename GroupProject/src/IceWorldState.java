import iceworld.given.MyIcetizen;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class IceWorldState {
	protected Long weatherTime;
	protected String weather;
	protected ArrayList<MyIcetizen> onlineUser;
	
	
	public IceWorldState(){
		this(0L,"",null);
	}
	public IceWorldState(Long weatherTime, String weather, ArrayList<MyIcetizen> onlineUser){
		this.weatherTime = weatherTime;
		this.weather = weather;
		this.onlineUser = onlineUser;
	}
	public void processState() throws MalformedURLException {
		ICEWorldPeek peek = new ICEWorldPeek();
		String response = peek.getResponse(new URL("http://iceworld.sls-atl.com/api/&cmd=states"));
		JSONObject json = (JSONObject)JSONValue.parse(response);
		JSONObject data = (JSONObject)json.get("data");
		JSONObject wther = (JSONObject)data.get("weather");
		JSONObject user = (JSONObject)data.get("icetizen");
		setWeather(wther);
		setOnlineUser(user);
	}
	
	public void setWeather(JSONObject wther){
		weather = (String) wther.get("condition");
		weatherTime = (Long) wther.get("last_change");
	}
	public void setOnlineUser(JSONObject user){
		Set userKey = user.keySet();
		System.out.println(userKey);
		Object[] uid = userKey.toArray();
		for(int i=0;i<uid.length;i++){
			JSONObject info = (JSONObject)user.get(""+uid[i]);
			JSONObject ice = (JSONObject)info.get("user");
			
			System.out.println(ice.keySet());
			int type = (int)ice.get("type");
			MyIcetizen icetizen = null;
			if(type == 1){
				icetizen = new Icetizen();
			}else if(type == 0){
				icetizen = new Alien();
			}
			onlineUser.add(icetizen);
			
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
	public ArrayList<MyIcetizen> getOnlineUser(){
		return onlineUser;
	}
}
