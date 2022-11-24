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
        monthsMap.put("January",    Calendar.JANUARY+1);
        monthsMap.put("February",   Calendar.FEBRUARY+1);
        monthsMap.put("March",      Calendar.MARCH+1);
        monthsMap.put("April",      Calendar.APRIL+1);
        monthsMap.put("May",        Calendar.MAY+1);
        monthsMap.put("June",       Calendar.JUNE+1);
        monthsMap.put("July",       Calendar.JULY+1);
        monthsMap.put("August",     Calendar.AUGUST+1);
        monthsMap.put("September",  Calendar.SEPTEMBER+1);
        monthsMap.put("October"  ,  Calendar.OCTOBER+1);
        monthsMap.put("November",   Calendar.NOVEMBER+1);
        monthsMap.put("December",   Calendar.DECEMBER+1);

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
        // TODO: IMPLEMENT
        if (startDate.isBefore(endDate)) return true;
        else return startDate.isEqual(endDate);
    }

    // Checks if the given rent period is within the date of the given car's listing
    public static boolean rentPeriodIsValid(Car car, LocalDate startOfRentPeriod, LocalDate endOfRentPeriod){
        // TODO: IMPLEMENT
        Listing listing = car.getListing();
        if(!listing.isAvailable()) return false;
        if(!dateIsValid(startOfRentPeriod, endOfRentPeriod)) return false;
        else return dateIsValid(listing.getStartDate(), startOfRentPeriod)
                && dateIsValid(endOfRentPeriod, listing.getEndDate());
    }
}
