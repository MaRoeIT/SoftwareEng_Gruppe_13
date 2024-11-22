package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The server that listens for client connections.
 */
public class DeviceServer{
    private ServerSocket serverSocket;
    private ClientHandler clientHandler;

    public DeviceServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        System.out.println("The server is listening on port: " + serverSocket.getLocalPort());
    }

    /**
     * Starts the server by opening upp for connection on its serverSocket.
     * Also generates a new client handler with socket on a new thread for each device that connects.
     */
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

    /**
     * Closes the serverSocket, if it is unable to close the socket the program will end with an exit status 1
     */
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

    /**
     * Starts the server by generating a server object and uses the startServer() to make the server ready to listen
     * for connections.
     * It also starts the TerminalCommandsHandler witch handles the terminal commands.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        /*There have been instances where the port has been taken by other programs, if that happens change it from
        new ServerSocket(9999); to new ServerSocket(0); witch will let the os select a free port for you.
        You must then also use the command setPort on the client side to match the port on the server, this will be
        printed out in the terminal window of the server*/
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
