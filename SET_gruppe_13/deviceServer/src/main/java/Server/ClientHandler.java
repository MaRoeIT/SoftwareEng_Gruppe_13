package Server;

import adapters.SendSignalUseCase;
import no.hiof.g13.adapters.IsNewDeviceUseCase;
import no.hiof.g13.models.IOTDevice;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Handles the connection through sockets for each individual connection to the server
 *
 * Handles everything that happens when a device connects to the server and uses threads through the runnable interface.
 * This does so the server can hold up connection with multiple devices at once and have control over witch connection
 * is tied to witch device.
 */
public class ClientHandler implements Runnable {
    //static list that keep tabs on all the connections, closed connections are removed from the list.
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedWriter output;
    private BufferedReader input;
    private String deviceID;

    /**
     * The constructor saves the socket and create output and input streams. it also checks when there is a new connection
     * if the device exists or not in MyProducts in the core.
     * @param socket
     */
    public ClientHandler(Socket socket){
        try{
            this.socket = socket;
            this.output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.deviceID = input.readLine();
            clientHandlers.add(this);
            sendData("The device " + deviceID + " connected successfully.");
            new SendSignalUseCase().sendSignal(20);
            isNewProduct(IsNewDeviceUseCase.getMyProducts());
        }
        catch (IOException e){
            closeConnection(socket, output, input);
        }
    }

    /**
     * Starts a thread when clientHandler.start() is called.
     * It loops as long as connection is open and listens to the input stream.
     */
    public void run(){
        String data;

        while (!socket.isClosed()){
            try{
                data = input.readLine();

                if (data == null){
                    closeConnection(socket, output, input);
                }
                else {
                    System.out.println(data);
                }
            }
            catch (IOException e){
                closeConnection(socket, output, input);
                break;
            }
        }
    }

    /**
     * Sends a String to a device that is connected to this socket.
     * @param data
     */
    public void sendData(String data){
        try{
            this.output.write(data);
            this.output.newLine();
            this.output.flush();
        }
        catch (IOException e){
            closeConnection(socket, output, input);
        }
    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
    }

    /**
     * closes connection first to output stream then input stream and finally the socket
     * @param socket
     * @param output
     * @param input
     */
    public void closeConnection(Socket socket, BufferedWriter output, BufferedReader input){
        removeClientHandler();
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
            //TODO:Better exception handling
            System.out.println("Something went wrong closing connection: " + e.getMessage());
        }
    }

    /**
     * Checks if a device is new for this user or if it already exists in its product list
     * @param myProducts
     */
    public void isNewProduct(ArrayList<IOTDevice> myProducts){
        boolean newDevice = false;
        for (IOTDevice device:myProducts){
            if (device.getDeviceID().equals(deviceID)){
                System.out.println("The device " + deviceID + " has reconnected");
                newDevice = true;
                break;
            }
        }
        if (!newDevice){
            System.out.println("New device connected, adding device to your products");
            //TODO:Create new IOTDevice and update MyProducts in core
        }
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public static ArrayList<ClientHandler> getClientHandlers() {
        return clientHandlers;
    }

    public static void setClientHandlers(ArrayList<ClientHandler> clientHandlers) {
        ClientHandler.clientHandlers = clientHandlers;
    }
}
