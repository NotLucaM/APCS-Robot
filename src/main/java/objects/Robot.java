package objects;

import engine.window.*;

public class Robot implements GameObject {

    private double x, y, sX, sY;
    private double wantedX = 0.5, wantedY;
    private long lastTime;

    private double force;
    private double velocity;
    private double acceleration = 0;
    private double mass = 10;
    private double friction = 0.05;

    private double maxSpeed = 0.1;

    private double pX = 0.007, iX = 0.00001, dX = 0.065, iMaxX = 0;
    private double pY, iY, dY;
    private double integralX, integralY;
    private double lastEX, lastEY;

    public Robot(double x, double y, double sX, double sY) {
        this.x = x;
        this.y = y;
        this.sX = sX;
        this.sY = sY;
        this.lastTime = System.currentTimeMillis();
    }

    @Override
    public void draw(OpenGLWindow window) {
        window.setColor(255, 1, 3);
        window.fillRect(x, y, sX, sY);
    }

    @Override
    public void update(State state) {
        long deltaTime = 1;
        double pid = pidX(deltaTime);
//        double force = Math.signum(pid) * (Math.abs(pid) * mass - Math.min(Math.abs(pid) + Math.abs(velocity), friction * mass * 9.8));
        double force = pid * mass - Math.signum(pid + velocity) * Math.min(friction * mass * 9.8, Math.abs(pid + velocity));
        acceleration = force / mass;
        velocity = velocity + acceleration * deltaTime;
        x += velocity * deltaTime;
        System.out.printf("%.7f %.7f %.7f %.7f %.3f\n", pid, force, acceleration, velocity, x);
    }

    public double pidX(long deltaTime) {
        double error = wantedX - x;
        double integral = (error + integralX) * deltaTime;
        double derivative = (error - lastEX) / deltaTime;

        lastEX = error;
        integralX = integral;

        return pX * error + Math.min(iX * integral, iMaxX) + dX * derivative;
    }
}
