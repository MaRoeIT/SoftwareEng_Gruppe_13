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
    private final Gson gson;
    private static final Logger logger = LoggerFactory.getLogger(CreateUserAPI.class);

    public CreateUserAPI(CreateUserAPI_Port createUserAPI_Port) {
        this.createUserAPI_Port = createUserAPI_Port;
        this.gson = new Gson();
    }

    public void configureRoutes(Javalin app) {
        app.post("/api/users/create", ctx -> {
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
                        user.getFornavn(), user.getEtternavn(), user.getStatus_id(), user.getMobil(), user.getEpost(), hashedPassword, user.getAddress().getAdresse(), user.getAddress().getPostnummer(), user.getUserLevel()
                );

                Map<String, String> response = new HashMap<>();
                response.put("success", "true");
                response.put("success message", "New user created");

                ctx.status(200).result(gson.toJson(response)).contentType("application/json");
            }
            catch (Exception e) {
                Map<String, String> response = new HashMap<>();
                response.put("success", "false");
                response.put("message", "Balle");
                ctx.status(500).result(gson.toJson(response)).contentType("application/json");
            }
        });
    }

    private boolean validateUserData(User user) {
        if(user == null) {
            logger.warn("user object is null");
            return false;
        }

        logger.info("validating user data");
        logger.info("Fornavn: {}", user.getFornavn());
        logger.info("Etternavn: {}", user.getEtternavn());
        logger.info("Status id: {}", user.getStatus_id());
        logger.info("Mobil: {}", user.getMobil());
        logger.info("Epost: {}", user.getEpost());
        logger.info("Adresse: {}", user.getAddress().getAdresse());
        logger.info("postnummer: {}", user.getAddress().getPostnummer());
        logger.info("postnummer: {}", user.getUserLevel());

        logger.info("Password length: {}", user.getPassord() != null ? user.getPassord().length() : 0);

        boolean isFirstNameValid = user.getFornavn() != null && !user.getFornavn().trim().isEmpty();
        boolean isLastNameValid = user.getEtternavn() != null && !user.getEtternavn().trim().isEmpty();
        boolean isStatusIdValid = user.getStatus_id() != 0;
        boolean isMobileValid = user.getMobil() != null && !user.getMobil().trim().isEmpty();
        boolean isEmailValid = user.getEpost() != null && !user.getEpost().trim().isEmpty();
        boolean isAdressValid = user.getAddress().getAdresse() != null && !user.getAddress().getAdresse().trim().isEmpty();
        boolean isPostNumberValid = user.getAddress().getPostnummer() != null && !user.getAddress().getPostnummer().trim().isEmpty();
        boolean isUserLevelValid = user.getUserLevel() != 0;


        boolean isPasswordValid = user.getPassord() != null;

        logger.info("Validation results:");
        logger.info("Fornavn valid; {}", isFirstNameValid);
        logger.info("Etternavn valid; {}", isLastNameValid);
        logger.info("Status id valid; {}", isStatusIdValid);
        logger.info("Mobil valid; {}", isMobileValid);
        logger.info("Epost valid; {}", isEmailValid);
        logger.info("Adresse valid: {}", isAdressValid);
        logger.info("Postnummer valid: {}", isPostNumberValid);
        logger.info("Brukerniva valid: {}", isUserLevelValid);
        logger.info("Passrd valid: {}", isPasswordValid);

        return isFirstNameValid && isLastNameValid && isMobileValid && isEmailValid && isAdressValid && isPostNumberValid && isPasswordValid && isUserLevelValid;
    }
}
