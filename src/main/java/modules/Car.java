package modules;

import java.util.ArrayList;

public class Car {
    private String registrationNumber;
    private String owner;
    private String model;
    private boolean isAvailable = true;     //Whether the car is occupied (Not to be confused with available date)
    //private String fuelType;              //TODO: IMPLEMENT
    //private String transmission;          //TODO: IMPLEMENT
    //private Collection<String> features;  //TODO: IMPLEMENT

    private static ArrayList<Car> carArrayList = new ArrayList<>();


    public Car(){

    }

    public Car(String registrationNumber, String owner, String model) {
        this.registrationNumber = registrationNumber;
        this.owner = owner;
        this.model = model;

        carArrayList.add(this);
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
        return "registrationNumber: " + registrationNumber +
                "\n, Owner: " + owner +
                "\n, Model: " + model +
                "\n, is Available: " + isAvailable;
    }
}
