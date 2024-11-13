package no.hiof.g13.DTO.in;

public class GetProductsAPI_DTO {
    private int deviceID;
    private String name;
    private String article;
    private String barcode;
    private String category;

    public GetProductsAPI_DTO() {

    }

    public GetProductsAPI_DTO(int deviceID, String name, String article, String barcode, String category) {
        this.deviceID = deviceID;
        this.name = name;
        this.article = article;
        this.barcode = barcode;
        this.category = category;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
