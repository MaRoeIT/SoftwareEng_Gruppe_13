package no.hiof.g13.ports.out;

import no.hiof.g13.DTO.in.ProductDetailsDTO;

public interface ProductDetailsRepositoryPort {
    ProductDetailsDTO getProductDetails(int produktId);
}
