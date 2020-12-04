package engine.input;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

import static org.lwjgl.glfw.GLFW.*;

public class MouseInput extends GLFWMouseButtonCallback {

    private final boolean[] buttonsPressed = new boolean[65536];

    @Override
    public void invoke(long window, int button, int action, int mods) {
        if (action == GLFW_RELEASE) {
            buttonsPressed[button] = false;
        } else if (action == GLFW_PRESS || action == GLFW_REPEAT) {
            buttonsPressed[button] = true;
        }
    }

    public boolean getButton(int button) {
        return buttonsPressed[button];
    }
}
