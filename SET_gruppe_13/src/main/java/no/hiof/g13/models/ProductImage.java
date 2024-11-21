package no.hiof.g13.models;

public class ProductImage {
    private int produktId;
    private String bucketlink;
    private String kategori;
    private String navn;

    public ProductImage() {

    }

    public ProductImage(int produktId, String bucketlink, String kategori, String navn) {
        this.produktId = produktId;
        this.bucketlink = bucketlink;
        this.kategori = kategori;
        this.navn = navn;
    }
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
}
