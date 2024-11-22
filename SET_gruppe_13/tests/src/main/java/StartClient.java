import models.MockSmartLight.MockIOTSmartLight;

import java.io.IOException;
import java.util.Scanner;

/**
 * Starts the client and handles the commands for the client. It doesn't run on Threads since it doesn't need to run
 * anything else than the command loop at the same time.
 */
public class StartClient {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in);){
            //a device would always have a deviceID but in this test we need to be able to generate different
            //instances of this runnable class, to not get the same ID on devices you have to manually type it in
            System.out.println("Select deviceID: ");
            String deviceID = scanner.nextLine();

            MockIOTSmartLight device = new MockIOTSmartLight(deviceID);

            System.out.println("Starting values");
            System.out.println(device);

            //Commands aviable:
            //c or connect: connects the device to the server
            //d or disconnect: disconnects the socket
            //s or status: Prints out the current settings and if the device is connected
            //e or exit: closes the socket before exiting the program
            //sp or setPort: Change the port this device will try to connect to
            while (true){
                System.out.println("Write a command: ");
                String userInput = scanner.nextLine();
                switch (userInput){
                    case "c":
                    case "connect":
                        device.connect();
                        break;
                    case "d":
                    case "disconnect":
                        device.disconnect();
                        break;
                    case "s":
                    case "status":
                        device.getStatus();
                        break;
                    case "e":
                    case "exit":
                        device.disconnect();
                        System.exit(0);
                    case "sp":
                    case "setPort":
                        setPort(scanner, device);
                        break;
                    default:
                        System.out.println("That is not a command");
                        break;
                }
            }
        }
    }

    /**
     * Handles setting the port to what the user wants. This could be necessery if the port provided is taken by another
     * program. if so change in main the port number of ServerSocket() to ServerSocket(0) wich lets the os handle
     * what port is provided, then use the set port command on the client side to set the port before connecting.
     * The new port number of the DeviceServer will be posted in the DeviceServer Terminal.
     * @param scanner
     * @param device
     */
    private static void setPort(Scanner scanner, MockIOTSmartLight device){
        System.out.println("What port do you want to connect to: ");
        String userInput = scanner.nextLine();
        try{
            int port = Integer.parseInt(userInput);
            device.setPort(port);
            System.out.println("Port set to " + port);
        }
        catch (NumberFormatException e){
            System.out.println("That is not a number!");
        }
    }
}
