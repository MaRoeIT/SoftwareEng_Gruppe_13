package Server;

import no.hiof.g13.DTO.ChangeLightDTO;
import adapters.SendSignalUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import no.hiof.g13.adapters.GetLightSettingsUseCase;
import no.hiof.g13.models.IOTDevice;
import no.hiof.g13.models.product.IOTSmartLight;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * A class for controlling the terminal commands. It runs on threads through the interface runnable and will run even
 * when other work is done by the program.
 */
public class TerminalCommandsHandler implements Runnable {
    private final DeviceServer server;
    //A map that contains some colors, this is so the user won't need to enter rgb values. In the future the program
    //will handle interaction through the frontend where there will be a color picker that fikses the rgb values.
    private Map<String, Color> colorMap = Map.ofEntries(
            Map.entry("red", Color.RED),
            Map.entry("green", Color.GREEN),
            Map.entry("blue", Color.BLUE),
            Map.entry("yellow", Color.YELLOW),
            Map.entry("white", Color.WHITE));

    public TerminalCommandsHandler(DeviceServer server){
        this.server = server;
    }

    /**
     * Runs all the commands for the terminal version of this program.
     * Commands:
     * g or getDeviceIDs: shows all the connected devices ID's (for selecting what device to use other commands on)
     * s or send: Sends a user input string to the device selected
     * cl or changeLight: changes the light settings of the chosen device, the user selects the values from terminal.
     * on or turnOn: Turns the device on if it isn't already on
     * off or turnOff: Turns the device off if it isn't already off
     * lc or lightSettingsFromCore: Changes the light settings of the device based on what the cores device settings are
     */
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (!server.getServerSocket().isClosed()){
            System.out.println("Write a command: ");
            String userInput = scanner.nextLine().trim();
            switch (userInput){
                case "g":
                case "getDeviceIDs":
                    handleGetDeviceIDs();
                    break;
                case "s":
                case "send":
                    handleSend(scanner);
                    break;
                case "cl":
                case "changeLight":
                    handleChangeLight(scanner);
                    break;
                case "on":
                case "turnOn":
                    handleTurnOn(scanner);
                    break;
                case "off":
                case "turnOff":
                    handleTurnOff(scanner);
                    break;
                case "lc":
                case "lightSettingFromCore":
                    handleLightSettingsFromCore(scanner);
                    break;
                default:
                    System.out.println("That is not an accepted command");
                    break;
            }
        }
    }

    public void handleGetDeviceIDs(){
        int i = 0;
        for (ClientHandler handler: ClientHandler.getClientHandlers()){
            System.out.println("Connected device " + i + " Have deviceID: " + handler.getDeviceID());
            i++;
        }
    }

    public void handleSend(Scanner scanner){
        System.out.println("What do you want to send: ");
        String message = scanner.nextLine();

        System.out.println("To what device (deviceID): ");
        String deviceID = scanner.nextLine();

        boolean validateDevice = false;

        for (ClientHandler handler: ClientHandler.getClientHandlers()){
            if (handler.getDeviceID().equals(deviceID)){
                handler.sendData(message);
                System.out.println("Data sent");
                validateDevice = true;
                break;
            }
            System.out.println("That is not a valid deviceID");
        }
        if (!validateDevice){
            System.out.println("That is not a valid deviceID, try again");
            handleSend(scanner);
        }
    }

    public void handleChangeLight(Scanner scanner){
        System.out.println("What device do you wan't to change your light on (deviceID): ");
        String deviceID = scanner.nextLine();

        System.out.println("Choose Light pattern (Solid, wave, pulse, rain): ");
        String lightPattern = scanner.nextLine();

        System.out.println("Choose a color (red, green, blue, yellow, white): ");
        int rgb = getColorMap().get(scanner.nextLine()).getRGB();

        System.out.println("Choose a light strength %: ");
        int lightStrength = scanner.nextInt();
        scanner.nextLine();

        ChangeLightDTO changeLightDTO =  new ChangeLightDTO(lightPattern, rgb, lightStrength);
        sendChangeLightDTO(deviceID, changeLightDTO);
    }

    public void handleTurnOn(Scanner scanner){
        System.out.println("What device do you want to turn on (deviceID): ");
        String deviceID = scanner.nextLine();
        sendCommandToDevice(deviceID, "1");
    }

    public void handleTurnOff(Scanner scanner){
        System.out.println("What device do you wan't to turn off (deviceID): ");
        String deviceID = scanner.nextLine();
        sendCommandToDevice(deviceID, "2");
    }

    public void handleLightSettingsFromCore(Scanner scanner){
        System.out.println("What device do you want to change your light on (deviceID): ");
        String deviceID = scanner.nextLine();
        new SendSignalUseCase().sendSignal(30);
        getLightSettings(GetLightSettingsUseCase.getMyProducts(), deviceID);
    }

    public void sendChangeLightDTO(String deviceID, ChangeLightDTO changeLightDTO){
        ObjectWriter objectMapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            String json = objectMapper.writeValueAsString(changeLightDTO);
            for (ClientHandler handler: ClientHandler.getClientHandlers()){
                if (handler.getDeviceID().equals(deviceID)){
                    handler.sendData("JSON");
                    handler.sendData(json);
                    System.out.println("Data sent");
                }
            }
        }catch (JsonProcessingException e){
            System.err.println("Failed to serialize ChangeLightDTO: " + e.getMessage());
        }
    }

    public void sendCommandToDevice(String deviceID, String command){
        boolean validateDevice = false;
        for (ClientHandler handler: ClientHandler.getClientHandlers()){
            if (handler.getDeviceID().equals(deviceID)){
                handler.sendData(command);
                System.out.println("Data sent");
                validateDevice = true;
                break;
            }
        }
        if (!validateDevice){
            System.out.println("This is not a valid deviceID");
        }
    }

    /**
     * Deals with the MyProducts list from the core and then sends it over to the device. Needs to get
     * ArrayList<IOTDevice> devices from the core. It uses jackson to transform a dto object over the socket to the device
     * @param devices
     * @param deviceID
     */
    public void getLightSettings(ArrayList<IOTDevice> devices, String deviceID){
        for (IOTDevice device: devices){
            if (device.getDeviceID().equals(deviceID) && device instanceof IOTSmartLight iotSmartLight){
                ChangeLightDTO changeLightDTO = new ChangeLightDTO(iotSmartLight.getLightPattern(),
                        iotSmartLight.getColor().getRGB(), iotSmartLight.getLightStrength());
                sendChangeLightDTO(deviceID, changeLightDTO);
            }
        }
    }

    public Map<String, Color> getColorMap() {
        return colorMap;
    }
}
