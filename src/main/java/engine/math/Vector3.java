package engine.math;

public class Vector3 {

    public float x, y, z; // this is 2d, however z is used if it is above or below

    public Vector3() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
