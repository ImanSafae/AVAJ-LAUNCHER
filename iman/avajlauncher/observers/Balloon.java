package iman.avajlauncher.observers;

import iman.avajlauncher.utils.Coordinates;

public class Balloon extends Aircraft {
    
    public Balloon(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    public void updateConditions() {
        String weather = this.weatherTower.getWeather(coordinates);
        if (weather.equals("SUN")) {
            System.out.println(this.toString() + ": I totally know what I'm doing btw. I have a degree in ballooning.");
            this.coordinates.setLongitude(this.coordinates.getLongitude() + 2);
            this.coordinates.setHeight(this.coordinates.getHeight() + 4);
        }
        else if (weather.equals("RAIN")) {
            System.out.println(this.toString() + ": [deflating noises] - Someone heard that?");
            this.coordinates.setHeight(this.coordinates.getHeight() - 5);
        }
        else if (weather.equals("FOG")) {
            System.out.println(this.toString() + ": All that smoke... Someone found my mushroom stash?");
            this.coordinates.setHeight(this.coordinates.getHeight() - 3);
        }
        else if (weather.equals("SNOW")) {
            System.out.println(this.toString() + ": I used to be an adventurer like you. Then I took hail in the balloon.");
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
