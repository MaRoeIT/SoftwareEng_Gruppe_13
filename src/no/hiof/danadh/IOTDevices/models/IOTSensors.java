package no.hiof.danadh.IOTDevices.models;

import java.util.HashMap;

public class IOTSensors extends IOTDevice {
    protected float accuracy;
    protected int minimumOperationingTemp;
    protected int maximumOperationingTemp;
    protected HashMap<String, Integer> size;

    public IOTSensors(String name, String deviceID, String producer, float weight, boolean wifi, String model,
                      int powerUsage, float accuracy, int minimumOperationingTemp, int maximumOperationingTemp,
                      HashMap<String, Integer> size) {
        super(name, deviceID, producer, weight, wifi, model, powerUsage);

        this.accuracy = accuracy;
        this.minimumOperationingTemp = minimumOperationingTemp;
        this.maximumOperationingTemp = maximumOperationingTemp;
        this.size = size;
    }

}

