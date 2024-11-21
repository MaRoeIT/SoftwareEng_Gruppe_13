package no.hiof.g13.adapters;

import no.hiof.g13.GUI.MyProductsSwing;
import no.hiof.g13.models.IOTDevice;
import no.hiof.g13.models.MyProducts;
import no.hiof.g13.ports.out.MyProductsSendPort;

import java.util.ArrayList;

public class GUIOutAdapter implements MyProductsSendPort {
    //Bruker denne variabelen for å sende data direkte fra port til gui
    public final MyProductsSwing myProductsSwing = new MyProductsSwing();

    /*
    //metode som sender med produktlisten til gui
    @Override
    public ArrayList<IOTDevice> getMyProductList(MyProducts myProducts) {
        myProductsSwing.runMyProducts(myProducts.getMyProducts());
        return myProducts.getMyProducts();
    }*/

    @Override
    public void sendMyProductList(ArrayList<IOTDevice> myProducts) {

    }
}
