package no.hiof.g13.models;

abstract class IOTDevice {

    protected String name;
    protected String deviceID;
    protected String producer;

    public IOTDevice(String name, String deviceID, String producer){
        this.name = name;
        this.deviceID = deviceID;
        this.producer = producer;
    }
}
