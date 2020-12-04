package engine.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MouseLocation extends GLFWCursorPosCallback {

    private double width, height;
    private double xpos, ypos;

    public MouseLocation(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void invoke(long window, double xpos, double ypos) {
        this.xpos = 2 * xpos / width - 1;
        this.ypos = 2 * ypos / height - 1;
    }

    public double getX() {
        return xpos;
    }

    public double getY() {
        return ypos;
    }
}
