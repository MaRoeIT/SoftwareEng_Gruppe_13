package no.hiof.g13.adapters;

import com.google.gson.Gson;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.vue.VueComponent;
import no.hiof.g13.models.User;
import no.hiof.g13.ports.out.UserRepositoryPort;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ApiAdapter {
    private final UserRepositoryPort userRepositoryPort;
    private final Gson gson;

    public ApiAdapter(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.gson = new Gson();
    }

    public void registerRoutes(Javalin app) {
        app.get("/api/user/{id}", this::getUser);
        app.get("/logout/{id}", this::logout);
        app.post("/api/login", this::login);
        app.get("/", new VueComponent("frontpage"));
        app.get("/login", new VueComponent("login"));
        app.get("/user/{id}", new VueComponent("user"));
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
        ctx.redirect("/login");
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
}
