import no.hiof.g13.models.IOTDevice;
import no.hiof.g13.models.IOTDoorLock;
import no.hiof.g13.models.IOTHomeDevice;
import no.hiof.g13.models.MyProducts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class RemoveMyProductsTests {

    @Mock
    IOTDevice mockIOTDevice;

    @Mock
    IOTHomeDevice mockHomeDevice;

    @Mock
    IOTDoorLock mockDoorLock;

    @Test
    @DisplayName("Removing IOTDevices")
    public void removeIOTDevices(){
        MyProducts myProducts = new MyProducts();

        ArrayList<IOTDevice> expectedResult = new ArrayList<>();

        Collections.addAll(myProducts.getMyProducts(), mockIOTDevice, mockHomeDevice, mockDoorLock);

        myProducts.removeProduct(mockIOTDevice);
        myProducts.removeProduct(mockHomeDevice);
        myProducts.removeProduct(mockDoorLock);

        Assertions.assertEquals(myProducts.getMyProducts(), expectedResult);
    }
}
