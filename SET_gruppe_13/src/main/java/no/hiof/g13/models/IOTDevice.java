package no.hiof.g13.models;

//Generell klasse som inneholder all basisinformasjon generelt om IOT enheter.
abstract class IOTDevice {

    protected String name;
    protected String deviceID;
    protected String producer;
    protected float vekt;
    //Bruker enheten wifi eller ikke til å koble til.
    protected boolean wifi;
    protected String modell;

    //konstruktør
    public IOTDevice(String name, String deviceID, String producer, int vekt, boolean wifi, String modell){
        this.name = name;
        this.deviceID = deviceID;
        this.producer = producer;
        this.vekt = vekt;
        this.wifi = wifi;
        this.modell = modell;
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

    public float getVekt() {
        return vekt;
    }

    public void setVekt(float vekt) {
        this.vekt = vekt;
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
}
