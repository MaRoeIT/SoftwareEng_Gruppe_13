import models.DTO.SendSmartLightDTO;
import models.MockServer.MockServer;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class runningTest {
    public static void main(String[] args) {
        MockServer server = new MockServer();
        SendSmartLightDTO dto = new SendSmartLightDTO("wave", Color.pink, 100);
        Scanner scanner = new Scanner(System.in);
        String userInput;

        Map<String, Color> colorMap = new HashMap<>();
        colorMap.put("red", Color.RED);
        colorMap.put("green", Color.GREEN);
        colorMap.put("blue", Color.BLUE);
        colorMap.put("yellow", Color.YELLOW);
        colorMap.put("black", Color.BLACK);
        colorMap.put("white", Color.WHITE);

        server.start();

        while (true){
            System.out.println("Write a command: ");
            userInput = scanner.nextLine();
            switch (userInput){
                case "listID":
                    server.printClientListID();
                    break;
                case "send":
                    System.out.println("Write a clientID: ");
                    userInput = scanner.nextLine();
                    try {
                        int ID = Integer.parseInt(userInput);
                        server.sendDataToClient(dto, ID);
                    }
                    catch (NumberFormatException e){
                        System.out.println("That is not a valid ID");
                    }
                    break;
                case "changeLight":
                    System.out.println("write new light pattern: ");
                    String lightPattern = scanner.nextLine();
                    System.out.println("Write a color (red, green, blue, yellow, black, white): ");
                    Color color = colorMap.get(scanner.nextLine());
                    System.out.println("Write new light strength: ");
                    int lightStrength = scanner.nextInt();

                    dto.setAllVariables(lightPattern, color, lightStrength);
                    System.out.println("Dto changed and ready to send: ");
            }
        }


    }
}
