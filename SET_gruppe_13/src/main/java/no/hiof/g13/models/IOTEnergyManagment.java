package no.hiof.g13.models;

import java.util.HashMap;

/**
 * General grouping of IOTDevices. Contains properties tied to energy management.
 */
abstract class IOTEnergyManagment extends IOTDevice {
    //TODO:Look into making it a dependency injection
    protected int frequencies;
    protected int voltage;
    //Hashmap som inneholder den største mengden watt og ampere som enheten tåler
    protected HashMap<String, Integer> maxLoad = new HashMap<String, Integer>() {{
        put("Watt", 0);
        put("Ampere", 0);
    }};

    public IOTEnergyManagment(
            String name, String deviceID, String producer, int weight, boolean wifi, String modell, int frequencies, int voltage, HashMap<String, Integer> maxLoad
    ) {
        super(name, deviceID, producer, weight, wifi, modell);
        this.frequencies = frequencies;
        this.voltage = voltage;
        this.maxLoad = maxLoad;
    }
}
