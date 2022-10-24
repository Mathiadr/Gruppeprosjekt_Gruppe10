public class Car {
    private String registrationNumber;
    private String owner;

    public Car(String registrationNumber, String owner) {
        this.registrationNumber = registrationNumber;
        this.owner = owner;
    }


    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
