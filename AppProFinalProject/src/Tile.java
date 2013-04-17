import java.awt.Point;
import java.awt.Polygon;

public class Tile {
	private int[] xs;
	private int[] ys;
	private Polygon polygon;

	public Tile(int[] xs, int[] ys) {
		this.xs = xs;
		this.ys = ys;
		polygon = new Polygon(xs, ys, xs.length);
	}

	public int[] getXs() {
		return xs;
	}

	public void setXs(int[] xs) {
		this.xs = xs;
	}

	public int[] getYs() {
		return ys;
	}

	public void setYs(int[] ys) {
		this.ys = ys;
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}
}