package engine.math;

/**
 * A point, but did not want to override the {@link java.awt.Point}. Reason for writing this class instead of using the
 * {@link java.awt.Point} are this requires doubles and {@link java.awt.Point} uses ints. The reason doubles need to be
 * used is because OpenGL's window is on a from -1 to 1 in both directions.
 */
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
