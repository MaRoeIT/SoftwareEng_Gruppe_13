package no.hiof.g13.models;

import java.util.ArrayList;

//klasse som har listen over IOT enheter og metoder for å legge til å trekke fra enheter
public class MyProducts {
    //Arraylist som holder på den generelle klassen IOTDevice, Her legges IOT enhetene brukeren har til.
    private ArrayList<IOTDevice> myProducts = new ArrayList<>();

    //konstruktør
    public MyProducts(ArrayList<IOTDevice> myProducts){
        this.myProducts = myProducts;
    }
}
