package no.hiof.g13.API;

import com.google.gson.Gson;
import io.javalin.Javalin;
import no.hiof.g13.DTO.out.GetUserResponseDTO;
import no.hiof.g13.models.User;
import no.hiof.g13.ports.GetUsersAPI_Port;

import java.util.List;
import java.util.Map;

public class GetUsersAPI {
    private final GetUsersAPI_Port getUserAPI_Port;
    private final Gson gson;

    public GetUsersAPI(GetUsersAPI_Port getUsersLoginPort) {
        this.getUserAPI_Port = getUsersLoginPort;
        this.gson = new Gson();
    }

    public void getUsers(Javalin app) {

        app.get("/api/users/", ctx -> {
            try {
                List<User> allUsers = getUserAPI_Port.getAllUsers();
                List<GetUserResponseDTO> dto = allUsers.stream().map(GetUserResponseDTO::fromDomain).toList();
                ctx.result(gson.toJson(dto)).contentType("application/json");
            }
            catch(Exception e) {
                ctx.status(500).result("Error getting users");
            }
        });

        app.get("/api/users/id/{id}", ctx -> {
            try {
                int userId = Integer.parseInt(ctx.pathParam("id"));
                User user = getUserAPI_Port.getUserById(userId);

                if(user != null) {
                    GetUserResponseDTO dto = GetUserResponseDTO.fromDomain(user);
                    ctx.result(gson.toJson(dto)).contentType("application/json");
                }
                else {
                    ctx.status(404).result(gson.toJson(Map.of("error", "User not found with id " + userId))).contentType("application/json");
                }

            } catch(NumberFormatException e) {
                ctx.status(400).result(gson.toJson(Map.of("error", "Invalid ID format"))).contentType("application/json");
            }
        });

       app.get("/api/users/{epost}", ctx -> {
           try {
               String epost = ctx.pathParam("epost");
               User user = getUserAPI_Port.getUserByEmail(epost);

               if(user != null) {
                   GetUserResponseDTO dto = GetUserResponseDTO.fromDomain(user);
                   ctx.result(gson.toJson(user)).contentType("application/json");
               }
               else {
                   ctx.status(404).result("No user found");
               }
           }
           catch(Exception e) {
               ctx.status(500).result("Iternal server err9r");
           }
       });
    }
}