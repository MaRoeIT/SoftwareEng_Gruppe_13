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
                case "cl":
                case "changeLight":
                    System.out.println("What device do you wan't to change your light on (deviceID): ");
                    userInput = scanner.nextLine();

                    System.out.println("Choose Light pattern (Solid, wave, pulse, rain): ");
                    String lightPattern = scanner.nextLine();

                    System.out.println("Choose a color (red, green, blue, yellow, white): ");
                    int rgb = getColorMap().get(scanner.nextLine()).getRGB();

                    System.out.println("Choose a light strength %: ");
                    int lightStrength = scanner.nextInt();

                    ChangeLightDTO changeLightDTO =  new ChangeLightDTO(lightPattern, rgb, lightStrength);
                    for (ClientHandler handler: ClientHandler.getClientHandlers()){
                        if (handler.getDeviceID().equals(userInput)){
                            ObjectWriter objectMapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
                            String json;
                            try {
                                json = objectMapper.writeValueAsString(changeLightDTO);
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }
                            handler.sendData("JSON");
                            handler.sendData(json);
                            System.out.println("Data sent");
                        }
                    }
                    break;
                case "on":
                case "turnOn":
                    System.out.println("What device do you wan't to turn on (deviceID): ");
                    userInput = scanner.nextLine();

                    for (ClientHandler handler: ClientHandler.getClientHandlers()){
                        if (handler.getDeviceID().equals(userInput)){
                            handler.sendData("1");
                            System.out.println("Data sent");
                            break;
                        }
                    }
                    break;
                case "off":
                case "turnOff":
                    System.out.println("What device do you wan't to turn off (deviceID): ");
                    userInput = scanner.nextLine();

                    for (ClientHandler handler: ClientHandler.getClientHandlers()){
                        if (handler.getDeviceID().equals(userInput)){
                            handler.sendData("2");
                            System.out.println("Data sent");
                            break;
                        }
                    }
                    break;
                case "lc":
                case "lightSettingFromCore":
                    System.out.println("What device do you wan't to change your light on (deviceID): ");
                    userInput = scanner.nextLine();
                    new SendSignalUseCase().sendSignal(30);
                    getLightSettings(GetLightSettingsUseCase.getMyProducts(), userInput);
                    break;
                default:
                    System.out.println("That is not an accepted command");
                    break;
            }
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
            if (device.getDeviceID().equals(deviceID)){
                if (device instanceof IOTSmartLight iotSmartLight){
                    ChangeLightDTO changeLightDTO = new ChangeLightDTO(iotSmartLight.getLightPattern(),
                            iotSmartLight.getColor().getRGB(), iotSmartLight.getLightStrength());

                    ObjectWriter objectMapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
                    String json;
                    try {
                        json = objectMapper.writeValueAsString(changeLightDTO);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    for (ClientHandler handler: ClientHandler.getClientHandlers()){
                        if (handler.getDeviceID().equals(deviceID)){
                            handler.sendData("JSON");
                            handler.sendData(json);
                            System.out.println("Data sent");
                        }
                    }

                }

            }
        }
    }

    public Map<String, Color> getColorMap() {
        return colorMap;
    }

    public void setColorMap(Map<String, Color> colorMap) {
        this.colorMap = colorMap;
    }
}
