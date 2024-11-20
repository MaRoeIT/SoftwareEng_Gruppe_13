package no.hiof.g13.API;

import io.javalin.Javalin;
import no.hiof.g13.DTO.in.GetProductsAPI_DTO;
import no.hiof.g13.DTO.in.ProductDetailsDTO;
import no.hiof.g13.models.ProductImage;
import no.hiof.g13.ports.GetProductsAPI_Port;
import java.util.List;

public class GetProductsAPI {
    private final GetProductsAPI_Port getProductsPort;

    public GetProductsAPI(GetProductsAPI_Port getProductsPort) {
        this.getProductsPort = getProductsPort;
    }

    public void start(Javalin app) {
        app.get("/api/products", ctx -> {
            List<GetProductsAPI_DTO> dto = getProductsPort.getAllProducts();
            ctx.json(dto);
        });

        app.get("/api/products/images", ctx -> {
            List<ProductImage> productImages = getProductsPort.getAllProductImages();
            ctx.json(productImages);
        });

        app.get("/api/products/id/{id}", ctx -> {
            int urlParameter = Integer.parseInt(ctx.pathParam("id"));
            GetProductsAPI_DTO dto = getProductsPort.getProductById(urlParameter);
            ctx.json(dto);
        });

        app.get("/api/products/details/{id}", ctx -> {
            int urlParameter = Integer.parseInt(ctx.pathParam("id"));
            ProductDetailsDTO dto = getProductsPort.getProductDetails(urlParameter);
            ctx.json(dto);
        });

        app.get("/api/products/user_id/{id}", ctx -> {
           int urlParameter = Integer.parseInt(ctx.pathParam("id"));
           List<ProductDetailsDTO> dtoList = getProductsPort.getProductsByUserId(urlParameter);

           if(dtoList != null) ctx.status(200).json(dtoList);
           else ctx.status(404).result("No products available");
        });

        app.exception(NumberFormatException.class, (e, ctx) -> {
            ctx.status(400).result("Error, invalid product ID format");
        });
        app.exception(NullPointerException.class, (e, ctx) -> {
            String urlParameter = ctx.pathParam("id");
            ctx.status(404).result("Error, User with ID " + urlParameter + " not found");
        });
    }
}