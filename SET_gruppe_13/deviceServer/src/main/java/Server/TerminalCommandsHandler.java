package Server;

import DTO.ChangeLightDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.awt.*;
import java.util.Map;
import java.util.Scanner;

public class TerminalCommandsHandler implements Runnable {
    private DeviceServer server;
    private Map<String, Color> colorMap = Map.ofEntries(
            Map.entry("red", Color.RED),
            Map.entry("green", Color.GREEN),
            Map.entry("blue", Color.BLUE),
            Map.entry("yellow", Color.YELLOW),
            Map.entry("white", Color.WHITE));

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
                        else {
                            System.out.println("That is not a valid deviceID");
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
                        System.out.println("That is not a valid deviceID");
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
                        System.out.println("That is not a valid deviceID");
                    }
                    break;
                default:
                    System.out.println("That is not an accepted command");
                    break;

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
