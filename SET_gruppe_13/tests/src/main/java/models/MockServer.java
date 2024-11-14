package models;

import java.io.*;
import java.net.*;

public class MockServer extends Thread {
    public MockServer() {
    }

    @Override
    public void run(){
        listen();
    }

    public void listen() {
        try (ServerSocket serverSocket = new ServerSocket(4444)) {
            System.out.println("Server is listening on port 4444");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                new MockClientHandler(socket).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
