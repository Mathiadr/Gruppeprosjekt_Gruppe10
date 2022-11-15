package modules;


import java.util.Date;

public class Listing {
    // Contains information of every bid, each of which belongs to one car.
    // TODO: EVERY CAR BELONGS TO ONE LISTING
    // TODO: ur mum
    private Date startDate;
    private Date endDate;
    private boolean isAvailable;
    private boolean isPublished;
    private String description;

    public Listing(){}

    public Listing(Date startDate, Date endDate, boolean isPublished, String description){
        this.startDate = startDate;
        this.endDate = endDate;
        this.isAvailable = true;
        this.isPublished = false;
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

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        this.isPublished = published;
    }

    @Override
    public String toString(){
        return  "   Start date: " + startDate + ",\n" +
                "   End date: " + endDate + ",\n" +
                "   isAvailable: " + isAvailable + ",\n" +
                "   isPublished : " + isPublished() + ",\n" +
                "   Description : " + description;
    }


}
