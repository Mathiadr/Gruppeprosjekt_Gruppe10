package tools;

import com.spun.util.velocity.ParserDateUtils;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.*;

public class DateHandler {
    // Currently, does not take into account renting over current year (like dec 2022 to jan 2023)
    public static int getDaysOfMonth(int monthInt){
        int year = Year.now().getValue();
        Month month = Month.of(monthInt);
        YearMonth yearMonth = YearMonth.of(year, month);
        return yearMonth.lengthOfMonth();
    }

    public static Map<String, Integer> generateMonthsMapper(){

        Map<String ,Integer> monthsMap = new HashMap<String, Integer>();
        monthsMap.put("January", Calendar.JANUARY+1);
        monthsMap.put("February", Calendar.FEBRUARY+1);
        monthsMap.put("March", Calendar.MARCH+1);
        monthsMap.put("April", Calendar.APRIL+1);
        monthsMap.put("May", Calendar.MAY+1);
        monthsMap.put("June", Calendar.JUNE+1);
        monthsMap.put("July", Calendar.JULY+1);
        monthsMap.put("August", Calendar.AUGUST+1);
        monthsMap.put("September", Calendar.SEPTEMBER+1);
        monthsMap.put("October"  , Calendar.OCTOBER+1);
        monthsMap.put("November", Calendar.NOVEMBER+1);
        monthsMap.put("December", Calendar.DECEMBER+1);
        return monthsMap;
    }

}
