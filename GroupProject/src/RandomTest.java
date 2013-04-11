import java.awt.RenderingHints.Key;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class RandomTest {
	public static void main(String args[]) throws ParseException{
		String response="";
		try{
			URL myURL = new URL("http://iceworld.sls-atl.com/api/&cmd=states");
			URLConnection conn = myURL.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String temp;
			while ((temp = in.readLine()) != null) 
				response+=temp;
			in.close();		
			
		}catch(MalformedURLException e){} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(response);
		JSONParser test = new JSONParser();
		JSONObject test2 = (JSONObject) test.parse(response);
		System.out.println("Now JSON:"+test2);
		Set set =  test2.keySet();
		System.out.println("Key is :"+set);
		System.out.println("Status is:"+test2.get("status"));
		
		JSONObject data = (JSONObject) test2.get("data");
	
		Set setData = data.keySet();
		System.out.println("Key in data:"+setData);
		
		/*JSONValue value = new JSONValue();
		 * 
		JSONObject data = (JSONObject) JSONValue.parse(response);
		System.out.print("1");
		JSONObject one = (JSONObject) data.get("data");
		System.out.print("2");
		JSONObject two = (JSONObject) one.get("weather");
		System.out.print("3");
		String three = (String)two.get("condition");
		System.out.println(three);
		*/
	}
}
