package no.hiof.g13.ports.in;

import no.hiof.g13.DTO.in.GetProductsAPI_DTO;

import java.util.List;

public interface GetProductsAPI_Port {

    //List<IOTSmartLightDummy> getMyProducts(int userId);
    // IOTDevice getProductById(int productId);
    List<GetProductsAPI_DTO> getAllProducts();

}