package car;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CarRepository {
    private String repositoryName;
    private ArrayList<Car> carArrayList = new ArrayList<>(); // Holds ALL cars. Unsure if necessary



    public CarRepository(String repositoryName) {
        this.repositoryName = repositoryName;

        readFromJSON();
    }

    public ArrayList<Car> getCarArrayList() {
        return carArrayList;
    }

    public void setCarArrayList(ArrayList<Car> carArrayList) {
        this.carArrayList = carArrayList;
    }

    public void readFromJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(this.repositoryName);

        try{
            carArrayList = objectMapper.readValue(file, new TypeReference<ArrayList<Car>>(){});
            System.out.println(carArrayList.toString());
        }
        catch (IOException e){
            System.err.println(e);
        }
    }

    // Adds a new car to the repository file
    public void AddNewCar(Car car){
        //TODO: implement
        boolean exists = false;
        for (Car i : carArrayList){
            //Checks if the car being added does not already exist within the list
            if (car.getRegistrationNumber().equals(i.getRegistrationNumber())){
                exists = true;
                break;
            }
        }
        if (!exists){
            System.out.println("Adding car " + car.getRegistrationNumber() + " with car owner " + car.getOwner() + "to carArrayList");
            this.carArrayList.add(car);
            this.SaveCarsToJSON();
        }
        else{
            System.out.println("Car " + car.getRegistrationNumber() + " Already exists");
        }
    }

    // It rents da car, dumbass
    public void RentCar(Car car){
        //TODO: implement
    }

    // Returns all cars that are not currently occupied
    public ArrayList<Car> GetAllAvailableCars(){
        ArrayList<Car> allAvailableCars = new ArrayList<>();
        for (Car i : carArrayList){
            //Checks if the car being added does not already exist within the list
            if (i.isAvailable()){
                allAvailableCars.add(i);
            }
        }
        return allAvailableCars;
    }

    // Unsure that to do here...
    public void SaveCarsToJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(this.repositoryName);


        try{
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, carArrayList);
            readFromJSON();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
