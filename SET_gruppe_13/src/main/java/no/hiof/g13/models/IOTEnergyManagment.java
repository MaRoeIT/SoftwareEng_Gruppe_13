package no.hiof.g13.models;

import java.util.HashMap;

abstract class IOTEnergyManagment extends IOTDevice {
    protected int frequencies;
    protected int voltage;
    //Hashmap som inneholder den største mengden watt og ampere som enheten tåler
    protected HashMap<String, Integer> maxLoad = new HashMap<String, Integer>() {{
        put("Watt", 0);
        put("Ampere", 0);
    }};

    public IOTEnergyManagment(
            String name, String deviceID, String producer, int vekt, boolean wifi, String modell, int frequencies, int voltage, HashMap<String, Integer> maxLoad
    ) {
        super(name, deviceID, producer, vekt, wifi, modell);
        this.frequencies = frequencies;
        this.voltage = voltage;
        this.maxLoad = maxLoad;
    }
}
