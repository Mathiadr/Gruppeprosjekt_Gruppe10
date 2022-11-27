package tools.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tools.DateHandler;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import org.approvaltests.Approvals;

public class DateTest {

    // Provides an array of two dates to compare with
    public static Stream<Arguments> dateSet(){
        return Stream.of(
                Arguments.of((Object[]) new LocalDate[][]{{
                        LocalDate.of(2022, 1, 1),
                        LocalDate.of(2022, 12, 31)
                }}),
                Arguments.of((Object[]) new LocalDate[][]{{
                        LocalDate.of(2020, 2, 2),
                        LocalDate.of(2021, 2, 2)
                }}),
                Arguments.of((Object[]) new LocalDate[][]{{
                        LocalDate.of(2019, 6, 15),
                        LocalDate.of(2019, 12, 25)
                }}),
                Arguments.of((Object[]) new LocalDate[][]{{
                        LocalDate.of(2020, 11, 11),
                        LocalDate.of(2025, 11, 11)
                }}));
    }

    @ParameterizedTest
    @MethodSource(value = "dateSet")
    public void chronological_date_comparison_is_valid(LocalDate[] localDates){
        int startDate = 0;
        int endDate = 1;
        assertTrue(DateHandler.dateIsValid(localDates[startDate], localDates[endDate]));
        //startDate first, endDate second
    }

    @ParameterizedTest
    @MethodSource(value = "dateSet")
    public void non_chronological_date_comparison_is_invalid(LocalDate[] localDates){
        int startDate = 0;
        int endDate = 1;
        assertFalse(DateHandler.dateIsValid(localDates[endDate], localDates[startDate]));
        //endDate first and startDate second
    }

    @ParameterizedTest
    @MethodSource(value = "dateSet")
    public void same_date_comparison_is_valid(LocalDate[] localDates){
        int date = 0;
        assertTrue(DateHandler.dateIsValid(localDates[date], localDates[date]));
    }


    @Test
    // Due to the unordered nature of the Map class the report will seem chaotic, but it should be accurate
    public void month_number_to_string_mapper_is_accurate(){
        Map<String, Integer> monthMap = DateHandler.generateMonthsMapper();
        Approvals.verify(monthMap);
    }

    @Test
    // Due to the unordered nature of the Map class the report will seem chaotic, but it should be accurate
    public void month_has_correct_amount_of_days_with_2022_as_year(){
        int year = 2022;
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October"  , "November", "December"};
        Map<String, Integer> amountOfDaysPerMonthMap = new LinkedHashMap<>();
        for (int i = 0; i < 12; i++){
            amountOfDaysPerMonthMap.put(months[i], DateHandler.getDaysOfMonth(year, i+1));
        }
        Approvals.verify(amountOfDaysPerMonthMap);
    }

}
