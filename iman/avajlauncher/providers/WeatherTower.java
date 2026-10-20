package iman.avajlauncher.providers;

import iman.avajlauncher.utils.Coordinates;

public class WeatherTower extends Tower {

    public WeatherProvider weatherProvider;

    public WeatherTower() {
        this.weatherProvider = WeatherProvider.getInstance();
    }

    public String getWeather(Coordinates p_coordinates) {
        return weatherProvider.getCurrentWeather(p_coordinates);
    }

    public void changeWeather() {
        this.conditionChanged();
    }
}
