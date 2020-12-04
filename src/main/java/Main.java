import engine.window.OpenGLWindow;

public class Main {

    private final static int width = 1280;
    private final static int height = 720;

    public static void main(String[] args) {
        new OpenGLWindow("Robot", width, height).init();
    }
}
