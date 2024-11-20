package no.hiof.g13.API;

import com.google.gson.Gson;
import io.javalin.Javalin;
import no.hiof.g13.models.User;
import no.hiof.g13.ports.CreateUserAPI_Port;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class CreateUserAPI {
    private final CreateUserAPI_Port createUserAPI_Port;

    public CreateUserAPI(CreateUserAPI_Port createUserAPI_Port) {
        this.createUserAPI_Port = createUserAPI_Port;
    }

    public void configureRoutes(Javalin app) {
        app.post("/api/users/create", ctx -> {
            User user = ctx.bodyAsClass(User.class);
            HashMap<String, Object> response = new HashMap<>();

            if(!fieldValidation(user)) {
                response.put("response", false);
                response.put("message", "All fields must be filled out");
                ctx.status(400).json(response);
                return;
            }

            if(createUserAPI_Port.userExists(user.getEpost())) {
                response.put("success", false);
                response.put("message", "User already exists");
                ctx.status(400).json(response);
                return;
            }

            String hashedPassword = BCrypt.hashpw(user.getPassord(), BCrypt.gensalt());
            createUserAPI_Port.createUser(
                    user.getFornavn(), user.getEtternavn(), user.getStatus_id(), user.getMobil(), user.getEpost(), hashedPassword, user.getAddress().getAdresse(), user.getAddress().getPostnummer(), user.getUserLevel()
            );

            response.put("success", true);
            response.put("message", "A new user is born");
            ctx.status(200).json(response);
        });

        app.exception(Exception.class, (e, ctx) -> {
            ctx.status(500).result("Internal server error");
        });
    }

    private boolean fieldValidation(User user) {
        if(user == null) return false;

        return (user.getFornavn() != null && !user.getFornavn().trim().isEmpty()) &&
                (user.getEtternavn() != null && !user.getEtternavn().trim().isEmpty() &&
                        (user.getStatus_id() != null) && (user.getMobil() != null && !user.getMobil().trim().isEmpty()) &&
                        (user.getEpost() != null && !user.getEpost().trim().isEmpty()) &&
                        (user.getAddress().getAdresse() != null && !user.getAddress().getAdresse().trim().isEmpty()) &&
                        (user.getAddress().getPostnummer() != null && !user.getAddress().getPostnummer().trim().isEmpty()) &&
                        (user.getUserLevel() != null) && (user.getPassord() != null));
    }
}
