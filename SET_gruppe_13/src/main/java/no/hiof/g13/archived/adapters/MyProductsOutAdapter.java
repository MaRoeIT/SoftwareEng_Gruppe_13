package no.hiof.g13.archived.adapters;

import no.hiof.g13.models.IOTDevice;
import no.hiof.g13.models.MyProducts;
import no.hiof.g13.archived.ports.GetMyProductsPort;

import java.util.ArrayList;
import java.util.Optional;

public class MyProductsOutAdapter implements GetMyProductsPort {

    //returnerer en liste over mine produkter
    @Override
    public Optional<ArrayList<IOTDevice>> getMyProductList(MyProducts myProducts) {
        System.out.println("Getting MyProduct list wait just a moment...");
        return Optional.ofNullable(myProducts.getMyProducts());
    }
}
