package no.hiof.g13.archived.adapters;

import no.hiof.g13.archived.GUI.MyProductsSwing;
import no.hiof.g13.models.IOTDevice;
import no.hiof.g13.models.MyProducts;
import no.hiof.g13.archived.ports.GetMyProductsPort;

import java.util.ArrayList;
import java.util.Optional;

public class GUIOutAdapter implements GetMyProductsPort {
    //Bruker denne variabelen for å sende data direkte fra port til gui
    public final MyProductsSwing myProductsSwing = new MyProductsSwing();


    //metode som sender med produktlisten til gui
    @Override
    public Optional<ArrayList<IOTDevice>> getMyProductList(MyProducts myProducts) {
        myProductsSwing.runMyProducts(myProducts.getMyProducts());
        return Optional.empty();
    }
}
