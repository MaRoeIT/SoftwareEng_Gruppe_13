import models.MockSmartLight.MockIOTSmartLight;

import java.util.Scanner;

public class runningTest2 {
    public static void main(String[] args) {
        MockIOTSmartLight device = new MockIOTSmartLight();
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true){
            System.out.println("Write a command: ");
            userInput = scanner.nextLine();
            switch (userInput){
                case "connect":
                    Thread connectionThread = new Thread(device::connect);
                    connectionThread.start();
                    break;
                case "disconnect":
                    device.disconnect();
                case "status":
                    device.getStatus();
                case "exit":
                    System.exit(0);
                default:
                    System.out.println("That is not a command");
            }
        }
    }
}
