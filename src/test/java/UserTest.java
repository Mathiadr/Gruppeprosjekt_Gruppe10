import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test
    public void user_exists(){
        User user = new User("Mathias Ratdal", 23, "12312313");
        assertEquals("Mathias Ratdal", user.getName());
    }



}
