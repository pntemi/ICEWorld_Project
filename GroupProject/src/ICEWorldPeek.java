import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ICEWorldPeek{
	public boolean isConnected(){
		boolean connect = false;
		try {
			URL myURL = new URL("http://iceworld.sls-atl.com/");
		    URLConnection myURLConnection = myURL.openConnection();
		    myURLConnection.connect();
		    connect = true;
		} 
		catch (MalformedURLException e) {
			System.out.println("-----------------------------");
			System.out.println("/t"+"Invalid URL");
			System.out.println("-----------------------------");
		} 
		catch (IOException e) {   
			System.out.println("-----------------------------");
			System.out.println("/t"+"The URL cannot be reached");
			System.out.println("-----------------------------");
		}
		return connect;
	}
	public static String getResponse(){
		URL myURL = null; 
		Scanner kb = new Scanner(System.in);
		System.out.print("Enter the request: ");
		String request = kb.nextLine();
		System.out.println("-----------------------------");
		System.out.println("Waiting For the Connection...");
		System.out.println("-----------------------------");
		try{
			if(request.equals("time")){
				myURL = new URL("http://iceworld.sls-atl.com/api/&cmd=time");
			}else if(request.equals("states")||request.equals("State")){
				myURL = new URL("http://iceworld.sls-atl.com/api/&cmd=states");
			}else if(request.equals("actions")){
				System.out.print("From: ");
				String from = kb.nextLine();
				myURL = new URL("http://iceworld.sls-atl.com/api/&cmd=actions&from="+from);
			}else if(request.equals("gresources")){
				System.out.print("Enter uid: ");
				String uid = kb.nextLine();
				myURL = new URL("http://iceworld.sls-atl.com/api/&cmd=gresources&uid="+uid);
			}else if(request.equals("gurl")){
				System.out.print("Enter gid: ");
				String gid = kb.nextLine();
				myURL = new URL("http://iceworld.sls-atl.com/api/&cmd=gurl&gid="+gid);
			}else{
				myURL = null;
				System.out.println("Invalid Request");
				System.out.println("-----------------------------");
				return getResponse();
			}		
		}catch (MalformedURLException e) {
			return getResponse();
		}
			
		return getResponse(myURL);
	}
	
	public static String getResponse(URL myURL){
		String inputLine = "";
		try{
			URLConnection conn = myURL.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String temp;
			while ((temp = in.readLine()) != null) 
				inputLine+=temp;
			in.close();		
		}catch (IOException e){
			getResponse();
		}
		return inputLine;
		
	}
	public static void main(String[] args){
		ICEWorldPeek peek = new ICEWorldPeek();
		System.out.println("     --ICE World Peek--");
		try{
			if(peek.isConnected()){
				System.out.println("-----------------------------");
				System.out.println("ICE World is reachable");
				System.out.println("-----------------------------");
				System.out.println("Response: "+getResponse());
				System.out.println("-----------------------------");
			}
		}catch(NullPointerException e){}
	}
}
