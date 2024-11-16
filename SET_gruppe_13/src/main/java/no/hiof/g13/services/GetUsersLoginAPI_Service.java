package no.hiof.g13.services;

import com.google.protobuf.ServiceException;
import io.javalin.Javalin;
import no.hiof.g13.API.GetUsersLoginAPI;
import no.hiof.g13.DTO.out.UserLoginResponseDTO;
import no.hiof.g13.Exceptions.UserNotFoundException;
import no.hiof.g13.adapters.GetUsersLoginAPI_RepositoryMySQL;
import no.hiof.g13.models.User;
import no.hiof.g13.ports.in.GetUsersLoginAPI_Port;

import java.util.List;
import java.util.stream.Collectors;

public class GetUsersLoginAPI_Service {
    private final GetUsersLoginAPI getUsersLoginAPI;
    private final GetUsersLoginAPI_Port getUsersLoginAPI_port;

    public GetUsersLoginAPI_Service() {
        this.getUsersLoginAPI_port = new GetUsersLoginAPI_RepositoryMySQL();
        this.getUsersLoginAPI = new GetUsersLoginAPI(getUsersLoginAPI_port);
    }

    public void configureRoute(Javalin app) {
        getUsersLoginAPI.getUsers(app);
    }

    public UserLoginResponseDTO getUserById(int userId) throws ServiceException {
        try {
            User user = getUsersLoginAPI_port.getUserById(userId);
            if(user == null) {
                throw new UserNotFoundException("User not found");
            }
            return UserLoginResponseDTO.fromDomain(user);
        }
        catch (Exception e) {
            throw new ServiceException("Failed to fetch user", e);
        }
    }

    public UserLoginResponseDTO getUserByEmail(String email) throws ServiceException {
        try {
            User user = getUsersLoginAPI_port.getUserByEmail(email);
            if(user == null) {
                throw new UserNotFoundException("User not found");
            }
            return UserLoginResponseDTO.fromDomain(user);
        }
        catch (Exception e) {
            throw new ServiceException("Failed to fetch user", e);
        }
    }

    public List<UserLoginResponseDTO> getAllUsers() throws ServiceException {
        try {
            return getUsersLoginAPI_port.getAllUsers().stream().map(UserLoginResponseDTO::fromDomain).collect(Collectors.toList());
        }
        catch (Exception e) {
            throw new ServiceException("Failed to fetch users", e);
        }
    }

    public void getUsers(Javalin app) {
        getUsersLoginAPI.getUsers(app);
    }
}
