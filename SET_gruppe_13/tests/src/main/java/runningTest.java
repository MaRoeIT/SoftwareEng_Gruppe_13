import interfaces.MockIOTDevice;
import models.MockIOTSmartLight;
import models.MockServer;

public class runningTest {
    public static void main(String[] args) {
        MockIOTSmartLight device = new MockIOTSmartLight();
        MockIOTDevice device2 = new MockIOTSmartLight();
        MockIOTDevice device3 = new MockIOTSmartLight();
        MockServer server = new MockServer();

        server.start();
        device.connect();
        device2.connect();
        device3.connect();
    }
}
