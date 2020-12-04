package engine.window;

import java.util.ArrayList;

public class Updater {

    private ArrayList<Updatable> objects = new ArrayList<>();

    /**
     * Update all of the objects given a state
     * @param state The state of the field
     */
    public void update(State state) {
        for (Updatable object : objects) {
            object.update(state);
        }
    }

    public void addUpdatable(Updatable object) {
        objects.add(object);
    }
}
