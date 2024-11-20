package Server;

import no.hiof.g13.DTO.ChangeLightDTO;
import adapters.IsNewDeviceUseCase;
import adapters.UpdateConnectionIDUseCase;
import interfaces.GenericDevice;
import no.hiof.g13.interfaces.GenericDeviceDTO;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ClientHandler extends Thread {
    private Socket socket;
    private DeviceServer deviceServer;
    private BlockingQueue<GenericDeviceDTO> sendDataQueue = new LinkedBlockingQueue<>();

    public ClientHandler(Socket socket, DeviceServer deviceServer){
        this.deviceServer = deviceServer;
        this.socket = socket;
    }

    public void run(){
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

    public void receieveDeviceID(){
        try(ObjectInputStream input = new ObjectInputStream(socket.getInputStream())){
            System.out.println("i am inside receive");
            while (true){
                String signal = (String) input.readObject();
                System.out.println("Here is the signal: " + signal);
                if ("DEVICE_ID".equals(signal)){
                    System.out.println("got device id");
                    String deviceID = (String) input.readObject();
                    System.out.println(deviceID);
                    IsNewDeviceUseCase isNewDeviceUseCase = new IsNewDeviceUseCase(deviceServer);
                    if (isNewDeviceUseCase.isNewDevice(deviceID)){
                        //Create new IOT device adapter
                        break;
                    } else if (!isNewDeviceUseCase.isNewDevice(deviceID)) {
                        new UpdateConnectionIDUseCase(isNewDeviceUseCase.getIotDevice());
                        break;
                    }
                    else {
                        System.out.println("Something went wrong");
                        break;
                    }
                }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
