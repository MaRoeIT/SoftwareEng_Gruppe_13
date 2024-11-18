package interfaces;

import java.io.ObjectInputStream;

public interface MockIOTDevice {
    void connect();
    void disconnect();
    void getStatus();
    void sendData(String data);
    String receiveData();
}
