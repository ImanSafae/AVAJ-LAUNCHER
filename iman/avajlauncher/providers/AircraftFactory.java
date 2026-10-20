package iman.avajlauncher.providers;

import iman.avajlauncher.exceptions.AircraftTypeUnknownException;
import iman.avajlauncher.observers.Balloon;
import iman.avajlauncher.observers.Flyable;
import iman.avajlauncher.observers.Helicopter;
import iman.avajlauncher.observers.JetPlane;
import iman.avajlauncher.utils.Coordinates;

public class AircraftFactory {

    private static AircraftFactory instance;
    private long nextId = 0;

    private AircraftFactory() {}

    public static AircraftFactory getInstance() {
        if (instance == null) {
            instance = new AircraftFactory();
        }
        return instance;
    }

    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) throws AircraftTypeUnknownException {
        String type = p_type.toLowerCase();
        long id = nextId;
        nextId++;
        if (type.equals("balloon")) {
            return new Balloon(id, p_name, p_coordinates);
        }
        else if (type.equals("jetplane")) {
            return new JetPlane(id, p_name, p_coordinates);
        }
        else if (type.equals("helicopter")) {
            return new Helicopter(id, p_name, p_coordinates);
        }
        nextId--;
        throw new AircraftTypeUnknownException(p_type);
    }
}
