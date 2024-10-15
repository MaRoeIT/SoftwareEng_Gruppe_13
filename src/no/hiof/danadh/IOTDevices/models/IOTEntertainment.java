package no.hiof.danadh.IOTDevices.models;

abstract class IOTEntertainment extends IOTDevice {
    public IOTEntertainment(String name, String deviceID, String producer, float weight, boolean wifi, String model, int powerUsage) {
        super(name, deviceID, producer, weight, wifi, model, powerUsage);
    }
}
