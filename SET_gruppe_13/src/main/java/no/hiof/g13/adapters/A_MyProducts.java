package no.hiof.g13.adapters;

import no.hiof.g13.interfaces.GUIApiPort;
import no.hiof.g13.interfaces.GUISpiPort;
import no.hiof.g13.models.IOTDevice;

import java.util.ArrayList;

public class A_MyProducts implements GUIApiPort {
    private final GUISpiPort guiSpiPort;

    public ArrayList<IOTDevice> getMyProductList(){
        return guiSpiPort.findMyProducts();
    }
}
