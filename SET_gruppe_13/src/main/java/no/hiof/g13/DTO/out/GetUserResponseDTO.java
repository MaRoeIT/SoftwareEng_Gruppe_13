package no.hiof.g13.DTO.out;

import no.hiof.g13.models.Address;
import no.hiof.g13.models.User;

public class GetUserResponseDTO {
    private int bruker_id;
    private String fornavn;
    private String etternavn;
    private int status_id;
    private String mobil;
    private String epost;
    private Address address;
    private int userLevel;

    public GetUserResponseDTO(int bruker_id, String fornavn, String etternavn, int status_id, String mobil, String epost, Address address, int userLevel) {
        this.bruker_id = bruker_id;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.status_id = status_id;
        this.mobil = mobil;
        this.epost = epost;
        this.address = address;
        this.userLevel = userLevel;
    }

    public static GetUserResponseDTO fromDomain(User user) {
        return new GetUserResponseDTO(
            user.getBruker_id(), user.getFornavn(), user.getEtternavn(),
                user.getStatus_id(), user.getMobil(), user.getEpost(),
                user.getAddress(), user.getUserLevel()
        );
    }

    public int getBruker_id() {
        return bruker_id;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public int getStatus_id() {
        return status_id;
    }

    public String getMobil() {
        return mobil;
    }

    public String getEpost() {
        return epost;
    }

    public Address getAddress() {
        return address;
    }

    public int getUserLevel() {
        return userLevel;
    }}
