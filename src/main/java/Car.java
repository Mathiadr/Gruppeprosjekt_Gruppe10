import java.util.ArrayList;

public class Car {
    private String registrationNumber;
    private String owner;
    private String model;
    private String availableDate;

    private static ArrayList<Car> carArrayList = new ArrayList<>();

    public Car(String registrationNumber, String owner, String model) {
        this.registrationNumber = registrationNumber;
        this.owner = owner;
        this.model = model;
    }


    public String getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(String availableDate) {
        this.availableDate = availableDate;
    }

    public static ArrayList<Car> getCarArrayList() {
        return carArrayList;
    }

    public static void setCarArrayList(ArrayList<Car> carArrayList) {
        Car.carArrayList = carArrayList;
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
}
