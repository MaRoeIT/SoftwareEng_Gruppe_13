package no.hiof.g13.models.product;

import java.util.HashMap;
import no.hiof.g13.models.IOTHomeDevice;

public class IOTLawnMower extends IOTHomeDevice {
    public boolean isMowing;
    public boolean omwToDock;
    public int cuttingHeight;
    public int energyUsage;

    public IOTLawnMower(String name, String deviceID, String producer,
                        String model, boolean wifi, int weight, HashMap<String, Integer> size,
                        int batteryLevel, int energyUsage, boolean isMowing, boolean omwToDock) {
        super(name, deviceID, producer, model, wifi, weight, size, batteryLevel);
        this.isMowing = isMowing;
        this.omwToDock = omwToDock;
        this.cuttingHeight = 50;
        this.energyUsage = energyUsage;
    }

    @Override
    public void configureDevice() {
        System.out.println("Configuring " + name + " wait a minute...");
    }

    @Override
    public void updateSettings() {
        System.out.println("Settings for " + name + " has been successfully updated!");
    }

    public void startMowing() {
        if (wifi && batteryLevel > 10) {
            this.isMowing = true;
        } else {
            System.out.println(name + " cannot start mowing. Please check the battery level.");
        }
    }

    public void stopMowing() {
        if (isMowing) {
            isMowing = false;
            System.out.println(name + " has stopped mowing.");
        } else {
            System.out.println(name + " is not currently mowing.");
        }
    }

    public void setCuttingHeight(int height) {
        if (height > 70) {
            System.out.println("\n*** Could not set the cutting height! ***\n" + name
                    + " only supports cutting heights between 10 and 70 mm. Please try again..\n");
        } else {
            this.cuttingHeight = height;
            System.out.println(name + " setting cutting height: " + height + " mm.");
        }
    }

    public void returnToDock() {
        omwToDock = true;
        System.out.println(name + " return to dock.");
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public int getBatteryLevel() {
        System.out.println(name + ": Current battery level " + batteryLevel);
        return batteryLevel;
    }
}
