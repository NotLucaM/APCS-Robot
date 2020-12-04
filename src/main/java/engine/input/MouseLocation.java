package engine.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MouseLocation extends GLFWCursorPosCallback {

    private double xpos, ypos;

    @Override
    public void invoke(long window, double xpos, double ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public double getX() {
        return xpos;
    }

    public double getY() {
        return ypos;
    }
}
