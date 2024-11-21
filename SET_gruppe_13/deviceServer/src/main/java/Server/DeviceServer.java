package Server;

import no.hiof.g13.DTO.ChangeLightDTO;
import adapters.IsNewDeviceUseCase;
import adapters.UpdateConnectionIDUseCase;
import interfaces.GenericDevice;
import no.hiof.g13.interfaces.GenericDeviceDTO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DeviceServer{
    private ServerSocket serverSocket;
    private ClientHandler clientHandler;
    Map<Integer, ClientHandler> clientList = new HashMap<>();
    private Integer clientID = 0;

    public DeviceServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer(){
        try{
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("A new device has connected");
                clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }


        }
        catch (IOException e){
            System.out.println("I/O failed at start server: " + e.getMessage());
        }
    }

    public void closeServerSockets() {
        try {
            if (serverSocket != null){
                serverSocket.close();
            }
        }
        catch (IOException e){
            System.out.println("Something went wrong with closing the serverSocket: " + e.getMessage());
            System.exit(1);
        }
    }

    public void userInput(){
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (!serverSocket.isClosed()){
            userInput = scanner.nextLine();
            switch (userInput){
                case "getDeviceIDs":
                    int i = 0;
                    for (ClientHandler handler: ClientHandler.getClientHandlers()){
                        System.out.println("Connected device " + i + " Have deviceID: " + handler.getDeviceID());
                        i++;
                    }
                case "send":
                    System.out.println("What do you want to send: ");
                    userInput = scanner.nextLine();
                    System.out.println("To what device (deviceID): ");
                    String deviceID = scanner.nextLine();
                    for (ClientHandler handler: ClientHandler.getClientHandlers()){
                        if (handler.getDeviceID().equals(deviceID)){
                            handler.sendData(userInput);
                            System.out.println("Data sent");
                        }
                        else {
                            System.out.println("That is not a valid deviceID");
                        }
                    }
                default:
                    System.out.println("That is not an accepted command");

            }
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        DeviceServer server = new DeviceServer(serverSocket);
        TerminalCommandsHandler terminalCommandsHandler = new TerminalCommandsHandler(server);
        Thread thread = new Thread(terminalCommandsHandler);
        thread.start();
        server.startServer();


        /*
        while (!serverSocket.isClosed()){
            Scanner scanner = new Scanner(System.in);
            String userInput;

            userInput = scanner.nextLine();
            switch (userInput){
                case "g":
                case "getDeviceIDs":
                    int i = 0;
                    for (ClientHandler handler: ClientHandler.getClientHandlers()){
                        System.out.println("Connection " + i + " has deviceID: " + handler.getDeviceID());
                        i++;
                    }
                    break;
                case "s":
                case "send":
                    System.out.println("Write what to send: ");
                    userInput = scanner.nextLine();
                    System.out.println("To what device (deviceID)? ");
                    String deviceID = scanner.nextLine();
                    for (ClientHandler handler: ClientHandler.getClientHandlers()){
                        if (handler.getDeviceID().equals(deviceID)){
                            handler.sendData(userInput);
                            System.out.println("Data sent");
                        }
                        else {
                            System.out.println("Couldn't find that device");
                        }
                    }
                    break;
                default:
                    System.out.println("That is not an accepted command");
            }
        }*/
    }

    /*

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
    }*/

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public ClientHandler getClientHandler() {
        return clientHandler;
    }

    public void setClientHandler(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }
}
