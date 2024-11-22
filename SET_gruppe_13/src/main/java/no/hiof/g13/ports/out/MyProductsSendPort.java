package no.hiof.g13.ports.out;

import no.hiof.g13.models.IOTDevice;

import java.util.ArrayList;

public interface MyProductsSendPort {
    //method ment for sending myProducts out of the core
    void sendMyProductList(ArrayList<IOTDevice> myProducts);
}
