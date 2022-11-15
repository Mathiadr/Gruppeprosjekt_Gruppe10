import modules.Car;
import modules.CarRepository;

import forms.CarRental;
import modules.Listing;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        CarRental carRental = new CarRental("Car rental");
        carRental.setSize(700, 450);
        carRental.setVisible(true);


        CarRepository carRepository = new CarRepository("testRepository.json");
        carRepository.AddNewCar(new Car("MXH10045", "Mathias Dale Ratdal", "E2"));
        carRepository.AddNewCar(new Car("EBX23365", "Sokrat Starynkiewicz", "A4"));
        carRepository.AddNewCar(new Car("E0B10045", "Joe Mama", "C2"));
        carRepository.AddNewCar(new Car("ADHD0O11", "Sander Kristiansen", "C6"));
        carRepository.AddNewCar(new Car("D1CK9864", "Djikstra Van Houven", "BA1"));
        carRepository.AddNewCar(new Car("P0043456", "John Doe", "A2"));
        carRepository.AddNewCar(new Car("BE240051", "Ola Nordmann", "A1"));

        Listing newListing = new Listing(
                "MathiasDale", null, null, null, true, "d"
        );

        ArrayList<Car> carArrayList = carRepository.getCarArrayList();
        carArrayList.get(0).setListing(newListing);
        carRepository.SaveCarsToJSON();

    }
}
