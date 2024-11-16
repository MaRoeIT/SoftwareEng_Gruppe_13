package no.hiof.g13.DTO.in;

public class AuthenticateUserRequest_DTO {
    private final String epost;
    private final String passord;

    public AuthenticateUserRequest_DTO(String epost, String passord) {
        this.epost = epost;
        this.passord = passord;
    }

    public String getEpost() { return epost; }
    public String getPassord() { return passord; }
}
