package models.MockServer;

import models.DTO.SendSmartLightDTO;

import java.awt.*;
import java.net.Socket;
import java.io.*;

public class MockClientHandler extends Thread{
    private Socket socket;

    public MockClientHandler(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try (InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {


            output.writeObject("DATA_INCOMING");
            System.out.println("data incomming");

            SendSmartLightDTO smartLightDTO = new SendSmartLightDTO("Wave", Color.pink, 100);

            output.writeObject(smartLightDTO);
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
}
