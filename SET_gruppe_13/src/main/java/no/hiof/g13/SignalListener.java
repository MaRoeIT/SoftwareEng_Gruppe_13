package no.hiof.g13;

import no.hiof.g13.adapters.GetLightSettingsUseCase;
import no.hiof.g13.adapters.IsNewDeviceUseCase;
import no.hiof.g13.models.MyProducts;
import no.hiof.g13.models.product.IOTSmartLight;
import no.hiof.g13.services.MyProductsService;

import java.awt.*;
import java.util.HashMap;

/**
 * A class that listens for specific signals from the server, and performs different types of tasks based on the signal
 * given.
 */
public class SignalListener {
    //TODO:Make more signals with more functionality
    private static final int SIGNAL_NEW_DEVICE = 20;
    private static final int SIGNAL_GET_LIGHT_SETTINGS = 30;

    private int signal;

    public SignalListener() {
    }

    /**
     * Sends MyProduct list to different adapters based on incoming signal
     */
    public void sendMyProducts(){
        MyProducts myProducts = generateDummyData();

        if (this.signal == SIGNAL_NEW_DEVICE){
            new MyProductsService(new IsNewDeviceUseCase()).sendMyProducts(myProducts);
        }
        if (this.signal == SIGNAL_GET_LIGHT_SETTINGS){
            new MyProductsService(new GetLightSettingsUseCase()).sendMyProducts(myProducts);
        }
    }

    private MyProducts generateDummyData(){
        //Generating some dummy data to test against for the integrationtest with the server and client
        //TODO:Get an actuall MyProduct list generated from the database.
        HashMap<String, Integer> size = new HashMap<>();
        size.put("Height", 20);

        IOTSmartLight device1 = new IOTSmartLight("Carl", "1", "Calistan","Carl xyz",true,50, size, 100);
        IOTSmartLight device2 = new IOTSmartLight("Diana", "2", "Calistan","Carl xyz",true,50, size, 100);
        IOTSmartLight device3 = new IOTSmartLight("Marco", "3", "Calistan","Carl xyz",true,50, size, 100);
        IOTSmartLight device4 = new IOTSmartLight("Mabell", "5", "Calistan","Carl xyz",true,50, size, 100);

        device1.setLightPattern("Wave");
        device1.setColor(Color.DARK_GRAY);
        device1.setLightStrength(100);

        device2.setLightPattern("Wave");
        device2.setColor(Color.RED);
        device2.setLightStrength(15);

        device3.setLightPattern("Wave");
        device3.setColor(Color.cyan);
        device3.setLightStrength(20);

        device4.setLightPattern("Wave");
        device4.setColor(Color.MAGENTA);
        device4.setLightStrength(80);

        MyProducts myProducts = new MyProducts();
        myProducts.addProducts(device1,device2,device3,device4);

        return myProducts;
    }

    public void receiveSignal(int signal) {
        setSignal(signal);
        sendMyProducts();
    }

    public synchronized int getSignal() {
        return signal;
    }

    public synchronized void setSignal(int signal) {
        this.signal = signal;
    }
}
