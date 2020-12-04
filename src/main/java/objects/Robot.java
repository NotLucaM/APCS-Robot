package objects;

import engine.window.*;

public class Robot implements GameObject {

    private double x, y, sX, sY;
    private double wantedX;
    private long lastTime;

    private double velocity;
    private double acceleration = 0;
    private double mass = 10;
    private double friction = 0.05;

    private double p, i, d, iMax = Double.MAX_VALUE;
    private double integral;
    private double lastError;

    private Graph wantedGraph, xGraph;

    public Robot(double x, double y, double sX, double sY, double p, double i, double d) {
        this(x, y, sX, sY, p, i, d, null, null);
    }

    public Robot(double x, double y, double sX, double sY, double p, double i, double d, Graph wantedGraph, Graph xGraph) {
        this.x = x;
        this.wantedX = x;
        this.y = y;
        this.sX = sX;
        this.sY = sY;
        this.p = p;
        this.i = i;
        this.d = d;
        this.lastTime = System.currentTimeMillis();

        this.wantedGraph = wantedGraph;
        this.xGraph = xGraph;
    }

    @Override
    public void draw(OpenGLWindow window) {
        window.setColor(255, 1, 3);
        window.fillRect(x, y, sX, sY);

        if (xGraph != null && wantedGraph != null) {
            xGraph.draw(window);
            wantedGraph.draw(window);
        }
    }

    @Override
    public void update(State state) {
        if (state.mousePressed) {
            wantedX = state.mouseX;
        }

        long deltaTime = 1;
        double pid = pid(deltaTime);
        double force = pid * mass - Math.signum(pid + velocity) * Math.min(friction * mass * 9.8, Math.abs(pid + velocity));
        acceleration = force / mass;
        velocity = velocity + acceleration * deltaTime;
        x += velocity * deltaTime;

        if (xGraph != null && wantedGraph != null) {
            xGraph.addPoint(x);
            wantedGraph.addPoint(wantedX);
        }
//        System.out.printf("%.7f %.7f %.7f %.7f %.3f\n", pid, force, acceleration, velocity, x);
    }

    public double pid(long deltaTime) {
        double error = wantedX - x;
        double integral = (error + this.integral) * deltaTime;
        double derivative = (error - lastError) / deltaTime;

        lastError = error;
        this.integral = integral;

        return p * error + Math.min(i * integral, iMax) + d * derivative;
    }
}
