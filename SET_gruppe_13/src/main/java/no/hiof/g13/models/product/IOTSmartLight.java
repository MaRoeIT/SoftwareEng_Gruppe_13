package no.hiof.g13.models.product;

import no.hiof.g13.models.IOTHomeDevice;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class IOTSmartLight extends IOTHomeDevice {

    private int energyUsage;
    private String lightPattern;
    private Color color;

    public IOTSmartLight(String name, String deviceID, String producer,
                        String modell, boolean wifi, int weight, HashMap<String, Integer> size,
                        int batteryLevel, int energyUsage, String lightPattern, Color color) {
        
        super(name, deviceID, producer, modell, wifi, weight, size, batteryLevel);
        this.energyUsage = energyUsage;
        this.lightPattern = lightPattern;
        this.color = color;
        this.power = false;
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