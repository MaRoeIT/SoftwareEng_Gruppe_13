package models.MockSmartLight;

import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import interfaces.MockIOTDevice;

import static java.lang.System.out;

/**
 * This is a class that is used to test connectivity and functionality, it is supposed to resemble a very simple IOT
 * device.
 */
public class MockIOTSmartLight implements MockIOTDevice {
    //A boolean that is thread safe, the atomic boolean takes care of problems of concurrent threads trying to edit
    //The same variable. Read more on https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/package-summary.html
    private final AtomicBoolean connected = new AtomicBoolean(false);
    private Socket socket;
    private MockSocketHandler socketHandler;

    //These are supposed to be allocated based on the users products but for this mvp we focus on only changing some
    //of the settings and information.
    private String name = "Govee LED light";
    private String deviceID;
    private String producer = "Govee";
    private String modell = "L28";
    private boolean wifi = true;
    private int batteryLevel = 100;
    private String lightPattern;
    private Color color;
    private int lightStrength;
    private boolean isOn = false;

    //since we have had troubles with programs claiming ports we work on we let this variable be changable
    //if we had the opportunity to claim a port this would be final
    private static int port = 9999;

    private static final int LOW_BATTERY_WARNING_LEVEL = 15;
    private static final int CRITICAL_BATTERY_WARNING_LEVEL = 5;

    public MockIOTSmartLight(String deviceID) {
        this.deviceID = deviceID;
    }

    /**
     * Connects this device with the server trough sockets. it creates the MockSocketHandler that handles the socket
     * interactions.
     * @throws IOException
     */
    @Override
    public void connect() throws IOException {
        this.socket = new Socket("localHost", port);
        this.socketHandler = new MockSocketHandler(socket, deviceID, this);
        connected.set(true);
        out.println("Device connected");

        socketHandler.sendData("I am connected");

        socketHandler.listenForData();

        monitorBatteryLevel();
    }

    /**
     * Sends out warnings when the battery level of the device hits low and critical.
     */
    private void monitorBatteryLevel(){
        while(batteryLevel >= 0){
            this.batteryLevel--;
            try {
                TimeUnit.MILLISECONDS.sleep(700);
            }catch (InterruptedException e){
                System.err.println("Sleep was interrupted: " + e.getMessage());
            }
            if (batteryLevel == CRITICAL_BATTERY_WARNING_LEVEL){
                socketHandler.sendData("CRITICAL BATTERY LEVEL! Device only have 5% power left");
            } else if (batteryLevel == LOW_BATTERY_WARNING_LEVEL) {
                socketHandler.sendData("Low on battery, Device only have 15% power left, we recommend charging it");
            }
        }
    }

    @Override
    public void disconnect() {
        try {
            socketHandler.closeConnection();
            connected.set(false);
            out.println("Device disconnected.");
        }
        catch (NullPointerException e){
            System.err.println("The device haven't been connected and couldn't be disconnected\nError message: " + e.getMessage());
        }
    }


    @Override
    public void getStatus(){
        if (socketHandler.getSocket().isConnected()) {
            out.println("The device is Connected");
        }
        else {
            out.println("The device is Disconnected");
        }
        out.println("\n" + this);
    }

    @Override
    public void sendData(String data) {
        if (connected.get()){
            socketHandler.sendData(data);
        }
        else {
            out.println("Cannot send data. Device is not connected");
        }
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

    public String lightSettingsToString(){
        return "MockIOTSmartLight{" +
                "\n\tcolor=         " + color +
                ", \n\tlightStrength= " + lightStrength +
                ", \n\tisOn=          " + isOn +
                "\n}";
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }
}
