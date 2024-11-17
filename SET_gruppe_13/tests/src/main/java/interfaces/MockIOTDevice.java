package interfaces;

import java.io.ObjectInputStream;

public interface MockIOTDevice {
    void connect();
    void disconnect();
    String getStatus();
    void sendData(String data);
    String receiveData();
    void reciveDataHandler(ObjectInputStream data);
}
