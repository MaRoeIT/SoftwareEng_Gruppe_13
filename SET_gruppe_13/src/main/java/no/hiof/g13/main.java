package no.hiof.g13;
import no.hiof.g13.models.*;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;

public class main {

    public static void main(String[] args) {
        boolean r = true;
        Scanner userScanner = new Scanner(System.in);
        String userInput;
        int batteryWarningLevel = 10;
        HashMap<String, Integer> yaleSize = new HashMap<>(){{
            put("height", 60);
            put("length", 240);
            put("width", 340);
        }};
        IOTDoorLock yaleDoorman = new IOTDoorLock("Yale Doorman L3 Flex", "YHAR8948293-23",
                "Yale", "L3 Flex", false, 3730, yaleSize, 100, true,
                134819);
        IOTDoorLock lockX1 = new IOTDoorLock("Crono lock X1", "LDX12345",
                "Crono", "LockX1", true, 2500, yaleSize, 100, true,
                134819);
        IOTDoorLock secureDoor = new IOTDoorLock("Senden SecureDoor", "SD00789",
                "Senden", "SecureDoor", false, 3200, yaleSize, 100, true,
                134819);
        IOTDoorLock smartLockPro = new IOTDoorLock("Yale SmartLock Pro", "SLP123",
                "Yale", "SmartLock Pro", false, 2800, yaleSize, 100, true,
                134819);

        MyProducts myProducts = new MyProducts();

        yaleDoorman.configureDevice();
        yaleDoorman.updateSettings();

        myProducts.addProducts(yaleDoorman,lockX1,secureDoor,smartLockPro);

        for (IOTDevice device : myProducts.getMyProducts()){
            System.out.println(device.getName());
        }

        while (r){
            yaleDoorman.updateDeviceBattery(1);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (yaleDoorman.getBattery() <= batteryWarningLevel){
                System.out.println("The battery of " + yaleDoorman.getName() + "is now " + yaleDoorman.getBattery() +
                        "%\nWe suggest charging the device.");
                System.out.println("\nDo you wan't to charge the device now ?(y/n) ");
                userInput = userScanner.nextLine();
                switch (userInput.toUpperCase()){
                    case "Y":
                        yaleDoorman.updateBattery(100);
                        batteryWarningLevel = 10;
                        System.out.println("Your device is at 100%");
                    case "N":
                        System.out.println("Ok you will get a new notice soon");
                        batteryWarningLevel -= 2;
                    default:
                        System.out.println("That's not a valid input");
                }
            }
            if (yaleDoorman.getBattery() <= 0){
                System.out.println(yaleDoorman.getName() + " just went out of power");
                r = false;
            }
        }
    }
}
