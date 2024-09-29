package no.hiof.g13.models;

import java.util.HashMap;

abstract class IOTSensors extends IOTDevice {
    protected float accurecy;
    protected int minimumOperatingTemp;
    protected int maximumOperatingTemp;
    //diameter er ganske vanlig for sensorer
    protected HashMap<String, Integer> size = new HashMap<>();

    public IOTSensors(
            String name, String deviceID, String producer, int weight,
            boolean wifi, String modell, float accurecy, int minimumOperatingTemp,
            int maximumOperatingTemp, HashMap<String, Integer> size
    ) {
        super(name, deviceID, producer, weight, wifi, modell);
        this.accurecy = accurecy;
        this.minimumOperatingTemp = minimumOperatingTemp;
        this.maximumOperatingTemp = maximumOperatingTemp;
        this.size = size;
    }
}
