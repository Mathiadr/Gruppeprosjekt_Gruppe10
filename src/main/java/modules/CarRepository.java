package modules;

import tools.CarRepositoryFileHandler;
import tools.DateHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class CarRepository {
    private String repositoryName; // This holds the name of the repository being accessed.
    private ArrayList<Car> carArrayList = new ArrayList<>();
    private final CarRepositoryFileHandler carRepositoryFileHandler = new CarRepositoryFileHandler();

    public CarRepository(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    // Adds a new car to the repository file and returns 1 if succeeds, otherwise -1
    public int addNewCar(Car car){
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
            this.carArrayList.add(car);
            return 1;
        }
        else return -1; // TODO: Implement error message to GUI
    }

    // Removes Car object from carArrayList and returns 1 if succeeds, otherwise -1
    public int removeExistingCar(Car car){
        if (carArrayList.contains(car)){
            carArrayList.remove(car);
            return 1;
        }
        else return -1; // TODO: Implement error message to GUI
    }

    // Starts the rent process, checking if the date is valid and rents the car if succeeds, otherwise -1
    public int initiateRentProcess(Car car, LocalDate startOfRentPeriod, LocalDate endOfRentPeriod){
        if (DateHandler.rentPeriodIsValid(car, startOfRentPeriod, endOfRentPeriod)){
            car.getListing().setAvailable(false);
            return 1;
        }
        else{
            return -1;} // TODO: Implement error message to GUI
    }

    // Returns cars that are within given rent period and not currently occupied
    public ArrayList<Car> getAllAvailableCars(LocalDate startOfRentPeriod, LocalDate endOfRentPeriod){
        ArrayList<Car> allAvailableCars = new ArrayList<>();
        for (Car i : carArrayList){
            if (i.getListing().isAvailable() && DateHandler.rentPeriodIsValid(i, startOfRentPeriod, endOfRentPeriod)){
                allAvailableCars.add(i);
            }
        }
        return allAvailableCars;
    }
    // Returns all cars that are not currently occupied
    public ArrayList<Car> getAllAvailableCars(){
        ArrayList<Car> allAvailableCars = new ArrayList<>();
        for (Car i : carArrayList){
            if (i.getListing().isAvailable()){
                allAvailableCars.add(i);
            }
        }
        return allAvailableCars;
    }

    public void updateListing(Car car, Listing listing){
        for (Car i : carArrayList){
            if (car.getRegistrationNumber().equals(i.getRegistrationNumber())){
                car.setListing(listing);
                break;
            }
        }
    }

    public void carFactory(){
        String[] firstNames = {};
        String[] lastNames = {};
    }

    public void saveCarsToJSON(){
        carRepositoryFileHandler.writeArrayListToFile(getCarArrayList(), repositoryName);
    }

    // Reads all objects from JSON file and maps it to the Car object
    public void readFromJSON(){
        carArrayList = carRepositoryFileHandler.readFromFile(repositoryName);
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

    public CarRepositoryFileHandler getCarRepositoryFileHandler() {
        return carRepositoryFileHandler;
    }
}
