package objects;

import graphics.Shader;
import graphics.Texture;
import graphics.VertexArray;
import math.Matrix4;

public class Background implements GameObject {

    private Matrix4 projectionMatrix = Matrix4.orthographic(-16, 16, -90 / 16f, 90 / 16f, -1, 1);
    private Shader shader = new Shader("shaders/bg.vert", "shaders/bg.frag");
    private VertexArray mesh;
    private Texture texture;
    private int map = 0;

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

        shader.setUniformMat4f("projectionMatrix", projectionMatrix);
        shader.setUniform1i("tex", 1);
        mesh = new VertexArray(vertices, indices, tcs);
        texture = new Texture("shaders/bg.jpg");
    }

    @Override
    public void draw() {
        shader.enable();
        texture.bind();
        mesh.render();
        shader.disable();
    }

    @Override
    public void update() {

    }
}
