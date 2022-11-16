import modules.Car;
import modules.CarRepository;
import modules.Listing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CarRepositoryTest {
    @Test
    public void new_car_is_created_and_added_to_ArrayList(){
        CarRepository carRepository = new CarRepository("testRepository.json");
        Car car1 = new Car("DFG441563", "Dummy one", "Volvo");
        carRepository.AddNewCar(car1);
        assertTrue(carRepository.getCarArrayList().contains(car1));
    }

    @Test
    public void JSON_writes_to_ArrayList_and_is_of_class_Car(){
        CarRepository carRepository = new CarRepository("testRepository.json");
        carRepository.readFromJSON();
        ArrayList<Car> carArrayList = carRepository.getCarArrayList();
        assertFalse(carArrayList.isEmpty());
        assertSame(carArrayList.get(0).getClass(), Car.class);
    }

    @Test
    public void JSON_does_not_contain_multiple_similar_registrationNumbers(){
        CarRepository carRepository = new CarRepository("testRepository.json");
        Car car1 = new Car("DFG441563", "Dummy one", "Volvo");
        Car car2 = new Car("DFG441563", "Dummy two", "Toyota");
        carRepository.AddNewCar(car1);
        carRepository.AddNewCar(car2);
        ArrayList<Car> carArrayList = carRepository.getCarArrayList();
        assertTrue(carArrayList.contains(car1), "Specified car was not added");
        assertFalse(carArrayList.contains(car2), "Two cars with the same registrationNumber exist");
    }

    @Test
    public void Car_is_removed_from_carArrayList(){
        CarRepository carRepository = new CarRepository("testRepository.json");
        Car car1 = new Car("DFG441563", "Dummy one", "Volvo");
        ArrayList<Car> carArrayList = carRepository.getCarArrayList();
        carRepository.AddNewCar(car1);
        assertTrue(carArrayList.contains(car1), "Specified car was not added");
        carRepository.RemoveExistingCar(car1);
        assertFalse(carArrayList.contains(car1), "Specified car was not removed");
    }

    @Test
    public void available_cars_list_does_not_contain_unavailable_cars(){
        CarRepository carRepository = new CarRepository("testRepository.json");
        Car car1 = new Car("DFG441563", "Dummy one", "Volvo");
        Car car2 = new Car("AFGH13543", "Dummy two", "Toyota");
        Listing listing1 = new Listing(null, null, true, null);
        Listing listing2 = new Listing(null, null, false, null);
        listing1.setAvailable(true);
        listing2.setAvailable(false);
        car1.setListing(listing1);
        car2.setListing(listing2);
        carRepository.AddNewCar(car1);
        carRepository.AddNewCar(car2);
        ArrayList<Car> availableCarsArrayList = carRepository.GetAllAvailableCars();
        assertTrue(availableCarsArrayList.contains(car1), "Available car returned as not available");
        assertFalse(availableCarsArrayList.contains(car2), "Unavailable car returned as available");
    }

    @Test
    public void car_listing_is_updated(){
        CarRepository carRepository = new CarRepository("testRepository.json");
        Car car1 = new Car("DFG441563", "Dummy one", "Volvo",
                new Listing(null, null, true, "This is the original listing"));
        carRepository.AddNewCar(car1);
        Listing updatedListing = new Listing(null, null, true, "This is the updated listing");
        carRepository.updateListing(car1, updatedListing);
        assertNotEquals("This is the original listing", car1.getListing().getDescription());
        assertEquals("This is the updated listing", car1.getListing().getDescription());
    }


    @Test
    public void car_becomes_unavailable_when_renting(){
        CarRepository carRepository = new CarRepository("testRepository.json");
        Car car1 = new Car("DFG441563", "Dummy one", "Volvo",
                new Listing(null, null, true, "TEST DESCRIPTION"));
        carRepository.AddNewCar(car1);
        carRepository.RentCar(car1);
        assertFalse(car1.getListing().isAvailable());
    }
}
