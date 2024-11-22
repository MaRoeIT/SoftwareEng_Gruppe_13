package no.hiof.g13;

import io.javalin.Javalin;
import no.hiof.g13.adapters.ApiAdapter;
import no.hiof.g13.adapters.UserAdapter;

import no.hiof.g13.ports.out.UserRepositoryPort;
import no.hiof.g13.services.GetProductsAPI_Service;

/**
 * Runs the frontend through javalin
 * TODO:Write more about this class
 */
public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(javalinConfig -> {
            javalinConfig.staticFiles.enableWebjars();
            javalinConfig.vue.vueInstanceNameInJs = "app";
        }).start();

        UserRepositoryPort userRepositoryPort = new UserAdapter();
        ApiAdapter apiAdapter = new ApiAdapter(userRepositoryPort);

        // Register all routes via ApiAdapter
        apiAdapter.registerRoutes(app);

        // Get products from database
        GetProductsAPI_Service productsAPIService = new GetProductsAPI_Service();
        productsAPIService.start(app);
    }
}
