package iman.avajlauncher.observers;

import iman.avajlauncher.utils.Coordinates;
import iman.avajlauncher.utils.SimulationLogger;

public class Helicopter extends Aircraft {
    
    public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    public void updateConditions() {
        String weather = this.weatherTower.getWeather(coordinates);
        if (weather.equals("SUN")) {
            SimulationLogger.log(this.toString() + ": HELICOPTER HELICOPTER");
            this.coordinates.setLongitude(this.coordinates.getLongitude() + 10);
            this.coordinates.setHeight(this.coordinates.getHeight() + 2);
        }
        else if (weather.equals("RAIN")) {
            SimulationLogger.log(this.toString() + ": [sad helicopter noises]");
            this.coordinates.setLongitude(this.coordinates.getLongitude() + 5);
        }
        else if (weather.equals("FOG")) {
            SimulationLogger.log(this.toString() + ": [confused helicopter noises]");
            this.coordinates.setLongitude(this.coordinates.getLongitude() + 1);
        }
        else if (weather.equals("SNOW")) {
            SimulationLogger.log(this.toString() + ": [weird sleigh bells with helix noises]");
            int newHeight = this.coordinates.getHeight() - 12;
            if (newHeight <= 0) {
                newHeight = 0;
                this.weatherTower.unregister(this);
            }
            this.coordinates.setHeight(newHeight);
        }
        // System.out.println(this.toString() + ": Height now at: " + this.coordinates.getHeight());
    }

}
