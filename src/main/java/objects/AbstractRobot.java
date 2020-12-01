package objects;

import math.Vector3;
import utils.Color;

import static org.lwjgl.opengl.GL20.*;

public abstract class AbstractRobot implements GameObject {

    protected Vector3 location;
    protected Color color;

    public void draw() {

    }

    public abstract void update();
}
