package no.hiof.g13.ports.out;

import no.hiof.g13.models.ProductImage;
import java.util.List;

public interface ProductImageRepositoryPort {
    List<ProductImage> getAllProductImages();
}
