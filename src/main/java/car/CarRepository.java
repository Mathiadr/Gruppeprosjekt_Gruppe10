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
        for (Car carListObject : carArrayList){
            //Checks if the car being added does not already exist within the list
            if (!Objects.equals(carListObject.getRegistrationNumber(), car.getRegistrationNumber())){
                this.carArrayList.add(car);
                this.SaveCarsToJSON();
            }
        }
    }

    // It rents da car, dumbass
    public void RentCar(Car car){
        //TODO: implement
    }

    // Returns all cars that are not currently occupied
    public ArrayList<Car> GetAllAvailableCars(){

        return null;
    }

    // Unsure what to do here...
    public void SaveCarsToJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(this.repositoryName);


        try{
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, carArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
