package no.hiof.g13.API;

import com.google.gson.Gson;
import io.javalin.Javalin;
import no.hiof.g13.models.User;
import no.hiof.g13.ports.CreateUserAPI_Port;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;

public class CreateUserAPI {
    private final CreateUserAPI_Port createUserAPI_Port;
    private final Gson gson;

    public CreateUserAPI(CreateUserAPI_Port createUserAPI_Port) {
        this.createUserAPI_Port = createUserAPI_Port;
        this.gson = new Gson();
    }

    public void configureRoutes(Javalin app) {
        app.post("/api/users/", ctx -> {
            try {
                User user = gson.fromJson(ctx.body(), User.class);

                if(!validateUserData(user)) {
                    Map<String, String> response = new HashMap<>();
                    response.put("success", "false");
                    response.put("error message", "Wrong user data");
                    ctx.status(400).result(gson.toJson(response)).contentType("application/json");
                    return;
                }

                if(createUserAPI_Port.userExists(user.getEpost())) {
                    Map<String, String> response = new HashMap<>();
                    response.put("success", "false");
                    response.put("error message", "User with this email already exists");
                    ctx.status(400).result(gson.toJson(response)).contentType("application/json");
                    return;
                }

                String hashedPassword = BCrypt.hashpw(user.getPassord(), BCrypt.gensalt());

                int userId = createUserAPI_Port.createUser(
                        user.getFornavn(), user.getEtternavn(), user.getStatus_id(), user.getMobil(), user.getEpost(), hashedPassword, user.getAddress().getAdresse(), user.getAddress().getPostnummer()
                );

                Map<String, String> response = new HashMap<>();
                response.put("success", "true");
                response.put("success message", "New user created");

                ctx.status(200).result(gson.toJson(response)).contentType("application/json");
            }
            catch (Exception e) {
                Map<String, String> response = new HashMap<>();
                response.put("success", "false");
                response.put("message", "Internal server error");
                ctx.status(500).result(gson.toJson(response)).contentType("application/json");
            }
        });
    }

    private boolean validateUserData(User user) {
        return user != null && user.getFornavn() != null && user.getEtternavn() != null
                && user.getMobil() != null && user.getEpost() != null
                && user.getAddress().getAdresse() != null && user.getAddress().getPostnummer() != null;
    }
}
