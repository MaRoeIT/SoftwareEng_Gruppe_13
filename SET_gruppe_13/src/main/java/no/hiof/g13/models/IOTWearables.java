package no.hiof.g13.models;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class IOTWearables extends IOTDevice{
    protected boolean display;
    //om den er for håndledd, finger eller andre bruksmåter
    protected String useStyle;
    protected ArrayList<String> funksjoner = new ArrayList<>();
    //bruker enheten gps
    protected boolean gps;
    protected int batteryCapacity;
    protected HashMap<String, Integer> size = new HashMap<>();

    public IOTWearables(
            String name, String deviceID, String producer, int weight,
            boolean wifi, String modell, boolean display, String useStyle,
            ArrayList<String> funksjoner, boolean gps, int batteryCapacity,
            HashMap<String, Integer> size
    ) {
        super(name, deviceID, producer, weight, wifi, modell);
        this.display = display;
        this.useStyle = useStyle;
        this.funksjoner = funksjoner;
        this.gps = gps;
        this.batteryCapacity = batteryCapacity;
        this.size = size;
    }
}
