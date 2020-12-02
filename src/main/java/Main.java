import engine.input.Input;

import engine.window.OpenGLWindow;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main {

    private final static int width = 1280;
    private final static int height = 720;

    public static void main(String[] args) {
        new OpenGLWindow("Robot", width, height).init();
    }
}
