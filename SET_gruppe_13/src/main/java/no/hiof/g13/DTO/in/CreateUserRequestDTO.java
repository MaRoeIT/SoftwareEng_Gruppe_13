package no.hiof.g13.DTO.in;

import no.hiof.g13.models.Address;

public class CreateUserRequestDTO {
    private String fornavn;
    private String etternavn;
    private Integer status_id;
    private String epost;
    private String passord;
    private String mobil;
    private Address address;
    private Integer userLevel;

    public CreateUserRequestDTO() {
    }

    public CreateUserRequestDTO(String fornavn, String etternavn, Integer status_id, String mobil, String epost, String passord, Address address) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.status_id = status_id;
        this.epost = epost;
        this.passord = passord;
        this.mobil = mobil;
        this.address = address;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public Integer getStatus_id() {
        return status_id;
    }

    public String getEpost() {
        return epost;
    }

    public String getMobil() {
        return mobil;
    }

    public String getPassord() {
        return this.passord;
    }

    public Address getAddress() {
        return address;
    }

    public Integer getUserLevel() {
        return this.userLevel;
    }
}
