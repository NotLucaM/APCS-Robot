package objects;

import engine.math.Vector2;
import engine.window.Drawable;
import engine.window.OpenGLWindow;

import java.util.ArrayList;

public class Graph implements Drawable {

    private static final int displayedPoints = 100;

    // Not using a queue because every element will be seen by the drawPoints function
    private ArrayList<Vector2> points = new ArrayList<>();
    private double x, y, sX, sY, r, g, b;
    private double scale;

    public Graph(double x, double y, double sX, double sY, double scale, double r, double g, double b) {
        for (int i = 0; i < displayedPoints; i++) {
            points.add(new Vector2(x + sX / i, y));
        }
        this.x = x;
        this.y = y;
        this.sX = sX;
        this.sY = sY;
        this.scale = scale;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public void draw(OpenGLWindow window) {
        window.setColor(r, g, b);
        window.drawPoints(points);
    }

    public void addPoint(double y) {
        points.remove(0);
        for (Vector2 v : points) {
            v.x -= sX / displayedPoints;
        }
        points.add(new Vector2(x + sX, this.y + y / scale * sY));
    }
}
