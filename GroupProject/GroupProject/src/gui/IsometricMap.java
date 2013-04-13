
package gui;


import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import tiles.IsometricPoint;

import addon.Drawable;

public class IsometricMap implements Drawable {
        private HashMap<Point, IsometricPoint> pointMap = null;
        private int last_x, total_rows;
        private Point mousePoint = null;

        public IsometricMap() {
                generateBlankMap(20);
        }

        @Override
        public synchronized void draw(Graphics g) {
                if (pointMap == null)
                        return;
                for (int y = 0; y < total_rows; y++) {
                        for (int x = last_x; x >= 0; x--) {
                                Point p = new Point(x, y);
                                IsometricPoint ip = pointMap.get(p);
                                if (ip != null) {
                                        if (mousePoint != null && ip.contains(mousePoint)) {
                                                ip.setHighlight(true);
                                        } else if (mousePoint != null) {
                                                ip.setHighlight(false);
                                        }
                                        ip.draw(g);
                                }
                        }
                }
        }

        private void generateBlankMap(int rows) {
                pointMap = new HashMap<Point, IsometricPoint>();

                int tmp_backwards = rows + 1;

                int tileRows = (rows * 2) - 1; // 9 rida kokku kui on 5x5 maatriks.
                int lastX = rows - 1;

                boolean decrease = false;

                for (int y = 0; y < tileRows; y++) {

                        if (y + 1 >= rows) {
                                decrease = true;
                                tmp_backwards--;
                        }

                        int tilesInRow = (y < rows - 1 ? y + 1 : (y + 1 == rows ? rows
                                        : tmp_backwards));

                        for (int x = 0; x < tilesInRow; x++) {
                                int tmp_x = lastX + 2 * x;
                                if (tmp_x > last_x) {
                                        last_x = tmp_x;
                                }
                                Point tmp = new Point(tmp_x, y);
                                pointMap.put(tmp, new IsometricPoint(tmp_x, y));
                        }
                        if (!decrease)
                                lastX--;
                        else
                                lastX++;
                }
                total_rows = tileRows;
        }

        public void setMousePoint(Point mouse) {
                mousePoint = mouse;
        }
}
