package modules.test;

import modules.Car;
import modules.CarRepository;
import modules.Listing;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CarRepositoryTest {

    CarRepository carRepository;

    @BeforeAll
    static void initializeCarRepository(){
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
    }

    @BeforeEach
    void resetCarRepository(){
        carRepository = new CarRepository("testRepository.json");
        carRepository.readFromJSON();
    }


    @Test
    public void new_car_is_created(){
        Car car = new Car("DFG441563", "Dummy one", "Volvo");
        assertNotNull(car);
    }


    @Test
    /*
     *  Tester krav:
     *      900. En utleier skal kunne registrer informasjon om kj??ret??y
     *      901. En utleier skal kunne oppgi bilmerke
     *      902. En utleier skal kunne oppgi bilens eier
     */
    public void car_is_added_to_repository(){
        Car car = new Car("DFG441563", "Dummy one", "Volvo");
        carRepository.addNewCar(car);
        assertTrue(carRepository.getCarArrayList().contains(car));
    }


    @Test
    /*
     *  Tester krav:
     *      915. En utleier skal ikke kunne ha flere biler registrert samtidig med samme registreringsnummer
     */
    public void car_duplicate_is_NOT_added_to_repository(){
        Car car1 = new Car("DFG441563", "Dummy one", "Volvo");
        carRepository.addNewCar(car1);
        carRepository.addNewCar(car1);
        int count = 0;
        for (Car car : carRepository.getCarArrayList()) {
            if (car == car1) count++;
        }
        assertEquals(1, count);
    }

    /*
     *  Tester krav:
     *      915. En utleier skal ikke kunne ha flere biler registrert samtidig med samme registreringsnummer
     */
    @Test
    public void car_with_existing_registration_number_is_NOT_added_to_repository(){
        Car car1 = new Car("DFG441563", "Dummy one", "Volvo");
        Car car2 = new Car("DFG441563", "Dummy two", "Toyota");
        carRepository.addNewCar(car1);
        carRepository.addNewCar(car2);
        assertFalse(carRepository.getCarArrayList().contains(car2));
    }


     /*  Tester krav:
     *      914. En utleier skal kunne slette registrert bil fra databasen, og tilh??rende annonse
     */
    @Test
    public void car_is_removed_from_repository(){ // TODO: save, Re-read and check
        Car car1 = new Car("DFG441563", "Dummy one", "Volvo");
        ArrayList<Car> carArrayList = carRepository.getCarArrayList();
        carRepository.addNewCar(car1);
        carRepository.saveCarsToJSON();
        carRepository.removeExistingCar(car1);
        assertFalse(carArrayList.contains(car1), "Specified car was not removed");
        carRepository.saveCarsToJSON();
        carRepository.readFromJSON();
        Approvals.verify(carRepository.getCarArrayList());
    }


    /*  Tester krav:
     *      1100. En utleier skal kunne opprette en annonse for utleie av kj??ret??y
     *      1104. En utleier kan ikke ha flere annonser knyttet til samme bil
     *      1106. En utleier skal kunne endre innhold i annonsen
     */
    @Test
    public void new_listing_replaces_old_listing(){
        Car car1 = new Car("DFG441563", "Dummy one", "Volvo",
                new Listing(null, null, true, "This is the original listing"));
        carRepository.addNewCar(car1);
        Listing updatedListing = new Listing(null, null, true, "This is the updated listing");
        carRepository.updateListing(car1, updatedListing);
        assertNotEquals("This is the original listing", car1.getListing().getDescription());
        assertEquals("This is the updated listing", car1.getListing().getDescription());
    }


    @Test

    /*  Tester krav:
     *      400. En leietaker skal kunne se en oversikt over tilgjengelige biler for utleie
     */
    public void list_of_available_cars_contains_all_available_cars(){
        carRepository.readFromJSON();
        Approvals.verify(carRepository.getAllAvailableCars());
    }

    @Test
    /*  Tester krav:
     *      400. En leietaker skal kunne se en oversikt over tilgjengelige biler for utleie
     */
    public void list_of_available_cars_does_not_contain_unavailable_cars(){
        carRepository.getCarArrayList().get(0).getListing().setAvailable(false);
        ArrayList<Car> availableCarList = carRepository.getAllAvailableCars();
        assertFalse(availableCarList.contains(carRepository.getCarArrayList().get(0)));
        Approvals.verify(carRepository.getAllAvailableCars());

    }

     /*  Tester krav:
     *      402. En leietaker skal kunne se tilgjengelige biler basert p?? dato
     */
    @Test
    public void list_of_available_cars_within_specified_date_contains_all_available_cars_within_rentable_period(){
        LocalDate startOfRentPeriod = LocalDate.of(2022, 2, 27);
        LocalDate endOfRentPeriod = LocalDate.of(2022, 3, 1);
        ArrayList<Car> availableCarList = carRepository.getAllAvailableCars(startOfRentPeriod, endOfRentPeriod);
        Approvals.verify(availableCarList);
    }


     /*  Tester krav:
     *      600. En leietaker skal kunne leie bil
     *      1105. En utleier skal kunne velge tidsperiode for n??r bilen er tilgjengelig for utleie
     *      1200. En utleier skal kunne leie ut bil
     */
    @Test
    public void can_rent_car_if_within_rentable_period(){
        LocalDate listingStartDate = LocalDate.of(2022, 1, 1);
        LocalDate listingEndDate = LocalDate.of(2022, 6, 6);
        Car car1 = new Car("DFG441563", "Dummy one", "Volvo",
                new Listing(listingStartDate, listingEndDate, true, "TEST DESCRIPTION"));
        carRepository.addNewCar(car1);
        LocalDate startOfRentPeriod = LocalDate.of(2022, 3, 3);
        LocalDate endOfRentPeriod = LocalDate.of(2022, 4, 4);

        assertEquals(1 , carRepository.initiateRentProcess(car1, startOfRentPeriod, endOfRentPeriod));
    }

    /*
     *  Tester krav:
     *      600. En leietaker skal kunne leie bil
     *      1105. En utleier skal kunne velge tidsperiode for n??r bilen er tilgjengelig for utleie
     *      1200. En utleier skal kunne leie ut bil
     */
    @Test
    public void cannot_rent_car_if_outside_of_rentable_period(){
        LocalDate listingStartDate = LocalDate.of(2022, 1, 1);
        LocalDate listingEndDate = LocalDate.of(2022, 6, 6);
        Car car1 = new Car("DFG441563", "Dummy one", "Volvo",
                new Listing(listingStartDate, listingEndDate, true, "TEST DESCRIPTION"));
        carRepository.addNewCar(car1);
        LocalDate startOfRentPeriod = LocalDate.of(2022, 4, 4);
        LocalDate endOfRentPeriod = LocalDate.of(2022, 7, 7);

        assertEquals(-1 , carRepository.initiateRentProcess(car1, startOfRentPeriod, endOfRentPeriod));
    }


    /*
     *  Tester krav:
     *      600. En leietaker skal kunne leie bil
     *      1105. En utleier skal kunne velge tidsperiode for n??r bilen er tilgjengelig for utleie
     *      1200. En utleier skal kunne leie ut bil
     */
    @Test
    public void cannot_rent_car_if_illogical_date(){
        LocalDate listingStartDate = LocalDate.of(2022, 1, 1);
        LocalDate listingEndDate = LocalDate.of(2022, 6, 6);
        Car car1 = new Car("DFG441563", "Dummy one", "Volvo",
                new Listing(listingStartDate, listingEndDate, true, "TEST DESCRIPTION"));
        carRepository.addNewCar(car1);
        LocalDate startOfRentPeriod = LocalDate.of(2022, 5, 5);
        LocalDate endOfRentPeriod = LocalDate.of(2022, 4, 4);

        assertEquals(-1 , carRepository.initiateRentProcess(car1, startOfRentPeriod, endOfRentPeriod));
    }

    /*
     *  Tester krav:
     *      600. En leietaker skal kunne leie bil
     */
    @Test
    public void car_is_not_available_after_renting(){
        LocalDate listingStartDate = LocalDate.of(2022, 1, 1);
        LocalDate listingEndDate = LocalDate.of(2022, 6, 6);
        Car newCar = new Car("DFG441563", "Dummy one", "Volvo",
                new Listing(listingStartDate, listingEndDate, true, "TEST DESCRIPTION"));
        carRepository.addNewCar(newCar);
        LocalDate startOfRentPeriod = LocalDate.of(2022, 3, 3);
        LocalDate endOfRentPeriod = LocalDate.of(2022, 4, 4);

        carRepository.initiateRentProcess(newCar, startOfRentPeriod, endOfRentPeriod);

        ArrayList<Car> availableCarList = carRepository.getAllAvailableCars();
        assertFalse(availableCarList.contains(newCar));
    }
}
