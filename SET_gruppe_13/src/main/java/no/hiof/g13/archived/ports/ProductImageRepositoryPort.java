package no.hiof.g13.archived.ports;

import no.hiof.g13.models.ProductImage;
import java.util.List;

public interface ProductImageRepositoryPort {
    List<ProductImage> getAllProductImages();
}
