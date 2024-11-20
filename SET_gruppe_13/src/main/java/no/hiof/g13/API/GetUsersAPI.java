package no.hiof.g13.API;

import io.javalin.Javalin;
import no.hiof.g13.DTO.out.GetUserResponseDTO;
import no.hiof.g13.models.User;
import no.hiof.g13.ports.GetUsersAPI_Port;

import java.util.ArrayList;
import java.util.List;

public class GetUsersAPI {
    private final GetUsersAPI_Port getUserAPI_Port;

    public GetUsersAPI(GetUsersAPI_Port getUsersLoginPort) {
        this.getUserAPI_Port = getUsersLoginPort;
    }

    public void getUsers(Javalin app) {

        app.get("/api/users/", ctx -> {
            List<GetUserResponseDTO> dto = new ArrayList<>();
            for(User userX : getUserAPI_Port.getAllUsers()) {
                dto.add(GetUserResponseDTO.fromDomain(userX));
            }
            ctx.json(dto);
        });

        app.get("/api/users/id/{id}", ctx -> {
            int url_parameter = Integer.parseInt(ctx.pathParam("id"));
            User user = getUserAPI_Port.getUserById(url_parameter);
            GetUserResponseDTO dto = GetUserResponseDTO.fromDomain(user);
            ctx.json(dto);
        });

       app.get("/api/users/{id}", ctx -> {
           String url_parameter = ctx.pathParam("id");
           User user = getUserAPI_Port.getUserByEmail(url_parameter);
           GetUserResponseDTO dto = GetUserResponseDTO.fromDomain(user);
           ctx.json(dto);
       });

       app.exception(NumberFormatException.class, (e, ctx) -> {
           ctx.status(400).result("Error, invalid ID format");
       });

        app.exception(NullPointerException.class, (e, ctx) -> {
            String urlParameter = ctx.pathParam("id");
            ctx.status(404).result("Error, User with ID " + urlParameter + " not found");
        });
        app.exception(Exception.class, (e, ctx) -> {
            ctx.status(500).result("Error, internal server error");
        });
    }
}