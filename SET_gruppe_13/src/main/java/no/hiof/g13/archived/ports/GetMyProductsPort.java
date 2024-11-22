package no.hiof.g13.archived.ports;

import no.hiof.g13.models.IOTDevice;
import no.hiof.g13.models.MyProducts;

import java.util.ArrayList;
import java.util.Optional;

public interface GetMyProductsPort {
    //interface metode som skal brukes til å sende my produkt sin arrayliste over iot enheter
    Optional<ArrayList<IOTDevice>> getMyProductList(MyProducts myProducts);
}
