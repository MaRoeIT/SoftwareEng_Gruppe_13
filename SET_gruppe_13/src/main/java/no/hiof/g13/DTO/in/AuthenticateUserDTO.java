package no.hiof.g13.DTO.in;

import no.hiof.g13.models.User;

public class AuthenticateUserDTO {
    private int bruker_id;
    private String epost;
    private String passord;

    public AuthenticateUserDTO() {
    }

    public AuthenticateUserDTO(int bruker_id, String epost, String passord) {
        this.bruker_id = bruker_id;
        this.epost = epost;
        this.passord = passord;
    }

    public User toDomain() {
        return new User(getBruker_id(), getEpost(), getPassord());
    }

    public int getBruker_id() {
        return bruker_id;
    }

    public void setBruker_id(int bruker_id) {
        this.bruker_id = bruker_id;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }
}




