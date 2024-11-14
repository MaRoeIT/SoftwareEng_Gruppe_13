package models;

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
             OutputStream output = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(output, true)) {

            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Received: " + message);
                if (message.equals("200")){
                    writer.println("Connection OK");
                }
                else {
                    writer.println("Hello device");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
