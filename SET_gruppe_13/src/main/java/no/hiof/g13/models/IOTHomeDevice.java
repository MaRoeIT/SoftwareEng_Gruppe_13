package no.hiof.g13.models;


import java.util.HashMap;

public class IOTHomeDevice extends IOTDevice{
    protected HashMap<String, Integer> size = new HashMap<String, Integer>() {{
        put("height", 0);
        put("lenght", 0);
        put("width", 0);
    }};
    public IOTHomeDevice(String name, String deviceID, String producer,HashMap<String, Integer> size){
        super(name,deviceID,producer);
        this.size = size;
    }
}
