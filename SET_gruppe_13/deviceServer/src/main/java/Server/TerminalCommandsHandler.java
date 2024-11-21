package Server;

import java.util.Scanner;

public class TerminalCommandsHandler implements Runnable {
    private DeviceServer server;

    public TerminalCommandsHandler(DeviceServer server){
        this.server = server;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (!server.getServerSocket().isClosed()){
            System.out.println("Write a command: ");
            userInput = scanner.nextLine();
            switch (userInput){
                case "g":
                case "getDeviceIDs":
                    int i = 0;
                    for (ClientHandler handler: ClientHandler.getClientHandlers()){
                        System.out.println("Connected device " + i + " Have deviceID: " + handler.getDeviceID());
                        i++;
                    }
                    break;
                case "s":
                case "send":
                    System.out.println("What do you want to send: ");
                    userInput = scanner.nextLine();
                    System.out.println("To what device (deviceID): ");
                    String deviceID = scanner.nextLine();
                    for (ClientHandler handler: ClientHandler.getClientHandlers()){
                        if (handler.getDeviceID().equals(deviceID)){
                            handler.sendData(userInput);
                            System.out.println("Data sent");
                            break;
                        }
                        System.out.println("That is not a valid deviceID");
                    }
                    break;
                default:
                    System.out.println("That is not an accepted command");
                    break;

            }
        }
    }

}
