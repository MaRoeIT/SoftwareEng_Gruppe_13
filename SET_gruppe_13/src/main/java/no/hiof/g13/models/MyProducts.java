package no.hiof.g13.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

//klasse som har listen over IOT enheter og metoder for å legge til å trekke fra enheter
public class MyProducts {
    //Arraylist som holder på den generelle klassen IOTDevice, Her legges IOT enhetene brukeren har til.
    private ArrayList<IOTDevice> myProducts = new ArrayList<>();

    //konstruktører, en hvor man har en liste med enheter fra før av og en som starter blankt.
    public MyProducts(ArrayList<IOTDevice> myProducts){
        this.myProducts = myProducts;
    }

    public MyProducts(){};

    //Legger til hvor mange som helst IOTDevice objekter til myProducts gjennom varargs.
    public void addProducts(IOTDevice... devices){
        for (IOTDevice device: devices){
            myProducts.add(device);
        }
    }

    public IOTDevice getDevice(String deviceName){
        for(IOTDevice device : myProducts){
            if (device.getName().equals(deviceName)){
                return device;
            }
        }
        System.out.println("The device you where looking for is not added to your profile");
        return null;
    }

    public ArrayList<IOTDevice> getMyProducts() {
        return myProducts;
    }

    public void setMyProducts(ArrayList<IOTDevice> myProducts) {
        this.myProducts = myProducts;
    }
}
