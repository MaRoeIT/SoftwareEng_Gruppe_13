import no.hiof.g13.DTO.in.GetProductsAPI_DTO;
import no.hiof.g13.adapters.GetProductsAPI_RepositoryMySQL;
import no.hiof.g13.ports.in.GetProductsAPI_Port;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GetProductsAPI_RepositoryTests {

    @Test
    @DisplayName("Existing product ID should return correct product")
    public void getValidProductById() {
        // Arrange
        GetProductsAPI_Port getProductsAPIPort = new GetProductsAPI_RepositoryMySQL();
        int product_id = 1;

        // Act
        GetProductsAPI_DTO productsAPIDto = getProductsAPIPort.getProductById(product_id);

        // Assert
        Assertions.assertNotNull(productsAPIDto);
        Assertions.assertEquals(1, productsAPIDto.getDeviceID());
    }
}
