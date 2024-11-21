package no.hiof.g13.adapters;

import no.hiof.g13.models.IOTDevice;
import no.hiof.g13.ports.out.MyProductsSendPort;

import java.util.ArrayList;

public class GetLightSettingsUseCase implements MyProductsSendPort {
    private static ArrayList<IOTDevice> myProducts;

    public GetLightSettingsUseCase(){
    }

    @Override
    public void sendMyProductList(ArrayList<IOTDevice> myProducts) {
        System.out.println("The list recived " + myProducts);
        GetLightSettingsUseCase.myProducts = myProducts;
    }

    public static ArrayList<IOTDevice> getMyProducts() {
        return myProducts;
    }

    public static void setMyProducts(ArrayList<IOTDevice> myProducts) {
        GetLightSettingsUseCase.myProducts = myProducts;
    }
}
