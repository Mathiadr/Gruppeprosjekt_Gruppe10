import modules.Car;
import modules.CarRepository;

import forms.CarRental;
import modules.Listing;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {



        CarRepository carRepository = new CarRepository("testRepository.json");
        carRepository.addNewCar(
                new Car("MXH10045", "Mathias Dale Ratdal", "E2",
                new Listing(
                        LocalDate.of(2022, 1, 1),
                        LocalDate.of(2022, 3, 7),
                        true, "PLACEHOLDER DESCRIPTION")));
        carRepository.addNewCar(
                new Car("EBX23365", "Sokrat Starynkiewicz", "A4",
                        new Listing(
                                LocalDate.of(2022, 2, 25),
                                LocalDate.of(2022, 6, 7),
                                true, "PLACEHOLDER DESCRIPTION")));
        carRepository.addNewCar(
                new Car("E0B10045", "Joe Mama", "C2",
                        new Listing(
                                LocalDate.of(2022, 2, 1),
                                LocalDate.of(2022, 2, 10),
                                true, "PLACEHOLDER DESCRIPTION")));
        carRepository.addNewCar(
                new Car("ADHD0O11", "Sander Kristiansen", "C6",
                        new Listing(
                                LocalDate.of(2022, 7, 15),
                                LocalDate.of(2022, 8, 15),
                                true, "PLACEHOLDER DESCRIPTION")));
        carRepository.addNewCar(
                new Car("D1CK9864", "Djikstra Van Houven", "BA1",
                        new Listing(
                                LocalDate.of(2022, 10, 14),
                                LocalDate.of(2022, 11, 4),
                                true, "PLACEHOLDER DESCRIPTION")));
        carRepository.addNewCar(
                new Car("P0043456", "John Doe", "A2",
                        new Listing(
                                LocalDate.of(2022, 12, 1),
                                LocalDate.of(2022, 12, 31),
                                true, "PLACEHOLDER DESCRIPTION")));
        carRepository.addNewCar(
                new Car("BE240051", "Ola Nordmann", "A1",
                        new Listing(
                                LocalDate.of(2022, 2, 7),
                                LocalDate.of(2022, 12, 23),
                                true, "PLACEHOLDER DESCRIPTION")));

        carRepository.saveCarsToJSON();
        carRepository.readFromJSON();

        CarRental carRental = new CarRental("Car rental");
        carRental.setSize(700, 450);
        carRental.setVisible(true);
        ImageIcon logo = new ImageIcon("src/windowLogo.png");
        carRental.setIconImage(logo.getImage());
    }
}
