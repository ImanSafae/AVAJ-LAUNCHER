package iman.avajlauncher.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import iman.avajlauncher.exceptions.AircraftTypeUnknownException;
import iman.avajlauncher.exceptions.InvalidFormatException;
import iman.avajlauncher.observers.Flyable;
import iman.avajlauncher.providers.AircraftFactory;
import iman.avajlauncher.providers.WeatherTower;

public class Simulator {

    private static int nbOfSimulations;
    private static AircraftFactory aircraftFactory;
    private static WeatherTower weatherTower;
    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                throw new InvalidFormatException("Usage: java Simulator <scenario file>");
            }
            aircraftFactory = AircraftFactory.getInstance();
            weatherTower = new WeatherTower();

            String filename = args[0];
            Path filePath = Paths.get(filename);

            if (Files.notExists(filePath)) {
                throw new FileNotFoundException(filename);
            }
            initSimulation(filePath);
            for (int i = 0; i< nbOfSimulations; i++) {
                weatherTower.changeWeather();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    private static void initSimulation(Path filePath) throws IOException, InvalidFormatException, AircraftTypeUnknownException {
        try (BufferedReader reader = Files.newBufferedReader(filePath)){
            String line = reader.readLine();
            int lines = 0;
            while (line != null) {
                if (lines == 0) {
                    if (line.isEmpty() || !(line.chars().allMatch(Character::isDigit))) {
                        throw new InvalidFormatException("First line should be a number");
                    }
                    if (Integer.parseInt(line) <= 0) {
                        throw new InvalidFormatException("Number of simulations should be positive");
                    }
                    nbOfSimulations = Integer.parseInt(line);
                }
                else {
                    String[] elements = line.trim().split("\\s+");
                    if (elements.length != 5) {
                        throw new InvalidFormatException("Line format should be TYPE NAME LONGITUDE LATITUDE HEIGHT. Found: " + line);
                    }
                    String p_type = elements[0].toLowerCase();
                    String p_name = elements[1];
                    int p_longitude = Integer.parseInt(elements[2]);
                    int p_latitude = Integer.parseInt(elements[3]);
                    int p_height = Integer.parseInt(elements[4]);
                    Coordinates p_coordinates = validateCoordinates(p_longitude, p_latitude, p_height);
                    Flyable newFlyable = aircraftFactory.newAircraft(p_type, p_name, p_coordinates);
                    newFlyable.registerTower(weatherTower);
                }
                line = reader.readLine();
                lines++;
            }
            if (lines == 0 || lines == 1) {
                throw new InvalidFormatException("Scenario file should contain at least one aircraft");
            }
        }
        catch (NumberFormatException e) {
            throw new InvalidFormatException("Coordinates should be numeric. Found: " + e.getMessage());
        }
    }

    private static Coordinates validateCoordinates(int longitude, int latitude, int height) throws InvalidFormatException {
        if (longitude < 0) {
            throw new InvalidFormatException("Coordinates should be positive numbers. Found longitude: " + longitude);
        }
        if (latitude < 0) {
            throw new InvalidFormatException("Coordinates should be positive numbers. Found latitude: " + longitude);
        }
        if (height < 0) {
            throw new InvalidFormatException("Height should be between 0 and 100. Found: " + height);
        }
        if (height > 100) {
            System.out.println("Height should be between 0 and 100. Found: " + height + ". Replaced with max value: 100.");
            height = 100;
        }
        return new Coordinates(longitude, latitude, height);
    }
}
