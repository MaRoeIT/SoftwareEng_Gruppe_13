package no.hiof.g13.models;

/**
 * General class containing general properties of IOTDevices.
 * Will be changed out for more flexibility.
 */
public abstract class IOTDevice {
    //TODO:Change structure to mix of interface and dependency injection

    protected String name;
    protected String deviceID;
    protected String producer;
    protected float weight;
    protected boolean wifi;
    protected String modell;
    protected boolean power;
    protected Integer connectionID;

    public IOTDevice(String name, String deviceID, String producer, int weight, boolean wifi, String modell){
        this.name = name;
        this.deviceID = deviceID;
        this.producer = producer;
        this.weight = weight;
        this.wifi = wifi;
        this.modell = modell;
    }

    public void configureDevice(){}

    public void updateSettings(){}

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

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public Integer getConnectionID() {
        return connectionID;
    }

    public void setConnectionID(Integer connectionID) {
        this.connectionID = connectionID;
    }
}
