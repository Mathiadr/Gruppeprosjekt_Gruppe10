package tools;

import com.spun.util.velocity.ParserDateUtils;
import modules.Car;
import modules.Listing;

import javax.swing.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.*;

public class DateHandler {
    // Currently, does not take into account rent periods that cross between years (like dec 2022 to jan 2023)
    public static int getDaysOfMonth(int monthInt){
        int year = Year.now().getValue();
        Month month = Month.of(monthInt);
        YearMonth yearMonth = YearMonth.of(year, month);
        return yearMonth.lengthOfMonth();
    }

    public static int getDaysOfMonth(int yearInt, int monthInt){
        Month month = Month.of(monthInt);
        YearMonth yearMonth = YearMonth.of(yearInt, month);
        return yearMonth.lengthOfMonth();
    }

    // Generated a map which links the name of the months with their numeral version
    public static Map<String, Integer> generateMonthsMapper(){
        Map<String ,Integer> monthsMap = new LinkedHashMap<String, Integer>();
        monthsMap.put( "JANUARY",  Calendar.JANUARY+1);
        monthsMap.put( "FEBRUARY",  Calendar.FEBRUARY+1);
        monthsMap.put( "MARCH",  Calendar.MARCH+1);
        monthsMap.put( "APRIL",  Calendar.APRIL+1);
        monthsMap.put( "MAY",  Calendar.MAY+1);
        monthsMap.put( "JUNE",  Calendar.JUNE+1);
        monthsMap.put( "JULY",  Calendar.JULY+1);
        monthsMap.put( "AUGUST",  Calendar.AUGUST+1);
        monthsMap.put( "SEPTEMBER",  Calendar.SEPTEMBER+1);
        monthsMap.put( "OCTOBER",  Calendar.OCTOBER+1);
        monthsMap.put( "NOVEMBER",  Calendar.NOVEMBER+1);
        monthsMap.put( "DECEMBER",  Calendar.DECEMBER+1);

        return monthsMap;
    }

    // Updates the days within the ComboBoxModel depending on the month selected
    public static void updateDaysInComboBox(Object o, DefaultComboBoxModel<Integer> defaultComboBoxModel){
        defaultComboBoxModel.removeAllElements();
        int monthInt = DateHandler.generateMonthsMapper().get((String)o);
        int daysInMonth = DateHandler.getDaysOfMonth(monthInt);
        for (int day = 1; day <= daysInMonth; day++){
            defaultComboBoxModel.addElement(day);
        }
    }

    // Checks if the given startDate and endDate are valid (e.g endDate is not before startDate)
    public static boolean dateIsValid(LocalDate startDate, LocalDate endDate){
        if (startDate.isBefore(endDate)) return true;
        else return startDate.isEqual(endDate);
    }

    // Checks if the given rent period is within the date of the given car's listing
    public static boolean rentPeriodIsValid(Car car, LocalDate startOfRentPeriod, LocalDate endOfRentPeriod){
        Listing listing = car.getListing();
        if(!listing.isAvailable()) return false;
        if(!dateIsValid(startOfRentPeriod, endOfRentPeriod)) return false;
        else return dateIsValid(listing.getStartDate(), startOfRentPeriod)
                && dateIsValid(endOfRentPeriod, listing.getEndDate());
    }
}
