import iceworld.given.IcetizenLook;
import iceworld.given.MyIcetizen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Icetizen implements MyIcetizen {

	int uid;
	String username;
	int icePortID;
	IcetizenLook look;
	int listeningPort;
	long timestamp;
	Point location;
	String ip;
	int type;
	String message;

	private int charWidth = 40;
	private int charHeight = 40;

	private MatrixPoint currDest;
	private MatrixPoint currLoc;
	private MatrixPoint leavingLoc;
	private Tile iceTile;
	private boolean destChange = false;

	private int walkTimePerTile = 10;
	private int currentX;
	private int currentY;

	private BufferedImage avatar;

	private Timer talkingTimer = new Timer(10000, null);
	private boolean talking = false;
	private boolean yelling = false;
	// private double totalLength, pctLen, accLen;
	private double tileDist = 0, accTileLen = 0;

	public Icetizen() {
		this(0);
	}

	public Icetizen(int uid) {
		this.uid = uid;
		talkingTimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				talking = false;
				yelling = false;
			}
		});
		talkingTimer.setRepeats(false);
	}

	@Override
	public int getIcePortID() {
		// TODO Auto-generated method stub
		return icePortID;

	}

	public int getUID() {
		return uid;
	}

	@Override
	public IcetizenLook getIcetizenLook() {
		// TODO Auto-generated method stub
		return look;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public int getListeningPort() {
		// TODO Auto-generated method stub
		return listeningPort;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	public String getIP() {
		return ip;
	}

	public int getType() {
		return type;
	}

	public void setIcePortID(int l) {
		// TODO Auto-generated method stub
		this.icePortID = l;
	}

	public void setUID(int uid) {
		this.uid = uid;
	}

	public long getTimeStamp() {
		return timestamp;
	}

	public Point getLocation() {
		return location;
	}

	@Override
	public void setIcetizenLook(IcetizenLook look) {
		this.look = look;

	}

	@Override
	public void setListeningPort(int port) {
		// TODO Auto-generated method stub
		listeningPort = port;
	}

	@Override
	public void setUsername(String arg0) {
		// TODO Auto-generated method stub
		username = arg0;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public void setLocation(MatrixPoint mp) {
		Point p = new Point(mp.getColumn(), mp.getRow());
		currDest = mp;
		if (leavingLoc == null || currLoc == null) {
			leavingLoc = currLoc = mp;
		}
		// tileDist = iso.getTileDistance();
		this.location = p;
	}

	public void setIP(String ip) {
		this.ip = ip;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getMiddle() {
		return 0;
	}

	public double getTop() {
		return 0;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCurrentX() {
		return currentX;
	}

	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}

	public int getCurrentY() {
		return currentY;
	}

	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}

	public boolean isDestChange() {
		return destChange;
	}

	public void draw(Graphics g, IsometricMap iso) throws MalformedURLException {
		double currTileDist = iso.getTileDistance();
		if (tileDist <= 0) {
			tileDist = iso.getTileDistance();
		}
		if (tileDist > 0 && leavingLoc != null) {
			if (tileDist != currTileDist) {
				accTileLen = accTileLen * (currTileDist / tileDist);
				tileDist = currTileDist;
			}
			double stepLen = tileDist / walkTimePerTile;
			double pptLen = accTileLen / tileDist;
			if (!leavingLoc.equals(currLoc) && pptLen < 1) {
				Point p1 = iso.getMiddlePointOfTile(leavingLoc.getColumn(),
						leavingLoc.getRow());
				Point p2 = iso.getMiddlePointOfTile(currLoc.getColumn(),
						currLoc.getRow());
				accTileLen += stepLen;
				pptLen = accTileLen / tileDist;
				iceTile = (pptLen > 0.5) ? iso.getTile(currLoc.getColumn(),
						currLoc.getRow()) : iso.getTile(leavingLoc.getColumn(),
						leavingLoc.getRow());
				if (pptLen >= 1) {
					currentX = p2.x;
					currentY = p2.y;
				} else {
					currentX = (int) Math.round(p1.x * (1 - pptLen) + p2.x
							* pptLen);
					currentY = (int) Math.round(p1.y * (1 - pptLen) + p2.y
							* pptLen);
				}
			} else if (!leavingLoc.equals(currDest)) {
				accTileLen = 0;
				leavingLoc = currLoc;
				currLoc = new MatrixPoint(currLoc.getColumn(), currLoc.getRow());
				int xDif = currDest.getColumn() - currLoc.getColumn();
				int yDif = currDest.getRow() - currLoc.getRow();
				if (xDif != 0 || yDif != 0) {
					if (Math.abs(xDif) > Math.abs(yDif)) {
						currLoc.setColumn((xDif > 0) ? (currLoc.getColumn() + 1)
								: (currLoc.getColumn() - 1));
					} else {
						currLoc.setRow((yDif > 0) ? (currLoc.getRow() + 1)
								: (currLoc.getRow() - 1));
					}
				}
				// int drawY = 20;
				// g.drawString("xDif : " + xDif, 10, drawY += 20);
				// g.drawString("yDif : " + yDif, 10, drawY += 20);
				// g.drawString("currLoc : " + currLoc.getColumn() + "," +
				// currLoc.getRow(), 10, drawY += 20);
				// g.drawString("leavingLoc : " + leavingLoc.getColumn() + "," +
				// leavingLoc.getRow(), 10, drawY += 20);
				// g.drawString("currDest : " + currDest.getColumn() + "," +
				// currDest.getRow(), 10, drawY += 20);
			} else {
				Point currP = iso.getMiddlePointOfTile(currLoc.getColumn(),
						currLoc.getRow());
				currentX = currP.x;
				currentY = currP.y;
				iceTile = iso.getTile(currLoc.getColumn(), currLoc.getRow());
			}
		}

		// draw tile
		if (iceTile != null) {
			g.setColor(Color.YELLOW);
			g.fillPolygon(iceTile.getPolygon());
		}

		charWidth = (int) currTileDist;
		charHeight = (int) (currTileDist * 1.5);

		// draw icetizen
		BufferedImage b;
		if (look != null && look.gidB != null && look.gidH != null
				&& look.gidS != null && look.gidW != null) {
			System.out.println("LOOK :" + look);
			try {
				b = getPic(look);
				// this.avatar = new
				// BufferedImage(40,40,BufferedImage.TYPE_INT_ARGB);
				// g = avatar.getGraphics();
				// g.setColor(Color.white);
				g.drawImage(b, currentX - (charWidth / 2), currentY
						- charHeight, charWidth+100, charHeight+100, null);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else{
			g.drawRect(currentX - (charWidth / 2), currentY - charHeight,charWidth, charHeight);
		}
		// ImageIcon image = new ImageIcon(new
		// URL("http://dl.dropboxusercontent.com/u/97749501/icetizen.PNG"));
		// Image im = image.getImage();
		// g=im.getGraphics();
		// g.drawImage(im,currentX - (charWidth / 2), currentY -
		// charHeight,charWidth, charHeight,null );
		// g.drawRect(currentX - (charWidth / 2), currentY -
		// charHeight,charWidth, charHeight);
		g.drawString((username == null) ? "ALIEN" : username, currentX
				- charWidth - 2, currentY - charHeight - 3);
		// talk and yell
		if (talking) {
			drawTalk(g);
		}
		if (yelling) {
			drawYell(g);
		}

	}

	@Override
	public boolean equals(Object ice) {
		return this.uid == ((Icetizen) ice).uid;
	}

	public void yell(String msg) {
		message = msg;
		yelling = true;
		talkingTimer.start();
	}

	public void talk(String msg) {
		message = msg;
		talking = true;
		talkingTimer.start();
	}

	public void drawTalk(Graphics g) {
		Polygon arrow = new Polygon();
		int charHi = (int) (tileDist * 1.5);
		arrow.addPoint(currentX, currentY - charHi);
		arrow.addPoint(currentX - 40, currentY - 50 - charHi);
		arrow.addPoint(currentX + 40, currentY - 50 - charHi);

		g.setColor(Color.WHITE);
		g.fillRect(currentX - 100, currentY - 100 - charHi, 200, 50);
		g.fillPolygon(arrow);
		g.setColor(Color.BLACK);
		g.drawString(message, currentX - 90, currentY - 75 - charHi);
	}

	public void drawYell(Graphics g) {
		Polygon arrow = new Polygon();
		int charHi = (int) (tileDist * 1.5);
		arrow.addPoint(currentX, currentY - charHi);
		arrow.addPoint(currentX - 40, currentY - 50 - charHi);
		arrow.addPoint(currentX + 40, currentY - 50 - charHi);

		g.setColor(Color.WHITE);
		g.drawRect(0, 0, 700, 400);
		g.fillPolygon(arrow);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Algerian", Font.BOLD, 50));
		g.drawString(message, 20, 100);
		g.setFont(new Font("Arial", Font.PLAIN, 16));
	}

	/*
	 * public String getLink(String s) throws MalformedURLException{
	 * ICEWorldPeek peek = new ICEWorldPeek(); String g = peek.getResponse(new
	 * URL("http://iceworld.sls-atl.com/api/&cmd=gurl&gid="+s)); JSONObject
	 * jLink = (JSONObject) JSONValue.parse(g); JSONObject data = (JSONObject)
	 * jLink.get("data"); String address = (String) data.get("location");
	 * //System.out.println(address); return address; }
	 */
	public BufferedImage getPic(IcetizenLook look) throws IOException {
		ICEWorldPeek peek = new ICEWorldPeek();
		String response = peek.getResponse(new URL(
				"http://iceworld.sls-atl.com/api/&cmd=gurl&gid=" + look.gidB));
		// System.out.println(response);
		// System.out.println(look.gidB);
		JSONObject data = (JSONObject) JSONValue.parse(response);
		JSONObject location = (JSONObject) data.get("data");
		String link = location.get("location").toString();

		// String link =((JSONObject)
		// data.get("data")).get("location").toString();
		BufferedImage b = ImageIO.read(new URL("http://iceworld.sls-atl.com/"
				+ link));

		response = peek.getResponse(new URL(
				"http://iceworld.sls-atl.com/api/&cmd=gurl&gid=" + look.gidH));
		data = (JSONObject) JSONValue.parse(response);
		link = ((JSONObject) data.get("data")).get("location").toString();
		BufferedImage h = ImageIO.read(new URL("http://iceworld.sls-atl.com/"
				+ link));

		response = peek.getResponse(new URL(
				"http://iceworld.sls-atl.com/api/&cmd=gurl&gid=" + look.gidS));
		data = (JSONObject) JSONValue.parse(response);
		link = ((JSONObject) data.get("data")).get("location").toString();
		BufferedImage s = ImageIO.read(new URL("http://iceworld.sls-atl.com/"
				+ link));

		response = peek.getResponse(new URL(
				"http://iceworld.sls-atl.com/api/&cmd=gurl&gid=" + look.gidW));
		data = (JSONObject) JSONValue.parse(response);
		link = ((JSONObject) data.get("data")).get("location").toString();
		BufferedImage w = ImageIO.read(new URL("http://iceworld.sls-atl.com/"
				+ link));

		BufferedImage avatar = new BufferedImage(1000, 1000,
				BufferedImage.TYPE_INT_ARGB);
		Graphics g = avatar.getGraphics();
		g.drawImage(b, 0, 0, null);
		g.drawImage(h, 0, 0, null);
		g.drawImage(s, 0, 0, null);
		g.drawImage(w, 0, 0, null);
		return avatar;
	}
}