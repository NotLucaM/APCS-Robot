package objects;

import engine.window.GameObject;
import engine.window.OpenGLWindow;
import engine.window.State;
import engine.window.*;

public class Pointer implements GameObject {

    private double x;

    public Pointer(double x) {
        this.x = x;
    }

    @Override
    public void draw(OpenGLWindow window) {
        window.setColor(1, 1, 255);
        window.fillRect(x, -1, 0.005, 2);
    }

    @Override
    public void update(State state) {
        if (state.mousePressed) {
            this.x = state.mouseX;
        }
    }
}
