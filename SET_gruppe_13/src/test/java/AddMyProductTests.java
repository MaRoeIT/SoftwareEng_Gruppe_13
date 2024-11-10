import no.hiof.g13.models.IOTDevice;
import no.hiof.g13.models.product.IOTDoorLock;
import no.hiof.g13.models.IOTHomeDevice;
import no.hiof.g13.models.MyProducts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class AddMyProductTests {

    @Mock
    IOTDevice mockIOTDevice;

    @Mock
    IOTHomeDevice mockHomeDevice;

    @Mock
    IOTDoorLock mockDoorLock;

    @Mock
    MyProducts mockMyProducts;

    @Test
    @DisplayName("Add multiple types of IOTDevice to MyProducts")
    public void addMultipleProducts(){

        //Arrange
        MyProducts myProducts = new MyProducts();
        //Act
        myProducts.addProducts(mockIOTDevice, mockHomeDevice, mockDoorLock);
        //Assert
        ArrayList<IOTDevice> expected = new ArrayList<>();
        expected.add(mockIOTDevice);
        expected.add(mockHomeDevice);
        expected.add(mockDoorLock);
        Assertions.assertEquals(expected, myProducts.getMyProducts());
    }

    @Test
    @DisplayName("Add No products to myproduct")
    public void addNoProduct(){

        //Arrange
        MyProducts myProducts = new MyProducts();
        //Act
        myProducts.addProducts();
        //Assert
        ArrayList<IOTDevice> expected = new ArrayList<>();
        Assertions.assertEquals(expected,myProducts.getMyProducts());
    }

    @Test
    @DisplayName("Add one product to myproduct")
    public void addOneProduct(){

        //Arrange
        MyProducts myProducts = new MyProducts();
        //Act
        myProducts.addProducts(mockDoorLock);
        //Assert
        ArrayList<IOTDevice> expected = new ArrayList<>();
        expected.add(mockDoorLock);
        Assertions.assertEquals(expected, myProducts.getMyProducts());
    }
}
