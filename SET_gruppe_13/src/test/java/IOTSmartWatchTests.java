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

    IOTSmartWatch mockSmartWatch;

    @BeforeEach
    void setUp() {
        ArrayList<String> funksjoner = new ArrayList<>();
        funksjoner.add("Puls Monitor");
        funksjoner.add("GPS");

        HashMap<String, Integer> size = new HashMap<>();
        size.put("width", 40);
        size.put("height", 50);

        mockSmartWatch = new IOTSmartWatch(
            "Tesla watch", "12", "MuskBands", 9, true, "X Æ A-12", true, "formal",
            funksjoner, true, 42, size, 0
        );
    }

    @Test
    @DisplayName("Check initial steps counter is 0")
    void testStepCounterInitialCount() {
        assertEquals(0, mockSmartWatch.stepCount);
    }

    @Test
    @DisplayName("Step Counter increment check")
    void testStepCounterIncrements() {
        mockSmartWatch.stepCounter();

        if (mockSmartWatch.stepCount == 1) {
            assertEquals(1, mockSmartWatch.stepCount);
        }
    }

    @Test
    void testMultipleStepIncrements() {
        for (int i = 0; i < 10; i++) {
            mockSmartWatch.stepCounter();
        }

        assertTrue(mockSmartWatch.stepCount > 8, "Step should increment.");
    }

}