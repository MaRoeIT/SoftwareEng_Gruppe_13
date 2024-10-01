package no.hiof.danadh.IOTDevices.models;

abstract class IOTDevices {
    protected String name;
    protected int batteryLevel;
    protected String modes;
    protected boolean remoteActivate;
    protected int nextPlannedActivation;

    public IOTDevices(String name, int batteryLevel, String modes, boolean remoteActivate, int nextPlannedActivation) {
        this.name = name;
        this.batteryLevel = batteryLevel;
        this.modes = modes;
        this.remoteActivate = remoteActivate;
        this.nextPlannedActivation = nextPlannedActivation;
    }

    public void configureDevice() {
        System.out.println(name + " is being configured with default settings...");
    }

    public void updateSettings() {
        System.out.println(name + " settings updated successfully.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String getModes() {
        return modes;
    }

    public void setModes(String modes) {
        this.modes = modes;
    }

    public boolean isRemoteActivate() {
        return remoteActivate;
    }

    public void setRemoteActivate(boolean remoteActivate) {
        this.remoteActivate = remoteActivate;
    }

    public int getNextPlannedActivation() {
        return nextPlannedActivation;
    }

    public void setNextPlannedActivation(int nextPlannedActivation) {
        this.nextPlannedActivation = nextPlannedActivation;
    }
}
