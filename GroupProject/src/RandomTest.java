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
		//JSONValue value = new JSONValue();
		JSONObject data = (JSONObject) JSONValue.parse(response);
		System.out.print("1");
		JSONObject one = (JSONObject) data.get("data");
		System.out.print("2");
		JSONObject two = (JSONObject) one.get("weather");
		System.out.print("3");
		String three = (String)two.get("condition");
		System.out.println(three);
	}
}
/*class ParseContext {

    private Map<String, List<String>> contentMap;

    private String currentKey;
    private List<String> currentList;

    private boolean continueParsing = true;
    private ParseState state = new StartParseState();

    public Map<String, List<String>> parseContent(String content) {
        StringTokenizer tokenizer = new StringTokenizer(content, "{}:,", true);
        while (continueParsing) {
            state.parse(tokenizer);
        }
        System.out.println("1");
        return contentMap;
    }

    interface ParseState {
        void parse(StringTokenizer tokenizer);
    }

    class StartParseState implements ParseState {
        @Override
        public void parse(StringTokenizer tokenizer) {
        	System.out.println("2");
        	while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                if (!"data".equals(token)) {
                    continue;
                }else if(!"icetizen".equals(token)){
                	continue;
                }

                contentMap = new LinkedHashMap<String, List<String>>();

                tokenizer.nextToken();
                tokenizer.nextToken();

                state = new KeyParseState();
                break;
            }
        }
    }

    class KeyParseState implements ParseState {
        @Override
        public void parse(StringTokenizer tokenizer) {
        	System.out.println("3");
            if (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                if (",".equals(token)) {
                    token = tokenizer.nextToken();
                }
                if ("}".equals(token)) {
                    state = new EndParseState();
                    return;
                }
                currentKey = token;
                tokenizer.nextToken();
                state = new ListParseState();
            }
        }
    }

    class ListParseState implements ParseState {
        @Override
        public void parse(StringTokenizer tokenizer) {
        	System.out.println("4");
        	currentList = new ArrayList<String>();
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                if ("}".equals(token)) {
                    break;
                }
                if (":".equals(token)) {
                    currentList.add(tokenizer.nextToken());
                }
            }
            contentMap.put(currentKey, currentList);
            state = new KeyParseState();
        }
    }

    class EndParseState implements ParseState {
        @Override
        public void parse(StringTokenizer tokenizer) {
            continueParsing = false;
        }
    }
}*/