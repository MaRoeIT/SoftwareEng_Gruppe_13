package no.hiof.g13.ports;

import no.hiof.g13.models.Address;
import no.hiof.g13.models.User;

public interface CreateUserAPI_Port {
    void createUser(User user);
    boolean userExists(String email);
}
