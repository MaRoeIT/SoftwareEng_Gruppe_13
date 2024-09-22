package no.hiof.danadh.IOTDevices.models;

abstract class IOTDoorLock extends IOTDevices {
    protected boolean lockedStatus;
    protected int userAuthentication;

    public IOTDoorLock(String name, int batteryLevel, String modes,
                       boolean remoteActivate, int nextPlannedActivation,
                       boolean lockedStatus, int userAuthentication) {
        super(name, batteryLevel, modes, remoteActivate, nextPlannedActivation);

        this.lockedStatus = lockedStatus;
        this.userAuthentication = userAuthentication;
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
