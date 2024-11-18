package no.hiof.g13.ports.out;

import no.hiof.g13.models.User;

public interface AuthenticateUserAPI_Port {
    boolean authenticateUser(String epost, String passord);
    int getUserIdByEmail(String email);
    String getToken(int userId);
    void saveToken(String token, int userId);
    void removeToken(int userId);
}
