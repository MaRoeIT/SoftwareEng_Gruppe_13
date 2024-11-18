package models.MockSmartLight;

import models.DTO.SendSmartLightDTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MockSocketHandler {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    private SendSmartLightDTO lastReceivedDTO;
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

    public void sendData(SendSmartLightDTO smartLightDTO){
        try {
            output.writeObject(smartLightDTO);
        }
        catch (IOException e){
            System.out.println("Error sending data: " + e.getMessage());
        }
    }

    public void receiveData(){
        new Thread (() -> {
            try {
                String signal = (String) input.readObject();
                if ("DATA_INCOMING".equals(signal)){
                    System.out.println("data incoming");
                    this.lastReceivedDTO = (SendSmartLightDTO) input.readObject();
                    this.newData = true;
                }
            }
            catch (IOException | ClassNotFoundException e){
                System.out.println("Error receiving data: " + e.getMessage());
            }
        }).start();
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

    public SendSmartLightDTO getLastReceivedDTO() {
        return lastReceivedDTO;
    }

    public void setLastReceivedDTO(SendSmartLightDTO lastReceivedDTO) {
        this.lastReceivedDTO = lastReceivedDTO;
    }

    public boolean isNewData() {
        return newData;
    }

    public void setNewData(boolean newData) {
        this.newData = newData;
    }
}
