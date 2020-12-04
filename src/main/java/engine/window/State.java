package engine.window;

import org.lwjgl.glfw.GLFW;

public class State {

    public double mouseX, mouseY;
    public boolean mousePressed;

    public void update(OpenGLWindow window) {
        this.mouseX = 2 * window.mouseLocation.getX() / window.width - 1;
        this.mouseY = 2 * window.mouseLocation.getY() / window.height - 1;
        this.mousePressed = window.mouseInput.getButton(GLFW.GLFW_MOUSE_BUTTON_1);
    }
}
