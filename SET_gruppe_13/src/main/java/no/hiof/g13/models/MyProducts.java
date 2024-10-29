package no.hiof.g13.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Handles the users collection of IOT products currently installed.
 *
 * The class is responsible for adding and removal of IOTDevices from it's list of devices.
 *
 * This class is reliant on communicating with a persistent storage, the class needs to get this data to work properly.
 *
 * Example usage:
 * MyProducts myProducts = new MyProducts();
 * myProducts.addProducts(IOTDevice1, IOTDevice2, ...);
 */
public class MyProducts {
    //Arraylist som holder på den generelle klassen IOTDevice, Her legges IOT enhetene brukeren har til.
    private ArrayList<IOTDevice> myProducts = new ArrayList<>();

    //konstruktører, en hvor man har en liste med enheter fra før av og en som starter blankt.
    public MyProducts(ArrayList<IOTDevice> myProducts){
        this.myProducts = myProducts;
    }

    public MyProducts(){};

    /**
     * Adds objects of the type IOTDevice into the ArrayList myProducts.
     *
     * @param devices Takes ann undefined amount of IOTDevices objects
     */
    public void addProducts(IOTDevice... devices){
        for (IOTDevice device: devices){
            myProducts.add(device);
        }
    }

    /**
     * Removes a single IOTDevice object from myPtoducts ArraList
     *
     * @param device A single object of the IOTDevice type
     */
    public void removeProduct(IOTDevice device){
        this.myProducts.remove(device);
    }

    /**
     * gets a specified IOTDevice from myProducts ArrayList
     *
     * @param deviceName The name of the product you want to remove from the list
     * @return Returns the IOTDevice object that was specified, 
     * if the method can't find the object it will print a message and then return null
     */
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
