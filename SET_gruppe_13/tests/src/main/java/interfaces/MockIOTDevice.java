package interfaces;

public interface MockIOTDevice {
    void connect();
    void disconnect();
    String getStatus();
    void sendData(String data);
    String receiveData();
}
