package no.hiof.g13;

import no.hiof.g13.adapters.IsNewDeviceUseCase;
import no.hiof.g13.models.MyProducts;
import no.hiof.g13.models.product.IOTSmartLight;
import no.hiof.g13.ports.in.ServerSignalPort;
import no.hiof.g13.ports.out.MyProductsSendPort;
import no.hiof.g13.services.MyProductsService;

import java.util.HashMap;
import java.util.Map;

public class SignalListener implements Runnable {
    private int signal;

    public SignalListener() {
    }



    @Override
    public void run() {
        while (true){
            if (getSignal() == 20){
                System.out.println("Send the goddamn signal man");
                break;
            } else if (getSignal() != 0) {
                System.out.println("Something is atleast happening");
                break;
            }
        }
    }

    public void sendMyProducts(){
        if (this.signal == 20){
            HashMap<String, Integer> size = new HashMap<String, Integer>();
            size.put("Height", 20);

            IOTSmartLight device1 = new IOTSmartLight("Carl", "1", "Calistan","Carl xyz",true,50, size, 100);
            IOTSmartLight device2 = new IOTSmartLight("Diana", "2", "Calistan","Carl xyz",true,50, size, 100);
            IOTSmartLight device3 = new IOTSmartLight("Marco", "3", "Calistan","Carl xyz",true,50, size, 100);
            IOTSmartLight device4 = new IOTSmartLight("Mabell", "5", "Calistan","Carl xyz",true,50, size, 100);

            MyProducts myProducts = new MyProducts();
            myProducts.addProducts(device1,device2,device3,device4);
            new MyProductsService(new IsNewDeviceUseCase()).sendMyProducts(myProducts);
        }
    }

    public void reciveSignal(int signal) {
        setSignal(signal);
        System.out.println("The signal has ben set " + getSignal());
        sendMyProducts();
    }

    public synchronized int getSignal() {
        return signal;
    }

    public synchronized void setSignal(int signal) {
        this.signal = signal;
        System.out.println("Now it should be more set than ever");
    }
}
