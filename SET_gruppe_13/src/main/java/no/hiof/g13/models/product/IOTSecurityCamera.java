package no.hiof.g13.models.product;

import java.util.HashMap;

import no.hiof.g13.models.IOTHomeDevice;

public class IOTSecurityCamera extends IOTHomeDevice {
    protected boolean faceRegistrationActivated;
    protected String cloudSaveStatus;

    public IOTSecurityCamera(String name, String deviceID, String producer,
                        String modell, boolean wifi, int weight, HashMap<String, Integer> size,
                        int batteryLevel, int energyUsage, String lightPattern, boolean faceRegistrationActivated, String cloudSaveStatus) {
        
        super(name, deviceID, producer, modell, wifi, weight, size, batteryLevel);

        this.faceRegistrationActivated = faceRegistrationActivated;
        this.cloudSaveStatus = cloudSaveStatus;
    }

    public Boolean movementDetected(Boolean movement){
        return movement;
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
