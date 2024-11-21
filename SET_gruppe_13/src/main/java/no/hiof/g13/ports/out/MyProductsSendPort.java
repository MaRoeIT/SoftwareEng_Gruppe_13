package no.hiof.g13.ports.out;

import no.hiof.g13.models.IOTDevice;

import java.util.ArrayList;

public interface MyProductsSendPort {
    //interface metode som skal brukes til å sende my produkt sin arrayliste over iot enheter
    void sendMyProductList(ArrayList<IOTDevice> myProducts);
}
