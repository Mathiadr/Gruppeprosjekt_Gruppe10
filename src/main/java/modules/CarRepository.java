package modules;

import tools.CarRepositoryFileHandler;

import java.io.File;
import java.util.ArrayList;

public class CarRepository {
    private String repositoryName; // This holds the name of the repository being accessed.
    private ArrayList<Car> carArrayList = new ArrayList<>();

    private final CarRepositoryFileHandler carRepositoryFileHandler = new CarRepositoryFileHandler();

    public CarRepository(String repositoryName) {
        this.repositoryName = repositoryName;

        carArrayList = readFromJSON();
    }



    // Reads all objects from JSON file and maps it to the Car object
    public ArrayList<Car> readFromJSON(){
        return carRepositoryFileHandler.readFromFile(repositoryName);
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
            System.out.println("Adding car " + car.getRegistrationNumber() + " with car owner " + car.getOwner() + " to carArrayList");
            this.carArrayList.add(car);
        }
        else{
            System.out.println("Car " + car.getRegistrationNumber() + " Already exists");
        }
    }

    // Removes Car object from carArrayList
    public void RemoveExistingCar(Car car){
        if (carArrayList.contains(car)){
            // TODO: Implement error message to GUI
            System.out.println("Removed car " + car.getRegistrationNumber() + " with car owner " + car.getOwner() + " from carArrayList");
            carArrayList.remove(car);
        }
        else{
            System.out.println("Could not find car " + car.getRegistrationNumber() + " in CarArrayList");
        }
    }

    // It rents da car, dumbass
    public void RentCar(Car car){
        //TODO: REMOVE
    }

    // Returns all cars that are not currently occupied
    public ArrayList<Car> GetAllAvailableCars(){
        ArrayList<Car> allAvailableCars = new ArrayList<>();
        for (Car i : carArrayList){
            if (i.isAvailable()){
                allAvailableCars.add(i);
            }
        }
        return allAvailableCars;
    }

    public void SaveCarsToJSON(){
        carRepositoryFileHandler.writeArrayListToFile(getCarArrayList(), repositoryName);
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public ArrayList<Car> getCarArrayList() {
        return carArrayList;
    }

    public void setCarArrayList(ArrayList<Car> carArrayList) {
        this.carArrayList = carArrayList;
    }
}
