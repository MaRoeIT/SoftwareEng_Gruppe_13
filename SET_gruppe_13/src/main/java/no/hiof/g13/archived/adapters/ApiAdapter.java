package no.hiof.g13.archived.adapters;

import com.google.gson.Gson;
import io.javalin.Javalin;
import io.javalin.http.Context;
import no.hiof.g13.DTO.in.ProductDetailsDTO;
import no.hiof.g13.models.ProductImage;
import no.hiof.g13.models.User;
import no.hiof.g13.archived.ports.ProductDetailsRepositoryPort;
import no.hiof.g13.archived.ports.ProductImageRepositoryPort;
import no.hiof.g13.archived.ports.UserRepositoryPort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ApiAdapter {
    private final UserRepositoryPort userRepositoryPort;
    private final ProductImageRepositoryPort productImageRepositoryPort;
    private final ProductDetailsRepositoryPort productDetailsRepositoryPort;
    private final Gson gson;

    public ApiAdapter(UserRepositoryPort userRepositoryPort, ProductImageRepositoryPort productImageRepositoryPort, ProductDetailsRepositoryPort productDetailsRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.productImageRepositoryPort = productImageRepositoryPort;
        this.productDetailsRepositoryPort = productDetailsRepositoryPort;
        this.gson = new Gson();
    }

    public void registerRoutes(Javalin app) {
        app.get("/api/user/{id}", this::getUser);
        app.get("/logout/{id}", this::logout);
        app.post("/api/login", this::login);
        app.get("/api/product-images", this::getProductImages);
        app.get("/api/product-details", this::getProductDetails);
    }

    private void getUser(Context ctx) {
        String idParam = ctx.pathParam("id");
        try {
            int userId = Integer.parseInt(idParam);
            User user = userRepositoryPort.getUser(userId);
            if (user != null) {
                ctx.result(gson.toJson(user)).contentType("application/json");
            } else {
                ctx.status(404).result("User not found");
            }
        } catch (NumberFormatException e) {
            ctx.status(400).result("Invalid user ID format");
        }
    }

    private void logout(Context ctx) {
        String idParam = ctx.pathParam("id");
        int userId = Integer.parseInt(idParam);
        ctx.removeCookie("authToken");
        userRepositoryPort.deleteToken(userId);
        ctx.redirect("/pages/login.html");
    }

    private void login(Context ctx) {
        String email = ctx.bodyAsClass(User.class).getEpost();
        String password = ctx.bodyAsClass(User.class).getPassord();

        if (email == null || password == null) {
            ctx.status(400).json("Invalid input");
            return;
        }

        boolean isAuthenticated = userRepositoryPort.authenticateUser(email, password);
        int userId = userRepositoryPort.getUserIdByEmail(email);
        Map<String, Object> response = new HashMap<>();
        if (isAuthenticated) {
            response.put("isAuthenticated", true);
            response.put("userId", userId);
            String authToken = ctx.cookie("authToken") != null ? userRepositoryPort.getToken(userId) : UUID.randomUUID().toString();
            ctx.cookie("authToken", authToken, 86400);
            userRepositoryPort.saveToken(authToken, userId);
        } else {
            response.put("isAuthenticated", false);
        }
        ctx.json(response);
    }

    private void getProductImages(Context ctx) {
        // Correctly use the instance method call
        List<ProductImage> productImages = productImageRepositoryPort.getAllProductImages();
        if (productImages != null && !productImages.isEmpty()) {
            ctx.json(productImages);
        } else {
            ctx.status(404).result("No product images found");
        }
    }

    private void getProductDetails(Context ctx) {
        String productIdParam = ctx.queryParam("product_id");
        if (productIdParam != null) {
            try {
                int productId = Integer.parseInt(productIdParam);
                ProductDetailsDTO productDetails = productDetailsRepositoryPort.getProductDetails(productId);
                if (productDetails != null) {
                    ctx.json(productDetails);
                } else {
                    ctx.status(404).result("Product not found");
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result("Invalid product ID format");
            }
        } else {
            ctx.status(400).result("Product ID is required");
        }
    }

}
