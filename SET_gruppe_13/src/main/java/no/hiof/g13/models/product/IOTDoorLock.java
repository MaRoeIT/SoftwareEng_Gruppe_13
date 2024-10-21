package no.hiof.g13.models.product;

import java.util.HashMap;

import no.hiof.g13.models.IOTHomeDevice;

public class IOTDoorLock extends IOTHomeDevice {
protected boolean lockedStatus;
protected int userAuthentication;

    public IOTDoorLock(String name, String deviceID, String producer,
    String modell, boolean wifi, int weight, HashMap<String, Integer> size,
    int batteryLevel, int energyUsage, String lightPattern) {

super(name, deviceID, producer, modell, wifi, weight, size, batteryLevel);

this.lockedStatus = lockedStatus;
this.userAuthentication = userAuthentication;
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
