package no.hiof.danadh.IOTDevices.models;

abstract class IOTSmartLight extends IOTDevices {
    protected int energyUsage;
    protected String lightPattern;

    public IOTSmartLight(String name, int batteryLevel, String modes, boolean remoteActivate,
                         int nextPlannedActivation, int energyUsage, String lightPattern) {
        super(name, batteryLevel, modes, remoteActivate, nextPlannedActivation);

        this.energyUsage = energyUsage;
        this.lightPattern = lightPattern;
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
