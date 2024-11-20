package adapters;

import no.hiof.g13.models.product.IOTSmartLight;
import no.hiof.g13.models.IOTDevice;
import no.hiof.g13.ports.in.ConnectionIDPort;

public class UpdateConnectionIDUseCase implements ConnectionIDPort {
    private static Integer connectionID;
    //forandre senere til generell med IOTDevice istede for spesific smartlight
    private final IOTDevice iotDevice;

    public UpdateConnectionIDUseCase(IOTDevice iotDevice){
        this.iotDevice = iotDevice;
    }

    @Override
    public void sendNewConnectionID(Integer connectionID) {
        iotDevice.setConnectionID(connectionID);
    }

    public Integer getConnectionID() {
        return UpdateConnectionIDUseCase.connectionID;
    }

    public void setConnectionID(Integer connectionID) {
        UpdateConnectionIDUseCase.connectionID = connectionID;
    }
}
