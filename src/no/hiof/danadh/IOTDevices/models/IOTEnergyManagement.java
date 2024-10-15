package no.hiof.danadh.IOTDevices.models;

import java.util.HashMap;

abstract class IOTEnergyManagement extends IOTDevice {
    protected int frequencies;
    protected int voltage;
    protected HashMap<String, Integer> maxLoad;

    public IOTEnergyManagement(String name, String deviceID, String producer, float weight, boolean wifi,
                              String model, int powerUsage, int frequencies, int voltage,
                              HashMap<String, Integer> maxLoad) {
        super(name, deviceID, producer, weight, wifi, model, powerUsage);

        this.frequencies = frequencies;
        this.voltage = voltage;
        this.maxLoad = maxLoad;
    }
}
