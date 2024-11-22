package no.hiof.g13.models;


import java.util.HashMap;

/**
 * General group class for all IOT devices that is for the home market, things like
 * housecleaner, StockSmart and smart lights
 */
public abstract class IOTHomeDevice extends IOTDevice{
    //Hashmap to set up dimensions for the device
    protected HashMap<String, Integer> size = new HashMap<String, Integer>() {{
        put("height", 0);
        put("lenght", 0);
        put("width", 0);
    }};
    protected int batteryLevel;

    public IOTHomeDevice(String name, String deviceID, String producer,
                        String modell, boolean wifi,int weight,HashMap<String, Integer> size,
                        int batteryLevel) {
        super(name,deviceID,producer,weight,wifi, modell);
        this.size = size; //Denne må endres på basert på tilknyttningen til databasen
        this.batteryLevel = batteryLevel;
    }

    //Method that return height, length, width or all tree based on response from the user.
    public int[] getSize(String kake){
        int[] sizeHolder = new int[3];
        switch (kake){
            case "h":
                sizeHolder[0] = this.size.get("height");
                return sizeHolder;

            case "l":
                sizeHolder[0] = this.size.get("length");
                return sizeHolder;

            case "w":
                sizeHolder[0] = this.size.get("width");
                return sizeHolder;

            case "a":
                sizeHolder[0] = this.size.get("height");
                sizeHolder[1] = this.size.get("length");
                sizeHolder[2] = this.size.get("width");
                return sizeHolder;
            default:
                return sizeHolder;
        }
    }

    public void updateBattery (int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    //Denne skal etterhvert kobles mot enheten slik at batteriet oppdaterer seg
    public void updateDeviceBattery(int batteryLevel){
        this.batteryLevel -= batteryLevel;
    }

    public int getBattery(){
        return this.batteryLevel;
    }
}
