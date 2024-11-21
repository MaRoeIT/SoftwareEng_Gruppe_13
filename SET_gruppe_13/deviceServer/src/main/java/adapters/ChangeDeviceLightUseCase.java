package adapters;


import no.hiof.g13.DTO.ChangeLightDTO;
import no.hiof.g13.interfaces.GenericDeviceDTO;
import no.hiof.g13.ports.out.DeviceDataSender;
import Server.DeviceServer;

public class ChangeDeviceLightUseCase implements DeviceDataSender {
    private DeviceServer server;
    @Override
    public void sendData(ChangeLightDTO dto, int deviceID) {/*
        getServer().sendDataToClient(dto,deviceID);
        */
    }

    public DeviceServer getServer() {
        return server;
    }

    public void setServer(DeviceServer server) {
        this.server = server;
    }
}
