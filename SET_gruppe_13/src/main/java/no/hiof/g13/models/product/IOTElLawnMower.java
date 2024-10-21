package no.hiof.g13.models.product;

public class IOTElLawnMower extends IOTDevices {

    public IOTElLawnMower(String name, int batteryLevel, String modes, boolean remoteActivate, int nextPlannedActivation) {
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

}
