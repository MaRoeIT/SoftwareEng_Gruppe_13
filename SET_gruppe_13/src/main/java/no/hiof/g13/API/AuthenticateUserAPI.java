package no.hiof.g13.API;

import com.google.gson.Gson;
import io.javalin.Javalin;
import no.hiof.g13.DTO.in.AuthenticateUserRequest_DTO;
import no.hiof.g13.DTO.out.AuthenticateUserResponseDTO;
import no.hiof.g13.models.User;
import no.hiof.g13.ports.out.AuthenticateUserAPI_Port;

public class AuthenticateUserAPI {
    private final AuthenticateUserAPI_Port authenticateUserAPIPort;
    private final Gson gson;

    public AuthenticateUserAPI(AuthenticateUserAPI_Port authenticateUserAPIPort) {
        this.authenticateUserAPIPort = authenticateUserAPIPort;
        this.gson = new Gson();
    }

    public void configureRoutes(Javalin app) {
        app.post("api/authenticate/login", ctx -> {
            try {
                AuthenticateUserRequest_DTO dto = gson.fromJson(ctx.body(), AuthenticateUserRequest_DTO.class);

                if(dto == null) {
                    ctx.status(400).result("Email and password fields are required");
                    return;
                }
                User authenticatedUser = authenticateUserAPIPort.authenticateUser(
                        dto.getEpost(), dto.getPassord()
                );

                AuthenticateUserResponseDTO response;
                if(authenticatedUser != null) {
                    response = AuthenticateUserResponseDTO.successLogin(authenticatedUser.getBruker_id());
                    ctx.status(200);
                }
                else {
                    response = AuthenticateUserResponseDTO.failedLogin("error while logging in");
                    ctx.status(401);
                }

                ctx.result((gson.toJson(response))).contentType("application/json");
            }
            catch (Exception e) {
                ctx.status(500).result("Internal server error");
            }
        });
    }

}