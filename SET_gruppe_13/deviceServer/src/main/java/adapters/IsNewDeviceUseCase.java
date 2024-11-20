package adapters;

import Server.DeviceServer;
import interfaces.GenericDevice;
import no.hiof.g13.models.IOTDevice;
import no.hiof.g13.models.MyProducts;
import no.hiof.g13.ports.out.GetMyProductsPort;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class IsNewDeviceUseCase implements GetMyProductsPort {
    private MyProducts myProducts;
    private final DeviceServer server;
    private IOTDevice iotDevice;

    public IsNewDeviceUseCase(DeviceServer server){
        this.server = server;
    }
    @Override
    public ArrayList<IOTDevice> getMyProductList(MyProducts myProducts) {
        this.myProducts = myProducts;
        return myProducts.getMyProducts();
    }

    public boolean isNewDevice(String deviceID){
        for (IOTDevice device:getMyProductList(getMyProducts())){
            if (device.getDeviceID().equals(deviceID)){
                System.out.println("This device is allredy in the users product list");
                iotDevice = device;
                return true;
            }
            else {
                System.out.println("new Device, generating new device");
                iotDevice = device;
                return false;
            }
        }
        System.out.println("Oh no");
        return false;
    }

    public MyProducts getMyProducts() {
        return myProducts;
    }

    public DeviceServer getServer() {
        return server;
    }

    public IOTDevice getIotDevice() {
        return iotDevice;
    }
}
