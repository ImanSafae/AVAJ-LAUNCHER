package iman.avajlauncher.providers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import iman.avajlauncher.utils.Coordinates;

public class WeatherProvider {
    
    private List<String> weather = new ArrayList<String>();
    private static WeatherProvider instance;

    private WeatherProvider() {
        Collections.addAll(weather, "SUN", "FOG", "RAIN", "SNOW");
    }

    public static WeatherProvider getInstance() {
        if (instance == null) {
            instance = new WeatherProvider();
        }
        return instance;
    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        int res = Math.abs(p_coordinates.getLatitude() + p_coordinates.getLatitude() - p_coordinates.getHeight()) % 4;
        return weather.get(res);
    }
}
