package no.hiof.g13;

import no.hiof.g13.models.IOTDevice;
import no.hiof.g13.models.MyProducts;
import no.hiof.g13.models.product.IOTSmartLight;
import no.hiof.g13.ports.in.ServerSignalPort;

import java.util.ArrayList;
import java.util.HashMap;

public class CoreServerRun {
    public static void main(String[] args) {
        MyProducts myProducts = new MyProducts();

        HashMap<String, Integer> size = new HashMap<String, Integer>();
        size.put("Height", 20);

        IOTSmartLight device1 = new IOTSmartLight("Carl", "1", "Calistan","Carl xyz",true,50, size, 100);
        IOTSmartLight device2 = new IOTSmartLight("Diana", "2", "Calistan","Carl xyz",true,50, size, 100);
        IOTSmartLight device3 = new IOTSmartLight("Marco", "3", "Calistan","Carl xyz",true,50, size, 100);
        IOTSmartLight device4 = new IOTSmartLight("Mabell", "5", "Calistan","Carl xyz",true,50, size, 100);
        IOTSmartLight device5 = new IOTSmartLight("Systema", "10", "Calistan","Carl xyz",true,50, size, 100);
        IOTSmartLight device6 = new IOTSmartLight("ONLOCKER", "22", "Calistan","Carl xyz",true,50, size, 100);

        myProducts.addProducts(device1,device2,device3,device4,device5,device6);

//        SignalListener signalListener = new SignalListener();
//        Thread thread = new Thread(signalListener);
//        thread.start();
//
//        try{
//            thread.join();
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }

    }
}
