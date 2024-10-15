package no.hiof.danadh.IOTDevices.models;

abstract class IOTDevice {
    protected String name;
    protected String deviceID;
    protected String producer;
    protected float weight;
    protected boolean wifi;
    protected String model;
    protected int powerUsage;

    public IOTDevice(String name, String deviceID, String producer, float weight, boolean wifi,
                     String model, int powerUsage) {
        this.name = name;
        this.deviceID = deviceID;
        this.producer = producer;
        this.weight = weight;
        this.wifi = wifi;
        this.model = model;
        this.powerUsage = powerUsage;
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

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPowerUsage() {
        return powerUsage;
    }

    public void setPowerUsage(int powerUsage) {
        this.powerUsage = powerUsage;
    }
}
