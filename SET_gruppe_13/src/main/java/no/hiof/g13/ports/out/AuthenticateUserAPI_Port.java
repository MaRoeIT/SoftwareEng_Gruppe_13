package no.hiof.g13.ports.out;

import no.hiof.g13.models.User;

public interface AuthenticateUserAPI_Port {
    User authenticateUser(String epost, String passord);
}
