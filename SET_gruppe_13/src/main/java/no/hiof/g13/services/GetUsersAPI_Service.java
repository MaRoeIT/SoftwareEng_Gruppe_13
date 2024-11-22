package no.hiof.g13.services;

import com.google.protobuf.ServiceException;
import io.javalin.Javalin;
import no.hiof.g13.API.GetUsersAPI;
import no.hiof.g13.DTO.out.GetUserResponseDTO;
import no.hiof.g13.Exceptions.UserNotFoundException;
import no.hiof.g13.adapters.GetUsersAPI_RepositoryMySQL;
import no.hiof.g13.models.User;
import no.hiof.g13.ports.GetUsersAPI_Port;

import java.util.List;
import java.util.stream.Collectors;

public class GetUsersAPI_Service {
    private final GetUsersAPI getUsersLoginAPI;
    private final GetUsersAPI_Port getUsersLoginAPI_port;

    public GetUsersAPI_Service() {
        this.getUsersLoginAPI_port = new GetUsersAPI_RepositoryMySQL();
        this.getUsersLoginAPI = new GetUsersAPI(getUsersLoginAPI_port);
    }

    public void configureRoute(Javalin app) {
        getUsersLoginAPI.getUsers(app);
    }

}
