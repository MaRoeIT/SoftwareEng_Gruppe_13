package no.hiof.g13.archived.ports;

import no.hiof.g13.DTO.in.ProductDetailsDTO;

public interface ProductDetailsRepositoryPort {
    ProductDetailsDTO getProductDetails(int produktId);
}
