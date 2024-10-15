package no.hiof.g13.ports.out;

import no.hiof.g13.models.IOTDevice;
import no.hiof.g13.models.MyProducts;

import java.util.ArrayList;
import java.util.Optional;

public interface GetMyProductsPort {
    Optional<ArrayList<IOTDevice>> getMyProductList(MyProducts myProducts);
}
