package no.hiof.g13.models;

//Generell klasse som inneholder all basisinformasjon generelt om IOT enheter.
abstract class IOTDevice {

    protected String name;
    protected String deviceID;
    protected String producer;
    protected float vekt;
    //Bruker enheten wifi eller ikke til å koble til.
    protected boolean wifi;

    //konstruktør
    public IOTDevice(String name, String deviceID, String producer, int vekt, boolean wifi){
        this.name = name;
        this.deviceID = deviceID;
        this.producer = producer;
        this.vekt = vekt;
        this.wifi = wifi;
    }
}
