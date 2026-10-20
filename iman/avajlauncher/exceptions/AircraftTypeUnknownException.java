package iman.avajlauncher.exceptions;

public class AircraftTypeUnknownException extends Exception {
    
    public AircraftTypeUnknownException(String p_type) {
        super("Aircraft type unknown: " + p_type + ". Valid types are: balloon, jetplane, helicopter.");
    }
}
