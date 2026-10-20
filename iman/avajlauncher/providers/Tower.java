package iman.avajlauncher.providers;

import java.util.ArrayList;
import java.util.List;

import iman.avajlauncher.observers.Flyable;
import iman.avajlauncher.utils.SimulationLogger;

public class Tower {
    private List<Flyable> observers = new ArrayList<Flyable>();

    public void register(Flyable p_flyable) {
        observers.add(p_flyable);
        SimulationLogger.log("Tower says: "  + p_flyable.toString() +  " registered to weather tower.");
    }

    public void unregister(Flyable p_flyable) {
        observers.remove(p_flyable);
        SimulationLogger.log("Tower says: "  + p_flyable.toString() +  " unregistered from weather tower.");
    }

    protected void conditionChanged() {
        for (Flyable observer : new ArrayList<>(observers)) {
            observer.updateConditions();
        }
    }

}
