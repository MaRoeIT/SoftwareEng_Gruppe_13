package no.hiof.g13.models;

public class Bruker {
    public int bruker_id;
    public String fornavn;
    public String etternavn;
    public int status_id;
    public int kontakt_id;

    public Bruker(){
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

    public int getKontakt_id() {
        return kontakt_id;
    }

    public void setKontakt_id(int kontakt_id) {
        this.kontakt_id = kontakt_id;
    }
}

