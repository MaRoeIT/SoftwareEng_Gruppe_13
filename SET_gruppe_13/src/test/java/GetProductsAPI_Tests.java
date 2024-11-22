import io.javalin.Javalin;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import no.hiof.g13.API.GetProductsAPI;
import no.hiof.g13.DTO.in.GetProductsAPI_DTO;
import no.hiof.g13.DTO.in.ProductDetailsDTO;
import no.hiof.g13.models.ProductImage;
import no.hiof.g13.ports.GetProductsAPI_Port;
import org.json.JSONException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GetProductsAPI_Tests {

    @Mock
    private GetProductsAPI_Port apiPort;
    private Javalin app;

    @BeforeEach
    void setUp() {
        app = Javalin.create();
        GetProductsAPI api = new GetProductsAPI(apiPort);
        api.start(app);
        app.start(8080);
    }

    @AfterEach
    void tearDown() {
        app.stop();
    }

    @Test
    @DisplayName("getAllProducts returns all existing IOT products in a JSON list")
    public void getAllProducts_returnsAllIOT_products_existingInList() throws JSONException {
        // Arrange
        List<GetProductsAPI_DTO> products = Arrays.asList(
                new GetProductsAPI_DTO(1, "Nokia smartphone", "En smart Nokia 5510", "P987654321", "Phones"),
                new GetProductsAPI_DTO(5, "Philips SmartMower", "Roboterklipper med GPS-navigasjon og app-kontroll.", "S987654321", "Gardening"),
                new GetProductsAPI_DTO(6, "Samsung SmartVacuumr", "Robotstøvsuger med kraftig sugeeffekt og app-kontroll.", "S111222333", "Sanitation"),
                new GetProductsAPI_DTO(7, "Philips SmartWatch", "Klokke med fitness-funksjoner og tilkobling til mobil", "P987123654", "Wearables")
                );
        Mockito.when(apiPort.getAllProducts()).thenReturn(products);

        // Act
        HttpResponse<String> response = Unirest.get("http://localhost:" + app.port() + "/api/products").asString();

        // Assert
        Assertions.assertEquals(200, response.getStatus());
        Mockito.verify(apiPort).getAllProducts();
        JSONAssert.assertEquals("""
            [
                {"deviceID": 1, "name": "Nokia smartphone", "article": "En smart Nokia 5510", "barcode": P987654321, "category": "Phones"},
                {"deviceID": 5, "name": "Philips SmartMower", "article": "Roboterklipper med GPS-navigasjon og app-kontroll.", "barcode": S987654321, "category": "Gardening"},
                {"deviceID": 6, "name": "Samsung SmartVacuumr", "article": "Robotstøvsuger med kraftig sugeeffekt og app-kontroll.", "barcode": S111222333, "category": "Sanitation"},
                {"deviceID": 7, "name": "Philips SmartWatch", "article": "Klokke med fitness-funksjoner og tilkobling til mobil", "barcode": P987123654, "category": "Wearables"}

             ]""", response.getBody(), true);
    }

    @Test
    @DisplayName("getAllProducts returns empty JSON list when no products registrered")
    public void getAllProductsReturnsEmptyJsonListWhenNoProductsRegistered() throws JSONException {
        // Arrange
        List<GetProductsAPI_DTO> emptyProducts = new ArrayList<>();
        Mockito.when(apiPort.getAllProducts()).thenReturn(emptyProducts);

        // Act
        HttpResponse<String> response = Unirest.get("http://localhost:" + app.port() + "/api/products").asString();

        // Assert
        Assertions.assertEquals(200, response.getStatus());
        Mockito.verify(apiPort).getAllProducts();
        JSONAssert.assertEquals("[]", response.getBody(), true);
    }

    @Test
    @DisplayName("getAllProductImages returns JSON list of valid images links")
    public void getAllProductImages_returnsJsonListOfLinkedImages() throws JSONException {
        // Arrange
        List<ProductImage> productImages = Arrays.asList(
            new ProductImage(1, "https://g-13-product-pics.s3.eu-north-1.amazonaws.com/s3bucket/bulb1.jfif", "Electronics", "Samsung SmartBulb"),
                new ProductImage(2, "https://g-13-product-pics.s3.eu-north-1.amazonaws.com/s3bucket/bulb2.jfif", "Electronics", "Philips SmartBulb"),
                new ProductImage(3, "https://g-13-product-pics.s3.eu-north-1.amazonaws.com/s3bucket/bulb3.jfif", "Electronics", "Nokia SmartBul"),
                new ProductImage(4, "https://g-13-product-pics.s3.eu-north-1.amazonaws.com/s3bucket/bulb4.jfif", "Electronics", "Apple SmartBulb"),
                new ProductImage(5, "https://g-13-product-pics.s3.eu-north-1.amazonaws.com/s3bucket/cam1.jfif", "Security", "Philips SmartCam")
                );
        Mockito.when(apiPort.getAllProductImages()).thenReturn(productImages);

        // Act
        HttpResponse<String> response = Unirest.get("http://localhost:" + app.port() + "/api/products/images").asString();

        // Assert
        Assertions.assertEquals(200, response.getStatus());
        Mockito.verify(apiPort).getAllProductImages();
        JSONAssert.assertEquals("""
            [
                {"produktId": 1, "bucketlink": "https://g-13-product-pics.s3.eu-north-1.amazonaws.com/s3bucket/bulb1.jfif", "kategori": "Electronics", "navn": "Samsung SmartBulb"},
                {"produktId": 2, "bucketlink": "https://g-13-product-pics.s3.eu-north-1.amazonaws.com/s3bucket/bulb2.jfif", "kategori": "Electronics", "navn": "Philips SmartBulb"},
                {"produktId": 3, "bucketlink": "https://g-13-product-pics.s3.eu-north-1.amazonaws.com/s3bucket/bulb3.jfif", "kategori": "Electronics", "navn": "Nokia SmartBul"},
                {"produktId": 4, "bucketlink": "https://g-13-product-pics.s3.eu-north-1.amazonaws.com/s3bucket/bulb4.jfif", "kategori": "Electronics", "navn": "Apple SmartBulb"},
                {"produktId": 5, "bucketlink": "https://g-13-product-pics.s3.eu-north-1.amazonaws.com/s3bucket/cam1.jfif", "kategori": "Security", "navn": "Philips SmartCam"}

             ]""", response.getBody(), true);
    }

    @Test
    @DisplayName("getproductsById returns correct product corresponding with ID_InJSON")
    public void getProductById_returnsCorrectProductInJson() throws JSONException {
        // Arrange
        GetProductsAPI_DTO product = new GetProductsAPI_DTO(13, "Samsung SmartVacuum", "Robotstøvsuger med kraftig sugeeffekt og app-kontroll.", "S111222333", "Sanitation");
        Mockito.when(apiPort.getProductById(13)).thenReturn(product);

        // Act
        HttpResponse<String> response = Unirest.get("http://localhost:" + app.port() + "/api/products/id/13").asString();

        // Assert
        Assertions.assertEquals(200, response.getStatus());
        Mockito.verify(apiPort).getProductById(13);
        JSONAssert.assertEquals("""
                { "deviceID": 13, "name": "Samsung SmartVacuum", "article": "Robotstøvsuger med kraftig sugeeffekt og app-kontroll.", "barcode": "S111222333", "category": "Sanitation" },
                """, response.getBody(), true);
    }

    @Test
    @DisplayName("getProductsByUserId returns only products owned by specific user ID")
    public void getProductsById_returnsProductsByGivenUser() throws JSONException {
        // Arrange
        List<ProductDetailsDTO> productList = Arrays.asList(
            new ProductDetailsDTO(3, "https://g-13-product-pics.s3.eu-north-1.amazonaws.com/s3bucket/bulb1.jfif", "Electronics", "Nokia SmartBulb", null, null, 12, null, null),
                new ProductDetailsDTO(10, "https://g-13-product-pics.s3.eu-north-1.amazonaws.com/s3bucket/bulb4.jfif", "Garderning", "Philips SmartMower", null, null, 24, null, null)
        );

        Mockito.when(apiPort.getProductsByUserId(1)).thenReturn(productList);

        // Act
        HttpResponse<String> response = Unirest.get("http://localhost:" + app.port() + "/api/products/user_id/1").asString();

        // Assert
        Assertions.assertEquals(200, response.getStatus());
        Mockito.verify(apiPort).getProductsByUserId(1);
        JSONAssert.assertEquals("""
            [
                {"produktId": 3, "bucketlink": "https://g-13-product-pics.s3.eu-north-1.amazonaws.com/s3bucket/bulb1.jfif", "kategori": "Electronics", "navn": "Nokia SmartBulb", "description": null, "modell": null, "garantiMåneder": 12, "ean": null, "avhengigAv": null},
                {"produktId": 10, "bucketlink": "https://g-13-product-pics.s3.eu-north-1.amazonaws.com/s3bucket/bulb4.jfif", "kategori": "Garderning", "navn": "Philips SmartMower", "description": null, "modell": null, "garantiMåneder": 24, "ean": null, "avhengigAv": null}
             ]""", response.getBody(), true);
    }
}
