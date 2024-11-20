package Server;

import no.hiof.g13.DTO.ChangeLightDTO;
import adapters.IsNewDeviceUseCase;
import adapters.UpdateConnectionIDUseCase;
import interfaces.GenericDevice;
import no.hiof.g13.interfaces.GenericDeviceDTO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class DeviceServer extends Thread{
    Map<Integer, ClientHandler> clientList = new HashMap<>();
    private Integer clientID = 0;

    public DeviceServer() {
    }

    @Override
    public void run(){
        listen();
    }

    public void listen() {
        try (ServerSocket serverSocket = new ServerSocket(0)) {
            System.out.println("Server is listening on port: " + serverSocket.getLocalPort());
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                ClientHandler clientHandler = new ClientHandler(socket, this);

                //clientHandler.receieveDeviceID();

                clientList.put(getClientID(), clientHandler);
                updateClientID();
                clientHandler.start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDataToClient(ChangeLightDTO data, Integer clientID){
        clientList.get(clientID).sendDataToQueue(data);
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public void updateClientID(){
        //Change this so that it Updates core every time the ID's are updated
        this.clientID = this.clientID + 1;
    }

    public Map<Integer, ClientHandler> getClientList() {
        return clientList;
    }

    public void setClientList(Map<Integer, ClientHandler> clientList) {
        this.clientList = clientList;
    }

    public void printClientListID(){
        for (Integer ID : clientList.keySet()){
            System.out.println(ID);
        }
    }
}
