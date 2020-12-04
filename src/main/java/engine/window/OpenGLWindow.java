package engine.window;

import engine.input.KeyboardInput;
import engine.input.MouseInput;
import engine.input.MouseLocation;
import engine.math.Vector2;
import objects.Graph;
import objects.Pointer;
import objects.Robot;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class OpenGLWindow {

    private Drawer drawer = new Drawer();
    private Updater updater = new Updater();

    public final KeyboardInput keyboardInput = new KeyboardInput();
    public final MouseInput mouseInput = new MouseInput();
    public final MouseLocation mouseLocation = new MouseLocation();

    public int width, height;
    private String name;
    private long window = -1;

    public OpenGLWindow(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_SAMPLES, 4);

        window = glfwCreateWindow(width, height, name, NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        glfwSetKeyCallback(window, keyboardInput);
        glfwSetMouseButtonCallback(window, mouseInput);
        glfwSetCursorPosCallback(window, mouseLocation);

        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*
            glfwGetWindowSize(window, pWidth, pHeight);
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
        } // I believe they get automatically freed after the try block

        glfwMakeContextCurrent(window);
        GLFW.glfwSwapInterval(1);
        glfwShowWindow(window);

        addObject(new Pointer(-1));
        addObject(new Robot(-1, -0.5, 0.1, 0.1,
                new Graph(-0.98, -0.98, 1.9, 0.2, -1, 1, new Color(255, 0, 0)),
                new Graph(-0.98, -0.98, 1.9, 0.2, -1, 1, new Color(0, 255, 0))));

        this.loop();
    }

    private void loop() {
        GL.createCapabilities();
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        State state = new State();

        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents();
            state.update(this);
            updater.update(state);
            drawer.draw(this);
        }
    }

    public long getWindow() {
        if (this.window == -1) {
            init();
        }
        return this.window;
    }

    public void setShowCursor(boolean show) {
        int mouse = GLFW.GLFW_CURSOR_HIDDEN;
        if (show) {
            mouse = GLFW.GLFW_CURSOR_NORMAL;
        }

        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, mouse);
    }

    public void setColor(Color color) {
        glColor4d(color.r / 255, color.g / 255, color.b / 255, color.a / 255); // OpenGL uses percentages
    }

    public void setColor(double r, double g, double b, double a) {
        glColor4d(r / 255, g / 255, b / 255, a / 255); // OpenGL uses percentages
    }

    public void setColor(double r, double g, double b) {
        glColor3d(r / 255, g / 255, b / 255); // OpenGL uses percentages
    }

    public void fillRect(double x, double y, double sX, double sY) {
        glBegin(GL_TRIANGLE_FAN);

        glVertex2d(x, y);
        glVertex2d(x + sX, y);
        glVertex2d(x + sX, y + sY);
        glVertex2d(x, y + sY);

        glEnd();
    }

    public void fillOval(double x, double y, double sX, double sY) {
        x += sX / 2;
        y += sY / 2;
        int sides = (int) (sX + sY) / 4 + 5;
        glBegin(GL_TRIANGLE_FAN);
        for (double i = 0; i < Math.PI * 2; i += Math.PI * 2 / sides) {
            glVertex2d(x + Math.cos(i) * sX / 2, y + Math.sin(i) * sY / 2);
        }
        glEnd();
    }

    public void drawPoints(ArrayList<Vector2> points) {
        glBegin(GL_LINE_STRIP);
        for (Vector2 point : points) {
            glVertex2d(point.x, point.y);
        }
        glEnd();
    }

    public void addObject(GameObject object) {
        drawer.addDrawable(object);
        updater.addUpdatable(object);
    }
}
