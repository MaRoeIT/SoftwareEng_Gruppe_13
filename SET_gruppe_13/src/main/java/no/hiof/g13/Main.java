package no.hiof.g13;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import no.hiof.g13.services.AuthenticateUserAPI_Service;

import no.hiof.g13.services.CreateUserAPI_Service;
import no.hiof.g13.services.GetProductsAPI_Service;
import no.hiof.g13.services.GetUsersAPI_Service;

import java.awt.*;
import java.net.URI;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(javalinConfig -> {
            javalinConfig.bundledPlugins.enableCors(cors -> {
                cors.addRule(corsRule -> {
                    corsRule.reflectClientOrigin = true;
                    corsRule.exposeHeader("*");
                });
            });
            javalinConfig.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/html"; // Point to the 'html' folder in 'resources'
                staticFileConfig.location = Location.CLASSPATH; // Ensure classpath-based serving
            });
        }).start(8080);

        // Get products from database
        GetProductsAPI_Service productsAPIService = new GetProductsAPI_Service();
        productsAPIService.start(app);

        // get users info from database
        GetUsersAPI_Service usersLoginAPI_service = new GetUsersAPI_Service();
        usersLoginAPI_service.configureRoute(app);

        // user login authentication
        AuthenticateUserAPI_Service authenticateUserAPIService = new AuthenticateUserAPI_Service();
        authenticateUserAPIService.configureRoute(app);

        // Create and insert new user to database
        CreateUserAPI_Service createUserAPIService = new CreateUserAPI_Service();
        createUserAPIService.configureRoute(app);

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