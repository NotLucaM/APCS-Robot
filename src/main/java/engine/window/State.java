package engine.window;

import org.lwjgl.glfw.GLFW;

public class State {

    public double mouseX, mouseY;
    public boolean mousePressed;

    public void update(OpenGLWindow window) {
        this.mouseX = window.mouseLocation.getX();
        this.mouseY = window.mouseLocation.getY();
        this.mousePressed = window.mouseInput.getButton(GLFW.GLFW_MOUSE_BUTTON_1);
    }
}
