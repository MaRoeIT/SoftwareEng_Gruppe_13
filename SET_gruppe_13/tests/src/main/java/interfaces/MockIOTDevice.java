package interfaces;

import java.io.IOException;
import java.io.ObjectInputStream;

public interface MockIOTDevice {
    void connect() throws IOException;
    void disconnect();
    void getStatus();
    void sendData(String data);
    String receiveData();
}
