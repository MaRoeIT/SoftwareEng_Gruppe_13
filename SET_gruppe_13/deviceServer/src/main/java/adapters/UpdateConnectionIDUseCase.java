package adapters;

import no.hiof.g13.ports.in.ConnectionIDPort;

public class UpdateConnectionIDUseCase implements ConnectionIDPort {
    private static Integer connectionID;

    @Override
    public Integer receiveConnectionID(){
        return getConnectionID();
    }

    public Integer getConnectionID() {
        return UpdateConnectionIDUseCase.connectionID;
    }

    public void setConnectionID(Integer connectionID) {
        UpdateConnectionIDUseCase.connectionID = connectionID;
    }
}
