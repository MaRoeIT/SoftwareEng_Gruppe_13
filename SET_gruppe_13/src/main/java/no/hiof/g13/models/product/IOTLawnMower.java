package no.hiof.g13.models.product;

import java.util.HashMap;
import no.hiof.g13.models.IOTHomeDevice;

public class IOTLawnMower extends IOTHomeDevice {
    private static final int MIN_REQUIRED_BATTERY = 10;
    private static final int MIN_ALLOWED_CUTTING_HEIGHT = 10;
    private static final int MAX_ALLOWED_CUTTING_HEIGHT = 70;
    private static final int DEFAULT_CUTTING_HEIGHT = 50;

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
        this.cuttingHeight = DEFAULT_CUTTING_HEIGHT;
        this.energyUsage = energyUsage;
    }

    @Override
    public void configureDevice() {
        System.out.println("Configuring " + getName() + " wait a minute...");
    }

    @Override
    public void updateSettings() {
        System.out.println("Settings for " + getName() + " has been successfully updated!");
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public boolean isMowing() {
        return this.isMowing;
    }

    public void setIsMowing(boolean isMoving) {
        this.isMowing = isMoving;
    }

    public boolean isOmwToDock() {
        return this.omwToDock;
    }
    public void setIsOmwToDock(boolean omwToDock) {
        this.omwToDock = omwToDock;
    }

    public int getCuttingHeight() {
        return this.cuttingHeight;
    }

    public int getEnergyUsage() {
        return this.energyUsage;
    }

    public boolean startMowing() {
        if (isWifi() && getBattery() > MIN_REQUIRED_BATTERY) {
            setIsMowing(true);
            return true;
        } else {
            System.out.println(name + " cannot start mowing. Please check the battery level.");
            return false;
        }
    }

    public boolean stopMowing() {
        if (!isMowing()) {
            System.out.println(name + " is not currently mowing.");
            return true;
        }

        setIsMowing(false);
        System.out.println(name + " has stopped mowing.");
        return false;
    }

   public void setCuttingHeight(int cuttingHeight) {
        if (MAX_ALLOWED_CUTTING_HEIGHT > cuttingHeight && cuttingHeight > MIN_ALLOWED_CUTTING_HEIGHT) {
            this.cuttingHeight = cuttingHeight;
            System.out.println(name + " setting cutting height: " + cuttingHeight + " mm.");

        } else {
            System.out.println("\n*** Could not set the cutting height! ***\n" + getName()
                    + " only supports cutting heights between 10 and 70 mm. Please try again..\n");
        }
    }

    public boolean returnToDock() {
        if(isOmwToDock()) {
            System.out.println("Already returning to dock.");
            return false;
        }

        setIsOmwToDock(true);
        System.out.println(name + " return to dock.");
        return true;
    }
}
