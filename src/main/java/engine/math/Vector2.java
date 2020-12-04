package engine.math;

public class Vector2 {

    public double x, y; // this is 2d, however z is used if it is above or below

    public Vector2() {
        x = 0;
        y = 0;
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
