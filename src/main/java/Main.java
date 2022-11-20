import modules.Car;
import modules.CarRepository;

import forms.CarRental;


public class Main {
    public static void main(String[] args) {



        CarRepository carRepository = new CarRepository("testRepository.json");
        carRepository.addNewCar(new Car("MXH10045", "Mathias Dale Ratdal", "E2"));
        carRepository.addNewCar(new Car("EBX23365", "Sokrat Starynkiewicz", "A4"));
        carRepository.addNewCar(new Car("E0B10045", "Joe Mama", "C2"));
        carRepository.addNewCar(new Car("ADHD0O11", "Sander Kristiansen", "C6"));
        carRepository.addNewCar(new Car("D1CK9864", "Djikstra Van Houven", "BA1"));
        carRepository.addNewCar(new Car("P0043456", "John Doe", "A2"));
        carRepository.addNewCar(new Car("BE240051", "Ola Nordmann", "A1"));

        carRepository.saveCarsToJSON();
        carRepository.readFromJSON();

        CarRental carRental = new CarRental("Car rental");
        carRental.setSize(700, 450);
        carRental.setVisible(true);
    }
}
