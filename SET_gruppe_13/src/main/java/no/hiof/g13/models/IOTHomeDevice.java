package no.hiof.g13.models;


import java.util.HashMap;

//Overklasse for hjemme IOT enheter, inneholder gennerelle egenskaper for IOT enheter
public abstract class IOTHomeDevice extends IOTDevice{
    //Hashmap for å sette dimensioner for produktet, bredde høyde og vidde.
    protected HashMap<String, Integer> size = new HashMap<String, Integer>() {{
        put("height", 0);
        put("lenght", 0);
        put("width", 0);
    }};
    //Holder batteri nivået til enheten
    protected int batteryLevel;
    //konstruktør
    public IOTHomeDevice(String name, String deviceID, String producer,
                         String modell, boolean wifi,int weight,HashMap<String, Integer> size,
                         int batteryLevel) {
        super(name,deviceID,producer,weight,wifi, modell);
        this.size = size; //Denne må endres på basert på tilknyttningen til databasen
        this.batteryLevel = batteryLevel;
    }

    //Metode som returnerer en array med enten høyde, lengde, vidde eller alle 3
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
