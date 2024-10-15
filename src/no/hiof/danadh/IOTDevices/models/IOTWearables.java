package no.hiof.danadh.IOTDevices.models;

import java.util.ArrayList;
import java.util.HashMap;

abstract class IOTWearables extends IOTDevice {
   protected boolean display;
   protected String useStyle;
   protected ArrayList<String> functions;
   protected boolean gps;
   protected int batteryCapacity;
   protected HashMap<String, Integer> size;

    public IOTWearables(String name, String deviceID, String producer, float weight, boolean wifi,
                        String model, int powerUsage, boolean display, String useStyle,
                        ArrayList<String> functions, boolean gps, int batteryCapacity, HashMap<String, Integer> size) {
        super(name, deviceID, producer, weight, wifi, model, powerUsage);


        this.display = display;
        this.useStyle = useStyle;
        this.functions = functions;
        this.gps = gps;
        this.batteryCapacity = batteryCapacity;
        this.size = size;
    }
}
