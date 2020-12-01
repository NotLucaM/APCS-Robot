package objects;

import math.Vector3;
import utils.Color;

import static org.lwjgl.opengl.GL20.*;

public abstract class AbstractRobot implements GameObject {

    protected Vector3 location;
    protected Color color;

    public void draw() {
        glColor3i(color.r, color.g, color.b);
    }

    public abstract void update();

    protected
}
