package models.MockSmartLight;

import com.fasterxml.jackson.databind.ObjectMapper;
import netscape.javascript.JSObject;
import DTO.ChangeLightDTO;

import java.awt.*;
import java.io.*;
import java.net.Socket;

public class MockSocketHandler {
    private Socket socket;
    private BufferedWriter output;
    private BufferedReader input;
    private String deviceID;
    private final MockIOTSmartLight mockIOTSmartLight;

    private ChangeLightDTO lastReceivedDTO;
    private boolean newData = false;

    public MockSocketHandler(Socket socket, String deviceID, MockIOTSmartLight mockIOTSmartLight) {
        try {
            this.socket = socket;
            this.output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.deviceID = deviceID;
        }
        catch (IOException e){
            closeConnection();
        }
        this.mockIOTSmartLight = mockIOTSmartLight;
    }


    public void sendData(String data){
        try{
            output.write(deviceID);
            output.newLine();
            output.flush();

            output.write("Device: " + deviceID + " sent");
            output.newLine();
            output.write(data);
            output.newLine();
            output.flush();

        }
        catch (IOException e){
            closeConnection();
        }
    }

    public void listenForData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String dataFromServer;

                while (socket.isConnected()){
                    try {
                        dataFromServer = input.readLine();
                        if (dataFromServer.equals("JSON")){
                            StringBuilder stringBuilder = new StringBuilder();
                            String line;
                            while (input.ready()){
                                line = input.readLine();
                                stringBuilder.append(line).append(System.lineSeparator());
                            }
                            String content = stringBuilder.toString();
                            ObjectMapper objectMapper = new ObjectMapper();
                            System.out.println("how are u doing " + content);
                            try {

                                ChangeLightDTO changeLightDTO = objectMapper.readValue(content,ChangeLightDTO.class);
                                updateLightSettings(changeLightDTO.getLightPattern(), changeLightDTO.getRgb(), changeLightDTO.getLightStrength());
                                System.out.println("This device light settings have been updated to:");
                                System.out.println(mockIOTSmartLight.lightSettingsToString());
                            }catch (IOException e){
                                System.out.println("Something went wrong");
                                e.printStackTrace();
                            }
                        } else if (dataFromServer.equals("1")) {
                            getMockIOTSmartLight().setOn(true);
                        } else if (dataFromServer.equals("2")) {
                            getMockIOTSmartLight().setOn(false);
                        }
                        System.out.println("Data recieved: " + dataFromServer);
                    } catch (IOException e) {
                        closeConnection();
                    }
                }
            }
        }).start();
    }

    public void closeConnection(){
        try{
            if (output != null){
                output.close();
            }
            if (input != null){
                input.close();
            }
            if (socket != null){
                socket.close();
            }
        }
        catch (IOException e){
            System.out.println("Something went wrong closing connection: " + e.getMessage());
        }
    }

    public void updateLightSettings(String lightPattern, int rgb, int lightStrength){
        getMockIOTSmartLight().setLightSettings(lightPattern, new Color(rgb), lightStrength);
    }
    /*
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
    }*/

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BufferedWriter getOutput() {
        return output;
    }

    public void setOutput(BufferedWriter output) {
        this.output = output;
    }

    public BufferedReader getInput() {
        return input;
    }

    public void setInput(BufferedReader input) {
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

    public MockIOTSmartLight getMockIOTSmartLight() {
        return mockIOTSmartLight;
    }
}
