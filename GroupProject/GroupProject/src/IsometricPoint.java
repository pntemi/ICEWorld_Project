
package tiles;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;

import addon.Drawable;

public class IsometricPoint {

        private int width = 20;
        private int height = 10;
        private boolean highlighted = false;

        private Polygon poly;

        private int x, y, realX, realY;

        protected int tileX, tileY;

        public IsometricPoint(int x, int y) {

                x++;
                y++;

                this.realX = (x * width / 2) + (y * width / 2);
                this.realY = (y * height / 2) - (x * height / 2);

                this.x = x;
                this.y = y;

                int[] px = { x * width + width, x * width, x * width - width, x * width };
                int[] py = { y * height, y * height + height, y * height,
                                y * height - height };

                poly = new Polygon(px, py, 4);
        }

        public void draw(Graphics g) {
                g.setColor(Color.black);

                if (highlighted) {
                        g.setColor(Color.orange);
                        g.fillPolygon(poly);
                } else {
                        g.drawPolygon(poly);
                }

        }

        public String toString() {
                return "x=" + x + ", y=" + y + ", height=" + height + ", width="
                                + width;
        }

        public void setHighlight(boolean highlighted) {
                this.highlighted = highlighted;
        }

        public boolean contains(Point p) {
                return poly.contains(p);
        }

}