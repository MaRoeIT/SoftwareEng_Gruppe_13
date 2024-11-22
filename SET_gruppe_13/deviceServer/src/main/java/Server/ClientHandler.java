package Server;

import adapters.SendSignalUseCase;
import no.hiof.g13.adapters.IsNewDeviceUseCase;
import no.hiof.g13.models.IOTDevice;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedWriter output;
    private BufferedReader input;
    private String deviceID;

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

    public void sendData(String data){
        for (ClientHandler clientHandler:clientHandlers){
            try{
                if (clientHandler.deviceID.equals(deviceID)){
                    clientHandler.output.write(data);
                    clientHandler.output.newLine();
                    clientHandler.output.flush();
                }
            }
            catch (IOException e){
                closeConnection(socket, output, input);
            }
        }
    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
    }

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
