package no.hiof.g13.adapters;

import no.hiof.g13.models.IOTDevice;
import no.hiof.g13.models.MyProducts;
import no.hiof.g13.ports.out.GetMyProductsPort;

import java.util.ArrayList;
import java.util.Optional;

public class MyProductsOutAdapter implements GetMyProductsPort {

    @Override
    public Optional<ArrayList<IOTDevice>> getMyProductList(MyProducts myProducts) {
        System.out.println("Getting MyProduct list wait just a moment...");
        return Optional.ofNullable(myProducts.getMyProducts());
    }
}
