package objects;

import engine.window.*;

public class Robot implements GameObject {

    private double x, y, sX, sY;
    private double wantedX = 0.7, wantedY;
    private long lastTime;

    private double force;
    private double velocity;
    private double acceleration = 0.1;
    private double mass = 10;
    private double friction = 0.001;

    private double maxSpeed = 0.1;

    private double pX = 0.001, iX = 0, dX = 0;
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
        acceleration = pidX(deltaTime) - friction;
        velocity = velocity + acceleration * deltaTime;
        x += velocity * deltaTime;
    }

    public double pidX(long deltaTime) {
        double error = wantedX - x;
        double integral = (error + integralX) * deltaTime;
        double derivative = (error - lastEX) / deltaTime;

        lastEX = error;
        integralX = integral;

        return pX * error + iX * integral + dX * derivative;
    }
}
