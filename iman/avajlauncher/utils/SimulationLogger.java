package iman.avajlauncher.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SimulationLogger {
    private static SimulationLogger instance;
    private static BufferedWriter writer;

    private SimulationLogger() throws Exception {}

    public static SimulationLogger getInstance() throws Exception {
        if (instance == null) {
            instance = new SimulationLogger();
        }
        return instance;
    }

    public static void init() throws Exception {
        writer = Files.newBufferedWriter(Paths.get("simulation.txt"));
    }

    public static void log(String message) {
        try {
            writer.write(message);
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close() throws IOException {
        writer.close();
    }

}