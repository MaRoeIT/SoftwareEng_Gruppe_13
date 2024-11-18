package models.MockSmartLight;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

import interfaces.MockIOTDevice;
import models.DTO.SendSmartLightDTO;

import static java.lang.System.out;

public class MockIOTSmartLight implements MockIOTDevice {
    private boolean connected = false;
    private String lastReceivedData = "";
    private Socket socket;
    private MockSocketHandler socketHandler;

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
        this.socketHandler = new MockSocketHandler("localhost", 4444);
        out.println("Device connected");

        socketHandler.receiveData();

        while (true){
            if (socketHandler.isNewData()){
                updateLightSettings();
                socketHandler.setNewData(false);
            }
            //needs a delay to work, the delay can go down to exactly 77 milliseconds before it won't run properly
            try {
                TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException e){
                out.println(e.getMessage());
            }
        }

        /*
        try(socket = new Socket("localhost", 4444)){
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
        */
    }

    /*
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
    }*/

    @Override
    public void disconnect() {
        try {
            socketHandler.closeConnection();
            out.println("Device disconnected.");
        }
        catch (NullPointerException e){
            out.println("The device haven't been connected and couldn't be disconnected\n" + e.getMessage());
        }
        //connected = false;
    }

    @Override
    public void getStatus(){
        if (!socketHandler.getSocket().isClosed()) {
            out.println("Connected");
        }
        else {
            out.println("Disconnected");
        }
        out.println("\n" + this);
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

    public void updateLightSettings(){
        setLightSettings(socketHandler.getLastReceivedDTO().getLightPattern(),
                socketHandler.getLastReceivedDTO().getColor(),
                socketHandler.getLastReceivedDTO().getLightStrength());
        out.println("The light settings are updated");
    }

    public void sendLightSettings(){
        socketHandler.sendData(new SendSmartLightDTO(this.lightPattern, this.color, this.lightStrength));
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setLightSettings(String lightPattern, Color color, int lightStrength){
        this.lightPattern = lightPattern;
        this.color = color;
        this.lightStrength = lightStrength;
    }

    @Override
    public String toString() {
        return "MockIOTSmartLight{" +
                "\n\tname=          '" + name + '\'' +
                ", \n\tdeviceID=      '" + deviceID + '\'' +
                ", \n\tproducer=      '" + producer + '\'' +
                ", \n\tmodell=        '" + modell + '\'' +
                ", \n\twifi=          " + wifi +
                ", \n\tbatteryLevel=  " + batteryLevel +
                ", \n\tlightPattern=  '" + lightPattern + '\'' +
                ", \n\tcolor=         " + color +
                ", \n\tlightStrength= " + lightStrength +
                ", \n\tisOn=          " + isOn +
                "\n}";
    }
}
