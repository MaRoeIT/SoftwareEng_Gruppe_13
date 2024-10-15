package no.hiof.danadh.IOTDevices.models;

public class IOTSmartWatch extends IOTDevice {

    public IOTSmartWatch(String name, int batteryLevel, String modes, boolean remoteActivate, int nextPlannedActivation) {
        super(name, batteryLevel, modes, remoteActivate, nextPlannedActivation);
    }

    @Override
    public void configureDevice() {
        System.out.println("Configuring " + name + " wait a minute...");
    }

    @Override
    public void updateSettings() {
        System.out.println("Settings for " + name + " has been successfully updated!");
    }

    @Override
    public int getBatteryLevel() {
        return batteryLevel;
    }
    
}
