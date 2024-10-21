package no.hiof.g13.models.product;

abstract class IOTSecurityCamera extends IOTDevices {
    protected boolean faceRegistrationActivated;
    protected String cloudSaveStatus;

    public IOTSecurityCamera(String name, int batteryLevel, String modes,
                              boolean remoteActivate, int nextPlannedActivation, boolean faceRegistrationActivated,
                              String cloudSaveStatus) {
        super(name, batteryLevel, modes, remoteActivate, nextPlannedActivation);

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
