import modules.Car;
import modules.CarRepository;
import modules.Listing;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CarRepositoryFileHandlerTest {
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
    public void repository_is_persistently_stored_in_file(){ //TODO: CHANGE
        Car newCar = new Car("DFG441563", "Dummy one", "Volvo");
        carRepository.addNewCar(newCar);
        carRepository.saveCarsToJSON();
        carRepository.readFromJSON();
        Approvals.verify(carRepository.getCarArrayList());
    }

    @Test
    public void repository_reads_from_file(){
        carRepository.readFromJSON();
        Approvals.verify(carRepository.getCarArrayList());
    }
}
