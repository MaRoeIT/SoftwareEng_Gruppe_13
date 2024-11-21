package Server;

import adapters.SendSignalUseCase;
import com.google.gson.Gson;
import no.hiof.g13.DTO.ChangeLightDTO;
import no.hiof.g13.adapters.IsNewDeviceUseCase;
import no.hiof.g13.interfaces.GenericDeviceDTO;
import no.hiof.g13.models.IOTDevice;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedWriter output;
    private BufferedReader input;
    private String deviceID;
    private DeviceServer deviceServer;
    private BlockingQueue<GenericDeviceDTO> sendDataQueue = new LinkedBlockingQueue<>();

    public ClientHandler(Socket socket){
        try{
            this.socket = socket;
            this.output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.deviceID = input.readLine();
            clientHandlers.add(this);
            sendData("The device " + deviceID + " connected successfully.");
            //TODO:Send signal to core need my products
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
        /*
        try (ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {

            try{
                while (true){
                    GenericDeviceDTO data = sendDataQueue.take();
                    output.writeObject("DATA_INCOMING");
                    System.out.println("data incomming");
                    output.writeObject(data);
                }
            }
            catch (InterruptedException | NullPointerException | ClassCastException | IllegalArgumentException e){
                System.out.println(e.getMessage());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/
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
            System.out.println("Something went wrong closing connection: " + e.getMessage());
        }
    }

    public void sendDataToQueue(ChangeLightDTO data){
        try {
            sendDataQueue.put(data);
            System.out.println("Data sent to queue");
        }
        catch (InterruptedException | NullPointerException | ClassCastException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void isNewProduct(ArrayList<IOTDevice> myProducts){
        for (IOTDevice device:myProducts){
            if (device.getDeviceID().equals(deviceID)){
                System.out.println("This device exsists in the list");
                break;
            }
            //TODO:Create new iot device and update myproducts in core
        }

    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
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
