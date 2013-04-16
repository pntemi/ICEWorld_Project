import iceworld.given.IcetizenLook;

import java.awt.Point;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import tiles.MatrixPoint;

public class IceWorldState {
	protected String latestTime;
	protected String weatherTime;
	protected String weather;
	protected ArrayList<Icetizen> onlineUser;
	protected ArrayList<Action> currentAction;
	protected ICEWorldPeek peek = new ICEWorldPeek();

	public IceWorldState() throws MalformedURLException {
		this("", "", null, "");
	}

	public IceWorldState(String weatherTime, String weather,
			ArrayList<Icetizen> onlineUser, String latestTime)
			throws MalformedURLException {
		this.weatherTime = weatherTime;
		this.weather = weather;
		this.onlineUser = onlineUser;
		ICEWorldPeek ploy = new ICEWorldPeek();
		String response = ploy.getResponse(new URL(
				"http://iceworld.sls-atl.com/api/&cmd=time"));
		this.latestTime = response.substring(20, response.indexOf("\"}") - 1);
	}

	public void processState() throws MalformedURLException {
		String response = peek.getResponse(new URL(
				"http://iceworld.sls-atl.com/api/&cmd=states"));
		JSONParser parser = new JSONParser();
		JSONObject json;
		try {
			json = (JSONObject) parser.parse(response);
			JSONObject data = (JSONObject) json.get("data");
			JSONObject wther = (JSONObject) data.get("weather");
			JSONObject user = (JSONObject) data.get("icetizen");
			setWeather(wther);
			setOnlineUser(user);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setWeather(JSONObject wther) {
		weather = (String) wther.get("condition");
		weatherTime = wther.get("last_change").toString();
	}

	public void setOnlineUser(JSONObject user) throws MalformedURLException {
		Set userKey = user.keySet();
		int num = userKey.size();
		// int[] uid = new int[num];
		int i = 0;
		int uid;
		System.out.println(userKey);
		onlineUser = new ArrayList();
		// System.out.print(uid);

		for (Object key : userKey) {

			Icetizen icetizen = new Icetizen(Integer.parseInt((String) key));
			uid = Integer.parseInt((String) key);
			JSONObject info = (JSONObject) user.get((String) key);
			JSONObject ice = (JSONObject) info.get("user");
			JSONObject action = (JSONObject) info.get("last_known_destination");
			onlineUser.add(icetizen);

			String username = ice.get("username").toString();
			System.out.println(username);
			icetizen.setUsername(username);

			int type = Integer.parseInt((ice.get("type").toString()));
			System.out.println(type);
			icetizen.setType(type);

			String ip;
			if (ice.get("ip") == null)
				ip = "";
			else
				ip = ice.get("ip").toString();
			System.out.println(ip);
			icetizen.setIP(ip);

			int pid = Integer.parseInt(ice.get("pid").toString());
			System.out.println(pid);
			icetizen.setIcePortID(pid);

			int port = Integer.parseInt(ice.get("port").toString());
			System.out.println(port);
			icetizen.setListeningPort(port);

			long time;
			if (action.get("timestamp") == null)
				time = -1;
			else
				time = Long.parseLong(action.get("timestamp").toString());
			System.out.println(time);
			icetizen.setTimestamp(time);

			if (action.get("position") == null)
				icetizen.setLocation(new MatrixPoint(0, 0));
			else {
				String point = (String) action.get("position");
				int x = Integer
						.parseInt(point.substring(1, point.indexOf(",")));
				System.out.println(x);
				int y = Integer.parseInt(point.substring(
						point.indexOf(",") + 1, point.indexOf(")")));
				System.out.println(y);
				icetizen.setLocation(new MatrixPoint(x, y));
			}
			String looks = peek.getResponse(new URL("http://iceworld.sls-atl.com/api/&cmd=gresources&uid="	+ uid));
			JSONObject jlooks = (JSONObject) JSONValue.parse(looks);
			JSONArray d = (JSONArray) jlooks.get("data");
			JSONObject data = (JSONObject) d.get(0);
			IcetizenLook f = new IcetizenLook();
			f.gidB = (String) data.get("B");
			f.gidH = (String) data.get("H");
			f.gidS = (String) data.get("S");
			f.gidW = (String) data.get("W");
			icetizen.setIcetizenLook(f);

			// System.out.println("B: "+f.gidB+"   H: "+f.gidH+"   S:"+f.gidS+"    W:"+f.gidW);
			System.out.println("-------------------");
		}
		// setAllTheLook(uid);
	}

	public void setAction() throws MalformedURLException, ParseException {
		ICEWorldPeek peek = new ICEWorldPeek();
		String url = "http://iceworld.sls-atl.com/api/&cmd=actions&from="
				+ latestTime;
		String response = peek.getResponse(new URL(url));
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(response);
		JSONArray actionArray = (JSONArray) json.get("data");
		for (int i = actionArray.size() - 1; i >= 0; i--) {
			JSONObject data = (JSONObject) actionArray.get(i);
			Action action = new Action(latestTime);
			latestTime = (String) data.get("timestamp");
			action.setTimestamp(latestTime);
			action.setActionType(Integer.parseInt((String) data
					.get("action_type")));
			action.setAID(Long.parseLong((String) data.get("aid")));
			String detail = (String) data.get("detail");
			if (action.actionType == 1) {
				int x = Integer.parseInt(detail.substring(1,
						detail.indexOf(",")));
				System.out.println(x);
				int y = Integer.parseInt(detail.substring(
						detail.indexOf(",") + 1, detail.indexOf(")")));
				System.out.println(y);
				action.setPoint(new Point(x, y));
			} else {
				action.setWord(detail);
			}
		}
	}

	public void printState() {

	}

	public String getWeather() {
		return weather;
	}

	public String getWeatherTime() {
		return weatherTime;
	}

	public ArrayList<Icetizen> getOnlineUser() {
		return onlineUser;
	}

	public ArrayList<Action> getAction() {
		return currentAction;
	}
}
