package engine.window;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11C.*;

public class Drawer {

    private ArrayList<Drawable> objects = new ArrayList<>();

    public void draw(OpenGLWindow window) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
        for (Drawable object : objects) {
            object.draw(window);
        }
        glfwSwapBuffers(window.getWindow());
    }

    public void addDrawable(Drawable object) {
        objects.add(object);
    }
}
