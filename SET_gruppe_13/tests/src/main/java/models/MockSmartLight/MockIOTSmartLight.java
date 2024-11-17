package models.MockSmartLight;

import java.awt.*;
import java.io.*;
import java.net.*;

import interfaces.MockIOTDevice;
import models.DTO.SendSmartLightDTO;

import static java.lang.System.out;

public class MockIOTSmartLight implements MockIOTDevice {
    private boolean connected = false;
    private String lastReceivedData = "";

    private String name = "Govee LED light";
    private String deviceID = "130410949094";
    private String producer = "Govee";
    private String modell = "L28";
    private boolean wifi = true;
    private int batteryLevel = 100;
    private String lightPattern;
    private Color color;
    private int lightStrength;
    private boolean isOn = false;

    public MockIOTSmartLight() {
    }

    @Override
    public void connect() {
        try(Socket socket = new Socket("localhost", 4444)){
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            connected = true;
            out.println("Device connected.");

            writer.println("Hello, Server");
            while (connected){
                keepConnectionAlive(writer);

                try {
                    String signal = (String) input.readObject();
                    if ("DATA_INCOMING".equals(signal)){
                        out.println("Data incoming");
                        SendSmartLightDTO testClass = (SendSmartLightDTO) input.readObject();
                        out.println("Received object from server: " + testClass);
                        this.lightPattern = testClass.getLightPattern();
                        this.color = testClass.getColor();
                        this.lightStrength = testClass.getLightStrength();
                        out.println("Changes done to color = " + this.color + " lightPattern = " + this.lightPattern);
                    }
                }
                catch (ClassNotFoundException e){
                    out.println("No data yet: " + e.getMessage());
                }


            }
        }
        catch (UnknownHostException e){
            out.println("Server not found: " + e.getMessage());
        }
        catch (IOException e){
            out.println("I/O error: " + e.getMessage());
        }

    }

    public void keepConnectionAlive(PrintWriter writer) {
        new Thread(() -> {
            try {
                while (true) {
                    writer.println(200);
                    Thread.sleep(5000);
                }
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    @Override
    public void disconnect() {
        connected = false;
        out.println("Device disconnected.");
    }

    @Override
    public String getStatus(){
        if (connected) {
            return "Connected";
        }
        else {
            return "Disconnected";
        }
    }

    @Override
    public void sendData(String data) {
        if (connected){
            out.println("Sending data: " + data);
            lastReceivedData = data;
        }
        else {
            out.println("Cannot send data. Device is not connected");
        }
    }

    @Override
    public String receiveData() {
        if (connected){
            out.println("Receiving data...");
            return lastReceivedData;
        }
        else {
            return "Cannot receive data. Device is not connected";
        }
    }

    @Override
    public void reciveDataHandler(ObjectInputStream receivedData){

    }
}
