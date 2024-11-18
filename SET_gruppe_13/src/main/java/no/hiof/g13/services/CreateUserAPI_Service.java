package no.hiof.g13.services;

import io.javalin.Javalin;
import no.hiof.g13.API.CreateUserAPI;
import no.hiof.g13.adapters.CreateUserAPI_RepositoryMySQL;
import no.hiof.g13.ports.CreateUserAPI_Port;

public class CreateUserAPI_Service {
    private final CreateUserAPI createUserAPI;

    public CreateUserAPI_Service() {
        CreateUserAPI_Port createUserAPIPort = new CreateUserAPI_RepositoryMySQL();
        this.createUserAPI = new CreateUserAPI(createUserAPIPort);
    }

    public void configureRoute(Javalin app) {
        createUserAPI.configureRoutes(app);
    }
}