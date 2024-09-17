package no.hiof.g13.models;


import java.util.HashMap;

//Overklasse for hjemme IOT enheter, inneholder gennerelle egenskaper for IOT enheter
public class IOTHomeDevice extends IOTDevice{
    //Hashmap for å sette dimensioner for produktet, bredde høyde og vidde.
    protected HashMap<String, Integer> size = new HashMap<String, Integer>() {{
        put("height", 0);
        put("lenght", 0);
        put("width", 0);
    }};
    //konstruktør
    public IOTHomeDevice(String name, String deviceID, String producer, boolean wifi,int vekt,HashMap<String, Integer> size){
        super(name,deviceID,producer,vekt,wifi);
        this.size = size; //Denne må endres på basert på tilknyttningen til databasen
    }
}
