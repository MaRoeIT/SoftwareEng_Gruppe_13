package no.hiof.g13.API;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import io.javalin.Javalin;
import no.hiof.g13.models.User;
import no.hiof.g13.ports.AuthenticateUserAPI_Port;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthenticateUserAPI {
    private final AuthenticateUserAPI_Port authenticateUserAPIPort;

    public AuthenticateUserAPI(AuthenticateUserAPI_Port authenticateUserAPIPort) {
        this.authenticateUserAPIPort = authenticateUserAPIPort;
    }

    public void configureRoutes(Javalin app) {
        app.post("/api/authenticate/login", ctx -> {
            User user = ctx.bodyAsClass(User.class);
            HashMap<String, Object> response = new HashMap<>();

            if(user.getEpost() == null || user.getPassord() == null) {
                ctx.status(400).result("Error, wrong email or password credentials");
                return;
            }

            boolean isAuthenticated = authenticateUserAPIPort.authenticateUser(user.getEpost(), user.getPassord());

            if(isAuthenticated) {
                int userId = authenticateUserAPIPort.getUserIdByEmail(user.getEpost());
                String cookieToken = UUID.randomUUID().toString();
                ctx.cookie("authToken", cookieToken, 86400);
                authenticateUserAPIPort.saveToken(cookieToken, userId);

                response.put("isAuthenticated", true);
                response.put("userId", userId);

            }
            else response.put("isAuthenticated", false);

            ctx.json(response);
        });

        app.post("/api/authenticate/logout", ctx -> {
           HashMap<String, Object> response = new HashMap<>();

           if(ctx.cookie("authToken") != null) {
               ctx.removeCookie("authToken");
               response.put("response", true);
               response.put("message", "Logging out success");
           }
           else {
               response.put("response", false);
               response.put("message", "Logging out failed");
           }
           ctx.json(response);
        });

        app.exception(JsonSyntaxException.class, (e, ctx) -> {
            ctx.status(400).result("error, wrong JSON format");
        });
        app.exception(Exception.class, (e, ctx) -> {
            ctx.status(500).result("Internal server erro");
        });
    }
}