package models.MockSmartLight;

import com.fasterxml.jackson.databind.ObjectMapper;
import DTO.ChangeLightDTO;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Handles the connection and communication through the socket to the server.
 */
public class MockSocketHandler {
    //Add more commands when adding more functionality
    private static final String TURN_ON_COMMAND = "1";
    private static final String TURN_OFF_COMMAND = "2";

    private Socket socket;
    private BufferedWriter output;
    private BufferedReader input;
    private final String deviceID;
    private final MockIOTSmartLight mockIOTSmartLight;

    private ChangeLightDTO lastReceivedDTO;
    private boolean newData = false;

    //Also part of the concurrent toolkit, for handling Threads. this makes sure that tasks are run subsequently.
    //Read more https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    /**
     * Initialize socket, deviceID, mockIOTSmartlight, BufferedWriter and BufferedReader. Will close connection if the
     * Initialization fails.
     * @param socket
     * @param deviceID
     * @param mockIOTSmartLight
     */
    public MockSocketHandler(Socket socket, String deviceID, MockIOTSmartLight mockIOTSmartLight) {
        this.deviceID = deviceID;
        this.mockIOTSmartLight = mockIOTSmartLight;
        try {
            this.socket = socket;
            this.output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e){
            closeConnection();
        }
    }


    /**
     * Sends a String to the server.
     * @param data
     * Example:
     * <pr>
     *     MockSocketHandler socketHandler = new MockSocketHandler();
     * <p>
     *     socketHandler.sendData("Hello World!);
     * </pr>
     */
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

    /**
     * Listens for incoming data from the server, if the data comes with the String JSON it will take the next lines
     * and convert them from JSON back to an object. Thread are used to make sure the handler can continue to run
     * while listening for data.
     */
    public void listenForData(){
        executorService.submit(() -> {
            String dataFromServer;

            while (socket.isConnected()){
                try {
                    dataFromServer = input.readLine();
                    switch (dataFromServer) {
                        case "JSON":
                            String data = readJsonData();
                            processJsonData(data);
                        case TURN_ON_COMMAND:
                            mockIOTSmartLight.setOn(true);
                        case TURN_OFF_COMMAND:
                            mockIOTSmartLight.setOn(false);
                    }
                    System.out.println("Data recieved: " + dataFromServer);
                } catch (IOException e) {
                    closeConnection();
                }
            }
        });
    }

    /**
     * Builds a String based on the whole incoming input stream. Returns a the string for further processing.
     * @return
     * @throws IOException
     */
    private String readJsonData() throws IOException{
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while (input.ready() && (line = input.readLine()) != null){
            stringBuilder.append(line).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    /**
     * Processes data that is in JSON format and generates the data back to a ChangeLightDTO.
     * @param data
     */
    private void processJsonData(String data){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ChangeLightDTO changeLightDTO = objectMapper.readValue(data,ChangeLightDTO.class);
            updateLightSettings(changeLightDTO.getLightPattern(), changeLightDTO.getRgb(), changeLightDTO.getLightStrength());
            System.out.println("This device settings have been updated to: ");
            System.out.println(mockIOTSmartLight.lightSettingsToString());
        }
        catch (IOException e){
            System.err.println("Failed to process JSON data: " + e.getMessage());
        }
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
            System.err.println("Something went wrong closing connection: " + e.getMessage());
        }
    }

    public void updateLightSettings(String lightPattern, int rgb, int lightStrength){
        mockIOTSmartLight.setLightSettings(lightPattern, new Color(rgb), lightStrength);
    }

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
