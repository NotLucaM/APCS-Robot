package graphics;

import utils.ShaderUtils;

public class Shader {

    private final int id;

    public Shader(String vert, String fragment) {
        id = ShaderUtils.load(vert, fragment);
    }
}
