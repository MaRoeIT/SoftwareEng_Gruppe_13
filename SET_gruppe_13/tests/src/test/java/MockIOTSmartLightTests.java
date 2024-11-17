import interfaces.MockIOTDevice;
import models.MockSmartLight.MockIOTSmartLight;

public class MockIOTSmartLightTests {
    public static void main(String[] args) {
        MockIOTDevice device = new MockIOTSmartLight();

        device.connect();
        System.out.println("Status: " + device.getStatus());

        device.sendData("Hello");
        String receiveData = device.receiveData();
        System.out.println("Received Data: " + receiveData);

        device.disconnect();
        System.out.println("Status: " + device.getStatus());
    }
}
