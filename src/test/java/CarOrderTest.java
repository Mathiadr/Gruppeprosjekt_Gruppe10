import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CarOrderTest {

    public static Stream<Car> add_objects_to_test() {
        return Stream.of(new Car("11dd", "Ola Nordmann"));
    }

    @ParameterizedTest
    @MethodSource("add_objects_to_test")
    public void car_exists(Car car){
        assertSame(car, car);
        assertEquals("Ola Nordmann", car.getOwner());
        assertNotEquals("Jens", car.getOwner());
        //Approvals.verify(car.getOwner());
    }

    // TODO: car does not exist more than once / no duplicates
    // TODO: User does not exist more than once / no duplicates
    // TODO: date_is_available
    // TODO: date_is_not_available
    // TODO: car_does_not_appear_in_search_if_incorrect_requirements

}
