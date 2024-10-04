package no.hiof.g13;
import no.hiof.g13.GUI.MyProductsSwing;
import no.hiof.g13.models.*;
import no.hiof.g13.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
public class Main {
    public static void main(String[] args) {

        MyProductsSwing myProductWindow = new MyProductsSwing();
        /*
        List<Bruker> brukere = new ArrayList<>();

        try {
            Connection connection = mysql.getConnection();

            Statement statement = connection.createStatement();

            String selectQuery = "select * from gruppe13.bruker";

            ResultSet rs = statement.executeQuery(selectQuery);

            while (rs.next()) {
                Bruker bruker = new Bruker();
                bruker.setBruker_id(rs.getInt("bruker_id"));
                bruker.setFornavn(rs.getString("fornavn"));
                bruker.setEtternavn(rs.getString("etternavn"));
                bruker.setStatus_id(rs.getInt("status_id(FK)"));
                bruker.setKontakt_id(rs.getInt("kontakt_id(FK)"));

                brukere.add(bruker);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Bruker bruker : brukere) {
            System.out.println("Bruker id: " +bruker.getBruker_id()+ " Navn: " +bruker.getFornavn());
        }
        */

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
        IOTDoorLock smartLockPro1 = new IOTDoorLock("Yale SmartLock Pro", "SLP123",
                "Yale", "SmartLock Pro", false, 2800, yaleSize, 100, true,
                134819);
        IOTDoorLock smartLockPro2 = new IOTDoorLock("Yale SmartLock Pro", "SLP123",
                "Yale", "SmartLock Pro", false, 2800, yaleSize, 100, true,
                134819);

        MyProducts myProducts = new MyProducts();

        myProducts.addProducts(yaleDoorman,lockX1,secureDoor,smartLockPro, smartLockPro1, smartLockPro);

        myProductWindow.runMyProducts(myProducts.getMyProducts());

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