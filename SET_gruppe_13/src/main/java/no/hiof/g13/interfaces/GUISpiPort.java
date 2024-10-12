package no.hiof.g13.interfaces;

import no.hiof.g13.models.IOTDevice;
import no.hiof.g13.models.MyProducts;

import java.util.ArrayList;
import java.util.Optional;

public interface GUISpiPort {
    Optional<MyProducts> findMyProducts();
}
