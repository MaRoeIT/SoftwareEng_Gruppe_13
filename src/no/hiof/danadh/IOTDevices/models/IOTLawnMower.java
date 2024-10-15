package no.hiof.danadh.IOTDevices.models;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class IOTLawnMower extends IOTHomeDevice {
    public IOTLawnMower(String name, String deviceID,
                        String producer, float weight, boolean wifi, String model, int powerUsage,
                        HashMap<String, Integer> size, int batteryLevel, ArrayList<OffsetDateTime> schedule) {
        super(name, deviceID, producer, weight, wifi, model, powerUsage, size, batteryLevel, schedule);
    }

    @Override
    public void configureDevice() {
        super.configureDevice();
    }

    @Override
    public void updateSettings() {
        super.updateSettings();
    }
}
