package no.hiof.g13.ports.out;

import no.hiof.g13.models.User;

import java.util.List;

public interface UserRepositoryPort {

    User getUser(int userId);
    List<User> getUsers();
    void saveUser(User user);
    void deleteUser(int userId);
    boolean authenticateUser(String email, String password);


}
