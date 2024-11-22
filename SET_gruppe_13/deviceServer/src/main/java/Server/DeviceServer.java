package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DeviceServer{
    private ServerSocket serverSocket;
    private ClientHandler clientHandler;

    public DeviceServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer(){
        try{
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("A device has connected");
                clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }
        catch (IOException e){
            System.out.println("I/O failed at start server: " + e.getMessage());
            closeServerSockets();
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

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9999);
        DeviceServer server = new DeviceServer(serverSocket);
        TerminalCommandsHandler terminalCommandsHandler = new TerminalCommandsHandler(server);
        Thread thread = new Thread(terminalCommandsHandler);
        thread.start();
        server.startServer();
    }

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
