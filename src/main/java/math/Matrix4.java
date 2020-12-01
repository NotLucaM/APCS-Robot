package math;

import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public class Matrix4 {

    static final int size = 4 * 4; // not writing 16 to make it obv that its a 4x4 matrix instead of a 2x8 matrix
    public float[] elements = new float[size]; // not and array of arrays because its a constant size

    public Matrix4() {

    }

    public Matrix4(float[] elements) {
        if (elements.length == size) {
            this.elements = elements;
        } else {
            throw new IllegalArgumentException("Matrix size is not 4x4");
        }
    }

    public static Matrix4 identity() {
        return new Matrix4(new float[]{1, 0, 0, 0,
                                       0, 1, 0, 0,
                                       0, 0, 1, 0,
                                       0, 0, 0, 1});
    }

    public static Matrix4 orthographic(float left, float right, float bottom, float top, float near, float far) {
        Matrix4 ret = identity();

        // not using the actual values to make it more clear where in the matrix they are
        ret.elements[0 + 0 * 4] = 2.0f / (right - left);
        ret.elements[1 + 1 * 4] = 2.0f / (top - bottom);
        ret.elements[1 + 2 * 4] = 2.0f / (near - far);
        ret.elements[0 + 3 * 4] = (left + right) / (left - right);
        ret.elements[1 + 3 * 4] = (bottom + top) / (bottom - top);
        ret.elements[2 + 3 * 4] = (far + near) / (far - near);

        return ret;
    }

    public static Matrix4 translate(Vector3 vector) {
        Matrix4 ret = identity();
        ret.elements[0 + 3 * 4] = vector.x;
        ret.elements[1 + 3 * 4] = vector.y;
        ret.elements[2 + 3 * 4] = vector.z;
        return ret;
    }

    public static Matrix4 rotate(float radians) {
        Matrix4 ret = identity();
        float cos = (float) Math.cos(radians);
        float sin = (float) Math.sin(radians);

        ret.elements[0 + 0 * 4] = cos;
        ret.elements[1 + 0 * 4] = sin;

        ret.elements[0 + 1 * 4] = -sin;
        ret.elements[1 + 1 * 4] = cos;

        return ret;
    }

    public Matrix4 multiply(Matrix4 matrix) {
        Matrix4 result = new Matrix4();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                float sum = 0.0f;
                for (int k = 0; k < 4; k++) {
                    sum += this.elements[j + k * 4] * matrix.elements[k + i * 4];
                }
                result.elements[j + i * 4] = sum;
            }
        }
        return result;
    }

    public FloatBuffer toFloatBuffer() {
        return BufferUtils.createFloatBuffer(size).put(elements).flip();
    }
}
