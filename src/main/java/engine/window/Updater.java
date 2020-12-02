package engine.window;

import java.util.ArrayList;

public class Updater {

    private ArrayList<Updatable> objects = new ArrayList<>();

    public void update(State state) {
        for (Updatable object : objects) {
            object.update(state);
        }
    }

    public void addUpdatable(Updatable object) {
        objects.add(object);
    }
}
