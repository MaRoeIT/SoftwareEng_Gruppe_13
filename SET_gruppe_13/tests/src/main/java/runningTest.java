import interfaces.MockIOTDevice;
import models.MockIOTSmartLight;
import models.MockServer;

public class runningTest {
    public static void main(String[] args) {
        MockServer server = new MockServer();

        server.start();

    }
}
