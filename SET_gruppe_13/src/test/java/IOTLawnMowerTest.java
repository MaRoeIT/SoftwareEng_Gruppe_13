import no.hiof.g13.models.product.IOTLawnMower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IOTLawnMowerTest {
    private IOTLawnMower lawnMower;

    @BeforeEach
    public void setUp() {
        // Arrange
        HashMap<String, Integer> size = new HashMap<>();
        size.put("length", 100);
        size.put("width", 50);
        lawnMower = new IOTLawnMower("LawnMaster", "ID1234", "GardenPro", "ModelX", true, 20, size, 100, 10, false, false);
    }

    @Test
    @DisplayName("Start mowing with all COND met")
    public void testStartMowing_WithEnoughBattery() {
        // Act
        lawnMower.setBatteryLevel(50);
        lawnMower.startMowing();

        // Assert
        assertTrue(lawnMower.isMowing, "Should start mowing");
    }

    @Test
    @DisplayName("Should NOT start mowing with insufficient battery")
    public void testStartMowing_LowBattery() {
        // Act
        lawnMower.setBatteryLevel(5);
        lawnMower.startMowing();

        // Assert
        assertFalse(lawnMower.isMowing, "Should NOT start mowing.");
    }

    @Test
    @DisplayName("Stop mowing")
    public void testStopMowing() {
        // Act
        lawnMower.setBatteryLevel(50);
        lawnMower.startMowing();
        lawnMower.stopMowing();

        // Assert
        assertFalse(lawnMower.isMowing, "The lawn mower should stop mowing");
    }

    @Test
    @DisplayName("The cutting height when set correctly within the valid range.")
    public void testSetCuttingHeight_ValidHeight() {
        // Act
        lawnMower.setCuttingHeight(30);

        // Assert
        assertEquals(30, lawnMower.cuttingHeight, "The cutting height should be set correctly within the valid range.");
    }

    @Test
    @DisplayName("The cutting height when NOT set within the valid range.")
    public void testSetCuttingHeight_InvalidHeight() {
        // Act
        lawnMower.setCuttingHeight(80);

        // Assert
        assertNotEquals(80, lawnMower.cuttingHeight, "The cutting height should not be set when out of valid range.");
    }

    @Test
    @DisplayName("Return to dock")
    public void testReturnToDock() {
        // Act
        lawnMower.returnToDock();

        // Assert
        assertTrue(lawnMower.omwToDock, "The lawn mower should be on its way to the dock.");
    }
}