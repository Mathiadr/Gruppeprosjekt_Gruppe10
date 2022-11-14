import modules.Car;
import modules.Repository;

import forms.CarRental;


public class Main {
    public static void main(String[] args) {
        CarRental carRental = new CarRental("Car rental");
        carRental.setSize(700, 450);
        carRental.setVisible(true);

        /*
        Repository repository = new Repository("testRepository.json");
        repository.addNewListing(new Car("MXH10045", "Mathias Dale Ratdal", "E2"));
        repository.addNewListing(new Car("EBX23365", "Sokrat Starynkiewicz", "A4"));
        repository.addNewListing(new Car("E0B10045", "Joe Mama", "C2"));
        repository.addNewListing(new Car("ADHD0O11", "Sander Kristiansen", "C6"));
        repository.addNewListing(new Car("D1CK9864", "Djikstra Van Houven", "BA1"));
        repository.addNewListing(new Car("P0043456", "John Doe", "A2"));
        repository.addNewListing(new Car("BE240051", "Ola Nordmann", "A1"));
        */


    }
}
