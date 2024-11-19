package Server;

import interfaces.GenericDevice;
import no.hiof.g13.interfaces.GenericDeviceDTO;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientHandler extends Thread {
    private Socket socket;
    private BlockingQueue<GenericDeviceDTO> sendDataQueue = new LinkedBlockingQueue<>();

    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try (InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {

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

            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Received: " + message);
                if (message.equals("200")){
                    output.writeObject("Connection OK");
                }
                else {
                    output.writeObject("Hello device");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDataToQueue(GenericDeviceDTO data){
        try {
            sendDataQueue.put(data);
            System.out.println("Data sent to queue");
        }
        catch (InterruptedException | NullPointerException | ClassCastException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
