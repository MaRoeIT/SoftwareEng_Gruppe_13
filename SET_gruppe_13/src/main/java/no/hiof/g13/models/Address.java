package no.hiof.g13.models;

public class Address {
    public int adresse_id;
    public String adresse;
    public String postnummer;

    public Address() {
    }

    public Address(int adresse_id, String adresse, String postnummer) {
        this.adresse_id = adresse_id;
        this.adresse = adresse;
        this.postnummer = postnummer;
    }

    public int getAdresse_id() {
        return adresse_id;
    }

    public void setAdresse_id(int adresse_id) {
        this.adresse_id = adresse_id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(String postnummer) {
        this.postnummer = postnummer;
    }
}
