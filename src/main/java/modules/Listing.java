package modules;


import java.util.Date;

public class Listing {
    // Contains information of every bid, each of which belongs to one car.
    // TODO: EVERY CAR BELONGS TO ONE LISTING
    // TODO: ur mum
    private String publisherUsername;
    private Date startDate;
    private Date endDate;
    private Car car;
    private boolean isAvailable;
    private boolean published;
    private String description;

    public Listing(){}

    public Listing(String publisherUsername, Date startDate, Date endDate, Car car, boolean published, String description){
        this.publisherUsername = publisherUsername;
        this.startDate = startDate;
        this.endDate = endDate;
        this.car    = car;
        this.isAvailable = true;
        this.published = published;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
