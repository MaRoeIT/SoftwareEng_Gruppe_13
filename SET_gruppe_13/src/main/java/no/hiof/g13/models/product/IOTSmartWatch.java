package no.hiof.g13.models.product;

import java.util.ArrayList;
import java.util.HashMap;

import no.hiof.g13.models.IOTWearables;

public class IOTSmartWatch extends IOTWearables {

    public int stepCount;
    
    public IOTSmartWatch(
        String name, String deviceID, String producer, int weight,
        boolean wifi, String modell, boolean display, String useStyle,
        ArrayList<String> funksjoner, boolean gps, int batteryCapacity,
        HashMap<String, Integer> size, int stepCount
    ) {
        super(name, deviceID, producer, weight, wifi, modell,
        display, useStyle, funksjoner, gps, batteryCapacity, size);

        this.stepCount = stepCount;
    }

    @Override
    public void configureDevice() {
        System.out.println("Configuring " + name + " wait a minute...");
    }

    @Override
    public void updateSettings() {
        System.out.println("Settings for " + name + " has been successfully updated!");
    }

    public void stepCounter() {
        boolean stepDetected = detectStep();
        if (stepDetected) {
            stepCount++;
            }
        }

    private boolean detectStep() {
        // Simulerer steg for å kunne utføre testing
        return true;
        
        // Her må det byttes ut med faktiske funksjoner
        // som snakker med enheten via en port.
        }
        
        
        public int getStepCount() {
            return stepCount;
        }
        
}
