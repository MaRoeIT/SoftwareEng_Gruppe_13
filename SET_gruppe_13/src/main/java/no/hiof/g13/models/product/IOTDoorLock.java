package no.hiof.g13.models.product;

import no.hiof.g13.models.IOTHomeDevice;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class IOTDoorLock extends IOTHomeDevice {
    protected boolean lockedStatus;
    protected int userAuthentication;

    public IOTDoorLock(String name, String deviceID, String producer,
                       String modell, boolean wifi, int weight,
                       HashMap<String, Integer> size, int batteryLevel, boolean lockedStatus,
                       int userAuthentication) {
        super(name, deviceID, producer, modell, wifi, weight, size, batteryLevel);

        this.lockedStatus = lockedStatus;
        this.userAuthentication = userAuthentication;
    }
    @Override
    public void configureDevice() {
        System.out.println("Configuring " + name + " wait a minute...");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Configuring " + name + " done");
    }
    @Override
    public void updateSettings() {
        System.out.println("Settings for " + name + " has been successfully updated!");
    }

}
