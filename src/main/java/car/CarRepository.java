package car;

import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class CarRepository {
    private String repositoryName;

    private ArrayList<Car> CarArrayList; // Holds ALL cars. Unsure if necessary

    public CarRepository(String repositoryName) {
        this.repositoryName = repositoryName;

        try {
            FileReader fileReader = new FileReader(this.repositoryName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Adds a new car to the repository file
    public void AddNewCar(Car car){
        //TODO: implement
        this.CarArrayList.add(car);
        this.SaveAllCars();
    }

    // It rents da car, dumbass
    public void RentCar(Car car){
        //TODO: implement
    }

    // Returns all cars that are not currently occupied
    public ArrayList<Car> GetAllAvailableCars(){
        //TODO: implement
        return null;
    }

    // Unsure what to do here...
    public void SaveAllCars(){
        //TODO: implement
        /*
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("registrationNumber", car.getRegistrationNumber());
        jsonObject.put("owner", car.getOwner());
        jsonObject.put("model", car.getModel());

        jsonObject.put("availableDate", car.getAvailableDate());
        jsonObject.put("isAvailable", car.isAvailable());
        jsonObject.put("fuelType", car.getFuelType());
        jsonObject.put("transmission", car.getTransmission());
        jsonObject.put("features", car.getFeatures());//Maybe a nested JSONObject/JSONArray belongs here?
        try{
            FileWriter fileWriter = new FileWriter(this.repositoryName);
            fileWriter.append(jsonObject.toString());
            System.out.println("Car " + car.getRegistrationNumber() + " has been written to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }


}
