package no.hiof.g13.services;

import no.hiof.g13.models.MyProducts;
import no.hiof.g13.ports.out.MyProductsSendPort;

public class MyProductsService {
    private final MyProductsSendPort myProductsSendPort;

    public MyProductsService(MyProductsSendPort myProductsSendPort) {
        this.myProductsSendPort = myProductsSendPort;
    }

    public void sendMyProducts(MyProducts myProducts){
        myProductsSendPort.sendMyProductList(myProducts.getMyProducts());
    }
}
