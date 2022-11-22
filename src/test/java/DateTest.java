import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tools.DateHandler;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DateTest {

    public static LocalDate[][] dateSet(){
        return new LocalDate[][]{
                {LocalDate.of(2022, 1, 1),    LocalDate.of(2022, 12, 31)},
                {LocalDate.of(2020, 2, 2),    LocalDate.of(2021, 2, 2)},
                {LocalDate.of(2019, 4, 20),    LocalDate.of(2019, 4, 20)},
                {LocalDate.of(2020, 11, 11),    LocalDate.of(2030, 11, 11)}
        };
    }
    public static Stream<Arguments> dateSet1(){
        return Stream.of(
                Arguments.of((Object[]) new LocalDate[]{
                        LocalDate.of(2022, 1, 1),
                        LocalDate.of(2022, 12, 31)
                }),
                Arguments.of((Object[]) new LocalDate[]{
                        LocalDate.of(2020, 2, 2),
                        LocalDate.of(2021, 2, 2)
                }),
                Arguments.of((Object[]) new LocalDate[]{
                        LocalDate.of(2019, 4, 20),
                        LocalDate.of(2019, 4, 20)
                }),
                Arguments.of((Object[]) new LocalDate[]{
                        LocalDate.of(2020, 11, 11),
                        LocalDate.of(2030, 11, 11)
                }));
    }

    @ParameterizedTest
    @MethodSource(value = "dateSet1")
    public void valid_date_comparison_is_valid(LocalDate[] localDates){
        int startDate = 0;
        int endDate = 1;
        assertTrue(DateHandler.dateIsValid(localDates[startDate], localDates[endDate]));
    }

    @ParameterizedTest
    @MethodSource(value = "dateSet")
    public void invalid_date_comparison_is_invalid(LocalDate[] localDates){
        int startDate = 0;
        int endDate = 1;
        assertFalse(DateHandler.dateIsValid(localDates[endDate], localDates[startDate]));
    }

    @Test
    public void valid_rent_period_is_valid(){

    }

    @Test
    public void invalid_rent_period_is_invalid(){

    }

    @Test
    public void name_of_month_is_equivalent_to_numerical_version(){

    }

    @Test
    public void month_has_correct_amount_of_days(){}

}
