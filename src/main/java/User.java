public class User {
    private String name;
    private int age;
    private String personalID;

    public User(String name, int age, String personalID) {
        this.name = name;
        this.age = age;
        this.personalID = personalID;

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPersonalID() {
        return personalID;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
