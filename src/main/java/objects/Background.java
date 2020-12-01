package objects;

import graphics.Shader;
import math.Matrix4;

public class Background implements GameObject {

    private Matrix4 projectionMatrix = Matrix4.orthographic(-16, 16, -90 / 16f, 90 / 16f, -1, 1);
    private Shader shader = new Shader("shaders/bg.vert", "shaders/bg.frag");

    public Background() {
        float[] vertices = new float[] {
                -10.0f, -10.0f * 9.0f / 16.0f, 0.0f,
                -10.0f,  10.0f * 9.0f / 16.0f, 0.0f,
                0.0f,  10.0f * 9.0f / 16.0f, 0.0f,
                0.0f, -10.0f * 9.0f / 16.0f, 0.0f
        };

        byte[] indices = new byte[] {
                0, 1, 2,
                2, 3, 0
        };

        float[] tcs = new float[] {
                0, 1,
                0, 0,
                1, 0,
                1, 1
        };
    }

    @Override
    public void draw() {

    }

    @Override
    public void update() {

    }
}
