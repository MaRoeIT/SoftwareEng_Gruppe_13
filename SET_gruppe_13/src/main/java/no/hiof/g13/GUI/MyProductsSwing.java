package no.hiof.g13.GUI;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import no.hiof.g13.models.*;

public class MyProductsSwing extends JPanel {
    private int i;
    public static void main(String[] args) {
        ArrayList<IOTDevice> testIOT = new ArrayList<>();

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
        IOTDoorLock smartLockPro2 = new IOTDoorLock("Yale SmartLock Pro", "SLP123",
                "Yale", "SmartLock Pro", false, 2800, yaleSize, 100, true,
                134819);
        IOTDoorLock smartLockPro3 = new IOTDoorLock("Yale SmartLock Pro", "SLP123",
                "Yale", "SmartLock Pro", false, 2800, yaleSize, 100, true,
                134819);

        testIOT.add(yaleDoorman);
        testIOT.add(lockX1);
        testIOT.add(secureDoor);
        testIOT.add(smartLockPro);
        testIOT.add(smartLockPro2);
        testIOT.add(smartLockPro3);
    }

    public void runMyProducts(ArrayList<IOTDevice> devices){
        JFrame mainWindow = new JFrame();

        for(i=0; i < devices.size(); i++){
            mainWindow.add(new JLabel(devices.get(i).getName(), JLabel.CENTER));
        }

        System.out.println("Length / 3 =" + Math.ceilDiv(devices.size(),3) + " \n");

        mainWindow.setLayout(new GridLayout(Math.ceilDiv(devices.size(),3),3));

        mainWindow.setSize(600,600);
        mainWindow.setVisible(true);
    }
}
