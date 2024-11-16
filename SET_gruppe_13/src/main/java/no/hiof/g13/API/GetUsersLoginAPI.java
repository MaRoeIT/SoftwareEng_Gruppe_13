package no.hiof.g13.API;

import com.google.gson.Gson;
import io.javalin.Javalin;
import no.hiof.g13.DTO.out.UserLoginResponseDTO;
import no.hiof.g13.models.User;
import no.hiof.g13.ports.in.GetUsersLoginAPI_Port;

import java.util.List;

public class GetUsersLoginAPI {
    private final GetUsersLoginAPI_Port getUsersLoginPort;
    private final Gson gson;

    public GetUsersLoginAPI(GetUsersLoginAPI_Port getUsersLoginPort) {
        this.getUsersLoginPort = getUsersLoginPort;
        this.gson = new Gson();
    }

    public void getUsers(Javalin app) {

        app.get("/api/users/", ctx -> {
            try {
                List<User> allUsers = getUsersLoginPort.getAllUsers();
                List<UserLoginResponseDTO> dto = allUsers.stream().map(UserLoginResponseDTO::fromDomain).toList();
                ctx.result(gson.toJson(dto)).contentType("application/json");
            }
            catch(Exception e) {
                ctx.status(500).result("Error getting users");
            }

        });

        app.get("/api/users/id/{id}", ctx -> {
            try {
                int userId = Integer.parseInt(ctx.pathParam("id"));
                User user = getUsersLoginPort.getUserById(userId);

                if(user != null && user.getBruker_id() != 0) {
                    UserLoginResponseDTO dto = UserLoginResponseDTO.fromDomain(user);
                    ctx.result(gson.toJson(dto)).contentType("application/json");
                }
                else {
                    ctx.status(404).result("No user found");
                }

            } catch(NumberFormatException e) {
                ctx.status(404).result("Invalid product ID format");
            }
        });

       app.get("/api/users/{epost}", ctx -> {
           try {
               String epost = ctx.pathParam("epost");
               User user = getUsersLoginPort.getUserByEmail(epost);

               if(user != null) {
                   UserLoginResponseDTO dto = UserLoginResponseDTO.fromDomain(user);
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