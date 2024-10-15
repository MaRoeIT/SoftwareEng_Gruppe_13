package no.hiof.g13.adapters;

import no.hiof.g13.GUI.MyProductsSwing;
import no.hiof.g13.models.IOTDevice;
import no.hiof.g13.models.MyProducts;
import no.hiof.g13.ports.out.GetMyProductsPort;

import java.util.ArrayList;
import java.util.Optional;

public class GUIOutAdapter implements GetMyProductsPort {
    public final MyProductsSwing myProductsSwing = new MyProductsSwing();


    @Override
    public Optional<ArrayList<IOTDevice>> getMyProductList(MyProducts myProducts) {
        myProductsSwing.runMyProducts(myProducts.getMyProducts());
        return Optional.empty();
    }
}
