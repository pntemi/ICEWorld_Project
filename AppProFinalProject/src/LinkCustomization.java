

import iceworld.given.IcetizenLook;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class LinkCustomization {
	ICEWorldPeek ice;
	LinkedList<String> headgearList;
	LinkedList<String> bodyList;
	LinkedList<String> shirtList;
	LinkedList<String> weaponList;
	/*
	ArrayList<String> headgearList;
	ArrayList<String> bodyList;
	ArrayList<String> shirtList;
	ArrayList<String> weaponList;
	*/
	public LinkCustomization(){
		
		ice = new ICEWorldPeek();
		headgearList = new LinkedList<String>();
		bodyList = new LinkedList<String>();
		shirtList = new LinkedList<String>();
		weaponList = new LinkedList<String>();
		/*
		headgearList = new ArrayList();;
		bodyList = new ArrayList();
		shirtList = new ArrayList();
		weaponList = new ArrayList();
		*/
		try {
			setGResource();
		} catch (MalformedURLException e){}
	}
	//public LinkedList<String> getHeadGearList(){
		
	//}
	public void setGResource() throws MalformedURLException{
		String gresource = ice.getResponse(new URL("http://iceworld.sls-atl.com/api/&cmd=gresources&uid="+0));
		JSONObject source = (JSONObject) JSONValue.parse(gresource);
		JSONArray d = (JSONArray) source.get("data");
		//ArrayList<String> resourceNameList = new ArrayList<String>();
		for(int i=0; i<d.size();i++){
			
			String resourceName = (String) d.get(i);
			if(resourceName.substring(0,1).equals("H")){
				headgearList.add(resourceName);
				//System.out.println(headgearList.get(0));
			}else if(resourceName.substring(0,1).equals("B")){
				bodyList.add(resourceName);
				//System.out.println(bodyList.get(0));
			}else if(resourceName.substring(0,1).equals("S")){
				shirtList.add(resourceName);
				//System.out.println(shirtList.get(0));
			}else{
				weaponList.add(resourceName);
				//System.out.println(weaponList.get(0));
			}
			//resourceNameList.add(resourceName);
		}
	}
	public String getLink(String s) throws MalformedURLException{
		String g = ice.getResponse(new URL("http://iceworld.sls-atl.com/api/&cmd=gurl&gid="+s));
		JSONObject jLink = (JSONObject) JSONValue.parse(g);
		JSONObject data = (JSONObject) jLink.get("data");
		String address = (String) data.get("location");
		//System.out.println(address);
		return address;
	}/*
	public BufferedImage getImage(String address) throws MalformedURLException, IOException{
		BufferedImage part = ImageIO.read(new URL("http://iceworld.sls-atl.com/"+address));
		//BufferedImage image = new BufferedImage(1000,1000,BufferedImage.TYPE_INT_ARGB);
		//Graphics g = image.getGraphics();
		//g.drawImage(part, 0, 0, null);
		//return image;
		return part;
	}*/
	public BufferedImage getImage(String url){
		BufferedImage image = null;
		BufferedImage i = null;
		try {
			image = ImageIO.read(new URL("http://iceworld.sls-atl.com/"+url));
			i = new BufferedImage(1000,1000,BufferedImage.TYPE_INT_ARGB);
			Graphics g = i.getGraphics();
			g.drawImage(image, 0, 0, null);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	return i;
	}
/*
	public BufferedImage getPic(IcetizenLook look) throws IOException{
		BufferedImage b = ImageIO.read(new URL("http://iceworld.sls-atl.com/"+getLink(look.gidB)));
		BufferedImage h = ImageIO.read(new URL("http://iceworld.sls-atl.com/"+getLink(look.gidH)));
		BufferedImage s = ImageIO.read(new URL("http://iceworld.sls-atl.com/"+getLink(look.gidS)));
		BufferedImage w = ImageIO.read(new URL("http://iceworld.sls-atl.com/"+getLink(look.gidW)));

		BufferedImage avatar = new BufferedImage(1000,1000,BufferedImage.TYPE_INT_ARGB);
		Graphics g = avatar.getGraphics();
		g.drawImage(b, 0, 0, null);
		g.drawImage(h, 0, 0, null);
		g.drawImage(s, 0, 0, null);
		g.drawImage(w, 0, 0, null);
		return avatar;
	}
	
	
	
	public static void main(String[] args){
		LinkCustomization c = new LinkCustomization();
		try {
			c.setGResource();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
