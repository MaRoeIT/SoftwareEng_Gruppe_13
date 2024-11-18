package no.hiof.g13.GUI;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import no.hiof.g13.models.*;

public class MyProductsSwing extends JPanel {
    private int i;

    public void runMyProducts(ArrayList<IOTDevice> devices){
        JFrame mainWindow = new JFrame("IOTAwsome");

        Toolkit toolKit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolKit.getScreenSize();

        for(i=0; i < devices.size(); i++){
            deviceBox(i, devices, mainWindow);
        }

        System.out.println("Length / 3 =" + Math.ceilDiv(devices.size(),3) + " \n");

        mainWindow.setLayout(new GridLayout(0,3,100,50));

        mainWindow.setSize(screenSize.width,screenSize.height);
        mainWindow.setVisible(true);

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);
    }

    public void deviceBox(int i, ArrayList<IOTDevice> devices, JFrame window){
        JPanel devicePanel = new JPanel();
        JLabel deviceLabel = new JLabel();
        ImageIcon backgroundImage = new ImageIcon("images/yaleDoorman.jpg");

        backgroundImage.getImage().getScaledInstance(300,300,Image.SCALE_SMOOTH);

        JLabel background = new JLabel(backgroundImage);

        background.setLayout(new BorderLayout());

        devicePanel.setPreferredSize(new Dimension(300,300));

        deviceLabel.setText(devices.get(i).getName());

        devicePanel.setBackground(Color.GRAY);

        devicePanel.add(deviceLabel);
        devicePanel.add(background, BorderLayout.CENTER);
        window.add(devicePanel);
    }
}
