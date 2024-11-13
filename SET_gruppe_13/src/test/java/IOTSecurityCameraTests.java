import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import no.hiof.g13.models.product.IOTSecurityCamera;

@ExtendWith(MockitoExtension.class)

public class IOTSecurityCameraTests {

    @Test
    @DisplayName("Is movement detected?")
    public void movementDetectedTest() {
        //Arrange
        HashMap<String, Integer> size = new HashMap<>();
        size.put("width", 200);
        size.put("height", 300);
        size.put("length", 250);

        IOTSecurityCamera camera = new IOTSecurityCamera("Camera","ABC123", "Huawei",
                "OneCam", true, 1000, size, 80, 8, "asdf", true, "online");

        //Act
        Boolean result = camera.movementDetected(true);

        //Assert
        Boolean expected = true;
        Assertions.assertEquals(expected, result);
    }
}
