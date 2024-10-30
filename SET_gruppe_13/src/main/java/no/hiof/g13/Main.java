package no.hiof.g13;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.javalin.vue.VueComponent;
import no.hiof.g13.adapters.UserAdapter;
import no.hiof.g13.models.User;
import org.jetbrains.annotations.NotNull;
import org.mindrot.jbcrypt.BCrypt;

import java.util.UUID;



public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create(javalinConfig -> {
            javalinConfig.staticFiles.enableWebjars();
            javalinConfig.vue.vueInstanceNameInJs = "app";
        }).start();

        Gson gson = new Gson();
        UserAdapter userAdapter = new UserAdapter();

        app.get("/api/user", ctx -> {

            User user = userAdapter.getUser(9);

            ctx.result(gson.toJson(user)); // Serialize object to JSON
            ctx.contentType("application/json");
        });

        app.get("/api/user/{id}", ctx -> {
            // Extract the user ID from the path parameter
            String idParam = ctx.pathParam("id");
            System.out.println("id: " + idParam);

            try {
                // Parse the ID to an integer
                int userId = Integer.parseInt(idParam);

                // Fetch the user using the userAdapter with the provided ID
                User user = userAdapter.getUser(userId);

                if (user != null) {
                    // Return the user data as JSON if found
                    ctx.result(gson.toJson(user));
                    ctx.contentType("application/json");
                } else {
                    // Respond with 404 if user not found
                    ctx.status(404).result("User not found");
                }
            } catch (NumberFormatException e) {
                // Handle case where ID is not a valid integer
                ctx.status(400).result("Invalid user ID format");
            }
        });

        app.get("/", new VueComponent("frontpage"));
        app.get("/login", new VueComponent("login"));
        app.get("/user/{id}", new VueComponent("user"));


        app.post("/api/login", ctx -> {
            // Parse the JSON request body to get email and password
            String email = ctx.bodyAsClass(User.class).getEpost();
            String password = ctx.bodyAsClass(User.class).getPassord();

            if (email == null || password == null) {
                ctx.status(400).json("Invalid input");
                return;
            }

            // Use the adapter to authenticate
            boolean isAuthenticated = userAdapter.authenticateUser(email, password);
            if(isAuthenticated){
                int userId = userAdapter.getUserId(email);
                String authToken = UUID.randomUUID().toString();
                ctx.cookie("authToken", authToken, 86400); // Valid for 1 day
                userAdapter.saveToken(authToken, userId);
            }
            // Return the result as JSON
            ctx.json(isAuthenticated);



        });

        app.get("/other-page", new Handler() {
            @Override
            public void handle(@NotNull Context context) throws Exception {
                context.result("Hello from the other page!");
            }
        });


    }
}