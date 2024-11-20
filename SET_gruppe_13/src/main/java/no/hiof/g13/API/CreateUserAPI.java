package no.hiof.g13.API;

import io.javalin.Javalin;
import no.hiof.g13.DTO.in.CreateUserRequestDTO;
import no.hiof.g13.models.User;
import no.hiof.g13.ports.CreateUserAPI_Port;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;

public class CreateUserAPI {
    private final CreateUserAPI_Port createUserAPI_Port;

    public CreateUserAPI(CreateUserAPI_Port createUserAPI_Port) {
        this.createUserAPI_Port = createUserAPI_Port;
    }

    public void configureRoutes(Javalin app) {
        app.post("/api/users/create", ctx -> {
            CreateUserRequestDTO userDto = ctx.bodyAsClass(CreateUserRequestDTO.class);
            HashMap<String, Object> response = new HashMap<>();

            if(!fieldValidation(userDto)) {
                response.put("error", false);
                response.put("message", "All fields must be filled out");
                ctx.status(400).json(response);
                return;
            }

            if(createUserAPI_Port.userExists(userDto.getEpost())) {
                response.put("error", false);
                response.put("message", "User already exists");
                ctx.status(400).json(response);
                return;
            }

            String hashedPassword = BCrypt.hashpw(userDto.getPassord(), BCrypt.gensalt());
            createUserAPI_Port.createUser(
                    new User(userDto.getFornavn(), userDto.getEtternavn(), userDto.getStatus_id(), userDto.getMobil(), userDto.getEpost(), hashedPassword, userDto.getAddress(), userDto.getUserLevel()
            ));

            response.put("success", true);
            response.put("message", "A new user is born");
            ctx.status(200).json(response);
        });

        app.exception(Exception.class, (e, ctx) -> {
            HashMap<String, Object> errorJson = new HashMap<>();
            errorJson.put("error", "Internal server error");
            errorJson.put("message", e.getMessage());
            ctx.status(500).json(errorJson);
        });
    }

    private boolean fieldValidation(CreateUserRequestDTO user) {
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
