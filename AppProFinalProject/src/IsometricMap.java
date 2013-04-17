import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.Queue;

import utils.MathUtils;


public class IsometricMap implements Drawable {
	public static final int MAP_ROW = 100;
	public static final int MAP_COLUMN = 100;
	public static final double Z_ROTATION = -MathUtils.DEGREE_45;
	public static final double COS_ROTATE = Math.cos(-MathUtils.DEGREE_45);
	public static final double SIN_ROTATE = Math.sin(-MathUtils.DEGREE_45);
	public static final double X_ADJUSTMENT = Math.sqrt(2.0 / 3.0);
	public static final double Y_ADJUSTMENT = Math.sqrt(2.0);
	public static final int MOVE_PX = 20;
	public static final int MOVE_NONE = 0;
	public static final int MOVE_RIGHT = 1;
	public static final int MOVE_LEFT = 2;
	public static final int MOVE_UP = 3;
	public static final int MOVE_DOWN = 4;

	private int tile_px = 20;
	private double tileDistance;
	private int origin_x = 400;
	private int origin_y = 20;
	private Polygon mapPoly = null;
	private Color mapBgColor = Color.GREEN;
	private int moveMapVer = MOVE_NONE, moveMapHor = MOVE_NONE;

	private Point mousePoint = null;
	
	GameCanvas gameCanvas;

	public IsometricMap(GameCanvas gamecanvas) {
		gameCanvas = gamecanvas;
		// generateBlankMap(MAP_COLUMN, MAP_ROW);
	}

	@Override
	public synchronized void draw(Graphics g) {
		// if (mapPoly == null)
		// return;

		generateBlankMap(MAP_COLUMN, MAP_ROW);
		g.setColor(mapBgColor);
		g.fillPolygon(mapPoly);
		if (mousePoint != null && mapPoly.contains(mousePoint)) {
			drawHighlight(mousePoint, g);
		}
		moveMap();
	}

	private void moveMap() {
		switch (moveMapHor) {
		case MOVE_LEFT:
			moveMapLeft();
			break;
		case MOVE_RIGHT:
			moveMapRight();
			break;
		default:
			break;
		}
		switch (moveMapVer) {
		case MOVE_UP:
			moveMapUp();
			break;
		case MOVE_DOWN:
			moveMapDown();
			break;
		default:
			break;
		}
	}

	public void drawHighlight(Point mouse, Graphics g) {
		MatrixPoint mp = getMatrixPoint(mouse);
		g.setColor(Color.RED);
		g.fillPolygon(getTile(mp.getColumn(), mp.getRow()).getPolygon());
		g.drawString("column: " + mp.getColumn(), 0, 20);
		g.drawString("row: " + mp.getRow(), 0, 40);
	}

	private void generateBlankMap(int columns, int rows) {
		float zoom = gameCanvas.getZoomLevel();
		float tilePX = zoom * this.tile_px;
		float mapWidth = columns * tilePX;
		float mapHeight = rows * tilePX;
		int realWidth = (int) Math.round(mapWidth * Math.sin(Math.PI / 3));
		int realHeight = (int) Math.round(mapHeight * Math.cos(Math.PI / 3));

		int[] x = { origin_x, origin_x + realWidth, origin_x,
				origin_x - realWidth };
		int[] y = { origin_y, origin_y + realHeight, origin_y + realHeight * 2,
				origin_y + realHeight };

		mapPoly = new Polygon(x, y, 4);
		updateTileDistance();
	}

	public void setMousePoint(Point mouse) {
		mousePoint = mouse;
	}

	public MatrixPoint getMatrixPoint(Point p) {

		float zoom = gameCanvas.getZoomLevel();
		float tilePX = zoom * this.tile_px;
		double x = X_ADJUSTMENT * (p.x - origin_x);
		double y = Y_ADJUSTMENT * (p.y - origin_y);
		double unrealX = x * Math.cos(Z_ROTATION) - y * Math.sin(Z_ROTATION);
		double unrealY = x * Math.sin(Z_ROTATION) + y * Math.cos(Z_ROTATION);
		return new MatrixPoint((int) (unrealX / tilePX),
				(int) (unrealY / tilePX));
	}

	public Tile getTile(int column, int row) {
		float zoom = gameCanvas.getZoomLevel();
		float tilePX = zoom * this.tile_px;
		float tileX = column * tilePX;
		float tileY = row * tilePX;
		int realTileX = origin_x
				+ (int) Math.round((tileX * Math.cos(-Z_ROTATION) - tileY
						* Math.sin(-Z_ROTATION))
						/ X_ADJUSTMENT);
		int realTileY = origin_y
				+ (int) Math.round((tileX * Math.sin(-Z_ROTATION) + tileY
						* Math.cos(-Z_ROTATION))
						/ Y_ADJUSTMENT);
		int tileRealWidth = (int) Math.round(tilePX * MathUtils.SIN_60);
		int tileRealHeight = (int) Math.round(tilePX * MathUtils.COS_60);
		int[] xs = { realTileX, realTileX + tileRealWidth, realTileX,
				realTileX - tileRealWidth };
		int[] ys = { realTileY, realTileY + tileRealHeight,
				realTileY + tileRealHeight * 2, realTileY + tileRealHeight };
		Tile tile = new Tile(xs, ys);
		return tile;
	}

	public void updateTileDistance() {
		Tile tile1 = getTile(0, 0);
		Tile tile2 = getTile(0, 1);
		tileDistance = MathUtils.distance(tile1.getXs()[0], tile1.getYs()[0],
				tile2.getXs()[0], tile2.getYs()[0]);
	}

	public double getTileDistance() {
		return tileDistance;
	}

	public Point getMiddlePointOfTile(int column, int row) {

		float zoom = gameCanvas.getZoomLevel();
		float tilePX = zoom * this.tile_px;
		Tile tile = getTile(column, row);
		return new Point(tile.getXs()[0], tile.getYs()[0]
				+ (int) Math.round(tilePX * MathUtils.COS_60));
	}

	public boolean mapContain(Point mouse) {
		if (mapPoly.contains(mouse))
			return true;
		return false;
	}

	public void moveMapUp() {
		float zoom = gameCanvas.getZoomLevel();
		float tilePX = zoom * this.tile_px;
		int height = (int) mapPoly.getBounds().getHeight();
		if (origin_y + height <= GameCanvas.HEIGHT - tilePX) {
			// origin_y = GameCanvas.HEIGHT - height;
		} else {
			origin_y -= MOVE_PX;
		}
	}

	public void moveMapDown() {

		float zoom = gameCanvas.getZoomLevel();
		float tilePX = zoom * this.tile_px;
		if (origin_y >= tilePX) {
			origin_y =(int) tilePX;
		} else {
			origin_y += MOVE_PX;
		}
	}

	public void moveMapLeft() {

		float zoom = gameCanvas.getZoomLevel();
		float tilePX = zoom * this.tile_px;
		int width = (int) mapPoly.getBounds().getWidth();
		if (origin_x + width / 2 <= GameCanvas.WIDTH - tilePX) {
			// origin_x = GameCanvas.WIDTH - tile_px - width;
		} else {
			origin_x -= MOVE_PX;
		}

	}

	public void moveMapRight() {

		float zoom = gameCanvas.getZoomLevel();
		float tilePX = zoom * this.tile_px;
		int width = (int) mapPoly.getBounds().getWidth();
		if (origin_x - width / 2 >= tilePX) {
			// origin_x = width/2 - tile_px;
		} else {
			origin_x += MOVE_PX;
		}
	}

	public int getMoveMapVer() {
		return moveMapVer;
	}

	public void setMoveMapVer(int moveMap) {
		this.moveMapVer = moveMap;
	}

	public int getMoveMapHor() {
		return moveMapHor;
	}

	public void setMoveMapHor(int moveMap) {
		this.moveMapHor = moveMap;
	}
}