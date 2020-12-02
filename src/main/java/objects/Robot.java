package objects;

import graphics.Shader;
import graphics.Texture;
import graphics.VertexArray;
import math.Matrix4;
import math.Vector3;

public class Robot extends AbstractRobot {

    private float size = 1.0f;
    private float rot = 0.0f;
    private float delta = 0.0f;
    private int map = 0;
    private Vector3 position = new Vector3();

    private Matrix4 projectionMatrix = Matrix4.orthographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -1.0f, 1.0f);
    private Shader shader = new Shader("shaders/robot.vert", "shaders/robot.frag");
    private VertexArray mesh;
    private Texture texture;


    public Robot() {
        float[] vertices = new float[] {
                -size / 2.0f, -size / 2.0f, 0.2f,
                -size / 2.0f,  size / 2.0f, 0.2f,
                size / 2.0f,  size / 2.0f, 0.2f,
                size / 2.0f, -size / 2.0f, 0.2f
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
        mesh = new VertexArray(vertices, indices, tcs);
        texture = new Texture("shaders/robot.png");
    }

    @Override
    public void draw() {
        shader.enable();
        shader.setUniformMat4f("ml_matrix", Matrix4.translate(position).multiply(Matrix4.rotate(rot)));
        texture.bind();
        mesh.render();
        shader.disable();
    }

    @Override
    public void update() {

    }
}
