package no.hiof.g13.API;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import io.javalin.Javalin;
import no.hiof.g13.models.User;
import no.hiof.g13.ports.out.AuthenticateUserAPI_Port;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthenticateUserAPI {
    private final AuthenticateUserAPI_Port authenticateUserAPIPort;
    private final Gson gson;

    public AuthenticateUserAPI(AuthenticateUserAPI_Port authenticateUserAPIPort) {
        this.authenticateUserAPIPort = authenticateUserAPIPort;
        this.gson = new Gson();
    }

    public void configureRoutes(Javalin app) {
        app.post("/api/authenticate/login", ctx -> {
            try {
                String jsonBody = ctx.body();
                User user = gson.fromJson(jsonBody, User.class);
                String epost = user.getEpost();
                String passord = user.getPassord();

                if(epost == null || passord == null) {
                    Map<String, String> errorMessage = new HashMap<>();
                    errorMessage.put("error", "wrong credentials");
                    ctx.status(400).result(gson.toJson(errorMessage)).contentType("application/json");
                    return;
                }

                boolean isAuthenticated = authenticateUserAPIPort.authenticateUser(epost, passord);
                Map<String, Object> response = new HashMap<>();

                if(isAuthenticated) {
                    int userId = authenticateUserAPIPort.getUserIdByEmail(epost);

                    String authToken = UUID.randomUUID().toString();
                    authenticateUserAPIPort.saveToken(authToken, userId);
                    ctx.cookie("authToken", authToken, 86400);

                    response.put("isAuthenticated", true);
                    response.put("userId", userId);
                    response.put("token", authToken);
                }
                else {
                    response.put("isAuthenticated", false);
                    response.put("error", "wrong credentials");
                }

                ctx.result(gson.toJson(response)).contentType("application/json");
            }
            catch (JsonSyntaxException e) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Invalid JSON format");
                ctx.status(400)
                        .result(gson.toJson(errorResponse))
                        .contentType("application/json");
            }
            catch (Exception e) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Internal server error");
                ctx.status(500)
                        .result(gson.toJson(errorResponse))
                        .contentType("application/json");
            }
        });
    }
}