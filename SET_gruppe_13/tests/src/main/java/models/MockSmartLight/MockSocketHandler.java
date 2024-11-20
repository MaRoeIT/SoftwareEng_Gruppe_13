package models.MockSmartLight;

import no.hiof.g13.DTO.ChangeLightDTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MockSocketHandler {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    private ChangeLightDTO lastReceivedDTO;
    private boolean newData = false;

    public MockSocketHandler(String host, int port) {
        try {
            this.socket = new Socket(host, port);
            this.output = new ObjectOutputStream(socket.getOutputStream());
            this.input = new ObjectInputStream(socket.getInputStream());
            socket.setKeepAlive(true);
        }
        catch (IOException e){
            System.out.println("I/O error: " + e.getMessage());
        }
    }

    public void sendData(ChangeLightDTO smartLightDTO){
        try {
            output.writeObject(smartLightDTO);
        }
        catch (IOException e){
            System.out.println("Error sending data: " + e.getMessage());
        }
    }

    public void sendDeviceID(String deviceID){
        try {
            output.writeObject("DEVICE_ID");
            output.writeObject(deviceID);
            System.out.println("Device sent");
        }
        catch (IOException e){
            System.out.println("I/O exeption: " + e.getMessage());
        }

    }

    public void receiveData(){
        new Thread (() -> {
            try {
                System.out.println("thread line 1");
                String signal = (String) input.readObject();
                System.out.println("thread line 2");
                System.out.println(signal);
                if ("DATA_INCOMING".equals(signal)){
                    this.lastReceivedDTO = (ChangeLightDTO) input.readObject();
                    this.newData = true;
                }
            }
            catch (IOException | ClassNotFoundException e){
                System.out.println("Error receiving data: " + e.getMessage());
            }
        }).start();
        System.out.println("Done");
    }

    public void closeConnection() {
        try{
            socket.close();
        }
        catch (IOException e){
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectOutputStream getOutput() {
        return output;
    }

    public void setOutput(ObjectOutputStream output) {
        this.output = output;
    }

    public ObjectInputStream getInput() {
        return input;
    }

    public void setInput(ObjectInputStream input) {
        this.input = input;
    }

    public ChangeLightDTO getLastReceivedDTO() {
        return lastReceivedDTO;
    }

    public void setLastReceivedDTO(ChangeLightDTO lastReceivedDTO) {
        this.lastReceivedDTO = lastReceivedDTO;
    }

    public boolean isNewData() {
        return newData;
    }

    public void setNewData(boolean newData) {
        this.newData = newData;
    }
}
