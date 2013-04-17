package utils;

public class MathUtils {
	public static final double DEGREE_60 = Math.PI / 3;
	public static final double DEGREE_45 = Math.PI / 4;
	public static final double SIN_60 = Math.sin(DEGREE_60);
	public static final double COS_60 = Math.cos(DEGREE_60);
	public static final double SIN_45 = Math.sin(DEGREE_45);
	public static final double COS_45 = Math.cos(DEGREE_45);

	public static double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
}