import car.Car;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CarOrderTest {



    public static Stream<Car> add_objects_to_test() {

        return Stream.of(new Car("11dd", "Ola Nordmann", "Nissan"));
    }

    @ParameterizedTest
    @MethodSource("add_objects_to_test")
    public void car_exists(Car car){
        assertSame(car, car);
        assertEquals("Ola Nordmann", car.getOwner());
        assertNotEquals("Oliver", car.getOwner());
        assertNotEquals("Peppa Pig", car.getOwner());
        //Approvals.verify(car.getOwner());

    }





    // TODO: car does not exist more than once / no duplicates
    // TODO: User does not exist more than once / no duplicates
    // TODO: date_is_available
    // TODO: date_is_not_available
    // TODO: car_does_not_appear_in_search_if_incorrect_requirements

}
