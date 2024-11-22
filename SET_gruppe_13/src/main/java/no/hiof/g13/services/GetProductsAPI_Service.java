package no.hiof.g13.services;

import io.javalin.Javalin;
import no.hiof.g13.API.GetProductsAPI;
import no.hiof.g13.adapters.GetProductsAPI_RepositoryMySQL;
import no.hiof.g13.ports.GetProductsAPI_Port;

public class GetProductsAPI_Service {
    private final GetProductsAPI getProductsAPI;

    public GetProductsAPI_Service() {
        GetProductsAPI_Port getProductsAPIPort = new GetProductsAPI_RepositoryMySQL();
        this.getProductsAPI = new GetProductsAPI(getProductsAPIPort);
    }

    public void start(Javalin app) {
        getProductsAPI.start(app);
    }
}