package no.hiof.g13.DTO.in;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsDTO {
    private int produktId;
    private String bucketlink;
    private String kategori;
    private String navn;
    private String description;
    private String modell;
    private int garantiMåneder;
    private String ean;
    private String avhengigAv;

    // Getters and setters
    public int getProduktId() {
        return produktId;
    }

    public void setProduktId(int produktId) {
        this.produktId = produktId;
    }

    public String getBucketlink() {
        return bucketlink;
    }

    public void setBucketlink(String bucketlink) {
        this.bucketlink = bucketlink;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public int getGarantiMåneder() {
        return garantiMåneder;
    }

    public void setGarantiMåneder(int garantiMåneder) {
        this.garantiMåneder = garantiMåneder;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getAvhengigAv() {
        return avhengigAv;
    }

    public void setAvhengigAv(String avhengigAv) {
        this.avhengigAv = avhengigAv;
    }
}
