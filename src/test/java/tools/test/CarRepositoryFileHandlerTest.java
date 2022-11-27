package tools.test;

import modules.Car;
import modules.CarRepository;
import modules.Listing;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tools.CarRepositoryFileHandler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CarRepositoryFileHandlerTest {
    CarRepository carRepository;
    CarRepositoryFileHandler carRepositoryFileHandler;

    @BeforeAll
    static void initializeCarRepository(){
        CarRepository carRepository = new CarRepository("testRepository.json");
        CarRepositoryFileHandler carRepositoryFileHandler = new CarRepositoryFileHandler();
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
    /*
     *  Tester krav:
     *      500. Databasen skal oppbevare informasjon om registrerte biler
     *      501. Databasen skal oppbevare bilmerke
     *      502. Databasen skal oppbevare navn på bilens eier
     *      600. Databasen skal oppbevare annonsen opprettet av utleier
     *      604. Databasen skal oppbevare tidsperiode for når bilen er tilgjengelig for utleie
     */
    public void repository_is_persistently_stored_in_file(){
        Car newCar = new Car("DFG441563", "Dummy one", "Volvo");
        carRepository.addNewCar(newCar);
        carRepository.saveCarsToJSON();
        carRepository.readFromJSON();
        Approvals.verify(carRepository.getCarArrayList());
    }

    @Disabled("Could not manage to get test to work. Kept to document attempt.")
    @Test
    public void writing_to_file_throws_exception_if_invalid(){
        String filename = "WrongFilename.txt";
        IOException ioException = assertThrows(IOException.class,
                () -> carRepositoryFileHandler.writeArrayListToFile(carRepository.getCarArrayList(), filename));
        assertEquals("", ioException.getMessage());
    }

    @Disabled("Could not manage to get test to work. Kept to document attempt.")
    @Test
    public void writing_to_file_throws_exception_if_arrayList_is_empty(){
        ArrayList<Car> carArrayList = new ArrayList<>(); // Empty
        NullPointerException nullPointerException =
                assertThrows(NullPointerException.class,
                () -> carRepositoryFileHandler.writeArrayListToFile(carArrayList, carRepository.getRepositoryName()));
        assertEquals("", nullPointerException.getMessage());
    }

    @Test
    /*
     *  Tester krav:
     *      500. Databasen skal oppbevare informasjon om registrerte biler
     *      501. Databasen skal oppbevare bilmerke
     *      502. Databasen skal oppbevare navn på bilens eier
     *      600. Databasen skal oppbevare annonsen opprettet av utleier
     *      604. Databasen skal oppbevare tidsperiode for når bilen er tilgjengelig for utleie
     */
    public void repository_reads_from_file(){
        carRepository.readFromJSON();
        Approvals.verify(carRepository.getCarArrayList());
    }
}
