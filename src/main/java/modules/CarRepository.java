package modules;


import tools.CarRepositoryFileHandler;
import tools.DateHandler;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

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

    // Used to generate dummies/placeholder objects for the prototype.
    public void carFactory(int createAmount){
        String[] firstNames = {"Mathias", "Martin", "Susanne", "Jesper", "Oliver", "Justin", "Kasper", "Markus", "Mario",
        "Jonathan", "Johannes", "Marius", "Ole", "Dole", "Doffen", "Mike", "Christian", "Tom", "Morten", "Wenche",
        "Julie", "Jasmin", "Anne", "Tomine"};
        String[] lastNames = {"Ratdal", "Dale", "Demuth", "Thorvaldsen", "Kimmell", "Jansen", "Smith", "Sandersen", "Berg",
        "Kristiansen", "Heine", "Heide", "Grenberg", "Olsen", "Hansen", "Nordmann", "Dahl", "Solberg", "Karlsen",
        "Strand", "Ali", "Holm", "Knutsen"};
        String[] regLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String[] model = {"Toyota", "Volvo", "Volkswagen", "Ford", "Mercedes-Benz", "BMW", "Audi", "Kia", "Renault"};
        for (int i = 0; i < createAmount; i++){
            int randomFirstNameInt = ThreadLocalRandom.current().nextInt(0, firstNames.length);
            int randomLastNameInt = ThreadLocalRandom.current().nextInt(0, lastNames.length);
            int randomReg1LetterInt = ThreadLocalRandom.current().nextInt(0, regLetters.length);
            int randomReg2LetterInt = ThreadLocalRandom.current().nextInt(0, regLetters.length);
            int randomReg3LetterInt = ThreadLocalRandom.current().nextInt(0, regLetters.length);
            long randomRegNumber = ThreadLocalRandom.current().nextInt(10000, 99999);
            int randomModelNumber = ThreadLocalRandom.current().nextInt(0, model.length);

            int year = 2022;
            int randomFirstDateMonth = ThreadLocalRandom.current().nextInt(1, 12+1);
            int randomFirstDateDays = ThreadLocalRandom.current().nextInt(1, DateHandler.getDaysOfMonth(randomFirstDateMonth)+1);
            int randomSecondDateMonth = ThreadLocalRandom.current().nextInt(1, 12+1);
            int randomSecondDateDays = ThreadLocalRandom.current().nextInt(1, DateHandler.getDaysOfMonth(randomSecondDateMonth)+1);

            LocalDate firstDate = LocalDate.of(year, randomFirstDateMonth, randomFirstDateDays);
            LocalDate secondDate = LocalDate.of(year, randomSecondDateMonth, randomSecondDateDays);

            Listing listing = new Listing();

            if(DateHandler.dateIsValid(firstDate, secondDate)){
                listing.setStartDate(firstDate);
                listing.setEndDate(secondDate);
            } else if (DateHandler.dateIsValid(secondDate, firstDate)){
                listing.setStartDate(secondDate);
                listing.setEndDate(firstDate);
            }
            listing.setAvailable(ThreadLocalRandom.current().nextBoolean());

            String name = firstNames[randomFirstNameInt] + " " + lastNames[randomLastNameInt];
            System.out.println(name);
            String licensePlate = regLetters[randomReg1LetterInt] + regLetters[randomReg2LetterInt] +
                    regLetters[randomReg3LetterInt] + randomRegNumber;


            Car car = new Car(licensePlate, name, model[randomModelNumber]);
            String description = "Hello! My name is " + car.getOwner() + " and I have a nice " + car.getModel() +
                    " which is available for rent! Please contact me if you're interested in renting it. :)";
            listing.setDescription(description);
            car.setListing(listing);
            addNewCar(car);
        }
        saveCarsToJSON();

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
