package no.hiof.g13.models;

//Generell klasse som inneholder all basisinformasjon generelt om IOT enheter.
abstract class IOTDevice {

    protected String name;
    protected String deviceID;
    protected String producer;
    protected float vekt;

    //konstruktør
    public IOTDevice(String name, String deviceID, String producer, int vekt){
        this.name = name;
        this.deviceID = deviceID;
        this.producer = producer;
        this.vekt = vekt;
    }
}
