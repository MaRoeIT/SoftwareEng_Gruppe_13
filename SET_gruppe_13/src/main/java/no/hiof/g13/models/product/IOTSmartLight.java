package no.hiof.g13.models.product;

import no.hiof.g13.models.IOTHomeDevice;

import java.awt.*;
import java.util.HashMap;

/**
 * Controlls the functionality of smart light IOT devices.
 *
 * IOTSmartLight handles changing the lights strength, color
 * and the handling of turning on and of the light based on the TimeScheduler class
 *
 * This class inherits general IOT functionality and datapoints from IOTHomeDevice,
 * examples like returning the dimensions of the device witch is handeled in IOTHomeDevice.
 *
 * Example usage:
 * IOTSmartLight smartLight = new IOTSmartLight("LightMaster", "2049281", "Philips",
 *                                              "L22", false, 20,
 *                                               sizeMap, 100, 10
 *                                              "wave", softBlue, 100);
 *
 */
public class IOTSmartLight extends IOTHomeDevice {

    private int energyUsage;
    private String lightPattern;
    private Color color;
    private int lightStrength;

    public IOTSmartLight(String name, String deviceID, String producer,
                        String modell, boolean wifi, int weight, HashMap<String, Integer> size,
                        int batteryLevel, int energyUsage, String lightPattern, Color color, int lightStrength) {
        
        super(name, deviceID, producer, modell, wifi, weight, size, batteryLevel);
        this.energyUsage = energyUsage;
        this.lightPattern = lightPattern;
        this.color = color;
        this.power = false;
        this.lightStrength = lightStrength;
    }

    /**
     * Configures the IOT device so it is ready for use.
     */
    @Override
    public void configureDevice() {
        System.out.println("Configuring " + name + " wait a minute...");
    }

    /**
     * Updates the settings set for the IOT device.
     */
    @Override
    public void updateSettings() {
        System.out.println("Settings for " + name + " has been successfully updated!");
    }


    public int getEnergyUsage() {
        return energyUsage;
    }

    public void setEnergyUsage(int energyUsage) {
        this.energyUsage = energyUsage;
    }

    public String getLightPattern() {
        return lightPattern;
    }

    public void setLightPattern(String lightPattern) {
        this.lightPattern = lightPattern;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getLightStrength() {
        return lightStrength;
    }

    public void setLightStrength(int lightStrength) {
        this.lightStrength = lightStrength;
    }
}