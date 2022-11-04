package car;

import java.util.ArrayList;
import java.util.Collection;

public class Car {
    private String registrationNumber;
    private String owner;
    private String model;
    //private String availableDate;         //TODO: IMPLEMENT
    private boolean isAvailable = true;     //Whether the car is occupied (Not to be confused with available date)
    //private String fuelType;              //TODO: IMPLEMENT
    //private String transmission;          //TODO: IMPLEMENT
    //private Collection<String> features;  //TODO: IMPLEMENT

    public Car(){

    }

    public Car(String registrationNumber, String owner, String model) {
        this.registrationNumber = registrationNumber;
        this.owner = owner;
        this.model = model;
    }


    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
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

    @Override
    public String toString(){
        return "registrationNumber: " + registrationNumber + ", owner: " + owner + ", model: "
            + model + ", isAvailable: " + isAvailable;
    }
}
