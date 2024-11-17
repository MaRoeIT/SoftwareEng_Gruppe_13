package no.hiof.g13.services;

import com.google.protobuf.ServiceException;
import io.javalin.Javalin;
import no.hiof.g13.API.GetUsersAPI;
import no.hiof.g13.DTO.out.GetUserResponseDTO;
import no.hiof.g13.Exceptions.UserNotFoundException;
import no.hiof.g13.adapters.GetUsersAPI_RepositoryMySQL;
import no.hiof.g13.models.User;
import no.hiof.g13.ports.in.GetUsersAPI_Port;

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

    public GetUserResponseDTO getUserById(int userId) throws ServiceException {
        try {
            User user = getUsersLoginAPI_port.getUserById(userId);
            if(user == null) {
                throw new UserNotFoundException("User not found");
            }
            return GetUserResponseDTO.fromDomain(user);
        }
        catch (Exception e) {
            throw new ServiceException("Failed to fetch user", e);
        }
    }

    public GetUserResponseDTO getUserByEmail(String email) throws ServiceException {
        try {
            User user = getUsersLoginAPI_port.getUserByEmail(email);
            if(user == null) {
                throw new UserNotFoundException("User not found");
            }
            return GetUserResponseDTO.fromDomain(user);
        }
        catch (Exception e) {
            throw new ServiceException("Failed to fetch user", e);
        }
    }

    public List<GetUserResponseDTO> getAllUsers() throws ServiceException {
        try {
            return getUsersLoginAPI_port.getAllUsers().stream().map(GetUserResponseDTO::fromDomain).collect(Collectors.toList());
        }
        catch (Exception e) {
            throw new ServiceException("Failed to fetch users", e);
        }
    }

    public void getUsers(Javalin app) {
        getUsersLoginAPI.getUsers(app);
    }
}
