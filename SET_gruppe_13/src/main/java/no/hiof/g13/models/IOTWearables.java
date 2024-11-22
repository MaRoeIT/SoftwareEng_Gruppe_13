package no.hiof.g13.models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Group class for IOTDevices that is wearable in any fashion.
 */
public abstract class IOTWearables extends IOTDevice{
    protected boolean display;
    //Is it for the wrist or somewhere else.
    protected String useStyle;
    protected ArrayList<String> functions = new ArrayList<>();
    //do the device use gps
    protected boolean gps;
    protected int batteryCapacity;
    protected HashMap<String, Integer> size = new HashMap<>();

    public IOTWearables(
            String name, String deviceID, String producer, int weight,
            boolean wifi, String modell, boolean display, String useStyle,
            ArrayList<String> functions, boolean gps, int batteryCapacity,
            HashMap<String, Integer> size
    ) {
        super(name, deviceID, producer, weight, wifi, modell);
        this.display = display;
        this.useStyle = useStyle;
        this.functions = functions;
        this.gps = gps;
        this.batteryCapacity = batteryCapacity;
        this.size = size;
    }
}
