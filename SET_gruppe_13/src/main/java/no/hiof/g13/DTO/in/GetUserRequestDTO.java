package no.hiof.g13.DTO.in;

import no.hiof.g13.models.Address;
import no.hiof.g13.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserRequestDTO {
    private int bruker_id;
    private String fornavn;
    private String etternavn;
    private int status_id;
    private String mobil;
    private String epost;
    private Address address;
    private int userLevel;

    public User toDomain() {
            User user = new User();
            user.setBruker_id(this.bruker_id);
            user.setFornavn(this.fornavn);
            user.setEtternavn(this.etternavn);
            user.setStatus_id(this.status_id);
            user.setMobil(this.mobil);
            user.setEpost(this.epost);
            user.setAddress(new Address(address.getAdresse_id(), address.getAdresse(), address.getPostnummer()));
            user.setUserLevel(this.userLevel);
            return user;
    }

    public int getBruker_id() {
        return bruker_id;
    }

    public void setBruker_id(int bruker_id) {
        this.bruker_id = bruker_id;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }
}
