import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import no.hiof.g13.models.product.IOTLawnMower;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IOTLawnMowerTest {
    private static final int VALID_BATTERY = 50;
    private static final int BATTERY_TOO_LOW = 9;
    private static final int VALID_CUTTING_HEIGHT = 11;
    private static final int TOO_HIGH_CUTTING_HEIGHT = 71;

    IOTLawnMower lawnMower;

    @BeforeEach
    void setUp() {
        // Arrange
        HashMap<String, Integer> size = new HashMap<>();
        size.put("length", 100);
        size.put("width", 50);
        lawnMower = new IOTLawnMower("LawnMaster", "ID1234", "GardenPro", "ModelX",
                true, 20, size, 100, 10, false, false);
    }

    @Test
    @DisplayName("Start mowing with all COND met")
    public void testStartMowing_WithEnoughBattery() {
        // Arrange
        HashMap<String, Integer> size = new HashMap<>();
        size.put("length", 100);
        size.put("width", 50);
        this.lawnMower = new IOTLawnMower("LawnMaster", "ID1234", "GardenPro", "ModelX", true, 20, size, 100, 10, false, false);

        lawnMower.setBatteryLevel(VALID_BATTERY);

        // Act
        boolean result = lawnMower.startMowing();

        // Assert
        assertTrue(result);
        assertTrue(lawnMower.isMowing(), "Should start mowing");
    }

    @Test
    @DisplayName("Should NOT start mowing with insufficient battery")
    public void testStartMowing_LowBattery() {
        // Arrange
        lawnMower.setBatteryLevel(BATTERY_TOO_LOW);

        // Act
        boolean result = lawnMower.startMowing();

        // Assert
        assertFalse(result);
        assertFalse(lawnMower.isMowing(), "Should NOT start mowing.");
    }

    @Test
    @DisplayName("Stop mowing")
    public void testStopMowing() {
        // Arrange
        lawnMower.setBatteryLevel(VALID_BATTERY);
        lawnMower.setIsMowing(true);

        // Act
        boolean result = lawnMower.stopMowing();

        // Assert
        assertFalse(result);
        assertFalse(lawnMower.isMowing(), "The lawn mower should stop mowing");
    }

    @Test
    @DisplayName("The cutting height when set correctly within the valid range.")
    public void testSetCuttingHeight_ValidHeight() {
        // Arrange & act
        lawnMower.setCuttingHeight(VALID_CUTTING_HEIGHT);

        // Assert
        assertEquals(VALID_CUTTING_HEIGHT, lawnMower.getCuttingHeight(), "The cutting height should be set correctly within the valid range.");
    }

    @Test
    @DisplayName("The cutting height when NOT set within the valid range.")
    public void testSetCuttingHeight_InvalidHeight() {
        // Arrange & Act
        lawnMower.setCuttingHeight(TOO_HIGH_CUTTING_HEIGHT);

        // Assert
        assertNotEquals(TOO_HIGH_CUTTING_HEIGHT, lawnMower.getCuttingHeight(), "The cutting height should not be set when out of valid range.");
    }

    @Test
    @DisplayName("Return to dock")
    public void testReturnToDock() {

        // Arrange
        lawnMower.setIsOmwToDock(false);

        // Act
        boolean result = lawnMower.returnToDock();

        // Assert
        assertTrue(result);
        assertTrue(lawnMower.isOmwToDock(), "The lawn mower should be on its way to the dock.");
    }
}