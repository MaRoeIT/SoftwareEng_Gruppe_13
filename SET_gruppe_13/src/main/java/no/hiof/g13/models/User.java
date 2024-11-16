package no.hiof.g13.models;

public class User {
    private int bruker_id;
    private String fornavn;
    private String etternavn;
    private int status_id;
    private String mobil;
    private String epost;
    private String passord;
    private Address address;
    private int userLevel;

    public User(){
    }
    
    public User(int bruker_id, String epost, String passord) {
        this.bruker_id = bruker_id;
        this.epost = epost;
        this.passord = passord;
    }

    public User(int bruker_id, String fornavn, String etternavn, int status_id, String mobil, String epost, String passord, Address address, int userLevel) {
        this.bruker_id = bruker_id;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.status_id = status_id;
        this.mobil = mobil;
        this.epost = epost;
        this.passord = passord;
        this.address = address;
        this.userLevel = userLevel;
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

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getUserLevel() {
        return this.userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }
}

