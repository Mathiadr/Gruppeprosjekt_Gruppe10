import modules.Car;
import modules.CarRepository;
import modules.Listing;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CarRepositoryTest {

    CarRepository carRepository;

    @BeforeEach
    void initializeCarRepository(){
        carRepository = new CarRepository("testRepository.json");
    }

    @Test
    /*
    *
    *
    *
    */
    public void new_car_is_creatable(){
        Car car1 = new Car("DFG441563", "Dummy one", "Volvo");
        assertNotNull(car1);
    }


    @Test
    public void car_is_added_to_repository(){
        Car car1 = new Car("DFG441563", "Dummy one", "Volvo");
        assertNotNull(car1);
        carRepository.addNewCar(car1);
        assertTrue(carRepository.getCarArrayList().contains(car1));
    }

    @Test
    public void car_duplicate_is_NOT_added_to_repository(){
        Car car1 = new Car("DFG441563", "Dummy one", "Volvo");
        assertNotNull(car1);
        carRepository.addNewCar(car1);
        assertTrue(carRepository.getCarArrayList().contains(car1));
    }

    @Test
    public void repository_is_persistently_stored(){ //TODO: CHANGE
        Car car1 = new Car("DFG441563", "Dummy one", "Volvo");
        Car car2 = new Car("DFG441563", "Dummy two", "Toyota");
        carRepository.addNewCar(car1);
        carRepository.addNewCar(car2);
        ArrayList<Car> carArrayList = carRepository.getCarArrayList();
        assertTrue(carArrayList.contains(car1), "Specified car was not added");
        assertFalse(carArrayList.contains(car2), "Two cars with the same registrationNumber exist");
    }

    @Test
    public void repository_reads_from_persistent_storage(){
        carRepository.readFromJSON();
        Approvals.verify(carRepository.getCarArrayList());
    }

    @Test
    public void car_is_removed_from_repository(){ // TODO: save, Re-read and check
        Car car1 = new Car("DFG441563", "Dummy one", "Volvo");
        ArrayList<Car> carArrayList = carRepository.getCarArrayList();
        carRepository.addNewCar(car1);
        assertTrue(carArrayList.contains(car1), "Specified car was not added");
        carRepository.removeExistingCar(car1);
        assertFalse(carArrayList.contains(car1), "Specified car was not removed");
    }

    @Test
    public void car_listing_is_updated(){
        Car car1 = new Car("DFG441563", "Dummy one", "Volvo",
                new Listing(null, null, true, "This is the original listing"));
        carRepository.addNewCar(car1);
        Listing updatedListing = new Listing(null, null, true, "This is the updated listing");
        carRepository.updateListing(car1, updatedListing);
        assertNotEquals("This is the original listing", car1.getListing().getDescription());
        assertEquals("This is the updated listing", car1.getListing().getDescription());
    }

    @Test
    public void new_listing_date_replaces_old_listing_date(){

    }

    @Test
    public void list_of_available_cars_contains_available_cars(){
        carRepository.readFromJSON();
        //Approvals.verify(carRepository.getAllAvailableCars()); // TODO : FIXME
    }

    @Test
    public void list_of_available_cars_does_not_contain_unavailable_cars(){

    }

    @Test
    public void car_becomes_unavailable_when_renting(){
        Car car1 = new Car("DFG441563", "Dummy one", "Volvo",
                new Listing(null, null, true, "TEST DESCRIPTION"));
        carRepository.addNewCar(car1);
        // FIXME
        //assertFalse(car1.getListing().isAvailable());
    }

    @Test
    public void car_is_not_available_when_renting(){

    }

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


    // TODO: tests for validation and invalidation
    // TODO: create Search class & tests
    // TODO:
    // TODO: GUI tests
}
