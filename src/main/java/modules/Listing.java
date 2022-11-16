package modules;


import java.util.Date;

public class Listing {
    // Contains information of every bid, each of which belongs to one car.
    // TODO: EVERY CAR BELONGS TO ONE LISTING
    // TODO: ur mum
    private Date startDate;
    private Date endDate;
    private boolean isAvailable;
    private String description;

    public Listing(){}

    public Listing(Date startDate, Date endDate, boolean isAvailable, String description){
        this.startDate = startDate;
        this.endDate = endDate;
        this.isAvailable = isAvailable;
        this.description = description;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
