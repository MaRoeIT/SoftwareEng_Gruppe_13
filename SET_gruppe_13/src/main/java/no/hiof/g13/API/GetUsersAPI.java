package no.hiof.g13.API;

import io.javalin.Javalin;
import no.hiof.g13.DTO.out.GetUserResponseDTO;
import no.hiof.g13.models.User;
import no.hiof.g13.ports.GetUsersAPI_Port;
import java.util.List;

public class GetUsersAPI {
    private final GetUsersAPI_Port getUserAPI_Port;

    public GetUsersAPI(GetUsersAPI_Port getUsersLoginPort) {
        this.getUserAPI_Port = getUsersLoginPort;
    }

    public void getUsers(Javalin app) {

        app.get("/api/users/", ctx -> {
            List<User> allUsers = getUserAPI_Port.getAllUsers();
            List<GetUserResponseDTO> dto = allUsers.stream().map(GetUserResponseDTO::fromDomain).toList();
            ctx.json(dto);
        });

        app.get("/api/users/id/{id}", ctx -> {
            int userId = Integer.parseInt(ctx.pathParam("id"));
            User user = getUserAPI_Port.getUserById(userId);
            GetUserResponseDTO dto = GetUserResponseDTO.fromDomain(user);
            ctx.json(dto);
        });

       app.get("/api/users/{id}", ctx -> {
           String email = ctx.pathParam("id");
           User user = getUserAPI_Port.getUserByEmail(email);
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