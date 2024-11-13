package no.hiof.g13.API;

import com.google.gson.Gson;
import io.javalin.Javalin;
import no.hiof.g13.DTO.in.GetProductsAPI_DTO;
import no.hiof.g13.ports.in.GetProductsAPI_Port;

import java.util.List;

public class GetProductsAPI {
    private final GetProductsAPI_Port getProductsPort;
    private final Gson gson;

    public GetProductsAPI(GetProductsAPI_Port getProductsPort) {
        this.getProductsPort = getProductsPort;
        this.gson = new Gson();
    }

    public void start(Javalin app) {
        app.get("/api/devices", ctx -> {
            List<GetProductsAPI_DTO> allProductsDTO = getProductsPort.getAllProducts();
            ctx.result(gson.toJson(allProductsDTO)).contentType("application/json");
        });
    }
}