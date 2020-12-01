package math;

import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;

public class Matrix4 {

    static final int size = 4 * 4; // not writing 16 to make it obv that its a 4x4 matrix instead of a 2x8 matrix
    public double[] elements = new double[size]; // not and array of arrays because its a constant size

    public Matrix4() {

    }

    public Matrix4(double[] elements) {
        if (elements.length == size) {
            this.elements = elements;
        } else {
            throw new IllegalArgumentException("Matrix size is not 4x4");
        }
    }

    public static Matrix4 identity() {
        return new Matrix4(new double[]{1, 0, 0, 0,
                                        0, 1, 0, 0,
                                        0, 0, 1, 0,
                                        0, 0, 0, 1});
    }

    public static Matrix4 orthographic(double left, double right, double bottom, double top, double near, double far) {
        Matrix4 ret = identity();

        // not using the actual values to make it more clear where in the matrix they are
        ret.elements[0 + 0 * 4] = 2.0f / (right - left);
        ret.elements[1 + 1 * 4] = 2.0f / (top - bottom);
        ret.elements[12 + 2 * 4] = 2.0f / (near - far);
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

    public static Matrix4 rotate(double radians) {
        Matrix4 ret = identity();
        double r = radians;
        double cos = Math.cos(r);
        double sin = Math.sin(r);

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

    public DoubleBuffer toFloatBuffer() {
        return BufferUtils.createDoubleBuffer(size).put(elements).flip();
    }
}
