package no.hiof.g13.DTO.out;

import no.hiof.g13.models.User;

public class UserLoginResponseDTO {
    private int bruker_id;
    private String epost;
    private String passord;

    public UserLoginResponseDTO(int bruker_id, String epost, String passord) {
        this.bruker_id = bruker_id;
        this.epost = epost;
        this.passord = passord;
    }

    public static UserLoginResponseDTO fromDomain(User user) {
        return new UserLoginResponseDTO(
            user.getBruker_id(), user.getEpost(), user.getPassord()
        );
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
