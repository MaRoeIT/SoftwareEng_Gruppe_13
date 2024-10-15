package no.hiof.danadh.IOTDevices.models;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;

abstract class IOTHomeDevice extends IOTDevice {
    protected HashMap<String, Integer> size;
    protected int batteryLevel;
    protected ArrayList<OffsetDateTime> schedule;

    public IOTHomeDevice(String name, String deviceID, String producer, float weight, boolean wifi, String model,
                         int powerUsage, HashMap<String, Integer> size,
                         int batteryLevel, ArrayList<OffsetDateTime> schedule) {
        super(name, deviceID, producer, weight, wifi, model, powerUsage);

        this.size = size;
        this.batteryLevel= batteryLevel;
        this.schedule = schedule;
    }


    public int getSize(String dummy) {
        return 0;
    }

    public void updateBattery(int batteryLevel){
        return;
    }

    public void updateDeviceBattery(int batteryLevel) {
        return;
    }
}
