package graphics;

import math.Matrix4;
import math.Vector3;
import utils.ShaderUtils;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

public class Shader {

    public static final int VERTEX_ATTRIB = 0;
    public static final int TCOORD_ATTRIB = 1;

    private final int id;

    private Map<String, Integer> cache = new HashMap<>();

    public Shader(String vert, String fragment) {
        id = ShaderUtils.load(vert, fragment);
    }

    public int getUniformLocation(String name) {
        if (cache.containsKey(name)) {
            return cache.get(name);
        } else {
            int location = glGetUniformLocation(id, name);
            cache.put(name, location);
            return location;
        }
    }

    public void setUniform1i(String name, int x) {
        glUniform1i(getUniformLocation(name), x);
    }

    public void setUniform1f(String name, float x) {
        glUniform1f(getUniformLocation(name), x);
    }

    public void setUniform2f(String name, float x, float y) {
        glUniform2f(getUniformLocation(name), x, y);
    }

    public void setUniform3f(String name, float x, float y, float z) {
        glUniform3f(getUniformLocation(name), x, y, z);
    }

    public void setUniform3f(String name, Vector3 v) {
        glUniform3f(getUniformLocation(name), v.x, v.y, v.z);
    }

    public void setUniformMat4f(String name, Matrix4 m) {
        glUniformMatrix4fv(getUniformLocation(name), false, m.elements);
    }

    public void enable() {
        glUseProgram(id);
    }

    public void disable() {
        glUseProgram(0); // 0 is NULL in opengl
    }
}
