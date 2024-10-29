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

import java.sql.SQLOutput;

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

        app.get("/", new VueComponent("hello-world"));
        app.get("/login", new VueComponent("login"));


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