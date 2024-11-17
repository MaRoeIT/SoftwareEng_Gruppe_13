package no.hiof.g13.ports.in;

import no.hiof.g13.models.User;

import java.util.List;

public interface GetUsersAPI_Port {
    List<User> getAllUsers();
    User getUserById(int userId);
    User getUserByEmail(String epost);
}
