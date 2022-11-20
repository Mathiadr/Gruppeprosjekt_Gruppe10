package modules;


import com.fasterxml.jackson.annotation.JsonFormat;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Listing {
    // Contains information of every bid, each of which belongs to one car.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private boolean isAvailable;
    private String description;

    public Listing(){}

    public Listing(LocalDate startDate, LocalDate endDate, boolean isAvailable, String description){
        this.startDate = startDate;
        this.endDate = endDate;
        this.isAvailable = isAvailable;
        this.description = description;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString(){
        return  "   Start date: " + startDate + ",\n" +
                "   End date: " + endDate + ",\n" +
                "   isAvailable: " + isAvailable + ",\n" +
                "   Description : " + description;
    }


}
