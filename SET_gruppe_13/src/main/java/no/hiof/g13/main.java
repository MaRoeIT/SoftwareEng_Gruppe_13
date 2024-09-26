package no.hiof.g13;
import no.hiof.g13.models.*;

import java.util.HashMap;

public class main {
    public static void main(String[] args) {
        HashMap<String, Integer> yaleSize = new HashMap<>(){{
            put("height", 60);
            put("length", 240);
            put("width", 340);
        }};
        IOTDoorLock yaleDoorman = new IOTDoorLock("Yale Doorman L3 Flex", "YHAR8948293-23",
                "Yale", "L3 Flex", false, 3730, yaleSize, 100, true,
                134819);

        yaleDoorman.configureDevice();
        yaleDoorman.updateSettings();
    }
}
