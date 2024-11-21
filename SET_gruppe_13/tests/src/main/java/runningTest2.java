import models.MockSmartLight.MockIOTSmartLight;

import java.io.IOException;
import java.util.Scanner;

public class runningTest2 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        System.out.println("Select deviceID: ");
        String deviceID = scanner.nextLine();

        MockIOTSmartLight device = new MockIOTSmartLight(deviceID);

        System.out.println("Starting values");
        System.out.println(device);

        while (true){
            System.out.println("Write a command: ");
            userInput = scanner.nextLine();
            switch (userInput){
                case "connect":
                    device.connect();
                    break;
                case "disconnect":
                    device.disconnect();
                    break;
                case "status":
                    device.getStatus();
                    break;
                case "exit":
                    device.disconnect();
                    System.exit(0);
                case "setPort":
                    System.out.println("What port do you want to connect to: ");
                    userInput = scanner.nextLine();
                    try{
                        int port = Integer.parseInt(userInput);
                        device.setPort(port);
                        System.out.println("Port set to " + port);
                    }
                    catch (NumberFormatException e){
                        System.out.println("That is not a number!");
                    }
                    break;
                default:
                    System.out.println("That is not a command");
                    break;
            }
        }
    }
}
