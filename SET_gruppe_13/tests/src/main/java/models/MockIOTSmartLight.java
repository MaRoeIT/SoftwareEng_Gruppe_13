package models;

import java.io.*;
import java.net.*;

import interfaces.MockIOTDevice;

import static java.lang.System.out;

public class MockIOTSmartLight implements MockIOTDevice {
    private boolean connected = false;
    private String lastReceivedData = "";

    public MockIOTSmartLight() {
    }

    @Override
    public void connect() {
        try(Socket socket = new Socket("localhost", 4444)){
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new BufferedReader(new InputStreamReader(input)));

            String response;
            connected = true;
            out.println("Device connected.");

            writer.println("Hello, Server");
            while (connected){
                keepConnectionAlive(writer);

                while ((response = reader.readLine()) != null){
                    out.println("Server response: " + response);
                }
            }
        }
        catch (UnknownHostException e){
            out.println("Server not found: " + e.getMessage());
        }
        catch (IOException e){
            out.println("I/O error: " + e.getMessage());
        }
    }

    public void keepConnectionAlive(PrintWriter writer) {
        new Thread(() -> {
            try {
                while (true) {
                    writer.println(200);
                    Thread.sleep(5000);
                }
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    @Override
    public void disconnect() {
        connected = false;
        out.println("Device disconnected.");
    }

    @Override
    public String getStatus(){
        if (connected) {
            return "Connected";
        }
        else {
            return "Disconnected";
        }
    }

    @Override
    public void sendData(String data) {
        if (connected){
            out.println("Sending data: " + data);
            lastReceivedData = data;
        }
        else {
            out.println("Cannot send data. Device is not connected");
        }
    }

    @Override
    public String receiveData() {
        if (connected){
            out.println("Receiving data...");
            return lastReceivedData;
        }
        else {
            return "Cannot receive data. Device is not connected";
        }
    }
}
