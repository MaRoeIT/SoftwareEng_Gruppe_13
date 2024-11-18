package no.hiof.g13.ports;

import no.hiof.g13.models.Address;

public interface CreateUserAPI_Port {
    int createUser(String firstName, String lastName, int statusId, String mobile, String email, String hashPass, String address, String postnumber);
    boolean userExists(String email);
}
