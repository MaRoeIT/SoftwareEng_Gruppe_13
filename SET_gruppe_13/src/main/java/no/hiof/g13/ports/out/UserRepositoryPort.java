package no.hiof.g13.ports.out;

import no.hiof.g13.models.User;

import java.util.List;

public interface UserRepositoryPort {

    User getUser(int userId);
    int getUserIdByEmail(String epost);
    int getUserIdByToken(String token);
    List<User> getUsers();
    void saveUser(User user);
    void deleteUser(int userId);
    boolean authenticateUser(String email, String password);
    void saveToken(String token, int userId);
    String getToken(int userId);
    void deleteToken(int userId);
}
