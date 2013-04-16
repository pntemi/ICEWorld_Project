package character;

import gui.GameCanvas;
import gui.IsometricMap;
import iceworld.given.IcetizenLook;
import iceworld.given.MyIcetizen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Path2D;

import tiles.MatrixPoint;
import tiles.Tile;

public class Icetizen implements MyIcetizen {

	String username;
	int icePortID;
	IcetizenLook look;
	int listeningPort;
	long timeStamp;
	Point location;
	String ip;
	long type;

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

	// private double totalLength, pctLen, accLen;
	private double tileDist = 0, accTileLen = 0;

	@Override
	public int getIcePortID() {
		// TODO Auto-generated method stub
		return icePortID;

	}

	@Override
	public IcetizenLook getIcetizenLook() {
		// TODO Auto-generated method stub
		return look;
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

	public long getType() {
		return type;
	}

	public void setIcePortID(int l) {
		// TODO Auto-generated method stub
		this.icePortID = l;
	}

	public long getTimeStamp() {
		return timeStamp;
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

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
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

	public void setType(long type) {
		this.type = type;
	}

	public double getMiddle() {
		return 0;
	}

	public double getTop() {
		return 0;
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

	// animation
	public void draw(Graphics g, IsometricMap iso) {
		double currTileDist = iso.getTileDistance();
		if (tileDist <= 0) {
			tileDist = iso.getTileDistance();
		}
		if (tileDist > 0 && leavingLoc !=null) {
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

		// tile draw
		if (iceTile != null) {
			g.setColor(Color.YELLOW);
			g.fillPolygon(iceTile.getPolygon());
		}

		charWidth = (int) currTileDist;
		charHeight = (int) (currTileDist * 1.5);

		// rectangle draw
		g.setColor(Color.WHITE);
		g.fillRect(currentX - (charWidth / 2), currentY - charHeight,
				charWidth, charHeight);
	}
}
