package modules;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Car {
    private String registrationNumber;
    private String owner;
    private String model;
    private String brand;                   //TODO: IMPLEMENT
    //private String fuelType;              //TODO: IMPLEMENT
    //private String transmission;          //TODO: IMPLEMENT
    //private Collection<String> features;  //TODO: IMPLEMENT
    private Listing listing; // Mapping instead??

    public static final String[] fuelTypesList = {"Electric", "Gasoline", "Diesel"};
    public static final String[] transmissionList = {"Manual", "Automatic"};
    public static final String[] featureList = {};



    public Car(){

    }

    public Car(String registrationNumber, String owner, String model) {
        this.registrationNumber = registrationNumber;
        this.owner = owner;
        this.model = model;
        LocalDate startDateplaceholder = LocalDate.of(2022, 1, 1);
        LocalDate endDatePlaceholder = LocalDate.of(2022, 1, 1);
        this.listing = new Listing(startDateplaceholder, endDatePlaceholder, true, "");
    }
    public Car(String registrationNumber, String owner, String model, Listing listing) {
        this.registrationNumber = registrationNumber;
        this.owner = owner;
        this.model = model;
        this.listing = listing;
    }


    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }



    @Override
    public String toString(){
        return "\n\nregistrationNumber: " + registrationNumber + ",\n" +
                "Owner: " + owner + ",\n" +
                "Model: " + model + ",\n" +
                "Listing: " + listing;
    }
}
