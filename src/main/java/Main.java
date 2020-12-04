import engine.window.OpenGLWindow;

/**
 * Simple class to start the program
 */
public class Main {

    private final static int width = 1280;
    private final static int height = 720;

    public static void main(String[] args) {
        new OpenGLWindow("Robot", width, height).init();
    }
}
