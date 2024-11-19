package no.hiof.g13.ports.out;

import no.hiof.g13.DTO.ChangeLightDTO;
import no.hiof.g13.interfaces.GenericDeviceDTO;

public interface DeviceDataSender {
    void sendData(GenericDeviceDTO dto, int DeviceID);
}
