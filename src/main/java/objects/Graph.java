package objects;

import engine.math.Vector2;
import engine.window.Color;
import engine.window.Drawable;
import engine.window.OpenGLWindow;

import java.util.ArrayList;

public class Graph implements Drawable {

    private static final int displayedPoints = 100;

    // Not using a queue because every element will be seen by the drawPoints function
    private ArrayList<Vector2> points = new ArrayList<>();
    private Color color;
    private double x, y, sX, sY;
    private double start, end;

    public Graph(double x, double y, double sX, double sY, double start, double end, Color color) {
        for (int i = 0; i < displayedPoints; i++) {
            points.add(new Vector2(x + sX / i, y));
        }
        this.x = x;
        this.y = y;
        this.sX = sX;
        this.sY = sY;
        this.start = start;
        this.end = end;
        this.color = color;
    }

    @Override
    public void draw(OpenGLWindow window) {
        window.setColor(color);
        window.drawPoints(points);
    }

    public void addPoint(double y) {
        points.remove(0);
        for (Vector2 v : points) {
            v.x -= sX / displayedPoints;
        }
        points.add(new Vector2(x + sX, this.y + (y - start) / ((end - start) * sX)));
    }
}
