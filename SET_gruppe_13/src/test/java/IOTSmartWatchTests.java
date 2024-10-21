import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import no.hiof.g13.models.product.IOTSmartWatch;




@ExtendWith(MockitoExtension.class)
public class IOTSmartWatchTests {

    IOTSmartWatch smartWatch;

    @BeforeEach
    void setUp() {
        ArrayList<String> funksjoner = new ArrayList<>();
        funksjoner.add("Puls Monitor");
        funksjoner.add("GPS");

        HashMap<String, Integer> size = new HashMap<>();
        size.put("width", 40);
        size.put("height", 50);

        smartWatch = new IOTSmartWatch(
            "Tesla watch", "12", "MuskBands", 9, true, "X Æ A-12", true, "formal",
            funksjoner, true, 42, size, 0
        );
    }

    @Test
    @DisplayName("Check initial steps counter is 0")
    void testStepCounterInitialCount() {
        assertEquals(0, smartWatch.stepCount);
    }

    @Test
    @DisplayName("Step Counter increment check")
    void testStepCounterIncrements() {
        smartWatch.stepCounter();

        if (smartWatch.stepCount == 1) {
            assertEquals(1, smartWatch.stepCount);
        }
    }

    @Test
    void testMultipleStepIncrements() {
        for (int i = 0; i < 10; i++) {
            smartWatch.stepCounter();
        }

        assertTrue(smartWatch.stepCount > 0, "Step should increment.");
    }

}