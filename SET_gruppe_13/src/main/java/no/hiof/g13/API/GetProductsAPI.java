package no.hiof.g13.API;

import com.google.gson.Gson;
import io.javalin.Javalin;
import no.hiof.g13.DTO.in.GetProductsAPI_DTO;
import no.hiof.g13.DTO.in.ProductDetailsDTO;
import no.hiof.g13.models.ProductImage;
import no.hiof.g13.ports.GetProductsAPI_Port;

import java.util.List;

public class GetProductsAPI {
    private final GetProductsAPI_Port getProductsPort;
    private final Gson gson;

    public GetProductsAPI(GetProductsAPI_Port getProductsPort) {
        this.getProductsPort = getProductsPort;
        this.gson = new Gson();
    }

    public void start(Javalin app) {
        app.get("/api/products", ctx -> {
            List<GetProductsAPI_DTO> allProductsDTO = getProductsPort.getAllProducts();
            ctx.result(gson.toJson(allProductsDTO)).contentType("application/json");
        });

        app.get("/api/products/images", ctx -> {
            List<ProductImage> productImages = getProductsPort.getAllProductImages();
            if(productImages != null && !productImages.isEmpty()) {
                ctx.json(productImages);
            }
            else {
                ctx.status(404).result("No product images found");
            }
        });

        app.get("/api/products/id/{id}", ctx -> {
            String idParam = ctx.pathParam("id");
            try {
                int productId = Integer.parseInt(idParam);
                GetProductsAPI_DTO product = getProductsPort.getProductById(productId);

                if(product != null) {
                    ctx.result(gson.toJson(product)).contentType("application/json");
                }
                else {
                    ctx.status(404).result("Product not found");
                }
            }
            catch(NumberFormatException e) {
                ctx.status(400).result("Invalid product ID format");
            }
        });

        app.get("/api/products/details/{product_id}", ctx -> {
            try {
                int productIdParam = Integer.parseInt(ctx.pathParam("product_id"));
                ProductDetailsDTO dto = getProductsPort.getProductDetails(productIdParam);

                if(dto != null) {
                    ctx.json(dto);
                }
                else {
                    ctx.status(404).result("Product not found");
                }
            }
            catch (NumberFormatException e) {
                ctx.status(400).result("Invalid product ID format");
            }
        });
    }
}