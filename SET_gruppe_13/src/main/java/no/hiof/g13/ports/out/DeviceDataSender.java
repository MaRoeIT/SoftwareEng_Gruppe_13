package main.java.no.hiof.g13.ports.out;

import main.java.no.hiof.g13.interfaces.GenericDeviceDTO;

public interface DeviceDataSender {
    void sendData(GenericDeviceDTO dto);
}
