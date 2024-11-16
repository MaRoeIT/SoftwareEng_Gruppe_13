package no.hiof.g13.services;

import io.javalin.Javalin;
import no.hiof.g13.API.AuthenticateUserAPI;
import no.hiof.g13.DTO.out.AuthenticateUserResponseDTO;
import no.hiof.g13.Exceptions.AuthenticationException;
import no.hiof.g13.adapters.AuthenticateUserAPI_RepositoryMySQL;
import no.hiof.g13.models.User;
import no.hiof.g13.ports.out.AuthenticateUserAPI_Port;

public class AuthenticateUserAPI_Service {
    private final AuthenticateUserAPI authenticateUserAPI;
    private final AuthenticateUserAPI_Port authenticateUserAPIPort;

    public AuthenticateUserAPI_Service() {
        this.authenticateUserAPIPort = new AuthenticateUserAPI_RepositoryMySQL();
        this.authenticateUserAPI = new AuthenticateUserAPI(authenticateUserAPIPort);
    }

    public void configureRoute(Javalin app) {
        authenticateUserAPI.configureRoutes(app);
    }

    public AuthenticateUserResponseDTO authenticateUserDTO(String epost, String passord) {
        try {
            if (epost == null) {
                return AuthenticateUserResponseDTO.failedLogin("Email and password are required");
            }

            User authUser = authenticateUserAPIPort.authenticateUser(epost, passord);

            if (authUser != null) {
                return AuthenticateUserResponseDTO.successLogin(authUser.getBruker_id());
            } else {
                return AuthenticateUserResponseDTO.failedLogin("Failed to login");
            }
        } catch (Exception e) {
            throw new AuthenticationException("Authentication failed");
        }
    }
}