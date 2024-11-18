package no.hiof.g13.ports.in;

import no.hiof.g13.DTO.in.GetProductsAPI_DTO;
import no.hiof.g13.DTO.in.ProductDetailsDTO;
import no.hiof.g13.models.ProductImage;

import java.util.List;

public interface GetProductsAPI_Port {

    //List<IOTSmartLightDummy> getMyProducts(int userId);
    GetProductsAPI_DTO getProductById(int productId);
    List<GetProductsAPI_DTO> getAllProducts();
    ProductDetailsDTO getProductDetails(int produktId);
    List<ProductImage> getAllProductImages();
}