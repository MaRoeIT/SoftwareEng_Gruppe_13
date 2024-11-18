import models.MockSmartLight.MockIOTSmartLight;

import java.util.Scanner;

public class runningTest2 {
    public static void main(String[] args) {
        MockIOTSmartLight device = new MockIOTSmartLight();
        MockIOTSmartLight device2 = new MockIOTSmartLight();
        MockIOTSmartLight device3 = new MockIOTSmartLight();
        MockIOTSmartLight device4 = new MockIOTSmartLight();
        Scanner scanner = new Scanner(System.in);
        String userInput;

        System.out.println("Starting values");
        System.out.println(device);

        while (true){
            System.out.println("Write a command: ");
            userInput = scanner.nextLine();
            switch (userInput){
                case "connect":
                    Thread connectionThread = new Thread(device::connect);
                    Thread connectionThread2 = new Thread(device2::connect);
                    Thread connectionThread3 = new Thread(device3::connect);
                    Thread connectionThread4 = new Thread(device4::connect);
                    connectionThread.start();
                    connectionThread2.start();
                    connectionThread3.start();
                    connectionThread4.start();
                    break;
                case "disconnect":
                    device.disconnect();
                    break;
                case "status":
                    device.getStatus();
                    device2.getStatus();
                    device3.getStatus();
                    device4.getStatus();
                    break;
                case "exit":
                    device.disconnect();
                    System.exit(0);
                default:
                    System.out.println("That is not a command");
                    break;
            }
        }
    }
}
