package no.hiof.g13.services;

import io.javalin.Javalin;
import no.hiof.g13.API.AuthenticateUserAPI;
import no.hiof.g13.adapters.AuthenticateUserAPI_RepositoryMySQL;
import no.hiof.g13.ports.AuthenticateUserAPI_Port;

public class AuthenticateUserAPI_Service {
    private final AuthenticateUserAPI authenticateUserAPI;

    public AuthenticateUserAPI_Service() {
        AuthenticateUserAPI_Port authenticateUserAPIPort = new AuthenticateUserAPI_RepositoryMySQL();
        this.authenticateUserAPI = new AuthenticateUserAPI(authenticateUserAPIPort);
    }

    public void configureRoute(Javalin app) {
        authenticateUserAPI.configureRoutes(app);
    }

}