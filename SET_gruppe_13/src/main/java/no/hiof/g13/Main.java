package no.hiof.g13;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import no.hiof.g13.API.GetProductsAPI;
import no.hiof.g13.adapters.*;

import no.hiof.g13.ports.in.GetProductsAPI_Port;
import no.hiof.g13.ports.out.ProductDetailsRepositoryPort;
import no.hiof.g13.ports.out.UserRepositoryPort;
import no.hiof.g13.ports.out.ProductImageRepositoryPort;

import no.hiof.g13.services.GetProductsAPI_Service;

import java.awt.*;
import java.net.URI;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(javalinConfig -> {
            //javalinConfig.staticFiles.enableWebjars();
            //javalinConfig.vue.vueInstanceNameInJs = "app";
            javalinConfig.bundledPlugins.enableCors(cors -> {
                cors.addRule(corsRule -> {
                    corsRule.reflectClientOrigin = true;
                    corsRule.allowCredentials = true;
                });
            });;
            javalinConfig.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/html"; // Point to the 'html' folder in 'resources'
                staticFileConfig.location = Location.CLASSPATH; // Ensure classpath-based serving
            });
        }).start();
        UserRepositoryPort userRepositoryPort = new UserAdapter();
        ProductImageRepositoryPort productImageRepositoryPort = new ProductImageAdapter();
        ProductDetailsRepositoryPort productDetailsRepositoryPort = new ProductDetailsAdapter();

        ApiAdapter apiAdapter = new ApiAdapter(userRepositoryPort, productImageRepositoryPort, productDetailsRepositoryPort);

        // Register all routes via ApiAdapter
        apiAdapter.registerRoutes(app);

        // Get products from database
        GetProductsAPI_Service productsAPIService = new GetProductsAPI_Service();
        productsAPIService.start(app);

        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI("http://localhost:8080"));
            } else {
                System.err.println("Desktop or browse action not supported.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}