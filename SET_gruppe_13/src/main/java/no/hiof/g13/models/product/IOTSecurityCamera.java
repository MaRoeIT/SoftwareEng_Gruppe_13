package no.hiof.g13.models.product;

import java.util.HashMap;

import no.hiof.g13.models.IOTHomeDevice;

abstract class IOTSecurityCamera extends IOTHomeDevice {
    protected boolean faceRegistrationActivated;
    protected String cloudSaveStatus;

    public IOTSecurityCamera(String name, String deviceID, String producer,
                        String modell, boolean wifi, int weight, HashMap<String, Integer> size,
                        int batteryLevel, int energyUsage, String lightPattern) {
        
        super(name, deviceID, producer, modell, wifi, weight, size, batteryLevel);

        this.faceRegistrationActivated = faceRegistrationActivated;
        this.cloudSaveStatus = cloudSaveStatus;
    }

    @Override
    public void configureDevice() {
        System.out.println("Configuring " + name + " wait a minute...");
    }
    @Override
    public void updateSettings() {
        System.out.println("Settings for " + name + " has been successfully updated!");
    }
}
