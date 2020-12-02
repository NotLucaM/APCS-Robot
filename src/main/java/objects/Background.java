package objects;

import graphics.Shader;
import graphics.Texture;
import graphics.VertexArray;
import math.Matrix4;
import math.Vector3;

public class Background implements GameObject {

    private Matrix4 projectionMatrix = Matrix4.orthographic(-16, 16, -90 / 16f, 90 / 16f, -1, 1);
    private Shader shader = new Shader("shaders/bg.vert", "shaders/bg.frag");
    private VertexArray mesh;
    private Texture texture;

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

        mesh = new VertexArray(vertices, indices, tcs);
        texture = new Texture("shaders/bg.jpg");
    }

    @Override
    public void draw() {
        shader.enable();
        shader.setUniformMat4f("ml_matrix", Matrix4.translate(new Vector3(0, 0, 0)).multiply(Matrix4.rotate(0)));
        texture.bind();
        mesh.render();
        shader.disable();
    }

    @Override
    public void update() {

    }
}
